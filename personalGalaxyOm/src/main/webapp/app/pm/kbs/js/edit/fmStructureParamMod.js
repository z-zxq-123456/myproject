
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmStructureParam").find(".selected").length===1){
        rowData = parent.$('#fmStructureParam').DataTable().rows(".selected").data()[0];
        $("#paramType").val(rowData.PARAM_TYPE).attr("disabled",true);
        $("#startPos").val(rowData.START_POS).attr("disabled",true);
        $("#structureType").val(rowData.STRUCTURE_TYPE).attr("disabled",true);
        $("#endPos").val(rowData.END_POS);
        $("#length").val(rowData.LENGTH);
        $("#company").val(rowData.COMPANY);
        $("#paddingChar").val(rowData.PADDING_CHAR);
        $("#seqType").val(rowData.SEQ_TYPE);
        $("#stringValue").val(rowData.STRING_VALUE);
    }

    $("#fmStructureParamMod").Validform({
        tiptype:2,
        callback:function(form){
            fmStructureParamMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmStructureParamMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_STRUCTURE_PARAM";
    keyFieldsJson.PARAM_TYPE=$("#paramType").val();
    keyFieldsJson.START_POS=$("#startPos").val();
    keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
    generalFieldsJson.END_POS=$("#endPos").val();
    generalFieldsJson.LENGTH=$("#length").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PADDING_CHAR=$("#paddingChar").val();
    generalFieldsJson.SEQ_TYPE=$("#seqType").val();
    generalFieldsJson.STRING_VALUE=$("#stringValue").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmStructureParamMod,"json");
}

function callback_fmStructureParamMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmStructureParam").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PARAM_TYPE:$("#paramType").val(),
            START_POS:$("#startPos").val(),
            STRUCTURE_TYPE:$("#structureType").val(),
            END_POS:$("#endPos").val(),
            LENGTH:$("#length").val(),
            COMPANY:$("#company").val(),
            PADDING_CHAR:$("#paddingChar").val(),
            SEQ_TYPE:$("#seqType").val(),
            STRING_VALUE:$("#stringValue").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmStructureParamModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

