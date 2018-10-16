    var form;
    $(document).ready(function () {
         getPkList({
                    url: contextPath + "/findParaCombox",
                    id: "appIndexIsview",
                    async: false,
                    params: {
                        paraParentId: '0009',
                        isDefault: false
                    }
                });
        form = $("#form-parameter-add").Validform({
            tiptype: 2,
            callback: function (form) {
                appInfoAdd();
                return false;
            }
        });
        $(".select2").select2();
    });
    function appInfoAdd() {
        var url = contextPath + "/saveAppmoniIndex";
        sendPostRequest(url, getFormData("form-parameter-add"), callback_appInfoAdd, "json");
    }

    function callback_appInfoAdd(json) {
        if (json.success) {
            var dataTable=parent.$("#indexList").DataTable();
            dataTable.row.add({
                appIndexName: $("#appIndexName").val(),
                appIndexNo: $("#appIndexNo").val(),
                appIndexIsviewName:$("#appIndexIsview").find("option:selected").text(),
                appIndexDesc: $("#appIndexDesc").val(),
                appIndexFac: $("#appIndexFac").val(),
            }).draw(false);
            parent.showMsgDuringTime("添加成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        /*form.resetForm();  //JS表单清空
        appInfoAddCancel();*/
    }
    function appInfoAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }