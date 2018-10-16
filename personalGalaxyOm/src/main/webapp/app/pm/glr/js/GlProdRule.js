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
    	var opt = getDataTableOpt($("#glProdRule"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"GL_PROD_RULE",
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
                ,{ "data": "TRAN_EVENT_TYPE", "defaultContent":""}
                ,{ "data": "CLIENT_TYPE", "defaultContent":""}
                ,{ "data": "ACCOUNTING_STATUS", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "SYS_NAME", "defaultContent":""}
                ,{ "data": "TRAN_TYPE", "defaultContent":""}
                ,{ "data": "PROD_TYPE", "defaultContent":""}
                ,{ "data": "SOURCE_TYPE", "defaultContent":""}
                ,{ "data": "ACCOUNTING_NO", "defaultContent":""}
                ,{ "data": "ACCOUNTING_DESC", "defaultContent":""}
                ,{ "data": "CUSTOM_RULE", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#glProdRule"),opt);
        $("#glProdRule").beautyUi({
            tableId:"glProdRule",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#glProdRule tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdRule').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#glProdRule').on('page.dt', function (e) {
                $('#glProdRule').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=GL_EVENT_MAPPING&tableCol=EVENT_TYPE,EVENT_TYPE_DESC",
        id: "TRAN_EVENT_TYPE",
        async: false
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
        id: "TRAN_TYPE",
        async: false
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
        id: "PROD_TYPE",
        async: false
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#PROD_TYPE").val()===""
        &&$("#TRAN_EVENT_TYPE").val()===""
        &&$("#TRAN_TYPE").val()===""
    )
        {
            initData_refresh();
        }else{
            var attrTab = $("#glProdRule").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=PROD_TYPE&colV1="+$("#PROD_TYPE").val()+"&col2=TRAN_EVENT_TYPE&colV2="+$("#TRAN_EVENT_TYPE").val()+"&col3=TRAN_TYPE&"+"colV3="+$("#TRAN_TYPE").val()   ).load();
        }
    });

});

/*增加*/
function gl_prod_rule_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function gl_prod_rule_mod(title,url,w,h){
    if ($("#glProdRule").find(".selected").length!==1){
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
function  gl_prod_rule_del(){
    if ($("#glProdRule").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         glProdRuleDel();
    });
}

function  glProdRuleDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="GL_PROD_RULE";
    paraJson.systemId="GLR";
        keyFieldsJson.TRAN_EVENT_TYPE=rowData.TRAN_EVENT_TYPE;
        keyFieldsJson.CLIENT_TYPE=rowData.CLIENT_TYPE;
        keyFieldsJson.ACCOUNTING_STATUS=rowData.ACCOUNTING_STATUS;
        keyFieldsJson.CCY=rowData.CCY;
        keyFieldsJson.SYS_NAME=rowData.SYS_NAME;
        keyFieldsJson.TRAN_TYPE=rowData.TRAN_TYPE;
        keyFieldsJson.PROD_TYPE=rowData.PROD_TYPE;
        keyFieldsJson.SOURCE_TYPE=rowData.SOURCE_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_glProdRuleDel,"json");                //将获取数据发送给后台处理
}

function  callback_glProdRuleDel(json){
    if (json.success) {
        $("#glProdRule").dataTable().api().row(".selected").remove().draw(false);
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
            gl_prod_rule_contrast();
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
            gl_prod_rule_add('添加','add/glProdRuleAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            gl_prod_rule_mod('修改','edit/glProdRuleMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                gl_prod_rule_del();
            });
        }
        $("#submit").on("click",function(){
            gl_prod_rule_submit();
        });
        $("#contrast").on("click",function(){
            gl_prod_rule_contrast();
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
            gl_prod_rule_contrast();
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
			gl_prod_rule_add('添加','add/glProdRuleAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			gl_prod_rule_mod('修改','edit/glProdRuleMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			gl_prod_rule_del();
		});
		$("#contrast").on("click",function(){
			gl_prod_rule_contrast();
		});
		$("#submit").on("click",function(){
			gl_prod_rule_submit();
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
function  gl_prod_rule_contrast(){
    var attrTab = $("#glProdRule").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=GL_PROD_RULE&systemId=GLR" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function gl_prod_rule_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"GL_PROD_RULE",
            systemId:"GLR"
            }, callback_glProdRuleSubmit,"json");
    });
}

function  callback_glProdRuleSubmit(json){
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
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=GL_PROD_RULE").load();
}



