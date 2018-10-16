
$(document).ready(function() {
 	$('#eventClassLevel').change(function(){
 		var value=$('#eventClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentEventClass?eventClassLevel="+value,
                          id: "parentEventClass",
                          async: false
                             });
 	});

	$("#mbEventClassAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventClassAdd();
			return false;
		}
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$(".select2").select2();
});

function mbEventClassAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_CLASS";
		keyFieldsJson.EVENT_CLASS=$("#eventClass").val();
		generalFieldsJson.EVENT_CLASS_DESC=$("#eventClassDesc").val();
		generalFieldsJson.EVENT_CLASS_LEVEL=$("#eventClassLevel").val();
		if($("#parentEventClass").val()!=null){
		generalFieldsJson.PARENT_EVENT_CLASS=$("#parentEventClass").val().toString();
		}
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventClassAdd,"json");
}

function callback_mbEventClassAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventClassAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


