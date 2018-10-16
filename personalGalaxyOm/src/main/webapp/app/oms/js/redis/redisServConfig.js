    var layer_add_index, layer_edit_index;
    function showMsgDuringTime(msg)
    {
        showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    }
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#redisServList"));


        opt.ajax = {
            "url": contextPath + "/findServerConfig",
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "serverIp",
                "defaultContent": ""
            },
            {
                "data": "serverPort",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#redisServList"), opt);
        //界面美化tables
        $("#redisServList").beautyUi({
            tableId: "redisServList"
        });
        $("#add").on("click", function () {
            user_Add('Redis服务器信息添加', 'redisServAdd.jsp', '600', '400');
        });
        $("#edit").on("click", function () {
            user_Edit('Redis服务器信息修改', 'redisServEdit.jsp', '600', '400');
        });
        $("#delete").on("click", function () {
            user_del();
        });
        /*页面按钮根据权限实现隐藏*/

    });
    function user_Add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px']
        });
    }
    /*user删除*/
    function user_del() {
        if ($("#redisServList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteServerConfig";
        var rowData = $('#redisServList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            $("#redisServList").dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime('删除成功！');
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    /*user修改*/
    function user_Edit(title, url, w, h) {
        if ($("#redisServList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px']
        });
    }
