
var rowData;
$(document).ready(function() {

    if (parent.$("#mbProdRelationDefine").find(".selected").length===1){
        rowData = parent.$('#mbProdRelationDefine').DataTable().rows(".selected").data()[0];
        $("#assembleId").val(rowData.ASSEMBLE_ID).attr("disabled",true);
        $("#eventType").val(rowData.EVENT_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#subProdType").val(rowData.SUB_PROD_TYPE).attr("disabled",true);
        $("#assembleType").val(rowData.ASSEMBLE_TYPE);
        $("#status").val(rowData.STATUS);
    }

    $("#mbProdRelationDefineMod").Validform({
        tiptype:2,
        callback:function(form){
            mbProdRelationDefineMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdRelationDefineMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_RELATION_DEFINE";
    keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    keyFieldsJson.SUB_PROD_TYPE=$("#subProdType").val();
    generalFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
    generalFieldsJson.STATUS=$("#status").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdRelationDefineMod,"json");
}

function callback_mbProdRelationDefineMod(json){
    if (json.success) {
       if (parent.$("#mbProdRelationDefine").find(".selected").length===1){
        rowData = parent.$('#mbProdRelationDefine').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbProdRelationDefine").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ASSEMBLE_ID:$("#assembleId").val(),
            EVENT_TYPE:$("#eventType").val(),
            PROD_TYPE:$("#prodType").val(),
            SUB_PROD_TYPE:$("#subProdType").val(),
            ASSEMBLE_TYPE:$("#assembleType").val(),
            STATUS:$("#status").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdRelationDefineModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

