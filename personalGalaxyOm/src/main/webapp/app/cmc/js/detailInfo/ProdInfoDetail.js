$(document).ready(function() {

    var opt = getDataTableOpt($("#cmcProductInfo"));
    opt.stateSave=true;
    opt.processing=true;
    opt.scrollX=true;
    opt.deferRender=true;
    opt.ajax= {
        "url": contextPath+"/baseComm/getAll",
        "type": "POST",
        "data":{
            "pk_value":parent.$("#newCardProductCode").val(),
            "pk_name":"CARD_PRODUCT_CODE",
            "tableName":"CMC_PRODUCT_INFO"
        }
    };
    opt.fnRowCallback=function( nRow, aData) {
        var value = aData.OPERATE_TYPE;
        if ( value === "3" )
        {
            $('td', nRow).addClass('danger');
        }
        else if ( value === "2" )
        {
            $('td', nRow).addClass('highlight');
        }
        else if ( value === "1" )
        {
            $('td', nRow).addClass('newFace');
        }
    };
    opt.columnDefs=[
        {
            "targets": [ 0 ],
            "visible": false
        }
        ,{
            "width":"100px",
            "targets":1
        }
        ,{
            "width":"100px",
            "targets":2
        }
        ,{
            "width":"100px",
            "targets":3
        }
        ,{
            "width":"100px",
            "targets":4
        }
        ,{
            "width":"100px",
            "targets":5
        }
        ,{
            "width":"100px",
            "targets":6
        }
        ,{
            "width":"100px",
            "targets":7
        }
        ,{
            "width":"100px",
            "targets":8
        }
        ,{
            "width":"100px",
            "targets":9
        }
        ,{
            "width":"100px",
            "targets":10
        }
        ,{
            "width":"100px",
            "targets":11
        }
        ,{
            "width":"100px",
            "targets":12
        }
        ,{
            "width":"150px",
            "targets":13
        }
        ,{
            "width":"150px",
            "targets":14
        }
        ,{
            "width":"150px",
            "targets":15
        }
        ,{
            "width":"150px",
            "targets":16
        }
        ,{
            "width":"200px",
            "targets":17
        }
        ,{
            "width":"100px",
            "targets":18
        }
        ,{
            "width":"100px",
            "targets":19
        }
        ,{
            "width":"150px",
            "targets":20
        },
        {
            "width":"100px",
            "targets":21
        }
        /*,{
            "width":"100px",
            "targets":21
        }
        ,{
            "width":"100px",
            "targets":22
        }
        ,{
            "width":"100px",
            "targets":23
        }
        ,{
            "width":"100px",
            "targets":24
        }
        ,{
            "width":"100px",
            "targets":25
        }
        ,{
            "width":"100px",
            "targets":26
        }
        ,{
            "width":"100px",
            "targets":27
        }
        ,{
            "width":"100px",
            "targets":28
        }
        ,{
            "width":"100px",
            "targets":29
        }
        ,{
            "width":"100px",
            "targets":30
        }
        ,{
            "width":"100px",
            "targets":31
        }
        ,{
            "width":"100px",
            "targets":32
        }
        ,{
            "width":"100px",
            "targets":33
        }
        ,{
            "width":"100px",
            "targets":34
        }
        ,{
            "width":"100px",
            "targets":35
        }
        ,{
            "width":"100px",
            "targets":36
        }*/
    ];
    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "CARD_PRODUCT_CODE", "defaultContent":""}
        ,{ "data": "CARD_PRODUCT_NAME", "defaultContent":""}
        ,{ "data": "BIN_ORDER", "defaultContent":""}
        ,{ "data": "PUBLISH_CHANNEL", "defaultContent":""}
        , { "data": "CARD_TYPE_CODE", "defaultContent":""}
        , { "data": "CARD_TYPE_NAME", "defaultContent":""}
        , { "data": "CARD_PRODUCT_TYPE", "defaultContent":""}
        , { "data": "CARD_CRDBFLG", "defaultContent":""}
        , { "data": "CARD_NO_TYPE", "defaultContent":""}
        //,{ "data": "FEE_LEVEL", "defaultContent":""}
        //,{ "data": "CARD_LEVEL", "defaultContent":""}
        ,{ "data": "ENABLE_FLAG", "defaultContent":""}
        ,{ "data": "ENABLE_DATE", "defaultContent":""}
        //,{ "data": "ORDER_FLAG", "defaultContent":""}
        //,{ "data": "BEGIN_SEQ", "defaultContent":""}
        //,{ "data": "END_SEQ", "defaultContent":""}
        ,{ "data": "CARD_PHY_SORT", "defaultContent":""}
        ,{ "data": "ATM_ERR_NUM", "defaultContent":""}
        ,{ "data": "TOTAL_ERR_NUM", "defaultContent":""}
        ,{ "data": "CVN_TOT_ERR_NUM", "defaultContent":""}
        ,{ "data": "CVN2_TOT_ERR_NUM", "defaultContent":""}
        ,{ "data": "LAST_TOT_ERR_NUM", "defaultContent":""}
        ,{ "data": "CCY", "defaultContent":""}
        //,{ "data": "MARK_FLG", "defaultContent":""}
        //,{ "data": "ACTIVATE_FEE", "defaultContent":""}
        ,{ "data": "MAX_HOLD_NO", "defaultContent":""}
        //,{ "data": "PSWD_MARK", "defaultContent":""}
        //,{ "data": "LOST_MARK", "defaultContent":""}
        //,{ "data": "EX_DATE", "defaultContent":""}
        ,{ "data": "SET_FIX_EX_DATE", "defaultContent":""}
        ,{ "data": "FIX_EX_DATE", "defaultContent":""}];
        //,{ "data": "INVALID_DATE", "defaultContent":""}
        //,{ "data": "INTERFACE_PERMIT", "defaultContent":""}
        //,{ "data": "ISSUE_TARGET", "defaultContent":""}
        //,{ "data": "BEGIN_AMT", "defaultContent":""}
        //,{ "data": "CONVALUE", "defaultContent":""}];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcProductInfo"),opt);
    dataTableUi("cmcProductInfo", ["查看差异数据", "刷新","返回"], ["contrast", "refresh","return"]);

    $(".select2").select2();
    $('#cmcProductInfo tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcProductInfo').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcProductInfo').on('page.dt', function (e) {
        $('#cmcProductInfo').find("tr").removeClass('selected');
    });

    $("#contrast").on("click",function(){
        tran_mapping_contrast();
    });

    $("#return").on("click",function(){
        $("#refresh").show();
        initData_refresh();
    });

    $("#refresh").on("click",function(){
        selectAll_refresh();
    });

});

function tran_mapping_contrast() {
    var attrTab = $("#cmcProductInfo").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CMC_PRODUCT_INFO").load();
    $("#refresh").hide();
}

function initData_refresh() {
   selectAll_refresh();
}

function selectAll_refresh(){
    var attrTab = $("#cmcProductInfo").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/cardProductInfo/getAll?prodCode="+parent.$("#newCardProductCode").val()).load();
}


