
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#tbParValue").find(".selected").length===1){
        rowData = parent.$('#tbParValue').DataTable().rows(".selected").data()[0];
        $("#parValueId").val(rowData.PAR_VALUE_ID).attr("disabled",true);
        $("#ccy").val(rowData.CCY);
        $("#isSpall").val(rowData.IS_SPALL);
        $("#company").val(rowData.COMPANY);
        $("#parDesc").val(rowData.PAR_DESC);
        $("#parType").val(rowData.PAR_TYPE);
        $("#parValue").val(rowData.PAR_VALUE);
        $("#updateDate").val(rowData.UPDATE_DATE);
    }

    $("#tbParValueMod").Validform({
        tiptype:2,
        callback:function(form){
            tbParValueMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbParValueMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_PAR_VALUE";
    keyFieldsJson.PAR_VALUE_ID=$("#parValueId").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.IS_SPALL=$("#isSpall").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PAR_DESC=$("#parDesc").val();
    generalFieldsJson.PAR_TYPE=$("#parType").val();
    generalFieldsJson.PAR_VALUE=$("#parValue").val();
    generalFieldsJson.UPDATE_DATE=$("#updateDate").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbParValueMod,"json");
}

function callback_tbParValueMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbParValue").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PAR_VALUE_ID:$("#parValueId").val(),
            CCY:$("#ccy").val(),
            IS_SPALL:$("#isSpall").val(),
            COMPANY:$("#company").val(),
            PAR_DESC:$("#parDesc").val(),
            PAR_TYPE:$("#parType").val(),
            PAR_VALUE:$("#parValue").val(),
            UPDATE_DATE:$("#updateDate").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbParValueModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

