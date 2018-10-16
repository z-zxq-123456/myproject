<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>应用不间断升级视图</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/leaderview/appUpgViewManager.js"></script>
	</head>
	<body class="easyui-layout"> 	
	  <div class="mb-10">
	  <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">操作流水</a><span >&gt;</span><span >应用监控流水</span>
                			 <span >&gt;</span><span >不间断升级流水</span>
                			 <a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
           		</div>
           		<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
           		<div class="row">
           				<label class="form-label span2"><span class="c-red">*</span>应用名称：</label>
     					 <div class="formControls span3">
     						  <select name="appId" id="queryAppId" size="1" class="select2" style="margin-top:5px">
     						  </select>
     					 </div>
     					 <label class="form-label span2">起始日期：</label>
						  <div class="formControls span3">
							  <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="startTime" name="startTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
						  </div>
           		</div>
             	<div class="row">
					 <label class="form-label span2">终止日期：</label>
					 <div class="formControls span3">
					   <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
					 </div>
					  <div class="span1"></div>
					 <a id="select" href="#" href="javascript:location.replace(location.href);" class="button-sure M" style="margin-bottom:10px">
						 <i class="iconfont">&#xe624;</i> 查询</a>
					 <a id="selectVaild" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
                     						 <i class="iconfont">&#xe624;</i>查看升级验证规则</a>
                     <a id="selectTransfer" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
                     						 <i class="iconfont">&#xe624;</i>查看升级流转信息</a>
				 </div>
           		</form>
     	  <div class="mr-20 ml-20 mt-10">
          				<table id="upgViewList" class="table table-border table-bordered table-hover table-bg table-sort">
          					<thead>
          					<tr>
          						<th>升级日期</th>
          						<th>应用名称</th>
          						<th>升级状态</th>
          						<th>升级旧版本号</th>
          						<th>升级新版本号</th>
          					</tr>
          					</thead>
          				</table>
          		 </div>
	</body>		  
</html>
  