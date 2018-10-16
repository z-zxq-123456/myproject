var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });



    if (parent.$("#mbOpenCtl").find(".selected").length===1){
        rowData = parent.$('#mbOpenCtl').DataTable().rows(".selected").data()[0];
        $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
        $("#ctrlAttr").val(rowData.CTRL_ATTR).attr("disabled",true);
        $("#ctrlValue").val(rowData.CTRL_VALUE);
        $("#dealFlow").val(rowData.DEAL_FLOW);
        $("#quantity").val(rowData.QUANTITY);
        $("#company").val(rowData.COMPANY);
/*        $("#tranTime").val(rowData.TRAN_TIME);
        $("#tranTimestamp").val(rowData.TRAN_TIMESTAMP);*/
    }

    $("#mbOpenCtlMod").Validform({
        tiptype:2,
        callback:function(){
            mbOpenCtlMod();
            return false;
        }
    });

    $(".select2").select2();

    var value=$('#ctrlAttr').val();
    if(value == "P"){
        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
            id: "ctrlValue",
            async: false
        });
    }else if(value == "A"){
        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=MB_ACCT_NATURE_DEF&tableCol=ACCT_NATURE,DESCRIPTION",
            id: "ctrlValue",
            async: false
        });
    }else if(value == "D"){
        getPkList({  url: contextPath + "/pklist/getCtrlValue",
            id: "ctrlValue",
            async: false
        });
    }

    if (parent.$("#mbOpenCtl").find(".selected").length===1){
        rowData = parent.$('#mbOpenCtl').DataTable().rows(".selected").data()[0];
        $("#clientType").val(rowData.CLIENT_TYPE).attr("disabled",true);
        $("#ctrlAttr").val(rowData.CTRL_ATTR).attr("disabled",true);
        $("#ctrlValue").val(rowData.CTRL_VALUE);
        $("#dealFlow").val(rowData.DEAL_FLOW);
        $("#quantity").val(rowData.QUANTITY);
        $("#company").val(rowData.COMPANY);
        /*        $("#tranTime").val(rowData.TRAN_TIME);
         $("#tranTimestamp").val(rowData.TRAN_TIMESTAMP);*/
    }
    $(".select2").select2();

});

function mbOpenCtlMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_OPEN_CTL";
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    keyFieldsJson.CTRL_ATTR=$("#ctrlAttr").val();
    generalFieldsJson.CTRL_VALUE=$("#ctrlValue").val();
    generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
    generalFieldsJson.QUANTITY=$("#quantity").val();
    generalFieldsJson.COMPANY=$("#company").val();
/*    generalFieldsJson.TRAN_TIME=$("#tranTime").val();
     generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();*/
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbOpenCtlMod,"json");
}

function callback_mbOpenCtlMod(json){
    if (json.success) {
        if (parent.$("#mbOpenCtl").find(".selected").length===1){
            rowData = parent.$('#mbOpenCtl').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbOpenCtl").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                CLIENT_TYPE:$("#clientType").val(),
                CTRL_ATTR:$("#ctrlAttr").val(),
                CTRL_VALUE:$("#ctrlValue").val(),
                DEAL_FLOW:$("#dealFlow").val(),
                QUANTITY:$("#quantity").val(),
                COMPANY:$("#company").val(),
                TRAN_TIME:$("#tranTime").val(),
                TRAN_TIMESTAMP:$("#tranTimestamp").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbOpenCtlModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}