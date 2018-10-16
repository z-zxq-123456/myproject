
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmCounterParty").find(".selected").length===1){
        rowData = parent.$('#fmCounterParty').DataTable().rows(".selected").data()[0];
        $("#counterParty").val(rowData.COUNTER_PARTY).attr("disabled",true);
        $("#counterPartyDesc").val(rowData.COUNTER_PARTY_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#fmCounterPartyMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCounterPartyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCounterPartyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_COUNTER_PARTY";
    keyFieldsJson.COUNTER_PARTY=$("#counterParty").val();
    generalFieldsJson.COUNTER_PARTY_DESC=$("#counterPartyDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCounterPartyMod,"json");
}

function callback_fmCounterPartyMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCounterParty").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            COUNTER_PARTY:$("#counterParty").val(),
            COUNTER_PARTY_DESC:$("#counterPartyDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCounterPartyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

