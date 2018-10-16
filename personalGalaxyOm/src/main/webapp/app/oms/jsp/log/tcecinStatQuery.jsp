<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
	<head>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/highcharts.js"></script>

	</head>
	<script type="text/javascript">
            var chartData=parent.chartData;
            var	chart;
            var yScale = parent.yScale;
            var title = parent.title;
        	$(document).ready(function() {
        		chart = new Highcharts.Chart({
        				chart: {
        					renderTo: 'container',
        					defaultSeriesType: 'line',

        				},
        				title: {
        					text: title

        				},

        				xAxis: {
        				    title: {
        						text: '日期'
        				    },


        					categories: chartData.date
        				},
        				yAxis: {
        					min:0,
        					title: {
        						text: yScale
        				    }
        				},
        				tooltip: {
        					formatter: function() {
        			                return '<b>'+ this.series.name +'</b><br/>'
        							+"日期："+this.x +',数值：'+ this.y ;
        					}
        				},
        				legend: {
        					layout: 'vertical',
        					align: 'right',
        					verticalAlign: 'top',
        					borderWidth: 1
        				},
        				series:[chartData]
        		});
        	});


          </script>
	<body>
      <div id="container" style="width:720px; height: 400px; margin: 0 auto"></div>
	</body>

</html>