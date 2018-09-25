package com.dcits.galaxy.sequences;

import com.dcits.galaxy.sequences.model.Sequences;

import java.util.Map;

/**
 * 序列属性配置
 * <p/>
 * Created by Tim on 2016/4/3.
 */
public class SequencesConfiguration {

    private Map<String, Sequences> configuration;

    public Map<String, Sequences> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, Sequences> configuration) {
        this.configuration = configuration;
    }

    public Sequences getSequencesConfiguration(String seqName) {
        if (configuration != null && configuration.containsKey(seqName))
            return configuration.get(seqName);
        else
            return new Sequences(seqName);
    }
}
