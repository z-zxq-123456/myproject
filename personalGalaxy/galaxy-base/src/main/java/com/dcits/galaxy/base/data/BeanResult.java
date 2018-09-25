package com.dcits.galaxy.base.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.exception.*;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 供第三方接口服务结果对象实现
 *
 * @author Tim
 */
public class BeanResult extends AbstractBean {

	private static final Logger logger = LoggerFactory.getLogger(BeanResult.class);

	private static final long serialVersionUID = -6659043338199929298L;

	private String retStatus;

	private Results rs;

	private IAppHead appHead;

	private BaseResponse response;

	// 九鼎问题修改增加强制事务提交异常判断，增加异常保留。 2017/04/06 for Tim
	private Throwable throwable;

	/**
	 * 无返回body的成功结果<br>
	 * 用于成功场景
	 */
	public BeanResult() {
		addResult(GalaxyConstants.CODE_SUCCESS, GalaxyConstants.MESSAGE_SUCCESS);
		setRetStatus();
	}

	/**
	 * 通过异常组织报文。
	 *
	 * @param throwable
	 */
	public BeanResult(Throwable throwable) {
		if (logger.isErrorEnabled()) {
			logger.error("BeanResult:" + throwable.getMessage());
		} else if(logger.isDebugEnabled()) {
			logger.debug(ExceptionUtils.getStackTrace(throwable));
		}
		Results rs = null;
		String retStatus = GalaxyConstants.STATUS_FAILED;
		if (throwable instanceof BusinessException) {
			// 处理授权、确认类异常的RetStatus
			if (throwable instanceof WithoutAuthorizationException)
				retStatus = GalaxyConstants.STATUS_AUTH;
			else if (throwable instanceof WithoutConfirmationException)
				retStatus = GalaxyConstants.STATUS_CONF;
			else if (throwable instanceof WithoutAuthAndConfirmException)
				retStatus = GalaxyConstants.STATUS_AUTH_CONF;
			rs = ((BusinessException) throwable).getRets();
		} else if (throwable instanceof GalaxyException) {
			if (throwable instanceof MultiBusinessException) {
				MultiBusinessException mbExcepton = (MultiBusinessException) throwable;
				TwoTuple<String, Results> twp = mbExcepton.getRet();
				retStatus = twp.first;
				rs = new Results(twp.second.getRet());
			}else{
				rs = new Results(GalaxyConstants.CODE_FAILED, throwable.getMessage());
				// 应核心要求，增加对SQLException的处理。modify by Tim 20170413
			}

		} else if (throwable instanceof SQLException) {
			rs = new Results("999994", ErrorUtils.getParseErrorDesc("999994", new String[] {
					String.valueOf(((SQLException) throwable).getErrorCode()), throwable.getMessage() }));
		} else if (throwable.getCause() instanceof SQLException) {
			rs = new Results("999994",
					ErrorUtils.getParseErrorDesc("999994",
							new String[] { String.valueOf(((SQLException) throwable.getCause()).getErrorCode()),
									throwable.getCause().getMessage() }));
		}

		else {
			rs = new Results(GalaxyConstants.CODE_FAILED,
					throwable.getClass().getSimpleName() + ":" + throwable.getMessage());
		}
		this.rs = rs;
		this.retStatus = retStatus;
		// 九鼎问题修改增加强制事务提交异常判断，增加异常保留。 2017/04/06 for Tim
		this.throwable = throwable;
	}

	/**
	 * 成功结果含结果Body<br>
	 * 用于成功场景
	 *
	 * @param response
	 *            响应报文体数据
	 */
	public BeanResult(BaseResponse response) {
		addResult(GalaxyConstants.CODE_SUCCESS, GalaxyConstants.MESSAGE_SUCCESS);
		setRetStatus();
		this.response = response;
	}

	/**
	 * 成功结果含结果Body<br>
	 * 用于成功场景
	 *
	 * @param response
	 *            响应报文体数据
	 * @param appHead
	 *            分页应用头返回数据
	 */
	public BeanResult(BaseResponse response, IAppHead appHead) {
		addResult(GalaxyConstants.CODE_SUCCESS, GalaxyConstants.MESSAGE_SUCCESS);
		setRetStatus();
		this.response = response;
		this.appHead = appHead;
	}

	/**
	 * 设置结果集合，根据结合结果判断状态
	 *
	 * @param rs
	 *            响应结果
	 */
	public BeanResult(Results rs) {
		this.rs = rs;
		setRetStatus();
	}

	/**
	 * 设置结果集合，自定义结果状态
	 *
	 * @param rs
	 *            响应结果
	 * @param retStatus
	 *            响应状态
	 */
	public BeanResult(Results rs, String retStatus) {
		this.rs = rs;
		this.retStatus = retStatus;
	}

	/**
	 * 指定错误码的返回结果<br>
	 * 用于失败场景
	 *
	 * @param errCode
	 *            响应码
	 * @param errMsg
	 *            响应信息
	 */
	public BeanResult(String errCode, String errMsg) {
		addResult(errCode, errMsg);
		setRetStatus();
	}

	/**
	 * 指定错误码集合返回结果<br>
	 * 用于失败场景
	 *
	 * @param errCode
	 *            响应码集合
	 * @param errMsg
	 *            响应信息集合
	 */
	public BeanResult(String[] errCode, String[] errMsg) {
		if (null != errCode && errCode.length > 0 && null != errMsg && errMsg.length > 0
				&& errMsg.length == errMsg.length) {
			for (int i = 0; i < errCode.length; i++) {
				addResult(errCode[i], errMsg[i]);
			}
			setRetStatus();
		}
	}

	private void addResult(String errCode, String errMsg) {
		if (null == rs)
			rs = new Results();
		if (null == errCode && null == errMsg) {
			return;
		}
		// 检查错误码是否存在重复
		boolean exist = false;
		for (Result result : rs.rets) {
			if (result.getRetCode().equals(errCode) && result.getRetMsg().equals(errMsg))
				exist = true;
			break;
		}
		if (exist)
			return;
		List<Result> result = rs.getRets();
		result.add(new Result(errCode, errMsg));
	}

	public void mergeResult(BeanResult br) {
		if (null == br || null == br.getRs())
			return;
		for (Result r : br.getRs().getRets()) {
			addResult(r.getRetCode(), r.getRetMsg());
		}
		setRetStatus();
		if (GalaxyConstants.STATUS_SUCCESS.equals(this.retStatus)) {
			// 后面服务的Response会覆盖原来原子服务的结果
			this.response = br.response;
			this.appHead = br.appHead;
			// 九鼎问题修改增加强制事务提交异常判断，增加异常保留。 2017/04/06 for Tim
			this.throwable = null;
		} else {
			// 如果失败，清空当前的响应
			this.response = null;
			this.appHead = null;
			// 九鼎问题修改增加强制事务提交异常判断，增加异常保留。 2017/04/06 for Tim
			if (br.throwable != null) {
				this.throwable = br.throwable;
			}
		}
	}

	public Results getRs() {
		return rs;
	}

	public void setRs(Results rs) {
		this.rs = rs;
		setRetStatus();
	}

	public JSONArray getRetJsonObject() {
		JSONArray rets = new JSONArray();
		JSONObject ret;
		if (null == rs)
			return new JSONArray();
		for (Result r : rs.getRets()) {
			ret = new JSONObject();
			ret.put(GalaxyConstants.RET_CODE, r.getRetCode());
			ret.put(GalaxyConstants.RET_MSG, r.getRetMsg());
			rets.add(ret);
		}
		return rets;
	}

	public String getRetStatus() {
		if (null == retStatus) {
			setRetStatus();
		}
		return retStatus;
	}

	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus;
	}

	private void setRetStatus() {
		// modify for sonar
		if (null == rs)
			return;
		// 一个错误结果时
		if (!rs.isEmpty() && rs.getRets().size() == 1) {
			// 有且仅有一个错误码为000000；则成功，否则失败
			if (GalaxyConstants.CODE_SUCCESS.equals(rs.rets.get(0).getRetCode())) {
				retStatus = GalaxyConstants.STATUS_SUCCESS;
			} else {
				retStatus = GalaxyConstants.STATUS_FAILED;
			}
		} else {
			// 检查是否全部成功
			boolean success = false;
			boolean fail = false;
			List<Result> failResults = new ArrayList<Result>();
			for (Result result : rs.rets) {
				// 检查是否含有000000结果
				if (GalaxyConstants.CODE_SUCCESS.equals(result.getRetCode())) {
					success = true;
				} else {
					fail = true;
					failResults.add(result);
				}
			}

			// 全错
			if (!success && fail) {
				retStatus = GalaxyConstants.STATUS_FAILED;
				// 全成功
			} else if (success && !fail) {
				retStatus = GalaxyConstants.STATUS_SUCCESS;
			} else if (success && fail) {
				// 过滤掉000000错误码
				rs = new Results(failResults);
				retStatus = GalaxyConstants.STATUS_FAILED;
			}
		}
	}

	public BaseResponse getResponse() {
		return response;
	}

	public String toString() {
		return "RetStatus [" + retStatus + "]\n" + rs + "\nResponse [\n" + (null == response ? "" : response.toString())
				+ "\n]";
	}

	public IAppHead getAppHead() {
		return appHead;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}