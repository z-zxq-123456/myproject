  $(document).ready(function () {
  var opt = getDataTableOpt($("#table_data"));
      opt.stateSave = true;
      opt.processing = true;
      opt.autoWidth = false;
       opt.ajax = {
              "url": contextPath + "/findFwTranInfo",
              "type": "POST"
          };
       opt.columns = [
              {
                  "data": "serviceId",
                  "defaultContent": ""
              },
              {
                  "data": "keyValue",
                  "defaultContent": ""
              },
              {
                  "data": "tranDate",
                  "defaultContent": ""
              },
              {
                  "data": "tranTime",
                  "defaultContent": ""
              },
              {
                  "data": "endTime",
                  "defaultContent": ""
              },
              {
                "data": "sourceType",
                "defaultContent": ""
             },
             {
                  "data": "seqNo",
                  "defaultContent": ""
            },
            {
                "data": "status",
                "defaultContent": ""
            },
            {
              "data": "reference",
              "defaultContent": ""
             },
             {
                 "data": "demoInMsg",
                 "defaultContent": ""
             },
             {
               "data": "demoOutMsg",
               "defaultContent": ""
              }
          ];
          //渲染tables
          drawDataTable($("#table_data"), opt);
          //界面美化tables
          $("#table_data").beautyUi({
              tableId: "table_data",
              needBtn: false
          });
           $('#table_data tbody').on('click', 'tr', function (e) {
                  if ($(this).hasClass('selected')) {
                      $(this).removeClass('selected');
                  } else {
                      $('#table_data').find("tr").removeClass('selected');
                      $(this).addClass('selected');
                  }
              });
              $('#table_data').on('page.dt', function (e) {
                  $('#table_data').find("tr").removeClass('selected');
              });

              $(".select2").select2();
                 $("#select").click(function () {
                     searchRec();
               });
               $(function(){
               	startTimeOut();
               });

  });

 function searchRec(){
     var startDate = $("#startTime").val();
     var endDate = $("#endTime").val();
     var refreshTime = $("#refreshTime").val();
     var userTab = $("#table_data").dataTable();
     var api = userTab.api();
     if($('#startTime').val()&&$('#endTime').val()){
	    if($('#startTime').val()>$('#endTime').val()){
	    	alert("温馨提示,开始时间不能大于结束时间");
	    }else {
	    	 api.ajax.url(contextPath + "/findFwTranInfo?startTime=" + startDate + "&endTime=" + endDate + "&refreshTime=" + refreshTime).load();
	    }
     }else if($('#startTime').val()){
    	 alert("温馨提示,请输入结束时间");
     }else if($('#endTime').val()){
    		 alert("温馨提示,请输入开始时间");
     }else {
    		  api.ajax.url(contextPath + "/findFwTranInfo?refreshTime=" + refreshTime).load();
     }
}
function colOverInMsg(index){
	$('#msgDetailDiv').open();
	$('#table_data').datagrid('selectRow',index);
	$("#msgDetailText").html("");
	$("#msgDetailText").html($('#table_data').DataTable().rows(".selected").data().inMsg);

}

function colOutInMsg(index){
	//$('#table_data').datagrid('clearSelections');
	 var dataTable = $("#table_data").DataTable();
	 dataTable.row(".selected").remove().draw(false);
	$('#msgDetailDiv').close();
    $("#msgDetailText").html("");
}

function colOverOutMsg(index){
	$('#msgDetailDiv').open();
	$("#msgDetailText").html("");
	alert($('#table_data').DataTable().rows(".selected").data().outMsg);
	$("#msgDetailText").html($('#table_data').DataTable().rows(".selected").data().outMsg);
}

function colOutOutMsg(index){
//	$('#table_data').datagrid('clearSelections');
    var dataTable = $("#table_data").DataTable();
	dataTable.row(".selected").remove().draw(false);
	$('#msgDetailDiv').close();
    $("#msgDetailText").html("");
}

function startTimeOut(){
     var startDate = $("#startTime").val();
     var endDate = $("#endTime").val();
     var refreshTime = $("#refreshTime").val();
     var userTab = $("#table_data").dataTable();
     var api = userTab.api();
	 api.ajax.url(contextPath + "/findFwTranInfo?refreshTime=" + refreshTime).load();
	var delayTime = $('#refreshTime').val();
    //alert(delayTime);
	setTimeout('startTimeOut()',delayTime*1000);
}