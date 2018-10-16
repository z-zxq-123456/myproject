// 获取表单有效元素的值，并组织成url参数形式(a=1&b=2&c=3)
function getFormData(formId) {
    return $("#" + formId).serialize();
}

// JQUERY AJAX异步请求(url:地址 params:参数 callBack:回调函数 dataType:返回数据类型)
function sendPostRequest(url, params, callBack, dataType, async, type) {
    var index1;
    if (dataType === undefined || dataType === "" || dataType === null)
        dataType = "json";
    if (async === undefined)
        async = true;
    if (type === undefined || type === "" || type === null)
        type = "POST";
    $.ajax({
        type: type,
        url: url,
        async: async,
        data: params,
        dataType: dataType,
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callBack,
        complete: function () {
            layer.close(index1);
        }
    });
}

// 弹出信息窗口 msgString:提示信息 msgType:信息类型 [success,error,info,question,warning]
function showMsg(msgString, msgType) {
    if ("success" === msgType) {
        layer.alert(msgString, {icon: 1});
    } else if ("error" === msgType) {
        layer.alert(msgString, {icon: 2});
    } else if ("question" === msgType) {
        layer.alert(msgString, {icon: 3});
    } else if ("warning" === msgType) {
        layer.alert(msgString, {icon: 7});
    } else {
        layer.alert(msgString);
    }
}

function alert(msgString) {
    layer.alert(msgString);
}


// 清空Form
function cleanForm(id) {
    $(':input', '#' + id)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

function datatableSetOptionNoElement(table, opt) {
    if (table.hasClass("dataTable-noheader")) {
        opt.bFilter = false;
        opt.bLengthChange = false;
    }
    if (table.hasClass("dataTable-nofooter")) {
        opt.bInfo = false;
        opt.bPaginate = false;
    }
    if (table.hasClass("dataTable-nosort")) {
        var column = table.attr('data-nosort');
        column = column.split(',');
        for (var i = 0; i < column.length; i++) {
            column[i] = parseInt(column[i]);
        }
        opt.aoColumnDefs = [{
            'bSortable': false,
            'aTargets': column
        }];
    }
}

function datatableDoStuffOption(table, opt) {
    table.on('xhr.dt', function (e, settings, json) {
        if (json != null && json.errorMsg != null) {
            showMsg(json.errorMsg, "error");
        }
        else if (json == null) {
            parent.location.href = contextPath + "/index.jsp";
        }
    });
    if (table.hasClass("dataTable-reorder")) {
        opt.sDom = "R" + opt.sDom;
    }
    if (table.hasClass("dataTable-colvis")) {
        opt.sDom = "C" + opt.sDom;
        opt.oColVis = {
            "buttonText": "选择列 <i class='icon-angle-down'></i>"
        };
    }
    if (table.hasClass('dataTable-tools')) {
        opt.sDom = "T" + opt.sDom;
        opt.oTableTools = {
            "sSwfPath": "js/plugins/dataTable/swf/copy_csv_xls_pdf.swf"
        };
    }
    if (table.hasClass("dataTable-grouping") && table.attr("data-grouping") === "expandable") {
        opt.bLengthChange = false;
        opt.bPaginate = false;
    }
}

function datatableSetScroller(table, opt) {
    if (table.hasClass("dataTable-scroll-x")) {
        opt.sScrollX = "100%";
        opt.bScrollCollapse = true;
    }
    if (table.hasClass("dataTable-scroll-y")) {
        opt.sScrollY = "300px";
        opt.bPaginate = false;
        opt.bScrollCollapse = true;
    }
    if (table.hasClass("dataTable-scroller")) {
        opt.sScrollY = "300px";
        opt.bDeferRender = true;
        if (table.hasClass("dataTable-tools")) {
            opt.sDom = 'TfrtiS';
        } else {
            opt.sDom = 'frtiS';
        }
        opt.sAjaxSource = "js/plugins/dataTable/demo.txt";
    }
}

/*dataTable初始化*/
function getDataTableOpt(table) {
    var opt = {
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sSearch": "",
            "sInfo": "<span>_START_</span> 到 <span>_END_</span> 共 <span>_TOTAL_</span> 条",
            "sLengthMenu": "_MENU_",
            "sInfoFiltered": "_MAX_",
            "sInfoEmpty": "<span>0</span> 到 <span>0</span> 共 <span>0</span> 条",
            "oPaginate": {
                "sFirst": "首页",
                "sLast": "尾页",
                "sNext": "下一页",
                "sPrevious": "上一页"
            }
        },
        'sDom': "lfrtip"
    };
    datatableSetOptionNoElement(table, opt);
    datatableDoStuffOption(table, opt);
    return opt;
}


/*dataTable初始化*/
function getDataTableServerSideOpt(table) {
    var opt = {
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sInfo": "<span>_START_</span> 到 <span>_END_</span> 共 <span>_TOTAL_</span> 条",
            "sInfoEmpty": "<span>0</span> 到 <span>0</span> 共 <span>0</span> 条",
            "oPaginate": {
                "sFirst": "首页",
                "sLast": "尾页",
                "sNext": "下一页",
                "sPrevious": "上一页"
            }
        },
        'sDom': "lfrtip"
    };
    datatableSetOptionNoElement(table, opt);
    datatableDoStuffOption(table, opt);

    return opt;
}

/*带按钮有分页的dataTable美化*/
function dataTableUi(dataTableId, buttonName, buttonId) {
    if (buttonName === undefined)
        buttonName = ["添加", "修改", "删除"];
    if (buttonId === undefined)
        buttonId = ["add", "edit", "delete"];
    $("#" + dataTableId + "_length").wrap('<div  class="cl table-header" id="' + dataTableId + '-header"></div>');
    $("#" + dataTableId + "_filter").appendTo("#" + dataTableId + "-header");
    for (var i in buttonName) {
        if (buttonName.hasOwnProperty(i)) {
            $("#" + dataTableId + "_length").before('<a  class="table-btn" id="' + buttonId[i] + '">' + buttonName[i] + '</a>');
        }
    }
    $("#" + dataTableId + "_length").before($("#" + dataTableId + "_filter"));
    //以下两行是兼容dm
    $("#" + dataTableId + "_length").find("select").addClass("select");
    $("#" + dataTableId + "_filter").find("input").addClass("input-text");
    $("#" + dataTableId + "_paginate").wrap('<div  class="cl table-footer"  id="' + dataTableId + '-footer"></div>');
    $("#" + dataTableId + "_info").prependTo($("#" + dataTableId + "-footer"));
}

/*带按钮无分页的dataTable美化*/
function dtBPaginateUi(dataTableId, buttonName, buttonId) {
    if (buttonName === undefined)
        buttonName = ["添加", "修改", "删除"];
    if (buttonId === undefined)
        buttonId = ["add", "edit", "delete"];
    $("#" + dataTableId + "_filter").wrap('<div  class="cl table-header"  id="' + dataTableId + '-header"></div>');
    for (var i in buttonName) {
        if (buttonName.hasOwnProperty(i)) {
            $("#" + dataTableId + "-header").append('<a  class="table-btn" id="' + buttonId[i] + '">' + buttonName[i] + '</a>');
        }
    }
    //以下两行是兼容dm
    $("#" + dataTableId + "_filter").find("input").addClass("input-text");
    $("#" + dataTableId + "_paginate").wrap('<div  class="cl table-footer"  id="' + dataTableId + '-footer"></div>');
    $("#" + dataTableId + "_info").prependTo($("#" + dataTableId + "-footer"));
}

/*没按钮有分页的dataTable美化*/
function normalUi(dataTableId) {
    $("#" + dataTableId + "_filter").wrap('<div  class="cl table-header"  id="' + dataTableId + '-header"></div>');
    //以下两行是兼容dm
    $("#" + dataTableId + "_filter").find("input").addClass("input-text");
    $("#" + dataTableId + "_paginate").wrap('<div  class="cl table-footer"  id="' + dataTableId + '-footer"></div>');
    $("#" + dataTableId + "_info").prependTo($("#" + dataTableId + "-footer"));
}

function drawDataTableWithChilds(table) {
    table.css("width", '100%');
    $('.dataTables_filter input').attr("placeholder", "检索内容...");
    $("#check_all").click(function () {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
    });
}

function drawDataTable(table, opt) {
    opt.deferRender = true;
    var oTable = table.dataTable(opt);
    table.css("width", '100%');
    if (table.hasClass("dataTable-scroll-x")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    if (table.hasClass("dataTable-scroll-y")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    $('.dataTables_filter input').attr("placeholder", "检索内容...");
    $("#check_all").click(function () {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
        if (this.checked === true) {
            table.find("tr").addClass('selected');
        } else if (this.checked === false) {
            table.find("tr").removeClass('selected');
        }
    });
    if (table.hasClass("dataTable-columnfilter")) {
        oTable.columnFilter({
            "sPlaceHolder": "head:after"
        });
    }
    if (table.hasClass("dataTable-grouping")) {
        var rowOpt = {};

        if (table.attr("data-grouping") === 'expandable') {
            rowOpt.bExpandableGrouping = true;
        }
        oTable.rowGrouping(rowOpt);
    }
    oTable.fnDraw();
    oTable.fnAdjustColumnSizing();
    return oTable;
}


function drawDataTableServerSide(table, opt, length) {
    opt.serverSide = true;
    opt.pageLength = length;
    opt.deferRender = true;
    var oTable = table.dataTable(opt);
    table.css("width", '100%');
    if (table.hasClass("dataTable-scroll-x")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    if (table.hasClass("dataTable-scroll-y")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    $('.dataTables_filter input').attr("placeholder", "检索内容...");
    $("#check_all").click(function () {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
        if (this.checked === true) {
            table.find("tr").addClass('selected');
        } else if (this.checked === false) {
            table.find("tr").removeClass('selected');
        }
    });
    if (table.hasClass("dataTable-columnfilter")) {
        oTable.columnFilter({
            "sPlaceHolder": "head:after"
        });
    }
    if (table.hasClass("dataTable-grouping")) {
        var rowOpt = {};

        if (table.attr("data-grouping") === 'expandable') {
            rowOpt.bExpandableGrouping = true;
        }
        oTable.rowGrouping(rowOpt);
    }
    oTable.fnDraw();
    oTable.fnAdjustColumnSizing();
    return oTable;
}

function setDataTableOpt(table, opt) {
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;
    var oTable = table.dataTable(opt);
    table.css("width", '100%');
    if (table.hasClass("dataTable-scroll-x")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    if (table.hasClass("dataTable-scroll-y")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    $('.dataTables_filter input').attr("placeholder", "检索内容...");
    $("#check_all").click(function () {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
    });
    if (table.hasClass("dataTable-columnfilter")) {
        oTable.columnFilter({
            "sPlaceHolder": "head:after"
        });
    }
    if (table.hasClass("dataTable-grouping")) {
        var rowOpt = {};

        if (table.attr("data-grouping") === 'expandable') {
            rowOpt.bExpandableGrouping = true;
        }
        oTable.rowGrouping(rowOpt);
    }
    oTable.fnDraw();
    oTable.fnAdjustColumnSizing();
    table.find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    table.on('page.dt', function () {
        table.find("tr").removeClass('selected');
    });
    return oTable;
}

function setDataTableOptServerSide(table, opt, length) {
    opt.serverSide = true;
    opt.pageLength = length;
    opt.lengthChange = false;
    //opt.searching = false;
    opt.ordering = false;
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.deferRender = true;

    var oTable = table.dataTable(opt);
    table.css("width", '100%');
    if (table.hasClass("dataTable-scroll-x")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    if (table.hasClass("dataTable-scroll-y")) {
        $(window).resize(function () {
            oTable.fnAdjustColumnSizing();
        });
    }
    $("#check_all").click(function () {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
    });
    if (table.hasClass("dataTable-columnfilter")) {
        oTable.columnFilter({
            "sPlaceHolder": "head:after"
        });
    }
    if (table.hasClass("dataTable-grouping")) {
        var rowOpt = {};

        if (table.attr("data-grouping") === 'expandable') {
            rowOpt.bExpandableGrouping = true;
        }
        oTable.rowGrouping(rowOpt);
    }
    oTable.fnDraw();
    oTable.fnAdjustColumnSizing();
    table.find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    table.on('page.dt', function () {
        table.find("tr").removeClass('selected');
    });
    return oTable;
}


/*layer组件关闭弹出框口*/
function layer_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

var showMsgCloseLayer = function (msg, layer_add_index, layer_edit_index) {
    layer.msg(msg);
    setTimeout(function () {
        layer.closeAll('dialog');
    }, 1000);
    if (msg === "添加成功") {
        layer.close(layer_add_index);
    }
    if (msg === "编辑成功") {
        layer.close(layer_edit_index);
    }
};

/*页面按钮根据权限实现隐藏 20160314*/
var pagePerm = function () {
    var a = window.location.href;
    var fileName = a.substring(a.lastIndexOf('/') + 1);
    var menuUrl = contextPath + "/menu/showPageButtons";
    $.ajax({
        dataType: "json",
        async: false,
        url: menuUrl,
        data: {"fileName": fileName},
        success: function (json) {
            if (json) {
                for (var i in json) {
                    if (json.hasOwnProperty(i)) {
                        var pageUrl = window.location.href;
                        var result = pageUrl.split("/");
                        var jspName_1 = result[result.length - 3];
                        var jspName_2 = result[result.length - 2];
                        var jspName_3 = result[result.length - 1];
                        var jspName = jspName_1 + '/' + jspName_2 + '/' + jspName_3;
                        if (json[i].id.split(",")[0] === jspName) {
                            var ele = json[i].id.split(",")[1];
                            $(ele).hide();
                        }
                    }
                }
            }
        }
    });
};

/*校验当前表格是否有数据，若没有，则给予提示*/
function vaildTabData(tableId) {
    var tableData = $("#" + tableId + " tr:eq(1) td:eq(0)").html();
    if (tableData === "No data available in table") {
        showMsg('该表格无数据,不能对其进行操作！', 'warning');
        return false;
    }
    return true;
}

/*弹出层*/
/*
 参数解释：
 title   标题
 url      请求的url
 id      需要操作的数据id
 w      弹出层宽度（缺省调默认值）
 h      弹出层高度（缺省调默认值）
 */
function layer_show(title, url, w, h) {
    if (title == null) {
        title = false;
    }
    if (url == null || url === '') {
        url = "404.html";
    }
    if (w == null) {
        w = 800;
    }
    if (h == null) {
        h = ($(window).height() - 50);
    }
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
}

function successForPKListCommon(data, id, defaultValue) {
    for (var i in data) {
        if (data[i].pkValue !== "") {
            if (defaultValue !== undefined && defaultValue !== ""
                && defaultValue !== null && defaultValue === data[i].pkValue) {
                $("#" + id).append("<option value='" + data[i].pkValue + "' selected>" + data[i].pkDesc + "</option>");
            } else {
                $("#" + id).append("<option value='" + data[i].pkValue + "'>" + data[i].pkDesc + "</option>");
            }
        }
    }
    // 查询select2列表
    if ($("#" + id).hasClass("select2")) {
        $("#" + id).select2();
    }
}
function successForPKListIsArray(data, id, defaultValue, normalSelect) {
    var strOption = "";
    for (var i in data) {
        if (data.hasOwnProperty(i) && data[i].pkValue !== "") {
            if (defaultValue !== undefined && defaultValue !== ""
                && defaultValue != null && defaultValue === data[i].pkValue) {
                strOption += "<option value='" + data[i].pkValue + "' selected>" + data[i].pkDesc + "</option>";
            } else {
                strOption += "<option value='" + data[i].pkValue + "'>" + data[i].pkDesc + "</option>";
            }
        }
    }
    for (var n in id) {
        if (id.hasOwnProperty(n)) {
            $("#" + id[n]).html("");
            if (data.length === 0) {
                $("#" + id[n]).append("<option value='' selected></option>");
            }
            //chosen不设此项
            if (normalSelect === true) {
                $("#" + id[n]).append("<option value=''>请选择</option>");
            }
            if ($("#" + id[n]).hasClass("select2")) {
                $("#" + id[n]).select2().val(null).trigger("change");
            }
            for (var i in data) {
                if (data.hasOwnProperty(i) && data[i].pkValue !== "") {
                    if (defaultValue !== undefined && defaultValue !== ""
                        && defaultValue != null && defaultValue === data[i].pkValue) {
                        $("#" + id[n]).append("<option value='" + data[i].pkValue + "' selected>" + data[i].pkDesc + "</option>");
                    } else {
                        $("#" + id[n]).append("<option value='" + data[i].pkValue + "'>" + data[i].pkDesc + "</option>");
                    }
                }
            }
            // 使用了chosen的列表进行更新
            // 查询select2列表
            if ($("#" + id[n]).hasClass("select2")) {
                $("#" + id[n]).select2();
            }
        }
    }
}

function getPkList(option) {
    var url, id, params, async, type, normalSelect;
    if (option.url)
        url = option.url;
    else
        return;
    if (option.url)
        id = option.id;
    else return;
    if (option.url)
        params = option.params;
    if (option.url)
        async = option.async;
    if (option.url)
        type = option.type;
    if (params === undefined)
        params = {};
    if (async === undefined)
        async = true;
    if (type === undefined || type === "" || type == null)
        type = "POST";
    if (option.normalSelect === undefined)
        normalSelect = true;
    function isArrayS(o) {
        if (typeof Array.isArray === "function") {
            return Array.isArray(o);
        } else {
            return Object.prototype.toString.call(o) === "[object Array]";
        }
    }

    if (isArrayS(id)) {
        $.ajax({
            url: url,
            type: type,
            dataType: "json",
            async: async,
            data: params,
            error: function () {
                for (var n in id) {
                    if (id.hasOwnProperty(n)) {
                        $("#" + id[n]).html("");
                        $("#" + id[n]).append("<option value='' selected></option>");
                    }
                }
            },
            success: function (data) {
                successForPKListIsArray(data, id, option.defaultValue, normalSelect);
            }
        });
    } else {
        $.ajax({
            url: url,
            type: type,
            dataType: "json",
            async: async,
            data: params,
            error: function () {
                $("#" + id).html("");
                $("#" + id).append("<option value='' selected></option>");
            },
            success: function (data) {
                $("#" + id).html("");
                if (data.length === 0) {
                    $("#" + id).append("<option value='' selected ></option>");
                }
                //chosen不设此项
                if (normalSelect === true) {
                    $("#" + id).append("<option value=''>请选择</option>");
                }
                successForPKListCommon(data, id, option.defaultValue);
            }
        });
    }
}

