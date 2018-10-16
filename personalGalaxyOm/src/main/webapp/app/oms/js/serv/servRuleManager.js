var layer_add_index, layer_edit_index;
$(document).ready(function () {
    var opt = getDataTableOpt($("#servRuleList"));


    opt.ajax = {
        "url": contextPath + "/findEcmServRule",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "servRuleTypeName",
            "defaultContent": ""
        },
        {
            "data": "routerCondName",
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
        {
            "data": "ruleStatusName",
            "defaultContent": ""
        },
        {
            "data": "routerArgsPos",
            "defaultContent": "0"
        },
        {
            "data": "routerArgsTypeName",
            "defaultContent": ""
        },
        {
            "data": "servRuleSelf",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#servRuleList"), opt);
    //界面美化tables
    $("#servRuleList").beautyUi({
        tableId: "servRuleList"
    });
    $("#add").on("click", function () {
        midwareVer_Add('服务路由信息添加', 'servRuleAdd.jsp', '600', '500');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('服务路由信息修改', 'servRuleEdit.jsp', '600', '500');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    getPkList({
        url: contextPath + "/findParaCombox",
        id: "queryServRuleType",
        async: false,
        params: {
            paraParentId: '0026',
            isDefault: false
        }
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
    if ($("#servRuleList").find(".selected").length != 1) {
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
    if ($("#servRuleList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}

function midwareVerDel() {
    var url = contextPath + "/deleteEcmServRule";
    var rowData = $('#servRuleList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        $('#servRuleList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function selecttable_data() {
    var userTab = $("#servRuleList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}

function searchRec() {
    var midwareType = $("#queryServRuleType").val();
    var userTab = $("#servRuleList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findEcmServRule").load();
    } else {
        api.ajax.url(contextPath + "/findEcmServRule?servRuleType=" + midwareType).load();
    }
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}