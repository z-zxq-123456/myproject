<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
	<head>
		<title>调用链查看视图</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/tcecinStatManager.js"></script>
	</head>
	<body>
       <div class="mb-10">
      			 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">管理视图</a><span >&gt;</span><span >调用链查看视图</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
      		</div>
      		<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
        	<div class="row">
                            <label class="form-label span2">起始日期：</label>
								<div class="formControls span3">
										<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" id="startTime" name="startTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
								</div>
                            <label class="form-label span2">终止日期：</label>
								<div class="formControls span3">
								 		 <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})" id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
								</div>
            </div>
            <div class="row">
                  				 <label class="form-label span2"><span class="c-red">*</span>消息码：</label>
                  					   <div class="formControls span3">
            							   <input class="input-text grid" name="messageCode" id="messageCode" type="text" style="margin-top:5px">
            						   </div>

                  				<label class="form-label span2"><span class="c-red">*</span>消息类型：</label>
            						  <div class="formControls span3">
            							   <input class="input-text grid" name="messageType" id="messageType" type="text" style="margin-top:5px">
            						   </div>
                  		</div>
            <div class="row">
						  <label class="form-label span2"><span class="c-red">*</span>服务码：</label>
							  <div class="formControls span3">
								   <input class="input-text grid" name="serviceCode" id="serviceCode" type="text" style="margin-top:5px">
							   </div>
							   <div class="formControls span3">
                               	  </div>
							  <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
								   <i class="iconfont">&#xe624;</i> 查询</a>
                  		</div>
      		</form>
	  <div class="mr-20 ml-20 mt-10">
     				<table id="deployViewList" class="table table-border table-bordered table-hover table-bg dateTable-nosort">
     					<thead>
     					<tr>
     						<th>日期</th>
     						<th>消息码</th>
     						<th>消息类型</th>
     						<th>服务码</th>
     						<th>访问量</th>
     						<th>成功量</th>
     						<th>成功率</th>
     						<th>平均耗时</th>
     						<th>峰值</th>
     					</tr>
     					</thead>
     				</table>
     		 </div>
	</body>
</html>