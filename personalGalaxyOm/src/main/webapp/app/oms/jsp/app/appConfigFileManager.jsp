<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<%
    String appId = request.getParameter("id");
    String appName = request.getParameter("name");
    String urlArgs = "";
    if (appId != null) {
        urlArgs = "?appId=" + appId;
    }
%>
<html>
	<head>
		<title>配置调整</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appConfigFileManager.js"></script>
	</head>
	<body>
	  <div class="mb-10"  id= "appTile" style ="display:none">
	   <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>应用管理</a>
                   <span>&gt;</span><span>配置调整</span><a
                          href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
      </div>
      <div class="mb-10"  id= "allTile"   style ="display:none">
      	   <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>一键部署</a>
                         <span>&gt;</span><span>全量应用配置</span><a
                                href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
            </div>
      <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
      	<div class="row">
      			 <label class="form-label span2"><span class="c-red">*</span>应用实例名称：</label>
      			 <div class="span3">
      				  <select name="queryAppIntantId" id="queryAppIntantId" size="1" class="select2" style="margin-top:5px">
      				  </select>
      			 </div>
      			<label class="form-label span2"><span class="c-red">*</span>配置文件名称：</label>
				 <div class="span3">
					  <select name="queryFileName" id="queryFileName" size="1" class="select2" style="margin-top:5px">
					  </select>
				 </div>
      			 <div class="span2">
      				  <a  onclick="searchRec()" class="button-select M ml-20 mr-20"><i class="iconfont">&#xe614;</i>查询</a>
      			 </div>
      	</div>
      	</form>
       <div class="mr-20 ml-20 mt-10">
			<table id="appConfigList" class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
				<tr>
					<th>配置文件名称</th>
					<th>配置参数名称</th>
					<th>配置参数值</th>
				</tr>
				</thead>
			</table>
       </div>
	</body>		  
</html>
<script type="text/javascript" >
$("#queryAppIntantId").change(function(){
         var appIntantId = $("#queryAppIntantId").val();
          getPkList({
    		  url:contextPath + "/findEcmAppFile",
    		  id:"queryFileName",
    		  async:false,
    		  params:{
    		     appIntantId:appIntantId
    		  }
    	  });
    })
 var urlArgs = '<%=urlArgs%>';
 var appId = '<%=appId%>';
</script>