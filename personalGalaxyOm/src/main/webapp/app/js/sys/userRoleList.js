var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg) {
    showMsgCloseLayer(msg, layer_add_index, layer_edit_index);
}

$(document).ready(function() {
    // 获取默认opt配置
    var opt = getDataTableOpt($("#userRoleList"));

    opt.ajax = {
        "url": contextPath + "/userRole/getList",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "userName",
            "defaultContent": ""
        },
        {
            "data": "message",
            "defaultContent": ""
        },
        {
            "data": "roleName",
            "defaultContent": ""
        },
        {
            "data": "roleDesc",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#userRoleList"), opt);
    $("#userRoleList").beautyUi({
        tableId: "userRoleList"
    });
    $('#add').click(function () {
        userRoleAdd('用户角色信息添加', 'userRoleAdd.jsp', '600', '400');
    });
    $('#edit').on("click", function () {
        userRoleEdit('用户角色信息修改', 'userRoleEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        userRoleDelete();
    });
});

/*userRole增加*/
function userRoleAdd(title,url,w,h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px']
    });
}

/*userRole删除*/
function userRoleDelete() {
    if ($("#userRoleList").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        userRoleDel();
    });
}

function userRoleDel() {
    var url = contextPath + "/userRole/delete";
    var rowData = $('#userRoleList').DataTable().rows(".selected").data()[0];
    var loginUserId = $("#userName").val();
    if (rowData.userId === "admin" || rowData.userId == loginUserId) {
        showMsg('不能删除admin或者自己！', 'warning');
        return;
    }
    sendPostRequest(url, rowData, callbackUserRoleDel, "json");
}

function callbackUserRoleDel(json) {
    if (json.success) {
        $('#userRoleList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
/*userRole修改*/
function userRoleEdit(title,url,w,h) {
    if ($("#userRoleList").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w + 'px', h + 'px'],
        title: title
    });
}

