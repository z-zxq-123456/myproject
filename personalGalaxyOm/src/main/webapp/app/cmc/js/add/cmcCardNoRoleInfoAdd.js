$(document).ready(function() {

    $("#cardNoRoleCode").val($.session.get("cardNoType")).attr("disabled",true);
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

    var generalFieldsJson = JSON.stringify({
        cardNoRoleCode:$("#cardNoRoleCode").val(),
        lenOfCardNo:$("#lenOfCardNo").val(),
        cardGenId:$("#cardGenId").val(),
        seqNoLen:$("#seqNoLen").val(),
        fldnum1:$("#fldnum1").val(),
        fldsrctbl1:$("#fldsrctbl1").val(),
        fldsrcfld1:$("#fldsrcfld1").val(),
        fldlen1:$("#fldlen1").val(),
        fldwhr1:$("#fldwhr1").val(),
        fldsign1:$("#fldsign1").val(),
        fldsrctbl2:$("#fldsrctbl2").val(),
        fldsrcfld2:$("#fldsrcfld2").val(),
        fldlen2:$("#fldlen2").val(),
        fldwhr2:$("#fldwhr2").val(),
        fldsign2:$("#fldsign2").val(),
        fldsrctbl3:$("#fldsrctbl3").val(),
        fldsrcfld3:$("#fldsrcfld3").val(),
        fldlen3:$("#fldlen3").val(),
        fldwhr3:$("#fldwhr3").val(),
        fldsign3:$("#fldsign3").val(),
        fldsrctbl4:$("#fldsrctbl4").val(),
        fldsrcfld4:$("#fldsrcfld4").val(),
        fldlen4:$("#fldlen4").val(),
        fldwhr4:$("#fldwhr4").val(),
        fldsign4:$("#fldsign4").val(),
        fldsrctbl5:$("#fldsrctbl5").val(),
        fldsrcfld5:$("#fldsrcfld5").val(),
        fldlen5:$("#fldlen5").val(),
        fldwhr5:$("#fldwhr5").val(),
        fldsign5:$("#fldsign5").val(),
        fldnum2:$("#fldnum2").val(),
        fldsrctbl6:$("#fldsrctbl6").val(),
        fldsrcfld6:$("#fldsrcfld6").val(),
        fldlen6:$("#fldlen6").val(),
        fldwhr6:$("#fldwhr6").val(),
        fldsign6:$("#fldsign6").val(),
        fldsrctbl7:$("#fldsrctbl7").val(),
        fldsrcfld7:$("#fldsrcfld7").val(),
        fldlen7:$("#fldlen7").val(),
        fldwhr7:$("#fldwhr7").val(),
        fldsign7:$("#fldsign7").val(),
        fldsrctbl8:$("#fldsrctbl8").val(),
        fldsrcfld8:$("#fldsrcfld8").val(),
        fldlen8:$("#fldlen8").val(),
        fldwhr8:$("#fldwhr8").val(),
        fldsign8:$("#fldsign8").val(),
        vryBitMeth:$("#vryBitMeth").val(),
        vryBitLen:$("#vryBitLen").val(),
        vryGenFunc:$("#vryGenFunc").val(),
        reqNum:parent.parent.$(".breadcrumb").data("reqNum"),
        operateType:parent.parent.$("#operateType").val()
    });
    var url = contextPath+"/cardNoRoleInfo/add";

    $.ajax({
        type: "post",
        url: url,
        data: generalFieldsJson,
        dataType: "json",
        cache: false,
        contentType: "application/json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callback_cmcCardNoRoleInfoAdd,
        complete: function () {
            layer.close(index1);
        }
    });

}

function callback_cmcCardNoRoleInfoAdd(json){
    if (json.success) {
        var dataTable=parent.$('#cmcCardNoRoleInfo').DataTable();
        dataTable.row.add({
            CARD_NO_ROLE_CODE:$("#cardNoRoleCode").val(),
            LEN_OF_CARD_NO:$("#lenOfCardNo").val(),
            CARD_GEN_ID:$("#cardGenId").val(),
            SEQ_NO_LEN:$("#seqNoLen").val(),
            FLDNUM1:$("#fldnum1").val(),
            FLDSRCTBL1:$("#fldsrctbl1").val(),
            FLDSRCFLD1:$("#fldsrcfld1").val(),
            FLDLEN1:$("#fldlen1").val(),
            FLDWHR1:$("#fldwhr1").val(),
            FLDSIGN1:$("#fldsign1").val(),
            FLDSRCTBL2:$("#fldsrctbl2").val(),
            FLDSRCFLD2:$("#fldsrcfld2").val(),
            FLDLEN2:$("#fldlen2").val(),
            FLDWHR2:$("#fldwhr2").val(),
            FLDSIGN2:$("#fldsign2").val(),
            FLDSRCTBL3:$("#fldsrctbl3").val(),
            FLDSRCFLD3:$("#fldsrcfld3").val(),
            FLDLEN3:$("#fldlen3").val(),
            FLDWHR3:$("#fldwhr3").val(),
            FLDSIGN3:$("#fldsign3").val(),
            FLDSRCTBL4:$("#fldsrctbl4").val(),
            FLDSRCFLD4:$("#fldsrcfld4").val(),
            FLDLEN4:$("#fldlen4").val(),
            FLDWHR4:$("#fldwhr4").val(),
            FLDSIGN4:$("#fldsign4").val(),
            FLDSRCTBL5:$("#fldsrctbl5").val(),
            FLDSRCFLD5:$("#fldsrcfld5").val(),
            FLDLEN5:$("#fldlen5").val(),
            FLDWHR5:$("#fldwhr5").val(),
            FLDSIGN5:$("#fldsign5").val(),
            FLDNUM2:$("#fldnum2").val(),
            FLDSRCTBL6:$("#fldsrctbl6").val(),
            FLDSRCFLD6:$("#fldsrcfld6").val(),
            FLDLEN6:$("#fldlen6").val(),
            FLDWHR6:$("#fldwhr6").val(),
            FLDSIGN6:$("#fldsign6").val(),
            FLDSRCTBL7:$("#fldsrctbl7").val(),
            FLDSRCFLD7:$("#fldsrcfld7").val(),
            FLDLEN7:$("#fldlen7").val(),
            FLDWHR7:$("#fldwhr7").val(),
            FLDSIGN7:$("#fldsign7").val(),
            FLDSRCTBL8:$("#fldsrctbl8").val(),
            FLDSRCFLD8:$("#fldsrcfld8").val(),
            FLDLEN8:$("#fldlen8").val(),
            FLDWHR8:$("#fldwhr8").val(),
            FLDSIGN8:$("#fldsign8").val(),
            VRY_BIT_METH:$("#vryBitMeth").val(),
            VRY_BIT_LEN:$("#vryBitLen").val(),
            VRY_GEN_FUNC:$("#vryGenFunc").val()
        }).draw(false);
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}