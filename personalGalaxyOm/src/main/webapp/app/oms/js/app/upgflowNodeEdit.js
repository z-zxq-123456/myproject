	var form;
	var rowData;
	$(document).ready(function () {
		if (parent.$("#table_data").find(".selected").length == 1) {
			rowData = parent.$('#table_data').DataTable().rows(".selected").data()[0];
		}
		if (rowData) {
			$("#upgflowNodeName").val(rowData.upgflowNodeName);
			$("#upgflowNodeSeq").val(rowData.upgflowNodeSeq);
			$("#upgflowNodeUrl").val(rowData.upgflowNodeUrl);
			form = $("#form-data-edit").Validform({
				tiptype: 2,
				callback: function (form) {
					dataEdit();
					return false;
				}
			});
		}
	});

	function dataEdit() {
		var url = contextPath + "/updateEcmUpgflowNode";
		sendPostRequest(url, {
			upgflowNodeId: rowData.upgflowNodeId,
			upgflowNodeName: $("#upgflowNodeName").val(),
			upgflowNodeSeq: $("#upgflowNodeSeq").val(),
			upgflowNodeUrl: $("#upgflowNodeUrl").val()
		}, callback_dataEdit, "json");
	}

	function callback_dataEdit(json) {
		if (json.success) {
			var dataTable=parent.$("#table_data").DataTable();
			dataTable.row(".selected").remove().draw(false);
			dataTable.row.add({
				upgflowNodeId: rowData.upgflowNodeId,
				upgflowNodeName: $("#upgflowNodeName").val(),
				upgflowNodeSeq: $("#upgflowNodeSeq").val(),
				upgflowNodeUrl: $("#upgflowNodeUrl").val()
			}).draw(false);
			parent.showMsgDuringTime("编辑成功");
		} else if (json.errorMsg) {
			showMsg(json.errorMsg, 'errorMsg');
		}
		//dataEditCancel();
	}
	function dataEditCancel() {
		var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
		parent.layer.close(index);  //关闭窗口
	}