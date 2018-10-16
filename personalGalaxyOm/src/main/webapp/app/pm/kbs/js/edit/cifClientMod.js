
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
        id: "categoryType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_STATE&tableCol=STATE,STATE_DESC",
        id: "stateLoc",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
        id: "countryRisk",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
        id: "countryLoc",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COUNTRY&tableCol=COUNTRY,COUNTRY_DESC",
        id: "countryCitizen",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "clientType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CR_RATING&tableCol=CR_RATING,CR_RATING_DESC",
        id: "crRating",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_DOCUMENT_TYPE&tableCol=DOCUMENT_TYPE,DOCUMENT_TYPE",
        id: "globalIdType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_STATUS&tableCol=CLIENT_STATUS,CLIENT_STATUS_DESC",
        id: "clientStatus",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClient").find(".selected").length===1){
        rowData = parent.$('#cifClient').DataTable().rows(".selected").data()[0];
        $("#clientNo").val(rowData.CLIENT_NO).attr("disabled",true);
        $("#tranStatus").val(rowData.TRAN_STATUS);
        $("#acctExec").val(rowData.ACCT_EXEC);
        $("#categoryType").val(rowData.CATEGORY_TYPE);
        $("#stateLoc").val(rowData.STATE_LOC);
        $("#countryRisk").val(rowData.COUNTRY_RISK);
        $("#countryLoc").val(rowData.COUNTRY_LOC);
        $("#countryCitizen").val(rowData.COUNTRY_CITIZEN);
        $("#clientType").val(rowData.CLIENT_TYPE);
        $("#clientShort").val(rowData.CLIENT_SHORT);
        $("#inlandOffshore").val(rowData.INLAND_OFFSHORE);
        $("#location").val(rowData.LOCATION);
        $("#tempClient").val(rowData.TEMP_CLIENT);
        $("#crRating").val(rowData.CR_RATING);
        $("#ctrlBranch").val(rowData.CTRL_BRANCH);
        $("#clientCity").val(rowData.CLIENT_CITY);
        $("#globalIdType").val(rowData.GLOBAL_ID_TYPE);
        $("#clientStatus").val(rowData.CLIENT_STATUS);
        $("#globalId").val(rowData.GLOBAL_ID);
        $("#chClientName").val(rowData.CH_CLIENT_NAME);
        $("#clientName").val(rowData.CLIENT_NAME);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClientMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClientMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClientMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLIENT";
    keyFieldsJson.CLIENT_NO=$("#clientNo").val();
    generalFieldsJson.TRAN_STATUS=$("#tranStatus").val();
    generalFieldsJson.ACCT_EXEC=$("#acctExec").val();
    generalFieldsJson.CATEGORY_TYPE=$("#categoryType").val();
    generalFieldsJson.STATE_LOC=$("#stateLoc").val();
    generalFieldsJson.COUNTRY_RISK=$("#countryRisk").val();
    generalFieldsJson.COUNTRY_LOC=$("#countryLoc").val();
    generalFieldsJson.COUNTRY_CITIZEN=$("#countryCitizen").val();
    generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
    generalFieldsJson.CLIENT_SHORT=$("#clientShort").val();
    generalFieldsJson.INLAND_OFFSHORE=$("#inlandOffshore").val();
    generalFieldsJson.LOCATION=$("#location").val();
    generalFieldsJson.TEMP_CLIENT=$("#tempClient").val();
    generalFieldsJson.CR_RATING=$("#crRating").val();
    generalFieldsJson.CTRL_BRANCH=$("#ctrlBranch").val();
    generalFieldsJson.CLIENT_CITY=$("#clientCity").val();
    generalFieldsJson.GLOBAL_ID_TYPE=$("#globalIdType").val();
    generalFieldsJson.CLIENT_STATUS=$("#clientStatus").val();
    generalFieldsJson.GLOBAL_ID=$("#globalId").val();
    generalFieldsJson.CH_CLIENT_NAME=$("#chClientName").val();
    generalFieldsJson.CLIENT_NAME=$("#clientName").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClientMod,"json");
}

function callback_cifClientMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClient").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        CLIENT_NO:$("#clientNo").val()
        ,TRAN_STATUS:$("#tranStatus").val()
        ,ACCT_EXEC:$("#acctExec").val()
        ,CATEGORY_TYPE:$("#categoryType").val()
        ,STATE_LOC:$("#stateLoc").val()
        ,COUNTRY_RISK:$("#countryRisk").val()
        ,COUNTRY_LOC:$("#countryLoc").val()
        ,COUNTRY_CITIZEN:$("#countryCitizen").val()
        ,CLIENT_TYPE:$("#clientType").val()
        ,CLIENT_SHORT:$("#clientShort").val()
        ,INLAND_OFFSHORE:$("#inlandOffshore").val()
        ,LOCATION:$("#location").val()
        ,TEMP_CLIENT:$("#tempClient").val()
        ,CR_RATING:$("#crRating").val()
        ,CTRL_BRANCH:$("#ctrlBranch").val()
        ,CLIENT_CITY:$("#clientCity").val()
        ,GLOBAL_ID_TYPE:$("#globalIdType").val()
        ,CLIENT_STATUS:$("#clientStatus").val()
        ,GLOBAL_ID:$("#globalId").val()
        ,CH_CLIENT_NAME:$("#chClientName").val()
        ,CLIENT_NAME:$("#clientName").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClientModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

