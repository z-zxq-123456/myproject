package com.dcits.ensemble.om.oms.manager.redis;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareRedisintService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Redis状态检查类* 
 * @author luolang
 * @date 2016-5-3
 */
@Component
public class RedisStatusCheck {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	private static final String CHECK_REDIS_FLAG ="PONG";
	private static final String CHECK_REDIS_CONNECTION ="Redis连通性检查";
	private static final String CHECK_REDIS_MOS="Redis主备检查";
	private static final int CHECK_FAIL = 1; //失败
	private static final String REDIS_NODE_FLAG="master";//节点标识
	
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	EcmMidwareRedisintService midwareRedisintService;
	/**
	 *redis连通性检查
	 *@param EcmMidwareRedisint  midwareRedisint 容器实例对象	 
	 *@param ContainerCheckResult  checkResult       检查结果     
	 */ 
	public void checkRedisConnection(EcmMidwareRedisint midwareRedisint,ContainerCheckResult checkResult) {
		Jedis jedis = new Jedis(midwareRedisint.getSerIp(), midwareRedisint.getRedisintPort());
		try {
			if(jedis.ping().equals(CHECK_REDIS_FLAG)) {
				checkResult.addMessage(CHECK_REDIS_CONNECTION+":连通");
			}else {
				errorCheck(CHECK_REDIS_CONNECTION,checkResult);
			}
	        }catch(JedisDataException  e) {
	    	errorCheck(CHECK_REDIS_CONNECTION,checkResult);
	    	log.info(CHECK_REDIS_CONNECTION+" ,信息为："+e.getMessage());	
	        } catch(JedisConnectionException  e) {
		    	errorCheck(CHECK_REDIS_CONNECTION,checkResult);
		    	log.info(CHECK_REDIS_CONNECTION+" ,信息为："+e.getMessage());	
		    }finally {
           	 if(jedis!=null) {
           		jedis.close();
           	 }else {
           		 System.out.println("jedis为空！");
           	 }
            }
	}

	public static void main(String[] args){
		Jedis jedis = new Jedis("192.168.161.17", 57779);
		System.out.println(jedis.ping());
	}
	
	/**
	 *redis主备检查
	 *@param EcmMidwareRedisint  midwareRedisint 容器实例对象	 
	 *@param ContainerCheckResult  checkResult       检查结果     
	 */ 
	public void cheakMasterOrSlave(EcmMidwareRedisint midwareRedisint ,ContainerCheckResult checkResult ,Set<String> sentinelInfo ) {
		 try{
		 JedisSentinelPool jedis= new JedisSentinelPool(REDIS_NODE_FLAG+midwareRedisint.getRedisintNodeNum(),sentinelInfo);
		 HostAndPort hostAndPort =jedis.getCurrentHostMaster();
		 jedis.close();
		 jedis.destroy();
		 String masterInfo = hostAndPort.toString();
		 log.info("masterInfo::"+masterInfo);
		 String comparedObj =midwareRedisint.getSerIp()+":"+midwareRedisint.getRedisintPort();
		 if(masterInfo.equals(comparedObj)){
			 checkResult.addMessage(CHECK_REDIS_MOS+":"+paramComboxService.getParaName(SysConfigConstants.REDIS_MASTER));
		 } else{
			 checkResult.addMessage(CHECK_REDIS_MOS+":"+paramComboxService.getParaName(SysConfigConstants.REDIS_SLAVE));
		 }
		 }catch(JedisConnectionException  e) {
			 errorMOSCheck(CHECK_REDIS_MOS,checkResult);
		     log.info(CHECK_REDIS_MOS+" ,信息为："+e.getMessage());	 
		 }
	}
	/**
	 * 连通异常报错
	 * @param String   str  检查对象
	 * @param ContainerCheckResult  checkResult       检查结果
	 * 
	 */
	private void errorCheck(String str ,ContainerCheckResult checkResult) {
	     checkResult.addResultNum(CHECK_FAIL);
	     checkResult.addMessage(str+":不连通");  
	}
	
	/**
	 * 获取redis集群哨兵集合
	 * @param int midwareId  中间件Id，确定唯一集群
	 * @return Set<String>   哨兵集合
	 * 
	 */
	public Set<String> sentinelPool(int midwareId) {
		 List<EcmMidwareRedisint> redisintList = midwareRedisintService.findSentinels(midwareId);
		 Set<String> sentinelInfo = new HashSet<String>();
		 for (EcmMidwareRedisint redisint:redisintList) {
			sentinelInfo.add(redisint.getSerIp()+":"+redisint.getRedisintPort());
		 }
		 return sentinelInfo;
	}
	/**
	 * 主备检查异常报错
	 * @param String   str  检查对象
	 * @param ContainerCheckResult  checkResult       检查结果
	 * 
	 */
	private void errorMOSCheck(String str ,ContainerCheckResult checkResult) {
	     checkResult.addResultNum(CHECK_FAIL);
	     checkResult.addMessage(str+":检查失败");  
	}	
}
