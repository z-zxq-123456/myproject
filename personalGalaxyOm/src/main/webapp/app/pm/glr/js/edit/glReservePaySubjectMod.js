var rowData;
$(document).ready(function() {

    if (parent.$("#glReservePaySubject").find(".selected").length===1){
        rowData = parent.$('#glReservePaySubject').DataTable().rows(".selected").data()[0];
        $("#payGlCode").val(rowData.PAY_GL_CODE).attr("disabled",true);
        $("#payType").val(rowData.PAY_TYPE).attr("disabled",true);
        $("#countRate").val(rowData.COUNT_RATE);
        $("#sumBalFlag").val(rowData.SUM_BAL_FLAG);
    }

    $("#glReservePaySubjectMod").Validform({
        tiptype:2,
        callback:function(){
            glReservePaySubjectMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glReservePaySubjectMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_RESERVE_PAY_SUBJECT";
    keyFieldsJson.PAY_GL_CODE=$("#payGlCode").val();
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.COUNT_RATE=$("#countRate").val();
    generalFieldsJson.SUM_BAL_FLAG=$("#sumBalFlag").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glReservePaySubjectMod,"json");
}

function callback_glReservePaySubjectMod(json){
    if (json.success) {
        if (parent.$("#glReservePaySubject").find(".selected").length===1){
            rowData = parent.$('#glReservePaySubject').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#glReservePaySubject").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PAY_GL_CODE:$("#payGlCode").val(),
                PAY_TYPE:$("#payType").val(),
                COUNT_RATE:$("#countRate").val(),
                SUM_BAL_FLAG:$("#sumBalFlag").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glReservePaySubjectModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}