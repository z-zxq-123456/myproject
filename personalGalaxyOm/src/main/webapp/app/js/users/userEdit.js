var form;
$(document).ready(function () {
    var rowData;
    if (parent.$("#userList").find(".selected").length === 1) {
        rowData = parent.$('#userList').DataTable().rows(".selected").data()[0];
    }
    if (rowData) {
        $("#userId").val(rowData.userId);
        $("#userName").val(rowData.userName);
        $("#passwordConfirm").val(rowData.password);
        $("#password").val(rowData.password);
        $("#message").val(rowData.message);
        $("#legalentity").val(rowData.legalentity);
        $("#organization").val(rowData.organization);
        form = $("#form-user-edit").Validform({
            tiptype: 2,
            callback: function () {
                userEdit();
                return false;
            }
        });
    }
});

function userEdit() {
    if($("#password").val()!==$("#passwordConfirm").val()){
        showMsg("两次密码输入不同，请确认！");
        return;
    }
    var url = contextPath + "/user/updateUser";
    sendPostRequest(url, {
        userId: $("#userId").val(),
        userName: $("#userName").val(),
        password: $("#password").val(),
        legalentity: $("#legalentity").val(),
        organization: $("#organization").val(),
        message: $("#message").val()
    }, callbackUserEdit, "json");
}

function callbackUserEdit(json) {
    if (json.retStatus === 'F') {
        showMsg(json.retMsg, 'info');
    } else if (json.success) {
        var dataTable=parent.$("#userList").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            userId: $("#userId").val(),
            userName: $("#userName").val(),
            password: $("#password").val(),
            legalentity: $("#legalentity").val(),
            organization: $("#organization").val(),
            message: $("#message").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    }
}
function userEditCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}