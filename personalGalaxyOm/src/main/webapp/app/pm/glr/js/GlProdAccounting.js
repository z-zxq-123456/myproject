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
    	var opt = getDataTableOpt($("#glProdAccounting"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"GL_PROD_ACCOUNTING",
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "ACCOUNTING_STATUS", "defaultContent":""}
                ,{ "data": "PROD_TYPE", "defaultContent":""}
                ,{ "data": "GL_CODE_ODP_REC", "defaultContent":""}
                ,{ "data": "GL_CODE_ODP_I", "defaultContent":""}
                ,{ "data": "GL_CODE_ODP_ACR", "defaultContent":""}
                ,{ "data": "GL_CODE_ODI_REC", "defaultContent":""}
                ,{ "data": "GL_CODE_ODI_I", "defaultContent":""}
                ,{ "data": "GL_CODE_ODI_ACR", "defaultContent":""}
                ,{ "data": "GL_CODE_L", "defaultContent":""}
                ,{ "data": "GL_CODE_INT_REC", "defaultContent":""}
                ,{ "data": "GL_CODE_INT_PAY", "defaultContent":""}
                ,{ "data": "GL_CODE_INT_I", "defaultContent":""}
                ,{ "data": "GL_CODE_INT_E", "defaultContent":""}
                ,{ "data": "GL_CODE_INT_ACR", "defaultContent":""}
                ,{ "data": "GL_CODE_A_LOSS", "defaultContent":""}
                ,{ "data": "GL_CODE_ADJUST", "defaultContent":""}
                ,{ "data": "GL_CODE_A", "defaultContent":""}
                ,{ "data": "BUSINESS_UNIT", "defaultContent":""}
                ,{ "data": "PROFIT_CENTRE", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#glProdAccounting"),opt);
        $("#glProdAccounting").beautyUi({
            tableId:"glProdAccounting",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#glProdAccounting tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdAccounting').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#glProdAccounting').on('page.dt', function (e) {
                $('#glProdAccounting').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=GL_PROD_MAPPING&tableCol=PROD_TYPE,PROD_DESC",
        id: "PROD_TYPE",
        async: false
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#PROD_TYPE").val()===""
        &&$("#ACCOUNTING_STATUS").val()===""
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#glProdAccounting").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=PROD_TYPE&colV1="+$("#PROD_TYPE").val()+"&col2=ACCOUNTING_STATUS&colV2="+$("#ACCOUNTING_STATUS").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function gl_prod_accounting_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function gl_prod_accounting_mod(title,url,w,h){
    if ($("#glProdAccounting").find(".selected").length!==1){
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
function  gl_prod_accounting_del(){
    if ($("#glProdAccounting").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         glProdAccountingDel();
    });
}

function  glProdAccountingDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="GL_PROD_ACCOUNTING";
    paraJson.systemId="GLR";
        keyFieldsJson.PROD_TYPE=rowData.PROD_TYPE;
        keyFieldsJson.ACCOUNTING_STATUS=rowData.ACCOUNTING_STATUS;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_glProdAccountingDel,"json");                //将获取数据发送给后台处理
}

function  callback_glProdAccountingDel(json){
    if (json.success) {
        $("#glProdAccounting").dataTable().api().row(".selected").remove().draw(false);
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
            gl_prod_accounting_contrast();
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
            gl_prod_accounting_add('添加','add/glProdAccountingAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            gl_prod_accounting_mod('修改','edit/glProdAccountingMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                gl_prod_accounting_del();
            });
        }
        $("#submit").on("click",function(){
            gl_prod_accounting_submit();
        });
        $("#contrast").on("click",function(){
            gl_prod_accounting_contrast();
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
            gl_prod_accounting_contrast();
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
			gl_prod_accounting_add('添加','add/glProdAccountingAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			gl_prod_accounting_mod('修改','edit/glProdAccountingMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			gl_prod_accounting_del();
		});
		$("#contrast").on("click",function(){
			gl_prod_accounting_contrast();
		});
		$("#submit").on("click",function(){
			gl_prod_accounting_submit();
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
function  gl_prod_accounting_contrast(){
    var attrTab = $("#glProdAccounting").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=GL_PROD_ACCOUNTING&systemId=GLR" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function gl_prod_accounting_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"GL_PROD_ACCOUNTING",
            systemId:"GLR"
            }, callback_glProdAccountingSubmit,"json");
    });
}

function  callback_glProdAccountingSubmit(json){
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
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=GL_PROD_ACCOUNTING").load();
}



