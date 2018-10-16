var layer_chain_index;
var statusMark="0015002";//失败参数
var colorFail="#FF8888";//失败背景色
var opt;
var cirDatil;
function format ( d ) {
    // `d` is the original data object for the row
    var tempRows ="";
    var circleList;
    if(d.traceId){
    $.ajax({
           url : contextPath + "/findCircleInfoByTraceId?traceId="+d.traceId,
           async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected
           type : "POST",
           success : function(result) {
               var dataJson = JSON.parse(result);
               circleList = dataJson.data;
           }
    });
         tempRows += formatChildData(circleList);
    }

    return '<table id="childTable_data" class="childTable"  cellspacing="0" border="0"  style="background-color: #FDF7E6; padding-left:-2px; border-left: 1px solid #e6e6e6;border-collapse:separate;margin-bottom:-2px;margin-left:-2px;">'+
                                '<thead>'+
           '<tr>'+
           '<th>服务方法</th>'+
           '<th>客户端IP端口</th>'+
           '<th>服务端IP端口</th>'+
           '<th>执行时长</th>'+
           '<th>网络消耗</th>'+
           '<th>输入报文</th>'+
           '<th>输出报文</th>'+
           '<th>调用环详情</th>'+
           '<th>查看业务日志</th>'+
           '</tr>'+
           '</thead>'+tempRows+'</table>';
}
function formatChildData(children){
        var tempRows="";
        for(var i=0;i<children.length;i++){
            var child = children[i];
            var moreInfo= "调用方式:"+child.cirTypeName+"||服务端服务名:"+child.cirServerSer+"||客户端开始时间:"+child.cirClientSttm+"||客户端IP端口:"+child.cirClientIpport+"||客户端线程号:"+child.cirClientThdnum;
            if(child.cirType=="0070001") {
                      moreInfo=moreInfo+"||服务端开始时间:"+child.cirServerSttm+"||服务端线程号:"+child.cirServerThdnum+"||服务端调用状态:"+child.cirServerStatusName;
            }
            if(child.cirClientStatus==statusMark){
            		  moreInfo=moreInfo+"||客户端失败消息:"+child.cirServerFailinfo;
            		  cirDatil = moreInfo;
            		   var temp = '<tr style="background-color:red">'+
                       '<th>'+child.cirServerSerAndMtd+'</th>'+
                       '<th>'+child.cirClientIpport+'</th>'+
                       '<th>'+child.cirServerIpport+'</th>'+
                       '<th>'+child.cirExeTime+'</th>'+
                       '<th>'+child.circonsupTime+'</th>'+
                       "<th><a type='text' onclick=msg_look("+"\'"+child.cirInMsg+"\'"+") >点击</a></th>"+
                       "<th><a type='text' onclick=msg_look("+"\'"+child.cirOutMsg+"\'"+") >点击</a></th>"+
                       "<th><a type='text' onclick=msg_cirDatil() >点击</a></th>"+
                       "<td style='text-decoration: none;text-align:center'><img src="+contextPath+"/images/dataTable/icon-search.png onclick=product_brand_edit("+'\"'+child.cirId+'\"'+") title='查看'></td>" +
                       '</tr>';
            }else{
                 var temp = '<tr>'+
                 '<th>'+child.cirServerSerAndMtd+'</th>'+
                 '<th>'+child.cirClientIpport+'</th>'+
                 '<th>'+child.cirServerIpport+'</th>'+
                 '<th>'+child.cirExeTime+'</th>'+
                 '<th>'+child.circonsupTime+'</th>'+
                 "<th><a type='text' onclick=msg_look("+"\'"+child.cirInMsg+"\'"+") >点击</a></th>"+
                 "<th><a type='text' onclick=msg_look("+"\'"+child.cirOutMsg+"\'"+") >点击</a></th>"+
                 "<th><a type='text' title="+"\'"+moreInfo+"\'"+">详情</a></th>"+
                 "<td style='text-decoration: none;text-align:center'><img src="+contextPath+"/images/dataTable/icon-search.png onclick=product_brand_edit("+'\"'+child.cirId+'\"'+") title='查看'></td>" +
                 '</tr>';
            }
            tempRows += temp;
        }
        return tempRows;
}
$(document).ready(function () {
    var startDate = $("#startTime").text();
    var endDate  = $("#endTime").text();
    var opt = getDataTableServerSideOpt ($("#table_data"));
        opt.ajax = {
            "url": contextPath + "/findTcechainTraceInfo?startTime="+startDate+"&endTime="+endDate,
            "type": "POST",
            "async": false
        };

   opt.rowCallback = function(row,data,index){
        if(data.traceStatus== statusMark) {
            row.style.backgroundColor=colorFail;
        }
   };
   opt.columns = [
        {
            "class":          'details-control',
            "orderable":      false,
            "data":           null,
            "defaultContent": ''
        },
        { "orderable":      false,"data": "uuid"},
        { "orderable":      false,"data": "traceStatusName" },
        { "orderable":      false,"data": "traceSttime" },
        { "orderable":      false,"data": "exeTime" },
        { "orderable":      false,"data": "allInfo" },
        {
                   "class":          'details-control2',
                   "orderable":      false,
                   "data":           null,
                   "defaultContent": ''
        },
        {
            "class":          'details-control1',
            "orderable":      false,
            "data":           null,
            "defaultContent": ''
        }
   ];


    //渲染tables
    drawDataTableWithChilds($("#table_data"));
    drawDataTableServerSide($("#table_data"),opt,10);
    var table = $('#table_data').DataTable();
        //界面美化tables
    $("#table_data").beautyUi({
        tableId: "table_data",
        needBtn: false,
        serverSide : true
    });
     $('#table_data tbody').on('click', 'td.details-control2', function () {
       var tr = $(this).closest('tr');
       var row = table.row( tr );
       var traceId = row.data().traceId;
       test(traceId);
     });
    $('#table_data tbody').on('click', 'td.details-control1', function () {
       var tr = $(this).closest('tr');
              var row = table.row( tr );
              var traceId = row.data().traceId;
        layer_chain_index = layer.open({
                                          type: 2,
                                          content: 'logDetailInquire.jsp?traceId='+traceId,
                                          area: ['100%', '100%'],
                                          title: '交易日志查看',
                                          closeBtn: 1,
                                          shade: [0.8, '#393D49']
           });

    });
    $('#table_data tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        if ( row.child.isShown() ) {
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            row.child( format(row.data()) );
            var childTable = row.child().find("table");
            //childTable.css({"border-collapse":"separate","margin-bottom":"-2px","margin-left":"-2px"});
            /*row.child().find("table,tr,td,th").css({ "background-color": "#FDF7E6" });*/
            row.child.show();
            tr.addClass('shown');
        }
    } );

    $(".select2").select2();
    $("#selectDate").click(function(){
         findTcechainTraceInfo();
    });

});
 function  findTcechainTraceInfo(){
             var branchId = $("#branchId").val();
             var tellerId  = $("#tellerId").val();
             var startDate = $("#startTime").val();
             var endDate  = $("#endTime").val();
             var userTab = $("#table_data").dataTable();
             var api = userTab.api();
             if((startDate!=""&&endDate!="")||(startDate==""&&endDate=="")){
             api.ajax.url(contextPath+"/findTcechainTraceInfo?startTime="+startDate+"&endTime="+endDate+"&branchId="+branchId+"&tellerId="+tellerId).load();
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
 }
 function test(traceId) {
 layer_chain_index = layer.open({
                            type: 2,
                            content: 'chainDetailsInquire.jsp?traceId='+traceId,
                            area: ['100%', '100%'],
                            title: '调用环视图',
                            closeBtn: 1,
                            shade: [0.8, '#393D49']
                        });
 }
function product_brand_edit(cirId) {
    layer_chain_index = layer.open({
                                   type: 2,
                                   content: 'annotDetailInquire.jsp?cirId='+cirId,
                                   area: ['100%', '100%'],
                                   title: '业务日志查看',
                                   closeBtn: 1,
                                   shade: [0.8, '#393D49']
    });
}
  function msg_look(msg) {
          alert(msg);
  }
function msg_cirDatil() {
          showMsg(cirDatil,"info");
  }


