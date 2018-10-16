$(document).ready(function () {
    $(".breadcrumb").data("reqNum", parent.$(".breadcrumb").data("reqNum"));
    // 获取默认opt配置
    var opt = getDataTableOpt($("#irlFeeClientType"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;
    opt.ajax = {
        "url": contextPath + "/baseCommon/getList",
        "type": "POST",
        "data": {
            "tableName": "IRL_FEE_CLIENT_TYPE",
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
    ];

    opt.columns = [
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        , {"data": "FEE_TYPE", "defaultContent": ""}
        , {"data": "CLIENT_TYPE", "defaultContent": ""}


    ];
    //渲染tables
    opt.order = [[1, 'asc']];
    drawDataTable($("#irlFeeClientType"), opt);
    $("#irlFeeClientType").beautyUi({
        tableId: "irlFeeClientType",
        buttonName: ["添加", "修改", "删除", "提交", "查看差异数据", "刷新", "返回"],
        buttonId: ["add", "edit", "delete", "submit", "contrast", "refresh", "return"]
    });
    $('#irlFeeClientType tbody').on('click', 'tr', function (e) {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#irlFeeClientType').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#irlFeeClientType').on('page.dt', function (e) {
        $('#irlFeeClientType').find("tr").removeClass('selected');
    });
    $("#refresh").on("click", function () {
        selectAll_refresh();
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "CLIENT_TYPE",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
        id: "FEE_TYPE",
        async: false
    });
    $("#selectByPrimaryKey").click(function () {
        if (1 === 1
            && $("#FEE_TYPE").val() === ""
            && $("#CLIENT_TYPE").val() === ""
        ) {
            initData_refresh();
        } else {
            var attrTab = $("#irlFeeClientType").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath + "/baseCommon/selectBase?col1=FEE_TYPE&colV1=" + $("#FEE_TYPE").val() + "&col2=CLIENT_TYPE&colV2=" + $("#CLIENT_TYPE").val() + "&col3=&" + "colV3=" + $("#").val()).load();
        }
    });
    $(".select2").select2();
    buttonStatus();
});
function buttonStatus() {
    $("#return").hide();
    if (parent.$(".breadcrumb").data("needButton") === "N") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click", function () {
            irl_fee_client_type_contrast();
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
            irl_fee_client_type_add('添加', 'add/irlFeeClientTypeAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            irl_fee_client_type_mod('修改', 'edit/irlFeeClientTypeMod.jsp', '600', '520');
        });
        if (parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else {
            $("#delete").on("click", function () {
                irl_fee_client_type_del();
            });
        }
        $("#submit").on("click", function () {
            irl_fee_client_type_submit();
        });
        $("#contrast").on("click", function () {
            irl_fee_client_type_contrast();
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
            irl_fee_client_type_contrast();
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
            irl_fee_client_type_add('添加', 'add/irlFeeClientTypeAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            irl_fee_client_type_mod('修改', 'edit/irlFeeClientTypeMod.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            irl_fee_client_type_del();
        });
        $("#contrast").on("click", function () {
            irl_fee_client_type_contrast();
        });
        $("#submit").on("click", function () {
            irl_fee_client_type_submit();
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
function irl_fee_client_type_contrast() {
    var attrTab = $("#irlFeeClientType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath + "/baseCommon/contrastBase?tableName=IRL_FEE_CLIENT_TYPE").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function irl_fee_client_type_submit() {
    layer.confirm('确认要提交吗？', function () {
        var url = contextPath + "/baseCommon/submitParaData";
        sendPostRequest(url, {
            tableName: "IRL_FEE_CLIENT_TYPE"
        }, callback_irlFeeClientTypeSubmit, "json");
    });
}

function callback_irlFeeClientTypeSubmit(json) {
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


var layer_add_index, layer_edit_index;
/*增加*/
function irl_fee_client_type_add(title, url, w, h) {
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w + 'px', h + 'px']
    });
}

/*修改*/
function irl_fee_client_type_mod(title, url, w, h) {
    if ($("#irlFeeClientType").find(".selected").length !== 1) {
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
function irl_fee_client_type_del() {
    if ($("#irlFeeClientType").find(".selected").length !== 1) {
        showMsg('请选择一行记录！', 'warning');
        return;
    }
    layer.confirm('确认要删除吗？', function () {
        irlFeeClientTypeDel();
    });
}

function irlFeeClientTypeDel() {
    var paraJson, keyFieldsJson;
    paraJson = {};
    keyFieldsJson = {};
    var url = contextPath + "/baseCommon/updateForDelete";
    var rowData = $('#irlFeeClientType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName = "IRL_FEE_CLIENT_TYPE";
    keyFieldsJson.CLIENT_TYPE = rowData.CLIENT_TYPE;
    keyFieldsJson.FEE_TYPE = rowData.FEE_TYPE;
    generalFieldsJson.COMPANY = $("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.status = rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status = rowData.COLUMN_STATUS;
    var params = {
        paraJson: JSON.stringify(paraJson)
    };
    sendPostRequest(url, params, callback_irlFeeClientTypeDel, "json");                //将获取数据发送给后台处理
}

function callback_irlFeeClientTypeDel(json) {
    if (json.success) {
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function selectAll_refresh() {
    var prodTab = $("#irlFeeClientType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh() {
    var prodTab = $("#irlFeeClientType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath + "/baseCommon/getList?tableName=IRL_FEE_CLIENT_TYPE").load();
}


function showMsgDuringTime(msg) {
    layer.msg(msg);
    setTimeout(function () {
        layer.closeAll('dialog');
    }, 1000);
    if (msg === "添加成功") {
        layer.close(layer_add_index);
        selectAll_refresh();
    }
    if (msg === "编辑成功") {
        layer.close(layer_edit_index);
    }

}