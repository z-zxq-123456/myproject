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
        if ($("#parameterList").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }

        layer.confirm('确认要删除吗？', function () {
            dataDel();
        });
    }

    function dataDel() {
        var url = contextPath + "/deleteRule";
        var rowData = $('#parameterList').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url, rowData, callback_dataDel, "json");                //将获取数据发送给后台处理
    }

    function callback_dataDel(json) {
        if (json.success) {
            $('#parameterList').dataTable().api().row(".selected").remove().draw(false);
            showMsgDuringTime("删除成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }
    /*user修改*/
    function user_Edit(title, url, w, h) {
        if ($("#parameterList").find(".selected").length != 1) {
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
        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0130&&isDefault=true",
            id: "ruleRank"
        });
        // 获取默认opt配置
        var opt = getDataTableOpt($("#parameterList"));


        opt.ajax = {
                      "url": contextPath + "/findRule",
                      "type": "POST"
                  };
        opt.columns = [
            {
                "data": "ruleName",
                "defaultContent": ""
            },
            {
                "data": "ruleRankName",
                "defaultContent": ""
            },
            {
                "data": "ruleDesc",
                "defaultContent": ""
            }
        ];
        //渲染tables
        setDataTableOpt($("#parameterList"), opt);
        //界面美化tables
        $("#parameterList").beautyUi({
            tableId: "parameterList"
        });
        $("#add").on("click", function () {
            user_Add('参数信息添加', 'ruleAdd.jsp', '600', '500');
        });
        $("#edit").on("click", function () {
            user_Edit('参数信息修改', 'ruleEdit.jsp', '600', '500');
        });
        $("#delete").on("click", function () {
            user_del();
        });
        /*页面按钮根据权限实现隐藏*/

        $(".select2").select2();
    });

    function searchRec() {
        var url = "/findRule";

        var ruleName = $("#queryRuleName").val();
        var ruleRankName = $("#ruleRank").val();

        if (ruleName != "" && ruleName != undefined && ruleName != null) {
            url = url + "?ruleName=" + ruleName;

            if (ruleRankName != "" && ruleRankName != undefined && ruleRankName != null) {
                url = url + "&&ruleRank=" + ruleRankName;
            }
        }else{
            if (ruleRankName != "" && ruleRankName != undefined && ruleRankName != null) {
                url = url + "?ruleRank=" + ruleRankName;
            }else {
                showMsg("请输入查询条件！","info");
                return;
            }
        }

        var table_data = $("#parameterList").dataTable();
        var api = table_data.api();
        api.ajax.url(contextPath + url).load();
    }