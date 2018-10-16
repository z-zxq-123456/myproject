    var isRemainConfig = parent.isRemainConfig;
    var isFinish;
    var sub_url = parent.sub_url;
    var interval;
    $(document).ready(function () {
        interval = setInterval(start, 1000);
        var jsonObj = "";
        if (isRemainConfig != null) {
            jsonObj = "isRemainConfig=" + isRemainConfig;
        }
        $.post(sub_url, jsonObj, function (result) {
            var result = eval('(' + result + ')');
            if (result.errorMsg) {
                parent.showMsg(result.errorMsg, 'error');
                close();
            } else {
                stopInterval(interval);
                setTimeout(function () {
                    $("#progress").attr("style", "width:100%");
                    $("#progress").text("100%");
                    $("#progress-animated").html("已完成");
                    layer.msg("三秒后关闭此页面！");
                    setTimeout(close, 3000);
                }, 1000);
            }
        });
    });
    function start() {
        var file_url = contextPath + '/findProgressNotify';
        $.post(file_url, {}, function (result) {
            var result = eval('(' + result + ')');
            if (result.errorMsg) {
                showMsg(result.errorMsg, 'error');
            } else {
                var deployProgress = result.progressNum;
                var deployMessage = result.progressMessage;
                if (deployProgress <= 100) {
                    $("#progress").attr("style", "width:" + deployProgress + "%");
                    $("#progress").text(deployProgress + "%");
                    $("#progress-animated").html(deployMessage);
                }
            }
        });
    }
    function stopInterval(interval) {
        clearInterval(interval);
    }
    function close() {
        parent.refresh();
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }