<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户列表</title>
    <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appInstantProgress.js"></script>
</head>
<body>
<h5 id="progress-animated" style="text-align:center"></h5>
<div class="bs-example" style="width:100%;margin-left:30px">
    <div class="progress progress-striped active">
        <div class="progress-bar" id="progress"></div>
    </div>
</div>
    <div class="bs-example" style="width:100%;margin-left:30px">
     <!--<img class="icon iconfont"  id="stateBut" src="&#xe609;" onClick="test(this)"/ >--!>
    <span id="stateBut_Id" class="icon iconfont"   style = "color:blue;font-size:14px" onClick="test(this)">查看日志：   &#xe609;</span>
         <div id="dlg"  style="display:none;width:100%">
         </div>
   </div>
</body>
</html>
<script   style = "text/javascript">

</script>