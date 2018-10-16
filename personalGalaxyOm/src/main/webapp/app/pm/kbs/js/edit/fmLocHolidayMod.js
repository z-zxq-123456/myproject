
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmLocHoliday").find(".selected").length===1){
        rowData = parent.$('#fmLocHoliday').DataTable().rows(".selected").data()[0];
        $("#country").val(rowData.COUNTRY).attr("disabled",true);
        $("#holidayDate").val(rowData.HOLIDAY_DATE).attr("disabled",true);
        $("#state").val(rowData.STATE).attr("disabled",true);
        $("#applyInd").val(rowData.APPLY_IND);
        $("#holidayDesc").val(rowData.HOLIDAY_DESC);
        $("#holidayType").val(rowData.HOLIDAY_TYPE);
        $("#workingHoliday").val(rowData.WORKING_HOLIDAY);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmLocHolidayMod").Validform({
        tiptype:2,
        callback:function(form){
            fmLocHolidayMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmLocHolidayMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_LOC_HOLIDAY";
    keyFieldsJson.COUNTRY=$("#country").val();
    keyFieldsJson.HOLIDAY_DATE=$("#holidayDate").val();
    keyFieldsJson.STATE=$("#state").val();
    generalFieldsJson.APPLY_IND=$("#applyInd").val();
    generalFieldsJson.HOLIDAY_DESC=$("#holidayDesc").val();
    generalFieldsJson.HOLIDAY_TYPE=$("#holidayType").val();
    generalFieldsJson.WORKING_HOLIDAY=$("#workingHoliday").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmLocHolidayMod,"json");
}

function callback_fmLocHolidayMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmLocHoliday").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COUNTRY:$("#country").val(),
            HOLIDAY_DATE:$("#holidayDate").val(),
            STATE:$("#state").val(),
            APPLY_IND:$("#applyInd").val(),
            HOLIDAY_DESC:$("#holidayDesc").val(),
            HOLIDAY_TYPE:$("#holidayType").val(),
            WORKING_HOLIDAY:$("#workingHoliday").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmLocHolidayModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

