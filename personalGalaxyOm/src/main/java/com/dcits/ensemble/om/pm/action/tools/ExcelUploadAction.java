package com.dcits.ensemble.om.pm.action.tools;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dcits.dynamic.web.util.AppInfo;
import com.dcits.dynamic.web.util.BaseState;
import com.dcits.dynamic.web.util.State;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.ensemble.om.pm.service.TableHelperChsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/1/7.
 */
@Controller
@RequestMapping("/parameter")
public class ExcelUploadAction {
    @Resource
    private TableHelperChsService tableHelperChsService;


    /**
     * 上传目录名
     */

    private static final Logger log = LoggerFactory
            .getLogger(ExcelUploadAction.class);

    @RequestMapping("/excelUpload")
    @Transactional
    public void fileUpload(HttpServletRequest request, PrintWriter printWriter) {
        String retState;
        try {
            String savePath = ParaMeterUtils.uploadExcelParaFile(request, printWriter);
            if (savePath == null)
                return;
            State state = new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            if (StringUtils.isNotEmpty(savePath)) {
                String ret = tableHelperChsService.readExcelData(savePath);
                if (ret.contains("导入成功")) {
                    state = new BaseState(true);
                } else {
                    state = new BaseState(false, ret);
                }
            }
            retState = state.toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            retState = new BaseState(false, AppInfo.PARSE_REQUEST_ERROR).toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
            e.printStackTrace();
        }
    }
}
