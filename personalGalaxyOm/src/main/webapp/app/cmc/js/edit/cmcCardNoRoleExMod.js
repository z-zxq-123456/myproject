var rowData;
$(document).ready(function() {

    $.ajax({
        url: contextPath + "/cmcRuleNo/selectByPrimaryKey?ruleNo="+ $.session.get("cardNoType"),
        type: "post",
        async: false,
        success:function (json) {
            $("#paramValue").val(JSON.parse(json).ruleEx).attr("disabled",true);
        }
    });
    $("#paramName").val( $.session.get("cardProductCode")).attr("disabled",true);

    $("#cmcCardNoRoleExMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardNoRoleExMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardNoRoleExMod(){
    var url = contextPath+"/cardNoRoleEx/update";
    var generalFieldsJson = JSON.stringify({
        paramName:$("#paramName").val(),
        paramValue:$("#paramValue").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    $.ajax({
        type: "post",
        url: url,
        data: generalFieldsJson,
        dataType: "json",
        cache: false,
        contentType: "application/json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callback_cmcCardNoRoleExMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcCardNoRoleExMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoRoleEx").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoRoleEx').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcCardNoRoleEx").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PARAM_NAME:$("#paramName").val(),
                PARAM_VALUE:$("#paramValue").val()
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}