
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#mbAccountingStatus").find(".selected").length===1){
        rowData = parent.$('#mbAccountingStatus').DataTable().rows(".selected").data()[0];
        $("#accountingStatus").val(rowData.ACCOUNTING_STATUS).attr("disabled",true);
        $("#graceDay").val(rowData.GRACE_DAY);
        $("#huntingStatus").val(rowData.HUNTING_STATUS);
        $("#nonPerforming").val(rowData.NON_PERFORMING);
        $("#nonPerformingPri").val(rowData.NON_PERFORMING_PRI);
        $("#period").val(rowData.PERIOD);
        $("#periodType").val(rowData.PERIOD_TYPE);
        $("#suspendInd").val(rowData.SUSPEND_IND);
        $("#terminateInd").val(rowData.TERMINATE_IND);
        $("#company").val(rowData.COMPANY);
        $("#changeType").val(rowData.CHANGE_TYPE);
        $("#available").val(rowData.AVAILABLE);
        $("#accountingStatusDesc").val(rowData.ACCOUNTING_STATUS_DESC);
        $("#allocSeqFee").val(rowData.ALLOC_SEQ_FEE);
        $("#allocSeqInt").val(rowData.ALLOC_SEQ_INT);
        $("#allocSeqOdi").val(rowData.ALLOC_SEQ_ODI);
        $("#allocSeqOdp").val(rowData.ALLOC_SEQ_ODP);
        $("#allocSeqPri").val(rowData.ALLOC_SEQ_PRI);
        $("#allocSeqType").val(rowData.ALLOC_SEQ_TYPE);
        $("#autoChange").val(rowData.AUTO_CHANGE);
        $("#writeOff").val(rowData.WRITE_OFF);
    }

    $("#mbAccountingStatusMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAccountingStatusMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAccountingStatusMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ACCOUNTING_STATUS";
    keyFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
    generalFieldsJson.GRACE_DAY=$("#graceDay").val();
    generalFieldsJson.HUNTING_STATUS=$("#huntingStatus").val();
    generalFieldsJson.NON_PERFORMING=$("#nonPerforming").val();
    generalFieldsJson.NON_PERFORMING_PRI=$("#nonPerformingPri").val();
    generalFieldsJson.PERIOD=$("#period").val();
    generalFieldsJson.PERIOD_TYPE=$("#periodType").val();
    generalFieldsJson.SUSPEND_IND=$("#suspendInd").val();
    generalFieldsJson.TERMINATE_IND=$("#terminateInd").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CHANGE_TYPE=$("#changeType").val();
    generalFieldsJson.AVAILABLE=$("#available").val();
    generalFieldsJson.ACCOUNTING_STATUS_DESC=$("#accountingStatusDesc").val();
    generalFieldsJson.ALLOC_SEQ_FEE=$("#allocSeqFee").val();
    generalFieldsJson.ALLOC_SEQ_INT=$("#allocSeqInt").val();
    generalFieldsJson.ALLOC_SEQ_ODI=$("#allocSeqOdi").val();
    generalFieldsJson.ALLOC_SEQ_ODP=$("#allocSeqOdp").val();
    generalFieldsJson.ALLOC_SEQ_PRI=$("#allocSeqPri").val();
    generalFieldsJson.ALLOC_SEQ_TYPE=$("#allocSeqType").val();
    generalFieldsJson.AUTO_CHANGE=$("#autoChange").val();
    generalFieldsJson.WRITE_OFF=$("#writeOff").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAccountingStatusMod,"json");
}

function callback_mbAccountingStatusMod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAccountingStatus").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCOUNTING_STATUS:$("#accountingStatus").val(),
            GRACE_DAY:$("#graceDay").val(),
            HUNTING_STATUS:$("#huntingStatus").val(),
            NON_PERFORMING:$("#nonPerforming").val(),
            NON_PERFORMING_PRI:$("#nonPerformingPri").val(),
            PERIOD:$("#period").val(),
            PERIOD_TYPE:$("#periodType").val(),
            SUSPEND_IND:$("#suspendInd").val(),
            TERMINATE_IND:$("#terminateInd").val(),
            COMPANY:$("#company").val(),
            CHANGE_TYPE:$("#changeType").val(),
            AVAILABLE:$("#available").val(),
            ACCOUNTING_STATUS_DESC:$("#accountingStatusDesc").val(),
            ALLOC_SEQ_FEE:$("#allocSeqFee").val(),
            ALLOC_SEQ_INT:$("#allocSeqInt").val(),
            ALLOC_SEQ_ODI:$("#allocSeqOdi").val(),
            ALLOC_SEQ_ODP:$("#allocSeqOdp").val(),
            ALLOC_SEQ_PRI:$("#allocSeqPri").val(),
            ALLOC_SEQ_TYPE:$("#allocSeqType").val(),
            AUTO_CHANGE:$("#autoChange").val(),
            WRITE_OFF:$("#writeOff").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAccountingStatusModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

