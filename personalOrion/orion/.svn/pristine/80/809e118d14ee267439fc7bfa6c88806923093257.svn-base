package com.dcits.orion.batch;

import com.dcits.orion.batch.api.IBatchStartCheck;
import com.dcits.orion.batch.api.data.CheckResult;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixbb on 2016/6/7.
 */
@Repository
public class BatchStartCheck  implements IBatchStartCheck{
    @Override
    public CheckResult check(String batchClass) {

        if("MAIN".equals(batchClass))
        {


            ////此处理是做检查

                String errorMsg = "有未关门的机构！";
                List tableHead = new ArrayList<>();
                tableHead.add(new Object[]{ "机构号", 40.0});
                tableHead.add(new Object[]{"机构名称",60.0});

                List tableContent = new ArrayList();
                tableContent.add(new String[]{"1400","深圳分行"});
                tableContent.add(new String[]{"2200","西安分行"});

                return new CheckResult(errorMsg,tableHead,tableContent);



        }
        else return new CheckResult();


    }
}
