
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifEconDist").find(".selected").length===1){
        rowData = parent.$('#cifEconDist').DataTable().rows(".selected").data()[0];
        $("#econDist").val(rowData.ECON_DIST).attr("disabled",true);
        $("#econDistDesc").val(rowData.ECON_DIST_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifEconDistMod").Validform({
        tiptype:2,
        callback:function(form){
            cifEconDistMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifEconDistMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_ECON_DIST";
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
    sendPostRequest(url,params,callback_cifEconDistMod,"json");
}

function callback_cifEconDistMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifEconDist").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ECON_DIST:$("#econDist").val(),
            ECON_DIST_DESC:$("#econDistDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifEconDistModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

