var layer_add_index, layer_edit_index;
$(document).ready(function () {
    var opt = getDataTableOpt($("#routerCondList"));


    opt.ajax = {
        "url": contextPath + "/findRouterCond",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "routerCondName",
            "defaultContent": ""
        },
        {
            "data": "routerColCdn",
            "defaultContent": ""
        },
        {
            "data": "routerColCn",
            "defaultContent": ""
        },
        {
            "data": "routerCondOperName",
            "defaultContent": ""
        },
        {
            "data": "routerCondVal",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#routerCondList"), opt);
    //界面美化tables
    $("#routerCondList").beautyUi({
        tableId: "routerCondList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('路由条件信息添加', 'routerCondAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('路由条件信息修改', 'routerCondEdit.jsp', '600', '400');
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
        area: [w + 'px', h + 'px'],
        end: function () {
            selecttable_data();
        }
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#routerCondList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px'],
        end: function () {
            selecttable_data();
        }
    });
}
function midwareVer_Del() {
    if ($("#routerCondList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteEcmRouterCond";
    var rowData = $('#routerCondList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#routerCondList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selecttable_data() {
    var userTab = $("#routerCondList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}