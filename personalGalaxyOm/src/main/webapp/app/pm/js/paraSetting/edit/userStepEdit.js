var form;
$(document).ready(function() {
	//pkList for userId
	getPkList({
		url:contextPath+"/pklist/getUserIdList",
		id:"userId",
		async:false
	});
	var rowData;
	if (parent.$("#userStep").find(".selected").length==1){
		rowData = parent.$('#userStep').DataTable().rows(".selected").data()[0];
		$("#userId").val(rowData.userId);
		$("#authApplication").val(rowData.authApplication);
		$("#authEntering").val(rowData.authEntering);
		$("#authCheck").val(rowData.authCheck);
		$("#authPublish").val(rowData.authPublish);
		form = $("#form-edit").Validform({
			tiptype:2,
			callback:function(form){
				edit();
				return false;
			}
		});
	}
	$('.select2').select2();
});

function edit(){
	if($('#authEntering').val() == "Y" || $('#authCheck').val() == "Y" || $("#authPublish").val() == "Y"){
		var aev,acv,apv;
		if($('#authEntering').val() == ""){aev="N";}else{aev=$('#authEntering').val();}
		if($('#authCheck').val() == ""){acv="N";}else{acv=$('#authCheck').val();}
		if($("#authPublish").val() == ""){apv="N";}else{apv=$("#authPublish").val();}
		var url = contextPath+"/authority/update";
		sendPostRequest(url,{
		userId:$("#userId").val(),
		authApplication:$("#authApplication").val(),
		authEntering:aev,
		authCheck:acv,
		authPublish:apv
		}	, callback_edit,"json");

	}else{
    	showMsg("请给用户流程授权！");
            		return;
	}
}

function callback_edit(json){
	if (json.success) {
	    var aev,acv,apv;
		if($('#authEntering').val() == ""){aev="N";}else{aev=$('#authEntering').val();}
		if($('#authCheck').val() == ""){acv="N";}else{acv=$('#authCheck').val();}
		if($("#authPublish").val() == ""){apv="N";}else{apv=$("#authPublish").val();}
	    var dataTable=parent.$("#userStep").DataTable();
		dataTable.row(".selected").remove().draw(false);
		dataTable.row.add({
			userId:$("#userId").val(),
			authApplication:$("#authApplication").val(),
			authEntering:aev,
			authCheck:acv,
			authPublish:apv
		}).draw(false);
		parent.showMsgDuringTime("编辑成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}
