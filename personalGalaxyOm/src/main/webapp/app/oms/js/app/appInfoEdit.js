    var form;
    var appId;
    $(document).ready(function () {

        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0021&&isDefault=false",
            id: "appType",
            async: false
        });
        getPkList({
            url: contextPath + "/findMidwareCombox?midwareType=0006001",
            id: "redisMidwareName",
            async: false
        });
        getPkList({
            url: contextPath + "/findMidwareCombox?midwareType=0006002",
            id: "zkMidwareName",
            async: false
        });
        getPkList({
            url: contextPath + "/findMidwareCombox?midwareType=0006003",
            id: "dbMidwareName",
            async: false
        });

        var rowData;
        if (parent.$("#appList").find(".selected").length == 1) {
            rowData = parent.$('#appList').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            appId = rowData.appId;
            $("#appName").val(rowData.appName);
            $("#appSimpenNm").val(rowData.appSimpenNm);
            $("#appType").val(rowData.appType);
            $("#redisMidwareName").val(rowData.midwareRedisId);
            $("#zkMidwareName").val(rowData.midwareZKId);
            $("#dbMidwareName").val(rowData.midwareDBId);
            $("#appPath").val(rowData.appPath);
            $("#appDesc").val(rowData.appDesc);
            form = $("#form-appInfo-edit").Validform({
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
        var url = contextPath + "/updateEcmAppInfo";
        sendPostRequest(url, {
            appId: appId,
            appName: $("#appName").val(),
            appSimpenNm: $("#appSimpenNm").val(),
            appType: $("#appType").val(),
            midwareRedisId: $("#redisMidwareName").val(),
            midwareZKId: $("#zkMidwareName").val(),
            midwareDBId: $("#dbMidwareName").val(),
            appPath: $("#appPath").val(),
            appDesc: $("#appDesc").val()
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
             var dataTable=parent.$("#appList").DataTable();
             dataTable.row(".selected").remove().draw(false);
             dataTable.row.add({
                 appId: appId,
                 appName: $("#appName").val(),
                 appSimpenNm: $("#appSimpenNm").val(),
                 appTypeName: $("#appType option:selected").val(),
                 redisMidwareName: $("#redisMidwareName option:selected").text(),
                 zkMidwareName: $("#zkMidwareName option:selected").text(),
                 dbMidwareName: $("#dbMidwareName option:selected").text(),
                 appPath: $("#appPath").val(),
                 appDesc: $("#appDesc").val()
             }).draw(false);
             parent.reference();
             parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    function dataEditCancel() {
            var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
            parent.layer.close(index);  //关闭窗口
        }