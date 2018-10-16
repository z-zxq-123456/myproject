$(document).ready(function () {
    var opt = getDataTableOpt($("#appVerList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;

    opt.ajax = {
        "url": contextPath + "/findAppVer",
        "type": "POST"
    };
    opt.columns = [
		{
            "data": "appVerId",
            "defaultContent": ""
        },
        {
            "data": "appName",
            "defaultContent": ""
        },
        {
            "data": "appSimpenNm",
            "defaultContent": ""
        },
        {
            "data": "appVerNum",
            "defaultContent": ""
        },
        {
            "data": "appVerDate",
            "defaultContent": ""
        },
        {
            "data": "userName",
            "defaultContent": ""
        },
        {
            "data": "appVerPath",
            "defaultContent": ""
        },
        {
            "data": "appVerDesc",
            "defaultContent": ""
        },
        {
            "data": "appVerTypeName",
            "defaultContent": ""
        }
    ];
    //渲染tables
    drawDataTable($("#appVerList"), opt);
    //界面美化tables
    $("#appVerList").beautyUi({
        tableId: "appVerList",
        buttonName: ["新增", "修改", "删除", "下载版本"],
        buttonId: ["add", "edit", "delete", "downVer"]
    });
    $('#appVerList tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#appVerList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#appVerList').on('page.dt', function (e) {
        $('#appVerList').find("tr").removeClass('selected');
    });
    getPkList({
        url: contextPath + "/findAppCombox",
        id: "queryAppId",
        async: false
    });
    $(".select2").select2();
    $("#add").on("click", function () {
        midwareVer_Add('全量版本信息添加', 'fullAppVerAdd.jsp', '600', '400');
    });
    $("#edit").on("click", function () {
        midwareVer_Edit('全量版本信息修改', 'fullAppVerEdit.jsp', '600', '400');
    });
    $("#delete").on("click", function () {
        midwareVer_Del();
    });
    $("#downVer").on("click", function () {
        downVer();
    });
});

var  layer_add_index,layer_edit_index;

function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    if(msg=="添加成功") {
        layer.close(layer_add_index);
    }
    if(msg=="编辑成功") {
        layer.close(layer_edit_index);
    }
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
    if ($("#appVerList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    if (vaildTabData("appVerList")) {
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
function midwareVer_Del() {
    if ($("#appVerList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareVerDel();
    });
}
function midwareVerDel() {
    var url = contextPath + "/deleteAppVer";
    var rowData = $('#appVerList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success) {
        showMsgDuringTime("操作成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
    selecttable_data();
}
function selecttable_data() {
    var userTab = $("#appVerList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function searchRec() {
    var appId = $("#queryAppId").val();
    var userTab = $("#appVerList").dataTable();
    var api = userTab.api();
    if (appId == "") {
        api.ajax.url(contextPath + "/findAppVer").load();
    } else {
        api.ajax.url(contextPath + "/findAppVer?appId=" + appId).load();
    }
}
function downVer() {
    if ($("#appVerList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    var sub_url = contextPath + '/downloadAppVer';
    var rowData = $('#appVerList').DataTable().rows(".selected").data()[0];
    if (rowData) {
        window.location.href = sub_url + "?path=" + rowData.appVerPath;
    }
}
    
      
