    var form;
    $(document).ready(function () {
        var rowData;
        if (parent.$("#dataList").find(".selected").length == 1) {
            rowData = parent.$('#dataList').DataTable().rows(".selected").data()[0];
        }
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
        if (rowData) {
            $("#appId").val(rowData.appId);
            $("#appIntantId").val(rowData.appIntantId);
            $("#serId").val(rowData.serId);
            $("#serIp").val(rowData.serIp);
            $("#appIntantName").val(rowData.appIntantName);
            $("#appIntantDesc").val(rowData.appIntantDesc);
            form = $("#form-data-edit").Validform({
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
        var url = contextPath + "/updateEcmAppIntantConfig";
        sendPostRequest(url, {
            appIntantId: $("#appIntantId").val(),
            appId: $("#appId").val(),
            serId: $("#serId").val(),
            serIp: $("#serId").val(),
            appIntantName: $("#appIntantName").val(),
            appIntantDesc: $("#appIntantDesc").val(),
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            parent.showMsgDuringTime("编辑成功");
            dataEditCancel();
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }

    }
    function dataEditCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }