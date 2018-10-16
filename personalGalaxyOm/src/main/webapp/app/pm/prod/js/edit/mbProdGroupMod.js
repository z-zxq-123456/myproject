
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbProdGroup").find(".selected").length===1){
        rowData = parent.$('#mbProdGroup').DataTable().rows(".selected").data()[0];
        $("#prodSubType").val(rowData.PROD_SUB_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#seqNo").val(rowData.SEQ_NO);
        $("#defaultProd").val(rowData.DEFAULT_PROD);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbProdGroupMod").Validform({
        tiptype:2,
        callback:function(form){
            mbProdGroupMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdGroupMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_GROUP";
    keyFieldsJson.PROD_SUB_TYPE=$("#prodSubType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.SEQ_NO=$("#seqNo").val();
    generalFieldsJson.DEFAULT_PROD=$("#defaultProd").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdGroupMod,"json");
}

function callback_mbProdGroupMod(json){
    if (json.success) {
       if (parent.$("#mbProdGroup").find(".selected").length===1){
        rowData = parent.$('#mbProdGroup').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbProdGroup").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROD_SUB_TYPE:$("#prodSubType").val(),
            PROD_TYPE:$("#prodType").val(),
            SEQ_NO:$("#seqNo").val(),
            DEFAULT_PROD:$("#defaultProd").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdGroupModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

