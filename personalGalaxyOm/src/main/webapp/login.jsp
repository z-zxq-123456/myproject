<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/portralonly.jsp"%>
<html>
<head>
    <title>Sm@rtOM 新一代运营管理平台</title>
</head>
<body onkeypress="keyLogin(event);">
<div class="logo">
    </div>
    <form action="" method="post"  id="login-main" >
        <p class="text">Sm@rtOM 运营管理平台</p>
        <p class="copyright">Copyright&#169;中信百信银行股份有限公司 <span class="version" name="version" >v1.0</span></p>
        <input name="name" placeholder="账号" class="name" id="InputUserName" required />
        <input name="password" placeholder="密码" class="password" type="password" id="InputPassword" required />
        <input  class="btn" type="button" value="  登   录  " onclick="return login(this)" id="login-btn"/>
    </form>
    <div class="msg" id="login-msg" style="display:none;"><span class="textMsg" >用户名和密码不匹配，请重新输入</span></div>
</body></html>

