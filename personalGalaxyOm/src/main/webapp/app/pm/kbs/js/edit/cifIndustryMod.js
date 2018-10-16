
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#cifIndustry").find(".selected").length===1){
        rowData = parent.$('#cifIndustry').DataTable().rows(".selected").data()[0];
        $("#industry").val(rowData.INDUSTRY).attr("disabled",true);
        $("#detailInd").val(rowData.DETAIL_IND);
        $("#industryDesc").val(rowData.INDUSTRY_DESC);
        $("#industryLevel").val(rowData.INDUSTRY_LEVEL);
        $("#standardInd").val(rowData.STANDARD_IND);
        $("#company").val(rowData.COMPANY);
        $("#parentIndustry").val(rowData.PARENT_INDUSTRY);
        $("#riskLevel").val(rowData.RISK_LEVEL);
    }

    $("#cifIndustryMod").Validform({
        tiptype:2,
        callback:function(form){
            cifIndustryMod();
            return false;
        }
    });

    $(".select2").select2();
});

function cifIndustryMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="CIF_INDUSTRY";
    keyFieldsJson.INDUSTRY=$("#industry").val();
    generalFieldsJson.DETAIL_IND=$("#detailInd").val();
    generalFieldsJson.INDUSTRY_DESC=$("#industryDesc").val();
    generalFieldsJson.INDUSTRY_LEVEL=$("#industryLevel").val();
    generalFieldsJson.STANDARD_IND=$("#standardInd").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.PARENT_INDUSTRY=$("#parentIndustry").val();
    generalFieldsJson.RISK_LEVEL=$("#riskLevel").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_cifIndustryMod,"json");
}

function callback_cifIndustryMod(json){
    if (json.success) {
        var dataTable=parent.$("#cifIndustry").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            INDUSTRY:$("#industry").val(),
            DETAIL_IND:$("#detailInd").val(),
            INDUSTRY_DESC:$("#industryDesc").val(),
            INDUSTRY_LEVEL:$("#industryLevel").val(),
            STANDARD_IND:$("#standardInd").val(),
            COMPANY:$("#company").val(),
            PARENT_INDUSTRY:$("#parentIndustry").val(),
            RISK_LEVEL:$("#riskLevel").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function cifIndustryModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

