$(function() {
    //版本号文本的宽度自变化
    var value = "V1.1.0";
    //添加版本号  后期可以动态获取此参数value
    $(".version").text(value);
    $(".version").width(function () {
        var content = "<pre id=\"p1\" style=\"dispaly:none\">" + value + "</pre>";   //给<pre>设置不可见样式
        $("body").append(content);
        var width = $("#p1").width();
        $("#p1").remove();
        return width;
    });  //宽度自变化处理

});
//校验函数
function login() {
    if ($("#InputUserName").val().length < 1 || $("#InputUserName").val().length > 20) {
        mainW(20);
        $('.textMsg').empty().append('用户名为字母、数字和_组成，长度为1至20位');
        $('#login-msg').show();
        return false;
    }
    if ($("#InputPassword").val().length < 6 || $("#InputPassword").val().length > 32) {
        mainW(20);
        $('.textMsg').empty().append('密码长度为6至32位');
        $('#login-msg').show();
        return false;
    }
    $.ajax({
        type: "POST",
        url: contextPath + "/pwdSSOLogin",
        // url: contextPath + "/getLogin",
        async: "false",
        data: {
            userId: $("#InputUserName").val(),
            password: $("#InputPassword").val()
        },
        dataType: "json",
        success: function (json) {
            var errMsg = json.errorMsg;
            if (errMsg !== "000000") {
                mainW(20);
                $('.textMsg').empty().append(errMsg);
                $('#login-msg').show();
            } else {
                location.href = contextPath + "/index.jsp";
            }
        }
    });
}

//提醒摇晃函数
function mainW(n) {
    $('#login-main').stop().css({'margin-left': -180});
    if (n > 0)
        $('#login-msg').animate({'margin-left': -180 - n}, 60).animate({'margin-left': -180 + n}, 120, function () {
        mainW(n - 5);
    });
    else
        $('#login-msg').animate({'margin-left': -180}, 50);
}

function keyLogin(event) {
    var code = event.keyCode;
    if (code === 13) {
        login();
    }
}