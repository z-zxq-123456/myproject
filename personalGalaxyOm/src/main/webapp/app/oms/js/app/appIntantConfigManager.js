    var layer_add_index, layer_edit_index;
    $(document).ready(function () {
        getPkList({
            url: contextPath + "/findAppCombox",
            id: "queryAppId",
            async: false
        });
        getPkList({
            url: contextPath + "/findSerCombox",
            id: "serName",
            async: false
        });
        // 获取默认opt配置
        var opt = getDataTableOpt($("#dataList"));


        opt.ajax = {
            "url": contextPath + "/findEcmAppIntantConfig",
            "type": "POST"
        };
        opt.columns = [
            {"data": "appIntantId"},
            {"data": "appName"},
            {"data": "serName"},
            {"data": "serIp"},
            {"data": "appIntantName"},
            {"data": "appIntantDesc"}
        ];
        //渲染tables
        setDataTableOpt($("#dataList"), opt);
        dataTableUi("dataList");
        $('#add').click(function () {
            data_add('添加', 'appIntantConfigAdd.jsp', '600', '400');
        });
        $("#edit").on("click", function () {
            data_edit('修改', 'appIntantConfigEdit.jsp', '600', '400')
        });
        $("#delete").on("click", function () {
            data_del()
        });
        $('.select2 ').select2();
    });
    function data_add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function () {
                selectdataList();
            }
        });
    }
    function data_edit(title, url, w, h) {
        if ($("#dataList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        //layer_show(title,url,w,h);
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function () {
                selectdataList();
            }
        });
    }
    function selectdataList() {
        var dataTab = $("#dataList").dataTable();
        var api = dataTab.api();
        api.ajax.reload();
    }

    function data_del() {
        if ($("#dataList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }
    function dataDel() {
        var url = contextPath + "/deleteEcmAppIntantConfig";
        var rowData = $('#dataList').DataTable().rows(".selected").data()[0];  //获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }
    function callback_dataDel(json) {
        if (json.success) {
            $('#dataList').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime("删除成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    function showMsgDuringTime(msg)
    {
        showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    }
    function searchRec() {
        var appId = $("#queryAppId").val();
        var serId = $("#serName").val();
        var dataList = $("#dataList").dataTable();
        var api = dataList.api();
        api.ajax.url(contextPath + "/findEcmAppIntantConfig?appId=" + appId + "&&serId=" + serId).load();
    }