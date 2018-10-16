<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
	<head>
		<title>中间件部署视图</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareDeployView.js"></script>
	</head>
	<body>
			  <div class="mb-10">
				  <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>c操作流水</a><span>&gt;</span>中间件监控流水</a><span>&gt;</span><span>中间件操作流水</span><a
								href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
			  </div>
      		<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
      		<div class="row">
      				 <label class="form-label span2"><span class="c-red">*</span>部署人：</label>
      				 <div class="formControls span3">
      					  <select name="userId" id="queryUserId" size="1" class="select2" style="margin-top:5px">
      					  </select>
      				 </div>
      				<label class="form-label span2"><span class="c-red">*</span>中间件类型：</label>
					  <div class="formControls span3">
                                <select name="midwareType" id="queryMiderParaCode" size="1" class="select2" style="margin-top:5px">
                                </select>
                            </div>
      		</div>
        	<div class="row">
                            <label class="form-label span2">起始日期：</label>
                            <div class="formControls span3">
                                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" id="startTime" name="startTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
                            </div>
                            <label class="form-label span2">终止日期：</label>
                            <div class="formControls span3">
                              <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})" id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
                            </div>
                            <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
                                <i class="iconfont">&#xe624;</i> 查询</a>
                        </div>
      		</form>
	  <div class="mr-20 ml-20 mt-10">
     				<table id="midwareDeployViewList" class="table table-border table-bordered table-hover table-bg table-sort">
     					<thead>
     					<tr>
     						<th>应用部署日期</th>
     						<th>集群名称</th>
     						<th>中间件类型</th>
     						<th>实例名称</th>
     						<th>实例类型</th>
     						<th>实例IP</th>
     						<th>实例版本号</th>
     						<th>应用安装路径</th>
     						<th>部署人</th>
     					</tr>
     					</thead>
     				</table>
     		 </div>
	</body>		  
</html>