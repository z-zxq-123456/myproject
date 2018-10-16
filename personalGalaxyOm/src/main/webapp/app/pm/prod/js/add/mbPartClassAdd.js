
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
 $("#partClass").blur(function(){
            if($("#partClass").val() === ""){
              return;
            }else{
                     var url = contextPath + "/part/getpartKey";
                     $.ajax({
                         url: url,
                         data: "partClass=" + $("#partClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#partClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json"
                     });
             }
    });
 	$('#partClassLevel').change(function(){
 		var value=$('#partClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentPartClass?partClassLevel="+value,
                          id: "parentPartClass",
                          async: false
                             });
 	});

	$("#mbPartClassAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbPartClassAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbPartClassAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PART_CLASS";
		keyFieldsJson.PART_CLASS=$("#partClass").val();
		generalFieldsJson.PART_CLASS_DESC=$("#partClassDesc").val();
		generalFieldsJson.PARENT_PART_CLASS=$("#parentPartClass").val();
		generalFieldsJson.PART_CLASS_LEVEL=$("#partClassLevel").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbPartClassAdd,"json");
}

function callback_mbPartClassAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbPartClassAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


