
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClass4").find(".selected").length===1){
        rowData = parent.$('#cifClass4').DataTable().rows(".selected").data()[0];
        $("#class4").val(rowData.CLASS_4).attr("disabled",true);
        $("#class4Desc").val(rowData.CLASS_4_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClass4Mod").Validform({
        tiptype:2,
        callback:function(form){
            cifClass4Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClass4Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_4";
    keyFieldsJson.CLASS_4=$("#class4").val();
    generalFieldsJson.CLASS_4_DESC=$("#class4Desc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClass4Mod,"json");
}

function callback_cifClass4Mod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClass4").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_4:$("#class4").val(),
            CLASS_4_DESC:$("#class4Desc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClass4ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

