var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg) {
    showMsgCloseLayer(msg, layer_add_index, layer_edit_index);
    if (msg === "添加成功") {
        selectAll_refresh();
    }
}
$(document).ready(function () {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
        id: "prodType",
        async: false
    });
    $(".breadcrumb").data("reqNum", parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#applyProdPublish"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;
    opt.ajax = {
        "url": contextPath + "/applyProdPublish/list",
        "type": "POST"
    };
    opt.columns = [
        {"data": "prodType", "defaultContent": ""}
        , {"data": "publishTime", "defaultContent": ""}
        , {"data": "checkText", "defaultContent": ""}
        , {"data": "uprightColumn", "defaultContent": ""}
        , {"data": "oldValue", "defaultContent": ""}
        , {"data": "newValue", "defaultContent": ""}
        , {"data": "operator", "defaultContent": ""}
        , {"data": "operatorTime", "defaultContent": ""}
        , {"data": "checkOperator", "defaultContent": ""}
        , {"data": "checkTime", "defaultContent": ""}
    ];
    //渲染tables
    opt.order = [[1, 'desc']];
    drawDataTable($("#applyProdPublish"), opt);
    $("#applyProdPublish").beautyUi({
        tableId: "applyProdPublish",
        buttonName:[],
    });
    $('#applyProdPublish tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#cifDistCode').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#applyProdPublish').on('page.dt', function (e) {
        $('#applyProdPublish').find("tr").removeClass('selected');
    });
    $("#refresh").on("click", function () {
        selectAll_refresh();
    });
    $(".select2").select2();
    $("#selectByPrimaryKey").click(function () {
            var attrTab = $("#applyProdPublish").dataTable();
            var api = attrTab.api();
        if ( $("#startDate").val() == "" && $("#endDate").val() == ""&& $("#prodType").val()=="") {
            api.ajax.url(contextPath + "/applyProdPublish/list").reload();
        }else if ( ($("#endDate").val() == ""&& $("#startDate").val()!="")||($("#endDate").val() != ""&& $("#startDate").val()=="")) {
            alert("起始日期、终止日期不能只输一个！");
        }
        else {
            api.ajax.url(contextPath + "/applyProdPublish/findProdType?prodType=" + $("#prodType").val() + "&startTime=" + $("#startDate").val() + "&endTime=" + $("#endDate").val()).load();
        }
    });

});
function selectAll_refresh() {
    var prodTab = $("#applyProdPublish").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}
function initData_refresh() {
    var prodTab = $("#applyProdPublish").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath + "/applyProdPublish/list").load();
}



