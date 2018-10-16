
var rowData;
$(document).ready(function() {

    if (parent.$("#mbAnalysis1").find(".selected").length===1){
        rowData = parent.$('#mbAnalysis1').DataTable().rows(".selected").data()[0];
        $("#analysis1").val(rowData.ANALYSIS1).attr("disabled",true);
        $("#analysis1Desc").val(rowData.ANALYSIS1_DESC);
    }

    $("#mbAnalysis1Mod").Validform({
        tiptype:2,
        callback:function(form){
            mbAnalysis1Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAnalysis1Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ANALYSIS1";
    keyFieldsJson.ANALYSIS1=$("#analysis1").val();
    generalFieldsJson.ANALYSIS1_DESC=$("#analysis1Desc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAnalysis1Mod,"json");
}

function callback_mbAnalysis1Mod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAnalysis1").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ANALYSIS1:$("#analysis1").val(),
            ANALYSIS1_DESC:$("#analysis1Desc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAnalysis1ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

