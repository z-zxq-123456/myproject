package com.dcits.ensemble.om.pm.tools.parameter;


public class CreateJavaScript extends AbstractCreateParameter {

    public CreateJavaScript(String jspPackage) {
        super(jspPackage);
    }

    @Override
    public void setFileName(String beanName) {
        //System.out.print("beanName " + beanName);
        this.fileName = beanName.substring(0, 1) + beanName.substring(1) + getFilePostfix();
    }

    @Override
    public String getTemplateName() {
        return "parameter-inquiry-js.j";
    }

    @Override
    public String getFilePostfix() {
        return ".js";
    }
}
