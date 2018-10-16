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
        if ($("#table_data").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteEcmUpgflowNode";
        var rowData = $('#table_data').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            $('#table_data').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime('删除成功！');
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    /*user修改*/
    function user_Edit(title, url, w, h) {
        if ($("#table_data").find(".selected").length != 1) {
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
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#table_data"));


        opt.ajax = {
            "url": contextPath + "/findEcmUpgflowNode",
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "upgflowNodeId",
                "defaultContent": ""
            },
            {
                "data": "upgflowNodeName",
                "defaultContent": ""
            },
            {
                "data": "upgflowNodeSeq",
                "defaultContent": ""
            },
            {
                "data": "upgflowNodeUrl",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#table_data"), opt);
        //界面美化tables
        $("#table_data").beautyUi({
            tableId: "table_data"
        });
        $("#add").on("click", function () {
            user_Add('节点信息添加', 'upgflowNodeAdd.jsp', '600', '400');
        });
        $("#edit").on("click", function () {
            user_Edit('节点信息修改', 'upgflowNodeEdit.jsp', '600', '400');
        });
        $("#delete").on("click", function () {
            user_del();
        });
        /*页面按钮根据权限实现隐藏*/

    });

