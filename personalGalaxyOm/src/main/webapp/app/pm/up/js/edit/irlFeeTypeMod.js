
var rowData;
$(document).ready(function() {
  var paraJson, keyFieldsJson;
            				  paraJson = {};
            				  keyFieldsJson = {};
            				  paraJson.tableName = "FM_REF_CODE";
            				  paraJson.tableCol="FIELD_VALUE,MEANING";
            				  keyFieldsJson.DOMAIN = "PROD_GRP";
            				  paraJson.key = keyFieldsJson;
            		   getPkList({
            							url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
            									   id: "prodGrp",
            									   async: false

            					  });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
                id: "taxType",
                async: false
            });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_ITEM&tableCol=FEE_ITEM,FEE_ITEM_DESC",
        id: "feeItem",
        async: false
    });

    $('#prodGrp').append("<option value='ALL'>ALL-全部</option>");
    if (parent.$("#irlFeeType").find(".selected").length===1){
        rowData = parent.$('#irlFeeType').DataTable().rows(".selected").data()[0];
            $("#feeType").val(rowData.FEE_TYPE).attr("disabled",true);
            $("#feeMode").val(rowData.FEE_MODE);
            $("#feeDesc").val(rowData.FEE_DESC);
            $("#prodGrp").val(rowData.PROD_GRP);
            $("#disType").val(rowData.DIS_TYPE);
            $("#ccyFlag").val(rowData.CCY_FLAG);
            $("#boInd").val(rowData.BO_IND);
            $("#mbCcyType").val(rowData.MB_CCY_TYPE);
            $("#feeAmtId").val(rowData.FEE_AMT_ID);
            $("#convertFlag").val(rowData.CONVERT_FLAG);
            $("#company").val(rowData.COMPANY);
            $("#collectionType").val(rowData.COLLECTION_TYPE);            $("#boundaryDesc").val(rowData.BOUNDARY_DESC);
            $("#boundaryAmtId").val(rowData.BOUNDARY_AMT_ID);
            $("#taxType").val(rowData.TAX_TYPE);
    }

    $("#irlFeeTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            irlFeeTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_TYPE";
        keyFieldsJson.FEE_TYPE=$("#feeType").val();
        generalFieldsJson.FEE_MODE=$("#feeMode").val();
        generalFieldsJson.FEE_DESC=$("#feeDesc").val();
        generalFieldsJson.PROD_GRP=$("#prodGrp").val();
        generalFieldsJson.DIS_TYPE=$("#disType").val();
        generalFieldsJson.CCY_FLAG=$("#ccyFlag").val();
        generalFieldsJson.BO_IND=$("#boInd").val();
        generalFieldsJson.MB_CCY_TYPE=$("#mbCcyType").val();
        generalFieldsJson.FEE_AMT_ID=$("#feeAmtId").val();
        generalFieldsJson.CONVERT_FLAG=$("#convertFlag").val();
        generalFieldsJson.COMPANY=$("#company").val();
        generalFieldsJson.BOUNDARY_DESC=$("#boundaryDesc").val();
        generalFieldsJson.BOUNDARY_AMT_ID=$("#boundaryAmtId").val();
        generalFieldsJson.TAX_TYPE=$("#taxType").val();
        generalFieldsJson.FEE_ITEM=$("#feeItem").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeTypeMod,"json");
}

function callback_irlFeeTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlFeeType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            FEE_TYPE:$("feeType").val(),
            FEE_MODE:$("feeMode").val(),
            FEE_DESC:$("feeDesc").val(),
            PROD_GRP:$("prodGrp").val(),
            DIS_TYPE:$("disType").val(),
            CCY_FLAG:$("highLimit").val(),
            BO_IND:$("boInd").val(),
            MB_CCY_TYPE:$("mbCcyType").val(),
            FEE_AMT_ID:$("feeAmtId").val(),
            CONVERT_FLAG:$("convertFlag").val(),
            COMPANY:$("company").val(),
            BOUNDARY_DESC:$("boundaryDesc").val(),
            BOUNDARY_AMT_ID:$("boundaryAmtId").val(),
            TAX_TYPE:$("taxType").val(),
            FEE_ITEM:$("feeItem").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

