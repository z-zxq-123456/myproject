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
    	var opt = getDataTableOpt($("#tbVoucherDef"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"TB_VOUCHER_DEF",
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
                     "targets":2,
                     "render":function( data, type, row ) {
                         return row.IS_CHEQUE_BOOK === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":3,
                     "render":function( data, type, row ) {
                         return row.PREFIX_REQ === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":4
                }
                 ,{
                     "width":"100px",
                     "targets":5,
                     "render":function( data, type, row ) {
                         return row.IN_CONTRAL === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":6,
                     "render":function( data, type, row ) {
                         return row.HAVE_NUMBER === "Y"?"是":"否";
                     }
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
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.OTHER_BANK_FLAG === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":10,
                     "render":function( data, type, row ) {
                         return row.ALLOW_DISTR === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":11,
                     "render":function( data, type, row ) {
                         return row.BRANCH_RESTRAINT === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":12
                }
                 ,{
                     "width":"100px",
                     "targets":13,
                     "render":function( data, type, row ) {
                         return row.USE_BY_ORDER === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":14
                }
                ,{
                      "width":"100px",
                      "targets":15
                }
                ,{
                      "width":"100px",
                      "targets":16
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
                ,{
                      "width":"100px",
                      "targets":20
                }
                ,{
                      "width":"100px",
                      "targets":21
                }
                ,{
                      "width":"100px",
                      "targets":22
                }
                ,{
                      "width":"100px",
                      "targets":23
                }
                ,{
                      "width":"100px",
                      "targets":24
                }
                 ,{
                     "width":"100px",
                     "targets":25,
                     "render":function( data, type, row ) {
                         return row.IS_CASH_CHEQUE === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":26,
                     "render":function( data, type, row ) {
                         return row.ALLOW_CHEQUE_DENOM === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":27
                }
                ,{
                      "width":"100px",
                      "targets":28
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "DOC_TYPE", "defaultContent":""}
                ,{ "data": "IS_CHEQUE_BOOK", "defaultContent":""}
                ,{ "data": "PREFIX_REQ", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "IN_CONTRAL", "defaultContent":""}
                ,{ "data": "HAVE_NUMBER", "defaultContent":""}
                ,{ "data": "DOC_TYPE_DESC", "defaultContent":""}
                ,{ "data": "DOC_CLASS", "defaultContent":""}
                ,{ "data": "OTHER_BANK_FLAG", "defaultContent":""}
                ,{ "data": "ALLOW_DISTR", "defaultContent":""}
                ,{ "data": "BRANCH_RESTRAINT", "defaultContent":""}
                ,{ "data": "VOUCHER_BILL_IND", "defaultContent":""}
                ,{ "data": "USE_BY_ORDER", "defaultContent":""}
                ,{ "data": "VOUCHER_LENGTH", "defaultContent":""}
                ,{ "data": "TC_DENOM_GROUP", "defaultContent":""}
                ,{ "data": "START_DATE", "defaultContent":""}
                ,{ "data": "PROFIT_CENTRE", "defaultContent":""}
                ,{ "data": "USER_ID", "defaultContent":""}
                ,{ "data": "LAST_CHANGE_USER_ID", "defaultContent":""}
                ,{ "data": "CHECK_USER_ID", "defaultContent":""}
                ,{ "data": "COMMISSION_VOU_LOST_DAYS", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "DEPOSIT_TYPE", "defaultContent":""}
                ,{ "data": "EXPIRE_DATE", "defaultContent":""}
                ,{ "data": "IS_CASH_CHEQUE", "defaultContent":""}
                ,{ "data": "ALLOW_CHEQUE_DENOM", "defaultContent":""}
                ,{ "data": "LAST_CHANGE_DATE", "defaultContent":""}
                ,{ "data": "VOU_LOST_DAYS", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#tbVoucherDef"),opt);
        $("#tbVoucherDef").beautyUi({
            tableId:"tbVoucherDef",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#tbVoucherDef tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#tbVoucherDef').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#tbVoucherDef').on('page.dt', function (e) {
                $('#tbVoucherDef').find("tr").removeClass('selected');
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
            var attrTab = $("#tbVoucherDef").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function tb_voucher_def_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function tb_voucher_def_mod(title,url,w,h){
    if ($("#tbVoucherDef").find(".selected").length!==1){
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
function  tb_voucher_def_del(){
    if ($("#tbVoucherDef").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         tbVoucherDefDel();
    });
}

function  tbVoucherDefDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#tbVoucherDef').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="TB_VOUCHER_DEF";
        keyFieldsJson.DOC_TYPE=rowData.DOC_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_tbVoucherDefDel,"json");                //将获取数据发送给后台处理
}

function  callback_tbVoucherDefDel(json){
    if (json.success) {
        $("#tbVoucherDef").dataTable().api().row(".selected").remove().draw(false);
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
            tb_voucher_def_contrast();
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
            tb_voucher_def_add('添加','add/tbVoucherDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            tb_voucher_def_mod('修改','edit/tbVoucherDefMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                tb_voucher_def_del();
            });
        }
        $("#submit").on("click",function(){
            tb_voucher_def_submit();
        });
        $("#contrast").on("click",function(){
            tb_voucher_def_contrast();
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
            tb_voucher_def_contrast();
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
			tb_voucher_def_add('添加','add/tbVoucherDefAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			tb_voucher_def_mod('修改','edit/tbVoucherDefMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			tb_voucher_def_del();
		});
		$("#contrast").on("click",function(){
			tb_voucher_def_contrast();
		});
		$("#submit").on("click",function(){
			tb_voucher_def_submit();
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
function  tb_voucher_def_contrast(){
    var attrTab = $("#tbVoucherDef").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=TB_VOUCHER_DEF" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function tb_voucher_def_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"TB_VOUCHER_DEF"
            }, callback_tbVoucherDefSubmit,"json");
    });
}

function  callback_tbVoucherDefSubmit(json){
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
  	var prodTab = $("#tbVoucherDef").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#tbVoucherDef").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=TB_VOUCHER_DEF").load();
}



