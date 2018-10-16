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

$(document).ready(function() {
    $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#cmcProductChannel"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth=false;
    opt.deferRender=true;
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        opt.ajax= {
            "url": contextPath + "/baseComm/getAll",
            "type": "POST",
            "data": {
                "pk_value": $.session.get('cardProductCode'),
                "pk_name": "CARD_PRODUCT_CODE",
                "tableName": "CMC_CARD_PRODUCT_CHANNEL"
            }
        };
    }
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
    ];

    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "CARD_PRODUCT_CODE", "defaultContent":""}
        ,{ "data": "LIMIT_CHANNEL", "defaultContent":""}
        ,{ "data": "AUTH_TRAN_TYPE", "defaultContent":""}
    ];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcProductChannel"),opt);
    $("#cmcProductChannel").beautyUi({
        tableId:"cmcProductChannel",
        buttonName:["添加","删除","刷新"],
        buttonId:["add","delete","refresh" ]
    });
    $('#cmcProductChannel tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcProductChannel').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcProductChannel').on('page.dt', function (e) {
        $('#cmcProductChannel').find("tr").removeClass('selected');
    });
    $(".select2").select2();
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    buttonStatus();
});

function selectAll_refresh(){
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        var prodTab = $("#cmcProductChannel").dataTable();
        var api = prodTab.api();
        api.ajax.reload();
    }
}

/*增加*/
function cmc_product_channel_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_product_channel_mod(title,url,w,h){
    if ($("#cmcProductChannel").find(".selected").length!==1){
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
function cmc_product_channel_del(){
    if ($("#cmcProductChannel").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcProductChannelDel();
    });
}

function  cmcProductChannelDel(){
    var rowData = $('#cmcProductChannel').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        cardProductCode:rowData.CARD_PRODUCT_CODE,
        limitChannel:rowData.LIMIT_CHANNEL,
        authTranType:rowData.AUTH_TRAN_TYPE,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardProductChannel/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcProductChannelDel,"json");
}

function  callback_cmcProductChannelDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcProductChannel').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
    }
}

function buttonStatus() {
    $("#return").hide();
    if (parent.$(".breadcrumb").data("needButton") === "N") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else if (parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回") {
        $("#add").on("click", function () {
            cmc_product_channel_add('添加', 'add/cmcProductChannelAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            cmc_product_channel_mod('修改', 'edit/cmcProductChannelMod.jsp', '600', '520');
        });
        if (parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else {
            $("#delete").on("click", function () {
                cmc_product_channel_del();
            });
        }
    }
    else if (parent.$(".breadcrumb").data("needButton") === "已录入") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();

    }
    else if (parent.$(".breadcrumb").data("needButton") === "NoEdit") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else {
        $(".breadcrumb").data("needButton", "windowShow");
        $("#add").on("click", function () {
            cmc_product_channel_add('添加', 'add/cmcProductChannelAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            cmc_product_channel_mod('修改', 'edit/cmcProductChannelMod.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            cmc_product_channel_del();
        });
    }
    if (parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}
