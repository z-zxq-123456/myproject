
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

    if (parent.$("#fmCcyHoliday").find(".selected").length===1){
        rowData = parent.$('#fmCcyHoliday').DataTable().rows(".selected").data()[0];
        $("#holidayDate").val(rowData.HOLIDAY_DATE).attr("disabled",true);
        $("#applyInd").val(rowData.APPLY_IND);
        $("#holidayDesc").val(rowData.HOLIDAY_DESC);
        $("#holidayType").val(rowData.HOLIDAY_TYPE);
        $("#ccy").val(rowData.CCY).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmCcyHolidayMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCcyHolidayMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCcyHolidayMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_CCY_HOLIDAY";
    keyFieldsJson.HOLIDAY_DATE=$("#holidayDate").val();
    generalFieldsJson.APPLY_IND=$("#applyInd").val();
    generalFieldsJson.HOLIDAY_DESC=$("#holidayDesc").val();
    generalFieldsJson.HOLIDAY_TYPE=$("#holidayType").val();
    keyFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCcyHolidayMod,"json");
}

function callback_fmCcyHolidayMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCcyHoliday").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            HOLIDAY_DATE:$("#holidayDate").val(),
            APPLY_IND:$("#applyInd").val(),
            HOLIDAY_DESC:$("#holidayDesc").val(),
            HOLIDAY_TYPE:$("#holidayType").val(),
            CCY:$("#ccy").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCcyHolidayModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

