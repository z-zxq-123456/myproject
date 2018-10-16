var roleId;
$(document).ready(function () {
    $('.panel').lobiPanel({
        reload: false,   //重载按钮关闭
        close: false,    //关闭按钮关闭
        editTitle: false, //标题更改按钮关闭
        unpin: false,    //移动按钮关闭
        minimize: false,   //面板内容收缩关闭
        expand: false    //窗口大小变化关闭
    });
    //获取roleName
    getPkList({
        url: contextPath + "/pklist/getUserRoleName",
        id: "roleId",
        async: false
    });

    $('#roleId').select2({
        maximumResultsForSearch: Infinity
    });

    $("#roleId").change(function () {
        if ($('#roleId').val() !== '') {
            find();
        } else {
            $("#nestable_list_nestableOrginal").empty().append("<div class='dd-empty'></div>");
            $("#nestable_list_nestableOverplus").empty().append("<div class='dd-empty'></div>");
        }
    });
});

function save() {
    //前提是roleId不能为空
    var dataString = window.JSON.stringify($("#nestable_list_nestableOrginal").nestable('serialize'));//角色菜单字符串输出序列化
    sendPostRequest(contextPath + "/roleMenu/save", {
        data: dataString,
        roleId: $("#roleId").val()
    }, callbackSave, "json");
}

function callbackSave(json) {
    if (json.success) {
        refresh();
        layer.msg('保存成功！正在刷新页面...');
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 5000);
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function find() {
    //输入roleId进行查询得到与之相关的菜单
    getList("nestable_list_nestableOrginal", contextPath + "/roleMenu/getList", {roleId: $("#roleId").val()});
    $("#nestable_list_nestableOrginal").nestable({
        group: 1,
        maxDepth: 5
    });
    $("#nestable_list_nestableOrginal").nestable('collapseAll');
    //左侧菜单列表加载，左右相加形成网站全部菜单
    getList("nestable_list_nestableOverplus", contextPath + "/roleMenu/compareFind", {roleId: $("#roleId").val()});
    $("#nestable_list_nestableOverplus").nestable({
        group: 1,
        maxDepth: 5
    });
    $("#nestable_list_nestableOverplus").nestable("collapseAll");
}

function refresh() {
    var index1;
    $.ajax({
        url: contextPath + "/roleMenu/refresh",
        dataType: "json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: function (json) {
            if (json.success) {
                parent.location.href = contextPath + "/index.jsp";
                layer.msg('刷新成功！');
                setTimeout(function () {
                    layer.closeAll('dialog');
                }, 1000);
            } else if (json.errorMsg) {
                showMsg(json.errorMsg, 'errorMsg');
            }
        },
        complete: function () {
            layer.close(index1);
        }
    });
}

//列表初始化函数   由于页面有两个nestable采用同一函数，控制入参
function getList(element, path, params) {
    var nestable;
    //element 是表格ID、path  是AJAX请求路径 、params  为ajax 发出请求所带数据
    if (params === undefined)
        params = {};
    $.ajax({
        url: path,
        dataType: "json",
        async: false,
        data: params,
        success: function (json) {
            if (json.errorMsg) {
                showMsg(json.errorMsg, 'errorMsg');
            } else if (json.length !== 0) {
                nestable = "<div class='dd' id='" + element + "'><ol class='dd-list'>";
                for (var i in json) {
                    if (json.hasOwnProperty(i)) {
                        nestable += "<li class='dd-item dd3-item' data-id=" + json[i].id.split(",")[2] + ">" +
                            "<div class='dd-handle dd3-handle'></div>" +
                            "<div class='dd3-content' id=" + json[i].id.split(",")[2] + ">" + json[i].id.split(",")[1] + "</div>";
                        if (json[i].children) {
                            nestable += "<ol class='dd-list'>";
                            for (var j in json[i].children) {
                                if (json[i].children.hasOwnProperty(j)) {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + json[i].children[j].id.split(",")[2] + ">" +
                                        "<div class='dd-handle dd3-handle'></div>" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[2] + ">" + json[i].children[j].id.split(",")[1] + "</div>";
                                    if (json[i].children[j].children) {
                                        nestable += "<ol class='dd-list'>";
                                        for (var k in json[i].children[j].children) {
                                            if (json[i].children[j].children.hasOwnProperty(k)) {
                                                nestable += "<li class='dd-item dd3-item' data-id=" + json[i].children[j].children[k].id.split(",")[2] + ">" +
                                                    "<div class='dd-handle dd3-handle'></div>" +
                                                    "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[2] + ">" + json[i].children[j].children[k].id.split(",")[1] + "</div>";
                                                if (json[i].children[j].children[k].children) {
                                                    nestable += "<ol class='dd-list'>";
                                                    for (var m in json[i].children[j].children[k].children) {
                                                        if (json[i].children[j].children[k].children.hasOwnProperty(m)) {
                                                            nestable += "<li class='dd-item dd3-item' data-id=" + json[i].children[j].children[k].children[m].id.split(",")[2] + ">" +
                                                                "<div class='dd-handle dd3-handle'></div>" +
                                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[2] + ">" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</div>";
                                                            if (json[i].children[j].children[k].children[m].children) {
                                                                nestable += "<ol class='dd-list'>";
                                                                for (var n in json[i].children[j].children[k].children[m].children) {
                                                                    if (json[i].children[j].children[k].children[m].children.hasOwnProperty(n)) {
                                                                        nestable += "<li class='dd-item dd3-item' data-id=" + json[i].children[j].children[k].children[m].children[n].id.split(",")[2] + ">" +
                                                                            "<div class='dd-handle dd3-handle'></div>" +
                                                                            "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].children[n].id.split(",")[2] + ">" + json[i].children[j].children[k].children[m].children[n].id.split(",")[1] + "</div></li>";
                                                                    }
                                                                }
                                                                nestable += "</ol>";
                                                            }
                                                            nestable += "</li>";
                                                        }
                                                    }
                                                    nestable += "</ol>";
                                                }
                                                nestable += "</li>";
                                            }
                                        }
                                        nestable += "</ol>";
                                    }
                                    nestable += "</li>";
                                }
                            }
                            nestable += "</ol>";
                        }
                        nestable += "</li>";
                    }
                }
                nestable += "</ol></div>";
            } else {  //空数据处理
                nestable = "<div class='dd' id='" + element + "'><div class='dd-empty'></div>";
            }
            if (element === "nestable_list_nestableOrginal") {
                $('#nestableOrginal').empty().append(nestable);
                $('#nestableOrginal').lobiPanel();
            }
            else {
                $('#nestableOverplus').empty().append(nestable);
                $('#nestableOverplus').lobiPanel();
            }
        }
    });
}