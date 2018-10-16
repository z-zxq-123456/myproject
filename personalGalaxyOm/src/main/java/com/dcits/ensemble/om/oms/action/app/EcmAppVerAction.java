package com.dcits.ensemble.om.oms.action.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppVer;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.app.EcmAppVerService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.FileOperService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppVerAction*
 *
 * @author tangxlf
 * @date 2015-08-11
 */
@Controller
public class EcmAppVerAction {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    IService omsBaseService;
    @Resource
    FileOperService fileOperService;
    @Resource
    ParamComboxService paramComboxService;
    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    EcmAppVerService ecmAppVerService;

    @RequestMapping("saveAppVer")
    public String save(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        String userId = (String) request.getSession().getAttribute("UserName");
        try {
            ecmAppVerService.save(appVer, userId);
            clearSessionFileProgress(request);
            return "redirect:/app/oms/jsp/app/appVerAdd.jsp?isRedirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/app/oms/jsp/app/appVerAdd.jsp?isErrorMsg=true";
        }
    }

    @RequestMapping("saveAllAppVer")
    public String saveAll(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer, String type) {
        String userId = (String) request.getSession().getAttribute("UserName");
        try {
            ecmAppVerService.save(appVer, userId);
            clearSessionFileProgress(request);
            return "redirect:/app/oms/jsp/fullgalaxy/fullAppVerAdd.jsp?isRedirect=true";
        } catch (MultipartException e) {
            //throw e;
            e.printStackTrace();

        } catch (Exception e) {
            //throw new MultipartException(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/app/oms/jsp/app/fullAppVerAdd.jsp?isErrorMsg=true";
    }


    @RequestMapping("downloadAppVer")
    public String download(HttpServletRequest request, HttpServletResponse response, String path) {

        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        try {
            byte[] bytes = fileName.getBytes("UTF-8");
            fileName = new String(bytes, "ISO-8859-1");
        } catch (Exception e) {
            throw new MultipartException(e.getMessage());
        }
        response.setCharacterEncoding("ISO-8859-1");
        response.setContentType("multipart/form-data"); //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
            try (InputStream inputStream = new FileInputStream(new File(path));
                 OutputStream os = response.getOutputStream()) {//自动关闭资源
                byte[] b = new byte[2048];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    os.write(b, 0, length);
                }
            }            // 这里主要关闭。
        } catch (Exception e) {
            throw new MultipartException(e.getMessage());
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }

    @RequestMapping("updateAppVer")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        try {
            omsBaseService.updateByPrimaryKey(appVer);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }


    @RequestMapping("deleteAppVer")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        try {
            ecmAppVerService.delete(appVer);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findAppVer")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmAppVer> pageData = omsBaseService.findPageByCond(appVer, pageNo, pageSize);
            if (pageData.getList().size() == 0) {
                JSONObject resultJson = new JSONObject();
                resultJson.put("data", new JSONArray());
                printWriter.print(resultJson.toJSONString());
                printWriter.flush();
                printWriter.close();
                return;
            }
            ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findAppVerComboxEarly")
    public void findCombox(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
        log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
        PageData<EcmAppVer> pageData = omsBaseService.findPageByCond(appVer, pageNo, pageSize);
//           ActionResultWrite.setReadResult(printWriter,magicList(pageData.getList()));
        List<EcmAppVer> infoList = magicList(pageData.getList());
//        List<PkList> pkLists = new ArrayList<>();
//        for (int i = 0; i < infoList.size(); i++) {
//            PkList pkList = new PkList();
//            pkList.setPkDesc(infoList.get(i).getAppVerNum().toString() + "-" + infoList.get(i).getAppVerTypeName());
//            pkList.setPkValue(infoList.get(i).getAppVerNum().toString());
//            pkLists.add(pkList);
//        }
//        printWriter.print(JSON.toJSONString(pkLists));
//        printWriter.flush();
//        printWriter.close();
        ActionResultWrite.setReadResult(printWriter, infoList);
    }
    @RequestMapping("findAppVerCombox")
    public void findAppVerCombox(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
        log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
        PageData<EcmAppVer> pageData = omsBaseService.findPageByCond(appVer, pageNo, pageSize);
//           ActionResultWrite.setReadResult(printWriter,magicList(pageData.getList()));
        List<EcmAppVer> infoList = magicList(pageData.getList());
        List<PkList> pkLists = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            PkList pkList = new PkList();
            pkList.setPkDesc(infoList.get(i).getAppVerNum().toString() + "-" + infoList.get(i).getAppVerTypeName());
            pkList.setPkValue(infoList.get(i).getAppVerNum().toString());
            pkLists.add(pkList);
        }
        printWriter.print(JSON.toJSONString(pkLists));
        printWriter.flush();
        printWriter.close();
     //   ActionResultWrite.setReadResult(printWriter, infoList);
    }
    @RequestMapping("findAppVerInfo")
    public void findAppVerInfo(HttpServletRequest request, PrintWriter printWriter) {
        String appId = request.getParameter("appId");
        String appVerNum = request.getParameter("appVerNum");
        EcmAppVer appVer = new EcmAppVer();
        appVer.setAppId(Integer.parseInt(appId));
        PageData<EcmAppVer> pageData = omsBaseService.findPageByCond(appVer, 1, 10);
        List<EcmAppVer> appVerList = pageData.getList();
        for (int i = 0; i < appVerList.size(); i++) {
            if (appVerNum.equals(appVerList.get(i).getAppVerNum().toString())) {
                appVer = appVerList.get(i);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("appVerInfo", appVer);
        printWriter.print(JSON.toJSONString(map));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("findAppVerCount")
    public void findAppVerCount(HttpServletRequest request, PrintWriter printWriter, EcmAppVer appVer) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            Map<String, Object> result = new HashMap<String, Object>();
            PageData<EcmAppVer> pageData = omsBaseService.findPageByCond(appVer, pageNo, pageSize);
            result.put("rowConunt", pageData.getTotalNum());
            ActionResultWrite.setMapResult(printWriter, result);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }


    /**
     * 清除sessionz中保存的文件上传进度
     *
     * @param request
     */
    private void clearSessionFileProgress(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SysConfigConstants.progressName);
        session.removeAttribute(SysConfigConstants.fileTotalSize);
    }


    private List<EcmAppVer> magicList(List<EcmAppVer> list) {
        for (EcmAppVer para : list) {
            para.setAppVerTypeName(paramComboxService.getParaName(para.getAppVerType()));
        }
        return list;
    }
}
