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
    // 获取默认opt配置
    var opt = getDataTableOpt($("#cmcRuleNo"));
    opt.stateSave=true;
    opt.processing=true;
    opt.scrollX=true;
    opt.deferRender=true;
    opt.ajax= {
        "url": contextPath+"/cmcRuleNo/getAll",
        "type": "POST"
    };
    opt.columnDefs=[
        {
            "width":"20%",
            "targets":0
        }
        ,{
            "width":"20%",
            "targets":1
        }
        ,{
            "width":"20%",
            "targets":2
        }
        ,{
            "width":"20%",
            "targets":3
        }

    ];
    opt.columns=[
        {"data": "ruleCode", "defaultContent": ""}
        ,{ "data": "ruleDesc", "defaultContent":""}
        ,{ "data": "ruleNo", "defaultContent":""}
        ,{ "data": "ruleEx", "defaultContent":""}];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcRuleNo"),opt);
    $(".select2").select2();

    $("#cmcRuleNo").beautyUi({
        tableId:"cmcProductInfo",
        buttonName:["添加", "修改","删除"],
        buttonId:["add",　"edit","delete"]
    });
    $('#cmcRuleNo tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcRuleNo').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcRuleNo').on('page.dt', function (e) {
        $('#cmcRuleNo').find("tr").removeClass('selected');
    });

    $("#add").on("click",function(){
        cmc_rule_no_add('添加','add/cmcRuleNoAdd.jsp','600','520');
    });
    $("#edit").on("click",function(){
        cmc_rule_no_mod('修改','edit/cmcRuleNoMod.jsp','600','520');
    });
    $("#delete").on("click",function(){
        cmc_rule_no_del();
    });
});

/*增加*/
function cmc_rule_no_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_rule_no_mod(title,url,w,h){
    if ($("#cmcRuleNo").find(".selected").length!==1){
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
function  cmc_rule_no_del(){
    if ($("#cmcRuleNo").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcRuleNoDel();
    });
}

function  cmcRuleNoDel(){
    var rowData = $('#cmcRuleNo').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        ruleCode:rowData.ruleCode
    };
    var url = contextPath+"/cmcRuleNo/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcRuleNoDel,"json");
}

function  callback_cmcRuleNoDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcRuleNo').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
    }
}
