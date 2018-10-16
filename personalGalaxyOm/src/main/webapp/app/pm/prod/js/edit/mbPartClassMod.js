
var rowData;
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
    getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_part_class&tableCol=PART_CLASS,PART_CLASS_DESC",
                                      id: "parentPartClass",
                                      async: false
                     });
      	$('#partClassLevel').change(function(){
      	    $("#parentPartClass").attr("disabled",false);
      	    $("#parentPartClass").select2();
      		var value=$('#partClassLevel').val();
      		 getPkList({  url: contextPath + "/pklist/getparentPartClass?partClassLevel="+value,
                               id: "parentPartClass",
                               async: false
                                  });
      	});
    if (parent.$("#mbPartClass").find(".selected").length===1){
        rowData = parent.$('#mbPartClass').DataTable().rows(".selected").data()[0];
        $("#partClass").val(rowData.PART_CLASS).attr("disabled",true);
        $("#partClassDesc").val(rowData.PART_CLASS_DESC);
        $("#parentPartClass").val(rowData.PARENT_PART_CLASS);
        $("#partClassLevel").val(rowData.PART_CLASS_LEVEL);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbPartClassMod").Validform({
        tiptype:2,
        callback:function(form){
            mbPartClassMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbPartClassMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PART_CLASS";
    keyFieldsJson.PART_CLASS=$("#partClass").val();
    generalFieldsJson.PART_CLASS_DESC=$("#partClassDesc").val();
    generalFieldsJson.PARENT_PART_CLASS=$("#parentPartClass").val();
    generalFieldsJson.PART_CLASS_LEVEL=$("#partClassLevel").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbPartClassMod,"json");
}

function callback_mbPartClassMod(json){
    if (json.success) {
       if (parent.$("#mbPartClass").find(".selected").length===1){
        rowData = parent.$('#mbPartClass').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbPartClass").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PART_CLASS:$("#partClass").val(),
            PART_CLASS_DESC:$("#partClassDesc").val(),
            PARENT_PART_CLASS:$("#parentPartClass").val(),
            PART_CLASS_LEVEL:$("#partClassLevel").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbPartClassModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

