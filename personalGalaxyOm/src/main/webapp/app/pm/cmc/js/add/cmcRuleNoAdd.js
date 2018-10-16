$(document).ready(function() {
    $("#cmcRuleNoAdd").Validform({
        tiptype:2,
        callback:function(){
            cmcRuleNoAdd();
            return false;
        }
    });
});

function cmcRuleNoAdd(){

    var generalFieldsJson = JSON.stringify({
        ruleCode:$("#ruleCode").val(),
        ruleDesc:$("#ruleDesc").val(),
        ruleNo:$("#ruleNo").val(),
        ruleEx:$("#ruleEx").val()
    });

    var url = contextPath+"/cmcRuleNo/add";

    $.ajax({
        type: "post",
        url: url,
        data: generalFieldsJson,
        dataType: "json",
        cache: false,
        contentType: "application/json",
        beforeSend: function () {
            index1 = layer.load(4, {
                shade: [0.4, '#777777'] //0.5透明度的白色背景
            });
        },
        success: callback_cmcRuleNoAdd,
        complete: function () {
            layer.close(index1);
        }
    });

}

function callback_cmcRuleNoAdd(json){
	if (json.success) {
        var dataTable=parent.$('#cmcRuleNo').DataTable();
        dataTable.row.add({
            ruleCode:$("#ruleCode").val(),
            ruleDesc:$("#ruleDesc").val(),
            ruleNo:$("#ruleNo").val(),
            ruleEx:$("#ruleEx").val()
        }).draw(false);
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}