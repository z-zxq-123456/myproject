
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_RELATION_TYPE&tableCol=RELATION_TYPE,RELATION_DESC",
        id: "counterRel",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifRelationType").find(".selected").length===1){
        rowData = parent.$('#cifRelationType').DataTable().rows(".selected").data()[0];
        $("#relationType").val(rowData.RELATION_TYPE).attr("disabled",true);
        $("#authorised").val(rowData.AUTHORISED);
        $("#relative").val(rowData.RELATIVE);
        $("#relationDesc").val(rowData.RELATION_DESC);
        $("#joinCollat").val(rowData.JOIN_COLLAT);
        $("#jointAcct").val(rowData.JOINT_ACCT);
        $("#exposure").val(rowData.EXPOSURE);
        $("#equity").val(rowData.EQUITY);
        $("#employment").val(rowData.EMPLOYMENT);
        $("#symmentric").val(rowData.SYMMENTRIC);
        $("#counterRel").val(rowData.COUNTER_REL);
        $("#relationTypeFlag").val(rowData.RELATION_TYPE_FLAG);
        $("#company").val(rowData.COMPANY);
    }

    $("#cifRelationTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            cifRelationTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifRelationTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_RELATION_TYPE";
    keyFieldsJson.RELATION_TYPE=$("#relationType").val();
    generalFieldsJson.AUTHORISED=$("#authorised").val();
    generalFieldsJson.RELATIVE=$("#relative").val();
    generalFieldsJson.RELATION_DESC=$("#relationDesc").val();
    generalFieldsJson.JOIN_COLLAT=$("#joinCollat").val();
    generalFieldsJson.JOINT_ACCT=$("#jointAcct").val();
    generalFieldsJson.EXPOSURE=$("#exposure").val();
    generalFieldsJson.EQUITY=$("#equity").val();
    generalFieldsJson.EMPLOYMENT=$("#employment").val();
    generalFieldsJson.SYMMENTRIC=$("#symmentric").val();
    generalFieldsJson.COUNTER_REL=$("#counterRel").val();
    generalFieldsJson.RELATION_TYPE_FLAG=$("#relationTypeFlag").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifRelationTypeMod,"json");
}

function callback_cifRelationTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifRelationType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            RELATION_TYPE:$("#relationType").val(),
            AUTHORISED:$("#authorised").val(),
            RELATIVE:$("#relative").val(),
            RELATION_DESC:$("#relationDesc").val(),
            JOIN_COLLAT:$("#joinCollat").val(),
            JOINT_ACCT:$("#jointAcct").val(),
            EXPOSURE:$("#exposure").val(),
            EQUITY:$("#equity").val(),
            EMPLOYMENT:$("#employment").val(),
            SYMMENTRIC:$("#symmentric").val(),
            COUNTER_REL:$("#counterRel").val(),
            RELATION_TYPE_FLAG:$("#relationTypeFlag").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifRelationTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

