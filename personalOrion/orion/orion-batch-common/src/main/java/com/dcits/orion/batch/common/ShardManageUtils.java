package com.dcits.orion.batch.common;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2016/2/29.
 */
@Repository
public class ShardManageUtils implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<ShardManager, ShardSqlSessionTemplate> mapping;

    public static ShardSqlSessionTemplate getShardSqlSessionTemplate(ShardManager shardManager) {
        return mapping.get(shardManager);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        mapping = new HashMap<>();
        Map<String, ShardSqlSessionTemplate> shardSqlSessionTemplates = SpringApplicationContext.getContext().getBeansOfType(ShardSqlSessionTemplate.class);
        for (ShardSqlSessionTemplate shardSqlSessionTemplate : shardSqlSessionTemplates.values()) {
            mapping.put(shardSqlSessionTemplate.getShardManager(), shardSqlSessionTemplate);
        }
    }
}
