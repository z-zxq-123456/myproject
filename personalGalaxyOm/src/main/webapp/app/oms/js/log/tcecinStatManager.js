var chartData;
var layer_chain_index;
var title;
$(document).ready(function () {
    var opt = getDataTableOpt($("#deployViewList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findTcecinStat",
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
  //  drawDataTable($("#deployViewList"), opt);
    drawDataTableServerSide($("#deployViewList"), opt,10);
    //界面美化tables
    $("#deployViewList").beautyUi({
        tableId: "deployViewList",
        needBtn: false,
        serverSide : true
    });


    $("#select").click(function(){
            var startDate = $("#startTime").val();
            var endDate  = $("#endTime").val();
            var messageCode = $("#messageCode").val();
            var messageType  = $("#messageType").val();
            var serviceCode  = $("#serviceCode").val();
            var userTab = $("#deployViewList").dataTable();
            var api = userTab.api();
            if(endDate!=""&&startDate==""){
            showMsg("请输入开始时间","warn");
            }else{
                if(startDate!=""&&endDate=="" ){
                    showMsg("请输入结束时间","warn");
                }else{
                    api.ajax.url(contextPath+"/findTcecinStat?startTime="+startDate+"&endTime="+endDate+"&messageCode="+messageCode+"&messageType="+messageType+"&serviceCode="+serviceCode).load();
                }
            }
    });
});

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

