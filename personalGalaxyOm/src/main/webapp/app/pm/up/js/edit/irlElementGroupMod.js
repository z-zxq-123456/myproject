
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_GROUP_TYPE&tableCol=GROUP_TYPE,GROUP_TYPE_DESC",
		id: "groupType",
		async: false
	});
    if (parent.$("#irlElementGroup").find(".selected").length===1){
        rowData = parent.$('#irlElementGroup').DataTable().rows(".selected").data()[0];
        $("#elementId").val(rowData.ELEMENT_ID).attr("disabled",true);
        $("#groupType").val(rowData.GROUP_TYPE).attr("disabled",true);
        $("#elementDesc").val(rowData.ELEMENT_DESC).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
    }

    $("#irlElementGroupMod").Validform({
        tiptype:2,
        callback:function(form){
            irlElementGroupMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlElementGroupMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_ELEMENT_GROUP";
    keyFieldsJson.ELEMENT_ID=$("#elementId").val();
    keyFieldsJson.GROUP_TYPE=$("#groupType").val();
    generalFieldsJson.ELEMENT_DESC=$("#elementDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlElementGroupMod,"json");
}

function callback_irlElementGroupMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlElementGroup").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ELEMENT_ID:$("#elementId").val(),
            GROUP_TYPE:$("#groupType").val(),
            ELEMENT_DESC:$("#elementDesc").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlElementGroupModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

