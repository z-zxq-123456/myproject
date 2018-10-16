package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareVer;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.MiddlewareVerService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 * EcmAppInfoAction*
 *
 * @author LuoLang
 * @createdate 2016-2-18
 */
@Controller
public class EcmMidwareVerAction {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;
    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    MiddlewareVerService middlewareVerService;

    @RequestMapping("saveMiddlewareVer")
    public String save(HttpServletRequest request, PrintWriter printWriter, EcmMidwareVer midwarVer) {
        String userId = (String) request.getSession().getAttribute("UserName");
        try {
            //String path = request.getSession().getServletContext().getRealPath("/") + "/";
            middlewareVerService.save(midwarVer, userId);
            clearSessionFileProgress(request);
            return "redirect:/app/oms/jsp/middleware/midwareVerAdd.jsp?isRedirect=true";
        } catch (MultipartException e) {
            e.printStackTrace();
            //throw new MultipartException(e.getMessage());
            return "redirect:/app/oms/jsp/app/midwareVerAdd.jsp?isErrorMsg=true";
        }
    }

    @RequestMapping("downloadMiddlewareVer")
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

    @RequestMapping("updateMiddlewareVer")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmMidwareVer midwarVer) {
        try {
            midwarVer.setMidwareVerPath(null);
            omsBaseService.updateByPrimaryKey(midwarVer);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }


    @RequestMapping("deleteMiddlewareVer")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmMidwareVer midwarVer) {
        try {
            middlewareVerService.delete(midwarVer);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }

    }

    @RequestMapping("findMiddlewareVer")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmMidwareVer midwarVer) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            //String path = request.getSession().getServletContext().getRealPath("/") + "/";
            PageData<EcmMidwareVer> pageData = middlewareVerService.findList(pageNo, pageSize, midwarVer);
            ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList()), pageData.getTotalNum());
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }

    }

    /**
     * 清除sessionz中保存的文件上传进度
     *
     * @param HttpServletRequest request
     */
    private void clearSessionFileProgress(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SysConfigConstants.progressName);
        session.removeAttribute(SysConfigConstants.fileTotalSize);
    }


    private List<EcmMidwareVer> magicList(List<EcmMidwareVer> list) {
        for (EcmMidwareVer para : list) {
            para.setMidwareTypeName(paramComboxService.getParaName(para.getMidwareType()));
        }
        return list;
    }

    @RequestMapping("findMiddlewareVerCombox")
    public void findMiddlewareVer(HttpServletRequest request, PrintWriter printWriter, @RequestParam("midwareType") String paraCode) {
        try {
            Map<String, Object> midwareVer = new HashMap<String, Object>();
            midwareVer.put("midwareType", paraCode);
            List<EcmMidwareVer> infoList = omsBaseService.findListByCond(new EcmMidwareVer(), "findCombList", midwareVer);
            ActionResultWrite.setReadResult(printWriter, magicList(infoList));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findMiddlewareNo")
    public void findMiddlewareNo(HttpServletRequest request, PrintWriter printWriter, @RequestParam("midwareType") String paraCode) {
        try {
            Map<String, Object> midwareVer = new HashMap<String, Object>();
            midwareVer.put("midwareType", paraCode);
            List<EcmMidwareVer> infoList = omsBaseService.findListByCond(new EcmMidwareVer(), "findCombList", midwareVer);
            List<PkList> pkLists = new ArrayList<>();
            if (infoList.size() == 0) {

            } else {

                for (int i = 0; i < infoList.size(); i++) {
                    PkList pkList = new PkList();
                    pkList.setPkDesc(infoList.get(i).getMidwareVerNo());
                    pkList.setPkValue(infoList.get(i).getMidwareVerId().toString());
                    pkLists.add(pkList);
                }
            }
            printWriter.print(JSON.toJSONString(pkLists));
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }
}
