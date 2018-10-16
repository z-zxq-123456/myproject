var form;
$(document).ready(function () {
    //获取userName
    getPkList({
        url: contextPath + "/pklist/getNameToUpdateUser",
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
    //选中处理
    var rowData;
    if (parent.$("#userRoleList").find(".selected").length === 1) {
        rowData = parent.$('#userRoleList').DataTable().rows(".selected").data()[0];
    }
    if (rowData) {
        $("#userName").val(rowData.userId);
        $("#roleName").val(rowData.roleId);
    }

    form = $("#form-userRole-edit").Validform({
        tiptype: 2,
        callback: function () {
            userRoleEdit();
            return false;
        }
    });
    $(".select2").select2();
});

function userRoleEdit() {
    var url = contextPath + "/userRole/update";
    sendPostRequest(url, {
        userId: $("#userName").val(),
        roleId: $("#roleName").val()
    }, callbackUserRoleEdit, "json");
}

function callbackUserRoleEdit(json) {
    if (json.success) {
        var userInfo = $("#userName").find("option:selected").text().split(" ");
        var roleInfo = $("#roleName").find("option:selected").text().split(" ");
        var userName = userInfo[0];
        var message = userInfo[1];
        var roleName = roleInfo[0];
        var roleDesc = roleInfo[1];
        var dataTable = parent.$("#userRoleList").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            userId: $("#userName").val(),
            roleId: $("#roleName").val(),
            userName: userName,
            message: message,
            roleName: roleName,
            roleDesc: roleDesc
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function userRoleEditCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}