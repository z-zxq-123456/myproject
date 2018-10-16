var form;
$(document).ready(function() {
     getPkList({
           url:contextPath+"/pklist/getSystem",
           id:"systemId",
           async:false
        });
    var rowData;
    if (parent.$("#paraNamespaceList").find(".selected").length==1){
        rowData = parent.$('#paraNamespaceList').DataTable().rows(".selected").data()[0];
    }
    if (rowData) {
        $("#systemId").val(rowData.systemId);
    }
    getPkList({
        url:contextPath+"/pklist/getModuleBySystem",
        params:{systemId:rowData.systemId},
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
	if (rowData){
		$("#transactionDesc").val(rowData.transactionDesc);
		$("#moduleId").val(rowData.moduleId);
		$("#transactionId").val(rowData.transactionId);
		$("#jspUrl").val(rowData.jspUrl);
		$("#namespaceName").val(rowData.namespaceName);
		$("#namespaceDesc").val(rowData.namespaceDesc);
        $("#bandEnteringcheck").val(rowData.bandEnteringcheck);
        $("#deleteAuth").val(rowData.deleteAuth);
        $("#jspViewurl").val(rowData.jspViewurl);
        $("#isTbname").val(rowData.isTbname);
		form = $("#form-namespace-edit").Validform({
			tiptype:2,
			callback:function(form){
				namespaceEdit();
				return false;
			}
		});
	}
	$(".select2").select2();
	hided();
	$("#isTbname").change(function(){
        hided();
    });
});

function namespaceEdit(){
	var rowData = parent.$('#paraNamespaceList').DataTable().rows(".selected").data()[0];
	var url = contextPath+"/paraNamespace/updateNamespace";
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
	}, callback_namespaceEdit,"json");
}

function callback_namespaceEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	    var dataTable=parent.$("#paraNamespaceList").DataTable();
        dataTable.row(".selected").remove().draw(false);
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
		parent.showMsgDuringTime("编辑成功");
	}
}
function userEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

function hided(){
    var isTbname = $("#isTbname").val();
    if(isTbname=="Y"){
       $("#VFJSP").hide();
       $("#jspViewurl").val("");
    }
    if(isTbname=="N"){
        $("#VFJSP").show();
    }
}
