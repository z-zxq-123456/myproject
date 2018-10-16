var form;
$(document).ready(function() {
    getPkList({
       url:contextPath+"/pklist/getSystem",
       id:"systemId",
       async:false
    });
    getPkList({
        url:contextPath+"/pklist/getModuleBySystem",
        params:{systemId:null},
        id:"moduleId",
        async:false
    });

    $("#systemId").change(function(){
		if($("#systemId").val()== ""){
			$("#moduleId").empty();
			getPkList({
				url:contextPath+"/pklist/getModuleBySystem",
                params:{systemId:null},
				id:"moduleId",
				async:false
			});

		}else{
			$("#moduleId").empty();
			getPkList({
				url:contextPath+"/pklist/getModuleBySystem",
				params:{systemId:$("#systemId").val()},
				id:"moduleId",
				async:false
			});

		}
    });
    $("#isTbname").change(function(){
            var isTbname = $("#isTbname").val();
            if(isTbname=="Y"){
               $("#VFJSP").hide();
               $("#jspViewurl").val("");
            }
            if(isTbname=="N"){
            	$("#VFJSP").show();
            }
        });
	form = $("#form-namespace-add").Validform({
		tiptype:2,
		callback:function(form){
			namespaceAdd();
			return false;
		}
	});
	$("#VFJSP").hide();
	$(".select2").select2();

});

function namespaceAdd(){
	var url = contextPath+"/paraNamespace/saveNamespace";
	sendPostRequest(url,{
		transactionDesc:$("#transactionDesc").val(),
        systemId:$("#systemId").val(),
		moduleId:$("#moduleId").val(),
		transactionId:$("#transactionId").val(),
		jspUrl:$("#jspUrl").val(),
		namespaceName:$("#namespaceName").val(),
		namespaceDesc:$("#namespaceDesc").val(),
		bandEnteringcheck:$("#bandEnteringcheck").val(),
		deleteAuth:$("#deleteAuth").val(),
		isTbname:$("#isTbname").val(),
		jspViewurl:$("#jspViewurl").val()
	}, callback_namespaceAdd,"json");
}

function callback_namespaceAdd(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraNamespaceList").DataTable();
		dataTable.row.add({
			transactionDesc:$("#transactionDesc").val(),
			systemId:$("#systemId").val(),
			moduleId:$("#moduleId").val(),
			transactionId:$("#transactionId").val(),
			jspUrl:$("#jspUrl").val(),
			namespaceName:$("#namespaceName").val(),
			namespaceDesc:$("#namespaceDesc").val(),
			bandEnteringcheck:$("#bandEnteringcheck").val(),
			deleteAuth:$("#deleteAuth").val(),
			isTbname:$("#isTbname").val(),
			jspViewurl:$("#jspViewurl").val()
		}).draw(false);
		parent.showMsgDuringTime("添加成功");
	}
	//form.resetForm();  //JS表单清空
}
function namespaceAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
