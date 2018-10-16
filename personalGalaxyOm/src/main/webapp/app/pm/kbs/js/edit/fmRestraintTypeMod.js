
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmRestraintType").find(".selected").length===1){
        rowData = parent.$('#fmRestraintType').DataTable().rows(".selected").data()[0];
        $("#restraintType").val(rowData.RESTRAINT_TYPE).attr("disabled",true);
        $("#ahBu").val(rowData.AH_BU);
        $("#systemUserFlag").val(rowData.SYSTEM_USE_FLAG);
        $("#company").val(rowData.COMPANY);
        $("#eodImpoundFalg").val(rowData.EOD_IMPOUND_FALG);
        $("#manualResFlag").val(rowData.MANUAL_RES_FLAG);
        $("#manualUnresFlag").val(rowData.MANUAL_UNRES_FLAG);
        $("#restraintClass").val(rowData.RESTRAINT_CLASS);
        $("#restraintDesc").val(rowData.RESTRAINT_DESC);
        $("#stopwtdfalg").val(rowData.STOP_WTD_FALG);
    }

    $("#fmRestraintTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmRestraintTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmRestraintTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_RESTRAINT_TYPE";
    keyFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.AH_BU=$("#ahBu").val();
    generalFieldsJson.SYSTEM_USE_FLAG=$("systemUserFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.EOD_IMPOUND_FALG=$("#eodImpoundFalg").val();
    generalFieldsJson.MANUAL_RES_FLAG=$("#manualResFlag").val();
    generalFieldsJson.MANUAL_UNRES_FLAG=$("#manualUnresFlag").val();
    generalFieldsJson.RESTRAINT_CLASS=$("#restraintClass").val();
    generalFieldsJson.RESTRAINT_DESC=$("#restraintDesc").val();
    generalFieldsJson.STOP_WTD_FALG=$("#stopwtdfalg").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmRestraintTypeMod,"json");
}

function callback_fmRestraintTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmRestraintType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            RESTRAINT_TYPE:$("#restraintType").val(),
            AH_BU:$("#ahBu").val(),
            SYSTEM_USE_FLAG:$("#systemUserFlag").val(),
            COMPANY:$("#company").val(),
            EOD_IMPOUND_FALG:$("#eodImpoundFalg").val(),
            MANUAL_RES_FLAG:$("#manualResFlag").val(),
            MANUAL_UNRES_FLAG:$("#manualUnresFlag").val(),
            RESTRAINT_CLASS:$("#restraintClass").val(),
            RESTRAINT_DESC:$("#restraintDesc").val(),
            STOP_WTD_FALG:$("#stopwtdfalg").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmRestraintTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

