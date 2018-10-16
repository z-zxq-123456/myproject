    var form;
    $(document).ready(function () {

        getPkList({
                       url: contextPath + "/findParaCombox?paraParentId=0001&&isDefault=true",
                       id: "isValidate",
                       async: false,
                       normalSelect: false
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
        var url = contextPath + "/savePara";
        sendPostRequest(url, getFormData("form-parameter-add"), callback_appInfoAdd, "json");
    }

    function callback_appInfoAdd(json) {
        if (json.success) {
            var dataTable=parent.$("#parameterList").DataTable();
            dataTable.row.add({
                paraCode: $("#paraCode").val(),
                paraName: $("#paraName").val(),
                paraParentId: $("#paraParentId").val(),
                isValidate: $("#isValidate").val(),
                isValidateName:$("#isValidate").find("option:selected").text(),
                remark1: $("#remark1").val(),
                remark2: $("#remark2").val(),
                remark3: $("#remark3").val(),
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