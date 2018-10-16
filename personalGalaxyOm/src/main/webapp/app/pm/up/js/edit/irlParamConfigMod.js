
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlParamConfig").find(".selected").length===1){
        rowData = parent.$('#irlParamConfig').DataTable().rows(".selected").data()[0];
            $("#paramName").val(rowData.PARAM_NAME).attr("disabled",true);
            $("#paramDesc").val(rowData.PARAM_DESC);
            $("#paramValue").val(rowData.PARAM_VALUE);
            $("#remark").val(rowData.REMARK);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlParamConfigMod").Validform({
        tiptype:2,
        callback:function(form){
            irlParamConfigMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlParamConfigMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_PARAM_CONFIG";
        keyFieldsJson.PARAM_NAME=$("#paramName").val();
        generalFieldsJson.PARAM_DESC=$("#paramDesc").val();
        generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
        generalFieldsJson.REMARK=$("#remark").val();

    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlParamConfigMod,"json");
}

function callback_irlParamConfigMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlParamConfig").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PARAM_NAME:$("paramName").val(),
            PARAM_DESC:$("paramDesc").val(),
            PARAM_VALUE:$("paramValue").val(),
            REMARK:$("remark").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlParamConfigModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

