
var rowData;
$(document).ready(function() {

    if (parent.$("#mbAnalysis3").find(".selected").length===1){
        rowData = parent.$('#mbAnalysis3').DataTable().rows(".selected").data()[0];
        $("#analysis3").val(rowData.ANALYSIS3).attr("disabled",true);
        $("#analysis3Desc").val(rowData.ANALYSIS3_DESC);
    }

    $("#mbAnalysis3Mod").Validform({
        tiptype:2,
        callback:function(form){
            mbAnalysis3Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAnalysis3Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ANALYSIS3";
    keyFieldsJson.ANALYSIS3=$("#analysis3").val();
    generalFieldsJson.ANALYSIS3_DESC=$("#analysis3Desc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAnalysis3Mod,"json");
}

function callback_mbAnalysis3Mod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAnalysis3").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ANALYSIS3:$("#analysis3").val(),
            ANALYSIS3_DESC:$("#analysis3Desc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAnalysis3ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

