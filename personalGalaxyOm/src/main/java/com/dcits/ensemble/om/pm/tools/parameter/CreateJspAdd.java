package com.dcits.ensemble.om.pm.tools.parameter;

public class CreateJspAdd extends AbstractCreateParameter {
 public CreateJspAdd( String jspPackage){
        super(jspPackage);
    }


    @Override
    public void setFileName(String beanName) {
        this.fileName = beanName.substring(0,1).toLowerCase()+beanName.substring(1) + "Add"+getFilePostfix();
    }

    @Override
    public String getTemplateName() {
        return "parameter-insert.j";
    }

    @Override
    public String getFilePostfix() {
        return ".jsp";
    }
}
