var rowData;
$(document).ready(function() {

    if (parent.$("#fmFakeCoinDef").find(".selected").length===1){
        rowData = parent.$('#fmFakeCoinDef').DataTable().rows(".selected").data()[0];
        $("#bondnotes").val(rowData.BONDNOTES).attr("disabled",true);
        $("#bondnumber").val(rowData.BONDNUMBER).attr("disabled",true);
        $("#bondtypeid").val(rowData.BONDTYPEID).attr("disabled",true);
        $("#bondversionnum").val(rowData.BONDVERSIONNUM).attr("disabled",true);
        $("#ccy").val(rowData.CCY).attr("disabled",true);
        $("#bondname").val(rowData.BONDNAME);
    }

    $("#fmFakeCoinDefMod").Validform({
        tiptype:2,
        callback:function(){
            fmFakeCoinDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmFakeCoinDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_FAKE_COIN_DEF";
    keyFieldsJson.BONDNOTES=$("#bondnotes").val();
    keyFieldsJson.BONDNUMBER=$("#bondnumber").val();
    keyFieldsJson.BONDTYPEID=$("#bondtypeid").val();
    keyFieldsJson.BONDVERSIONNUM=$("#bondversionnum").val();
    keyFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.BONDNAME=$("#bondname").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmFakeCoinDefMod,"json");
}

function callback_fmFakeCoinDefMod(json){
    if (json.success) {
        if (parent.$("#fmFakeCoinDef").find(".selected").length===1){
            rowData = parent.$('#fmFakeCoinDef').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#fmFakeCoinDef").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BONDNOTES:$("#bondnotes").val(),
                BONDNUMBER:$("#bondnumber").val(),
                BONDTYPEID:$("#bondtypeid").val(),
                BONDVERSIONNUM:$("#bondversionnum").val(),
                CCY:$("#ccy").val(),
                BONDNAME:$("#bondname").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmFakeCoinDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}