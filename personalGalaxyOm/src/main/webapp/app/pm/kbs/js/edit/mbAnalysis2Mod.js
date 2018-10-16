
var rowData;
$(document).ready(function() {

    if (parent.$("#mbAnalysis2").find(".selected").length===1){
        rowData = parent.$('#mbAnalysis2').DataTable().rows(".selected").data()[0];
        $("#analysis2").val(rowData.ANALYSIS2).attr("disabled",true);
        $("#analysis2Desc").val(rowData.ANALYSIS2_DESC);
    }

    $("#mbAnalysis2Mod").Validform({
        tiptype:2,
        callback:function(form){
            mbAnalysis2Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAnalysis2Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ANALYSIS2";
    keyFieldsJson.ANALYSIS2=$("#analysis2").val();
    generalFieldsJson.ANALYSIS2_DESC=$("#analysis2Desc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAnalysis2Mod,"json");
}

function callback_mbAnalysis2Mod(json){
    if (json.success) {
        var dataTable=parent.$("#mbAnalysis2").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ANALYSIS2:$("#analysis2").val(),
            ANALYSIS2_DESC:$("#analysis2Desc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAnalysis2ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

