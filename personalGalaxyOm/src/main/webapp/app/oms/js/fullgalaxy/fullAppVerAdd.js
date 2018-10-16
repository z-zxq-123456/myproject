    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    var form;
    function dataAdd() {
        var url = contextPath + "/saveAllAppVer";
        $('#form').attr("action", url);
        $('#form').submit();
    }
    function dataAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }