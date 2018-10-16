$(document).ready(function () {
    var opt = getDataTableOpt($("#deployViewList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findTcecirStat",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "cirStatDate",
            "defaultContent": ""
        },
        {
            "data": "cirServerSer",
            "defaultContent": ""
        },
        {
            "data": "cirServerMtd",
            "defaultContent": ""
        },
        {
            "data": "cirStatAmount",
            "defaultContent": ""
        },
        {
            "data": "cirStatOknum",
            "defaultContent": ""
        },
        {
            "data": "cirStatOkperc",
            "defaultContent": ""
        },
        {
            "data": "cirStatAvertime",
            "defaultContent": ""
        },
        {
            "data": "cirStatAverexutime",
            "defaultContent": ""
        },
        {
            "data": "cirStatPeaknum",
            "defaultContent": ""
        }
    ];
    //渲染tables
    //drawDataTable($("#deployViewList"), opt);
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
            var cirServerSer = $("#cirServerSer").val();
            var cirServerMtd  = $("#cirServerMtd").val();
            var userTab = $("#deployViewList").dataTable();
            var api = userTab.api();
            if(startDate!=""&&endDate!=""){
               api.ajax.url(contextPath+"/findTcecirStat?startTime="+startDate+"&endTime="+endDate+"&cirServerSer="+cirServerSer+"&cirServerMtd="+cirServerMtd).load();
            }else{
               if(startDate!=""){
                  alert('请输入结束日期！');
               }else{
                  if(endDate!=""){
                    alert('请输入开始日期！');
                  }else{
                    alert('开始日期，和结束日期必输');
                  }
               }
            }
    });
});
function drawCirStatChart(cirServerSer,cirServerMtd,cirStatDate,type,titleC,yScaleC) {
   title = titleC;
   yScale = yScaleC;
   $.post(contextPath+"/findCirChartData",{"cirServerSer":cirServerSer,"cirServerMtd":cirServerMtd,"cirStatDate":cirStatDate,"type":type},function(result){
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