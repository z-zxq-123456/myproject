
$(document).ready(function() {
	getPkList({  url: contextPath + "/pklist/getattrKey1",
                     id: "attrKey",
                     async: false
                         });
	$("#mbAttrValueAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAttrValueAdd();
			return false;
		}
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#attrKey").change(function(){
                       if($("#attrKey").val()  ==="" )  {
                                 return;
                       }else{
                         var url = contextPath + "/attrType/getattrKey";
                         $.ajax({
                             url: url,
                             data: "attrKey=" + $("#attrKey").val(),
                             success: function(json) {
                                 if (json.retStatus === 'F') {
                                       showMsg(json.retMsg, 'info');
                                        $("#attrKey").val("");
                                 } else if (json.retStatus === 'S') {
                                     	 if(json.data.valueMethod==="VL"){
                                     	   $("#refTable").attr("disabled",false);
                                     	   $("#refCondition").attr("disabled",false);
                                           $("#refColumns").attr("disabled",false);
                                     	   $("#attrValue").attr("disabled",false);
                                     	   $("#valueDesc").attr("disabled",false);
                                           $("#attrValue").attr("datatype","*1-40");
                                           $("#valueDesc").attr("datatype","*1-40");
                                     	   $("#refTable").attr("disabled",true);
                                     	   $("#refCondition").attr("disabled",true);
                                     	   $("#refColumns").attr("disabled",true);
                                         }else if(json.data.valueMethod==="RF"){
                                     	   $("#refTable").attr("disabled",false);
                                           $("#refCondition").attr("disabled",false);
                                           $("#refColumns").attr("disabled",false);
    									   $("#attrValue").val(" ");
    									   $("#valueDesc").val(" ");
                                     	   $("#refTable").attr("datatype","*1-40");
                                     	   $("#refCondition").attr("datatype","*1-40");
                                     	   $("#refColumns").attr("datatype","*1-40");
                                         }
                                        return;
                                 }
                             },
                             dataType: "json"
                         });
                         }
        });
	$(".select2").select2();
});

function mbAttrValueAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ATTR_VALUE";
		keyFieldsJson.ATTR_KEY=$("#attrKey").val();
		keyFieldsJson.ATTR_VALUE=$("#attrValue").val();
		generalFieldsJson.VALUE_DESC=$("#valueDesc").val();
		generalFieldsJson.REF_COLUMNS=$("#refColumns").val();
		generalFieldsJson.REF_CONDITION=$("#refCondition").val();
		generalFieldsJson.REF_TABLE=$("#refTable").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAttrValueAdd,"json");
}

function callback_mbAttrValueAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAttrValueAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


