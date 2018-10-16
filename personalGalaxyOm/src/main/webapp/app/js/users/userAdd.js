 var form;
    $(document).ready(function () {
        form = $("#form-user-add").Validform({
            tiptype: 2,
            callback: function () {
                userAdd();
                return false;
            }
        });
    });

    function userAdd() {
        if($("#password").val()!==$("#passwordConfirm").val()){
            showMsg("两次密码输入不同，请确认！");
            return;
        }
        var url = contextPath + "/user/saveUser";
        sendPostRequest(url, {
            userId: $("#userId").val(),
            userName: $("#userName").val(),
            password: $("#password").val(),
            legalentity: $("#legalentity").val(),
            organization: $("#organization").val(),
            message: $("#message").val()
        }, callbackUserAdd, "json");
    }

    function callbackUserAdd(json) {
        if (json.retStatus === 'F') {
            showMsg(json.retMsg, 'info');
        } else if (json.success) {
            var dataTable=parent.$("#userList").DataTable();
            dataTable.row.add({
                userId: $("#userId").val(),
                userName: $("#userName").val(),
                password: $("#password").val(),
                legalentity: $("#legalentity").val(),
                organization: $("#organization").val(),
                message: $("#message").val()
            }).draw(false);
            parent.showMsgDuringTime("添加成功");
        }
    }
    function userAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }