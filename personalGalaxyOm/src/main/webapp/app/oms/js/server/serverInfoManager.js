    var layer_add_index, layer_edit_index;
    function showMsgDuringTime(msg)
    {
        showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    }
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#EcmServerList"));


        opt.ajax = {
            "url": contextPath + "/findEcmServerInfo",
            "type": "POST"
        };
        opt.columns = [
            {"data": "serId"},
            {"data": "serName"},
            {"data": "serIp"},
            {"data": "serUser"},
            {"data": "serOsName"}
        ];
        //渲染tables
        setDataTableOpt($("#EcmServerList"), opt);
        dataTableUi("EcmServerList");
        $('#add').click(function () {
            serverInfo_add('添加', 'serverInfoAdd.jsp', '650', '400');
        });
        $("#edit").on("click", function () {
            serverInfo_edit('修改', 'serverInfoEdit.jsp', '650', '400')
        });
        $("#delete").on("click", function () {
            serverInfo_del()
        });
    });
    function serverInfo_add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px']
        });
    }
    function serverInfo_edit(title, url, w, h) {
        if ($("#EcmServerList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        //layer_show(title,url,w,h);
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px']
        });
    }

    function serverInfo_del() {
        if ($("#EcmServerList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            serverInfoDel();
        });
    }

    function serverInfoDel() {
        var url = contextPath + "/deleteEcmServerInfo";
        var rowData = $('#EcmServerList').DataTable().rows(".selected").data()[0];  //获取数据
        sendPostRequest(url, rowData, callback_serverInfoDel, "json");                //将获取数据发送给后台处理
    }

    function callback_serverInfoDel(json) {
        if (json.success) {
            $('#EcmServerList').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime("删除成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    function referch(){
       location.replace(location.href);
    }