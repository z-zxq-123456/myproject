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
    function index_Add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            title: title,
            area: [w + 'px', h + 'px']
        });
    }
   /*指标配置参数删除*/
    function index_del() {
        if ($("#indexList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteAppmoniIndex";
        var rowData = $('#indexList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            $('#indexList').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime("删除成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    /*指标配置参数修改*/
    function index_Edit(title, url, w, h) {
        if ($("#indexList").find(".selected").length != 1) {
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
        var opt = getDataTableOpt($("#indexList"));
        opt.ajax = {
            "url": contextPath + "/findAppmoniIndex",
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "appIndexName",
                "defaultContent": ""
            },
            {
                "data": "appIndexNo",
                "defaultContent": ""
            },
            {
                "data": "appIndexIsviewName",
                "defaultContent": ""
            },
            {
                "data": "appIndexDesc",
                "defaultContent": ""
            },
            {
                "data": "appIndexFac",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#indexList"), opt);
        //界面美化tables
        $("#indexList").beautyUi({
            tableId: "indexList"
        });
        $("#add").on("click", function () {
            index_Add('应用监控指标新增', 'indexAdd.jsp', '550', '500');
        });
        $("#edit").on("click", function () {
            index_Edit('应用监控指标修改', 'indexEdit.jsp', '550', '500');
        });
        $("#delete").on("click", function () {
            index_del();
        });
        /*页面按钮根据权限实现隐藏*/

        $(".select2").select2();
    });
