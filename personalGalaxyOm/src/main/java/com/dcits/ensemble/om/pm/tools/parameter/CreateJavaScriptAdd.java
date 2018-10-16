package com.dcits.ensemble.om.pm.tools.parameter;

public class CreateJavaScriptAdd extends AbstractCreateParameter {
 public CreateJavaScriptAdd( String jspPackage){
        super(jspPackage);
    }


    @Override
    public void setFileName(String beanName) {
        this.fileName = beanName.substring(0,1).toLowerCase()+beanName.substring(1) + "Add"+getFilePostfix();
    }

    @Override
    public String getTemplateName() {
        return "parameter-insert-js.j";
    }

    @Override
    public String getFilePostfix() {
        return ".js";
    }
}
