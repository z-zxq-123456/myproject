
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlIntBasis").find(".selected").length===1){
        rowData = parent.$('#irlIntBasis').DataTable().rows(".selected").data()[0];
            $("#intBasis").val(rowData.INT_BASIS).attr("disabled",true);
            $("#intBasisDesc").val(rowData.INT_BASIS_DESC);
            $("#company").val(rowData.COMPANY);
    }

    $("#irlIntBasisMod").Validform({
        tiptype:2,
        callback:function(form){
            irlIntBasisMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlIntBasisMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_INT_BASIS";
        keyFieldsJson.INT_BASIS=$("#intBasis").val();
        generalFieldsJson.INT_BASIS_DESC=$("#intBasisDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlIntBasisMod,"json");
}

function callback_irlIntBasisMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlIntBasis").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INT_BASIS:$("intBasis").val(),
            INT_BASIS_DESC:$("intBasisDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlIntBasisModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

