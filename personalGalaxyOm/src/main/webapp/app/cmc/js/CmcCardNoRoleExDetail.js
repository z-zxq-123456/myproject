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
function isNullRoleEx() {
    if ($('#cmcCardNoRoleEx').DataTable().tables().context[0].aoData.length>0){
        parent.$("#two").show();
    }else {
        parent.$("#two").hide();
    }
}
$(document).ready(function() {
    $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#cmcCardNoRoleEx"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth=false;
    opt.deferRender=true;
    if (parent.$("#operateType").val()===undefined || parent.$("#operateType").val() !== "add") {
        opt.ajax = {
            "url": contextPath+"/baseComm/getAll",
            "type": "POST",
            "data":{
                "pk_value":$.session.get('cardProductCode'),
                "pk_name":"PARAM_NAME",
                "tableName":"CMC_CARD_NO_ROLE_EX"
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
    ];

    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "PARAM_NAME", "defaultContent":""}
        ,{ "data": "PARAM_VALUE", "defaultContent":""}
    ];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcCardNoRoleEx"),opt);
    $("#cmcCardNoRoleEx").beautyUi({
        tableId:"cmcCardNoRoleEx",
        buttonName:["添加","修改","删除" ],
        buttonId:["add","edit","delete"]
    });
    $('#cmcCardNoRoleEx tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcCardNoRoleEx').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcCardNoRoleEx').on('page.dt', function (e) {
        $('#cmcCardNoRoleEx').find("tr").removeClass('selected');
    });
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    $(".select2").select2();
    buttonStatus();

});

/*增加*/
function cmc_card_no_role_ex_add(title,url,w,h){
    if ($('#cmcCardNoRoleEx').DataTable().tables().context[0].aoData.length>0){
        showMsg("只允许定义唯一一行记录！","warning");
        return;
    }
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*删除*/
function  cmc_card_no_role_ex_del(){
    if ($("#cmcCardNoRoleEx").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcCardNoRoleExDel();
    });
}

function  cmcCardNoRoleExDel(){
    var rowData = $('#cmcCardNoRoleEx').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        paramName:rowData.PARAM_NAME,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardNoRoleEx/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcCardNoRoleExDel,"json");

}

function  callback_cmcCardNoRoleExDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcCardNoRoleEx').dataTable().api().row(".selected").remove().draw(false);
        if ($("#operateType")==="add"){
            isNullRoleEx();
        }
        showMsg(json.retMsg, 'info');
    }
}

/*修改*/
function cmc_card_no_role_ex_mod(title,url,w,h){
    if ($("#cmcCardNoRoleEx").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}


function buttonStatus()
{
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回"){
        $("#add").on("click",function(){
            cmc_card_no_role_ex_add('添加','add/cmcCardNoRoleExAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_ex_mod('修改','edit/cmcCardNoRoleExMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cmc_card_no_role_ex_del();
            });
        }
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已录入" ) {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else if(parent.$(".breadcrumb").data("needButton") === "NoEdit" ){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else
    {
        $(".breadcrumb").data("needButton","windowShow");
        $("#add").on("click",function(){
            cmc_card_no_role_ex_add('添加','add/cmcCardNoRoleExAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_ex_mod('修改','edit/cmcCardNoRoleExMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            cmc_card_no_role_ex_del();
        });
    }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}