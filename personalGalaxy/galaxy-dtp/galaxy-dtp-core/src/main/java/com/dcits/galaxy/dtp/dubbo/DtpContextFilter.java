package com.dcits.galaxy.dtp.dubbo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.DtpContext.TransactionObject;
import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchPropagate;
import com.dcits.galaxy.dtp.branch.BranchPropagate.Propagation;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.sequence.BxidSequence;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;

@Activate(group = { Constants.PROVIDER, Constants.CONSUMER })
public class DtpContextFilter implements Filter {

	private static final String DTP_STATUS = "dtp_status";
	private static final String DTP_TRUNK = "dtp_trunk";
	private static final String DTP_BRANCH = "dtp_branch";
	private static final String DTP_SUBMITLOG = "dtp_submitlog";
	private static final String DTP_INDEX = "dtp_index";

	private BxidSequence bxidSequence = null;

	private PlatformTransactionManager tm = null;
	private TransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	
	private Map<Class<?>, Propagation> propagationMap = new ConcurrentHashMap<>();

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {

		// 忽略injvm协议
		if (Constants.LOCAL_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
			return invoker.invoke(invocation);
		}
		
		Class<?> interfaceClass = invoker.getInterface();
		
		Propagation propagation = propagationMap.get(interfaceClass);
		
		if(propagation == null) {
			BranchPropagate branchPropagate = interfaceClass.getAnnotation(BranchPropagate.class);
			
			if(branchPropagate == null) {
				propagation = Propagation.NEW;
			} else {
				propagation = branchPropagate.value();
			}
			propagationMap.put(interfaceClass, propagation);			
		}

		if(Propagation.NONE.equals(propagation)) {
			return invoker.invoke(invocation);
		}

		String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);

		if (Constants.CONSUMER_SIDE.equals(side)) {
			handleConsumer((RpcInvocation) invocation);
			return invoker.invoke(invocation);
		} else if (Constants.PROVIDER_SIDE.equals(side)) {
			return handleProvider(invoker, invocation, Propagation.NEW.equals(propagation));
		}

		return invoker.invoke(invocation);
	}

	private void handleConsumer(RpcInvocation invocation) {

		if (DtpContext.isInDtp()) {
			
			TrunkStatus status = DtpContext.getStatus();
			String txid = DtpContext.getTxid();
			String bxid = DtpContext.getBxid();
			String logid = DtpContext.getLogid();
			
			if (txid == null) {
				throw new DTPException("txid must not null!");
			}
			
			invocation.setAttachment(DTP_STATUS, status.name());
			invocation.setAttachment(DTP_TRUNK, txid);
			
			if(bxid != null){
				invocation.setAttachment(DTP_BRANCH, bxid);
			}
			
			if(status.equals(TrunkStatus.prepare)) {
				if(logid != null) {
					throw new DTPException("can't allow rpc invoke in submitLog method!");
				}
				
				int index;
				if(bxid == null){
					index = DtpContext.nextTrunkIndex();
				} else {
					index = DtpContext.nextBranchIndex();
				}
				
				invocation.setAttachment(DTP_INDEX, Integer.toString(index));
			} else {
				invocation.setAttachment(DTP_SUBMITLOG, logid);
			}
		}
	}

	private Result handleProvider(Invoker<?> invoker, Invocation invocation, boolean newBranch) {

		String status = invocation.getAttachment(DTP_STATUS);
		String txid = invocation.getAttachment(DTP_TRUNK);
		String bxid = invocation.getAttachment(DTP_BRANCH);
		String logid = invocation.getAttachment(DTP_SUBMITLOG);
		
		if (txid != null) {
			
			Result result = null;
			
			TrunkStatus trunkStatus = TrunkStatus.valueOf(status);
			
			TransactionObject object = new TransactionObject();
			object.setTxid(txid);
			object.setStatus(trunkStatus);
			object.setBxid(bxid);
			
			DtpContext.setTransactionObject(object);
			
			try {
				if(newBranch && trunkStatus.equals(TrunkStatus.prepare)) {
					int index = 0;
					try {
						index = Integer.parseInt(invocation.getAttachment(DTP_INDEX));
					} catch (Exception e) {
						//ignore
					}
					
					if (bxid == null){
						object.setTrunkIndex(index);
					} else {
						object.setBranchIndex(index);
					}
					beginBranch();
					PlatformTransactionManager tm = getTransactionManager();
					TransactionStatus transactionStatus = tm.getTransaction(definition);
					
					try {
						result = invoker.invoke(invocation);
					} finally {
						tm.rollback(transactionStatus);
					}
				} else {
					DtpContext.setLogid(logid);
					result = invoker.invoke(invocation);
				}
			} finally {
				DtpContext.clean();
			}
			return result;
		}
		return invoker.invoke(invocation);
	}

	private PlatformTransactionManager getTransactionManager() {
		if (tm != null)
			return tm;

		try {
			tm = SpringApplicationContext.getContext().getBean(PlatformTransactionManager.class);
		} catch (Exception e) {
			throw new DTPException("occur exception when get instance of PlatformTransactionManager, cause: ", e);
		}

		if (tm == null) {
			throw new DTPException("can't get instance of PlatformTransactionManager. ");
		}
		return tm;
	}

	private BxidSequence getBxidSequence() {

		if (bxidSequence != null)
			return bxidSequence;

		try {
			bxidSequence = SpringApplicationContext.getContext().getBean(BxidSequence.class);
		} catch (Exception e) {
			throw new DTPException("occur exception when get instance of BxidSequence, cause: ", e);
		}

		if (bxidSequence == null) {
			throw new DTPException("can't get instance of BxidSequence. ");
		}

		return bxidSequence;
	}

	private void beginBranch() {
		String txid = DtpContext.getTxid();
		String parentBxid = DtpContext.getBxid();
		
		String bxid = getBxidSequence().nextBxid();
		if(parentBxid == null) {
			int indexInTrunk = DtpContext.nextTrunkIndex();
			BranchManagerHelper.begin(bxid, indexInTrunk, txid);
		} else {
			int indexInBranch = DtpContext.nextBranchIndex();
			BranchManagerHelper.begin(bxid, indexInBranch, txid, parentBxid);
		}
	}
}
