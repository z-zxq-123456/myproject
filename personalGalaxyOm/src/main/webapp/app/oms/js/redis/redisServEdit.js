	var form;
	var rowData;
	$(document).ready(function () {
		if (parent.$("#redisServList").find(".selected").length == 1) {
			rowData = parent.$('#redisServList').DataTable().rows(".selected").data()[0];
		}
		if (rowData) {
			$("#serverIp").val(rowData.serverIp);
			$("#serverPort").val(rowData.serverPort);
			form = $("#form-redisServ-edit").Validform({
				tiptype: 2,
				callback: function (form) {
					dataEdit();
					return false;
				}
			});
		}
	});

	function dataEdit() {
		var url = contextPath + "/updateServerConfig";
		sendPostRequest(url, {
			serverIp: $("#serverIp").val(),
			serverPort: $("#serverPort").val(),
            serverId:rowData.serverId
		}, callback_dataEdit, "json");
	}

	function callback_dataEdit(json) {
		if (json.success) {
			var dataTable=parent.$("#redisServList").DataTable();
			dataTable.row(".selected").remove().draw(false);
			dataTable.row().add({
				serverIp: $("#serverIp").val(),
				serverPort: $("#serverPort").val(),
				serverId:rowData.serverId
			}).draw(false);
			parent.showMsgDuringTime("编辑成功");
		} else if (json.errorMsg) {
			showMsg(json.errorMsg, 'errorMsg');
		}
	}