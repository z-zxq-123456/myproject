<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>对不起，您访问的页面存在问题！</title>
    <link rel="stylesheet" type="text/css" href="./css/error/main.css">
</head>
<body>
<div id="wrapper"><a class="logo" href="images/logo.png"></a>
    <div id="main">
        <div class="img-center">
            <img src="./images/error.png" alt="">
        </div>
        <p class="text-red">哎呀...您访问的页面好像出错了</p>
        <p class="text-red">${errorMsg}</p>
        <!--<p>当您看到这个页面,表示您的访问出错,这个错误是您打开的页面不存在,请确认您的操作是正确的,如果是在本站点击后出现这个页面,请联系管理员处理,或者请通过点击返回上一页再一次尝试操作，感谢您的支持!</p>  -->
        <div class="utilities">
            <div class="clear"></div>
        </div>
    </div>
</div>
</body>
</html>
