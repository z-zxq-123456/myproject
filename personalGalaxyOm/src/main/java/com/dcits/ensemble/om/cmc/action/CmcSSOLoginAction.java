package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.dao.WebUserDao;
import com.dcits.dynamic.web.dao.sys.WebUserRoleDao;
import com.dcits.dynamic.web.mapper.WebUser;
import com.dcits.dynamic.web.util.action.PageDrawManager;
import com.dcits.ensemble.om.cmc.service.CmcSSOLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CmcSSOLoginAction {

    private static final Logger log = LoggerFactory.getLogger(CmcSSOLoginAction.class);

    @Resource
    private WebUserDao webUserDao;

    @Resource
    private WebUserRoleDao webUserRoleDao;

    @Resource
    private CmcSSOLoginService ssoLoginService;



    /**
     * SSO登录逻辑
     * 1.先验证token
     * 2.token正确则查询用户信息是否在OM库，
     *   不在则去SSO同步用户给信息
     * 3.获取到用户信息后，检查用户是否有权限。
     * 4.有权限则将用户信息放入session中，返回。
     * 5.无权限则返回提示信息
     * @param request
     * @param printWriter
     */
    @RequestMapping({"/tokenSSOLogin"})
    public ModelAndView ssoLogin(HttpServletRequest request, PrintWriter printWriter){
        ModelAndView mav=new ModelAndView("index");
        String userId = request.getParameter("userid");
        String tokenString = request.getParameter("token");
        boolean isTokenCheck = ssoLoginService.tokenCheck(request, userId, tokenString);
        String errorMsg = null;
        if (isTokenCheck){
            WebUser webUser = webUserDao.getUser(userId);
            if (webUser == null){
                webUser = ssoLoginService.syncWebUserFromSSO(userId);
            }

            Integer role = webUserRoleDao.selectRoleByuserId(userId);
            if (role == null) {
                errorMsg = "用户"+userId+"无权限，请先授权！";
            } else {
                errorMsg = "000000";
                request.getSession().setMaxInactiveInterval(-1);
                request.getSession().setAttribute("UserName", userId);
                request.getSession().setAttribute("userId", userId);
                request.getSession().setAttribute("UserRole", role.toString());
                String userRoleJsp = "role" + role.toString() + ".jsp";
                request.getSession().setAttribute("userRoleJsp", userRoleJsp);
                if (webUser.getLegalentity() != null) {
                    request.getSession().setAttribute("legalentity", webUser.getLegalentity());
                }

                if (webUser.getOrganization() != null) {
                    request.getSession().setAttribute("organization", webUser.getOrganization());
                }

                //PageDrawManager.generateJspPage(request, role);
            }
        } else {
            errorMsg = "单点登录令牌验证失败!";
        }

        if (null!=errorMsg && !"000000".equals(errorMsg)){
            mav=new ModelAndView("error");
            mav.addObject("errorMsg", errorMsg);
        }

        return mav;
    }

    /**
     * 用户密码验证的单点登录
     * @param request
     * @param printWriter
     */
    @RequestMapping({"/pwdSSOLogin"})
    public void getSSOLogin(HttpServletRequest request, PrintWriter printWriter){
        String userId = request.getParameter("userId");
        String pwd = request.getParameter("password");
        boolean isPWDCheck = ssoLoginService.pwdCheck(userId,pwd);
        String errorMsg = null;
        if (isPWDCheck){
            WebUser webUser = webUserDao.getUser(userId);
            if (webUser == null){
                webUser = ssoLoginService.syncWebUserFromSSO(userId);
            }

            Integer role = webUserRoleDao.selectRoleByuserId(userId);
            if (role == null) {
                errorMsg = "用户"+userId+"无权限，请先授权！";
            } else {
                errorMsg = "000000";
                request.getSession().setMaxInactiveInterval(-1);
                request.getSession().setAttribute("UserName", userId);
                request.getSession().setAttribute("userId", userId);
                request.getSession().setAttribute("UserRole", role.toString());
                String userRoleJsp = "role" + role.toString() + ".jsp";
                request.getSession().setAttribute("userRoleJsp", userRoleJsp);
                if (webUser.getLegalentity() != null) {
                    request.getSession().setAttribute("legalentity", webUser.getLegalentity());
                }

                if (webUser.getOrganization() != null) {
                    request.getSession().setAttribute("organization", webUser.getOrganization());
                }

                PageDrawManager.generateJspPage(request, role);
            }
        } else {
            errorMsg = "单点登录用户密码验证失败！";
        }
        Map<String, String> finalResult = new HashMap();
        finalResult.put("errorMsg", errorMsg);
        String jsonStr = JSON.toJSONString(finalResult);
        printWriter.print(jsonStr);
        printWriter.flush();
        printWriter.close();
    }

}
