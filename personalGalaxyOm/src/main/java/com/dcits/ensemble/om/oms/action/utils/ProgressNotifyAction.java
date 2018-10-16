package com.dcits.ensemble.om.oms.action.utils;

import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * ProgressNotifyAction--执行进度通知Action* 
 * @author tangxlf
 * @date 2015-08-27
 */
@Controller
public class ProgressNotifyAction {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("findProgressNotify")
    public void findFileProgress(HttpServletRequest request, PrintWriter printWriter) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] messageArray = ProgressMessageUtil.getCurrentProgress((String) request.getSession().getAttribute("UserName"));
        result.put(SysConfigConstants.PROGRESS_NUM, messageArray[0]);
        result.put(SysConfigConstants.PROGRESS_MESSAGE, messageArray[1]);
        if (messageArray[0].equals("0")) {
            result.put(SysConfigConstants.PROGRESS_MESSAGE, "操作结束");
        } else {
            log.info("PROGRESS_NUM=" + messageArray[0] + "  PROGRESS_MESSAGE=" + messageArray[1]);
        }
        ActionResultWrite.setMapResult(printWriter, result);
    }
}
