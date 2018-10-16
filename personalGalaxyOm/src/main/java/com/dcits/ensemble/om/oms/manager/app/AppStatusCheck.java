package com.dcits.ensemble.om.oms.manager.app;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.MachineUtil;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import com.dcits.galaxy.oms.monitor.api.health.HealthCheckResult;
import com.dcits.galaxy.oms.monitor.api.health.IAppHealthCheck;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.SimpleHttpConnectionManager;


/**
 * 应用状态检查类*
 *
 * @author tangxlf
 * @date 2015-11-13
 */
@Component
public class AppStatusCheck {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CHECK_APP_CONNECTION = "应用连通性检查";

    private static final int CHECK_FAIL = 1; //失败

    private int readTimeout = 2 * 1000;

    private static final String CHECK_APP_HTTP = "HTTP连通性检查";

    //private static final String CHECK_APP_DEPEND ="应用依赖检查";


    @Resource
    ShellExcuteService shellService;

    @Resource
    CmdFactory cmdFactory;
    @Resource
    PropertiesCacheService propertiesCacheService;
    @Resource
    ParamComboxService paramComboxService;


    /**
     * 应用服务连通性检查
     *
     * @param EcmAppIntant         intant            容器实例对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    public void checkServeicConnection(EcmAppIntant intant, ContainerCheckResult checkResult) {
        if (intant.getAppType().equals(SysConfigConstants.BUSINESS_APP_TYPE)) {
            checkBusinessApp(intant, checkResult);
        } else {
            checkWebApp(intant, checkResult);
        }
    }

    /**
     * WEB应用服务连通性检查
     *
     * @param EcmAppIntant         intant            容器实例对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    private void checkWebApp(EcmAppIntant intant, ContainerCheckResult checkResult) {
        try {
            ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
            String checkPort = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL_PORT), intant);
            ShellResult result = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
                    cmd.netstatCmd(checkPort));
            if (MachineUtil.appStatus(result, checkPort)) {
                checkResult.addMessage(CHECK_APP_CONNECTION + ":连通");
            } else {
                errorCheck(CHECK_APP_CONNECTION, checkResult);
            }
        } catch (IllegalStateException e) {
            log.error(CHECK_APP_CONNECTION + "出错,错误信息为：" + e.getMessage());
            throw new GalaxyException(CHECK_APP_CONNECTION + "出错!");
        }
    }


    /**
     * 业务应用服务连通性检查
     *
     * @param EcmAppIntant         intant            容器实例对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    private void checkBusinessApp(EcmAppIntant intant, ContainerCheckResult checkResult) {
        ReferenceConfig<IAppHealthCheck> reference = new ReferenceConfig<IAppHealthCheck>();//健康检查配置类
        try {
            String name = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL_NAME), intant);
            String port = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL_PORT), intant);
            String healthCheckName = IAppHealthCheck.class.getName();
            reference.setInterface(healthCheckName);
            String url = name + "://" + intant.getSerIp() + ":" + port + "/" + healthCheckName;
            log.info("直连服务url:" + url);
            reference.setUrl(url);
            reference.setCheck(false);
            IAppHealthCheck service = (IAppHealthCheck) reference.get();
            HealthCheckResult result = service.checkAppHealth();
            log.info("健康检查返回结果：HealthStatus=" + result.getHealthStatus() + " Message=" + result.getMessage());
            if (result.getHealthStatus() == 0) {
                checkResult.addMessage(CHECK_APP_CONNECTION + ":连通");
            } else {
                //checkResult.addResultNum(CHECK_FAIL);
                checkResult.addResultNum(result.getHealthStatus());
                checkResult.addMessage(CHECK_APP_CONNECTION + ":不连通" + "；提示：" + result.getMessage());
            }
        } catch (RpcException e) {
            log.info(CHECK_APP_CONNECTION + " ,信息为：" + e.getMessage());
            errorCheck(CHECK_APP_CONNECTION, checkResult);
        } finally {
            if (reference != null) reference.destroy();
        }
    }


    /**
     * http服务连通性检查
     *
     * @param EcmAppIntant         intant            容器实例对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    public void checkHttpConnection(EcmAppIntant intant, ContainerCheckResult checkResult) {
        GetMethod httpGet = null;
        HttpClient httpClient = null;

        String checkPort = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.JETTY_PORT), intant);
        if (checkPort != null) {//不为空，才检查
            String jettyUrlPattern = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.JETTY_URLPATTERN), intant);
            if (jettyUrlPattern.indexOf("/") == -1) {
                // modify for sonar
                jettyUrlPattern = "/".concat(jettyUrlPattern);
            }
            String url = "http://" + intant.getSerIp() + ":" + checkPort + jettyUrlPattern;
            try {
                httpClient = new HttpClient();
                DefaultHttpMethodRetryHandler handler = new DefaultHttpMethodRetryHandler(0, false);
                httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, handler);
                httpGet = new GetMethod(url);
                httpGet.setRequestHeader("Connection", "close");
                httpGet.getParams().setSoTimeout(readTimeout);
                int status = httpClient.executeMethod(httpGet);
                log.info("url =" + url + " status =" + status);
                if (status == 200 || status == 500) {
                    checkResult.addMessage(CHECK_APP_HTTP + ":连通");
                } else {
                    errorCheck(CHECK_APP_HTTP, checkResult);
                }
            } catch (ConnectException | SocketTimeoutException | HttpException e) {
                log.info(CHECK_APP_HTTP + " ,信息为：" + e.getMessage());
                errorCheck(CHECK_APP_HTTP, checkResult);
            } catch (IOException e) {
                log.info(CHECK_APP_HTTP + " ,信息为：" + e.getMessage());
                errorCheck(CHECK_APP_HTTP, checkResult);
            } finally {
                if (httpGet != null) {
                    httpGet.releaseConnection();
                } else {
                    System.out.println("httpGet为空！");
                }
                if (httpClient != null) {
                    try {
                        ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
                    } catch (Exception e) {
                        log.error("关闭httpClient出错,错误信息为:" + DataUtil.printErrorStack(e));
                    }
                }
            }
        }
    }

    /**
     * 依赖检查
     *
     * @param EcmAppIntant         intant            容器实例对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    public void checkDependConnection(EcmAppIntant intant, ContainerCheckResult checkResult) {

    }


    /**
     * 异常报错
     *
     * @param String               str  检查对象
     * @param ContainerCheckResult checkResult       检查结果
     */
    private void errorCheck(String str, ContainerCheckResult checkResult) {
        checkResult.addResultNum(CHECK_FAIL);
        checkResult.addMessage(str + ":不连通");
    }

    public static void main(String[] args) {
        System.out.println(IAppHealthCheck.class.getName());
    }

}
