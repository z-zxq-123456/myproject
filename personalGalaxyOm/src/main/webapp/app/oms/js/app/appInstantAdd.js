    var form;
    var rowData;
    $(document).ready(function () {
        form = $("#form-data-add").Validform({
            tiptype: 2,
            callback: function (form) {
                dataAdd();
                return false;
            }
        });
        var a = parent.$("#appid").val();
        getPkList({
            url: contextPath + "/findAppVerCombox?appId=" + parent.$("#appid").val(),
            id: "demoAppIntantVer",
            async: false
        });
        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0023&&isDefault=false",
            id: "isRemainConfig",
            async: false
        });
        if (parent.$("#appInstantList").find(".selected").length == 1) {
            rowData = parent.$('#appInstantList').DataTable().rows(".selected").data()[0];
            if (rowData.appIntantStatusName == '尚未部署') {
                $("#isRemainConfig option[value='0023001']").remove();
            }
            else if (rowData.appIntantVer != null) {
                $("#demoAppIntantVer option[value=" + rowData.appIntantVer + "]").remove();
            }
            $("#serIp").val(rowData.serIp);
            $("#appIntantVer").val(rowData.appIntantVer);
            $("#appPath").val(rowData.appPath);
            form = $("#form-user-edit").Validform({
                tiptype: 2,
                callback: function (form) {
                    dataEdit();
                    return false;
                }
            });
        }
         $("#demoAppIntantVer").change(function () {
            var demoAppIntantVer = $("#demoAppIntantVer").val();
            if (demoAppIntantVer == "") {
                showMsg('部署版本号为空,请重新选择！', 'warning');
            } else {
                $.post(contextPath + "/findAppVerInfo", {
                    appId: parent.$("#appid").val(),
                    appVerNum: demoAppIntantVer
                }, function (result) {
                    var result = eval('(' + result + ')');
                    $("#appVerId").val(result.appVerInfo.appVerId);
                    $("#appVerPath").val(result.appVerInfo.appVerPath);
                    $("#appVerType").val(result.appVerInfo.appVerType);
                });
            }
         });
        $(".select2").select2();
    });
    function dataAdd() {
        var url = contextPath + "/saveAppIntant";
        rowData = parent.$('#appInstantList').DataTable().rows(".selected").data()[0];
        sendPostRequest(url, {
            appIntantId: rowData.appIntantId,
            appInstantId: rowData.appInstantId,
            serIp: $("#serIp").val(),
            appPath: $("#appPath").val(),
            appSimpenNm: rowData.appSimpenNm,
            appVerPath: $("#appVerPath").val(),
            appVerType: $("#appVerType").val(),
            serUser: rowData.serUser,
            serPwd: rowData.serPwd,
            serOs: rowData.serOs,
            appInstantName: rowData.appInstantName,
            appVerId: $("#appVerId").val(),
            appId: rowData.appId,
            appWork: $("#appWork").val(),
            appIntantVer: $("#appIntantVer").val(),
            newAppIntantVer: $("#demoAppIntantVer").val(),
            isRemainConfig: $("#isRemainConfig").val()
        }, callback_dataAdd, "json");
    }

    function callback_dataAdd(json) {
        if (json.success) {
            parent.showMsgDuringTime("部署成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    function dataAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

