<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/formServerSide.jsp"%>
<html>
<head>
<style>
      body {
        text-align: center;
		font-family: Arial;
      }

      #g1 {
        width:220px; height:120px;
        display: inline-block;
        margin: 1em;
		border: 1px soild #202020;
		box-shadow: 0px 0px 15px #101010;
		margin-top: 10px;
		border-radius: 8px;
      }
      #g2 {
              width:220px; height:120px;
              display: inline-block;
              margin: 1em;
      		border: 1px soild #202020;
      		box-shadow: 0px 0px 15px #101010;
      		margin-top: 10px;
      		border-radius: 8px;
            }
      #g3 {
              width:220px; height:120px;
              display: inline-block;
              margin: 1em;
      		border: 1px soild #202020;
      		box-shadow: 0px 0px 15px #101010;
      		margin-top: 10px;
      		border-radius: 8px;
            }
      #g4 {
              width:220px; height:120px;
              display: inline-block;
              margin: 1em;
      	    border: 1px soild #202020;
      		box-shadow: 0px 0px 15px #101010;
      		margin-top: 10px;
      		border-radius: 8px;
            }


    </style>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/highcharts.js"></script>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/justgage.1.0.1.js"></script>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/justgage.1.0.1.min.js"></script>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/raphael.2.1.0.min.js"></script>

	</head>
<script type="text/javascript">
            var serId = parent.$("#serName").val();
			var firstPoint;
				$(document).ready(function() {
                 $.ajax({
                   url : contextPath + "/findServerMonitorInfo?serId="+serId ,
                   async : false,
                   type : "POST",
                   success : function (result) {
                   var dataJson = JSON.parse(result);
                   if(dataJson.success ) {
                   firstPoint = dataJson.data[0];
                   }
                  }
                  });
                 var 	options = {
                        chart: {
                            borderColor: '#EBBA95',
                            borderRadius: 20,
                            backgroundColor: '#FCFFC5',
                            renderTo: 'container1',
                            defaultSeriesType: 'line',
                            events: {load: function(){
                                var data = setSeriesData(firstPoint.serMoniCpu);
                                var myseries = this.series[0];
                                myseries.setData(data);
                                data = null;
                                }}
                        },
                        title: {
                            text: 'CPU监控',
                            x: -20 //center
                        },
                        xAxis: {
                        maxPadding: 0.2,
                        type: '时间',
                        tickPixelInterval: 70,
                        labels:{formatter:function(){
                         var vDate = new Date(this.value);
                         return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
                        }}
                    },
                    yAxis: {
                        title: {
                            text: ''
                        },
                        min:0,
                        max: 100 ,
                        labels:{formatter:function(){
                            return parseInt(this.value)+"%";
                        }},
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#fc6021'
                        }]
                    },
                        tooltip: {
                            formatter: function() {
                                    var vDate = new Date(this.x);
                                    return '<b>'+ this.series.name +'</b><br/>'+
                                    '时间 :'+vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds() +','+'指标 :'+ this.y +'%';
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'top',
                            borderWidth: 1,
                            enabled : false
                        },
                        series: [{
                               name: 'CPU',
                               color: '#fc6021',
                               data: []
                        }]
                };
                var chart = new Highcharts.Chart(options);
                var tpser = chart.series;
 var 	options2 = {
                        chart: {
                            borderColor: '#EBBA95',
                            borderRadius: 20,
                            backgroundColor: '#00FF7F',
                            renderTo: 'container2',
                            defaultSeriesType: 'line',
                            events: {load: function(){
                                var data = setSeriesData(firstPoint.serMoniMem);
                                var myseries = this.series[0];
                                myseries.setData(data);
                                data = null;
                                }}
                        },
                        title: {
                            text: 'Memory监控',
                            x: -20 //center
                        },

                        xAxis: {
                        maxPadding: 0.2,
                        type: '时间',
                        tickPixelInterval: 70,
                        labels:{formatter:function(){
                            var vDate = new Date(this.value);
                            return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
                        }}
                    },
                    yAxis: {
                        title: {
                            text: ''
                        },
                        min:0,
                        max: 100 ,
                        labels:{formatter:function(){
                            return parseInt(this.value)+"%";
                        }},
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#920783'
                        }]
                    },
                        tooltip: {
                            formatter: function() {
                                    var vDate = new Date(this.x);
                                    return '<b>'+ this.series.name +'</b><br/>'+
                                    '时间 :'+vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds() +','+'指标 :'+ this.y +'%';
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'top',
                            borderWidth: 1,
                            enabled : false
                        },
                        series: [{
                               name: 'Memory',
                               data: [],
                               color: '#FF0000'
                        }]

                };
                var chart2 = new Highcharts.Chart(options2);
                var tpser2 = chart2.series;
 var 	options3 = {
                     chart: {
                         borderColor: '#EBBA95',
                         borderRadius: 20,
                         backgroundColor: '#87CEFA',
                         renderTo: 'container3',
                         defaultSeriesType: 'line',
                         events: {load: function(){
                             var data = setSeriesData(firstPoint.serMoniIo);
                             var myseries = this.series[0];
                             myseries.setData(data);
                             data = null;
                             }}
                     },
                     title: {
                         text: '磁盘IO监控',
                         x: -20 //center
                     },
                     xAxis: {
                     maxPadding: 0.2,
                     type: '时间',
                     tickPixelInterval: 70,
                     labels:{formatter:function(){
                         var vDate = new Date(this.value);
                         return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
                     }}
                 },

                 yAxis: {
                     title: {
                         text: ''
                     },
                     min:0,
                     max: 100 ,
                     labels:{formatter:function(){
                         return parseInt(this.value)+"%";
                     }},
                     plotLines: [{
                         value: 0,
                         width: 1,
                         color: '#920783'
                     }]
                 },
                     tooltip: {
                         formatter: function() {
                                 var vDate = new Date(this.x);
                                 return '<b>'+ this.series.name +'</b><br/>'+
                                 '时间 :'+vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds() +','+'指标 :'+ this.y +'%';
                         }
                     },
                     legend: {
                         layout: 'vertical',
                         align: 'right',
                         verticalAlign: 'top',
                         borderWidth: 1,
                         enabled :false
                     },
                     series: [{
                            name: 'IO',
                            data: [],
                            color: '#920783'
                     }]

             };
             var chart3 = new Highcharts.Chart(options3);
             var tpser3 = chart3.series;
 var 	options4 = {
                 chart: {
                     borderColor: '#EBBA95',
                     borderRadius: 20,
                     backgroundColor: '#FFDEAD',
                     renderTo: 'container4',
                     defaultSeriesType: 'line',
                     events: {load: function(){
                         var data = setSeriesData(firstPoint.serMoniNet);
                         var myseries = this.series[0];
                         myseries.setData(data);
                         data = null;
                         }}
                 },
                 title: {
                     text: '网络带宽监控',
                     x: -20 //center
                 },
                 xAxis: {
                 maxPadding: 0.2,
                 type: '时间',
                 tickPixelInterval: 70,
                 labels:{formatter:function(){
                     var vDate = new Date(this.value);
                     return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
                 }}
             },

             yAxis: {
                 title: {
                     text: ''
                 },
                 min:0,
                 labels:{formatter:function(){
                     return parseFloat(this.value)+"KB/S";
                 }},
                 plotLines: [{
                     value: 0,
                     width: 1,
                     color: '#920783'
                 }]
             },
                 tooltip: {
                     formatter: function() {
                             var vDate = new Date(this.x);
                             return '<b>'+ this.series.name +'</b><br/>'+
                             '时间 :'+vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds() +','+'指标 :'+ this.y +'KB/S';
                     }
                 },
                 legend: {
                     layout: 'vertical',
                     align: 'right',
                     verticalAlign: 'top',
                     borderWidth: 1,
                     enabled :false
                 },
                 series: [{
                        name: 'Net',
                        data: [],
                        color: '#920783'
                 }]

         };
         var chart4 = new Highcharts.Chart(options4);
         var tpser4 = chart4.series;
         var g1,g2,g3,g4;
              window.onload = function(){
                var g1 = new JustGage({
                  id: "g1",
                  value: 0,
                  min: 0,
                  max: 100,
                  title: "CPU",
                  label: "%",
                    levelColors: [
                      "#008B45",
                      "#FFFF00",
                      "#FF0000"
                    ]
                });
                 var g2 = new JustGage({
                          id: "g2",
                          value: 0,
                          min: 0,
                          max: 100,
                          title: "memory",
                          label: "%",
                            levelColors: [
                              "#008B45",
                              "#FFFF00",
                              "#FF0000"
                            ]
                        });

     var g3 = new JustGage({
              id: "g3",
              value: 0,
              min: 0,
              max: 100,
              title: "IO",
              label: "%",
                levelColors: [
                  "#008B45",
                  "#FFFF00",
                  "#FF0000"
                ]
            });
             var g4 = new JustGage({
                      id: "g4",
                      value: 0,
                      min: 0,
                      max: 7000,
                      title: "net",
                      label: "KB/S",
                        levelColors: [
                          "#008B45",
                          "#FFFF00",
                          "#FF0000"
                        ]
                    });
             g1.refresh(firstPoint.serMoniCpu);
             g2.refresh(firstPoint.serMoniMem);
             g3.refresh(firstPoint.serMoniIo);
             g4.refresh(firstPoint.serMoniNet);
                    setInterval(function() {
                    $.ajax({
                       url : contextPath + "/findServerMonitorInfo?serId="+serId ,
                       async : false,
                       type : "POST",
                       success : function (result) {
                       var dataJson = JSON.parse(result);
                       if(dataJson.success ) {
                          var data = dataJson.data[0];
                          var date = new Date(data.serMoniMs);
                          tpser[0].addPoint([date.getTime(),parseFloat(data.serMoniCpu)], true, true);
                          tpser2[0].addPoint([date.getTime(),parseFloat(data.serMoniMem)], true, true);
                          tpser3[0].addPoint([date.getTime(),parseFloat(data.serMoniIo)], true, true);
                          tpser4[0].addPoint([date.getTime(),parseFloat(data.serMoniNet)], true, true);
                          g1.refresh(data.serMoniCpu);
                          g2.refresh(data.serMoniMem);
                          g3.refresh(data.serMoniIo);
                          g4.refresh(data.serMoniNet);
                       } else {
                          return ;
                       }
                       }
                    });
                    }, parseFloat(firstPoint.refreshTime)*1000);
              };
        });


     function setSeriesData( info ){
						 var data = [] , i;
						 var date = new Date(firstPoint.serMoniMs);
						 for (i = -9; i <= -1; i++) {
							data.push({
								x: date.getTime()+i*10000,
								y: 0
							});
						 }
						 data.push({
                         x: date.getTime(),
                         y: parseFloat(info)
                         });
						return data;
	 }
          </script>
	<body>
     <div id="g1"></div>
     <div id="g2"></div>
     <div id="g3"></div>
     <div id="g4"></div>

      <div id="container1" style="width:527px; height: 250px;float:left; margin: 8px 8px"></div>
      <div id="container2" style="width:527px; height: 250px;float:left; margin: 8px 8px"></div>
      <div id="container3" style="width:527px; height: 250px;float:left; margin: 8px 8px"></div>
      <div id="container4" style="width:527px; height: 250px;float:left; margin: 8px 8px"></div>

	</body>

</html>

