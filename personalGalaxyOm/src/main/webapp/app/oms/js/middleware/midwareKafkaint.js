$(document).ready(function () {
    var opt = getDataTableOpt($("#midwareKafkaList"));


    opt.ajax = {
        "url": contextPath + "/findMidwareKafkaint",
        "type": "POST"
    };
    opt.columns = [
        {
            "data": "kafkaintId",
            "defaultContent": ""
        },
        {
            "data": "midwareName",
            "defaultContent": ""
        },
        {
            "data": "kafkaintName",
            "defaultContent": ""
        },
        {
            "data": "serIp",
            "defaultContent": ""
        },
        {
            "data": "kafkaintPort",
            "defaultContent": ""
        },
        {
            "data": "kafkaintDesc",
            "defaultContent": ""
        }
    ];
    //渲染tables
    setDataTableOpt($("#midwareKafkaList"), opt);
    //界面美化tables
    $("#midwareKafkaList").beautyUi({
        tableId: "midwareKafkaList"
    });
    $("#add").on("click", function () {
        midware_Add('Kafka实例信息添加', 'midwareKafkaAdd.jsp', '600', '450');
    });
    $("#edit").on("click", function () {
        midware_Edit('Kafka实例信息修改', 'midwareKafkaEdit.jsp', '600', '450');
    });
    $("#delete").on("click", function () {
        midware_Del();
    });
    getPkList({
        url: contextPath + "/findMidwareCombox",
        id: "queryMidwareId",
        async: false,
        params: {midwareType: '0006004'}
    });
    $(".select2").select2();
});

var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}
function midware_Add(title, url, w, h) {
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
function midware_Edit(title, url, w, h) {
    if ($("#midwareKafkaList").find(".selected").length != 1) {
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
function midware_Del() {
    if ($("#midwareKafkaList").find(".selected").length != 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }

    layer.confirm('确认要删除吗？', function () {
        midwareDel();
    });
}
function midwareDel() {
    var url = contextPath + "/deleteMidwareKafkaint";
    var rowData = $('#midwareKafkaList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_midwareVerDel, "json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json) {
    if (json.success=="success") {
        $('#midwareKafkaList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selecttable_data() {
    var userTab = $("#midwareKafkaList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
function searchRec() {
    var midwareType = $("#queryMidwareId").val();
    var userTab = $("#midwareKafkaList").dataTable();
    var api = userTab.api();
    if (midwareType == "") {
        api.ajax.url(contextPath + "/findMidwareKafkaint").load();
    }
    api.ajax.url(contextPath + "/findMidwareKafkaint?midwareId=" + midwareType).load();
}