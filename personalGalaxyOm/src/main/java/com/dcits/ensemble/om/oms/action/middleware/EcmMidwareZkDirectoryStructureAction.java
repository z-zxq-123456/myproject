package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.sys.MenuNode;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareZkDirectoryStructureService;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author wangbinaf
 * @date   2016-05-16
 * ZkController
 * zookeeper 操作
 */
@Controller
public class EcmMidwareZkDirectoryStructureAction {
	@Resource
    ZookeeperInfoService zookeeperInfoService;
	@Resource
    UpdateZkConf updateZkConf;
    @Resource
    EcmMidwareZkDirectoryStructureService ecmMidwareZkDirectoryStructureService;
    //查看zookeeper目录及数据
    @RequestMapping("readAddress")
    public void readAddress(HttpServletRequest request, PrintWriter printWriter,Integer midwareId) {
        List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareId);//通过中间件查找zk实例列表
        String cxnStr  = updateZkConf.zkUrl(zkList);//生成zk地址串
        MenuNode rootNode = ecmMidwareZkDirectoryStructureService.getTree(cxnStr);
        String jsonStr = JSON.toJSONString(rootNode);
        printWriter.print("["+jsonStr+"]");
        printWriter.flush();
        printWriter.close();
    }
    //通过中间件返回相应的ZKURL串
    @RequestMapping("readZkUrl")
    public void readZkUrl(HttpServletRequest request, PrintWriter printWriter,Integer  midwareId) {
        List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareId);//通过中间件查找zk实例列表
        String cxnStr  = updateZkConf.zkUrl(zkList);//生成zk地址串
        String jsonStr = JSON.toJSONString(cxnStr);
        printWriter.print("["+jsonStr+"]");
        printWriter.flush();
        printWriter.close();

    }
    //通过节点路径返回节点数据
    @RequestMapping("getNodDate")
    public void getNodDate(HttpServletRequest request, PrintWriter printWriter,Integer midwareId) {
        try{
            String path = request.getParameter("path");
            String cxnStr  = ecmMidwareZkDirectoryStructureService.getNodData(path);//读取节点数据
            String jsonStr = JSON.toJSONString(cxnStr);
            printWriter.print("[" + jsonStr + "]");
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
