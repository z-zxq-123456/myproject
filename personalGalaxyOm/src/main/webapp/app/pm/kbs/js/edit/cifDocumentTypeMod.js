
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifDocumentType").find(".selected").length===1){
        rowData = parent.$('#cifDocumentType').DataTable().rows(".selected").data()[0];
        $("#documentType").val(rowData.DOCUMENT_TYPE).attr("disabled",true);
        $("#appInd").val(rowData.APP_IND);
        $("#documentTypeDesc").val(rowData.DOCUMENT_TYPE_DESC);
        $("#company").val(rowData.COMPANY);
        $("#documentTypeShort").val(rowData.DOCUMENT_TYPE_SHORT);
    }

    $("#cifDocumentTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifDocumentTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifDocumentTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_DOCUMENT_TYPE";
    keyFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
    generalFieldsJson.APP_IND=$("#appInd").val();
    generalFieldsJson.DOCUMENT_TYPE_DESC=$("#documentTypeDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DOCUMENT_TYPE_SHORT=$("#documentTypeShort").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifDocumentTypeMod,"json");
}

function callback_cifDocumentTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifDocumentType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            DOCUMENT_TYPE:$("#documentType").val(),
            APP_IND:$("#appInd").val(),
            DOCUMENT_TYPE_DESC:$("#documentTypeDesc").val(),
            COMPANY:$("#company").val(),
            DOCUMENT_TYPE_SHORT:$("#documentTypeShort").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifDocumentTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

