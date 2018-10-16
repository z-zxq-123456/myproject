    var form;
    $(document).ready(function () {
        getPkList({
            url: contextPath + "/findUpgflowNodeComboxA",
            id: "upgflowNodeId",
            async: false
        });
        getPkList({
            url: contextPath + "/findUpgflowNodeComboxB",
            id: "upgndBtnDirnodeid",
            async: false
        });
        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0009&&isDefault=false",
            id: "upgndBtnIsview",
            async: false
        });
        form = $("#form-data-add").Validform({
            tiptype: 2,
            callback: function (form) {
                dataAdd();
                return false;
            }
        });
        $(".select2").select2();
    });
    function dataAdd() {
        var url = contextPath + "/saveEcmUpgndBtn";
        sendPostRequest(url, getFormData("form-data-add"), callback_dataAdd, "json");
    }

    function callback_dataAdd(json) {
        if (json.success) {
            var dataTable=parent.$('#table_data').DataTable();
            dataTable.row.add({
                upgndBtnId: json.id,
                upgflowNodeId: $("#upgflowNodeId").val(),
                upgflowNodeName:$("#upgflowNodeId").find("option:selected").text(),
                upgndBtnName: $("#upgndBtnName").val(),
                upgndBtnSeq: $("#upgndBtnSeq").val(),
                upgndBtnClass: $("#upgndBtnClass").val(),
                upgndBtnDirnodeid: $("#upgndBtnDirnodeid").val(),
                upgndBtnIsview: $("#upgndBtnIsview").val(),
                upgndBtnIsviewName:$("#upgndBtnIsviewName").val(),
                upgndBtnFunc: $("#upgndBtnFunc").val()
            }).draw(false);
            parent.showMsgDuringTime("添加成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        form.resetForm();  //JS表单清空
        //dataAddCancel();
    }
    function dataAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }