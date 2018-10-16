var rowData;
$(document).ready(function() {
    getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
            id: "company",
            async: false
        });
    if (parent.$("#fmEconDist").find(".selected").length===1){
        rowData = parent.$('#fmEconDist').DataTable().rows(".selected").data()[0];
        $("#econDist").val(rowData.ECON_DIST).attr("disabled",true);
        $("#econDistDesc").val(rowData.ECON_DIST_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmEconDistMod").Validform({
        tiptype:2,
        callback:function(){
            fmEconDistMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmEconDistMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_ECON_DIST";
    keyFieldsJson.ECON_DIST=$("#econDist").val();
    generalFieldsJson.ECON_DIST_DESC=$("#econDistDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmEconDistMod,"json");
}

function callback_fmEconDistMod(json){
    if (json.success) {
        if (parent.$("#fmEconDist").find(".selected").length===1){
            rowData = parent.$('#fmEconDist').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#fmEconDist").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ECON_DIST:$("#econDist").val(),
                ECON_DIST_DESC:$("#econDistDesc").val(),
                COMPANY:$("#company").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmEconDistModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}