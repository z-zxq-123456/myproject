
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
                id: "quoteCcy",
                async: false
            });
    if (parent.$("#irlExchangeType").find(".selected").length===1){
        rowData = parent.$('#irlExchangeType').DataTable().rows(".selected").data()[0];
            $("#rateType").val(rowData.RATE_TYPE).attr("disabled",true);
            $("#quoteCcy").val(rowData.QUOTE_CCY);
            $("#rateTypeDesc").val(rowData.RATE_TYPE_DESC);
            $("#floatType").val(rowData.FLOAT_TYPE);
            $("#company").val(rowData.COMPANY);
            $("#hbdFlag").val(rowData.HBD_FLAG);
    }

    $("#irlExchangeTypeMod").Validform({
        tiptype:2,
        callback:function(form){
            irlExchangeTypeMod();
            return false;
        }
    });

    $(".select2").select2();
});

 $("#hbdFlag").change(function(){

    var hbdFlag = $("#hbdFlag").val();
    if(hbdFlag==="Y"){

		   $("#quoteCcy").val("").attr("disabled",true);
		   }
		if(hbdFlag==="N"){
           	 $("#quoteCcy").val("").attr("disabled",false);
          }
  });
function irlExchangeTypeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_EXCHANGE_TYPE";
        keyFieldsJson.RATE_TYPE=$("#rateType").val();
        generalFieldsJson.QUOTE_CCY=$("#quoteCcy").val();
        generalFieldsJson.RATE_TYPE_DESC=$("#rateTypeDesc").val();
        generalFieldsJson.FLOAT_TYPE=$("#floatType").val();

        generalFieldsJson.HBD_FLAG=$("#hbdFlag").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlExchangeTypeMod,"json");
}

function callback_irlExchangeTypeMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlExchangeType").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            RATE_TYPE:$("rateType").val(),
            QUOTE_CCY:$("quoteCcy").val(),
            RATE_TYPE_DESC:$("rateTypeDesc").val(),
            FLOAT_TYPE:$("floatType").val(),
            HBD_FLAG:$("hbdFlag").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlExchangeTypeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

