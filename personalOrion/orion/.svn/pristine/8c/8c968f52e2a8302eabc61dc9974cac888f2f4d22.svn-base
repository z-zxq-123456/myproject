/**
 * <p>Title: JobContainer.java</p>
 * <p>Description: Job容器，存放已定义的Job配置</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.model;

import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tim
 * @version V1.0
 * @description Job容器，存放已定义的Job配置
 * @update 2014年9月15日 下午3:23:27
 */

@XStreamAlias("jobs")
public class JobContainer {

    @XStreamImplicit(itemFieldName = "mr_job")
    List<MRJob> mrj;

    @XStreamImplicit(itemFieldName = "dx_job")
    List<DXJob> sj;

    @XStreamImplicit(itemFieldName = "hb_job")
    List<HBJob> hj;

    @XStreamImplicit(itemFieldName = "gx_job")
    List<GXJob> gx;

    @Override
    public String toString() {
        return BeanUtils.getString(this);
    }

    private Map<String, AbstractJob> jobs;

    public void init() {
        jobs = new HashMap<>();
        if (mrj != null)
            for (MRJob ojb : mrj) {
                jobs.put(ojb.getName(), ojb);

            }
        if (sj != null)
            for (DXJob ojb : sj) {
                jobs.put(ojb.getName(), ojb);

            }
        if (hj != null)
            for (MRJob ojb : hj) {
                jobs.put(ojb.getName(), ojb);

            }

    }


    public MRJob getMRJob(String name) {
        if (null == mrj)
            return null;
        for (MRJob mj : mrj) {
            if (name.equals(mj.getName())) {
                return mj;
            }
        }
        return null;
    }

    public DXJob getSqoopJob(String name) {
        if (null == sj)
            return null;
        for (DXJob j : sj) {
            if (name.equals(j.getName())) {
                return j;
            }
        }
        return null;
    }

    public HBJob getHbaseJob(String name) {
        if (null == hj)
            return null;
        for (HBJob j : hj) {
            if (name.equals(j.getName())) {
                return j;
            }
        }
        return null;
    }

    public GXJob getGalaxyJob(String name) {
        if (null == gx)
            return null;
        for (GXJob j : gx) {
            if (name.equals(j.getName())) {
                return j;
            }
        }
        return null;
    }

    public AbstractJob getJob(String name) {
        return jobs.get(name);
    }
}
