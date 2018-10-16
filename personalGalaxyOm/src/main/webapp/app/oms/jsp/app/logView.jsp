<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<%
    String appId = request.getParameter("id");
    String urlArgs = "";
    if (appId != null) {
        urlArgs = "?appId=" + appId;
    }
%>
<script type="text/javascript" >
 var urlArgs = '<%=urlArgs%>';
</script>

<html>
<head>
<title>显示日志</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/logView.js"></script>
</head>
<body>
    <div class="mb-10">
		 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">应用管理</a><span >&gt;</span><span >应用日志</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
	</div>
	<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
	<div class="row">
			 <label class="form-label span2"><span class="c-red">*</span>实例名称：</label>
			 <div class="span3">
				  <select name="appIntantId" id="appIntantId" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <label class="form-label span2"><span class="c-red">*</span>单次查看行数：</label>
			 <div class="span3">
				  <select name="lineNumber" id="lineNumber" size="1" class="select2" style="margin-top:5px">
				  </select>
			 </div>
			 <div class="span2">
				  <a  onclick="searchRec()" class="button-select M ml-20 mr-20"><i class="iconfont">&#xe614;</i>查询</a>
			 </div>
	</div>
	</form>
	<div id="dlg"  style="width:99%;padding:50px 10px 0px;" type="hidden">
    </div>
</body>
</html>