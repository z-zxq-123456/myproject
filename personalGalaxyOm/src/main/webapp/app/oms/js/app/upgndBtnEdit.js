    var form;
    var rowData;
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

        if (parent.$("#table_data").find(".selected").length == 1) {
            rowData = parent.$('#table_data').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            $("#upgndBtnName").val(rowData.upgndBtnName);
            $("#upgndBtnSeq").val(rowData.upgndBtnSeq);
            $("#upgndBtnClass").val(rowData.upgndBtnClass);
            $("#upgndBtnDirnodeid").val(rowData.upgndBtnDirnodeid);
            $("#upgndBtnIsview").val(rowData.upgndBtnIsview);
            $("#upgndBtnFunc").val(rowData.upgndBtnFunc);
            $("#upgflowNodeId").val(rowData.upgflowNodeId);
            form = $("#form-user-edit").Validform({
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
        var url = contextPath + "/updateEcmUpgndBtn";
        sendPostRequest(url, {
            upgndBtnId: rowData.upgndBtnId,
            upgflowNodeId: $("#upgflowNodeId").val(),
            upgndBtnName: $("#upgndBtnName").val(),
            upgndBtnSeq: $("#upgndBtnSeq").val(),
            upgndBtnClass: $("#upgndBtnClass").val(),
            upgndBtnDirnodeid: $("#upgndBtnDirnodeid").val(),
            upgndBtnIsview: $("#upgndBtnIsview").val(),
            upgndBtnFunc: $("#upgndBtnFunc").val()
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            var dataTable=parent.$("#roleList").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                upgndBtnId:rowData.upgndBtnId,
                upgflowNodeId: $("#upgflowNodeId").val(),
                upgflowNodeName:$("#upgflowNodeId").find("option:selected").text(),
            //    upgflowNodeName:$("#upgflowNodeId").val(),
                upgndBtnName: $("#upgndBtnName").val(),
                upgndBtnSeq: $("#upgndBtnSeq").val(),
                upgndBtnClass: $("#upgndBtnClass").val(),
                upgndBtnDirnodeid: $("#upgndBtnDirnodeid").val(),
                upgndBtnIsview: $("#upgndBtnIsview").val(),
                upgndBtnIsviewName:$("#upgndBtnIsviewName").val(),
                upgndBtnFunc: $("#upgndBtnFunc").val()
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