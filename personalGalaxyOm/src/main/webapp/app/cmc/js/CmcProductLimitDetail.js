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
    var opt = getDataTableOpt($("#cmcProductLimit"));
    opt.stateSave=true;
    opt.processing=true;
    opt.deferRender=true;
    opt.scrollX=true;
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        opt.ajax = {
            "url": contextPath + "/baseComm/getAll",
            "type": "POST",
            "data": {
                "pk_value": $.session.get('cardProductCode'),
                "pk_name": "CARD_PRODUCT_CODE",
                "tableName": "CMC_PRODUCT_LIMIT"
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
    ];

    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "CARD_PRODUCT_CODE", "defaultContent":""}
        ,{ "data": "CHANNEL_TYPE", "defaultContent":""}
        ,{ "data": "CCY", "defaultContent":""}
        ,{ "data": "PERIOD", "defaultContent":""}
        ,{ "data": "CON_LIMIT_AMT", "defaultContent":""}
        ,{ "data": "TRAN_IN_LIMIT_AMT", "defaultContent":""}
        ,{ "data": "TRAN_OUT_LIMIT_AMT", "defaultContent":""}
        ,{ "data": "CON_LIMIT_TIME", "defaultContent":""}
        ,{ "data": "TRAN_IN_LIMIT_TIME", "defaultContent":""}
        ,{ "data": "TRAN_OUT_LIMIT_TIME", "defaultContent":""}
    ];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcProductLimit"),opt);
    $("#cmcProductLimit").beautyUi({
        tableId:"cmcProductLimit",
        buttonName:["添加", "修改","删除","刷新"],
        buttonId:["add",　"edit","delete" ,"refresh"]
    });
    $('#cmcProductLimit tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcProductLimit').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcProductLimit').on('page.dt', function (e) {
        $('#cmcProductLimit').find("tr").removeClass('selected');
    });
    $(".select2").select2();
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    buttonStatus();
});

function selectAll_refresh(){
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        var prodTab = $("#cmcProductLimit").dataTable();
        var api = prodTab.api();
        api.ajax.reload();
    }
}

/*增加*/
function cmc_product_limit_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_product_limit_mod(title,url,w,h){
    if ($("#cmcProductLimit").find(".selected").length!==1){
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
function  cmc_product_limit_del(){
    if ($("#cmcProductLimit").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcProductLimitDel();
    });
}

function  cmcProductLimitDel(){
    var rowData = $('#cmcProductLimit').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        cardProductCode:rowData.CARD_PRODUCT_CODE,
        channelType:rowData.CHANNEL_TYPE,
        ccy:rowData.CCY,
        period:rowData.PERIOD,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardProductLimit/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcProductLimitDel,"json");
}

function  callback_cmcProductLimitDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcProductLimit').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
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
            cmc_product_limit_contrast();
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
            cmc_product_limit_add('添加','add/cmcProductLimitAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_product_limit_mod('修改','edit/cmcProductLimitMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cmc_product_limit_del();
            });
        }
        $("#submit").on("click",function(){
            cmc_product_limit_submit();
        });
        $("#contrast").on("click",function(){
            cmc_product_limit_contrast();
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
            cmc_product_limit_contrast();
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
            cmc_product_limit_add('添加','add/cmcProductLimitAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_product_limit_mod('修改','edit/cmcProductLimitMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            cmc_product_limit_del();
        });
    }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}