
var rowData;
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_RATE&tableCol=IRL_SEQ_NO,IRL_SEQ_NO",
				id: "irlSeqNo",
				async: false
			});
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
                id: "intType",
                async: false
            });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlFeeMatrix").find(".selected").length===1){
        rowData = parent.$('#irlFeeMatrix').DataTable().rows(".selected").data()[0];
            $("#matrixNo").val(rowData.MATRIX_NO).attr("disabled",true);
            $("#irlSeqNo").val(rowData.IRL_SEQ_NO);
            $("#feeAmt").val(rowData.FEE_AMT);
            $("#boundary").val(rowData.BOUNDARY);
            $("#feeRate").val(rowData.FEE_RATE);
            $("#floatRate").val(rowData.FLOAT_RATE);
            $("#intType").val(rowData.INT_TYPE);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlFeeMatrixMod").Validform({
        tiptype:2,
        callback:function(form){
            irlFeeMatrixMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeeMatrixMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_FEE_MATRIX";
        keyFieldsJson.MATRIX_NO=$("#matrixNo").val();
        generalFieldsJson.IRL_SEQ_NO=$("#irlSeqNo").val();
        generalFieldsJson.FEE_AMT=$("#feeAmt").val();
        generalFieldsJson.BOUNDARY=$("#boundary").val();
        generalFieldsJson.FEE_RATE=$("#feeRate").val();
        generalFieldsJson.FLOAT_RATE=$("#floatRate").val();
        generalFieldsJson.INT_TYPE=$("#intType").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeeMatrixMod,"json");
}

function callback_irlFeeMatrixMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlFeeMatrix").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            MATRIX_NO:$("matrixNo").val(),
            IRL_SEQ_NO:$("irlSeqNo").val(),
            FEE_AMT:$("feeAmt").val(),
            BOUNDARY:$("boundary").val(),
            FEE_RATE:$("feeRate").val(),
            FLOAT_RATE:$("floatRate").val(),
            INT_TYPE:$("intType").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeeMatrixModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

