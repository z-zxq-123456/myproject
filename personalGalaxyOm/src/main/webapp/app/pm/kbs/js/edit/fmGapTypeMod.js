
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=KBS_FM_MODULE&tableCol=MODULE_ID,MODULE_NAME",
        id: "prodGrp",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#fmGapType").find(".selected").length===1){
        rowData = parent.$('#fmGapType').DataTable().rows(".selected").data()[0];
        $("#gapType").val(rowData.GAP_TYPE).attr("disabled",true);
        $("#gapStart").val(rowData.GAP_START);
        $("#workingDays").val(rowData.WORKING_DAYS);
        $("#gapTypeDesc").val(rowData.GAP_TYPE_DESC);
        $("#company").val(rowData.COMPANY);
        $("#prodGrp").val(rowData.PROD_GRP);
        $("#ccy").val(rowData.CCY);
    }

    $("#fmGapTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            fmGapTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmGapTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_GAP_TYPE";
    keyFieldsJson.GAP_TYPE=$("#gapType").val();
    generalFieldsJson.GAP_START=$("#gapStart").val();
    generalFieldsJson.WORKING_DAYS=$("#workingDays").val();
    generalFieldsJson.GAP_TYPE_DESC=$("#gapTypeDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PROD_GRP=$("#prodGrp").val();
    generalFieldsJson.CCY=$("#ccy").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmGapTypeMod,"json");
}

function callback_fmGapTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmGapType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        GAP_TYPE:$("#gapType").val()
        ,GAP_START:$("#gapStart").val()
        ,WORKING_DAYS:$("#workingDays").val()
        ,GAP_TYPE_DESC:$("#gapTypeDesc").val()
        ,COMPANY:$("#company").val()
        ,PROD_GRP:$("#prodGrp").val()
        ,CCY:$("#ccy").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmGapTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

