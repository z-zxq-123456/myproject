var layer_add_index, layer_edit_index;
function showMsgDuringTime(msg) {
    showMsgCloseLayer(msg, layer_add_index, layer_edit_index);
}
$(document).ready(function () {
    var opt = getDataTableServerSideOpt($('#userList'));
    opt.ajax = {
        "url": contextPath + "/user/findAllUser",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "userId",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        },
        {
            "data": "message",
            "defaultContent": ""
        },
        {
            "data": "legalentity",
            "defaultContent": ""
        },
        {
            "data": "organization",
            "defaultContent": ""
        }
    ];
    setDataTableOptServerSide($('#userList'), opt, 8);
    $('#userList').beautyUi({
        tableId: "userList",
        serverSide: true
    });

    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();

    // $("#add").on("click", function () {
    //     userAddContent('用户信息添加', 'userAdd.jsp', '600', '460');
    // });
    // $("#edit").on("click", function () {
    //     userEditContent('用户信息修改', 'userEdit.jsp', '600', '460');
    // });
    // $("#delete").on("click", function () {
    //     userDelete();
    // });

});

/*user增加*/
function userAddContent(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px']
    });
}

/*user修改*/
function userEditContent(title, url, w, h) {
    if ($('#userList').find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px']
    });
}

/*user删除*/
function userDelete() {
    if ($('#userList').find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer.confirm('确认要删除吗？', function () {
        var rowData = $('#userList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(contextPath + "/user/validateUser", rowData, userDel, "json");
    });
}

function userDel(json) {
    var url = contextPath + "/user/deleteUser";
    var rowData = $('#userList').DataTable().rows(".selected").data()[0];  //已经获取数据
    var loginUserId = $("#userName").val();
    if (rowData.userId === "admin" || rowData.userId == loginUserId) {
        showMsg('不能删除admin或者自己！', 'warning');
        return;
    }
    if (json.success) {
        layer.confirm('此用户绑定了角色，确认删除？', function () {
            sendPostRequest(url, rowData, callbackUserDel, "json");                //将获取数据发送给后台处理
        });
    } else {
        sendPostRequest(url, rowData, callbackUserDel, "json");
    }
}

function callbackUserDel(json) {
    if (json.success) {
        $('#userList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    } else if (json.retStatus === 'Info') {
        showMsg("请先删除该用户所绑定的角色！");
    }
}