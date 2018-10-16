var rowData;
$(document).ready(function() {

    if (parent.$("#acSubject").find(".selected").length===1){
        rowData = parent.$('#acSubject').DataTable().rows(".selected").data()[0];
        $("#subjectCode").val(rowData.SUBJECT_CODE).attr("disabled",true);
        $("#tfrInd").val(rowData.TFR_IND);
        $("#balanceWay").val(rowData.BALANCE_WAY);
        $("#bsplType").val(rowData.BSPL_TYPE);
        $("#subjectType").val(rowData.SUBJECT_TYPE);
        $("#subjectStatus").val(rowData.SUBJECT_STATUS);
        $("#subjectDesc").val(rowData.SUBJECT_DESC);
        $("#manualBatchRes").val(rowData.MANUAL_BATCH_RES);
        $("#glType").val(rowData.GL_TYPE);
        $("#operatingTax").val(rowData.OPERATING_TAX);
        $("#businessUnit").val(rowData.BUSINESS_UNIT);
        $("#ofTrf").val(rowData.OF_TRF);
        $("#subjectDescEn").val(rowData.SUBJECT_DESC_EN);
        $("#manualAccount").val(rowData.MANUAL_ACCOUNT);
        $("#internal").val(rowData.INTERNAL);
        $("#subjectLevel").val(rowData.SUBJECT_LEVEL);
        $("#controlSubject").val(rowData.CONTROL_SUBJECT);
    }

    $("#acSubjectMod").Validform({
        tiptype:2,
        callback:function(){
            acSubjectMod();
            return false;
        }
    });

    $(".select2").select2();
});

function acSubjectMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="AC_SUBJECT";
    keyFieldsJson.SUBJECT_CODE=$("#subjectCode").val();
    generalFieldsJson.TFR_IND=$("#tfrInd").val();
    generalFieldsJson.BALANCE_WAY=$("#balanceWay").val();
    generalFieldsJson.BSPL_TYPE=$("#bsplType").val();
    generalFieldsJson.SUBJECT_TYPE=$("#subjectType").val();
    generalFieldsJson.SUBJECT_STATUS=$("#subjectStatus").val();
    generalFieldsJson.SUBJECT_DESC=$("#subjectDesc").val();
    generalFieldsJson.MANUAL_BATCH_RES=$("#manualBatchRes").val();
    generalFieldsJson.GL_TYPE=$("#glType").val();
    generalFieldsJson.OPERATING_TAX=$("#operatingTax").val();
    generalFieldsJson.BUSINESS_UNIT=$("#businessUnit").val();
    generalFieldsJson.OF_TRF=$("#ofTrf").val();
    generalFieldsJson.SUBJECT_DESC_EN=$("#subjectDescEn").val();
    generalFieldsJson.MANUAL_ACCOUNT=$("#manualAccount").val();
    generalFieldsJson.INTERNAL=$("#internal").val();
    generalFieldsJson.SUBJECT_LEVEL=$("#subjectLevel").val();
    generalFieldsJson.CONTROL_SUBJECT=$("#controlSubject").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_acSubjectMod,"json");
}

function callback_acSubjectMod(json){
    if (json.success) {
        if (parent.$("#acSubject").find(".selected").length===1){
            rowData = parent.$('#acSubject').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#acSubject").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                SUBJECT_CODE:$("#subjectCode").val(),
                TFR_IND:$("#tfrInd").val(),
                BALANCE_WAY:$("#balanceWay").val(),
                BSPL_TYPE:$("#bsplType").val(),
                SUBJECT_TYPE:$("#subjectType").val(),
                SUBJECT_STATUS:$("#subjectStatus").val(),
                SUBJECT_DESC:$("#subjectDesc").val(),
                MANUAL_BATCH_RES:$("#manualBatchRes").val(),
                GL_TYPE:$("#glType").val(),
                OPERATING_TAX:$("#operatingTax").val(),
                BUSINESS_UNIT:$("#businessUnit").val(),
                OF_TRF:$("#ofTrf").val(),
                SUBJECT_DESC_EN:$("#subjectDescEn").val(),
                MANUAL_ACCOUNT:$("#manualAccount").val(),
                INTERNAL:$("#internal").val(),
                SUBJECT_LEVEL:$("#subjectLevel").val(),
                CONTROL_SUBJECT:$("#controlSubject").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function acSubjectModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}