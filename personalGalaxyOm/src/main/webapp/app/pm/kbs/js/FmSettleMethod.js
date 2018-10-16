var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    if(msg==="添加成功") {
        selectAll_refresh();
    }
}

$(document).ready(function() {
$(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
    	var opt = getDataTableOpt($("#fmSettleMethod"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_SETTLE_METHOD",
					"reqNum": parent.$(".breadcrumb").data("reqNum")
                 }
           	 };
        opt.fnRowCallback=function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                 var value = aData.OPERATE_TYPE;
                 if ( value === "3" )
                 {
                     $('td', nRow).addClass('danger');
                 }
                 else if ( value === "2" )
                 {
                     $('td', nRow).addClass('highlight');
                 }
                 else if ( value === "1" )
                 {
                     $('td', nRow).addClass('newFace');
                 }
             };

        opt.columnDefs=[
                 {
                     "targets": [ 0 ],
                     "visible": false
                 }
                ,{
                      "width":"100px",
                      "targets":1
                }
                ,{
                      "width":"100px",
                      "targets":2
                }
                ,{
                      "width":"100px",
                      "targets":3
                }
                ,{
                      "width":"100px",
                      "targets":4
                }
                ,{
                      "width":"100px",
                      "targets":5
                }
                ,{
                      "width":"100px",
                      "targets":6
                }
                ,{
                      "width":"100px",
                      "targets":7
                }
                ,{
                      "width":"100px",
                      "targets":8
                }
                ,{
                      "width":"100px",
                      "targets":9
                }
                ,{
                      "width":"100px",
                      "targets":10
                }
                ,{
                      "width":"100px",
                      "targets":11
                }
                ,{
                      "width":"100px",
                      "targets":12
                }
                ,{
                      "width":"100px",
                      "targets":13
                }
                 ,{
                     "width":"100px",
                     "targets":14,
                     "render":function( data, type, row ) {
                         return row.IS_CASH === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":15
                }
                 ,{
                     "width":"100px",
                     "targets":16,
                     "render":function( data, type, row ) {
                         return row.DP_SETTLE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":17
                }
                ,{
                      "width":"100px",
                      "targets":18
                }
                ,{
                      "width":"100px",
                      "targets":19
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "SETTLE_METHOD", "defaultContent":""}
                ,{ "data": "PAY_REC", "defaultContent":""}
                ,{ "data": "DEST_CLIENT_TYPE", "defaultContent":""}
                ,{ "data": "MEDIA", "defaultContent":""}
                ,{ "data": "SETTLE_METHOD_DESC", "defaultContent":""}
                ,{ "data": "SETTLE_ACCT_TYPE", "defaultContent":""}
                ,{ "data": "ROUTE", "defaultContent":""}
                ,{ "data": "RELEASE_SECURITY", "defaultContent":""}
                ,{ "data": "VERIFY_SECURITY", "defaultContent":""}
                ,{ "data": "FORMAT", "defaultContent":""}
                ,{ "data": "CONTACT_TYPE", "defaultContent":""}
                ,{ "data": "PRINT_MODE", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "IS_CASH", "defaultContent":""}
                ,{ "data": "DEST_ID", "defaultContent":""}
                ,{ "data": "DP_SETTLE", "defaultContent":""}
                ,{ "data": "DOC_TYPE", "defaultContent":""}
                ,{ "data": "DEST_TYPE", "defaultContent":""}
                ,{ "data": "SENDERS_CONTACT_TYPE", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#fmSettleMethod"),opt);
        $("#fmSettleMethod").beautyUi({
            tableId:"fmSettleMethod",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmSettleMethod tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmSettleMethod').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#fmSettleMethod').on('page.dt', function (e) {
                $('#fmSettleMethod').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#fmSettleMethod").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function fm_settle_method_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_settle_method_mod(title,url,w,h){
    if ($("#fmSettleMethod").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*删除*/
function  fm_settle_method_del(){
    if ($("#fmSettleMethod").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         fmSettleMethodDel();
    });
}

function  fmSettleMethodDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#fmSettleMethod').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="FM_SETTLE_METHOD";
        keyFieldsJson.SETTLE_METHOD=rowData.SETTLE_METHOD;
        keyFieldsJson.PAY_REC=rowData.PAY_REC;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_fmSettleMethodDel,"json");                //将获取数据发送给后台处理
}

function  callback_fmSettleMethodDel(json){
    if (json.success) {
        $("#fmSettleMethod").dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function buttonStatus()
{
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            fm_settle_method_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#queryPrimaryKey").show();
            $("#add").hide();
            $("#edit").hide();
            $("#delete").hide();
            $("#submit").hide();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回"){
        $("#add").on("click",function(){
            fm_settle_method_add('添加','add/fmSettleMethodAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_settle_method_mod('修改','edit/fmSettleMethodMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_settle_method_del();
            });
        }
        $("#submit").on("click",function(){
            fm_settle_method_submit();
        });
        $("#contrast").on("click",function(){
            fm_settle_method_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已录入" ) {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            fm_settle_method_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
	else if(parent.$(".breadcrumb").data("needButton") === "NoEdit" ){
		$("#add").hide();
		$("#edit").hide();
		$("#delete").hide();
		$("#submit").hide();
		$("#contrast").hide();
	}
	else
	{
		$(".breadcrumb").data("needButton","windowShow");
		$("#add").on("click",function(){
			fm_settle_method_add('添加','add/fmSettleMethodAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			fm_settle_method_mod('修改','edit/fmSettleMethodMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			fm_settle_method_del();
		});
		$("#contrast").on("click",function(){
			fm_settle_method_contrast();
		});
		$("#submit").on("click",function(){
			fm_settle_method_submit();
		});
		$("#return").on("click",function(){
			initData_refresh();
			$("#contrast").show();
			$("#refresh").show();
			$("#return").hide();
			$("#add").show();
			$("#edit").show();
			$("#delete").show();
			$("#submit").show();
			$("#queryPrimaryKey").show();
		});
	}
	if(parent.$(".breadcrumb").data("deleteButton") === "N") {
		$("#delete").hide();
	}
}


/*查看差异数据*/
function  fm_settle_method_contrast(){
    var attrTab = $("#fmSettleMethod").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_SETTLE_METHOD" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function fm_settle_method_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"FM_SETTLE_METHOD"
            }, callback_fmSettleMethodSubmit,"json");
    });
}

function  callback_fmSettleMethodSubmit(json){
	if (json.success) {
		if($(".breadcrumb").data("needButton") === "windowShow" )
		{
			showMsgDuringTime("提交成功");
		}
		else
		{
			parent.afterSelectPara("view","提交成功");
		}
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}


function selectAll_refresh(){
  	var prodTab = $("#fmSettleMethod").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmSettleMethod").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_SETTLE_METHOD").load();
}


