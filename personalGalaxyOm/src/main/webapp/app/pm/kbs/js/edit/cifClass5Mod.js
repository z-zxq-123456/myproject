
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClass5").find(".selected").length===1){
        rowData = parent.$('#cifClass5').DataTable().rows(".selected").data()[0];
        $("#class5").val(rowData.CLASS_5).attr("disabled",true);
        $("#class5Desc").val(rowData.CLASS_5_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClass5Mod").Validform({
        tiptype:2,
        callback:function(form){
            cifClass5Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClass5Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_5";
    keyFieldsJson.CLASS_5=$("#class5").val();
    generalFieldsJson.CLASS_5_DESC=$("#class5Desc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClass5Mod,"json");
}

function callback_cifClass5Mod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClass5").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_5:$("#class5").val(),
            CLASS_5_DESC:$("#class5Desc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClass5ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

