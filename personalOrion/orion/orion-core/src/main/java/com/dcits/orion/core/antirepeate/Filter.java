package com.dcits.orion.core.antirepeate;

import com.dcits.galaxy.base.data.BaseRequest;

import java.util.List;

/**
 * Created by lixbb on 2016/2/2.
 */
public class Filter {
    private String[] keys;

    private List<Condition> includes;

    private List<Condition> excludes;

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public List<Condition> getIncludes() {
        return includes;
    }

    public void setIncludes(List<Condition> includes) {
        this.includes = includes;
    }

    public List<Condition> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<Condition> excludes) {
        this.excludes = excludes;
    }
    public Filter(String keys)
    {
        if (keys != null && keys.trim().length() > 0)
            this.keys = keys.split(",");

    }
    public boolean accept(BaseRequest request)
    {
        if (match(request,includes) && !match(request,excludes))
            return true;
        else return false;
    }
    private boolean match(BaseRequest request,List<Condition> conditions)
    {
        if (conditions != null)
        {
            for (Condition condition : conditions)
            {
                if (condition.accept(request))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
