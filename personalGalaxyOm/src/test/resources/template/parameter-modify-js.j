var rowData;
$(document).ready(function() {
<#list updateCol as col>
<#if col.valueMethod == 'RF'>
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=${col.refTable}&tableCol=${col.refCol}",
        id: "${col.paramName}",
        async: false
    });
</#if>
</#list>

    if (parent.$("#${beanName}").find(".selected").length===1){
        rowData = parent.$('#${beanName}').DataTable().rows(".selected").data()[0];
    <#list pks as pk>
        $("#${pk.paramName}").val(rowData.${pk.colName}).attr("disabled",true);
    </#list>
    <#list updateCol as col>
        $("#${col.paramName}").val(rowData.${col.colName});
    </#list>
    }

    $("#${beanName}Mod").Validform({
        tiptype:2,
        callback:function(){
            ${beanName}Mod();
            return false;
        }
    });

    $(".select2").select2();
});

function ${beanName}Mod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="${TableName}";
<#list pks as pk>
    keyFieldsJson.${pk.colName}=$("#${pk.paramName}").val();
</#list>
<#list updateCol as update>
    generalFieldsJson.${update.colName}=$("#${update.paramName}").val();
</#list>
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_${beanName}Mod,"json");
}

function callback_${beanName}Mod(json){
    if (json.success) {
        if (parent.$("#${beanName}").find(".selected").length===1){
            rowData = parent.$('#${beanName}').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#${beanName}").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
            <#list pks as pk>
                ${pk.colName}:$("#${pk.paramName}").val(),
            </#list>
            <#list updateCol as update>
                ${update.colName}:$("#${update.paramName}").val(),
            </#list>
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function ${beanName}ModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}