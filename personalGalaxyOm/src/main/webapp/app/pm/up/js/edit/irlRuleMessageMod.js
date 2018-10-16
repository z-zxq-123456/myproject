
var form;
$(document).ready(function() {
        var rowData;
	if (parent.$("#irlRuleMessage").find(".selected").length===1){
		rowData = parent.$('#irlRuleMessage').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		var ruleClass1 = rowData.RULE_CLASS_1
		if(ruleClass1==="INRATE"){
			$("#float0").show();
			$("#float1").show();
		}else {
			$("#float0").hide();
			$("#float1").hide();
			if(ruleClass1==="FEE"){
			 $("#floatValue").attr("disabled","disabled");
			}
		}
		$("#irlSeqNo").val(rowData.IRL_SEQ_NO).attr("disabled","disabled");
		$("#ruleStatus").val(rowData.RULE_STATUS);
		$("#ruleDesc").val(rowData.RULE_DESC).attr("disabled","disabled");
		$("#startDateTime").val(rowData.START_DATE_TIME);
        $("#endDateTime").val(rowData.END_DATE_TIME);
        $("#intFloatType").val(rowData.INT_FLOAT_TYPE);
        $("#floatValue").val(rowData.FLOAT_VALUE);
		form = $("#form-rules-edit").Validform({
			tiptype:2,
			callback:function(form){
				getRulesEdit();
				return false;
			}
		});
	}
});

function getRulesEdit(){
		var paraJson, generalFieldsJson, keyFieldsJson,row;
        paraJson = {};
        generalFieldsJson = {};
        keyFieldsJson = {};
        row = parent.$('#irlRuleMessage').DataTable().rows(".selected").data()[0];
        var url = contextPath + "/baseCommon/updateAndInsertForUpdate";
        paraJson.tableName = "IRL_RULE_MESSAGE";
        keyFieldsJson.IRL_SEQ_NO=row.IRL_SEQ_NO;
		generalFieldsJson.RULE_STATUS=row.RULE_STATUS;
		generalFieldsJson.RULE_CLASS_2=row.RULE_CLASS_2;
		generalFieldsJson.RULE_CLASS_3=row.RULE_CLASS_3;
		generalFieldsJson.CREATE_DATE=row.CREATE_DATE;
		generalFieldsJson.COMPANY=row.COMPANY;
		generalFieldsJson.IMPORT_MESSAGE=row.IMPORT_MESSAGE;
		generalFieldsJson.RULE_DESC=row.RULE_DESC;
		generalFieldsJson.RULE_EXPRESS=row.RULE_EXPRESS;
		generalFieldsJson.SPECIAL_RULE_PROCESS=row.SPECIAL_RULE_PROCESS;
		generalFieldsJson.USER_ID=row.USER_ID;
		generalFieldsJson.RULE_CLASS_1=row.RULE_CLASS_1;
		generalFieldsJson.GROUP_CCY=row.GROUP_CCY;
		generalFieldsJson.RULE_WEIGHT=row.RULE_WEIGHT;
		generalFieldsJson.START_DATE_TIME=$("#startDateTime").val();
		generalFieldsJson.END_DATE_TIME=$("#endDateTime").val();
		generalFieldsJson.INT_FLOAT_TYPE=$("#intFloatType").val();
		generalFieldsJson.FLOAT_VALUE=$("#floatValue").val();
		generalFieldsJson.RULE_FLAG="S";
        paraJson.key = keyFieldsJson;
        paraJson.general = generalFieldsJson;
        var params = {
            paraJson: JSON.stringify(paraJson)
        };
        sendPostRequest(url, params, callback_rulesEdit, "json");
}

function callback_rulesEdit(json){
	if (json.success) {
		var dataTable=parent.$("#irlRuleMessage").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			IRL_SEQ_NO:$("#irlSeqNo").val(),
		RULE_STATUS:$("#ruleStatus").val(),
		RULE_CLASS_2:$("#ruleClass2").val(),
		RULE_CLASS_3:$("#ruleClass3").val(),
		CREATE_DATE:$("#createDate").val(),
		COMPANY:$("#company").val(),
		IMPORT_MESSAGE:$("#importMessage").val(),
		RULE_DESC:$("#ruledesc").val(),
		RULE_EXPRESS:$("#ruleexpress").val(),
		SPECIAL_RULE_PROCESS:$("#specialRuleProcess").val(),
		USER_ID:$("#userId").val(),
		RULE_CLASS_1:$("#ruleClass1").val(),
		GROUP_CCY:$("#groupCcy").val(),
		RULE_WEIGHT:$("#ruleWeight").val(),
		START_DATE_TIME:$("#startDateTime").val(),
		END_DATE_TIME:$("#endDateTime").val(),
		INT_FLOAT_TYPE:$("#intFloatType").val(),
		FLOAT_VALUE:$("#floatValue").val(),
		RULE_FLAG:$("#floatValue").val(),
			COLUMN_STATUS:'W'
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlRuleMessageModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

