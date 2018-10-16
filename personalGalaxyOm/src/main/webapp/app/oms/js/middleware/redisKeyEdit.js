var form;
$(document).ready(function() {
	var rowData;
	if (parent.$("#redisKeyList").find(".selected").length==1){
		rowData = parent.$('#redisKeyList').DataTable().rows(".selected").data()[0];
	}
	if (rowData){
		$("#seqKey").val(rowData.seqKey);
		$("#seqValue").val(rowData.seqValue);
        $("#redisUrl").val(rowData.redisUrl);
		$("#masterName").val(rowData.masterName);
		form = $("#form-redisKey-edit").Validform({
			tiptype:2,
			callback:function(form){
				redisKyeEdit();
				return false;
			}
		});
	}
});

function redisKyeEdit(){
	var url = contextPath+"/RedisKsy/saveReqManager";
    var redis = $("#redisUrl").val();
	sendPostRequest(url,{
        redisUrl:$("#redisUrl").val(),
		masterName:$("#masterName").val(),
		seqKey:$("#seqKey").val(),
		seqValue:$("#seqValue").val()
	}, callback_redisKyeEdit,"json");
}

function callback_redisKyeEdit(json){
	if (json.success) {
	    parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
function redisKyeEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}