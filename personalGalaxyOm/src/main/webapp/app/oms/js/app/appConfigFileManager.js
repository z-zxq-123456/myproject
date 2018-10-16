$(document).ready(function () {
    var opt = getDataTableOpt($("#appConfigList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findEcmAppFileConfig",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "fileName",
            "defaultContent": ""
        },
        {
            "data": "configKey",
            "defaultContent": ""
        },
        {
            "data": "configValue",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#appConfigList"), opt);
    //界面美化tables
    $("#appConfigList").beautyUi({
        tableId: "appConfigList",
        buttonName: ["修改", "校验端口"],
        buttonId: ["edit", "vaildPort"]
    });
    $('#appConfigList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#appConfigList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#appConfigList').on('page.dt', function (e) {
        $('#appConfigList').find("tr").removeClass('selected');
    });
    getPkList({
        url: contextPath + "/findComboxAppIntant"+urlArgs,
        id: "queryAppIntantId",
        async: false
    });

    if(urlArgs==""){
       $("#allTile").show();
    }else{
       $("#appTile").show();
    }
    $("#edit").on("click", function () {
        midwareVer_Edit('配置文件信息修改', 'appConfigFileEdit.jsp', '600', '400');
    });
    $("#vaildPort").on("click", function () {
        vaildPort();
    });
    $(".select2").select2();
});
function vaildPort() {
    if ($("#appConfigList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    var url = contextPath + "/findCheckPort";
    var rowData = $('#appConfigList').DataTable().rows(".selected").data()[0];
    if (rowData) {
        if (rowData.configKey.indexOf('.port') > 0) {
            sendPostRequest(url, {
                appIntantId: rowData.appIntantId,
                port: rowData.configValue
            }, callback_dataEdit, "json");
        } else {
            showMsg('效验端口只对端口有效,请重新选择!', 'warning');
        }
    }
}

var layer_add_index, layer_edit_index;
function showMsgDuringTime(msg) {
    layer.msg(msg);
    setTimeout(function () {
        layer.closeAll('dialog');
    }, 1000);
    if (msg == "添加成功") {
        layer.close(layer_add_index);
    }
    if (msg == "编辑成功") {
        layer.close(layer_edit_index);
    }
}
function midwareVer_Edit(title, url, w, h) {
    if ($("#appConfigList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    if (vaildTabData("appConfigList")) {
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
}
function callback_dataEdit(result) {
    if (result.errorMsg) {
        showMsg(result.errorMsg, 'error');
    } else {
        if (result.isConnection == "true") {
            showMsg('该端口已被占用!', 'info');
        } else {
            showMsg('该端口未被占用,可放心使用!', 'info');
        }
    }
}
function searchRec() {
    var appIntantId = $("#queryAppIntantId").val();
    var fileName = $("#queryFileName").val();
    if (appIntantId == "") {
        showMsg('应用实例名称为空！', 'warning');
    } else if (fileName == "") {
        showMsg('配置文件名称为空！', 'warning');
    } else {
        var configTab = $("#appConfigList").dataTable();
        var configApi = configTab.api();
        configApi.ajax.url(contextPath + "/findEcmAppFileConfig?appIntantId=" + appIntantId + "&fileName=" + fileName).load();
    }
}
function selecttable_data() {
    var appIntantId = $("#queryAppIntantId").val();
    var fileName = $("#queryFileName").val();
    var configTab = $("#appConfigList").dataTable();
    var configApi = configTab.api();
    configApi.ajax.url(contextPath + "/findEcmAppFileConfig?appIntantId=" + appIntantId + "&fileName=" + fileName).load();
}
function getAppId(){
  return appId;
}