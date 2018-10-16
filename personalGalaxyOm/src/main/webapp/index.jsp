<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<html>
<head>
    <title>sm@rtEnsemble-om</title>
</head>
<style type="text/css">
    .firstMenu {
        line-height: 40px;
        padding-left: 46px;
        font-size: 14px;
        border-bottom: 1px solid #f0f0f0;
    }

    .firstMenu:hover {
        background-color: #f2f2f2;
    }

    .menu_dropdown > ul > li > a.current {
        background-color: #d9d9d9;
    }
</style>

<body class="big-page">
<header class="Galaxy-header cl">
    <div class="logoArea">
        <a class="Galaxy-logo l" title="DCITS">aiBank Sm@rt OM</a>
        <span class="Galaxy-subtitle l">v1.0</span>
    </div>
    <div class="header-top">
        <nav class="mainnav cl" id="Galaxy-nav">
            <ul class="Galaxy-headerMid" id="firstTopMenu">
            </ul>
            <ul class="Galaxy-userbar_narrow">
                <li class="dropDown dropDown_hover"><a href="#" class="dropDown_G"><i class="iconfont">&#xe633;</i>&nbsp;${UserName}
                </a>
                    <ul class="dropDown-menu radius box-shadow">
                        <li><a href="javascript:;"
                               onclick="changePassword('修改个人密码','changePassword.jsp','600','350')"><i class="iconfont">
                            &#xe620;</i> 修改个人密码</a></li>
                        <li><a href="#" onclick="changeAcct()"><i class="iconfont">&#xe709;</i>切换</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="header-bottom">
        <div id="Galaxy-tabNav" class="Galaxy-tabNav">
            <div class="Galaxy-tabNav-wp">
                <ul id="min_title_list" class="acrossTab cl">
                    <li class="active"><span title="首页" data-href="firstPage.html">首页</span><em></em></li>
                </ul>
            </div>
            <div class="Galaxy-tabNav-more cl">
                <a id="js-tabNav-prev" class="button-select M" href="javascript:;"><i class="iconfont">&#xe74d;</i></a>
                <a id="js-tabNav-next" class="button-select M" href="javascript:;"><i class="iconfont">&#xe74e;</i></a>
            </div>
        </div>
    </div>
</header>
<aside class="Galaxy-aside">
    <div class="menu_dropdown" id="asideMenu"></div>
</aside>
<div class="displayArrow"><a class="pngfix open" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Galaxy-article-box">
    <div id="iframe_box" class="Galaxy-article">
        <div class="show_iframe firs-iframe">
            <div style="display:none" class="loading"></div>
            <iframe id="frame_page" scrolling="yes" frameborder="0" src="${userRoleJsp}"></iframe>
        </div>
    </div>
</section>
<script type="text/javascript">
    /*JS/HTML格式化工具*/
    function jsHtmlFormat_tool(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*JS/HEML转换工具*/
    function htmlJsConvert_tool(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*CSS代码格式化工具*/
    function cssFormat_tool(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*字母大小写工具*/
    function letterChange_tool(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*登出*/
    function loginOff() {
        layer.confirm("你确定要关闭窗口？", {
            btn: ['关闭', '登出']
        }, function () {
            $.ajax({
                url: contextPath + "/loginOff",
                success: function (json) {
                    window.open('', '_self', '');
                    window.close();
                }
            });
        }, function () {
            $.ajax({
                url: contextPath + "/loginOff",
                success: function (json) {
                    location.href = contextPath + "/login.jsp";
                }
            });
        });
    }
    /*切换账号*/
    function changeAcct() {
        $.ajax({
            url: contextPath + "/loginOff",
            success: function (json) {
                location.href = contextPath + "/login.jsp";
            }
        });
    }

    $(function () {
        /*菜单拼接*/
        $(".displayArrow").hide();
        var menuUrl = contextPath + "/menu/showMenuList";
        var topMenuId;
        $.ajax({
            dataType: "json",
            async: false,
            url: menuUrl,
            success: function (json) {
                if (json.length > 0) {
                    var firstMenu = "";
                    for (i = 0; i < json.length; i++) {
                        var menuId = json[i].id.split(",")[2];
                        firstMenu += "<li class='menuList'><a href='javascript:void(0)' id='" + menuId + "' onClick='displayTopMenuByClick(this)' title='" + json[i].id.split(",")[1] + "'><i class='iconfont' style='color:#fff;font-size:15px;margin-right:10px;'>" + json[i].id.split(",")[0] + "</i>" + json[i].id.split(",")[1] + "</a></li>";
                        if (json[i].children) {
                            $("#asideMenu").data(menuId, json[i].children);
                        }
                        if (0 == i) {
                            topMenuId = menuId;
                        }
                    }
                    $("#firstTopMenu").append(firstMenu);
                    displayMenu(topMenuId);
                }
                else {
                    $("#frame_page").hide();
                }
            }
        });
        /*左侧菜单*/
    });
    /*密码修改*/
    function changePassword(title, url, w, h) {
        layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px']
        });
    }

    (function ($) {
        var Menu = function (ele, opts) {
            this.$this = ele;
            this.defaults = {
                id: "1"
            };
            this.settings = $.extend({}, this.defaults, opts);
        };
        Menu.prototype = {
            init: function () {
                var num = this.settings.id;
                return this.$this.on("click", function () {
                    console.log("yes" + num);
                });
            }
        };
        $.fn.yesMenu = function (opt) {
            var star = new Menu(this, opt);
            return star.init();
        }
    }(jQuery));

    $("#menu-first").yesMenu({id: "1"});
    $("#menu-second").yesMenu({id: "2"});
    $("#menu-three").yesMenu({id: "3"});
    $("#menu-four").yesMenu({id: "4"});
    $("#menu-five").yesMenu({id: "5"});

    function displayTopMenuByClick(obj) {
        var id = $(obj).attr("id");
        displayMenu(id);
    }

    function displayMenu(id) {
        var json = $("#asideMenu").data(id);
        var menu = "";
        if (json) {
            for (i = 0; i < json.length; i++) {
                if (json[i].children) {
                    menu += "<dl><dt><i class='iconfont'>" + json[i].id.split(",")[0] + "</i>" + json[i].id.split(",")[1] + "</dt>";
                    menu += "<dd><ul>";
                    for (j = 0; j < json[i].children.length; j++) {
                        if (json[i].children[j].children) {
                            menu += "<li><a href='javascript:void(0)'>" + json[i].children[j].id.split(",")[1] + "<i class='iconfont'></i></a>";
                            menu += "<ul class='galaxy-menu-three'>";
                            for (k = 0; k < json[i].children[j].children.length; k++) {
                                if (json[i].children[j].children[k].children) {
                                    menu += "<li><a href='javascript:void(0)'>" + json[i].children[j].children[k].id.split(",")[1] + "<i class='iconfont'></i></a>";
                                    menu += "<ul class='galaxy-menu-four'>";
                                    for (m = 0; m < json[i].children[j].children[k].children.length; m++) {
                                        menu += "<li><a _href='" + json[i].children[j].children[k].children[m].id.split(",")[0] + "' href='javascript:void(0)' class='iconfont'>" + json[i].children[j].children[k].children[m].id.split(",")[1] + "<i class='iconfont'></i></a></li>";
                                    }
                                    menu += "</ul></li>";
                                }
                                else {
                                    menu += "<li><a _href='" + json[i].children[j].children[k].id.split(",")[0] + "' href='javascript:void(0)' class='iconfont'>" + json[i].children[j].children[k].id.split(",")[1] + "</a></li>";
                                }
                            }
                            menu += "</ul></li>";
                        }
                        else {
                            menu += "<li><a _href='" + json[i].children[j].id.split(",")[0] + "' href='javascript:void(0)'>" + json[i].children[j].id.split(",")[1] + "</a></li>";
                        }
                    }

                    menu += "</ul></dd>";
                    menu += "</dl>";
                }
                else {
                    menu += "<ul><li><a class='firstMenu' style='font-weight:normal;' _href='" + json[i].id.split(",")[0] + "'  href='javascript:void(0)'>" + json[i].id.split(",")[1] + "</a></li></ul>";
                }

            }
        }
        $("#asideMenu").empty().append(menu);
        $(".pngfix").removeClass("open");
        $("body").removeClass("big-page");
        $(".displayArrow").show();
        $.galaxyfold(".menu_dropdown dl dt", ".menu_dropdown dl dd", "fast", 1, "click");
        $.galaxyfold(".menu_dropdown dl dd>ul>li>a", ".menu_dropdown dl dd>ul>li>ul", "fast", 1, "click");
        $.galaxyfold(".menu_dropdown dl dd>ul>li>ul>li>a", ".menu_dropdown dd>ul>li>ul>li>ul", "fast", 1, "click");
        $.galaxyfold(".menu_dropdown dl dd>ul>li>ul>li>ul>li>a", ".menu_dropdown dd>ul>li>ul>li>ul>li>ul", "fast", 1, "click");
    }
</script>
</body>
</html>






