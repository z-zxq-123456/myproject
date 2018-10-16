
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifClientAttrDef").find(".selected").length===1){
        rowData = parent.$('#cifClientAttrDef').DataTable().rows(".selected").data()[0];
        $("#attrType").val(rowData.ATTR_TYPE).attr("disabled",true);
        $("#lossInd").val(rowData.LOSS_IND);
        $("#attrTypeDesc").val(rowData.ATTR_TYPE_DESC);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifClientAttrDefMod").Validform({
        tiptype:2,
        callback:function(form){
            cifClientAttrDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifClientAttrDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CLIENT_ATTR_DEF";
    keyFieldsJson.ATTR_TYPE=$("#attrType").val();
    generalFieldsJson.LOSS_IND=$("#lossInd").val();
    generalFieldsJson.ATTR_TYPE_DESC=$("#attrTypeDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifClientAttrDefMod,"json");
}

function callback_cifClientAttrDefMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifClientAttrDef").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        ATTR_TYPE:$("#attrType").val()
        ,LOSS_IND:$("#lossInd").val()
        ,ATTR_TYPE_DESC:$("#attrTypeDesc").val()
        ,COMPANY:$("#company").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifClientAttrDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

