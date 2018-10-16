var rowData;
$(document).ready(function() {

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
        $("#fldsrctbl2").val(rowData.FLDSRCTBL2);
        $("#fldsrcfld2").val(rowData.FLDSRCFLD2);
        $("#fldlen2").val(rowData.FLDLEN2);
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
        $("#fldsign5").val(rowData.FLDSIGN5);
        $("#fldnum2").val(rowData.FLDNUM2);
        $("#fldsrctbl6").val(rowData.FLDSRCTBL6);
        $("#fldsrcfld6").val(rowData.FLDSRCFLD6);
        $("#fldlen6").val(rowData.FLDLEN6);
        $("#fldwhr6").val(rowData.FLDWHR6);
        $("#fldsign6").val(rowData.FLDSIGN6);
        $("#fldsrctbl7").val(rowData.FLDSRCTBL7);
        $("#fldsrcfld7").val(rowData.FLDSRCFLD7);
        $("#fldlen7").val(rowData.FLDLEN7);
        $("#fldwhr7").val(rowData.FLDWHR7);
        $("#fldsign7").val(rowData.FLDSIGN7);
        $("#fldsrctbl8").val(rowData.FLDSRCTBL8);
        $("#fldsrcfld8").val(rowData.FLDSRCFLD8);
        $("#fldlen8").val(rowData.FLDLEN8);
        $("#fldwhr8").val(rowData.FLDWHR8);
        $("#fldsign8").val(rowData.FLDSIGN8);
        $("#vryBitMeth").val(rowData.VRY_BIT_METH);
        $("#vryBitLen").val(rowData.VRY_BIT_LEN);
        $("#vryGenFunc").val(rowData.VRY_GEN_FUNC);

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
    var url = contextPath+"/cardNoRoleInfo/update";
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
        success: callback_cmcCardNoRoleInfoMod,
        complete: function () {
            layer.close(index1);
        }
    });
}

function callback_cmcCardNoRoleInfoMod(json){
    if (json.success) {
        if (parent.$("#cmcCardNoRoleInfo").find(".selected").length===1){
            rowData = parent.$('#cmcCardNoRoleInfo').DataTable().rows(".selected").data()[0];
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
                FLDSRCTBL7:$("#fldsrctbl7").val(),
                FLDSRCTBL6:$("#fldsrctbl6").val(),
                FLDSRCTBL5:$("#fldsrctbl5").val(),
                FLDSRCTBL4:$("#fldsrctbl4").val(),
                FLDSRCTBL3:$("#fldsrctbl3").val(),
                FLDSRCTBL2:$("#fldsrctbl2").val(),
                FLDSRCTBL1:$("#fldsrctbl1").val(),
                FLDSRCFLD8:$("#fldsrcfld8").val(),
                FLDSRCTBL8:$("#fldsrctbl8").val(),
                FLDWHR1:$("#fldwhr1").val(),
                VRY_GEN_FUNC:$("#vryGenFunc").val(),
                FLDWHR8:$("#fldwhr8").val(),
                FLDWHR7:$("#fldwhr7").val(),
                FLDWHR6:$("#fldwhr6").val(),
                FLDWHR5:$("#fldwhr5").val(),
                FLDWHR4:$("#fldwhr4").val(),
                FLDWHR3:$("#fldwhr3").val(),
                FLDWHR2:$("#fldwhr2").val(),
                FLDSRCFLD7:$("#fldsrcfld7").val(),
                FLDSRCFLD6:$("#fldsrcfld6").val(),
                FLDSIGN1:$("#fldsign1").val(),
                FLDNUM2:$("#fldnum2").val(),
                FLDLEN8:$("#fldlen8").val(),
                FLDLEN7:$("#fldlen7").val(),
                FLDLEN6:$("#fldlen6").val(),
                FLDLEN5:$("#fldlen5").val(),
                FLDLEN4:$("#fldlen4").val(),
                FLDLEN3:$("#fldlen3").val(),
                FLDLEN2:$("#fldlen2").val(),
                FLDSIGN2:$("#fldsign2").val(),
                FLDSIGN3:$("#fldsign3").val(),
                FLDSIGN4:$("#fldsign4").val(),
                FLDSRCFLD5:$("#fldsrcfld5").val(),
                FLDSRCFLD4:$("#fldsrcfld4").val(),
                FLDSRCFLD3:$("#fldsrcfld3").val(),
                FLDSRCFLD2:$("#fldsrcfld2").val(),
                FLDSRCFLD1:$("#fldsrcfld1").val(),
                FLDSIGN8:$("#fldsign8").val(),
                FLDSIGN7:$("#fldsign7").val(),
                FLDSIGN6:$("#fldsign6").val(),
                FLDSIGN5:$("#fldsign5").val(),
                FLDLEN1:$("#fldlen1").val()
            }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}