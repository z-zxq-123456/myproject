    var form;
    var rowData;
    $(document).ready(function () {
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



        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
            id: "company",
            async: false,
        });
        var rowData;

        if (parent.$("#irlAmtExpression").find(".selected").length === 1) {
            rowData = parent.$('#irlAmtExpression').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#expressId").val(rowData.EXPRESS_ID).attr("disabled", true);

            $("#company").val(rowData.COMPANY);


            $("#expression").val(rowData.EXPRESSION).attr("disabled", true);


            $("#expressionDesc").val(rowData.EXPRESSION_DESC);

        }
        $(".select2").select2();
        form = $("#irlAmtExpressionMod").Validform({
            tiptype: 2,
            callback: function (form) {
                irlAmtExpressionMod();
                return false;
            }
        });

    });

    function irlAmtExpressionMod() {
        var paraJson, generalFieldsJson, keyFieldsJson;
        paraJson = {};
        generalFieldsJson = {};
        keyFieldsJson = {};
        var url = contextPath + "/baseCommon/updateAndInsertForUpdate";
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
        sendPostRequest(url, params, callback_irlAmtExpressionMod, "json");
    }

    function callback_irlAmtExpressionMod(json) {
        if (json.success) {
            var dataTable=parent.$("#irlAmtExpression").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                EXPRESS_ID:$("expressId").val(),
                COMPANY:$("company").val(),
                EXPRESSION:$("expression").val(),
                EXPRESSION_DESC:$("expressionDesc").val(),
                COLUMN_STATUS:'W'
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        //form.resetForm();  //JS表单清空

    }
    function irlAmtExpressionModCancel() {
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


