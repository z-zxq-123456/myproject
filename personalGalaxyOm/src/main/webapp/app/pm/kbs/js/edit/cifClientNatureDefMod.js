
var rowData;
$(document).ready(function() {
     getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    if (parent.$("#cifClientNatureDef").find(".selected").length===1){
        rowData = parent.$('#cifClientNatureDef').DataTable().rows(".selected").data()[0];
        $("#clientNature").val(rowData.CLIENT_NATURE).attr("disabled",true);
        $("#lossInd").val(rowData.LOSS_IND);
        $("#clientNatureDesc").val(rowData.CLIENT_NATURE_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClientNatureDefMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClientNatureDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClientNatureDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLIENT_NATURE_DEF";
    keyFieldsJson.CLIENT_NATURE=$("#clientNature").val();
    generalFieldsJson.LOSS_IND=$("#lossInd").val();
    generalFieldsJson.CLIENT_NATURE_DESC=$("#clientNatureDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClientNatureDefMod,"json");
}

function callback_cifClientNatureDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClientNatureDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLIENT_NATURE:$("#clientNature").val(),
            LOSS_IND:$("#lossInd").val(),
            CLIENT_NATURE_DESC:$("#clientNatureDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClientNatureDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

