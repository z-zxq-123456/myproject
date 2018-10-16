$(document).ready(function() {
	$("#vryBitMeth").val("0").select2().attr("disabled",true);
	$("#fldsign1").val("1").select2().attr("disabled",true);
	$("#fldsign6").val("1").select2().attr("disabled",true);
	 getPkList({
	        url: contextPath + "/cmcRuleNo/getAllForSelect?tableCol=RULE_NO",
	        id: "cardNoRoleCode",
	        async: false
	    });
	 
	$("#cmcCardNoRoleInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardNoRoleInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardNoRoleInfoAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_NO_ROLE_INFO";
    keyFieldsJson.CARD_NO_ROLE_CODE=$("#cardNoRoleCode").val();
    generalFieldsJson.LEN_OF_CARD_NO=$("#lenOfCardNo").val();
    generalFieldsJson.CARD_GEN_ID=$("#cardGenId").val();
    generalFieldsJson.SEQ_NO_LEN=$("#seqNoLen").val();
    generalFieldsJson.FLDNUM1=$("#fldnum1").val();
    generalFieldsJson.FLDSRCTBL1=$("#fldsrctbl1").val();
    generalFieldsJson.FLDSRCFLD1=$("#fldsrcfld1").val();
    generalFieldsJson.FLDLEN1=$("#fldlen1").val();
    generalFieldsJson.FLDWHR1=$("#fldwhr1").val();
    generalFieldsJson.FLDSIGN1=$("#fldsign1").val();
    /* generalFieldsJson.FLDSRCTBL2=$("#fldsrctbl2").val();
     generalFieldsJson.FLDSRCFLD2=$("#fldsrcfld2").val();
     generalFieldsJson.FLDLEN2=$("#fldlen2").val();
     generalFieldsJson.FLDWHR2=$("#fldwhr2").val();
     generalFieldsJson.FLDSIGN2=$("#fldsign2").val();
     generalFieldsJson.FLDSRCTBL3=$("#fldsrctbl3").val();
     generalFieldsJson.FLDSRCFLD3=$("#fldsrcfld3").val();
     generalFieldsJson.FLDLEN3=$("#fldlen3").val();
     generalFieldsJson.FLDWHR3=$("#fldwhr3").val();
     generalFieldsJson.FLDSIGN3=$("#fldsign3").val();
     generalFieldsJson.FLDSRCTBL4=$("#fldsrctbl4").val();
     generalFieldsJson.FLDSRCFLD4=$("#fldsrcfld4").val();
     generalFieldsJson.FLDLEN4=$("#fldlen4").val();
     generalFieldsJson.FLDWHR4=$("#fldwhr4").val();
     generalFieldsJson.FLDSIGN4=$("#fldsign4").val();
     generalFieldsJson.FLDSRCTBL5=$("#fldsrctbl5").val();
     generalFieldsJson.FLDSRCFLD5=$("#fldsrcfld5").val();
     generalFieldsJson.FLDLEN5=$("#fldlen5").val();
     generalFieldsJson.FLDWHR5=$("#fldwhr5").val();
     generalFieldsJson.FLDSIGN5=$("#fldsign5").val();*/
    generalFieldsJson.FLDNUM2=$("#fldnum2").val();
 /* generalFieldsJson.FLDSRCTBL6=$("#fldsrctbl6").val();
    generalFieldsJson.FLDSRCFLD6=$("#fldsrcfld6").val();
    generalFieldsJson.FLDLEN6=$("#fldlen6").val();
    generalFieldsJson.FLDWHR6=$("#fldwhr6").val();*/
    generalFieldsJson.FLDSIGN6=$("#fldsign6").val();
    /*generalFieldsJson.FLDSRCTBL7=$("#fldsrctbl7").val();
    generalFieldsJson.FLDSRCFLD7=$("#fldsrcfld7").val();
    generalFieldsJson.FLDLEN7=$("#fldlen7").val();
    generalFieldsJson.FLDWHR7=$("#fldwhr7").val();
    generalFieldsJson.FLDSIGN7=$("#fldsign7").val();
    generalFieldsJson.FLDSRCTBL8=$("#fldsrctbl8").val();
    generalFieldsJson.FLDSRCFLD8=$("#fldsrcfld8").val();
    generalFieldsJson.FLDLEN8=$("#fldlen8").val();
    generalFieldsJson.FLDWHR8=$("#fldwhr8").val();
    generalFieldsJson.FLDSIGN8=$("#fldsign8").val();*/
    generalFieldsJson.VRY_BIT_METH=$("#vryBitMeth").val();
    generalFieldsJson.VRY_BIT_LEN=$("#vryBitLen").val();
//    generalFieldsJson.VRY_GEN_FUNC=$("#vryGenFunc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardNoRoleInfoAdd,"json");
}

function callback_cmcCardNoRoleInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardNoRoleInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}