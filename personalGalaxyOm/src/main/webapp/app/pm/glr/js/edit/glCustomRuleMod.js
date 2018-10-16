
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "glCode",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_PROFIT_CENTRE&tableCol=PROFIT_CENTRE,PROFIT_CENTRE_DESC",
        id: "profitCentre",
        async: false
    });

    if (parent.$("#glCustomRule").find(".selected").length===1){
        rowData = parent.$('#glCustomRule').DataTable().rows(".selected").data()[0];
        $("#ruleNo").val(rowData.RULE_NO).attr("disabled",true);
        $("#glCode").val(rowData.GL_CODE);
        $("#ruleDesc").val(rowData.RULE_DESC);
        $("#ruleExpression").val(rowData.RULE_EXPRESSION);
        $("#bussinessUnit").val(rowData.BUSSINESS_UNIT);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
    }

    $("#glCustomRuleMod").Validform({
        tiptype:2,
        callback:function(form){
            glCustomRuleMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glCustomRuleMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_CUSTOM_RULE";
    keyFieldsJson.RULE_NO=$("#ruleNo").val();
    generalFieldsJson.GL_CODE=$("#glCode").val();
    generalFieldsJson.RULE_DESC=$("#ruleDesc").val();
    generalFieldsJson.RULE_EXPRESSION=$("#ruleExpression").val();
    generalFieldsJson.BUSSINESS_UNIT=$("#bussinessUnit").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glCustomRuleMod,"json");
}

function callback_glCustomRuleMod(json){
    if (json.success) {
        var dataTable=parent.$("#glCustomRule").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            RULE_NO:$("#ruleNo").val(),
            GL_CODE:$("#glCode").val(),
            RULE_DESC:$("#ruleDesc").val(),
            RULE_EXPRESSION:$("#ruleExpression").val(),
            BUSSINESS_UNIT:$("#bussinessUnit").val(),
            PROFIT_CENTRE:$("#profitCentre").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glCustomRuleModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

