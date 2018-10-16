/** 
* <p>Title: EcmMbsdDetailsDao<p>
* <p>Description: Table ecm_mbsd_details Dao operator<p>
* <p>Copyright: Copyright (c) 2015<p>
* <p>Description: <p>
* <p>Company: dcits<p>
* @author galaxyTools 
* @version V1.0
*/

package com.dcits.ensemble.om.pf.dao.tools;

import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class EcmMbsdDetailsDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.tools.EcmMbsdDetailsDao";

    /**
     * This method corresponds to the database table ecm_mbsd_details
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}