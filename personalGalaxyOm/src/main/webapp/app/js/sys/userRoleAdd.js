var form;
$(document).ready(function () {
    //获取userName
    getPkList({
        url: contextPath + "/pklist/getNameToAddUser",
        id: "userName",
        async: false
    });
    $('#userName').select2({
        maximumResultsForSearch: Infinity
    });
    //获取roleName
    getPkList({
        url: contextPath + "/pklist/getUserRoleName",
        id: "roleName",
        async: false
    });
    $('#roleName').select2({
        maximumResultsForSearch: Infinity
    });
    form = $("#form-userRole-add").Validform({
        tiptype: 2,
        callback: function () {
            userRoleAdd();
            return false;
        }
    });
});

function userRoleAdd() {
    var url = contextPath + "/userRole/insert";
    sendPostRequest(url, {
        userId: $("#userName").val(),
        roleId: $("#roleName").val()
    }, callbackUserRoleAdd, "json");
}

function callbackUserRoleAdd(json) {
    if (json.success) {
        var userInfo = $("#userName").find("option:selected").text().split(" ");
        var roleInfo = $("#roleName").find("option:selected").text().split(" ");
        var userName = userInfo[0];
        var message = userInfo[1];
        var roleName = roleInfo[0];
        var roleDesc = roleInfo[1];
        var dataTable = parent.$('#userRoleList').DataTable();
        dataTable.row.add({
            userId: $("#userName").val(),
            roleId: $("#roleName").val(),
            userName: userName,
            message: message,
            roleName: roleName,
            roleDesc: roleDesc
        }).draw(false);
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
    form.resetForm();
}

function userRoleAddCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}