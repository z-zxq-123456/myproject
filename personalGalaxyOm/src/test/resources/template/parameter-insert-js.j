$(document).ready(function() {
<#list insertCol as insert>
<#if insert.valueMethod == 'RF'>
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=${insert.refTable}&tableCol=${insert.refCol}",
		id: "${insert.paramName}",
		async: false
	});
</#if>
</#list>

	$("#${beanName}Add").Validform({
		tiptype:2,
		callback:function(){
			${beanName}Add();
			return false;
		}
	});

	$(".select2").select2();
});

function ${beanName}Add(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="${TableName}";
<#list pks as pk>
    keyFieldsJson.${pk.colName}=$("#${pk.paramName}").val();
</#list>
<#list updateCol as update>
    generalFieldsJson.${update.colName}=$("#${update.paramName}").val();
</#list>
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_${beanName}Add,"json");
}

function callback_${beanName}Add(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function ${beanName}AddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}