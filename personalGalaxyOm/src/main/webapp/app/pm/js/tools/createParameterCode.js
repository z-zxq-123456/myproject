
function createCode() {
    var url = contextPath+"/createCode/createParameter";
	var indexWaiting;
	$.ajax({
        url:url,
        type:"post",
        dataType:"json",
        beforeSend:function(){
            indexWaiting = layer.load(4, {
                shade: [0.4,'#777777'] //0.5透明度的白色背景
            });
        },
        data:{
			jspPackage:$("#jspPackage").val(),
			table:$("#table").val().join(",")
			},
        success:function(json){
        	createCode_callBack(json);
        },
        complete:function(){
            layer.close(indexWaiting);
        }
	});
}

function createCode_callBack(json){
	if (json.retStatus == 'F'){
		showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
		layer.msg("生成代码成功！请刷新页面！");
		setTimeout(function(){
			layer.closeAll('dialog');
		}, 2000);
	}
}

var form;
$(function(){
	// 初始化表单控件
	form = $("#createParameter").Validform({
		tiptype:2,
		callback:function(from){
            createCode();
            return false;
		}
	});

    getPkList({
		url:contextPath+"/pklist/getTableNameByNamespace",
		id:"table",
		async:false
	});    	$(".select2").select2();
	/*页面按钮根据权限实现隐藏*/

});
