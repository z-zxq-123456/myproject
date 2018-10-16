    function appInfoAdd() {
        var url = contextPath + "/saveEcmAppInfo";
        sendPostRequest(url, getFormData("form-appInfo-add"), callback_appInfoAdd, "json");
    }

    function callback_appInfoAdd(json) {
        if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        } else if (json) {
            appMenuAdd(json);
        }
    }
   function appInfoAddCancel() {
           var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
           parent.layer.close(index);  //关闭窗口
       }