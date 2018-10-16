
var rowData;
$(document).ready(function() {
	 getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	 });
     getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_attr_class&tableCol=ATTR_CLASS,ATTR_CLASS_DESC",
                                      id: "parentAttrClass",
                                      async: false
                     });
       $("#attrClass").blur(function(){
                     var url = contextPath + "/attr/getOne";
                     $.ajax({
                         url: url,
                         data: "attrClass=" + $("#attrClass").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#attrClass").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json"
                     });
         });
		$('#attrClassLevel').change(function(){
		 	    $("#parentAttrClass").attr("disabled",false);
         	    $("#parentAttrClass").select2();
    		var value=$('#attrClassLevel').val();
    		 getPkList({  url: contextPath + "/pklist/getparentAttrClass?attrClassLevel="+value,
                             id: "parentAttrClass",
                             async: false
                                });
    	});
    if (parent.$("#mbAttrClass").find(".selected").length===1){
        rowData = parent.$('#mbAttrClass').DataTable().rows(".selected").data()[0];
        $("#attrClass").val(rowData.ATTR_CLASS).attr("disabled",true);
        $("#attrClassDesc").val(rowData.ATTR_CLASS_DESC);
        $("#attrClassLevel").val(rowData.ATTR_CLASS_LEVEL);
        $("#parentAttrClass").val(rowData.PARENT_ATTR_CLASS);
        $("#company").val(rowData.COMPANY);
        if(rowData.ATTR_CLASS_LEVEL ==="1"){
        	 $("#parentAttrClass").attr("disabled",true);
            }
    }

    $("#mbAttrClassMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAttrClassMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbAttrClassMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ATTR_CLASS";
    keyFieldsJson.ATTR_CLASS=$("#attrClass").val();
    generalFieldsJson.ATTR_CLASS_DESC=$("#attrClassDesc").val();
    generalFieldsJson.ATTR_CLASS_LEVEL=$("#attrClassLevel").val();
    generalFieldsJson.PARENT_ATTR_CLASS=$("#parentAttrClass").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAttrClassMod,"json");
}

function callback_mbAttrClassMod(json){
    if (json.success) {
       if (parent.$("#mbAttrClass").find(".selected").length===1){
        rowData = parent.$('#mbAttrClass').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbAttrClass").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ATTR_CLASS:$("#attrClass").val(),
            ATTR_CLASS_DESC:$("#attrClassDesc").val(),
            ATTR_CLASS_LEVEL:$("#attrClassLevel").val(),
            PARENT_ATTR_CLASS:$("#parentAttrClass").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
       }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbAttrClassModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

