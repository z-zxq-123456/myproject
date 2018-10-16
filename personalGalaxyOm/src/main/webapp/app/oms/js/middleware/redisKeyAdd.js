var form;
$(document).ready(function() {
    $("#redisUrl").val(parent.$("#redisUrl").val());
    $("#masterName").val(parent.$("#masterName").val());
	form = $("#form-redisKey-add").Validform({
		tiptype:2,
		callback:function(form){
			redisKyeAdd();
			return false;
		}
	});
});

function redisKyeAdd(){
	var url = contextPath+"/RedisKsy/saveReqManager";
	sendPostRequest(url, {
        redisUrl:$("#redisUrl").val(),
        masterName:$("#masterName").val(),
        seqKey:$("#seqKey").val(),
        seqValue:$("#seqValue").val()
    }, callback_redisKyeAdd, "json");
}

function callback_redisKyeAdd(json){
	if (json.success) {
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
	//form.resetForm();  //JS表单清空

}
function redisKyeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}