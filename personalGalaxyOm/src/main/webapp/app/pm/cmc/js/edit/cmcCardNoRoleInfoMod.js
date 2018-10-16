var rowData;
$(document).ready(function() {
	$("#vryBitMeth").val("0").select2().attr("disabled",true);
    $("#fldsign1").val("1").select2().attr("disabled",true);
    $("#fldsign6").val("1").select2().attr("disabled",true);
	 getPkList({
	        url: contextPath + "/cmcRuleNo/getAllForSelect?tableCol=RULE_NO",
	        id: "cardNoRoleCode",
	        async: false
	    });
	 
    if (parent.$("#cmcCardNoRoleInfo").find(".selected").length===1){
        rowData = parent.$('#cmcCardNoRoleInfo').DataTable().rows(".selected").data()[0];
        $("#cardNoRoleCode").val(rowData.CARD_NO_ROLE_CODE).attr("disabled",true);
        $("#lenOfCardNo").val(rowData.LEN_OF_CARD_NO);
        $("#cardGenId").val(rowData.CARD_GEN_ID);
        $("#seqNoLen").val(rowData.SEQ_NO_LEN);
        $("#fldnum1").val(rowData.FLDNUM1);
        $("#fldsrctbl1").val(rowData.FLDSRCTBL1);
        $("#fldsrcfld1").val(rowData.FLDSRCFLD1);
        $("#fldlen1").val(rowData.FLDLEN1);
        $("#fldwhr1").val(rowData.FLDWHR1);
        $("#fldsign1").val(rowData.FLDSIGN1);
//        $("#fldsrctbl2").val(rowData.FLDSRCTBL2);
//        $("#fldsrcfld2").val(rowData.FLDSRCFLD2);
       /* $("#fldlen2").val(rowData.FLDLEN2);
        $("#fldwhr2").val(rowData.FLDWHR2);
        $("#fldsign2").val(rowData.FLDSIGN2);
        $("#fldsrctbl3").val(rowData.FLDSRCTBL3);
        $("#fldsrcfld3").val(rowData.FLDSRCFLD3);
        $("#fldlen3").val(rowData.FLDLEN3);
        $("#fldwhr3").val(rowData.FLDWHR3);
        $("#fldsign3").val(rowData.FLDSIGN3);
        $("#fldsrctbl4").val(rowData.FLDSRCTBL4);
        $("#fldsrcfld4").val(rowData.FLDSRCFLD4);
        $("#fldlen4").val(rowData.FLDLEN4);
        $("#fldwhr4").val(rowData.FLDWHR4);
        $("#fldsign4").val(rowData.FLDSIGN4);
        $("#fldsrctbl5").val(rowData.FLDSRCTBL5);
        $("#fldsrcfld5").val(rowData.FLDSRCFLD5);
        $("#fldlen5").val(rowData.FLDLEN5);
        $("#fldwhr5").val(rowData.FLDWHR5);
        $("#fldsign5").val(rowData.FLDSIGN5);*/
        $("#fldnum2").val(rowData.FLDNUM2);
       /* $("#fldsrctbl6").val(rowData.FLDSRCTBL6);
        $("#fldsrcfld6").val(rowData.FLDSRCFLD6);
        $("#fldlen6").val(rowData.FLDLEN6);
        $("#fldwhr6").val(rowData.FLDWHR6);*/
        $("#fldsign6").val(rowData.FLDSIGN6);
        /*$("#fldsrctbl7").val(rowData.FLDSRCTBL7);
        $("#fldsrcfld7").val(rowData.FLDSRCFLD7);
        $("#fldlen7").val(rowData.FLDLEN7);
        $("#fldwhr7").val(rowData.FLDWHR7);
        $("#fldsign7").val(rowData.FLDSIGN7);
        $("#fldsrctbl8").val(rowData.FLDSRCTBL8);
        $("#fldsrcfld8").val(rowData.FLDSRCFLD8);
        $("#fldlen8").val(rowData.FLDLEN8);
        $("#fldwhr8").val(rowData.FLDWHR8);
        $("#fldsign8").val(rowData.FLDSIGN8);*/
        $("#vryBitMeth").val(rowData.VRY_BIT_METH);
        $("#vryBitLen").val(rowData.VRY_BIT_LEN);
//        $("#vryGenFunc").val(rowData.VRY_GEN_FUNC);

    }

    $("#cmcCardNoRoleInfoMod").Validform({
        tiptype:2,
        callback:function(){
            cmcCardNoRoleInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cmcCardNoRoleInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CMC_CARD_NO_ROLE_INFO";
    keyFieldsJson.CARD_NO_ROLE_CODE=$("#cardNoRoleCode").val();
    generalFieldsJson.CARD_GEN_ID=$("#cardGenId").val();
    generalFieldsJson.LEN_OF_CARD_NO=$("#lenOfCardNo").val();
    generalFieldsJson.SEQ_NO_LEN=$("#seqNoLen").val();
    generalFieldsJson.VRY_BIT_LEN=$("#vryBitLen").val();
    generalFieldsJson.VRY_BIT_METH=$("#vryBitMeth").val();
    generalFieldsJson.FLDNUM1=$("#fldnum1").val();
//    generalFieldsJson.FLDSRCTBL7=$("#fldsrctbl7").val();
//    generalFieldsJson.FLDSRCTBL6=$("#fldsrctbl6").val();
//    generalFieldsJson.FLDSRCTBL5=$("#fldsrctbl5").val();
//    generalFieldsJson.FLDSRCTBL4=$("#fldsrctbl4").val();
//    generalFieldsJson.FLDSRCTBL3=$("#fldsrctbl3").val();
//    generalFieldsJson.FLDSRCTBL2=$("#fldsrctbl2").val();
    generalFieldsJson.FLDSRCTBL1=$("#fldsrctbl1").val();
//    generalFieldsJson.FLDSRCFLD8=$("#fldsrcfld8").val();
//    generalFieldsJson.FLDSRCTBL8=$("#fldsrctbl8").val();
    generalFieldsJson.FLDWHR1=$("#fldwhr1").val();
//    generalFieldsJson.VRY_GEN_FUNC=$("#vryGenFunc").val();
//    generalFieldsJson.FLDWHR8=$("#fldwhr8").val();
//    generalFieldsJson.FLDWHR7=$("#fldwhr7").val();
//    generalFieldsJson.FLDWHR6=$("#fldwhr6").val();
//    generalFieldsJson.FLDWHR5=$("#fldwhr5").val();
//    generalFieldsJson.FLDWHR4=$("#fldwhr4").val();
//    generalFieldsJson.FLDWHR3=$("#fldwhr3").val();
//    generalFieldsJson.FLDWHR2=$("#fldwhr2").val();
//    generalFieldsJson.FLDSRCFLD7=$("#fldsrcfld7").val();
//    generalFieldsJson.FLDSRCFLD6=$("#fldsrcfld6").val();
    generalFieldsJson.FLDSIGN1=$("#fldsign1").val();
    generalFieldsJson.FLDNUM2=$("#fldnum2").val();
//    generalFieldsJson.FLDLEN8=$("#fldlen8").val();
//    generalFieldsJson.FLDLEN7=$("#fldlen7").val();
//    generalFieldsJson.FLDLEN6=$("#fldlen6").val();
//    generalFieldsJson.FLDLEN5=$("#fldlen5").val();
//    generalFieldsJson.FLDLEN4=$("#fldlen4").val();
//    generalFieldsJson.FLDLEN3=$("#fldlen3").val();
//    generalFieldsJson.FLDLEN2=$("#fldlen2").val();
//    generalFieldsJson.FLDSIGN2=$("#fldsign2").val();
//    generalFieldsJson.FLDSIGN3=$("#fldsign3").val();
//    generalFieldsJson.FLDSIGN4=$("#fldsign4").val();
//    generalFieldsJson.FLDSRCFLD5=$("#fldsrcfld5").val();
//    generalFieldsJson.FLDSRCFLD4=$("#fldsrcfld4").val();
//    generalFieldsJson.FLDSRCFLD3=$("#fldsrcfld3").val();
//    generalFieldsJson.FLDSRCFLD2=$("#fldsrcfld2").val();
    generalFieldsJson.FLDSRCFLD1=$("#fldsrcfld1").val();
//    generalFieldsJson.FLDSIGN8=$("#fldsign8").val();
//    generalFieldsJson.FLDSIGN7=$("#fldsign7").val();
    generalFieldsJson.FLDSIGN6=$("#fldsign6").val();
//    generalFieldsJson.FLDSIGN5=$("#fldsign5").val();
    generalFieldsJson.FLDLEN1=$("#fldlen1").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cmcCardNoRoleInfoMod,"json");
}

function callback_cmcCardNoRoleInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoRoleInfo").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoRoleInfo').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cmcCardNoRoleInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CARD_NO_ROLE_CODE:$("#cardNoRoleCode").val(),
                CARD_GEN_ID:$("#cardGenId").val(),
                LEN_OF_CARD_NO:$("#lenOfCardNo").val(),
                SEQ_NO_LEN:$("#seqNoLen").val(),
                VRY_BIT_LEN:$("#vryBitLen").val(),
                VRY_BIT_METH:$("#vryBitMeth").val(),
                FLDNUM1:$("#fldnum1").val(),
//                FLDSRCTBL7:$("#fldsrctbl7").val(),
//                FLDSRCTBL6:$("#fldsrctbl6").val(),
//                FLDSRCTBL5:$("#fldsrctbl5").val(),
//                FLDSRCTBL4:$("#fldsrctbl4").val(),
//                FLDSRCTBL3:$("#fldsrctbl3").val(),
//              FLDSRCTBL2:$("#fldsrctbl2").val(),
                FLDSRCTBL1:$("#fldsrctbl1").val(),
//                FLDSRCFLD8:$("#fldsrcfld8").val(),
//                FLDSRCTBL8:$("#fldsrctbl8").val(),
                FLDWHR1:$("#fldwhr1").val(),
//                VRY_GEN_FUNC:$("#vryGenFunc").val(),
//                FLDWHR8:$("#fldwhr8").val(),
//                FLDWHR7:$("#fldwhr7").val(),
//                FLDWHR6:$("#fldwhr6").val(),
//                FLDWHR5:$("#fldwhr5").val(),
//                FLDWHR4:$("#fldwhr4").val(),
//                FLDWHR3:$("#fldwhr3").val(),
//                FLDWHR2:$("#fldwhr2").val(),
//                FLDSRCFLD7:$("#fldsrcfld7").val(),
//                FLDSRCFLD6:$("#fldsrcfld6").val(),
                FLDSIGN1:$("#fldsign1").val(),
                FLDNUM2:$("#fldnum2").val(),
//                FLDLEN8:$("#fldlen8").val(),
//                FLDLEN7:$("#fldlen7").val(),
//                FLDLEN6:$("#fldlen6").val(),
//                FLDLEN5:$("#fldlen5").val(),
//                FLDLEN4:$("#fldlen4").val(),
//                FLDLEN3:$("#fldlen3").val(),
//                FLDLEN2:$("#fldlen2").val(),
//                FLDSIGN2:$("#fldsign2").val(),
//                FLDSIGN3:$("#fldsign3").val(),
//                FLDSIGN4:$("#fldsign4").val(),
//                FLDSRCFLD5:$("#fldsrcfld5").val(),
//                FLDSRCFLD4:$("#fldsrcfld4").val(),
//                FLDSRCFLD3:$("#fldsrcfld3").val(),
//                FLDSRCFLD2:$("#fldsrcfld2").val(),
                FLDSRCFLD1:$("#fldsrcfld1").val(),
//                FLDSIGN8:$("#fldsign8").val(),
//                FLDSIGN7:$("#fldsign7").val(),
                FLDSIGN6:$("#fldsign6").val(),
//                FLDSIGN5:$("#fldsign5").val(),
                FLDLEN1:$("#fldlen1").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cmcCardNoRoleInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}