package com.dcits.ensemble.om.cmc.service;

import cn.com.agree.bxbank.sso.domain.*;
import com.baidu.ub.msoa.container.support.governance.annotation.BundleService;
import com.baidu.ub.msoa.rpc.RPCProtocol;
import com.dcits.dynamic.web.dao.WebUserDao;
import com.dcits.dynamic.web.mapper.WebUser;
import com.dcits.dynamic.web.util.ResourcesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CmcSSOLoginService {

    private static final Logger log = LoggerFactory.getLogger(CmcSSOLoginService.class);

    @BundleService(provider = "${msoa.sso.provider}", service = TokenAuthAPI.SERVICE_NAME,
            version = "${msoa.sso.version}", protocol = RPCProtocol.NAVI2JSON,
            interfaceType = TokenAuthAPI.class)
    private TokenAuthAPI tokenAuthAPI;

    @BundleService(provider = "${msoa.sso.provider}", service = UserInfoAPI.SERVICE_NAME,
            version = "${msoa.sso.version}", protocol = RPCProtocol.NAVI2JSON,
            interfaceType = UserInfoAPI.class)
    private UserInfoAPI userInfoAPI;

    @BundleService(provider = "${msoa.sso.provider}", service = PWDAuthAPI.SERVICE_NAME,
            version = "${msoa.sso.version}", protocol = RPCProtocol.NAVI2JSON,
            interfaceType = PWDAuthAPI.class)
    private PWDAuthAPI pwdAuthAPI;

    @Resource
    WebUserDao webUserDao;

    /**
     * 令牌验证
     * @param request
     * @param userId
     * @param tokenString
     * @return
     */
    public boolean tokenCheck(HttpServletRequest request, String userId, String tokenString){
        TokenAuthRequest tokenAuthRequest = new TokenAuthRequest();
        tokenAuthRequest.setUserID(userId);
        tokenAuthRequest.setTokenString(tokenString);
        tokenAuthRequest.setEncode(false);
        tokenAuthRequest.setEncrypt(true);
        tokenAuthRequest.setSystemName("cmc");
        tokenAuthRequest.setClientIp(ResourcesUtils.getClientIP(request));
        try{
            TokenAuthResponse tokenAuthResponse = tokenAuthAPI.TokenCheck(tokenAuthRequest);
            log.info("SSO令牌验证结果：" + tokenAuthResponse.isChkResult());
            return tokenAuthResponse.isChkResult();
        } catch(Exception e){
            log.error("单点登录验证令牌失败！",e);
            return false;
        }
    }

    /**
     * 用户名密码验证
     */
    public boolean pwdCheck(String userId, String userPWD){
        PWDAuthRequest pwdAuthRequest = new PWDAuthRequest();
        pwdAuthRequest.setUserID(userId);
        pwdAuthRequest.setUserPWD(userPWD);
        pwdAuthRequest.setSystemName("cmc");
        try{
            PWDAuthResponse pwdAuthResponse = pwdAuthAPI.checkPWD(pwdAuthRequest);
            return pwdAuthResponse.isChkResult();
        } catch(Exception e){
            log.error("单点登录验证用户名密码失败！",e);
            return false;
        }
    }

    /**
     * 同步单点登录系统用户信息
     * @param userId
     * @return
     */
    public WebUser syncWebUserFromSSO(String userId){
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setUserID(userId);
        UserInfoResponse userInfoResponse = null;
        try{
            userInfoResponse = userInfoAPI.getUserInfo(userInfoRequest);
        } catch (Exception e){
            log.error("同步单点登录系统用户信息失败！",e);
        }

        WebUser webUser = new WebUser();
        if (userInfoResponse!=null){
            webUser.setUserId(userInfoResponse.getUserId());
            webUser.setUserName(userInfoResponse.getUserName());
            webUser.setPassword("123456");
            webUserDao.insert(webUser);
        }
        return webUser;
    }


}
