var rowData;
$(document).ready(function() {
	getPkList({
		url:contextPath+"/attrType/getForPkList",
		id:"attrClass",
		async:false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
  getPkList({  url: contextPath + "/pklist/getBmodule",
                  id: "busiCategory",
                  async: false
                      });
    if (parent.$("#mbAttrType").find(".selected").length===1){
        rowData = parent.$('#mbAttrType').DataTable().rows(".selected").data()[0];
        $("#attrKey").val(rowData.ATTR_KEY).attr("disabled",true);
        $("#attrClass").val(rowData.ATTR_CLASS);
        $("#attrDesc").val(rowData.ATTR_DESC);
        $("#status").val(rowData.STATUS);
        $("#valueMethod").val(rowData.VALUE_METHOD);
        $("#attrType").val(rowData.ATTR_TYPE);
        $("#company").val(rowData.COMPANY);
        if(rowData.BUSI_CATEGORY!==undefined){
        $("#busiCategory").val(rowData.BUSI_CATEGORY.split(","));
        }
        $("#setValueFlag").val(rowData.SET_VALUE_FLAG);
        $("#useMethod").val(rowData.USE_METHOD);
    }
    $("#mbAttrTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            mbAttrTypeMod();
            return false;
        }
    });
    $(".select2").select2();
});
function mbAttrTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_ATTR_TYPE";
    keyFieldsJson.ATTR_KEY=$("#attrKey").val();
    generalFieldsJson.ATTR_CLASS=$("#attrClass").val();
    generalFieldsJson.ATTR_DESC=$("#attrDesc").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.VALUE_METHOD=$("#valueMethod").val();
    generalFieldsJson.ATTR_TYPE=$("#attrType").val();
    generalFieldsJson.COMPANY=$("#company").val();
    if($("#busiCategory").val()!==null){
    generalFieldsJson.BUSI_CATEGORY=$("#busiCategory").val().toString();
    }
    generalFieldsJson.SET_VALUE_FLAG=$("#setValueFlag").val();
    generalFieldsJson.USE_METHOD=$("#useMethod").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbAttrTypeMod,"json");
}
function callback_mbAttrTypeMod(json){
    if (json.success) {
       if (parent.$("#mbAttrType").find(".selected").length===1){
        rowData = parent.$('#mbAttrType').DataTable().rows(".selected").data()[0];
        var columnStatus = rowData.COLUMN_STATUS;
        var dataTable=parent.$("#mbAttrType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ATTR_KEY:$("#attrKey").val(),
            ATTR_CLASS:$("#attrClass").val(),
            ATTR_DESC:$("#attrDesc").val(),
            STATUS:$("#status").val(),
            VALUE_METHOD:$("#valueMethod").val(),
            ATTR_TYPE:$("#attrType").val(),
            BUSI_CATEGORY:$("#busiCategory").val(),
            SET_VALUE_FLAG:$("#setValueFlag").val(),
            USE_METHOD:$("#useMethod").val(),
            COMPANY:$("#company").val(),
            COLUMN_STATUS:columnStatus
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        }
    }else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function mbAttrTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

