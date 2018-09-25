package com.dcits.orion.core.antirepeate;

import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.tuple.TwoTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixbb on 2016/2/2.
 */
public class Condition {
    private List<TwoTuple> contents;
    private static final Logger logger = LoggerFactory
            .getLogger(Condition.class);
    public List<TwoTuple> getContents() {
        return contents;
    }
    public void setContents(String condition) {
        if (condition !=null && condition.trim().length() > 0)
        {
            contents = new ArrayList<>();
            String conditions[] = condition.split(",");
            for (String conditionStr : conditions)
            {
                String[] con = conditionStr.split("=");
                TwoTuple content = new TwoTuple(con[0],con[1]);
                contents.add(content);
            }
        }
    }
    public boolean accept(BaseRequest request)
    {
        if (contents != null)
        {
            int total = 0;
            int acceptCount = 0;
            for (TwoTuple<String,String> content : contents)
            {
                try {
                    Object keyValue = request.readValue(content.first);
                    if (keyValue != null)
                    {
                        if (content.second.equals(keyValue))
                        {
                            acceptCount++;
                        }
                    }

                } catch (Exception e) {

                    if (logger.isWarnEnabled())
                    {
                        logger.warn("Request中取不到指定域值！key=" + content.first +" Request=" + request);
                    }
                }
                total++;
            }
            if (total == acceptCount && total > 0)
            {
                return true;
            }
        }
        return false;
    }

}
