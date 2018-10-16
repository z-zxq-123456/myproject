
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClientStatus").find(".selected").length===1){
        rowData = parent.$('#cifClientStatus').DataTable().rows(".selected").data()[0];
        $("#clientStatus").val(rowData.CLIENT_STATUS).attr("disabled",true);
        $("#clientStatusDesc").val(rowData.CLIENT_STATUS_DESC);
        $("#badClientInd").val(rowData.BAD_CLIENT_IND);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClientStatusMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClientStatusMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClientStatusMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLIENT_STATUS";
    keyFieldsJson.CLIENT_STATUS=$("#clientStatus").val();
    generalFieldsJson.CLIENT_STATUS_DESC=$("#clientStatusDesc").val();
    generalFieldsJson.BAD_CLIENT_IND=$("#badClientInd").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClientStatusMod,"json");
}

function callback_cifClientStatusMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClientStatus").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        CLIENT_STATUS:$("#clientStatus").val()
        ,CLIENT_STATUS_DESC:$("#clientStatusDesc").val()
        ,BAD_CLIENT_IND:$("#badClientInd").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClientStatusModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

