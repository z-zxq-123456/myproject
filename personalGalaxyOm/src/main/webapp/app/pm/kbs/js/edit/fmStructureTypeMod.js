
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmStructureType").find(".selected").length===1){
        rowData = parent.$('#fmStructureType').DataTable().rows(".selected").data()[0];
        $("#structureType").val(rowData.STRUCTURE_TYPE).attr("disabled",true);
        $("#structureLength").val(rowData.STRUCTURE_LENGTH);
        $("#structureDesc").val(rowData.STRUCTURE_DESC);
        $("#structureClass").val(rowData.STRUCTURE_CLASS);
        $("#structureAttr").val(rowData.STRUCTURE_ATTR);
        $("#delimiterInd").val(rowData.DELIMITER_IND);
        $("#completeInd").val(rowData.COMPLETE_IND);
        $("#restrictedDelimiter").val(rowData.RESTRICTED_DELIMITER);
        $("#company").val(rowData.COMPANY);
        $("#checkDigitFormula").val(rowData.CHECK_DIGIT_FORMULA);
    }

    $("#fmStructureTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmStructureTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmStructureTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_STRUCTURE_TYPE";
    keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
    generalFieldsJson.STRUCTURE_LENGTH=$("#structureLength").val();
    generalFieldsJson.STRUCTURE_DESC=$("#structureDesc").val();
    generalFieldsJson.STRUCTURE_CLASS=$("#structureClass").val();
    generalFieldsJson.STRUCTURE_ATTR=$("#structureAttr").val();
    generalFieldsJson.DELIMITER_IND=$("#delimiterInd").val();
    generalFieldsJson.COMPLETE_IND=$("#completeInd").val();
    generalFieldsJson.RESTRICTED_DELIMITER=$("#restrictedDelimiter").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CHECK_DIGIT_FORMULA=$("#checkDigitFormula").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmStructureTypeMod,"json");
}

function callback_fmStructureTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmStructureType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            STRUCTURE_TYPE:$("#structureType").val(),
            STRUCTURE_LENGTH:$("#structureLength").val(),
            STRUCTURE_DESC:$("#structureDesc").val(),
            STRUCTURE_CLASS:$("#structureClass").val(),
            STRUCTURE_ATTR:$("#structureAttr").val(),
            DELIMITER_IND:$("#delimiterInd").val(),
            COMPLETE_IND:$("#completeInd").val(),
            RESTRICTED_DELIMITER:$("#restrictedDelimiter").val(),
            COMPANY:$("#company").val(),
            CHECK_DIGIT_FORMULA:$("#checkDigitFormula").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmStructureTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

