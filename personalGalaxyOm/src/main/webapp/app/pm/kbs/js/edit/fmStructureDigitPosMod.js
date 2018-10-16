
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmStructureDigitPos").find(".selected").length===1){
        rowData = parent.$('#fmStructureDigitPos').DataTable().rows(".selected").data()[0];
        $("#digitPos").val(rowData.DIGIT_POS).attr("disabled",true);
        $("#structureType").val(rowData.STRUCTURE_TYPE).attr("disabled",true);
        $("#checkDigitInd").val(rowData.CHECK_DIGIT_IND);
        $("#company").val(rowData.COMPANY);
        $("#weight").val(rowData.WEIGHT);
    }

    $("#fmStructureDigitPosMod").Validform({
        tiptype:2,
        callback:function(form){
            fmStructureDigitPosMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmStructureDigitPosMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_STRUCTURE_DIGIT_POS";
    keyFieldsJson.DIGIT_POS=$("#digitPos").val();
    keyFieldsJson.STRUCTURE_TYPE=$("#structureType").val();
    generalFieldsJson.CHECK_DIGIT_IND=$("#checkDigitInd").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.WEIGHT=$("#weight").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmStructureDigitPosMod,"json");
}

function callback_fmStructureDigitPosMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmStructureDigitPos").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            DIGIT_POS:$("#digitPos").val(),
            STRUCTURE_TYPE:$("#structureType").val(),
            CHECK_DIGIT_IND:$("#checkDigitInd").val(),
            COMPANY:$("#company").val(),
            WEIGHT:$("#weight").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmStructureDigitPosModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

