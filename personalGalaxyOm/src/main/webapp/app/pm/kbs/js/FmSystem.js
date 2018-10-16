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
    	var opt = getDataTableOpt($("#fmSystem"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_SYSTEM",
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
                     "targets":3,
                     "render":function( data, type, row ) {
                         return row.CONTINUOUS_RUN === "Y"?"是":"否";
                     }
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
                     "targets":6,
                     "render":function( data, type, row ) {
                         return row.GL_IND === "Y"?"是":"否";
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
                      "targets":9
                }
                 ,{
                     "width":"100px",
                     "targets":10,
                     "render":function( data, type, row ) {
                         return row.PROCESS_SPLIT_IND === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":11,
                     "render":function( data, type, row ) {
                         return row.CLIENT_BLOCK_FREQ === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":12,
                     "render":function( data, type, row ) {
                         return row.AUTO_LOCK_BL_CLIENT === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":13
                }
                 ,{
                     "width":"100px",
                     "targets":14,
                     "render":function( data, type, row ) {
                         return row.AUTO_CLIENT_GEN === "Y"?"是":"否";
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
                         return row.PRODUCT_30E === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":17
                }
                 ,{
                     "width":"100px",
                     "targets":18,
                     "render":function( data, type, row ) {
                         return row.AUTO_COLL_GEN === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":19
                }
                 ,{
                     "width":"100px",
                     "targets":20,
                     "render":function( data, type, row ) {
                         return row.IS_ERROR === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":21,
                     "render":function( data, type, row ) {
                         return row.IS_DEBUG === "Y"?"是":"否";
                     }
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
                         return row.BATCH_DEFAULT_USER_ID === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":26
                }
                 ,{
                     "width":"100px",
                     "targets":27,
                     "render":function( data, type, row ) {
                         return row.MULTI_CORP_QUERY_ALLOW === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":28,
                     "render":function( data, type, row ) {
                         return row.NPV_GAP_TYPE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":29
                }
                ,{
                      "width":"100px",
                      "targets":30
                }
                 ,{
                     "width":"100px",
                     "targets":31,
                     "render":function( data, type, row ) {
                         return row.MULTI_CORPORATION_FLAG === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":32,
                     "render":function( data, type, row ) {
                         return row.INTER_BRANCH_ACCT_HO === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":33
                }
                ,{
                      "width":"100px",
                      "targets":34
                }
                 ,{
                     "width":"100px",
                     "targets":35,
                     "render":function( data, type, row ) {
                         return row.CLIENT_NO_STRUCTURE_TYPE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":36
                }
                ,{
                      "width":"100px",
                      "targets":37
                }
                ,{
                      "width":"100px",
                      "targets":38
                }
                ,{
                      "width":"100px",
                      "targets":39
                }
                ,{
                      "width":"100px",
                      "targets":40
                }
                 ,{
                     "width":"100px",
                     "targets":41,
                     "render":function( data, type, row ) {
                         return row.DAC_IND === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":42
                }
                 ,{
                     "width":"100px",
                     "targets":43,
                     "render":function( data, type, row ) {
                         return row.INTERNAL_RATE_CHARGE_FLAG === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":44
                }
                 ,{
                     "width":"100px",
                     "targets":45,
                     "render":function( data, type, row ) {
                         return row.DEFAULT_CHARGE_RATE_TYPE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":46
                }
                ,{
                      "width":"100px",
                      "targets":47
                }
                ,{
                      "width":"100px",
                      "targets":48
                }
                ,{
                      "width":"100px",
                      "targets":49
                }
                ,{
                      "width":"100px",
                      "targets":50
                }
                ,{
                      "width":"100px",
                      "targets":51
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "COY_NAME", "defaultContent":""}
                ,{ "data": "INTER_BRANCH_IND", "defaultContent":""}
                ,{ "data": "CONTINUOUS_RUN", "defaultContent":""}
                ,{ "data": "COY_SHORT", "defaultContent":""}
                ,{ "data": "NEXT_RUN_DATE", "defaultContent":""}
                ,{ "data": "GL_IND", "defaultContent":""}
                ,{ "data": "YR_END_DATE", "defaultContent":""}
                ,{ "data": "MTH_END_DATE", "defaultContent":""}
                ,{ "data": "HALF_END_DATE", "defaultContent":""}
                ,{ "data": "PROCESS_SPLIT_IND", "defaultContent":""}
                ,{ "data": "CLIENT_BLOCK_FREQ", "defaultContent":""}
                ,{ "data": "AUTO_LOCK_BL_CLIENT", "defaultContent":""}
                ,{ "data": "SYSTEM_PHASE", "defaultContent":""}
                ,{ "data": "AUTO_CLIENT_GEN", "defaultContent":""}
                ,{ "data": "RUN_DATE", "defaultContent":""}
                ,{ "data": "PRODUCT_30E", "defaultContent":""}
                ,{ "data": "QUR_END_DATE", "defaultContent":""}
                ,{ "data": "AUTO_COLL_GEN", "defaultContent":""}
                ,{ "data": "LAST_RUN_DATE", "defaultContent":""}
                ,{ "data": "IS_ERROR", "defaultContent":""}
                ,{ "data": "IS_DEBUG", "defaultContent":""}
                ,{ "data": "LIMIT_CCY", "defaultContent":""}
                ,{ "data": "LOCAL_CCY", "defaultContent":""}
                ,{ "data": "MAIN_BRANCH_CODE", "defaultContent":""}
                ,{ "data": "BATCH_DEFAULT_USER_ID", "defaultContent":""}
                ,{ "data": "MULTI_CORPORATION_METHOD", "defaultContent":""}
                ,{ "data": "MULTI_CORP_QUERY_ALLOW", "defaultContent":""}
                ,{ "data": "NPV_GAP_TYPE", "defaultContent":""}
                ,{ "data": "REPORT_CCY", "defaultContent":""}
                ,{ "data": "RB_RESTRAINT_TYPE", "defaultContent":""}
                ,{ "data": "MULTI_CORPORATION_FLAG", "defaultContent":""}
                ,{ "data": "INTER_BRANCH_ACCT_HO", "defaultContent":""}
                ,{ "data": "CR_DR_CHECK_FLAG", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "CLIENT_NO_STRUCTURE_TYPE", "defaultContent":""}
                ,{ "data": "CAPITAL_FUNDS", "defaultContent":""}
                ,{ "data": "BATCH_UNIT", "defaultContent":""}
                ,{ "data": "BATCH_MODULE", "defaultContent":""}
                ,{ "data": "BATCH_CHECK_FLAG", "defaultContent":""}
                ,{ "data": "BASE_CCY", "defaultContent":""}
                ,{ "data": "DAC_IND", "defaultContent":""}
                ,{ "data": "DEFAULT_BRANCH", "defaultContent":""}
                ,{ "data": "INTERNAL_RATE_CHARGE_FLAG", "defaultContent":""}
                ,{ "data": "HEAD_OFFICE_CLIENT", "defaultContent":""}
                ,{ "data": "DEFAULT_CHARGE_RATE_TYPE", "defaultContent":""}
                ,{ "data": "EXCHANGE_RATE_VARIANCE", "defaultContent":""}
                ,{ "data": "EBH_BRANCH", "defaultContent":""}
                ,{ "data": "DEFAULT_RATE_TYPE_LOCAL", "defaultContent":""}
                ,{ "data": "DEFAULT_RATE_TYPE", "defaultContent":""}
                ,{ "data": "DEFAULT_PROFIT_CENTRE", "defaultContent":""}
                ,{ "data": "ALLOW_BACKQRY_DAY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#fmSystem"),opt);
        $("#fmSystem").beautyUi({
            tableId:"fmSystem",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmSystem tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmSystem').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#fmSystem').on('page.dt', function (e) {
                $('#fmSystem').find("tr").removeClass('selected');
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
            var attrTab = $("#fmSystem").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function fm_system_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_system_mod(title,url,w,h){
    if ($("#fmSystem").find(".selected").length!==1){
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
function  fm_system_del(){
    if ($("#fmSystem").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         fmSystemDel();
    });
}

function  fmSystemDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#fmSystem').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="FM_SYSTEM";

    keyFieldsJson.COY_NAME=rowData.COY_NAME;

    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_fmSystemDel,"json");                //将获取数据发送给后台处理
}

function  callback_fmSystemDel(json){
    if (json.success) {
        $("#fmSystem").dataTable().api().row(".selected").remove().draw(false);
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
            fm_system_contrast();
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
            fm_system_add('添加','add/fmSystemAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_system_mod('修改','edit/fmSystemMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_system_del();
            });
        }
        $("#submit").on("click",function(){
            fm_system_submit();
        });
        $("#contrast").on("click",function(){
            fm_system_contrast();
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
            fm_system_contrast();
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
			fm_system_add('添加','add/fmSystemAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			fm_system_mod('修改','edit/fmSystemMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			fm_system_del();
		});
		$("#contrast").on("click",function(){
			fm_system_contrast();
		});
		$("#submit").on("click",function(){
			fm_system_submit();
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
function  fm_system_contrast(){
    var attrTab = $("#fmSystem").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_SYSTEM" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function fm_system_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"FM_SYSTEM"
            }, callback_fmSystemSubmit,"json");
    });
}

function  callback_fmSystemSubmit(json){
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
  	var prodTab = $("#fmSystem").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmSystem").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_SYSTEM").load();
}
