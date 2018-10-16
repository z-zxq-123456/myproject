 $(document).ready(function() {
    		getPkList({
				  url: contextPath + "/findParaCombox?paraParentId=0120&&isDefault=true",
				  id: "statue",
				  async: false,
				  normalSelect: false
		  	});
		  	 var opt = getDataTableOpt($("#deployViewList"));
                opt.stateSave = true;
                opt.processing = true;
                opt.autoWidth = false;

                opt.ajax = {
                    "url": contextPath + "/findTcecinInfo?statue="+ $("#statue").val(),
                    "type": "POST"
                };
                opt.columns = [
                    {
                        "data": "traceStatDate",
                        "defaultContent": ""
                    },
                    {
                        "data": "messageCode",
                        "defaultContent": ""
                    },
                    {
                        "data": "messageType",
                        "defaultContent": ""
                    },
                    {
                        "data": "serviceCode",
                        "defaultContent": ""
                    },
                    {
                        "data": "traceStatAmount",
                        "defaultContent": ""
                    },
                    {
                        "data": "traceStatOknum",
                        "defaultContent": ""
                    },
                    {
                        "data": "traceStatOkperc",
                        "defaultContent": ""
                    },
                    {
                        "data": "traceStatAvertime",
                        "defaultContent": ""
                    },
                    {
                        "data": "traceStatPeaknum",
                        "defaultContent": ""
                    }
                ];
                //渲染tables
                drawDataTable($("#deployViewList"), opt);
                 //  drawDataTableServerSide($("#deployViewList"), opt,10);
                //界面美化tables
                $("#deployViewList").beautyUi({
                    tableId: "deployViewList",
                    needBtn: false,
                    serverSide : true
                });
                $('#deployViewList').on('page.dt', function (e) {
					$('#deployViewList').find("tr").removeClass('selected');
				});

   });
	$("#statue").ready(function(){
			searchRec ();//页面打开的时候默认查询一次
	});

  function 	searchRecCheck(){
      var startTime = $("#startTime").val();
	  var endTime  = $("#endTime").val();
	    if(startTime==""){
		  alert("请输入开始时间");
		  return;
		}
		if(endTime==""){
		  alert("请输入结束时间");
		  return;
		}
		searchRec();
  }
  function searchRec() {
		  var statue =  $("#statue").val();
		  if(statue==""){
			  alert("请选择查询条件");
			  return;
		  }else{
			  var startTime = $("#startTime").val();
			  var endTime  = $("#endTime").val();
			  var userTab = $("#deployViewList").dataTable();
			  var api = userTab.api();
			  api.ajax.url(contextPath+"/findTcecinInfo?startTime="+startTime+"&endTime="+endTime+"&statue="+statue).load();
			  var sub_url = contextPath + '/findTcecinTop';
			  $.post(sub_url,{statue:statue,startTime:startTime,endTime:endTime},function(result){
					var result = eval('('+result+')');
					if (result.errorMsg){
							  $.messager.show({
								  title: 'Error',
								  msg: result.errorMsg
							  });
					} else {
				   // alert(result.data.list[0]);
				   // alert(JSON.stringify(result.data.list[0]));
					parseResult( result.data[0]);
					}
			  });
		  }
  }
  function  parseResult(result){
               var  title =  $("#statue  option:selected").text();
               var statue =  $("#statue").val();
                 var chartColor= "lightgray";
               if(statue=="0120001"){
				   var colors= "chartreuse";
               }
               if(statue=="0120002"){
				   var colors= "green";
			   }
			   if(statue=="0120003"){
				   var colors= "navy";
			   }
			   if(statue=="0120004"){
				   var colors= "darkslateblue";
			   }
			   if(statue=="0120005"){
				   var colors= "navy";
			   }
               $('#container').highcharts({
					chart: {//默认图表类型line, spline, area, areaspline, column, bar, pie , scatter  选择图形的类型
						type: 'column',
						backgroundColor:chartColor,
						options3d:{
							enabled:true,
							 alpha:15,
							 beta: 15,
							depth: 50,
							viewDistance: 500
						}
					},
					title: {
						text: title+'TOP10'
					},
				//subtitle: {
				//		text: '统计'
				//	},
					xAxis: {//添加x轴的坐标
						categories: result.date
					},
					yAxis: {//添加y轴坐标
						min: 0,
						title: {
							text:title+'指标 (mm)'
						},
						labels: {//格式化纵坐标的显示风格
							formatter: function() {
								return this.value ;
							}
						},
						opposite: false//反转
					},
					colors:[colors],
					legend: {//是否显示底注，legend用于设置图例相关属性。
						 enabled: false
					},
					tooltip: {
						formatter: function() {
						   if(statue=="0120004"){
								return '<b>'+ this.series.name +'</b><br/>'
								+"服务名："+this.x +',数值：'+ this.y+"%";
						   }else{
							   return '<b>'+ this.series.name +'</b><br/>'
								+"服务名："+this.x +',数值：'+ this.y;
						   }
						}
					},
					plotOptions: {//数据点选项
						 column: {
							pointPadding: 0.2,
							borderWidth: 0
						 }
					},
					series:[result]
		    });
  }
  function drawTraceStatChart(messageCode,messageType,serviceCode,traceStatDate,type,titleC,yScaleC) {
     title = titleC;
     yScale = yScaleC;
     $.post(contextPath+"/findChartData",{"messageCode":messageCode,"messageType":messageType,"serviceCode":serviceCode,"traceStatDate":traceStatDate,"type":type},function(result){
     result = JSON.parse(result);
     if(result.success=="success" ){
     chartData = result.data[0];
     layer_chain_index = layer.open({
        type: 2,
        content: 'tcecinStatQuery.jsp',
        area: ['770px', ' 470px'],
        title: '',
        closeBtn: 2,
        shade: [0.8, '#393D49']
     });
     }else {
     }
     });
  }