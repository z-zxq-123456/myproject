var rowData;
$(document).ready(function() {
    rowData = parent.$('#cmcRuleNo').DataTable().rows(".selected").data()[0];
    if (parent.$("#cmcRuleNo").find(".selected").length===1){
        mappingData();
    }
    $("#cmcRuleNoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcRuleNoMod();
            return false;
        }
    });
    $(".select2").select2();
});

function mappingData() {
    $("#ruleCode").val(rowData.ruleCode).attr("disabled",true);

    $("#ruleDesc").val(rowData.ruleDesc);
    $("#ruleNo").val(rowData.ruleNo);
    $("#ruleEx").val(rowData.ruleEx);
}

function cmcRuleNoMod(){
    var url = contextPath+"/cmcRuleNo/update";
    var generalFieldsJson = JSON.stringify({
        ruleCode:$("#ruleCode").val(),
        ruleDesc:$("#ruleDesc").val(),
        ruleNo:$("#ruleNo").val(),
        ruleEx:$("#ruleEx").val()
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
        success: callback_cmcRuleNoMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcRuleNoMod(json){
    if (json.success) {
        if (parent.$("#cmcRuleNo").find(".selected").length===1){
            rowData = parent.$('#cmcRuleNo').DataTable().rows(".selected").data()[0];
            var dataTable=parent.$("#cmcRuleNo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ruleCode:$("#ruleCode").val(),
                ruleDesc:$("#ruleDesc").val(),
                ruleNo:$("#ruleNo").val(),
                ruleEx:$("#ruleEx").val()
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
