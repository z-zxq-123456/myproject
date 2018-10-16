var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg) {
    showMsgCloseLayer(msg, layer_add_index, layer_edit_index);
    if (msg === "添加成功") {
        selectAll_refresh();
    }
}

$(document).ready(function () {
    $(".breadcrumb").data("reqNum", parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#lmTranLimitLink"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;
    opt.ajax = {
        "url": contextPath + "/baseCommon/getList",
        "type": "POST",
        "data": {
            "tableName": "LM_TRAN_LIMIT_LINK",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
        }
    };
    opt.fnRowCallback = function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
        var value = aData.OPERATE_TYPE;
        if (value === "3") {
            $('td', nRow).addClass('danger');
        }
        else if (value === "2") {
            $('td', nRow).addClass('highlight');
        }
        else if (value === "1") {
            $('td', nRow).addClass('newFace');
        }
    };

    opt.columnDefs = [
        {
            "targets": [0],
            "visible": false
        }
        , {
            "width": "100px",
            "targets": 1
        }
        , {
            "width": "100px",
            "targets": 2
        }
        , {
            "width": "100px",
            "targets": 3
        }
        , {
            "width": "100px",
            "targets": 4
        }
        , {
            "width": "100px",
            "targets": 5
        }
        , {
            "width": "100px",
            "targets": 6
        }
        , {
            "width": "100px",
            "targets": 7
        }
        , {
            "width": "100px",
            "targets": 8
        }
        , {
            "width": "100px",
            "targets": 9
        }
        , {
            "width": "100px",
            "targets": 10
        }
    ];

    opt.columns = [
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        , {"data": "LIMIT_REF", "defaultContent": ""}
        , {"data": "TRAN_TYPE_LINK", "defaultContent": ""}
        , {"data": "CLIENT_TYPE", "defaultContent": ""}
        , {"data": "PROD_TYPE", "defaultContent": ""}
        , {"data": "SOURCE_TYPE_LINK", "defaultContent": ""}
        , {"data": "BALANCE_TYPE", "defaultContent": ""}
        , {"data": "AREA_CODE_LINK", "defaultContent": ""}
        , {"data": "INLAND_IND", "defaultContent": ""}
        , {"data": "IS_APP_FLAG", "defaultContent": ""}
        , {"data": "BRANCH_LINK", "defaultContent": ""}
    ];
    //渲染tables
    opt.order = [[1, 'asc']];
    drawDataTable($("#lmTranLimitLink"), opt);
    $("#lmTranLimitLink").beautyUi({
        tableId: "lmTranLimitLink",
        buttonName: ["添加", "修改", "删除", "提交", "查看差异数据", "刷新", "返回"],
        buttonId: ["add", "edit", "delete", "submit", "contrast", "refresh", "return"]
    });
    $('#lmTranLimitLink tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#lmTranLimitLink').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#lmTranLimitLink').on('page.dt', function (e) {
        $('#lmTranLimitLink').find("tr").removeClass('selected');
    });
    $("#refresh").on("click", function () {
        selectAll_refresh();
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=LM_TRAN_LIMIT_DEF&tableCol=LIMIT_REF,LIMIT_DESC",
        id: "LIMIT_REF",
        async: false
    });
    $(".select2").select2();
    buttonStatus();
    $("#selectByPrimaryKey").click(function () {
        if (1 === 1
            && $("#LIMIT_REF").val() === ""
        ) {
            initData_refresh();
        } else {
            var attrTab = $("#lmTranLimitLink").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath + "/baseCommon/selectBase?col1=LIMIT_REF&colV1=" + $("#LIMIT_REF").val() + "&col2=&colV2=" + $("#").val() + "&col3=&" + "colV3=" + $("#").val()).load();
        }
    });

});

/*增加*/
function lm_tran_limit_link_add(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w + 'px', h + 'px']
    });
}

/*修改*/
function lm_tran_limit_link_mod(title, url, w, h) {
    if ($("#lmTranLimitLink").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w + 'px', h + 'px']
    });
}

/*删除*/
function lm_tran_limit_link_del() {
    if ($("#lmTranLimitLink").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer.confirm('确认要删除吗？', function () {
        lmTranLimitLinkDel();
    });
}

function lmTranLimitLinkDel() {
    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    var url = contextPath + "/baseCommon/updateForDelete";
    var rowData = $('#lmTranLimitLink').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName = "LM_TRAN_LIMIT_LINK";
    keyFieldsJson.LIMIT_REF = rowData.LIMIT_REF;
    keyFieldsJson.TRAN_TYPE_LINK = rowData.TRAN_TYPE_LINK;
    paraJson.key = keyFieldsJson;
    paraJson.status = rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status = rowData.COLUMN_STATUS;
    var params = {
        paraJson: JSON.stringify(paraJson)
    };
    sendPostRequest(url, params, callback_lmTranLimitLinkDel, "json");                //将获取数据发送给后台处理
}

function callback_lmTranLimitLinkDel(json) {
    if (json.success) {
        $("#lmTranLimitLink").dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function buttonStatus() {
    $("#return").hide();
    if (parent.$(".breadcrumb").data("needButton") === "N") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click", function () {
            lm_tran_limit_link_contrast();
        });
        $("#return").on("click", function () {
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#queryPrimaryKey").show();
            $("#add").hide();
            $("#edit").hide();
            $("#delete").hide();
            $("#submit").hide();
        });
    }
    else if (parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回") {
        $("#add").on("click", function () {
            lm_tran_limit_link_add('添加', 'add/lmTranLimitLinkAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            lm_tran_limit_link_mod('修改', 'edit/lmTranLimitLinkMod.jsp', '600', '520');
        });
        if (parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else {
            $("#delete").on("click", function () {
                lm_tran_limit_link_del();
            });
        }
        $("#submit").on("click", function () {
            lm_tran_limit_link_submit();
        });
        $("#contrast").on("click", function () {
            lm_tran_limit_link_contrast();
        });
        $("#return").on("click", function () {
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if (parent.$(".breadcrumb").data("needButton") === "已录入") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click", function () {
            lm_tran_limit_link_contrast();
        });
        $("#return").on("click", function () {
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if (parent.$(".breadcrumb").data("needButton") === "NoEdit") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").hide();
    }
    else {
        $(".breadcrumb").data("needButton", "windowShow");
        $("#add").on("click", function () {
            lm_tran_limit_link_add('添加', 'add/lmTranLimitLinkAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            lm_tran_limit_link_mod('修改', 'edit/lmTranLimitLinkMod.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            lm_tran_limit_link_del();
        });
        $("#contrast").on("click", function () {
            lm_tran_limit_link_contrast();
        });
        $("#submit").on("click", function () {
            lm_tran_limit_link_submit();
        });
        $("#return").on("click", function () {
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    if (parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}


/*查看差异数据*/
function lm_tran_limit_link_contrast() {
    var attrTab = $("#lmTranLimitLink").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath + "/baseCommon/contrastBase?tableName=LM_TRAN_LIMIT_LINK").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function lm_tran_limit_link_submit() {
    layer.confirm('确认要提交吗？', function () {
        var url = contextPath + "/baseCommon/submitParaData";
        sendPostRequest(url, {
            tableName: "LM_TRAN_LIMIT_LINK"
        }, callback_lmTranLimitLinkSubmit, "json");
    });
}

function callback_lmTranLimitLinkSubmit(json) {
    if (json.success) {
        if ($(".breadcrumb").data("needButton") === "windowShow") {
            showMsgDuringTime("提交成功");
        }
        else {
            parent.afterSelectPara("view", "提交成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}


function selectAll_refresh() {
    var prodTab = $("#lmTranLimitLink").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh() {
    var prodTab = $("#lmTranLimitLink").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath + "/baseCommon/getList?tableName=LM_TRAN_LIMIT_LINK").load();
}



