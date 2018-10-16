var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg) {
    showMsgCloseLayer(msg, layer_add_index, layer_edit_index);
}

$(document).ready(function () {
    var opt = getDataTableOpt($('#roleList'));
    opt.ajax = {
        "url": contextPath + "/role/getList",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "roleId",
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
    setDataTableOpt($('#roleList'), opt);

    $('#roleList').beautyUi({
        tableId: "roleList"
    });
    $('#add').click(function () {
        roleAdd('角色信息添加', 'roleAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        roleEdit('角色信息修改', 'roleEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        roleDelete();
    });
});

/*role增加*/
function roleAdd(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w + 'px', h + 'px'],
        title: title
    });
}

/*role修改*/
function roleEdit(title, url, w, h) {
    if ($("#roleList").find(".selected").length !== 1) {
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

/*role删除*/
function roleDelete() {
    if ($("#roleList").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        roleDel();
    });
}

function roleDel() {
    var url = contextPath + "/role/delete";
    var rowData = $('#roleList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callbackRoleDel, "json");                //将获取数据发送给后台处理
}

function callbackRoleDel(json) {
    if (json.success) {
        $('#roleList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

