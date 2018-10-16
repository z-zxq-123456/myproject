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
    	var opt = getDataTableOpt($("#cmcCardNoParam"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
        opt.ajax= {
            "url": contextPath+"/baseComm/getAll",
            "type": "POST",
            "data":{
                "pk_value":$.session.get('cardNoType'),
                "pk_name":"PRODUCT_RULE_NO",
                "tableName":"CMC_CARD_NO_PARAM"
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
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "PRODUCT_RULE_NO", "defaultContent":""}
                ,{ "data": "CARD_NUM", "defaultContent":""}
                ,{ "data": "FLAG", "defaultContent":""}
                ,{ "data": "THRESHOLD_NUM", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#cmcCardNoParam"),opt);
        $("#cmcCardNoParam").beautyUi({
            tableId:"cmcCardNoParam",
            buttonName:["添加", "修改","删除" ],
            buttonId:["add",　"edit","delete"]
            });
        $('#cmcCardNoParam tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cmcCardNoParam').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#cmcCardNoParam').on('page.dt', function (e) {
                $('#cmcCardNoParam').find("tr").removeClass('selected');
        });
        $(".select2").select2();
        buttonStatus();
});

/*增加*/
function cmc_card_no_param_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_card_no_param_mod(title,url,w,h){
    if ($("#cmcCardNoParam").find(".selected").length!==1){
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
function  cmc_card_no_param_del(){
    if ($("#cmcCardNoParam").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcCardNoParamDel();
    });
}

function  cmcCardNoParamDel(){
    var rowData = $('#cmcCardNoParam').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        productRuleNo:rowData.PRODUCT_RULE_NO,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardNoParam/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcCardNoParamDel,"json");
}

function  callback_cmcCardNoParamDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcCardNoParam').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
    }
}

function buttonStatus() {

    if (parent.$(".breadcrumb").data("needButton") === "N") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else if (parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回") {
        $("#add").on("click", function () {
            cmc_card_no_param_add('添加', 'add/cmcCardNoParamAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            cmc_card_no_param_mod('修改', 'edit/cmcCardNoParamMod.jsp', '600', '520');
        });
        if (parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else {
            $("#delete").on("click", function () {
                cmc_card_no_param_del();
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
            cmc_card_no_param_add('添加', 'add/cmcCardNoParamAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            cmc_card_no_param_mod('修改', 'edit/cmcCardNoParamMod.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            cmc_card_no_param_del();
        });
    }
    if (parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
    if( $('#cmcCardNoParam').DataTable().data().length > 0){
        $("#add").attr("href","javascript:;");
        $("#add").css("color","gray");
        $("#delete").attr("href","javascript:;");
        $("#delete").css("color","gray");
        $("#edit").attr("href","javascript:;");
        $("#edit").css("color","gray");
    }
}