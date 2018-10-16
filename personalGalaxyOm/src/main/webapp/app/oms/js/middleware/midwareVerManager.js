$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareList"));


    opt.ajax = {
        "url": contextPath + "/findMiddlewareVer",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "midwareTypeName",
            "defaultContent": ""
        },
        {
            "data": "midwareVerNo",
            "defaultContent": ""
        },
        {
            "data": "midwareVerPath",
            "defaultContent": ""
        },
        {
            "data": "midwareVerDate",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        },
        {
            "data": "midwareVerDesc",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#midwareList"), opt);
    //界面美化tables
    $("#midwareList").beautyUi({
        tableId: "midwareList",
        buttonName: ["添加", "修改", "删除", "下载版本"],
        buttonId: ["add", "edit", "delete", "load"]
    });
    $("#add").on("click", function () {
        midwareVer_Add('中间件版本信息添加', 'midwareVerAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('中间件版本信息修改', 'midwareVerEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    $("#load").on('click', function () {
        midwareVer_Load();
    });
    getPkList({
        url: contextPath + "/findParaCombox",
        id: "queryMidwareType",
        async: false,
        params: {
            paraParentId: '0006',
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
        area: [w + 'px', h + 'px'],
        end: function () {
            selecttable_data();
        }
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#midwareList").find(".selected").length != 1) {
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
    if ($("#midwareList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteMiddlewareVer";
    var rowData = $('#midwareList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#midwareList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selecttable_data() {
    var userTab = $("#midwareList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function midwareVer_Load() {
    if ($("#midwareList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    var row = $('#midwareList').DataTable().rows(".selected").data()[0];
    var sub_url = contextPath + '/downloadMiddlewareVer';
    if (row) {
        window.location.href = sub_url + "?path=" + row.midwareVerPath;
    }
}
function searchRec() {
    var midwareType = $("#queryMidwareType").val();
    var userTab = $("#midwareList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findMiddlewareVer").load();
    } else {
        api.ajax.url(contextPath + "/findMiddlewareVer?midwareType=" + midwareType).load();
    }


}