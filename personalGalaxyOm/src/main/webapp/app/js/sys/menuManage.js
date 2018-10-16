var layer_add_index, layer_edit_index;
$(document).ready(function () {
    getList("nestable_list_menu");
    $('.panel-danger').lobiPanel({
        reload: false,   //重载按钮关闭
        close: false,    //关闭按钮关闭
        editTitle: false, //标题更改按钮关闭
        unpin: false,    //移动按钮关闭
        minimize: false,   //面板内容收缩关闭
        expand: false    //窗口大小变化关闭
    });
    $("#nestable_list_menu").nestable({
        group: 0,
        maxDepth: 5
    });
    $("#nestable_list_menu").nestable('collapseAll');
    //双击选中事件 更改背景色  视为选中事件
    $('div').on('click', '.dd3-content', function () {
        if ($(this).css("background") === "#ffb848") {
            $(this).css({"background": "#fafafa"});
        } else {
            $('#nestable_list_menu').find(".dd3-content").css({"background": "#fafafa"});
            $(this).css({"background": "#ffb848"});
        }
    });
});

//添加
function menuManageAdd() {
    layer_add_index = layer.open({
        type: 2,
        title: "菜单管理添加",
        content: "menuManageAdd.jsp",
        area: [800 + 'px', 400 + 'px'],
        end: function () {
            getList("nestable_list_menu");
            $("#nestable_list_menu").nestable({
                group: 0,
                maxDepth: 5
            });
            $("#nestable_list_menu").nestable('collapseAll');
        }
    });
}

//添加
function buttonManageAdd() {
    var i = 0, length = $('#nestable_list_menu').find(".dd3-content").length;
    $('#nestable_list_menu').find(".dd3-content").each(function () {
        if ($(this).css("background-color") === "rgb(255, 184, 72)") {  //被选中数据处理事件
            var menuName = this.textContent[0];
            if (menuName === "#") {
                showMsg('只能添加菜单的按钮！', 'warning');
                return;
            }
            layer_add_index = layer.open({
                type: 2,
                title: "屏蔽按钮添加",
                content: "buttonManageAdd.jsp",
                area: [800 + 'px', 400 + 'px'],
                end: function () {
                    getList("nestable_list_menu");
                    $("#nestable_list_menu").nestable({
                        group: 0,
                        maxDepth: 5
                    });
                    $("#nestable_list_menu").nestable('collapseAll');
                }
            });
        } else {
            i++;
        }
    });
    if (i === length) {
        showMsg('请先选择一行数据！', 'warning');
    }
}

//修改
function menuManageEdit() {
    var i = 0, length = $('#nestable_list_menu').find(".dd3-content").length;
    $('#nestable_list_menu').find(".dd3-content").each(function () {
        if ($(this).css("background-color") === "rgb(255, 184, 72)") {  //被选中数据处理事件
            var menuName = this.textContent[0];
            if (menuName === "#") {
                layer_edit_index = layer.open({
                    type: 2,
                    title: "屏蔽按钮修改",
                    content: "buttonManageEdit.jsp",
                    area: [800 + 'px', 400 + 'px'],
                    end: function () {
                        getList("nestable_list_menu");
                        $("#nestable_list_menu").nestable({
                            group: 0,
                            maxDepth: 5
                        });
                        $("#nestable_list_menu").nestable('collapseAll');
                    }
                });
            }
            else {
                layer_edit_index = layer.open({
                    type: 2,
                    title: "菜单管理修改",
                    content: "menuManageEdit.jsp",
                    area: [800 + 'px', 400 + 'px'],
                    end: function () {
                        getList("nestable_list_menu");
                        $("#nestable_list_menu").nestable({
                            group: 0,
                            maxDepth: 5
                        });
                        $("#nestable_list_menu").nestable('collapseAll');
                    }
                });
            }
        } else {
            i++;
        }
    });
    if (i === length) {
        showMsg('请先选择一行数据！', 'warning');
    }
}

//删除加判断，（判断父菜单还是子菜单，弹出不同询问框）
function buttonManageDel() {
    var id = 0;
    var menuName = "";
    var i = 0, length = $('#nestable_list_menu').find(".dd3-content").length;
    $('#nestable_list_menu').find(".dd3-content").each(function () {
        if ($(this).css("background-color") === "rgb(255, 184, 72)") {  //被选中数据处理事件
            id = this.id;
            menuName = this.textContent[0];
        } else {
            i++;
        }
    });
    if (i === length) {
        showMsg('请先选择一行数据！', 'warning');
    }

    if (menuName === "#") {
        layer.confirm('确认要删除该条按钮数据吗？', function () {
            buttonManageDeleteAction(id);
        });
    }
    else {
        showMsg('只能删除按钮数据！', 'warning');
    }
}

function buttonManageDeleteAction(id) {
    var url = contextPath + "/menu/delete";
    sendPostRequest(url, {"menuId": id}, callbackButtonManageDeleteAction, "json");                //将获取数据发送给后台处理
}

function callbackButtonManageDeleteAction(json) {
    if (json.success) {
        layer.msg('删除成功！');
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 1000);
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
    getList("nestable_list_menu");
    $("#nestable_list_menu").nestable({
        group: 0,
        maxDepth: 5
    });
    $("#nestable_list_menu").nestable('collapseAll');
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

function getList(element) {
    var nestable;
    $.ajax({
        url: contextPath + "/menu/getList",
        dataType: "json",
        async: false,
        success: function (json) {
            nestable = "<div class='dd' id='" + element + "'><ol class='dd-list'>";
            for (var i in json) {
                if (json[i].id.split(",")[2] !== "N") {
                    nestable += "<li class='dd-item dd3-item' data-id=" + i + ">" +
                        "<div class='dd-handle dd3-handle'></div>" +
                        "<div class='dd3-content' id=" + json[i].id.split(",")[0] + ">" + json[i].id.split(",")[1] + "</div>";
                } else if (json[i].id.split(",")[2] === "N") {
                    nestable += "<li class='dd-item dd3-item' data-id=" + i + ">" +
                        "<div class='dd-handle dd3-handle'></div>" +
                        "<div class='dd3-content' id=" + json[i].id.split(",")[0] + "><strike>" + json[i].id.split(",")[1] + "</strike></div>";
                }
                if (json[i].children) {
                    nestable += "<ol class='dd-list'>";
                    for (var j in json[i].children) {
                        if (json[i].id.split(",")[2] === "N") {
                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + ">" +
                                "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + "><strike>" + json[i].children[j].id.split(",")[1] + "</strike></div>";
                        } else {
                            if (json[i].children[j].children) {
                                if (json[i].children[j].id.split(",")[2] !== "N") {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + ">" +
                                        "<div class='dd-handle dd3-handle'></div>" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + ">" + json[i].children[j].id.split(",")[1] + "</div>";
                                } else if (json[i].children[j].id.split(",")[2] === "N") {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + ">" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + "><strike>" + json[i].children[j].id.split(",")[1] + "</strike></div>";
                                }
                            }
                            else {
                                if (json[i].children[j].id.split(",")[2] !== "N") {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + ">" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + ">" + json[i].children[j].id.split(",")[1] + "</div>";
                                } else if (json[i].children[j].id.split(",")[2] === "N") {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + ">" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + "><strike>" + json[i].children[j].id.split(",")[1] + "</strike></div>";
                                }
                            }
                        }
                        if (json[i].children[j].children) {
                            nestable += "<ol class='dd-list'>";
                            for (var k in json[i].children[j].children) {
                                if (json[i].children[j].id.split(",")[2] === "N") {
                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + ">" +
                                        "<div class='dd3-content' id=" + json[i].children[j].id.split(",")[0] + "><strike>" + json[i].children[j].id.split(",")[1] + "</strike></div>";
                                } else {
                                    if (json[i].children[j].children[k].children) {
                                        if (json[i].children[j].children[k].id.split(",")[2] !== "N") {
                                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + ">" +
                                                "<div class='dd-handle dd3-handle'></div>" +
                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[0] + ">" + json[i].children[j].children[k].id.split(",")[1] + "</div>";
                                        } else if (json[i].children[j].children[k].id.split(",")[2] === "N") {
                                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + ">" +
                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].id.split(",")[1] + "</strike></div>";
                                        }
                                    }
                                    else {
                                        if (json[i].children[j].children[k].id.split(",")[2] !== "N") {
                                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + ">" +
                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[0] + ">" + json[i].children[j].children[k].id.split(",")[1] + "</div>";
                                        } else if (json[i].children[j].children[k].id.split(",")[2] === "N") {
                                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + ">" +
                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].id.split(",")[1] + "</strike></div>";
                                        }
                                    }
                                }
                                if (json[i].children[j].children[k].children) {
                                    nestable += "<ol class='dd-list'>";
                                    for (var m in json[i].children[j].children[k].children) {
                                        if (json[i].children[j].children[k].id.split(",")[2] === "N") {
                                            nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + ">" +
                                                "<div class='dd3-content' id=" + json[i].children[j].children[k].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].id.split(",")[1] + "</strike></div>";
                                        } else {
                                            if (json[i].children[j].children[k].children[m].children) {
                                                if (json[i].children[j].children[k].children[m].id.split(",")[2] !== "N") {
                                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + ">" +
                                                        "<div class='dd-handle dd3-handle'></div>" +
                                                        "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[0] + ">" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</div>";
                                                } else if (json[i].children[j].children[k].children[m].id.split(",")[2] === "N") {
                                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + ">" +
                                                        "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</strike></div>";
                                                }
                                            }
                                            else {
                                                if (json[i].children[j].children[k].children[m].id.split(",")[2] !== "N") {
                                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + ">" +
                                                        "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[0] + ">" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</div>";
                                                } else if (json[i].children[j].children[k].children[m].id.split(",")[2] === "N") {
                                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + ">" +
                                                        "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</strike></div>";
                                                }
                                            }
                                        }
                                        if (json[i].children[j].children[k].children[m].children) {
                                            nestable += "<ol class='dd-list'>";
                                            for (var n in json[i].children[j].children[k].children[m].children) {
                                                if (json[i].children[j].children[k].children[m].id.split(",")[2] === "N") {
                                                    nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + "-" + n + ">" +
                                                        "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].children[m].id.split(",")[1] + "</strike></div></li>";
                                                } else {
                                                    if (json[i].children[j].children[k].children[m].children[n].id.split(",")[2] !== "N") {
                                                        nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + "-" + n + ">" +
                                                            "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].children[n].id.split(",")[0] + ">" + json[i].children[j].children[k].children[m].children[n].id.split(",")[1] + "</div></li>";
                                                    } else if (json[i].children[j].children[k].children[m].children[n].id.split(",")[2] === "N") {
                                                        nestable += "<li class='dd-item dd3-item' data-id=" + i + "-" + j + "-" + k + "-" + m + "-" + n + ">" +
                                                            "<div class='dd3-content' id=" + json[i].children[j].children[k].children[m].children[n].id.split(",")[0] + "><strike>" + json[i].children[j].children[k].children[m].children[n].id.split(",")[1] + "</strike></div></li>";
                                                    }
                                                }
                                            }
                                            nestable += "</ol>";
                                        }
                                        nestable += "</li>";
                                    }
                                    nestable += "</ol>";
                                }
                                nestable += "</li>";
                            }
                            nestable += "</ol>";
                        }
                        nestable += "</li>";
                    }
                    nestable += "</ol>";
                }
                nestable += "</li>";
            }
            nestable += "</ol></div>";
            $('#nestableMenuList').empty().append(nestable);
            $('#nestableMenuList').lobiPanel();
        }
    });
}

function showMsgDuringTime(msg) {
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
}