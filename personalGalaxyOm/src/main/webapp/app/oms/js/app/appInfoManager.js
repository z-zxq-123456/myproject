    var layer_add_index, layer_edit_index;
    function showMsgDuringTime(msg)
    {
        showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    }
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*user增加*/
    function app_Add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px'],
        });
    }
    /*user删除*/
    function app_del() {
        if ($("#appList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteEcmAppInfo";
        var rowData = $('#appList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            $('#appList').dataTable().api().row(".selected").remove().draw(false);
            reference();
            showMsgDuringTime('删除成功！');
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    /*user修改*/
    function app_Edit(title, url, w, h) {
        if ($("#appList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px'],
        });
    }
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#appList"));

        opt.ajax = {
            "url": contextPath + "/findEcmAppInfo",
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "appName",
                "defaultContent": ""
            },
            {
                "data": "appSimpenNm",
                "defaultContent": ""
            },
            {
                "data": "redisMidwareName",
                "defaultContent": ""
            },
            {
                "data": "zkMidwareName",
                "defaultContent": ""
            },
            {
                "data": "dbMidwareName",
                "defaultContent": ""
            },
            {
                "data": "appTypeName",
                "defaultContent": ""
            },
            {
                "data": "appPath",
                "defaultContent": ""
            },
            {
                "data": "appDesc",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#appList"), opt);
        //界面美化tables
        $("#appList").beautyUi({
            tableId: "appList"
        });
        $("#add").on("click", function () {
            app_Add('应用信息增加', 'appInfoAdd.jsp', '600', '550');
        });
        $("#edit").on("click", function () {
            app_Edit('应用信息修改', 'appInfoEdit.jsp', '600', '550');
        });
        $("#delete").on("click", function () {
            app_del();
        });
        /*页面按钮根据权限实现隐藏*/

    });
    function reference(){
         $.ajax({
                type: "POST",
                url: contextPath + "/menu/reference",
                dataType: "json",
                async: false,
                success: function(){
                    parent.location.href = contextPath + "/index.jsp";
                }
         });
    }