var layer_add_index, layer_edit_index;
$(document).ready(function () {
    var opt = getDataTableOpt($("#serMtdinfoList"));


    opt.ajax = {
        "url": contextPath + "/findEcmSerMtdinfo",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "appSerName",
            "defaultContent": ""
        },
        {
            "data": "serMtdCnm",
            "defaultContent": ""
        },
        {
            "data": "serMtdEnm",
            "defaultContent": ""
        },
    ];
    //渲染tables
    setDataTableOpt($("#serMtdinfoList"), opt);
    //界面美化tables
    $("#serMtdinfoList").beautyUi({
        tableId: "serMtdinfoList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('应用服务信息添加', 'serMtdinfoAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('应用服务信息修改', 'serMtdinfoEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    $(".select2").select2();
});
function midwareVer_Add(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px']
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#serMtdinfoList").find(".selected").length != 1) {
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
function midwareVer_Del() {
    if ($("#serMtdinfoList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteEcmSerMtdinfo";
    var rowData = $('#serMtdinfoList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#serMtdinfoList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}