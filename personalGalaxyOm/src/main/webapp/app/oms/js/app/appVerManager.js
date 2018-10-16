    var layer_add_index, layer_edit_index;
    function showMsgDuringTime(msg) {
        layer.msg(msg);
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 1000);
        if (msg == "添加成功") {
            layer.close(layer_add_index);
        }
        if (msg == "编辑成功") {
            layer.close(layer_edit_index);
        }
    }

    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*data增加*/
    function data_Add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px'],
            end: function () {
                selecttable_data();
            }
        });
    }

    /*data删除*/
    function data_del() {
        if ($("#appVerList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteAppVer";
        var rowData = $('#appVerList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                 //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            showMsgDuringTime("删除成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        selecttable_data();
    }

    /*data修改*/
    function data_Edit(title, url, w, h) {
        if ($("#appVerList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px'],
            end: function () {
                selecttable_data();
            }
        });
    }


    //异步获取规则查询信息
    function selecttable_data() {
        var dataTab = $("#appVerList").dataTable();
        var api = dataTab.api();
        api.ajax.reload();
    }

    function download() {
        if ($("#appVerList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        var sub_url = contextPath + '/downloadAppVer';
        var row = $('#appVerList').DataTable().rows(".selected").data()[0];
        if (vaildTabData("appVerList")) {
            window.location.href = sub_url + "?path=" + row.appVerPath;
        }
    }