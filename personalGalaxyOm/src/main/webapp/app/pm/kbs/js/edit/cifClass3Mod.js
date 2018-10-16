
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClass3").find(".selected").length===1){
        rowData = parent.$('#cifClass3').DataTable().rows(".selected").data()[0];
        $("#class3").val(rowData.CLASS_3).attr("disabled",true);
        $("#class3Desc").val(rowData.CLASS_3_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClass3Mod").Validform({
        tiptype:2,
        callback:function(form){
            cifClass3Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClass3Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_3";
    keyFieldsJson.CLASS_3=$("#class3").val();
    generalFieldsJson.CLASS_3_DESC=$("#class3Desc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClass3Mod,"json");
}

function callback_cifClass3Mod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClass3").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_3:$("#class3").val(),
            CLASS_3_DESC:$("#class3Desc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClass3ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

