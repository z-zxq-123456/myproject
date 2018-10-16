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
    	var opt = getDataTableOpt($("#glSystem"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"GL_SYSTEM",
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
                         return row.TWO_BOOK === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":3
                }
                 ,{
                     "width":"100px",
                     "targets":4,
                     "render":function( data, type, row ) {
                         return row.AUTO_INT_ACCT_CREATION === "Y"?"是":"否";
                     }
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
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.OS_CLOSE_IND === "Y"?"是":"否";
                     }
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
                      "targets":14
                }
                 ,{
                     "width":"100px",
                     "targets":15,
                     "render":function( data, type, row ) {
                         return row.IS_BATCH_FINISHED === "Y"?"是":"否";
                     }
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
                     "targets":23,
                     "render":function( data, type, row ) {
                         return row.AUTO_REF === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":24
                }
                 ,{
                     "width":"100px",
                     "targets":25,
                     "render":function( data, type, row ) {
                         return row.MULTISETTLE_IND === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":26
                }
                ,{
                      "width":"100px",
                      "targets":27
                }
                ,{
                      "width":"100px",
                      "targets":28
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
                      "targets":31
                }
                ,{
                      "width":"100px",
                      "targets":32
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
                      "targets":35
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
                     "targets":40,
                     "render":function( data, type, row ) {
                         return row.DEFAULT_CHARGE_RATE_TYPE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":41
                }
                ,{
                      "width":"100px",
                      "targets":42
                }
                ,{
                      "width":"100px",
                      "targets":43
                }
                ,{
                      "width":"100px",
                      "targets":44
                }
                 ,{
                     "width":"100px",
                     "targets":45,
                     "render":function( data, type, row ) {
                         return row.BATCH_CONS_IND === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":46
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "SEQ_NO", "defaultContent":""}
                ,{ "data": "TWO_BOOK", "defaultContent":""}
                ,{ "data": "ACCT_HIST_PERIOD_NO", "defaultContent":""}
                ,{ "data": "AUTO_INT_ACCT_CREATION", "defaultContent":""}
                ,{ "data": "TAX_ACCT_TYPE", "defaultContent":""}
                ,{ "data": "RUN_DATE", "defaultContent":""}
                ,{ "data": "REVAL_BY_POST", "defaultContent":""}
                ,{ "data": "PROFIT_CENTRE", "defaultContent":""}
                ,{ "data": "OS_CLOSE_IND", "defaultContent":""}
                ,{ "data": "NEXT_RUN_DATE", "defaultContent":""}
                ,{ "data": "MUTI_SETTLE_MODE", "defaultContent":""}
                ,{ "data": "MAX_FUTUREDATE_DAY", "defaultContent":""}
                ,{ "data": "MAX_BACKDATE_DAY", "defaultContent":""}
                ,{ "data": "LAST_RUN_DATE", "defaultContent":""}
                ,{ "data": "IS_BATCH_FINISHED", "defaultContent":""}
                ,{ "data": "INT_RECEIVABLE", "defaultContent":""}
                ,{ "data": "INT_ACCT_TYPE", "defaultContent":""}
                ,{ "data": "HIST_PERIOD_TYPE", "defaultContent":""}
                ,{ "data": "HIST_PERIOD_NO", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "TAX_FREQ", "defaultContent":""}
                ,{ "data": "BASE_CCY", "defaultContent":""}
                ,{ "data": "AUTO_REF", "defaultContent":""}
                ,{ "data": "SUSP_ASSET_ACCT", "defaultContent":""}
                ,{ "data": "MULTISETTLE_IND", "defaultContent":""}
                ,{ "data": "SUSP_CONT_ASSET", "defaultContent":""}
                ,{ "data": "NEXT_CYCLE_DATE", "defaultContent":""}
                ,{ "data": "SUSP_CONT_LIAB", "defaultContent":""}
                ,{ "data": "OS_CLOSE_BAL", "defaultContent":""}
                ,{ "data": "LAST_POSTING_RETENTION_DATE", "defaultContent":""}
                ,{ "data": "SUSP_SETTLE_LIAB", "defaultContent":""}
                ,{ "data": "RETAIN_EARNINGS", "defaultContent":""}
                ,{ "data": "INT_PAYABLE", "defaultContent":""}
                ,{ "data": "REF_PREFIX", "defaultContent":""}
                ,{ "data": "INTER_ACCTG_BK", "defaultContent":""}
                ,{ "data": "SUSP_SETTLE_ASSET", "defaultContent":""}
                ,{ "data": "PREV_CYCLE_DATE", "defaultContent":""}
                ,{ "data": "GL_CASH_CODE", "defaultContent":""}
                ,{ "data": "DEFAULT_RATE_TYPE", "defaultContent":""}
                ,{ "data": "DEFAULT_CHARGE_RATE_TYPE", "defaultContent":""}
                ,{ "data": "DEFAULT_BRANCH", "defaultContent":""}
                ,{ "data": "SUSP_LIAB_ACCT", "defaultContent":""}
                ,{ "data": "CCY_CTRL_ACCT", "defaultContent":""}
                ,{ "data": "BO_IND", "defaultContent":""}
                ,{ "data": "BATCH_CONS_IND", "defaultContent":""}
                ,{ "data": "LOCAL_CCY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#glSystem"),opt);
        $("#glSystem").beautyUi({
            tableId:"glSystem",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#glSystem tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glSystem').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#glSystem').on('page.dt', function (e) {
                $('#glSystem').find("tr").removeClass('selected');
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
            var attrTab = $("#glSystem").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function gl_system_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function gl_system_mod(title,url,w,h){
    if ($("#glSystem").find(".selected").length!==1){
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
function  gl_system_del(){
    if ($("#glSystem").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         glSystemDel();
    });
}

function  glSystemDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#glSystem').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="GL_SYSTEM";
        keyFieldsJson.SEQ_NO=rowData.SEQ_NO;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_glSystemDel,"json");                //将获取数据发送给后台处理
}

function  callback_glSystemDel(json){
    if (json.success) {
        $("#glSystem").dataTable().api().row(".selected").remove().draw(false);
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
            gl_system_contrast();
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
            gl_system_add('添加','add/glSystemAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            gl_system_mod('修改','edit/glSystemMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                gl_system_del();
            });
        }
        $("#submit").on("click",function(){
            gl_system_submit();
        });
        $("#contrast").on("click",function(){
            gl_system_contrast();
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
            gl_system_contrast();
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
			gl_system_add('添加','add/glSystemAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			gl_system_mod('修改','edit/glSystemMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			gl_system_del();
		});
		$("#contrast").on("click",function(){
			gl_system_contrast();
		});
		$("#submit").on("click",function(){
			gl_system_submit();
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
function  gl_system_contrast(){
    var attrTab = $("#glSystem").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=GL_SYSTEM" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function gl_system_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"GL_SYSTEM"
            }, callback_glSystemSubmit,"json");
    });
}

function  callback_glSystemSubmit(json){
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
  	var prodTab = $("#glSystem").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#glSystem").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=GL_SYSTEM").load();
}



