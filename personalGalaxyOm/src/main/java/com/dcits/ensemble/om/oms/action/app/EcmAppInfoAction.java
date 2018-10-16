package com.dcits.ensemble.om.oms.action.app;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.dao.sys.WebMenuDao;
import com.dcits.dynamic.web.dao.sys.WebRoleMenuDao;
import com.dcits.dynamic.web.dao.sys.WebUserRoleDao;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.mapper.sys.WebMenu;
import com.dcits.dynamic.web.mapper.sys.WebRoleMenu;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.action.middleware.EcmMidewareInfoAction;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.AppPathHandler;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppVer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.MiddlewareInfoService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * EcmAppInfoAction*
 *
 * @author LuoLang
 * @date 2015-08-27
 */
@Controller
public class EcmAppInfoAction {

    @Resource
    private WebMenuDao webMenuDao;
    @Resource
    private WebUserRoleDao webUserRoleDao;
    @Resource
    private WebRoleMenuDao webRoleMenuDao;
    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;
    @Resource
    AppPathHandler appPathHandler;
    @Resource
    EcmMidewareInfoAction midewareInfoAction;
    @Resource
    UpgNodeCache upgNodeCache;
    @Resource
    MiddlewareInfoService middlewareInfoService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Boolean flag1 = false; //不是用默认Redis集群
    private Boolean flag2 = false; //不是用默认ZK集群
    private Boolean flag3 = false; //不是用默认DB集群
    @RequestMapping("saveEcmAppInfo")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmAppInfo app) {
        int appId = serviceUtil.getMaxReqID(0, "ecm_app_info", "app_id"); //获取当前列表数据最大ID
        app.setAppId(appId);
        StringBuilder errorMsgB = new StringBuilder();
        Map<String, EcmMidwareInfo> midwareMap = middlewareInfoService.getMap();
        if (app.getMidwareRedisId() == null) {
            if (midwareMap.containsKey(SysConfigConstants.REDIS_MIDWARE)) {
                app.setMidwareRedisId(midwareMap.get(SysConfigConstants.REDIS_MIDWARE).getMidwareId());
                flag1 = true;
            } else {
                errorMsgB.append("未设置默认Redis集群！请选择具体集群");
            }
        } else {
            flag1 = true;
        }
        if (app.getMidwareZKId() == null) {
            if (midwareMap.containsKey(SysConfigConstants.ZOOKEEPER_MIDWARE)) {
                app.setMidwareZKId(midwareMap.get(SysConfigConstants.ZOOKEEPER_MIDWARE).getMidwareId());
                flag2 = true;
            } else {
                errorMsgB.append("未设置默认ZK集群！请选择具体集群");
            }
        } else {
            flag2 = true;
        }
        if (app.getMidwareDBId() == null) {
            if (midwareMap.containsKey(SysConfigConstants.DB_MIDWARE)) {
                app.setMidwareDBId(midwareMap.get(SysConfigConstants.DB_MIDWARE).getMidwareId());
                flag3 = true;
            } else {
                errorMsgB.append("未设置默认DB集群！请选择具体集群");

            }
        } else {
            flag3 = true;
        }
        if (errorMsgB.length() != 0) {
            String errorMsg = errorMsgB.toString();
            flag1 = false;
            flag2 = false;
            flag3 = false;
            ActionResultWrite.setErrorResult(printWriter, errorMsg);
        }
        if (flag1 && flag2 && flag3) {
            omsBaseService.insert(app);
           // ActionResultWrite.setsuccessfulResult(printWriter, appId);
            HashMap result = new HashMap();
            result.put("success", "success");
            result.put("id", appId);
            String jsonStr = JSON.toJSONString(result);
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();

        }
    }


    @RequestMapping("saveAppMenu")
    public void saveAppMenu(HttpServletRequest request, PrintWriter printWriter, WebMenu info) {
        WebRoleMenu webRoleMenu = new WebRoleMenu();
        String appId = request.getParameter("id");
        String appName = request.getParameter("menuName");
        String userId =request.getParameter("userId");
        Integer roleId = webUserRoleDao.selectRoleByuserId(userId);
        webRoleMenu.setRoleId(roleId);
        try {
            Integer menuParementId = webMenuDao.selectMenuIdByMenuName("应用管理",6);
            if(menuParementId==null || menuParementId<=0)
            {
                ActionResultWrite.setErrorResult(printWriter, "找不到应用管理菜单！");
                return;
            }
            if (log.isDebugEnabled()) {
                log.debug("应用管理 menu ID:" + menuParementId + " app:" + appId + " menu name:" + appName);
            }
            info.setMenuParentId(menuParementId);
            info.setMenuUrl("&#xe614;");
            info.setMenuStatus("Y");
            menuParementId = saveMenu(printWriter, info,webRoleMenu);
            info.setMenuParentId(menuParementId);
            String[] menuName = {"版本管理", "应用维护", "配置调整", "不间断升级", "应用日志"};
            String[] jspUrl = {"app/oms/jsp/app/appVerManager.jsp?id=" + appId + "&appName=" + appName,"app/oms/jsp/app/appInstantManager.jsp?id=" + appId + "&appName=" + appName, "app/oms/jsp/app/appConfigFileManager.jsp?id=" + appId + "&appName=" + appName,  "app/oms/jsp/app/continuUpgrade.jsp?id=" + appId + "&appName=" + appName, "app/oms/jsp/app/logView.jsp?id=" + appId + "&appName=" + appName, "app/oms/jsp/app/logView.jsp?id=" + appId + "&appName=" + appName, "app/oms/jsp/app/logView.jsp?id=" + appId + "&appName=" + appName};
            for (int i = 0; i < menuName.length; i++) {
                info.setMenuUrl(jspUrl[i]);
                info.setMenuName(menuName[i]);
                saveMenu(printWriter, info,webRoleMenu);
            }
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "操作错误！");
        }
    }

    public Integer saveMenu(PrintWriter printWriter, WebMenu info,WebRoleMenu webRoleMenu) {
        Integer e = webMenuDao.selectByPId(info.getMenuParentId());
        Integer menuSeq = webMenuDao.selectSeqByPId(info.getMenuParentId());
        if (menuSeq == null) {
            info.setMenuId(Integer.valueOf(String.valueOf(info.getMenuParentId().intValue() * 10) + 1));
            info.setMenuSeq(Integer.valueOf(1));
        } else {
            info.setMenuId(Integer.valueOf(e.intValue() + 1));
            info.setMenuSeq(Integer.valueOf(menuSeq.intValue() + 1));
        }
        if (info.getMenuUrl().equals("button")) {
            if (!info.getMenuName().startsWith("#")) {
                info.setMenuName("#" + info.getMenuName());
            }

            String mUrl = this.webMenuDao.selectUrlById(info.getMenuParentId());
            if ("#".equals(mUrl) || mUrl.length() < 5) {
                return null;
            }

            info.setMenuUrl(mUrl);
        }
        Integer menuId = webMenuDao.selectMenuIdByMenuName(info.getMenuName(),info.getMenuParentId());
        if(menuId == null) {
            webRoleMenu.setMenuId(info.getMenuId());
            this.webRoleMenuDao.insert(webRoleMenu);
            this.webMenuDao.insert(info);

        }
        else
        {
            info.setMenuId(menuId);
        }
        return info.getMenuId();
    }


    @RequestMapping("updateEcmAppInfo")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmAppInfo app) {
        Integer menuParementId = webMenuDao.selectMenuIdByMenuName("应用管理", 6);
        if(menuParementId==null || menuParementId<=0)
        {
            ActionResultWrite.setErrorResult(printWriter, "找不到应用管理菜单！");
            return;
        }
        if (isUpdateAppPath(app) || isUpdateAppSimpenNm(app) || isUpdateMidwareZKId(app)) {
            if (isAllAppIntantIsClean(app)) {
                omsBaseService.updateByPrimaryKey(app);
                appPathHandler.deleteAppPathIsCreatedCache(app);//删除该应用所有应用实例已创建路径缓存

                //更新对应得菜单。。。

                List<WebMenu> webMenuList= webMenuDao.selectChild(menuParementId);
                for(WebMenu webMenu:webMenuList){
                    List<WebMenu> webMenuList1= webMenuDao.selectChild(webMenu.getMenuId());
                    for (WebMenu webMenu1:webMenuList1){
                        int appid=Integer.parseInt(webMenu1.getMenuUrl().substring(webMenu1.getMenuUrl().indexOf("?id=")+4,webMenu1.getMenuUrl().indexOf("&appName")));
                        if (app.getAppId()==appid){
                            webMenu.setMenuName(app.getAppName());
                            webMenuDao.updateByPrimaryKey(webMenu);
                        }
                    }
                }

            } else {
                ActionResultWrite.setErrorResult(printWriter, "要修改应用安装路径、简称、zk集群，请先清理该应用下所有应用实例!");
            }
        } else {
            omsBaseService.updateByPrimaryKey(app);

            //更新对应得菜单。。。
            List<WebMenu> webMenuList= webMenuDao.selectChild(menuParementId);
            for(WebMenu webMenu:webMenuList){
                List<WebMenu> webMenuList1= webMenuDao.selectChild(webMenu.getMenuId());
                for (WebMenu webMenu1:webMenuList1){
                    int appid=Integer.parseInt(webMenu1.getMenuUrl().substring(webMenu1.getMenuUrl().indexOf("?id=")+4,webMenu1.getMenuUrl().indexOf("&appName")));
                    if (app.getAppId()==appid){
                        webMenu.setMenuName(app.getAppName());
                        webMenuDao.updateByPrimaryKey(webMenu);
                    }
                }
            }
        }
        ActionResultWrite.setsuccessfulResult(printWriter);
    }


    @RequestMapping("deleteEcmAppInfo")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmAppInfo app) {
        try {

            if (isExistAppint(app)) {
                 if(isExistAppVer(app)){
                     omsBaseService.deleteByPrimaryKey(app);
                     appPathHandler.deleteAppPathIsCreatedCache(app);//删除该应用所有应用实例已创建路径缓存

                     //删掉对应得菜单。。。
                     Integer menuParementId = webMenuDao.selectMenuIdByMenuName("应用管理",6);
                     if(menuParementId==null || menuParementId<=0)
                     {
                         ActionResultWrite.setErrorResult(printWriter, "找不到应用管理菜单！");
                         return;
                     }
                     List<WebMenu> webMenuList= webMenuDao.selectChild(menuParementId);
                     for(WebMenu webMenu:webMenuList){
                         List<WebMenu> webMenuList1= webMenuDao.selectChild(webMenu.getMenuId());
                         for (WebMenu webMenu1:webMenuList1){
                             if(webMenu1.getMenuUrl().contains("?id=")) {
                                 int appid = Integer.parseInt(webMenu1.getMenuUrl().substring(webMenu1.getMenuUrl().indexOf("?id=") + 4, webMenu1.getMenuUrl().indexOf("&appName")));
                                 if (app.getAppId() == appid) {
                                     webRoleMenuDao.deleteByMenuId(webMenu1.getMenuParentId());
                                     webRoleMenuDao.deleteByMenuId(webMenu1.getMenuId());
                                     webMenuDao.deleteByMenuParentId(webMenu1.getMenuParentId());
                                     webMenuDao.deleteByPrimaryKey(webMenu);

                                 }
                             }
                             else
                             {
                                 ActionResultWrite.setErrorResult(printWriter, "该应用的菜单数据存在问题。");
                             }
                         }
                     }
                 }else {
                     ActionResultWrite.setErrorResult(printWriter, "该应用已上传版本，禁止删除！");
                     return;
                 }

            } else {
                ActionResultWrite.setErrorResult(printWriter, "该应用已部署实例，禁止删除！");
                return;
            }
            ActionResultWrite.setsuccessfulResult(printWriter);
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    private int isString(String string){
        return  string.indexOf("?id=");
    }

    //判断是否修改了应用安装路径  true 修改  false 未修改
    private boolean isUpdateAppPath(EcmAppInfo app) {
        EcmAppInfo oldApp = omsBaseService.selectByPrimaryKey(app);
        if (!app.getAppPath().equals(oldApp.getAppPath())) {
            return true;
        }
        return false;
    }

    //判断是否修改了应用安装路径 true 清理  false 未清理
    private boolean isAllAppIntantIsClean(EcmAppInfo app) {
        EcmAppIntant intant = new EcmAppIntant();
        intant.setAppId(app.getAppId());
        List<EcmAppIntant> intantList = omsBaseService.findListByCond(intant);
        for (EcmAppIntant rowIntant : intantList) {
            if (!rowIntant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)) {
                return false;
            }
        }
        return true;
    }

    //判断是否修改了应用简称 true 修改  false 未修改
    private boolean isUpdateAppSimpenNm(EcmAppInfo app) {
        EcmAppInfo oldApp = omsBaseService.selectByPrimaryKey(app);
        if (!app.getAppSimpenNm().equals(oldApp.getAppSimpenNm())) {
            return true;
        }
        return false;
    }

    //判断是否修改了应用zk true 修改  false 未修改
    private boolean isUpdateMidwareZKId(EcmAppInfo app) {
        EcmAppInfo oldApp = omsBaseService.selectByPrimaryKey(app);
        if (!app.getMidwareZKId().equals(oldApp.getMidwareZKId())) {
            return true;
        }
        return false;
    }

    @RequestMapping("findEcmAppInfo")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmAppInfo app) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmAppInfo> pageData = omsBaseService.findPageByCond(app, pageNo, pageSize);
//        ActionResultWrite.setPageReadResult(printWriter,magicList(pageData.getList()),pageData.getTotalNum());
            List<EcmAppInfo> ecmAppInfo = pageData.getList();
            ActionResultWrite.setReadResult(printWriter, magicList(ecmAppInfo));
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }


    private List<EcmAppInfo> magicList(List<EcmAppInfo> list) {
        List<EcmMidwareInfo> midwareList = omsBaseService.findListByCond(new EcmMidwareInfo(), "findMidwareList", new HashMap<String, Object>());
        Map<Integer, String> midwareMap = new HashMap<Integer, String>();
        for (EcmMidwareInfo midware : midwareList) {
            midwareMap.put(midware.getMidwareId(), midware.getMidwareName());
        }
        for (EcmAppInfo app : list) {
            app.setAppTypeName(paramComboxService.getParaName(app.getAppType()));
            app.setRedisMidwareName(midwareMap.get(app.getMidwareRedisId()));
            app.setZkMidwareName(midwareMap.get(app.getMidwareZKId()));
            app.setDbMidwareName(midwareMap.get(app.getMidwareDBId()));
        }
        return list;
    }

    @RequestMapping("findAppCombox")
    public void findApp(HttpServletRequest request, PrintWriter printWriter) {
        try {
            List<EcmAppInfo> infoList = omsBaseService.findListByCond(new EcmAppInfo(), "findCombList", new HashMap<String, Object>());
            List<PkList> lists = new ArrayList<>();
            for (int i = 0; i < infoList.size(); i++) {
                PkList pkList = new PkList();
                pkList.setPkDesc(infoList.get(i).getAppName());
                pkList.setPkValue(infoList.get(i).getAppId().toString());
                lists.add(pkList);
            }
            printWriter.print(JSON.toJSONString(lists));
            printWriter.flush();
            printWriter.close();
            ActionResultWrite.setReadResult(printWriter, infoList);
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    //判断是否存在实例
    private boolean isExistAppint(EcmAppInfo app) {
        EcmAppIntant  intant =new EcmAppIntant();
        intant.setAppId(app.getAppId());
        List<EcmAppIntant>  intantList=omsBaseService.findListByCond(intant);
        if(intantList.size()>0) {
            return false;
        }else {
            return true;
        }
    }
    //判断是否存在版本
    private boolean isExistAppVer(EcmAppInfo app) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("appId", app.getAppId());
        List<EcmAppVer> appVerList = omsBaseService.findListByCond(new EcmAppVer(),"findVerInfo",map);
        if(appVerList.size()>0) {
            return false;
        }else {
            return true;
        }
    }
}
