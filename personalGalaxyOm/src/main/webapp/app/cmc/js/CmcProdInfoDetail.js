var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    if(msg==="添加成功") {
        layer.close(layer_add_index);
    }
    if(msg==="修改成功") {
        layer.close(layer_edit_index);
    }
}
function isNullProductInfo() {
    if ($('#cmcProductInfo').DataTable().tables().context[0].aoData.length>0){
        parent.$("#one").show();
    }else {
        parent.$("#one").hide();
    }
}
$(document).ready(function() {
    $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#cmcProductInfo"));
    opt.stateSave=true;
    opt.processing=true;
    opt.scrollX=true;
    opt.deferRender=true;
    $.session.remove("cardNoType");
    $.session.remove("cardProductCode");
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add"){
        opt.ajax= {
            "url": contextPath+"/baseComm/getAll",
            "type": "POST",
            "data":{
                "pk_value":parent.$("#newCardProductCode").val(),
                "pk_name":"CARD_PRODUCT_CODE",
                "tableName":"CMC_PRODUCT_INFO"
            }
        };
    }

    opt.fnRowCallback=function( nRow, aData) {
        $.session.remove("cardNoType");
        $.session.remove("cardProductCode");
        var CARD_NO_TYPE = aData.CARD_NO_TYPE;
        if (CARD_NO_TYPE!=null){
            $.session.set("cardNoType",CARD_NO_TYPE);
        }
        var CARD_PRODUCT_CODE = aData.CARD_PRODUCT_CODE;
        if (CARD_PRODUCT_CODE != null){
            $.session.set("cardProductCode",CARD_PRODUCT_CODE);
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
    $(".select2").select2();
    $("#cmcProductInfo").beautyUi({
        tableId:"cmcProductInfo",
        buttonName:["添加", "修改","删除","刷新"],
        buttonId:["add","edit","delete","refresh"]
    });

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
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    buttonStatus();
});

function selectAll_refresh(){
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        var prodTab = $("#cmcProductInfo").dataTable();
        var api = prodTab.api();
        api.ajax.reload();
    }
}

/*增加*/
function cmc_product_info_add(title,url,w,h){
    if ($('#cmcProductInfo').DataTable().tables().context[0].aoData.length>0){
        showMsg("只允许定义唯一一行记录！","warning");
        return;
    }
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_product_info_mod(title,url,w,h){
    if ($("#cmcProductInfo").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*删除*/
function  cmc_product_info_del(){
    if ($("#cmcProductInfo").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcProductInfoDel();
    });
}

function  cmcProductInfoDel(){
    var rowData = $('#cmcProductInfo').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        cardProductCode:rowData.CARD_PRODUCT_CODE,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardProductInfo/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcProductInfoDel,"json");
}

function  callback_cmcProductInfoDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcProductInfo').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
        if (parent.$("#operateType")==="add"){
            isNullProductInfo();
        }
    }
}

function buttonStatus()
{
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            cmc_product_info_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#queryPrimaryKey").show();
            $("#add").hide();
            $("#edit").hide();
            $("#delete").hide();
            $("#submit").hide();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回"){
        $("#add").on("click",function(){
            cmc_product_info_add('添加','add/cmcProductInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_product_info_mod('修改','edit/cmcProductInfoMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cmc_product_info_del();
            });
        }
        $("#submit").on("click",function(){
            cmc_product_info_submit();
        });
        $("#contrast").on("click",function(){
            cmc_product_info_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已录入" ) {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            cmc_product_info_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "NoEdit" ){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").hide();
    }
    else
    {
        $(".breadcrumb").data("needButton","windowShow");
        $("#add").on("click",function(){
            cmc_product_info_add('添加','add/cmcProductInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_product_info_mod('修改','edit/cmcProductInfoMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            cmc_product_info_del();
        });
        $("#contrast").on("click",function(){
            cmc_product_info_contrast();
        });
        $("#submit").on("click",function(){
            cmc_product_info_submit();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}