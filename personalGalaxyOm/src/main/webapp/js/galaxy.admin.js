/*获取顶部选项卡总长度*/
function tabNavallwidth() {
    /*为了兼容利率市场化首页快捷展示区功能，故将此函数做一定的修改*/
    var topWindow = $(window.parent.document);
    var taballwidth = 0,
        $tabNav = topWindow.find(".acrossTab"),
        $tabNavWp = topWindow.find(".Galaxy-tabNav-wp"),
        $tabNavitem = topWindow.find(".acrossTab li"),
        $tabNavmore = topWindow.find(".Galaxy-tabNav-more");
    if (!$tabNav[0]) {
        return;
    }
    taballwidth = Number($tabNavitem.length * 118 + 63);
    $tabNav.width(taballwidth);
    var w = $tabNavWp.width();
    if (taballwidth > w)
        $tabNavmore.show();
    else
        $tabNavmore.hide();
}

/*左侧菜单响应式式展示*/
function galaxyAsideDisplay() {
    if ($(window).width() >= 768) {
        $(".galaxy-aside").show();
    }
}

/*皮肤应用路径加载问题*/
function getSkinCookie() {
    var v = getCookie("galaxyskin");
    if (v == null || v === "") {
        v = "default";
    }
    $("#skin").attr("href", "skin/" + v + "/skin.css");
}

/*页面初始化函数*/
$(function() {
    getSkinCookie();
    galaxyAsideDisplay();
    var resizeID;
    $(window).resize(function () {
        clearTimeout(resizeID);
        resizeID = setTimeout(function () {
            galaxyAsideDisplay();
        }, 500);
    });

    $(".Galaxy-nav-toggle").click(function () {
        $(".Galaxy-aside").slideToggle();
    });

    $(".Galaxy-aside").on("click", ".menu_dropdown dd li a", function () {
        if ($(window).width() < 768) {
            $(".Galaxy-aside").slideToggle();
        }
    });

    /*菜单被选中当前样式以及事件  20160216  三级菜单实现有问题*/
    $(".Galaxy-aside").on("click", ".menu_dropdown a", function () {
        if ($(".menu_dropdown").find("a").hasClass("current")) {
            $(".menu_dropdown").find("a").removeClass("current");
        }
        $(this).addClass("current");
    });

    /*首页快捷展示区点击事件定义*/
    $(".fastDisplay").on("click", "li a", function () {
        if ($(this).attr('_href')) {
            var bStop = false;
            var bStopIndex = 0;
            var _href = $(this).attr('_href');
            var _titleName = null;
            if ($(this).attr('name')) {
                _titleName = $(this).attr('name');
            } else {
                _titleName = $(this).html();
            }
            var topWindow = $(window.parent.document);
            var show_navLi = topWindow.find("#min_title_list li");
            show_navLi.each(function () {
                if ($(this).find('span').attr("data-href") === _href) {
                    bStop = true;
                    bStopIndex = show_navLi.index($(this));
                    return false;
                }
            });
            if (!bStop) {
                creatIframe(_href, _titleName);
                min_titleList();
            }
            else {
                show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
                var iframe_box = topWindow.find("#iframe_box");
                iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", _href);
            }
        }
    });

    /*选项卡导航的事件定义*/
    $(".Galaxy-aside").on("click", ".menu_dropdown a", function () {
        if ($(this).attr('_href')) {
            var bStop = false;
            var bStopIndex = 0;
            var _href = $(this).attr('_href');
            var _titleName = null;
            if ($(this).attr('name')) {
                _titleName = $(this).attr('name');
            } else {
                _titleName = $(this).html();
            }
            var topWindow = $(window.parent.document);
            var show_navLi = topWindow.find("#min_title_list li");
            show_navLi.each(function () {
                if ($(this).find('span').attr("data-href") === _href) {
                    bStop = true;
                    bStopIndex = show_navLi.index($(this));
                    return false;
                }
            });
            if (!bStop) {
                creatIframe(_href, _titleName);
                min_titleList();
            }
            else {
                show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
                var iframe_box = topWindow.find("#iframe_box");
                iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", _href);
            }
        }
    });

    function min_titleList() {
        $(window.parent.document);
    }

    function creatIframe(href, titleName) {
        var topWindow = $(window.parent.document);
        var show_nav = topWindow.find('#min_title_list');
        show_nav.find('li').removeClass("active");
        var iframe_box = topWindow.find('#iframe_box');
        show_nav.find('li:first-child').after('<li class="active"><span data-href="' + href + '">' + titleName + '</span><i></i></li>');
        /*处理选项卡字体超出用省略号代替操作*/
        var $span = show_nav.find('li[class="active"]').find("span");
        if ($span.width() > 75) {
            $span.addClass("ellipsis");
        }
        tabNavallwidth();
        var iframeBox = iframe_box.find('.show_iframe');
        iframeBox.hide();
        iframe_box.find('.firs-iframe').after('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src=' + href + '></iframe></div>');
        var showBox = iframe_box.find('.show_iframe:visible');
        showBox.find('iframe').attr("src", href).load(function () {
            showBox.find('.loading').hide();
        });
    }

    var num = 0;
    var oUl = $("#min_title_list");
    $(document).on("click", "#min_title_list li", function () {
        var bStopIndex = $(this).index();
        var iframe_box = $("#iframe_box");
        $("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
        iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
    });

    $(document).on("click", "#min_title_list li i", function () {
        var aCloseIndex = $(this).parents("li").index();
        $(this).parent().remove();
        $('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
        num === 0 ? num = 0 : num--;
        tabNavallwidth();
        /*连同下面双击事件一块添加回退函数*/
        order(num);
    });

    $(document).on("dblclick", "#min_title_list li", function () {
        var aCloseIndex = $(this).index();
        var iframe_box = $("#iframe_box");
        if (aCloseIndex > 0) {
            $(this).remove();
            $('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
            num === 0 ? num = 0 : num--;
            $("#min_title_list li").removeClass("active").eq(aCloseIndex - 1).addClass("active");
            iframe_box.find(".show_iframe").hide().eq(aCloseIndex - 1).show();
            tabNavallwidth();
            order(num);
        } else {
            return false;
        }
    });

    tabNavallwidth();
    /*选项卡超出范围按钮事件*/
    $('#js-tabNav-next').click(function () {
        num === oUl.find('li').length - 1 ? num = oUl.find('li').length - 1 : num++;
        toNavPos();
    });

    $('#js-tabNav-prev').click(function () {
        num === 0 ? num = 0 : num--;
        toNavPos();
    });

    function toNavPos() {
        oUl.stop().animate({'left': -num * 121 + 3}, 100);
    }

    function order(num) {
        if (num === 0) {
            num = 0;
        } else {
            num--;
        }
        oUl.stop().animate({'left': -num * 121 + 3}, 100);
    }

    /*换肤*/
    $("#galaxy-skin .dropDown-menu a").click(function () {
        var v = $(this).attr("data-val");
        setCookie("galaxyskin", v);
        $("#skin").attr("href", "skin/" + v + "/skin.css");
    });
});
