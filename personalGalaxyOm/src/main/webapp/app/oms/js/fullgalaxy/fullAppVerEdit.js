    var form;
    $(document).ready(function () {
        var rowData;
        if (parent.$("#appVerList").find(".selected").length == 1) {
            rowData = parent.$('#appVerList').DataTable().rows(".selected").data()[0];
        }
        getPkList({
            url: contextPath + "/findParaCombox",
            id: "appVerType",
            async: false,
            params: {
                paraParentId: '0022',
                isDefault: false
            }
        });
        if (rowData) {
            $("#appVerId").val(rowData.appVerId);
            $("#appVerType").val(rowData.appVerType);
            $("#appVerDesc").val(rowData.appVerDesc);
            $("#appVerDate").val(rowData.appVerDate);
            $("#appVerPath").val(rowData.appVerPath);
            form = $("#form-appVerInfo-edit").Validform({
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
        var url = contextPath + "/updateAppVer";
        var rowData = parent.$('#appVerList').DataTable().rows(".selected").data()[0];
        sendPostRequest(url, {
            midwareVerId: rowData.midwareVerId,
            appVerId: $("#appVerId").val(),
            appVerPath: $("#appVerPath").val(),
            appVerDate: $("#appVerDate").val(),
            appVerDesc: $("#appVerDesc").val(),
            appVerType: $("#appVerType").val()
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    function dataEditCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }