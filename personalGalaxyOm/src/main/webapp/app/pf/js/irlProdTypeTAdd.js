var form;
    $(document).ready(function () {
         if(parent.parent.$("#operateType").val() == "add" || parent.parent.$("#operateType").val() == "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() == "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }

        $(".select2").select2();

             form = $("#irlProdTypeAdd").Validform({
                    tiptype: 2,
                    callback: function (form) {
                        irlProdTypeAdd();
                        return false;
                    }
                });

    function irlProdTypeAdd(){
        var url = contextPath+"/irlProdType/insert1";
        sendPostRequest(url,getFormData("irlProdTypeAdd"), callback_irlProdTypeAdd,"json");
    }

    function callback_irlProdTypeAdd(json){
        if (json.retStatus == 'F'){
            showMsg(json.retMsg,'info');
        } else if(json.retStatus == 'S'){
            parent.showMsgDuringTime("添加成功");
        }
        //form.resetForm();  JS表单清空
    }
        function irlProdTypeAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

     });
