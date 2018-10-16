$(document).ready(function() {
    var opt = getDataTableOpt($("#cmcTranInfo"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#cmcTranInfo"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth = false;
    opt.deferRender=true;
    opt.columnDefs=[
        {
            "targets": [ 0 ],
            "visible": false
        }
    ];
    opt.columns=[
        {"data":  "cardNo", "defaultContent": ""}
        ,{ "data": "baseAcctNo", "defaultContent":""}
        ,{ "data": "customerId", "defaultContent":""}
        ,{ "data": "othCardNo", "defaultContent":""}
        ,{ "data": "othClientNo", "defaultContent":""}
        ,{ "data": "channelSeqNo", "defaultContent":""}
        ,{ "data": "channelSubSeqNo", "defaultContent":""}
        ,{ "data": "tranDate", "defaultContent":""}
        ,{ "data": "settlementDate", "defaultContent":""}
        ,{ "data": "channelType", "defaultContent":""}
        ,{ "data": "tranStatus", "defaultContent":""}
        ,{ "data": "revFlag", "defaultContent":""}
        ,{ "data": "cancelFlag", "defaultContent":""}
        ,{ "data": "ccy", "defaultContent":""}
        ,{ "data": "systemId", "defaultContent":""}
        ,{ "data": "tranType", "defaultContent":""}
        ,{ "data": "cdFlag", "defaultContent":""}
        ,{ "data": "tranAmt", "defaultContent":""}
        ,{ "data": "origSeqNo", "defaultContent":""}
        ,{ "data": "origSubSeqNo", "defaultContent":""}
        ,{ "data": "settleFlag", "defaultContent":""}
    ];
    //渲染tables
    opt.order = [[1, 'asc']];
    setDataTableOpt($("#cmcTranInfo"),opt);

    $("#cmcTranInfo").beautyUi({
        tableId: "cmcTranInfo",
        buttonName: ["刷新", "返回"],
        buttonId: ["refresh", "return"]
    });

    $('#cmcTranInfo tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcTranInfo').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
        furtherInfoShow("参数详细信息");
    });
    $('#cmcTranInfo').on('page.dt', function (e) {
        $('#cmcTranInfo').find("tr").removeClass('selected');
    });
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });

    $("#selectByCardNo").click(function(){
        if(1===1 && $("#cardNo").val()==="")
        {
            alert("请输入有效的卡号！");
        }else
        {
            select_card_tran_hist();
        }
    });
    $(".select2").select2();
    buttonStatus();
});

/*查询卡交易流水*/
function select_card_tran_hist() {
    var attrTab = $("#cmcTranInfo").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+ "/cardTranHist/findHist?cardNo="+$("#cardNo").val()+"&beginDate="+$("#beginDate").val()+"&endDate="+$("#endDate").val()).load();
}



function buttonStatus()
{
    $("#look").on("click",function () {
        card_tran_info('查看','/app/cmc/jsp/CardTranInfoDetail.jsp?cardNo='+$("#cardNo").val(),'1000','600');
    });

    $("#return").on("click",function () {
        select_card_tran_hist();
    });
}

var layer_look_index;
/*查看交易流水明细*/
function card_tran_info(title,url,w,h) {
    layer_look_index = layer.open({
        type: 2,
        content: contextPath+url,
        title:title,
        area:[w+'px', h+'px']
    });
}


function selectAll_refresh(){
    var prodTab = $("#cmcTranInfo").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}


var info_index;
function furtherInfoShow(title) {
    var trValue=$('#cmcTranInfo tr:eq(1) td:eq(0)').html();
    if( trValue == "No data available in table"){
        $('#cmcTranInfo').find('tr').eq(1).removeClass('selected');
        showMsg("无信息！");
        return;
    }
    info_index=layer.open({
        type: 2,
        content: "CardTranInfoDetail.jsp",
        title:title,
        end: function(){
            selectInfo();
        }
    });
    layer.full(info_index);
}
