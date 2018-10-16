$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareZkList"));


    opt.ajax = {
        "url": contextPath + "/findZkIntantInfo",
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
            "data": "zkintName",
            "defaultContent": ""
        },
        {
            "data": "zkintNodeNum",
            "defaultContent": ""
        },
        {
            "data": "zkintClientPort",
            "defaultContent": ""
        },
        {
            "data": "zkintCommPort",
            "defaultContent": ""
        },
        {
            "data": "zkintElectPort",
            "defaultContent": ""
        },
        {
            "data": "zkintDesc",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#midwareZkList"), opt);
    //界面美化tables
    $("#midwareZkList").beautyUi({
        tableId: "midwareZkList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('ZK实例信息添加', 'zkAdd.jsp', '700', '550');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('ZK实例信息修改', 'zkEdit.jsp', '700', '550');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    getPkList({
        url: contextPath + "/findMidwareCombox",
        id: "queryMidwareId",
        async: false,
        params: {midwareType: '0006002'}
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
    if ($("#midwareZkList").find(".selected").length != 1) {
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
    if ($("#midwareZkList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteZkIntantInfo";
    var rowData = $('#midwareZkList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#midwareZkList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selecttable_data() {
    var userTab = $("#midwareZkList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function searchRec() {
    var midwareType = $("#queryMidwareId").val();
    var userTab = $("#midwareZkList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findZkIntantInfo").load();
    } else {
        api.ajax.url(contextPath + "/findZkIntantInfo?midwareId=" + midwareType).load();
    }
}