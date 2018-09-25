package com.dcits.galaxy.dtp.resume;

import org.I0Itec.zkclient.ZkClient;

import com.dcits.galaxy.dtp.zk.ZKSingleBuilder;

/**
 * 事务恢复上下文: 为事务恢复做些初始化的工作。如创建znode父目录
 * @author Yin.Weicai
 */
public class RecoveryConfig {
	
public final static String ZNode_separator = "/";
	
	public final static String ZNode_dtp = "/dtp";
	
	public final static String ZNode_dtp_recovery = "/dtp/recovery";
	
	public final static String ZNode_dtp_recovery_trunk = "/dtp/recovery/trunk";
	
	public final static String ZNode_dtp_recovery_branch = "/dtp/recovery/branch";
	
	/**
	 * 初始化
	 */
	public static void init(String appGroup){
		
		ZkClient zkClient = ZKSingleBuilder.getZkClient();
		
		boolean isExists = zkClient.exists( ZNode_dtp );
		if( !isExists){
			zkClient.createPersistent( ZNode_dtp );
		}
		
		isExists = zkClient.exists( ZNode_dtp_recovery );
		if( !isExists){
			zkClient.createPersistent( ZNode_dtp_recovery );
		}
		
		isExists = zkClient.exists( ZNode_dtp_recovery_trunk );
		if( !isExists){
			zkClient.createPersistent( ZNode_dtp_recovery_trunk );
		}
		
		String trunkGroupPath = ZNode_dtp_recovery_trunk + ZNode_separator + appGroup;
		isExists = zkClient.exists( trunkGroupPath );
		if( !isExists){
			zkClient.createPersistent( trunkGroupPath );
		}
		
		isExists = zkClient.exists( ZNode_dtp_recovery_branch );
		if( !isExists){
			zkClient.createPersistent( ZNode_dtp_recovery_branch );
		}
		
		String branchGroupPath = ZNode_dtp_recovery_trunk + ZNode_separator + appGroup;
		isExists = zkClient.exists( branchGroupPath );
		if( !isExists){
			zkClient.createPersistent( branchGroupPath );
		}
	}
	
	public static String getTrunkLockPath(String appGroup){
		return ZNode_dtp_recovery_trunk + ZNode_separator + appGroup + ZNode_separator + "lock";
	}
	
	public static String getBranchLockPath( String appGroup ){
		return ZNode_dtp_recovery_trunk + ZNode_separator + appGroup + ZNode_separator + "lock";
	}
	
	public static String getTrunkAppGroupPath( String appGroup ){
		return ZNode_dtp_recovery_trunk + ZNode_separator + appGroup ;
	}
	
	public static String getBranchAppGroupPath( String appGroup){
		return ZNode_dtp_recovery_trunk + ZNode_separator + appGroup ;
	}
	
}