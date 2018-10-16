var form;
$(document).ready(function() {
	getPkList({
			url:contextPath+"/pklist/getUserIdList",
			id:"userId",
			async:false
	});
	form = $("#form-add").Validform({
		tiptype:2,
		callback:function(form){
			add();
			return false;
		}
	});
	$('.select2').select2();

});

function add(){
	if($('#authEntering').val() == "Y" || $('#authCheck').val() == "Y" || $("#authPublish").val() == "Y"){
		var aev,acv,apv;
		if($('#authEntering').val() == ""){aev="N";}else{aev=$('#authEntering').val();}
		if($('#authCheck').val() == ""){acv="N";}else{acv=$('#authCheck').val();}
		if($("#authPublish").val() == ""){apv="N";}else{apv=$("#authPublish").val();}
		var url = contextPath+"/authority/add";
		sendPostRequest(url,{
		userId:$("#userId").val(),
		authApplication:$("#authApplication").val(),
		authEntering:aev,
		authCheck:acv,
		authPublish:apv
		}	, callback_add,"json");

	}else{
		showMsg("请给用户流程授权！");
		return;
    }
}

function callback_add(json){
	if (json.success) {
	    var aev,acv,apv;
		if($('#authEntering').val() == ""){aev="N";}else{aev=$('#authEntering').val();}
		if($('#authCheck').val() == ""){acv="N";}else{acv=$('#authCheck').val();}
		if($("#authPublish").val() == ""){apv="N";}else{apv=$("#authPublish").val();}
	    var dataTable=parent.$("#userStep").DataTable();
		dataTable.row.add({
			userId:$("#userId").val(),
			authApplication:$("#authApplication").val(),
			authEntering:aev,
			authCheck:acv,
			authPublish:apv
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}

}
