var rowData;
$(document).ready(function() {
          var paraJson, keyFieldsJson;
                      paraJson = {};
                      keyFieldsJson = {};
                      paraJson.tableName = "FM_REF_CODE";
                      paraJson.tableCol="FIELD_VALUE,MEANING";

                      keyFieldsJson.DOMAIN = "PROD_GRP";
                      paraJson.key = keyFieldsJson;
               getPkList({
                                url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
                                           id: "prodGrp",
                                           async: false

                          });
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlProdType").find(".selected").length===1){
        rowData = parent.$('#irlProdType').DataTable().rows(".selected").data()[0];
            $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
            $("#prodGrp").val(rowData.PROD_GRP);
            $("#prodTypeDesc").val(rowData.PROD_TYPE_DESC);
            $("#company").val(rowData.COMPANY);
            $("#fixedCall").val(rowData.FIXED_CALL);
    }

    $("#irlProdTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            irlProdTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlProdTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_PROD_TYPE";
    paraJson.systemId="UP";
        keyFieldsJson.PROD_TYPE=$("#prodType").val();
        generalFieldsJson.PROD_GRP=$("#prodGrp").val();
        generalFieldsJson.PROD_TYPE_DESC=$("#prodTypeDesc").val();
        generalFieldsJson.COMPANY=$("#company").val();
        generalFieldsJson.FIXED_CALL=$("#fixedCall").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlProdTypeMod,"json");
}

function callback_irlProdTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlProdType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROD_TYPE:$("prodType").val(),
            PROD_GRP:$("prodGrp").val(),
            PROD_TYPE_DESC:$("prodTypeDesc").val(),
            COMPANY:$("company").val(),
            FIXED_CALL:$("fixedCall").val(),
            COLUMN_STATUS:'W'
        }).draw(false);

        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlProdTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
