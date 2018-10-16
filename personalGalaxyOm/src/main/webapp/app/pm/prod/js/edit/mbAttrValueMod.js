
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    if (parent.$("#mbAttrValue").find(".selected").length===1){
        rowData = parent.$('#mbAttrValue').DataTable().rows(".selected").data()[0];
        $("#attrKey").val(rowData.ATTR_KEY).attr("disabled",true);
        $("#attrValue").val(rowData.ATTR_VALUE).attr("disabled",true);
        $("#valueDesc").val(rowData.VALUE_DESC);
        $("#refColumns").val(rowData.REF_COLUMNS);
        $("#refCondition").val(rowData.REF_CONDITION);
        $("#refTable").val(rowData.REF_TABLE);
        $("#company").val(rowData.COMPANY);
        var url = contextPath + "/attrType/getattrKey";
         $.ajax({
             url: url,
             data: "attrKey=" + $("#attrKey").val(),
             success: function(json) {
                 if (json.retStatus === 'F') {
                       showMsg(json.retMsg, 'info');
                        $("#attrKey").val("");
                 } else if (json.retStatus === 'S') {
                         if(json.data.valueMethod==="VL"){
                           $("#refTable").attr("disabled",false);
                           $("#refCondition").attr("disabled",false);
                           $("#refColumns").attr("disabled",false);
                           $("#valueDesc").attr("disabled",false);
                           $("#attrValue").attr("disabled",true);
                           $("#valueDesc").attr("datatype","*1-40");
                           $("#refTable").attr("disabled",true);
                           $("#refCondition").attr("disabled",true);
                           $("#refColumns").attr("disabled",true);
                         }else if(json.data.valueMethod==="RF"){
                           $("#refTable").attr("disabled",false);
                           $("#refCondition").attr("disabled",false);
                           $("#refColumns").attr("disabled",false);
                           $("#valueDesc").attr("disabled",false);
                           $("#attrValue").val(" ");
                           $("#valueDesc").val(" ");
                           $("#attrValue").attr("disabled",true);
                           $("#valueDesc").attr("disabled",true);
                           $("#refTable").attr("datatype","*1-40");
                           $("#refCondition").attr("datatype","*1-40");
                           $("#refColumns").attr("datatype","*1-40");
                         }
                        return;
                 }
             },
             dataType: "json"
         });
    }

    $("#mbAttrValueMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAttrValueMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAttrValueMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ATTR_VALUE";
    keyFieldsJson.ATTR_KEY=$("#attrKey").val();
    keyFieldsJson.ATTR_VALUE=$("#attrValue").val();
    generalFieldsJson.VALUE_DESC=$("#valueDesc").val();
    generalFieldsJson.REF_COLUMNS=$("#refColumns").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.REF_CONDITION=$("#refCondition").val();
    generalFieldsJson.REF_TABLE=$("#refTable").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAttrValueMod,"json");
}

function callback_mbAttrValueMod(json){
    if (json.success) {
       if (parent.$("#mbAttrValue").find(".selected").length===1){
        rowData = parent.$('#mbAttrValue').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbAttrValue").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ATTR_KEY:$("#attrKey").val(),
            ATTR_VALUE:$("#attrValue").val(),
            VALUE_DESC:$("#valueDesc").val(),
            REF_COLUMNS:$("#refColumns").val(),
            REF_CONDITION:$("#refCondition").val(),
            REF_TABLE:$("#refTable").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAttrValueModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

