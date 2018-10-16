
$(document).ready(function() {
$(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
	var opt = getDataTableOpt($("#irlSystem"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/baseCommon/getList",
			 "type": "POST",
			 "data":{
                "tableName":"IRL_SYSTEM",
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

         ];

    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
            ,{ "data": "COY_NAME", "defaultContent":""}
            ,{ "data": "COY_SHORT", "defaultContent":""}
             ,{ "data": "RUN_DATE", "defaultContent":""}
            ,{ "data": "BASE_CCY", "defaultContent":""}
             ,{ "data": "LOCAL_CCY", "defaultContent":""}
            ,{ "data": "DEFAULT_BRANCH", "defaultContent":""}
              ,{ "data": "LAST_RUN_DATE", "defaultContent":""}
             ,{ "data": "NEXT_RUN_DATE", "defaultContent":""}
             ,{ "data": "DEFAULT_RATE_TYPE", "defaultContent":""}
              ,{ "data": "QUOTE_BALANCE_TYPE", "defaultContent":""}
             ,{ "data": "INT_EVENT_VALUE", "defaultContent":""}
              ,{ "data": "GL_MERGE_TYPE", "defaultContent":""}
              ,{ "data": "DEFAULT_CHARGE_RATE_TYPE", "defaultContent":""}





        ];
	//渲染tables
	opt.order = [[1, 'asc']];
	drawDataTable($("#irlSystem"),opt);
    $("#irlSystem").beautyUi({
        tableId:"irlSystem",
        buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
        buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
        });
    $('#irlSystem tbody').on('click', 'tr', function (e) {
         if ( $(this).hasClass('selected') ) {
             $(this).removeClass('selected');
         } else {
             $('#irlSystem').find("tr").removeClass('selected');
             $(this).addClass('selected');
         }
        });

    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    $("#selectByPrimaryKey").click(function(){
        if(1===1
                &&$("#COY_NAME").val()===""
                &&$("#COY_SHORT").val()===""
            )
        {
            initData_refresh();
        }else{
            var attrTab = $("#irlSystem").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=COY_NAME&colV1="+$("#COY_NAME").val()+"&col2=COY_SHORT&colV2="+$("#COY_SHORT").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
    $(".select2").select2();
    buttonStatus();
});
function buttonStatus()
{
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            irl_system_contrast();
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
            irl_system_add('添加','add/irlSystemAdd.jsp','600','540');
        });
        $("#edit").on("click",function(){
            irl_system_mod('修改','edit/irlSystemMod.jsp','600','540');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                irl_system_del();
            });
        }
        $("#submit").on("click",function(){
            irl_system_submit();
        });
        $("#contrast").on("click",function(){
            irl_system_contrast();
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
            irl_system_contrast();
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
			irl_system_add('添加','add/irlSystemAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			irl_system_mod('修改','edit/irlSystemMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			irl_system_del();
		});
		$("#contrast").on("click",function(){
			irl_system_contrast();
		});
		$("#submit").on("click",function(){
			irl_system_submit();
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
function  irl_system_contrast(){
    var attrTab = $("#irlSystem").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=IRL_SYSTEM" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function irl_system_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"IRL_SYSTEM"
            }, callback_irlSystemSubmit,"json");
    });
}

function  callback_irlSystemSubmit(json){
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


var  layer_add_index,layer_edit_index;
/*增加*/
function irl_system_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px'],
        end: function(){
          selectAll_refresh();
        }
    });
}

/*修改*/
function irl_system_mod(title,url,w,h){
    if ($("#irlSystem").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px'],
        end: function(){
            selectAll_refresh();
        }
    });
}

/*删除*/
function  irl_system_del(){
    if ($("#irlSystem").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         irlSystemDel();
    });
}

function  irlSystemDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#irlSystem').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="IRL_SYSTEM";
    keyFieldsJson.COY_NAME=rowData.COY_NAME;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_irlSystemDel,"json");                //将获取数据发送给后台处理
}

function  callback_irlSystemDel(json){
    if (json.success) {
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function selectAll_refresh(){
  	var prodTab = $("#irlSystem").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#irlSystem").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=IRL_SYSTEM").load();
}



function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    if(msg==="添加成功") {
        layer.close(layer_add_index);
        selectAll_refresh();
    }
    if(msg==="编辑成功") {
        layer.close(layer_edit_index);
    }

}

