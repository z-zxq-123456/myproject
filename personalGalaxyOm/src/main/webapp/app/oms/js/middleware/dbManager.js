$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareDbList"));


    opt.ajax = {
        "url": contextPath + "/findDbIntantInfo",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "midwareName",
            "defaultContent": ""
        },
        {
            "data": "serName",
            "defaultContent": ""
        },
        {
            "data": "serIp",
            "defaultContent": ""
        },
        {
            "data": "dbintName",
            "defaultContent": ""
        },
        {
            "data": "dbTypeName",
            "defaultContent": ""
        },
        {
            "data": "dbintUserName",
            "defaultContent": ""
        },
        {
            "data": "dbintPort",
            "defaultContent": ""
        },
        {
            "data": "dbintServiceName",
            "defaultContent": ""
        },
        {
             "data": "dbintNodeNum",
             "defaultContent": ""
        },
        {
             "data": "dbintDesc",
             "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#midwareDbList"), opt);
    //界面美化tables
    $("#midwareDbList").beautyUi({
        tableId: "midwareDbList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('DB实例信息添加', 'dbAdd.jsp', '700', '550');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('DB实例信息修改', 'dbEdit.jsp', '700', '550');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    getPkList({
        url: contextPath + "/findMidwareCombox",
        id: "queryMidwareId",
        async: false,
        params: {midwareType: '0006003'}
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
        area: [w + 'px', h + 'px'],
        end: function () {
            selecttable_data();
        }
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#midwareDbList").find(".selected").length != 1) {
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
    if ($("#midwareDbList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteDbIntantInfo";
    var rowData = $('#midwareDbList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#midwareDbList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selecttable_data() {
    var userTab = $("#midwareDbList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function searchRec() {
    var midwareType = $("#queryMidwareId").val();
    var userTab = $("#midwareDbList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findDbIntantInfo").load();
    } else {
        api.ajax.url(contextPath + "/findDbIntantInfo?midwareId=" + midwareType).load();
    }

}