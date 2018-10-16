
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifMerchantTypeDef").find(".selected").length===1){
        rowData = parent.$('#cifMerchantTypeDef').DataTable().rows(".selected").data()[0];
        $("#ccSubType").val(rowData.CC_SUB_TYPE).attr("disabled",true);
        $("#ccType").val(rowData.CC_TYPE);
        $("#company").val(rowData.COMPANY);
        $("#ccTypeDesc").val(rowData.CC_TYPE_DESC);
        $("#ccSubTypeDesc").val(rowData.CC_SUB_TYPE_DESC);
    }

    $("#cifMerchantTypeDefMod").Validform({
        tiptype:2,
        callback:function(form){
            cifMerchantTypeDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifMerchantTypeDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_MERCHANT_TYPE_DEF";
    keyFieldsJson.CC_SUB_TYPE=$("#ccSubType").val();
    generalFieldsJson.CC_TYPE=$("#ccType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CC_TYPE_DESC=$("#ccTypeDesc").val();
    generalFieldsJson.CC_SUB_TYPE_DESC=$("#ccSubTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifMerchantTypeDefMod,"json");
}

function callback_cifMerchantTypeDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifMerchantTypeDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        CC_SUB_TYPE:$("#ccSubType").val()
        ,CC_TYPE:$("#ccType").val()
        ,COMPANY:$("#company").val()
        ,CC_TYPE_DESC:$("#ccTypeDesc").val()
        ,CC_SUB_TYPE_DESC:$("#ccSubTypeDesc").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifMerchantTypeDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

