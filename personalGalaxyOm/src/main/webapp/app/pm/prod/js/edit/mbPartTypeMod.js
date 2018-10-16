
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PART_CLASS&tableCol=PART_CLASS,PART_CLASS_DESC",
        id: "partClass",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_CLASS&tableCol=PROD_CLASS,PROD_CLASS_DESC",
        id: "busiCategory",
        async: false
    });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbPartType").find(".selected").length===1){
        rowData = parent.$('#mbPartType').DataTable().rows(".selected").data()[0];
        $("#partType").val(rowData.PART_TYPE).attr("disabled",true);
        $("#isStandard").val(rowData.IS_STANDARD);
        $("#partClass").val(rowData.PART_CLASS);
        $("#partDesc").val(rowData.PART_DESC);
        $("#busiCategory").val(rowData.BUSI_CATEGORY);
        $("#defaultPart").val(rowData.DEFAULT_PART);
        $("#processMethod").val(rowData.PROCESS_METHOD);
        $("#status").val(rowData.STATUS);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbPartTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbPartTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbPartTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PART_TYPE";
    keyFieldsJson.PART_TYPE=$("#partType").val();
    generalFieldsJson.IS_STANDARD=$("#isStandard").val();
    generalFieldsJson.PART_CLASS=$("#partClass").val();
    generalFieldsJson.PART_DESC=$("#partDesc").val();
    generalFieldsJson.BUSI_CATEGORY=$("#busiCategory").val();
    generalFieldsJson.DEFAULT_PART=$("#defaultPart").val();
    generalFieldsJson.PROCESS_METHOD=$("#processMethod").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbPartTypeMod,"json");
}

function callback_mbPartTypeMod(json){
    if (json.success) {
       if (parent.$("#mbPartType").find(".selected").length===1){
        rowData = parent.$('#mbPartType').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbPartType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PART_TYPE:$("#partType").val(),
            IS_STANDARD:$("#isStandard").val(),
            PART_CLASS:$("#partClass").val(),
            PART_DESC:$("#partDesc").val(),
            BUSI_CATEGORY:$("#busiCategory").val(),
            DEFAULT_PART:$("#defaultPart").val(),
            PROCESS_METHOD:$("#processMethod").val(),
            STATUS:$("#status").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbPartTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

