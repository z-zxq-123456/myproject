    var form;
    var rowData;
    $(document).ready(function () {

        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0009&&isDefault=true",
            id: "appIndexIsview",
            async: false,
            normalSelect: false
        });
        if (parent.$("#indexList").find(".selected").length == 1) {
            rowData = parent.$('#indexList').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#appIndexName").val(rowData.appIndexName);
            $("#appIndexNo").val(rowData.appIndexNo);
            $("#appIndexIsview").val(rowData.appIndexIsview);
            $("#appIndexDesc").val(rowData.appIndexDesc);
            $("#appIndexFac").val(rowData.appIndexFac);
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
        var url = contextPath + "/updateAppmoniIndex";
        sendPostRequest(url, {
                       appIndexId:rowData.appIndexId,
                       appIndexName: $("#appIndexName").val(),
                       appIndexNo: $("#appIndexNo").val(),
                       appIndexIsview:$("#appIndexIsview").val(),
                       appIndexDesc: $("#appIndexDesc").val(),
                       appIndexFac: $("#appIndexFac").val(),
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            var dataTable = parent.$("#indexList").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                 appIndexId:rowData.appIndexId,
                 appIndexName: $("#appIndexName").val(),
                 appIndexNo: $("#appIndexNo").val(),
                 appIndexIsviewName:$("#appIndexIsview").find("option:selected").text(),
                 appIndexDesc: $("#appIndexDesc").val(),
                 appIndexFac: $("#appIndexFac").val(),

            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        //dataEditCancel();
    }
    function dataEditCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }