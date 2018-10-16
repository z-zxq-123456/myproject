
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlElement").find(".selected").length===1){
        rowData = parent.$('#irlElement').DataTable().rows(".selected").data()[0];
            $("#elementId").val(rowData.ELEMENT_ID).attr("disabled",true);
            $("#elementAttr").val(rowData.ELEMENT_ATTR);
            $("#elementDesc").val(rowData.ELEMENT_DESC);
            $("#tableName").val(rowData.TABLE_NAME);
            $("#elementLength").val(rowData.ELEMENT_LENGTH);
            $("#elementType").val(rowData.ELEMENT_TYPE).attr("disabled",true);
            $("#fieldValue").val(rowData.IRL_FIELD_VALUE);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlElementMod").Validform({
        tiptype:2,
        callback:function(form){
            irlElementMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlElementMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_ELEMENT";
        keyFieldsJson.ELEMENT_ID=$("#elementId").val();
        keyFieldsJson.ELEMENT_TYPE=$("#elementType").val();
        generalFieldsJson.ELEMENT_ATTR=$("#elementAttr").val();
        generalFieldsJson.ELEMENT_DESC=$("#elementDesc").val();
        generalFieldsJson.TABLE_NAME=$("#tableName").val();
        generalFieldsJson.ELEMENT_LENGTH=$("#elementLength").val();
        generalFieldsJson.IRL_FIELD_VALUE=$("#fieldValue").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlElementMod,"json");
}

function callback_irlElementMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlElement").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ELEMENT_ID:$("elementId").val(),
            ELEMENT_ATTR:$("elementAttr").val(),
            ELEMENT_DESC:$("elementDesc").val(),
            TABLE_NAME:$("tableName").val(),
            ELEMENT_LENGTH:$("elementLength").val(),
            ELEMENT_TYPE:$("elementType").val(),
            FIELD_VALUE:$("fieldValue").val(),
            COMPANY:$("company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlElementModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

