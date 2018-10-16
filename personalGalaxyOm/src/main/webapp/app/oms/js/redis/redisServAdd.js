	var form;
	$(document).ready(function() {
		form = $("#form-redisServ-add").Validform({
			tiptype:2,
			callback:function(form){
				dataAdd();
				return false;
			}
		});
	});

	function dataAdd(){
		var url = contextPath+"/saveServerConfig";
		sendPostRequest(url,{
			serverId:0,
			serverIp:$("#serverIp").val(),
			serverPort:$("#serverPort").val()
		}	, callback_dataAdd,"json");
	}

	function callback_dataAdd(json){
		if (json.success) {
			var dataTable=parent.$("#redisServList").DataTable();
			dataTable.row.add({
				serverId:json.id,
				serverIp:$("#serverIp").val(),
				serverPort:$("#serverPort").val()
			}).draw(false);
			parent.showMsgDuringTime("添加成功");
		} else if (json.errorMsg) {
			showMsg(json.errorMsg, 'errorMsg');
		}
		//form.resetForm();  //JS表单清空
	}
	function appInfoAddCancel(){
		var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
		parent.layer.close(index);  //关闭窗口
	}