  var isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
  var layer_add_index ;
  var sub_url;
  var jsonObj ;
	$(document).ready(function () {
		$(".select2").select2();
		// 获取默认opt配置
		var opt = getDataTableOpt($("#lateServRuleList"));
		opt.stateSave = true;
		opt.processing = true;
		opt.autoWidth = false;
		opt.ajax = {
			"url": contextPath + "/findLateRule?appId=" + appId,
			"type": "POST"
		};
		opt.columns = [
			{
				"data": "appSerName",
				"defaultContent": ""
			},
			{
				"data": "appSerClsnm",
				"defaultContent": ""
			},
			{
				"data": "routerArgsPos",
				"defaultContent": ""
			},
			{
				"data": "routerColCn",
				"defaultContent": ""
			},
			{
				"data": "routerCondVal",
				"defaultContent": ""
			},
			{
				"data": "routerCondName",
				"defaultContent": ""
			},
			{
				"data": "servRuleSelf",
				"defaultContent": ""
			},

		];
		//渲染tables
		drawDataTable($("#lateServRuleList"), opt);
		//界面美化tables
		$("#lateServRuleList").beautyUi({
			tableId: "lateServRuleList",
			buttonId: ["add", "delete"],
			buttonName: ["新增", "删除"],
		});
		 $("#add").on("click", function () {
                 layer_add_index = layer.open({
                        type: 2,
                        content: "appLateServRuleAdd.jsp?id="+appId,
                        title: "服务路由规则增加",
                        area: ['100%', '400px'],
                        end: function () {
                        }
                    });
                });
                $("#delete").on("click", function () {
                    var rowSelect = $('#lateServRuleList').DataTable().rows(".selected").data();
                    if (rowSelect.length != 1) {
                        showMsg('请选择一行记录！', 'warning');
                        return;
                    }
                    layer.confirm('确认要删除吗？', function () {
                        var url = contextPath + "/deleteEcmAppValRule";
                        var rowData = $('#lateServRuleList').DataTable().rows(".selected").data()[0];  //已经获取数据
                        sendPostRequest(
                                url,
                               {appValruleId:rowData.appValruleId,appId:appId,servRuleId:rowData.servRuleId,appValruleType:rowData.appValruleType},
                                function (json) {
                                    if (json.success) {
                                      //  showMsgDuringTime("删除成功");
                                        location.replace(location.href);
                                    } else if (json.errorMsg) {
                                        showMsg(json.errorMsg, 'errorMsg');
                                    }
                                },
                                "json"
                        );

                    });
                });

                $('#lateServRuleList tbody').on('click', 'tr', function (e) {
                    if ($(this).hasClass('selected')) {
                        $(this).removeClass('selected');
                    } else {
                        $('#lateServRuleList').find("tr").removeClass('selected');
                        $(this).addClass('selected');
                    }
                });

	});
   //第六步验证失败回退
	function lateBackRec() {
            isSuccess = "001";
            sub_url = contextPath + '/lateBackEcmAppInstant';
            process_jsonObj = "&appId=" + appId;
            index_progress = layer.open({
                type: 2,
                content: "appLateBackRecProgress.jsp",
                area: ['400px', '200px'],
                title: '停止先启动所有实例进度'
            });
        }
        function getIsFinish() {
            return isFinish;
        }
 function showMsgDuringTime(msg) {
        layer.msg(msg);
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 1000);
        if (msg == "删除成功") {
              layer.close(layer_delete_index);
        }
        if (msg == "添加成功") {
              layer.close(layer_add_index);
              location.replace(location.href);
         }
        layer.close(index_progress);
}
function getSubUrl() {
    return sub_url;
}
function getJsonObj() {
    return process_jsonObj;
}
function setError() {
    isSuccess = "003";
}
function setSuccess() {
    isSuccess = "002";
}
