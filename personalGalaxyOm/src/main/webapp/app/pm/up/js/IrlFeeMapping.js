$(document).ready(function() {
$(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
	var opt = getDataTableOpt($("#irlFeeMapping"));
	opt.stateSave=true;
	opt.processing=true;
	    opt.scrollX=true;
	opt.deferRender=true;
	opt.ajax= {
			 "url": contextPath+"/baseCommon/getList",
			 "type": "POST",
			 "data":{
                "tableName":"IRL_FEE_MAPPING",
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
            ,{ "data": "IRL_SEQ_NO", "defaultContent":""}
            ,{ "data": "FEE_TYPE", "defaultContent":""}
            ,{ "data": "BRANCH_RULE", "defaultContent":""}
            ,{ "data": "EVENT_TYPE_RULE", "defaultContent":""}
            ,{ "data": "TRAN_TYPE_RULE", "defaultContent":""}
            ,{ "data": "PROD_GROUP_RULE", "defaultContent":""}
            ,{ "data": "PROD_TYPE_RULE", "defaultContent":""}
            ,{ "data": "URGENT_FLAG_RULE", "defaultContent":""}
            ,{ "data": "IS_LOCAL_RULE", "defaultContent":""}
            ,{ "data": "AREA_RULE", "defaultContent":""}
            ,{ "data": "CCY_RULE", "defaultContent":""}
            ,{ "data": "CLIENT_TYPE_RULE", "defaultContent":""}
            ,{ "data": "CATEGORY_TYPE_RULE", "defaultContent":""}
            ,{ "data": "SOURCE_TYPE_RULE", "defaultContent":""}
            ,{ "data": "DOC_TYPE_RULE", "defaultContent":""}
            ,{ "data": "OLD_STATUS_RULE", "defaultContent":""}
            ,{ "data": "NEW_STATUS_RULE", "defaultContent":""}
            ,{ "data": "IS_RULE", "defaultContent":""}
            ,{ "data": "SERVICE_ID_RULE", "defaultContent":""}
        ];
	//渲染tables
	opt.order = [[1, 'asc']];
	drawDataTable($("#irlFeeMapping"),opt);
    $("#irlFeeMapping").beautyUi({
        tableId:"irlFeeMapping",
        buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
        buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
        });
    $('#irlFeeMapping tbody').on('click', 'tr', function (e) {
         if ( $(this).hasClass('selected') ) {
             $(this).removeClass('selected');
         } else {
             $('#irlFeeMapping').find("tr").removeClass('selected');
             $(this).addClass('selected');
         }
        });
    $('#irlFeeMapping').on('page.dt', function (e) {
            $('#irlFeeMapping').find("tr").removeClass('selected');
        });
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    $("#selectByPrimaryKey").click(function(){
        if(1===1
                &&$("#IRL_SEQ_NO").val()===""
            )
        {
            initData_refresh();
        }else{
            var attrTab = $("#irlFeeMapping").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=IRL_SEQ_NO&colV1="+$("#IRL_SEQ_NO").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
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
            irl_fee_mapping_contrast();
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
            irl_fee_mapping_add('添加','add/irlFeeMappingAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            irl_fee_mapping_mod('修改','edit/irlFeeMappingMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                irl_fee_mapping_del();
            });
        }
        $("#submit").on("click",function(){
            irl_fee_mapping_submit();
        });
        $("#contrast").on("click",function(){
            irl_fee_mapping_contrast();
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
            irl_fee_mapping_contrast();
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
			irl_fee_mapping_add('添加','add/irlFeeMappingAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			irl_fee_mapping_mod('修改','edit/irlFeeMappingMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			irl_fee_mapping_del();
		});
		$("#contrast").on("click",function(){
			irl_fee_mapping_contrast();
		});
		$("#submit").on("click",function(){
			irl_fee_mapping_submit();
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
function  irl_fee_mapping_contrast(){
    var attrTab = $("#irlFeeMapping").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=IRL_FEE_MAPPING&systemId=UP" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function irl_fee_mapping_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"IRL_FEE_MAPPING",
            systemId:"UP"
            }, callback_irlFeeMappingSubmit,"json");
    });
}

function  callback_irlFeeMappingSubmit(json){
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
function irl_fee_mapping_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function irl_fee_mapping_mod(title,url,w,h){
    if ($("#irlFeeMapping").find(".selected").length!==1){
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
function  irl_fee_mapping_del(){
    if ($("#irlFeeMapping").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         irlFeeMappingDel();
    });
}

function  irlFeeMappingDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#irlFeeMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="IRL_FEE_MAPPING";
    paraJson.systemId="UP";
        keyFieldsJson.IRL_SEQ_NO=rowData.IRL_SEQ_NO;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_irlFeeMappingDel,"json");                //将获取数据发送给后台处理
}

function  callback_irlFeeMappingDel(json){
    if (json.success) {
        $("#irlFeeMapping").dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function selectAll_refresh(){
  	var prodTab = $("#irlFeeMapping").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#irlFeeMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=IRL_FEE_MAPPING").load();
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
