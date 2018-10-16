
  var isFirst = true;
	$(document).ready(function () {
	 //获取周期值
         getPkList({
               url: contextPath + "/findParaCombox?paraParentId=0091&&isDefault=true",
               id: "qeryAppMoniPeriod",
               async: false,
               normalSelect: false
           });
		 $(".select2").select2();
		 $("#select").click(function () {
			   searchRec();
		 });
	 });
  function searchRec(){
      var appMoniPeriod = $("#qeryAppMoniPeriod").val();
	  if(appMoniPeriod=="请选择"){
				appMoniPeriod="";
	   }else{
		  if( appMoniPeriod){
			 var sub_url = contextPath + '/findAppIndexMonitor';
			 $.post(sub_url,{appMoniPeriod:appMoniPeriod},function(result){
			  $("#indexQueryTableDiv").empty();
				 var result = eval('('+result+')');
				 if (result.errorMsg){
							showMsg(result.errorMsg, 'Error');
				 } else {
					 parseResult(result.data);
					 drawView(result.data);
				 }
			  });
		  }
	 }
  }
   
//  function parseResult(result){
//	  var colsName = result.colsName;
//      var colArray = new Array();
//      for(var i in colsName){
//		  var colName = {};
//		  colName['field']=colsName[i].code;
//		  colName['title']=colsName[i].name;
//		  colName['width']=colsName[i].length;
//		  colArray.push(colName);
//	  }
//
//	  $('#table_data').datagrid({
//	   //columns:eval(dgColStr),
//	   columns:[colArray],
//	   onLoadSuccess:errorInfo
//	}).datagrid("loadData",result);
//
//  }


  function parseResult(result){
           var colsName = result.colsName;
           var tempThs="";
           var colArray = new Array();
           for(var i in colsName){
               var temp = '<th>'+colsName[i].name+'</th>';
               tempThs += temp;
               var thObj = {
                       "data": colsName[i].code,
                       "defaultContent": ""
               };
               colArray.push(thObj);
           }
          var tempTable =
                        '<table id="sqlTableList" class="table table-border table-bordered table-hover table-bg table-sort">'+
                        '<thead>'+
                        '<tr>'+tempThs+
                        '</tr>'+
                        '</thead></table>';
         $("#indexQueryTableDiv").append(tempTable);
         var opt = getDataTableOpt($("#sqlTableList"));
         opt.stateSave = true;
         opt.processing = true;
         opt.autoWidth = false;
         //设置列
         opt.columns = colArray;
         //设置data
         opt.data = result.rows;
         //渲染tables
         drawDataTable($("#sqlTableList"), opt);
         $("#sqlTableList").beautyUi({
             tableId: "sqlTableList",
             needBtn: false
         });
    }

  function drawView(result){
	  clearChartData();
	  var colsName = result.colsName; 
	  var rowsData = result.rows;
	  var xData = new Array();
      var yDataArray = new Array();      
      for(var i =0 ;i< colsName.length-3;i++){
    	  yDataArray[i]={};    	  
    	  yDataArray[i]['name']=colsName[i+3].name;
    	  yDataArray[i]['data']=new Array();   
      }

      for(var i =rowsData.length-1 ;i>=0;i--){
          xData.push(rowsData[i][colsName[2].code]);
          for(var a =0 ;a< colsName.length-3;a++){
               yDataArray[a]['data'].push(Number(rowsData[i][colsName[a+3].code]));
          }

      }
      for(var b in yDataArray){		
    	  //alert("b="+b+" data="+yDataArray[b]['data']+" len="+yDataArray[b]['data'].length);
		  options.series.push(yDataArray[b]);
	  }
      //alert(xData);
       options.xAxis.categories=xData;
        if(chart){
          chart.redraw();
        }else{
          chart = new Highcharts.Chart(options);
        }
  }	
  
  function clearChartData(){
	  if(chart){
		  var series = options.series;
		  for(var b in series){
			  series[b].remove(false);
		  }
	  }

  }
  



