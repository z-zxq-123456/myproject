package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.app.AppPathHelp;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * LogViewUtil* 
 * @author LuoLang
 * @date 2016-01-13
 */
@Controller
public class LogViewAction {

	@Resource
	ShellExcuteService shellExcuteService;
	@Resource
	IService omsBaseService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ParamComboxService paramComboxService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("checkLog")
	public void check(HttpServletRequest request, PrintWriter printWriter, String appIntantId, String lineNumber) {
		EcmAppIntant intant = new EcmAppIntant();
		intant.setAppIntantId(Integer.parseInt(appIntantId));
		List<EcmAppIntant> dataList = omsBaseService.findListByCond(intant);
		EcmAppIntant newIntant = new EcmAppIntant();
		newIntant = dataList.get(0);
		String fileName = AppPathHelp.createLogPath(newIntant, paramComboxService);
		ICmd cmd = cmdFactory.getCmd(newIntant.getSerOs());
		try {
			String tailCmd = cmd.tailCmd(lineNumber + " " + fileName);//拼命令
			ShellResult result = shellExcuteService.doCmd(newIntant.getSerIp(), newIntant.getSerUser(), newIntant.getSerPwd(), tailCmd);
			ActionResultWrite.setReadResult(printWriter, result.getOutList());
		} catch (Exception e) {
			log.error("查询日志失败：" + e.getMessage());
			ActionResultWrite.setErrorResult(printWriter, "查询日志失败：" + e.getMessage());
		}
	}
}