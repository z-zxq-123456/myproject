    var form;
    $(document).ready(function () {

        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0001&&isDefault=true",
            id: "isValidate",
            async: false,
            normalSelect: false
        });
        var rowData;
        if (parent.$("#parameterList").find(".selected").length == 1) {
            rowData = parent.$('#parameterList').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#paraCode").val(rowData.paraCode);
            $("#paraName").val(rowData.paraName);
            $("#paraParentId").val(rowData.paraParentId);
            $("#isValidate").val(rowData.isValidate);
            $("#remark1").val(rowData.remark1);
            $("#remark2").val(rowData.remark2);
            $("#remark3").val(rowData.remark3);
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
        var url = contextPath + "/updatePara";
        sendPostRequest(url, {
            paraCode: $("#paraCode").val(),
            paraName: $("#paraName").val(),
            paraParentId: $("#paraParentId").val(),
            isValidate: $("#isValidate").val(),
            remark1: $("#remark1").val(),
            remark2: $("#remark2").val(),
            remark3: $("#remark3").val(),
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            var dataTable = parent.$("#parameterList").DataTable();
            dataTable.row(".selected").remove().draw(false);
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