<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
	<head>
		<title>调用链TOP统计图</title>
		<script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/highcharts.js"></script>
		<script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/highcharts-3d.js"></script>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/tcecinStatTopHistogram.js"></script>
	</head>
	<body>
	<div class="mb-10">
          			 <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">管理视图</a><span >&gt;</span><span >调用链TOP统计图</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
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
                            <label class="form-label span2">请选择排序条件：</label>
                            <div class="formControls span3">
                                <select type="text" class="select2" name="statue" id="statue"></select>
                            </div>
                            <div class="span2"></div>
                            <a class="button-select S " onclick="searchRecCheck()"><i class="iconfont icon-4" ></i>查询</a>

                        </div>
          		</form>
         <div  class="mb-10"></div>
         <div id="container" style="min-width:100%; height: 400px; margin: 0 auto"></div>
         <div  class="mb-10"></div>
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
<script type="text/javascript">
$(document).ready(function() {
	$("#startTime").val(today());//获取文本id并且传入当前日期
	$("#endTime").val(today());//获取文本id并且传入当前日期
});
function today(){
             var today=new Date();
             var h=today.getFullYear();
             var m=today.getMonth()+1;
             var d=today.getDate()-1;
             m= m<10?"0"+m:m;   //  这里判断月份是否<10,如果是在月份前面加'0'
             d= d<10?"0"+d:d;        //  这里判断日期是否<10,如果是在日期前面加'0'
             return h+"-"+m+"-"+d;
 }

</script>