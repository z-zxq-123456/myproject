    var form;
    $(document).ready(function () {
        form = $("#form-servRule-add").Validform({
            tiptype: 2,
            callback: function (form) {
                midwareVerAdd();
                return false;
            }
        });
        getPkList({
            url: contextPath + "/findParaCombox",
            id: "servRuleTypeName",
            async: false,
            params: {
                paraParentId: '0026',
                isDefault: false
            }
        });
        getPkList({
            url: contextPath + "/findParaCombox",
            id: "routerArgsType",
            async: false,
            params: {
                paraParentId: '0025',
                isDefault: false
            }
        });
        getPkList({
            url: contextPath + "/findEcmSerMtdinfoCombox",
            id: "serMtdId",
            async: false,
        });
        getPkList({
            url: contextPath + "/findEcmRouterCond",
            id: "routerCondId",
            async: false,
        });
        getPkList({
            url: contextPath + "/findParaCombox",
            id: "ruleStatus",
            async: false,
            params: {
                paraParentId: '0001',
                isDefault: false
            }
        });

        $(".select2").select2();
        $("#condDiv").hide();
        $("#posDiv").hide();
        $("#typeDiv").hide();
        $("#selfDiv").hide();
         $("#servRuleTypeName").change(function () {
                var servRuleType = $("#servRuleTypeName").val();
                if (servRuleType == "0026001") {
                    $("#condDiv").show();
                    $("#posDiv").show();
                    $("#typeDiv").show();
                    $("#selfDiv").hide();
                    $("#routerArgsPos").attr("datatype", "n1-20");
                } else if (servRuleType == "0026002") {
                    $("#condDiv").hide();
                    $("#posDiv").hide();
                    $("#typeDiv").hide();
                    $("#selfDiv").show();
                    $("#routerArgsPos").attr("datatype", "n0-20");
                }

            });
    });

    function midwareVerAdd() {
        var url = contextPath + "/saveEcmServRule";
        var servRuleType = $("#servRuleTypeName").val();
        if (servRuleType == "0026001") {
            sendPostRequest(url, {
                servRuleType: $("#servRuleTypeName").val(),
                serMtdId: $("#serMtdId").val(),
                ruleStatus: $("#ruleStatus").val(),
                routerCondId: $("#routerCondId").val(),
                routerArgsPos: $("#routerArgsPos").val(),
                routerArgsType: $("#routerArgsType").val(),
            }, callback_midwareVerAdd, "json");
        } else if (servRuleType == "0026002") {
            sendPostRequest(url, {
                servRuleType: $("#servRuleTypeName").val(),
                serMtdId: $("#serMtdId").val(),
                ruleStatus: $("#ruleStatus").val(),
                servRuleSelf: $("#servRuleSelf").val(),
            }, callback_midwareVerAdd, "json");
        }
    }

    function callback_midwareVerAdd(json) {
        if (json.success) {
          	parent.showMsgDuringTime("添加成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        //form.resetForm();  //JS表单清空
    }
    function midwareVerAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }