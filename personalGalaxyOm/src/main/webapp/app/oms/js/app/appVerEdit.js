	var form;
	$(document).ready(function () {

		getPkList({
			url: contextPath + "/findParaCombox?paraParentId=0022&&isDefault=false",
			id:"appVerType",
			async: false
		});
		var rowData;
		if (parent.$("#appVerList").find(".selected").length == 1) {
			rowData = parent.$('#appVerList').DataTable().rows(".selected").data()[0];
		}
		if (rowData) {
			$("#appVerId").val(rowData.appVerId);
			$("#appVerPath").val(rowData.appVerPath);
			$("#appVerDate").val(rowData.appVerDate);
			$("#appVerDesc").val(rowData.appVerDesc);
			$("#appVerType").val(rowData.appVerType);
			form = $("#form-user-edit").Validform({
				tiptype: 2,
				callback: function (form) {
					dataEdit();
					return false;
				}
			});
		}
		$(".select2").select2();
	});

	function dataEdit() {
		var url = contextPath + "/updateAppVer";
		sendPostRequest(url, {
			appVerDesc: $("#appVerDesc").val(),
			appVerType: $("#appVerType").val(),
			appVerId: $("#appVerId").val()
		}, callback_dataEdit, "json");
	}

	function callback_dataEdit(json) {
		if (json.success) {
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