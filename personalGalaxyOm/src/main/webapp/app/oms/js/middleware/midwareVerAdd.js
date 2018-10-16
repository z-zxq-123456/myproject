    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    var form;

    function appInfoAdd() {

        var url = contextPath + "/saveMiddlewareVer";
        $('#form').attr("action",url);
        $('#form').submit();
    }

    function appInfoAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }