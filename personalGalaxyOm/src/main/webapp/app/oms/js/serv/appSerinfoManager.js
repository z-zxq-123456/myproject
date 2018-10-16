$(document).ready(function () {
    var opt = getDataTableOpt($("#appSerinfoList"));


    opt.ajax = {
        "url": contextPath + "/findEcmAppSer",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "appSerName",
            "defaultContent": ""
        },
        {
            "data": "appSerClsnm",
            "defaultContent": ""
        },
        {
            "data": "appSerTypeName",
            "defaultContent": ""
        },
        {
            "data": "appSerDesc",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#appSerinfoList"), opt);
    //界面美化tables
    $("#appSerinfoList").beautyUi({
        tableId: "appSerinfoList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('应用服务信息添加', 'appSerinfoAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('应用服务信息修改', 'appSerinfoEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    getPkList({
        url: contextPath + "/findParaCombox",
        id: "queryAppSerType",
        async: false,
        params: {
            paraParentId: '0060',
            isDefault: false
        }
    });
    $(".select2").select2();
});

var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}


function midwareVer_Add(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        title: title,
        area: [w + 'px', h + 'px']
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#appSerinfoList").find(".selected").length != 1) {
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
    if ($("#appSerinfoList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteEcmAppSer";
    var rowData = $('#appSerinfoList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#appSerinfoList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function searchRec() {
    var midwareType = $("#queryAppSerType").val();
    var userTab = $("#appSerinfoList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findEcmAppSer").load();
    } else {
        api.ajax.url(contextPath + "/findEcmAppSer?appSerType=" + midwareType).load();
    }


}