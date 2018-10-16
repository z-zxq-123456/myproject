
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClass2").find(".selected").length===1){
        rowData = parent.$('#cifClass2').DataTable().rows(".selected").data()[0];
        $("#class2").val(rowData.CLASS_2).attr("disabled",true);
        $("#class2Desc").val(rowData.CLASS_2_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClass2Mod").Validform({
        tiptype:2,
        callback:function(form){
            cifClass2Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClass2Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_2";
    keyFieldsJson.CLASS_2=$("#class2").val();
    generalFieldsJson.CLASS_2_DESC=$("#class2Desc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClass2Mod,"json");
}

function callback_cifClass2Mod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClass2").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_2:$("#class2").val(),
            CLASS_2_DESC:$("#class2Desc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClass2ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

