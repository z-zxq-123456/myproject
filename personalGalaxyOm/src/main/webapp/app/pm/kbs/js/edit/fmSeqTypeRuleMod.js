
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmSeqTypeRule").find(".selected").length===1){
        rowData = parent.$('#fmSeqTypeRule').DataTable().rows(".selected").data()[0];
        $("#seqType").val(rowData.SEQ_TYPE).attr("disabled",true);
        $("#endNo").val(rowData.END_NO);
        $("#ruleType").val(rowData.RULE_TYPE);
        $("#startNo").val(rowData.START_NO);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmSeqTypeRuleMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSeqTypeRuleMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSeqTypeRuleMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SEQ_TYPE_RULE";
    keyFieldsJson.SEQ_TYPE=$("#seqType").val();
    generalFieldsJson.END_NO=$("#endNo").val();
    generalFieldsJson.RULE_TYPE=$("#ruleType").val();
    generalFieldsJson.START_NO=$("#startNo").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSeqTypeRuleMod,"json");
}

function callback_fmSeqTypeRuleMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSeqTypeRule").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SEQ_TYPE:$("#seqType").val(),
            END_NO:$("#endNo").val(),
            RULE_TYPE:$("#ruleType").val(),
            START_NO:$("#startNo").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSeqTypeRuleModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

