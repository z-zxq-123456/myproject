
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifCrRating").find(".selected").length===1){
        rowData = parent.$('#cifCrRating').DataTable().rows(".selected").data()[0];
        $("#crRating").val(rowData.CR_RATING).attr("disabled",true);
        $("#company").val(rowData.COMPANY);
        $("#crRatingDesc").val(rowData.CR_RATING_DESC);
    }

    $("#cifCrRatingMod").Validform({
        tiptype:2,
        callback:function(form){
            cifCrRatingMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifCrRatingMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_CR_RATING";
    keyFieldsJson.CR_RATING=$("#crRating").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CR_RATING_DESC=$("#crRatingDesc").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifCrRatingMod,"json");
}

function callback_cifCrRatingMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifCrRating").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CR_RATING:$("#crRating").val(),
            COMPANY:$("#company").val(),
            CR_RATING_DESC:$("#crRatingDesc").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifCrRatingModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

