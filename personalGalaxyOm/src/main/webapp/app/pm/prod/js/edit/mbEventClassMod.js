
var rowData;
$(document).ready(function() {
        rowData = parent.$('#mbEventClass').DataTable().rows(".selected").data()[0];
        var value=rowData.EVENT_CLASS_LEVEL
    getPkList({
                       url: contextPath + "/pklist/getparentEventClass?eventClassLevel="+value,
                                      id: "parentEventClass",
                                      async: false
                     });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
  	$('#eventClassLevel').change(function(){
 		$("#parentEventClass").attr("disabled",false);
 		$("#parentEventClass").select2();
  		var value=$('#eventClassLevel').val();
  		 getPkList({  url: contextPath + "/pklist/getparentEventClass?eventClassLevel="+value,
                           id: "parentEventClass",
                           async: false
                              });
  	});
    if (parent.$("#mbEventClass").find(".selected").length===1){

        $("#eventClass").val(rowData.EVENT_CLASS).attr("disabled",true);
        $("#eventClassDesc").val(rowData.EVENT_CLASS_DESC);
        $("#eventClassLevel").val(rowData.EVENT_CLASS_LEVEL);
        if(rowData.PARENT_EVENT_CLASS!=null){
        $("#parentEventClass").val(rowData.PARENT_EVENT_CLASS.split(","));
        }
        $("#company").val(rowData.COMPANY);
    }

    $("#mbEventClassMod").Validform({
        tiptype:2,
        callback:function(form){
            mbEventClassMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbEventClassMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_EVENT_CLASS";
    keyFieldsJson.EVENT_CLASS=$("#eventClass").val();
    generalFieldsJson.EVENT_CLASS_DESC=$("#eventClassDesc").val();
    generalFieldsJson.EVENT_CLASS_LEVEL=$("#eventClassLevel").val();
    generalFieldsJson.COMPANY=$("#company").val();
    if($("#parentEventClass").val()!=null){
    generalFieldsJson.PARENT_EVENT_CLASS=$("#parentEventClass").val().toString();
    }
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbEventClassMod,"json");
}

function callback_mbEventClassMod(json){
    if (json.success) {
       if (parent.$("#mbEventClass").find(".selected").length===1){
        rowData = parent.$('#mbEventClass').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbEventClass").DataTable();

        dataTable.row(".selected").remove().draw(false);

        dataTable.row.add({
            EVENT_CLASS:$("#eventClass").val(),
            EVENT_CLASS_DESC:$("#eventClassDesc").val(),
            EVENT_CLASS_LEVEL:$("#eventClassLevel").val(),
            PARENT_EVENT_CLASS:$("#parentEventClass").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbEventClassModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

