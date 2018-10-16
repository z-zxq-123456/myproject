<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用指标监控</title>
    <script type="text/javascript" charset="UTF-8" src="${ContextPath}/app/oms/js/shshpoc/appIndexQuery.js"></script>
       <script type="text/javascript" charset="UTF-8" src="${ContextPath}/app/oms/js/commons/highcharts.js"></script>
            <script type="text/javascript" src="${ContextPath}/js/application.js"></script>
</head>
<body>
<div class="mb-10">
<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">管理视图</a><span>&gt;</span><span>应用指标监控</span><a href="javascript:location.replace(location.href);"
                                                                    title="刷新"><i class="iconfont">&#xe61e;</i></a>
    </nav>
</div>
  <div id="container" style="width: 90%; height: 350px; margin: 0 auto"></div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">监控周期：</label>
        <div class="formControls span2">
            <select name="appMoniPeriod" id="qeryAppMoniPeriod" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
         <label>分钟</label>
        <div class=" span2"></div>
        <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
            <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
</form>
 <div class="span1"    id = "queryDataMessage"></div>
 <div class="mr-20 ml-20 mt-10" id="indexQueryTableDiv">
 </div>
</body>
 <script type="text/javascript">
  var 	options ;
  var   chart ;
$(document).ready(function () {
		startTimeOut();
		options = {
				chart: {
					renderTo: 'container',
					defaultSeriesType: 'line',
					marginRight: 130,
					marginBottom: 35
				},
				title: {
					text: '应用指标监控折线图',
					x: -20 //center
				},
				subtitle: {
					text: '',
					x: -50
				},
				xAxis: {
				    title: {
						text: '时间'
				    },
					categories: []
				},
				yAxis: {
					tickPixelInterval: 100,
					max:500,
					min:0,
					title: {
						text: '监控指标'
				    }
				},
				tooltip: {
					formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+
							this.x +'时刻,指标值为'+ this.y ;
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: 10,
					y: 200,
					borderWidth: 1
				},
				series: []
		};
	});
	function startTimeOut(){
	    var delayTime = $("#qeryAppMoniPeriod option:selected").text();
	    if(delayTime==''){
	    	delayTime = 1*100;
		}else{
			delayTime = delayTime*60*1000;
		}
		setTimeout('startTimeOut()',delayTime);
		setTimeout('refresh()',delayTime);
		searchRec();
	}
	function refresh(){
         location.replace(location.href);
    }

  </script>
</html>
