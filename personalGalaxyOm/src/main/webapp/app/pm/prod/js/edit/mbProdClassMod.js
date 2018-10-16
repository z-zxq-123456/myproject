
var rowData;
$(document).ready(function() {
    getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_prod_class&tableCol=PROD_CLASS,PROD_CLASS_DESC",
                                      id: "parentProdClass",
                                      async: false
                     });
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
 	$('#prodClassLevel').change(function(){
 	    $("#parentPartClass").attr("disabled",false);
 	    $("#parentPartClass").select2();
 		var value=$('#prodClassLevel').val();
 		 getPkList({  url: contextPath + "/pklist/getparentProdClass?prodClassLevel="+value,
                          id: "parentProdClass",
                          async: false
                             });
 	});
    if (parent.$("#mbProdClass").find(".selected").length===1){
        rowData = parent.$('#mbProdClass').DataTable().rows(".selected").data()[0];
        $("#prodClass").val(rowData.PROD_CLASS).attr("disabled",true);
        $("#prodClassDesc").val(rowData.PROD_CLASS_DESC);
        $("#parentProdClass").val(rowData.PARENT_PROD_CLASS);
        $("#prodClassLevel").val(rowData.PROD_CLASS_LEVEL);
        $("#company").val(rowData.COMPANY);
    }

    $("#mbProdClassMod").Validform({
        tiptype:2,
        callback:function(form){
            mbProdClassMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdClassMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_CLASS";
    keyFieldsJson.PROD_CLASS=$("#prodClass").val();
    generalFieldsJson.PROD_CLASS_DESC=$("#prodClassDesc").val();
    generalFieldsJson.PARENT_PROD_CLASS=$("#parentProdClass").val();
    generalFieldsJson.PROD_CLASS_LEVEL=$("#prodClassLevel").val();
    generalFieldsJson.COMPANY=$("#company").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdClassMod,"json");
}

function callback_mbProdClassMod(json){
    if (json.success) {
       if (parent.$("#mbProdClass").find(".selected").length===1){
        rowData = parent.$('#mbProdClass').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbProdClass").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            PROD_CLASS:$("#prodClass").val(),
            PROD_CLASS_DESC:$("#prodClassDesc").val(),
            PARENT_PROD_CLASS:$("#parentProdClass").val(),
            PROD_CLASS_LEVEL:$("#prodClassLevel").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdClassModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

