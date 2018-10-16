
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifOccupation").find(".selected").length===1){
        rowData = parent.$('#cifOccupation').DataTable().rows(".selected").data()[0];
        $("#occupationCode").val(rowData.OCCUPATION_CODE).attr("disabled",true);
        $("#occupationDesc").val(rowData.OCCUPATION_DESC);
        $("#company").val(rowData.COMPANY);
        $("#riskLevel").val(rowData.RISK_LEVEL);
    }

    $("#cifOccupationMod").Validform({
        tiptype:2,
        callback:function(form){
            cifOccupationMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifOccupationMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_OCCUPATION";
    keyFieldsJson.OCCUPATION_CODE=$("#occupationCode").val();
    generalFieldsJson.OCCUPATION_DESC=$("#occupationDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.RISK_LEVEL=$("#riskLevel").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifOccupationMod,"json");
}

function callback_cifOccupationMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifOccupation").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            OCCUPATION_CODE:$("#occupationCode").val(),
            OCCUPATION_DESC:$("#occupationDesc").val(),
            COMPANY:$("#company").val(),
            RISK_LEVEL:$("#riskLevel").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifOccupationModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

