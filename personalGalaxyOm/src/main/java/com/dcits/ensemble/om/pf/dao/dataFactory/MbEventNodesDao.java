package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventNodes;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lixue on 2015/11/30 16:59:11.
 */
@Repository
public class MbEventNodesDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventNodesDao";

    /**
     * This method corresponds to the database table MB_EVENT_NODES
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
    public List<PkList> getEventNodeforPklist(String prodType,String eventType) {
        MbEventNodes mbEventNodes = new MbEventNodes() ;
        mbEventNodes.setProdType(prodType);
        mbEventNodes.setEventType(eventType);
        return super.selectList(getNameSpace(),"getEventNodeforPklist",mbEventNodes);
    }
}