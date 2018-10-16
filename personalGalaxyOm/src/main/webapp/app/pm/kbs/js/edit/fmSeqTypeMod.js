
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmSeqType").find(".selected").length===1){
        rowData = parent.$('#fmSeqType').DataTable().rows(".selected").data()[0];
        $("#seqType").val(rowData.SEQ_TYPE).attr("disabled",true);
        $("#branchReset").val(rowData.BRANCH_RESET);
        $("#ccyReset").val(rowData.CCY_RESET);
        $("#endNo").val(rowData.END_NO);
        $("#prodTypeReset").val(rowData.PROD_TYPE_RESET);
        $("#startNo").val(rowData.START_NO);
        $("#company").val(rowData.COMPANY);
        $("#isIndvlReset").val(rowData.IS_INDVL_RESET);
    }

    $("#fmSeqTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmSeqTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmSeqTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_SEQ_TYPE";
    keyFieldsJson.SEQ_TYPE=$("#seqType").val();
    generalFieldsJson.BRANCH_RESET=$("#branchReset").val();
    generalFieldsJson.CCY_RESET=$("#ccyReset").val();
    generalFieldsJson.END_NO=$("#endNo").val();
    generalFieldsJson.PROD_TYPE_RESET=$("#prodTypeReset").val();
    generalFieldsJson.START_NO=$("#startNo").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.IS_INDVL_RESET=$("#isIndvlReset").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmSeqTypeMod,"json");
}

function callback_fmSeqTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmSeqType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            SEQ_TYPE:$("#seqType").val(),
            BRANCH_RESET:$("#branchReset").val(),
            CCY_RESET:$("#ccyReset").val(),
            END_NO:$("#endNo").val(),
            PROD_TYPE_RESET:$("#prodTypeReset").val(),
            START_NO:$("#startNo").val(),
            COMPANY:$("#company").val(),
            IS_INDVL_RESET:$("#isIndvlReset").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmSeqTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

