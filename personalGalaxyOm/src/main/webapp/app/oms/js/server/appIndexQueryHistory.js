  $(document).ready(function () {
  //获取维度类型
  	 getPkList({
            url: contextPath + "/findParaCombox",
            id: "qeryAppIndexrecDime",
            async: false,
            params: {
                paraParentId: '0090',
                isDefault: false
            }
        });
      $(".select2").select2();
      $("#select").click(function () {
      $("#select").click(function () {
            searchRec();
      });
  });

  function parseResult(result){
         var colsName = result.colsName;
         var tempThs="";
         var colArray = new Array();
         for(var i in colsName){
             var temp = '<th>'+colsName[i].name+'</th>';
             tempThs += temp;
             var thObj = {
                     "data": colsName[i],
                     "defaultContent": ""
             };
             colArray.push(thObj);
         }
        var tempTable =
                      '<table id="indexTableList" class="table table-border table-bordered table-hover table-bg table-sort">'+
                      '<thead>'+
                      '<tr>'+tempThs+
                      '</tr>'+
                      '</thead></table>';
       $("#indexTableDiv").append(tempTable);
       var opt = getDataTableOpt($("#sqlTableList"));
       opt.stateSave = true;
       opt.processing = true;
       opt.autoWidth = false;
       //设置列
       opt.columns = colArray;
       //设置data
       opt.data = result.resultList;
       //渲染tables
       drawDataTable($("#sqlTableList"), opt);
       $("#sqlTableList").beautyUi({
           tableId: "sqlTableList",
           needBtn: false
       });
  }


          
  function searchRec(){
      var startTime = $("#startTime").val();
      var endTime = $("#endTime").val();
      var appIndexrecDime = $("#qeryAppIndexrecDime").val();
	 if(startTime!=""&&endTime!=""){
		ajax(startTime,endTime,appIndexrecDime);
	 }else{
		if(startTime!=""){
		   alert('请输入结束日期！');
		}else{
		   if(endTime!=""){
			 alert('请输入开始日期！');
		   }else{
				ajax(startTime,endTime,appIndexrecDime);
		   }
		}
	 }
  }
function 	ajax(startTime,endTime,appIndexrecDime){
            var sub_url = contextPath + '/findAppIndexQuery';
            $.post(sub_url,{startTime:startTime,endTime:endTime,appIndexrecDime:appIndexrecDime},function(result){
		     var result = eval('('+result+')');
             if (result.errorMsg){
                       $.messager.show({
                           title: 'Error',
                           msg: result.errorMsg
                       });
             } else {
            	 parseResult(result);
             }
		  });
}
//  function parseResult(result){
//	  var colsName = result.colsName;
//      var colArray = new Array();
//      for(var i in colsName){
//		  var colName = {};
//		  colName['field']=colsName[i].code;
//		  colName['title']=colsName[i].name;
//		  colName['width']=colsName[i].length-20;
//		  colArray.push(colName);
//	  }
//	  $('#table_data').datagrid({
//	   url: contextPath + '/findAppIndexQuery',
//	   columns:[colArray],
//	   onLoadSuccess:errorInfo
//	}).datagrid("loadData",result);
//  }
  
 
    
      
