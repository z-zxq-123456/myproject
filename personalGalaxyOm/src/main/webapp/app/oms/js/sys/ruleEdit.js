    var form;
    $(document).ready(function () {

        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0130&&isDefault=true",
            id: "ruleRank",
            async: false,
            normalSelect: false
        });
        var rowData;
        if (parent.$("#parameterList").find(".selected").length == 1) {
            rowData = parent.$('#parameterList').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#ruleId").val(rowData.ruleId);
            $("#ruleName").val(rowData.ruleName);
            $("#ruleDesc").val(rowData.ruleDesc);
            $("#ruleRank").val(rowData.ruleRank);
            form = $("#form-parameter-edit").Validform({
                tiptype: 2,
                callback: function (form) {
                    dataEdit();
                    return false;
                }
            });
        }
        $(".select2").select2();
    });

    function dataEdit() {
        var url = contextPath + "/updateRule";
        var nameVal = $("#ruleName").val();
        sendPostRequest(url, getFormData("form-parameter-edit"), callback_dataEdit, "json");

        /*{
            ruleId:$("#ruleId").val(),
            ruleName: $("#ruleName").val(),
            ruleRank: $("#ruleRank").val(),
            ruleDesc: $("#ruleDesc").val(),
        }*/

    }

    function callback_dataEdit(json) {
        if (json.success) {
            var dataTable = parent.$("#parameterList").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                ruleId:$("#ruleId").val(),
                ruleName: $("#ruleName").val(),
                ruleRank: $("#ruleRank").val(),
                ruleDesc: $("#ruleDesc").val(),
                ruleRankName:$("#ruleRank").find("option:selected").text(),
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
//        dataEditCancel();
    }
    function dataEditCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }