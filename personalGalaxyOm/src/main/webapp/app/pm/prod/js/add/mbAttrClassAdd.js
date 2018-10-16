
$(document).ready(function() {

	$("#mbAttrClassAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbAttrClassAdd();
			return false;
		}
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$(".select2").select2();

	    $("#attrClass").blur(function(){
                if($("#attrClass").val() === ""){
                   return;
                }else{
                         var url = contextPath + "/attr/getOne";
                         $.ajax({
                             url: url,
                             data: "attrClass=" + $("#attrClass").val(),
                             success: function(json) {
                                 if (json.retStatus === 'F') {
                                       showMsg(json.retMsg, 'info');
                                        $("#attrClass").val("");
                                 } else if (json.retStatus === 'S') {
                                      return;
                                 }
                             },
                             dataType: "json"
                         });
                }
        });
		$('#attrClassLevel').change(function(){
			var value=$('#attrClassLevel').val();
			 getPkList({  url: contextPath + "/pklist/getparentAttrClass?attrClassLevel="+value,
							 id: "parentAttrClass",
							 async: false
								});
		});
});

function mbAttrClassAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ATTR_CLASS";
		keyFieldsJson.ATTR_CLASS=$("#attrClass").val();
		generalFieldsJson.ATTR_CLASS_DESC=$("#attrClassDesc").val();
		generalFieldsJson.ATTR_CLASS_LEVEL=$("#attrClassLevel").val();
		generalFieldsJson.PARENT_ATTR_CLASS=$("#parentAttrClass").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAttrClassAdd,"json");
}

function callback_mbAttrClassAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAttrClassAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


