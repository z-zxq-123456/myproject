
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifBusiness").find(".selected").length===1){
        rowData = parent.$('#cifBusiness').DataTable().rows(".selected").data()[0];
        $("#business").val(rowData.BUSINESS).attr("disabled",true);
        $("#businessDesc").val(rowData.BUSINESS_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifBusinessMod").Validform({
        tiptype:2,
        callback:function(form){
            cifBusinessMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifBusinessMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_BUSINESS";
    keyFieldsJson.BUSINESS=$("#business").val();
    generalFieldsJson.BUSINESS_DESC=$("#businessDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifBusinessMod,"json");
}

function callback_cifBusinessMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifBusiness").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BUSINESS:$("#business").val(),
            BUSINESS_DESC:$("#businessDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifBusinessModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

