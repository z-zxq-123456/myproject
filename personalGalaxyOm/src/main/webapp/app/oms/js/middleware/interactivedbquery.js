$(document).ready(function () {
   $('#queryDataMessage').hide();
//获取db集群
	getPkList({
		url:contextPath+"/findMidwareDefaultCombox",
		id:"queryMidwareId",
		async:false,
		normalSelect:false,
	    params: {
                 midwareType: '0006003'

              }
	});
    $(".select2").select2();
    $("#select").click(function () {
           exection();
    });
});

function exection(){
        var midwareId = $("#queryMidwareId").val();
        var sql = $("#querySql").val();
        if(sql.indexOf("update")> -1||sql.indexOf("delete")> -1||sql.indexOf("insert")> -1){
           showMsg('该操作无效，只能进行select操作','Error');
           return ;
        }else{
             var jsonObj = "&sql="+sql+"&midwareId="+midwareId;
             var sub_url = contextPath + '/queryDbInt';
             $.post(sub_url,jsonObj,function(result){
                $("#sqlTableDiv").empty();
                 var result = eval('('+result+')');
                    if (result.errorMsg){
                               showMsg(result.errorMsg, 'Error');
                    } else {
                        $('#queryDataMessage').show();
                        $("#queryDataMessage").html("&nbsp;&nbsp;&nbsp;&nbsp;总记录数:"+ result.data.totalNum +"&nbsp;&nbsp;&nbsp;消耗时间:"+result.data.consumerTime+"毫秒");
                        parseResult(result.data);
                    }
             });
        }
}
function parseResult(result){
       var colsName = result.colsName;
       var tempThs="";
       var colArray = new Array();
       for(var i in colsName){
           var temp = '<th>'+colsName[i]+'</th>';
           tempThs += temp;
           var thObj = {
                   "data": colsName[i],
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
     $("#sqlTableDiv").append(tempTable);
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
