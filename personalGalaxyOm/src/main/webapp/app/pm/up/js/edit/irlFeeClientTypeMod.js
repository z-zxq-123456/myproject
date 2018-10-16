
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlFeeClientType").find(".selected").length===1){
        rowData = parent.$('#irlFeeClientType').DataTable().rows(".selected").data()[0];
            $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
            $("#feeType").val(rowData.FEE_TYPE).attr("disabled",true);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlFeeClientTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            irlFeeClientTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeClientTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_CLIENT_TYPE";
        keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
        keyFieldsJson.FEE_TYPE=$("#feeType").val();
        generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeClientTypeMod,"json");
}

function callback_irlFeeClientTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlFeeClientType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLIENT_TYPE:$("clientType").val(),
            FEE_TYPE:$("feeType").val(),
            COMPANY:$("company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeClientTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

