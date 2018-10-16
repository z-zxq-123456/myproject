
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
 	$('#prodClassLevel').change(function(){
 		var value=$('#prodClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentProdClass?prodClassLevel="+value,
                          id: "parentProdClass",
                          async: false
                             });
 	});

    $("#prodClass").blur(function(){
       if($("#prodClass") === ""){
        return;
       }else{
                     var url = contextPath + "/prod/getprodKey";
                     $.ajax({
                         url: url,
                         data: "prodClass=" + $("#prodClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#prodClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json"
                     });
        }
    });

	$("#mbProdClassAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbProdClassAdd();
			return false;
		}
	});

	$(".select2").select2();
});
function mbProdClassAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_CLASS";
		keyFieldsJson.PROD_CLASS=$("#prodClass").val();
		generalFieldsJson.PROD_CLASS_DESC=$("#prodClassDesc").val();
		generalFieldsJson.PARENT_PROD_CLASS=$("#parentProdClass").val();
		generalFieldsJson.PROD_CLASS_LEVEL=$("#prodClassLevel").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdClassAdd,"json");
}

function callback_mbProdClassAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdClassAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


