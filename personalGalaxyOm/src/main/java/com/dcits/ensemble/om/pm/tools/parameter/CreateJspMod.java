package com.dcits.ensemble.om.pm.tools.parameter;

public class CreateJspMod extends AbstractCreateParameter{
 public CreateJspMod( String jspPackage){
        super(jspPackage);
    }


    @Override
    public void setFileName(String beanName) {
        this.fileName = beanName.substring(0,1).toLowerCase()+beanName.substring(1) + "Mod"+getFilePostfix();
    }

    @Override
    public String getTemplateName() {
        return "parameter-modify.j";
    }

    @Override
    public String getFilePostfix() {
        return ".jsp";
    }



}
