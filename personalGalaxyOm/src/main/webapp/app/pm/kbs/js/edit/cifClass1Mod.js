
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClass1").find(".selected").length===1){
        rowData = parent.$('#cifClass1').DataTable().rows(".selected").data()[0];
        $("#class1").val(rowData.CLASS_1).attr("disabled",true);
        $("#class1Desc").val(rowData.CLASS_1_DESC);
        $("#company").val(rowData.COMPANY);
        $("#counterParty").val(rowData.COUNTER_PARTY);
    }

    $("#cifClass1Mod").Validform({
        tiptype:2,
        callback:function(form){
            cifClass1Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClass1Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLASS_1";
    keyFieldsJson.CLASS_1=$("#class1").val();
    generalFieldsJson.CLASS_1_DESC=$("#class1Desc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.COUNTER_PARTY=$("#counterParty").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClass1Mod,"json");
}

function callback_cifClass1Mod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClass1").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CLASS_1:$("#class1").val(),
            CLASS_1_DESC:$("#class1Desc").val(),
            COMPANY:$("#company").val(),
            COUNTER_PARTY:$("#counterParty").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClass1ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

