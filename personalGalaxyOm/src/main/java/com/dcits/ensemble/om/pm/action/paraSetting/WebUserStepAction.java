package com.dcits.ensemble.om.pm.action.paraSetting;

import com.dcits.ensemble.om.pm.dao.paraSetting.ParaUserAuthorityDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaUserAuthority;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/authority")
public class WebUserStepAction {


    @Resource
    ParaUserAuthorityDao authorityDao;

    @RequestMapping("findAll")
    public void findAll(HttpServletRequest request, PrintWriter printWriter) {
        List<ParaUserAuthority> infoList = null;
        try{
            infoList= authorityDao.findAll();
            ActionResultWrite.setReadResult(printWriter, infoList);
        }catch(Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }
    @RequestMapping("add")
    public void add(HttpServletRequest request,PrintWriter printWriter,ParaUserAuthority userAuthority){
        try{
            authorityDao.insert(userAuthority);
            ActionResultWrite.setsuccessfulResult(printWriter);
        }catch(Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }
    @RequestMapping("update")
    public void update(HttpServletRequest request,PrintWriter printWriter,ParaUserAuthority userAuthority){
        try{
            authorityDao.updateByPrimaryKey(userAuthority);
            ActionResultWrite.setsuccessfulResult(printWriter);
        }catch(Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }
    @RequestMapping("delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter,ParaUserAuthority userAuthority){
        try{
            authorityDao.deleteByPrimaryKey(userAuthority);
            ActionResultWrite.setsuccessfulResult(printWriter);
        }catch(Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,"操作错误，系统异常退出！");
        }
    }
}
