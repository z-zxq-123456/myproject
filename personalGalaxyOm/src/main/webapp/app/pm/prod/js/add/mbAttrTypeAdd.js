
$(document).ready(function() {
    getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "busiCategory",
                 async: false
                     });
	getPkList({
		url:contextPath+"/attrType/getForPkList",
		id:"attrClass",
		async:false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#attrKey").blur(function(){
			if($("#attrKey").val() === ""){
			  return;
			}else{
					 var url = contextPath + "/attrType/getOne";
					 $.ajax({
						 url: url,
						 data: "attrKey=" + $("#attrKey").val(),
						 success: function(json) {
							 if (json.retStatus === 'F') {
								   showMsg(json.retMsg, 'info');
									$("#attrKey").val("");
							 } else if (json.retStatus === 'S') {
								  return;
							 }
						 },
						 dataType: "json"
					 });
			}
	});
	$("#mbAttrTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAttrTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAttrTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ATTR_TYPE";
		keyFieldsJson.ATTR_KEY=$("#attrKey").val();
		generalFieldsJson.ATTR_CLASS=$("#attrClass").val();
		generalFieldsJson.ATTR_DESC=$("#attrDesc").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.VALUE_METHOD=$("#valueMethod").val();
		generalFieldsJson.ATTR_TYPE=$("#attrType").val();
		if($("#busiCategory").val()!==null){
		generalFieldsJson.BUSI_CATEGORY=$("#busiCategory").val().toString();
		}
		generalFieldsJson.SET_VALUE_FLAG=$("#setValueFlag").val();
		generalFieldsJson.USE_METHOD=$("#useMethod").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAttrTypeAdd,"json");
}

function callback_mbAttrTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAttrTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


