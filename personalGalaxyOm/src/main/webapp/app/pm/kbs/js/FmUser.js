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
    	var opt = getDataTableOpt($("#fmUser"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_USER",
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
                         return row.EOD_SOD_ENABLED === "Y"?"是":"否";
                     }
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
                     "targets":22,
                     "render":function( data, type, row ) {
                         return row.APPLICATION_USER === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":23
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "USER_ID", "defaultContent":""}
                ,{ "data": "EOD_SOD_ENABLED", "defaultContent":""}
                ,{ "data": "ACCOUNT_STATUS", "defaultContent":""}
                ,{ "data": "USER_TYPE", "defaultContent":""}
                ,{ "data": "CHECK_DATE", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "DEPARTMENT", "defaultContent":""}
                ,{ "data": "DOCUMENT_ID", "defaultContent":""}
                ,{ "data": "DOCUMENT_TYPE", "defaultContent":""}
                ,{ "data": "INTER_BRANCH_IND", "defaultContent":""}
                ,{ "data": "MAKER", "defaultContent":""}
                ,{ "data": "MAKE_DATE", "defaultContent":""}
                ,{ "data": "PROFIT_CENTRE", "defaultContent":""}
                ,{ "data": "TBOOK", "defaultContent":""}
                ,{ "data": "USER_DESC", "defaultContent":""}
                ,{ "data": "USER_LANG", "defaultContent":""}
                ,{ "data": "USER_LEVEL", "defaultContent":""}
                ,{ "data": "USER_NAME", "defaultContent":""}
                ,{ "data": "CHECKER", "defaultContent":""}
                ,{ "data": "BRANCH", "defaultContent":""}
                ,{ "data": "AUTH_LEVEL", "defaultContent":""}
                ,{ "data": "APPLICATION_USER", "defaultContent":""}
                ,{ "data": "ACCT_EXEC", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#fmUser"),opt);
        $("#fmUser").beautyUi({
            tableId:"fmUser",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmUser tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmUser').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#fmUser').on('page.dt', function (e) {
                $('#fmUser').find("tr").removeClass('selected');
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
            var attrTab = $("#fmUser").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function fm_user_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_user_mod(title,url,w,h){
    if ($("#fmUser").find(".selected").length!==1){
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
function  fm_user_del(){
    if ($("#fmUser").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         fmUserDel();
    });
}

function  fmUserDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#fmUser').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="FM_USER";
        keyFieldsJson.USER_ID=rowData.USER_ID;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_fmUserDel,"json");                //将获取数据发送给后台处理
}

function  callback_fmUserDel(json){
    if (json.success) {
        $("#fmUser").dataTable().api().row(".selected").remove().draw(false);
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
            fm_user_contrast();
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
            fm_user_add('添加','add/fmUserAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_user_mod('修改','edit/fmUserMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_user_del();
            });
        }
        $("#submit").on("click",function(){
            fm_user_submit();
        });
        $("#contrast").on("click",function(){
            fm_user_contrast();
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
            fm_user_contrast();
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
			fm_user_add('添加','add/fmUserAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			fm_user_mod('修改','edit/fmUserMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			fm_user_del();
		});
		$("#contrast").on("click",function(){
			fm_user_contrast();
		});
		$("#submit").on("click",function(){
			fm_user_submit();
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
function  fm_user_contrast(){
    var attrTab = $("#fmUser").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_USER" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function fm_user_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"FM_USER"
            }, callback_fmUserSubmit,"json");
    });
}

function  callback_fmUserSubmit(json){
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
  	var prodTab = $("#fmUser").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmUser").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_USER").load();
}



