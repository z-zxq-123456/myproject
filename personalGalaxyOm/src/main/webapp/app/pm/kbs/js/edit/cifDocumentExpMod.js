var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
        id: "sourceTypeRule",
        async: false
    });
    // getPkList({
    //     url: contextPath + "/baseCommon/pklistBase?tableName=FM_RESTRAINT_TYPE&tableCol=RESTRAINT_TYPE,RESTRAINT_DESC",
    //     id: "restraintType",
    //     async: false
    // });
    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    paraJson.tableName = "FM_RESTRAINT_TYPE";
    paraJson.tableCol="RESTRAINT_TYPE,RESTRAINT_DESC";

    //keyFieldsJson.RESTRAINT_CLASS = "DQ";
    //paraJson.key = keyFieldsJson;
    getPkList({
        url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
        id: "restraintType",
        async: false

    });

    if (parent.$("#cifDocumentExp").find(".selected").length===1){
        rowData = parent.$('#cifDocumentExp').DataTable().rows(".selected").data()[0];
        $("#documentType").val(rowData.DOCUMENT_TYPE).attr("disabled",true);
        $("#dealFlow").val(rowData.DEAL_FLOW);
        $("#stopFlag").val(rowData.STOP_FLAG);
        $("#company").val(rowData.COMPANY);
        $("#days").val(rowData.DAYS);
    }

    $("#cifDocumentExpMod").Validform({
        tiptype:2,
        callback:function(){
            cifDocumentExpMod();
            return false;
        }
    });

    $('#stopFlag').change(function(){
        var value=$('#stopFlag').val();
        if(value=="N"){
            $("#restraintType").attr("disabled",true);
        }
        else if(value=="Y"){
            $("#restraintType").attr("disabled",false);
        }
    });

    $(".select2").select2();
});

function cifDocumentExpMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_DOCUMENT_EXP";
    keyFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
    generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
    generalFieldsJson.STOP_FLAG=$("#stopFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DAYS=$("#days").val();
    generalFieldsJson.RESTRAINT_TYPE=$("#restraintType").val();
    generalFieldsJson.SOURCE_TYPE_RULE=$("#sourceTypeRule").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifDocumentExpMod,"json");
}

function callback_cifDocumentExpMod(json){
    if (json.success) {
        if (parent.$("#cifDocumentExp").find(".selected").length===1){
            rowData = parent.$('#cifDocumentExp').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#cifDocumentExp").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                DOCUMENT_TYPE:$("#documentType").val(),
                DEAL_FLOW:$("#dealFlow").val(),
                STOP_FLAG:$("#stopFlag").val(),
                COMPANY:$("#company").val(),
                DAYS:$("#days").val(),
                RESTRAINT_TYPE:$("#restraintType").val(),
                SOURCE_TYPE_RULE:$("#sourceTypeRule").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifDocumentExpModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}