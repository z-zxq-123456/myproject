
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbProdDefine").find(".selected").length===1){
        rowData = parent.$('#mbProdDefine').DataTable().rows(".selected").data()[0];
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#seqNo").val(rowData.SEQ_NO).attr("disabled",true);
        $("#assembleType").val(rowData.ASSEMBLE_TYPE);
        $("#status").val(rowData.STATUS);
        $("#assembleId").val(rowData.ASSEMBLE_ID);
        $("#attrKey").val(rowData.ATTR_KEY);
        $("#attrValue").val(rowData.ATTR_VALUE);
        $("#eventDefault").val(rowData.EVENT_DEFAULT);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbProdDefineMod").Validform({
        tiptype:2,
        callback:function(form){
            mbProdDefineMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdDefineMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_DEFINE";
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    generalFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
    generalFieldsJson.ATTR_KEY=$("#attrKey").val();
    generalFieldsJson.ATTR_VALUE=$("#attrValue").val();
    generalFieldsJson.EVENT_DEFAULT=$("#eventDefault").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdDefineMod,"json");
}

function callback_mbProdDefineMod(json){
    if (json.success) {
       if (parent.$("#mbProdDefine").find(".selected").length===1){
        rowData = parent.$('#mbProdDefine').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbProdDefine").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROD_TYPE:$("#prodType").val(),
            SEQ_NO:$("#seqNo").val(),
            ASSEMBLE_TYPE:$("#assembleType").val(),
            STATUS:$("#status").val(),
            ASSEMBLE_ID:$("#assembleId").val(),
            ATTR_KEY:$("#attrKey").val(),
            ATTR_VALUE:$("#attrValue").val(),
            EVENT_DEFAULT:$("#eventDefault").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdDefineModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

