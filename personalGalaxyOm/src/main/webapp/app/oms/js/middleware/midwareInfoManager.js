$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareInfoList"));


    opt.ajax = {
        "url": contextPath + "/findEcmMidwareInfo",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "midwareName",
            "defaultContent": ""
        },
        {
            "data": "midwareTypeName",
            "defaultContent": ""
        },
        {
            "data": "midwareVerNo",
            "defaultContent": ""
        },
        {
            "data": "midwarePath",
            "defaultContent": ""
        },
        {
            "data": "isDefaultParaName",
            "defaultContent": ""
        },
        {
            "data": "kfkZksIdName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#midwareInfoList"), opt);
    //界面美化tables
    $("#midwareInfoList").beautyUi({
        tableId: "midwareInfoList",
    });
    $("#add").on("click", function () {
        midwareVer_Add('中间件信息添加', 'midwareInfoAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('中间件信息修改', 'midwareInfoEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
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
        area: [w + 'px', h + 'px']
    });
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#midwareInfoList").find(".selected").length != 1) {
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
    if ($("#midwareInfoList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteEcmMidewareInfo";
    var rowData = $('#midwareInfoList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#midwareInfoList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
        refresh();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function searchRec() {
    var midwareType = $("#queryMidwareType").val();
    var userTab = $("#midwareInfoList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findEcmMidwareInfo").load();
    } else {
        api.ajax.url(contextPath + "/findEcmMidwareInfo?midwareType=" + midwareType).load();
    }

}
function refresh(){
    location.replace(location.href);
}