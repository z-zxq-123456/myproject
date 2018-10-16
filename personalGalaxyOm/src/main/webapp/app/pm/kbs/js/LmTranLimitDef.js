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
    	var opt = getDataTableOpt($("#lmTranLimitDef"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"LM_TRAN_LIMIT_DEF",
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "LIMIT_REF", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "DEAL_FLOW", "defaultContent":""}
                ,{ "data": "EFFECT_DATE", "defaultContent":""}
                ,{ "data": "LIMIT_DESC", "defaultContent":""}
                ,{ "data": "LIMIT_TYPE", "defaultContent":""}
                ,{ "data": "MAX_AMT", "defaultContent":""}
                ,{ "data": "MIN_AMT", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "LIMIT_LEVEL", "defaultContent":""}
                ,{ "data": "ENABLE_DEFINE", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#lmTranLimitDef"),opt);
        $("#lmTranLimitDef").beautyUi({
            tableId:"lmTranLimitDef",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#lmTranLimitDef tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#lmTranLimitDef').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#lmTranLimitDef').on('page.dt', function (e) {
                $('#lmTranLimitDef').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#LIMIT_REF").val()===""
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#lmTranLimitDef").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=LIMIT_REF&colV1="+$("#LIMIT_REF").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function lm_tran_limit_def_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function lm_tran_limit_def_mod(title,url,w,h){
    if ($("#lmTranLimitDef").find(".selected").length!==1){
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
function  lm_tran_limit_def_del(){
    if ($("#lmTranLimitDef").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         lmTranLimitDefDel();
    });
}

function  lmTranLimitDefDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#lmTranLimitDef').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="LM_TRAN_LIMIT_DEF";
        keyFieldsJson.LIMIT_REF=rowData.LIMIT_REF;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_lmTranLimitDefDel,"json");                //将获取数据发送给后台处理
}

function  callback_lmTranLimitDefDel(json){
    if (json.success) {
        $("#lmTranLimitDef").dataTable().api().row(".selected").remove().draw(false);
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
            lm_tran_limit_def_contrast();
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
            lm_tran_limit_def_add('添加','add/lmTranLimitDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            lm_tran_limit_def_mod('修改','edit/lmTranLimitDefMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                lm_tran_limit_def_del();
            });
        }
        $("#submit").on("click",function(){
            lm_tran_limit_def_submit();
        });
        $("#contrast").on("click",function(){
            lm_tran_limit_def_contrast();
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
            lm_tran_limit_def_contrast();
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
			lm_tran_limit_def_add('添加','add/lmTranLimitDefAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			lm_tran_limit_def_mod('修改','edit/lmTranLimitDefMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			lm_tran_limit_def_del();
		});
		$("#contrast").on("click",function(){
			lm_tran_limit_def_contrast();
		});
		$("#submit").on("click",function(){
			lm_tran_limit_def_submit();
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
function  lm_tran_limit_def_contrast(){
    var attrTab = $("#lmTranLimitDef").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=LM_TRAN_LIMIT_DEF" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function lm_tran_limit_def_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"LM_TRAN_LIMIT_DEF"
            }, callback_lmTranLimitDefSubmit,"json");
    });
}

function  callback_lmTranLimitDefSubmit(json){
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
  	var prodTab = $("#lmTranLimitDef").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#lmTranLimitDef").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=LM_TRAN_LIMIT_DEF").load();
}


