
    var form;
    $(document).ready(function () {
    $("#expression").attr("disabled", true)

        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
            id: "company",
            async: false,
        });
        $(".select2").select2();

        var paraJson, keyFieldsJson;
        paraJson = {};
        keyFieldsJson = {};
        paraJson.tableName = "FM_REF_CODE";
        paraJson.tableCol="FIELD_VALUE,MEANING";

        keyFieldsJson.DOMAIN = "AMT_TYPE";
        paraJson.key = keyFieldsJson;
             getPkList({
                url:contextPath+"/baseCommon/pklistBase?paraJson=" + JSON.stringify(paraJson),
                id:"amtType",
                async:false
             });
      $(".select2").select2();

        form = $("#irlAmtExpressionAdd").Validform({
            tiptype: 2,
            callback: function (form) {
                irlAmtExpressionAdd();
                return false;
            }
        });
    });
    function irlAmtExpressionAdd() {
        var paraJson, generalFieldsJson, keyFieldsJson;
        paraJson = {};
        generalFieldsJson = {};
        keyFieldsJson = {};
        var url = contextPath + "/baseCommon/updateAndInsertForSave";
        paraJson.tableName = "IRL_AMT_EXPRESSION";
        keyFieldsJson.EXPRESS_ID = $("#expressId").val();
        generalFieldsJson.COMPANY = $("#company").val();
        generalFieldsJson.EXPRESSION = $("#expression").val();
        generalFieldsJson.EXPRESSION_DESC = $("#expressionDesc").val();
        paraJson.key = keyFieldsJson;
        paraJson.general = generalFieldsJson;
        var params = {
            paraJson: JSON.stringify(paraJson)
        };
        sendPostRequest(url, params, callback_irlAmtExpressionAdd, "json");
    }
    function callback_irlAmtExpressionAdd(json) {
        if (json.success) {
            parent.showMsgDuringTime("添加成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        //form.resetForm();  //JS表单清空

    }
    function irlAmtExpressionAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }
function addClick(){
     var  str = $("#amtType").val()+$("#symbol").val()+$("#number").val();
      $("#expression").val($("#expression").val() + str);
}
function clsClick(){
      $("#expression").val("");
}
function leftClick() {
		$("#expression").val($("#expression").val() + "(");
}
function rightClick() {
		$("#expression").val($("#expression").val() + ")");
}

