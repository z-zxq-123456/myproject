package com.dcits.ensemble.om.oms.action.middleware;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.middleware.SeqInfo;
import com.dcits.ensemble.om.oms.service.middleware.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author maruie
 * @date   2016-01-12
 * ReqManagerAction
 * redis 主键维护操作
 */

@Controller
@RequestMapping("/RedisKsy")
public class ReqManagerAction {
    @Resource
    MonitorService service;

    @RequestMapping("saveReqManager")
    public void save(HttpServletRequest request, PrintWriter printWriter, SeqInfo seqInfo) {
        if (seqInfo == null || seqInfo.getRedisUrl() == null || StringUtils.isEmpty(seqInfo.getRedisUrl())) {
            ActionResultWrite.setErrorResult(printWriter, "操作错误");
            return;
        }
        service.saveReqIntoServer(seqInfo);
        ActionResultWrite.setsuccessfulResult(printWriter);
    }

    @RequestMapping("findReqManager")
    public void find(HttpServletRequest request, PrintWriter printWriter) {
        List<SeqInfo> infoList;
        String serverUrl = request.getParameter("redisUrl");
        String masterName = request.getParameter("masterName");
        if (serverUrl == null || serverUrl.trim().equals("")) {
            infoList = new ArrayList<SeqInfo>();
        } else {
            infoList = service.findReqFromServer(serverUrl, masterName);
        }
        if(infoList == null)
            infoList = new ArrayList<>();
        ActionResultWrite.setReadResult(printWriter, infoList);
    }

    @RequestMapping("deleteReqManager")
    public void delete(HttpServletRequest request, PrintWriter printWriter, SeqInfo seqInfo) {
        if (seqInfo == null || seqInfo.getRedisUrl() == null || StringUtils.isEmpty(seqInfo.getRedisUrl())) {
            ActionResultWrite.setErrorResult(printWriter, "操作错误");
            return;
        }
        service.deleteReqIntoServer(seqInfo);
        ActionResultWrite.setsuccessfulResult(printWriter);
    }
}
