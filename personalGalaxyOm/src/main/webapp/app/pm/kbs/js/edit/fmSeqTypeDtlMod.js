
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmSeqTypeDtl").find(".selected").length===1){
        rowData = parent.$('#fmSeqTypeDtl').DataTable().rows(".selected").data()[0];
        $("#seqResetParam").val(rowData.SEQ_RESET_PARAM).attr("disabled",true);
        $("#seqType").val(rowData.SEQ_TYPE).attr("disabled",true);
        $("#endNo").val(rowData.END_NO);
        $("#startNo").val(rowData.START_NO);
        $("#company").val(rowData.COMPANY);
        $("#lastNo").val(rowData.LAST_NO);
    }

    $("#fmSeqTypeDtlMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSeqTypeDtlMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSeqTypeDtlMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SEQ_TYPE_DTL";
    keyFieldsJson.SEQ_RESET_PARAM=$("#seqResetParam").val();
    keyFieldsJson.SEQ_TYPE=$("#seqType").val();
    generalFieldsJson.END_NO=$("#endNo").val();
    generalFieldsJson.START_NO=$("#startNo").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.LAST_NO=$("#lastNo").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSeqTypeDtlMod,"json");
}

function callback_fmSeqTypeDtlMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSeqTypeDtl").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SEQ_RESET_PARAM:$("#seqResetParam").val(),
            SEQ_TYPE:$("#seqType").val(),
            END_NO:$("#endNo").val(),
            START_NO:$("#startNo").val(),
            COMPANY:$("#company").val(),
            LAST_NO:$("#lastNo").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSeqTypeDtlModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

