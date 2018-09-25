package com.dcits.orion.stria.impl;

import com.dcits.orion.stria.NoGenerator;
import com.dcits.orion.stria.model.FlowModel;
import com.dcits.galaxy.base.util.DateUtils;

import java.util.Random;

/**
 * Created by Tim on 2015/5/19.
 */
public class DefaultNoGenerator implements NoGenerator {
    public String generate(FlowModel model) {
        String time = DateUtils.getDateTime();
        Random random = new Random();
        return time + "-" + random.nextInt(1000);
    }
}
