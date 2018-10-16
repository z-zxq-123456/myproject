
var rowData;
$(document).ready(function() {

    if (parent.$("#irlRuleGroup").find(".selected").length===1){
        rowData = parent.$('#irlRuleGroup').DataTable().rows(".selected").data()[0];
        $("#groupType").val(rowData.GROUP_TYPE).attr("disabled",true);
        $("#weight").val(rowData.WEIGHT);
        $("#grpMatchType").val(rowData.GROUP_MATCH_TYPE);
        $("#groupClass").val(rowData.GROUP_CLASS);
        $("#groupTypeDesc").val(rowData.GROUP_TYPE_DESC);
    }

    $("#irlRuleGroupMod").Validform({
        tiptype:2,
        callback:function(form){
            irlRuleGroupMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlRuleGroupMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_RULE_GROUP";
    keyFieldsJson.GROUP_TYPE=$("#groupType").val();
    generalFieldsJson.WEIGHT=$("#weight").val();
    generalFieldsJson.GROUP_MATCH_TYPE=$("#grpMatchType").val();
    generalFieldsJson.GROUP_CLASS=$("#groupClass").val();
    generalFieldsJson.GROUP_TYPE_DESC=$("#groupTypeDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlRuleGroupMod,"json");
}

function callback_irlRuleGroupMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlRuleGroup").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            GROUP_TYPE:$("#groupType").val(),
            WEIGHT:$("#weight").val(),
            GROUP_MATCH_TYPE:$("#grpMatchType").val(),
            GROUP_CLASS:$("#groupClass").val(),
            GROUP_TYPE_DESC:$("#groupTypeDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlRuleGroupModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

