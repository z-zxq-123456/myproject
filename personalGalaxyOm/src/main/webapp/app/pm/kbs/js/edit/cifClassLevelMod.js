
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClassLevel").find(".selected").length===1){
        rowData = parent.$('#cifClassLevel').DataTable().rows(".selected").data()[0];
        $("#classLevel").val(rowData.CLASS_LEVEL).attr("disabled",true);
        $("#classLevelDesc").val(rowData.CLASS_LEVEL_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClassLevelMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClassLevelMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClassLevelMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_LEVEL";
    keyFieldsJson.CLASS_LEVEL=$("#classLevel").val();
    generalFieldsJson.CLASS_LEVEL_DESC=$("#classLevelDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClassLevelMod,"json");
}

function callback_cifClassLevelMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClassLevel").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_LEVEL:$("#classLevel").val(),
            CLASS_LEVEL_DESC:$("#classLevelDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClassLevelModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

