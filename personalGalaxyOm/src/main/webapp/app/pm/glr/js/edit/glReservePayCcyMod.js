var rowData;
$(document).ready(function() {

    if (parent.$("#glReservePayCcy").find(".selected").length===1){
        rowData = parent.$('#glReservePayCcy').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#payCcy").val(rowData.PAY_CCY).attr("disabled",true);
        $("#payType").val(rowData.PAY_TYPE).attr("disabled",true);
        $("#collInd").val(rowData.COLL_IND);
    }

    $("#glReservePayCcyMod").Validform({
        tiptype:2,
        callback:function(){
            glReservePayCcyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function glReservePayCcyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="GL_RESERVE_PAY_CCY";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.PAY_CCY=$("#payCcy").val();
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.COLL_IND=$("#collInd").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_glReservePayCcyMod,"json");
}

function callback_glReservePayCcyMod(json){
    if (json.success) {
        if (parent.$("#glReservePayCcy").find(".selected").length===1){
            rowData = parent.$('#glReservePayCcy').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#glReservePayCcy").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BRANCH:$("#branch").val(),
                PAY_CCY:$("#payCcy").val(),
                PAY_TYPE:$("#payType").val(),
                COLL_IND:$("#collInd").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function glReservePayCcyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}