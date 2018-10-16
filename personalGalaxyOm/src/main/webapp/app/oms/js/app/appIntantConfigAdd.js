    var form;
    $(document).ready(function () {
        form = $("#form-data-add").Validform({
            tiptype: 2,
            callback: function (form) {
                dataAdd();
                return false;
            }
        });
        getPkList({
            url: contextPath + "/findAppCombox",
            id: "appId",
            async: false
        });
        getPkList({
            url: contextPath + "/findSerCombox",
            id: "serId",
            async: false
        });
        $(".select2").select2();
    });
    function dataAdd() {
        var url = contextPath + "/saveEcmAppIntantConfig";
        sendPostRequest(url, getFormData("form-data-add"), callback_dataAdd, "json");
    }

    function callback_dataAdd(json) {
        if (json.success) {
            parent.showMsgDuringTime("添加成功");

        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        form.resetForm();  //JS表单清空

    }
    function dataAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }