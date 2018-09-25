package com.dcits.galaxy.sequences;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 管理序列名称，为了后面快速检索是否有已经存在名称
 * <p/>
 * Created by Tim on 2016/4/21.
 */
public class SequencesNameManager {

    private Set names = new ConcurrentSkipListSet();

    private static SequencesNameManager sequencesNameManager;

    public static SequencesNameManager getInstance() {
        if (null == sequencesNameManager)
            sequencesNameManager = new SequencesNameManager();
        return sequencesNameManager;
    }

    /**
     * 添加名称
     *
     * @param name
     */
    public void addName(String name) {
        names.add(name);
    }

    public boolean contains(String name) {
        return names.contains(name);
    }
}
