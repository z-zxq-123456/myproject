package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;

import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;

import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * EcmMidwareRedisintAction* 
 * @author luolang
 * @date 2016-05-11
 */
@Service
public class EcmRedisMaterToslaveService {
	@Resource
	IService omsBaseService;
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String REDIS_NODE_FLAG="master";//节点标识
	private static final String MASTER_TO_SLAVE="主备切换";
	
	/**
     * 通过jedis.sentinelFailover来对主备交换,其中jedis为哨兵对象
     * @param	 EcmMidwareRedisint  midwareRedisint redis实例对象  
     */    
	public void randomSetMS(EcmMidwareRedisint midwareRedisint) {
		List<EcmMidwareRedisint> sentinelList = getSentinelPool(midwareRedisint);
		EcmMidwareRedisint sentinel = randomSentinel(sentinelList);
		try{
		Jedis jedisSentinel = new Jedis(sentinel.getSerIp(), sentinel.getRedisintPort());
		jedisSentinel.sentinelFailover(REDIS_NODE_FLAG+midwareRedisint.getRedisintNodeNum());
		jedisSentinel.close();
		}catch(JedisDataException e) {
			log.info(MASTER_TO_SLAVE+" ,信息为："+e.getMessage());	
			throw new GalaxyException(MASTER_TO_SLAVE+"出错!请稍候操作");
		}catch(JedisConnectionException e) {
			log.info(MASTER_TO_SLAVE+" ,信息为："+e.getMessage());
			throw new GalaxyException(MASTER_TO_SLAVE+"出错!");
		}
		
	}
	//获取哨兵池
	private List<EcmMidwareRedisint> getSentinelPool(EcmMidwareRedisint midwareRedisint) {
		 Map<String,Object> redisMap= new HashMap<String,Object>();
		 redisMap.put("midwareId", midwareRedisint.getMidwareId());
		 redisMap.put("redisintType", SysConfigConstants.REDIS_SENTINEL);
		 List<EcmMidwareRedisint> sentinelList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList",redisMap);
		 return sentinelList;
   }
	//从哨兵池中随机一个对象，增大容错率
	private EcmMidwareRedisint randomSentinel(List<EcmMidwareRedisint> sentinelList ) {
		Random random =new Random();
		if(sentinelList.size()>1) {
			EcmMidwareRedisint sentinel = sentinelList.get(random.nextInt(sentinelList.size()-1));
			return sentinel;
		}else if(sentinelList.size()==1 ) {
			EcmMidwareRedisint sentinel = sentinelList.get(0);
			return sentinel;
		}else {
			throw new GalaxyException("redis集群没有sentinels,请检查！");
		}
	}
	/**
     * 获取集群主信息
     * @param	 int midwareId redis实例对象  
     * @return	 List<EcmMidwareRedisint>  主实例列表 
     */   
	public  List<EcmMidwareRedisint> findRedisMaster(int midwareId) {
		Map<String,Object> redisMap= new HashMap<String,Object>();
		redisMap.put("midwareId", midwareId);
		redisMap.put("redisintType", SysConfigConstants.REDIS_MASTER);
		List<EcmMidwareRedisint> midList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList",redisMap);
		return midList;
	}
}	
