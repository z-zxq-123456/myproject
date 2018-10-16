    var form;
    $(document).ready(function () {
        form = $("#form-patternMetadata-add").Validform({
            tiptype: 2,
            callback: function (form) {
                patternMetadataAdd();
                return false;
            }
        });
        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0030&&isDefault=false'",
            id: "serOs",
            async: false
        });

        $(".select2").select2();
    });
    function patternMetadataAdd() {
        if($("#serPwd").val()!=$("#serPwdConfirm").val()){
            showMsg("两次输入密码不同，重新输入！");
            return;
        }
        var url = contextPath + "/saveEcmServerInfo";
        sendPostRequest(url, getFormData("form-patternMetadata-add"), callback_patternMetadataAdd, "json");
    }

    function callback_patternMetadataAdd(json) {
        if (json.success) {
            var dataTable=parent.$('#EcmServerList').DataTable();
            dataTable.row.add({
                serId: json.id,
                serName: $("#serName").val(),
                serIp: $("#serIp").val(),
                serUser: $("#serUser").val(),
                serOs: $("#serOs").val(),
                serOsName:$("#serOs").find("option:selected").text(),
            }).draw(false);
            parent.referch();
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        form.resetForm();  //JS表单清空
    }
    function patternMetadataAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }