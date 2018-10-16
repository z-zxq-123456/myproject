
var rowData;
$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_BASIS&tableCol=INT_BASIS,INT_BASIS_DESC",
				id: "intBasis",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

    if (parent.$("#irlBasisRate").find(".selected").length===1){
        rowData = parent.$('#irlBasisRate').DataTable().rows(".selected").data()[0];
            $("#ccy").val(rowData.CCY).attr("disabled",true);
            // $("#effectDate").val(rowData.EFFECT_DATE).attr("disabled",true); //modify by qijun for bug73995  20170901
            $("#effectDate").val(rowData.EFFECT_DATE);
            $("#intBasis").val(rowData.INT_BASIS).attr("disabled",true);
            $("#intBasisRate").val(rowData.INT_BASIS_RATE);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlBasisRateMod").Validform({
        tiptype:2,
        callback:function(form){
            irlBasisRateMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlBasisRateMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_BASIS_RATE";
        keyFieldsJson.CCY=$("#ccy").val();
        keyFieldsJson.EFFECT_DATE=$("#effectDate").val();
        keyFieldsJson.INT_BASIS=$("#intBasis").val();
        generalFieldsJson.INT_BASIS_RATE=$("#intBasisRate").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlBasisRateMod,"json");
}

function callback_irlBasisRateMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlBasisRate").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CCY:$("ccy").val(),
            EFFECT_DATE:$("effectDate").val(),
            INT_BASIS:$("intBasis").val(),
            INT_BASIS_RATE:$("intBasisRate").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlBasisRateModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
