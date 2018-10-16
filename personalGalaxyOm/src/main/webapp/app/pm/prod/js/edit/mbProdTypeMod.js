
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_CLASS&tableCol=PROD_CLASS,PROD_CLASS_DESC",
        id: "prodClass",
        async: false
    });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbProdType").find(".selected").length===1){
        rowData = parent.$('#mbProdType').DataTable().rows(".selected").data()[0];
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#prodClass").val(rowData.PROD_CLASS);
        $("#prodDesc").val(rowData.PROD_DESC);
        $("#status").val(rowData.STATUS);
        $("#baseProdType").val(rowData.BASE_PROD_TYPE);
        $("#prodGroup").val(rowData.PROD_GROUP);
        $("#prodRange").val(rowData.PROD_RANGE);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbProdTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbProdTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_TYPE";
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.PROD_CLASS=$("#prodClass").val();
    generalFieldsJson.PROD_DESC=$("#prodDesc").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.BASE_PROD_TYPE=$("#baseProdType").val();
    generalFieldsJson.PROD_GROUP=$("#prodGroup").val();
    generalFieldsJson.PROD_RANGE=$("#prodRange").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdTypeMod,"json");
}

function callback_mbProdTypeMod(json){
    if (json.success) {
       if (parent.$("#mbProdType").find(".selected").length===1){
        rowData = parent.$('#mbProdType').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbProdType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROD_TYPE:$("#prodType").val(),
            PROD_CLASS:$("#prodClass").val(),
            PROD_DESC:$("#prodDesc").val(),
            STATUS:$("#status").val(),
            BASE_PROD_TYPE:$("#baseProdType").val(),
            PROD_GROUP:$("#prodGroup").val(),
            PROD_RANGE:$("#prodRange").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

