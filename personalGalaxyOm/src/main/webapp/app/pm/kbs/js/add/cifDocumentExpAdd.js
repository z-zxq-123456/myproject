$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
        id: "sourceTypeRule",
        async: false
    });
	// getPkList({
	// 	url: contextPath + "/baseCommon/pklistBase?tableName=FM_RESTRAINT_TYPE&tableCol=RESTRAINT_TYPE,RESTRAINT_DESC",
	// 	id: "restraintType",
	// 	async: false
	// });
    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    paraJson.tableName = "FM_RESTRAINT_TYPE";
    paraJson.tableCol="RESTRAINT_TYPE,RESTRAINT_DESC";

    //keyFieldsJson.RESTRAINT_CLASS = "DQ";
    //paraJson.key = keyFieldsJson;
    getPkList({
        url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
        id: "restraintType",
        async: false

    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_DOCUMENT_TYPE&tableCol=DOCUMENT_TYPE,DOCUMENT_TYPE_DESC",
        id: "documentType",
        async: false
    });

	$("#cifDocumentExpAdd").Validform({
		tiptype:2,
		callback:function(){
			cifDocumentExpAdd();
			return false;
		}
	});

    $('#stopFlag').change(function(){
        var value=$('#stopFlag').val();
        if(value=="N"){
            $("#restraintType").attr("disabled",true);
        }
        else if(value=="Y"){
            $("#restraintType").attr("disabled",false);
        }
    });

	$(".select2").select2();
});

function cifDocumentExpAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_DOCUMENT_EXP";
    keyFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
    generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
    generalFieldsJson.STOP_FLAG=$("#stopFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DAYS=$("#days").val();
    generalFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.SOURCE_TYPE_RULE=$("#sourceTypeRule").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifDocumentExpAdd,"json");
}

function callback_cifDocumentExpAdd(json) {
    if (json.success) {
        parent.showMsgDuringTime("添加成功");
    }
}