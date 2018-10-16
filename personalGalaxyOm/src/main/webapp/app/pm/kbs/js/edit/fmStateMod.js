
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmState").find(".selected").length===1){
        rowData = parent.$('#fmState').DataTable().rows(".selected").data()[0];
        $("#country").val(rowData.COUNTRY).attr("disabled",true);
        $("#state").val(rowData.STATE).attr("disabled",true);
        $("#stateDesc").val(rowData.STATE_DESC);
        $("#company").val(rowData.COMPANY);
        $("#weekend1").val(rowData.WEEKEND_1);
        $("#weekend2").val(rowData.WEEKEND_2);
    }

    $("#fmStateMod").Validform({
        tiptype:2,
        callback:function(form){
            fmStateMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmStateMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_STATE";
    keyFieldsJson.COUNTRY=$("#country").val();
    keyFieldsJson.STATE=$("#state").val();
    generalFieldsJson.STATE_DESC=$("#stateDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.WEEKEND_1=$("#weekend1").val();
    generalFieldsJson.WEEKEND_2=$("#weekend2").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmStateMod,"json");
}

function callback_fmStateMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmState").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COUNTRY:$("#country").val(),
            STATE:$("#state").val(),
            STATE_DESC:$("#stateDesc").val(),
            COMPANY:$("#company").val(),
            WEEKEND_1:$("#weekend1").val(),
            WEEKEND_2:$("#weekend2").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmStateModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

