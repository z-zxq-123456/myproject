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
    	var opt = getDataTableOpt($("#fmRestraintType"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_RESTRAINT_TYPE",
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
                         return row.EOD_IMPOUND_FALG === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":4,
                     "render":function( data, type, row ) {
                         return row.MANUAL_RES_FLAG === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":5,
                     "render":function( data, type, row ) {
                         return row.STOP_WTD_FALG === "Y"?"是":"否";
                     }
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
                     "targets":8,
                     "render":function( data, type, row ) {
                         return row.AH_BU === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.MANUAL_UNRES_FLAG === "Y"?"是":"否";
                     }
                 }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "RESTRAINT_TYPE", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "EOD_IMPOUND_FALG", "defaultContent":""}
                ,{ "data": "MANUAL_RES_FLAG", "defaultContent":""}
                ,{ "data": "STOP_WTD_FALG", "defaultContent":""}
                ,{ "data": "RESTRAINT_CLASS", "defaultContent":""}
                ,{ "data": "RESTRAINT_DESC", "defaultContent":""}
                ,{ "data": "AH_BU", "defaultContent":""}
                ,{ "data": "SYSTEM_USE_FLAG", "defaultContent":""}
                ,{ "data": "MANUAL_UNRES_FLAG", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#fmRestraintType"),opt);
        $("#fmRestraintType").beautyUi({
            tableId:"fmRestraintType",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmRestraintType tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmRestraintType').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#fmRestraintType').on('page.dt', function (e) {
                $('#fmRestraintType').find("tr").removeClass('selected');
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
            var attrTab = $("#fmRestraintType").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function fm_restraint_type_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_restraint_type_mod(title,url,w,h){
    if ($("#fmRestraintType").find(".selected").length!==1){
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
function  fm_restraint_type_del(){
    if ($("#fmRestraintType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         fmRestraintTypeDel();
    });
}

function  fmRestraintTypeDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#fmRestraintType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="FM_RESTRAINT_TYPE";
        keyFieldsJson.RESTRAINT_TYPE=rowData.RESTRAINT_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_fmRestraintTypeDel,"json");                //将获取数据发送给后台处理
}

function  callback_fmRestraintTypeDel(json){
    if (json.success) {
        $("#fmRestraintType").dataTable().api().row(".selected").remove().draw(false);
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
            fm_restraint_type_contrast();
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
            fm_restraint_type_add('添加','add/fmRestraintTypeAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_restraint_type_mod('修改','edit/fmRestraintTypeMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_restraint_type_del();
            });
        }
        $("#submit").on("click",function(){
            fm_restraint_type_submit();
        });
        $("#contrast").on("click",function(){
            fm_restraint_type_contrast();
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
            fm_restraint_type_contrast();
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
			fm_restraint_type_add('添加','add/fmRestraintTypeAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			fm_restraint_type_mod('修改','edit/fmRestraintTypeMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			fm_restraint_type_del();
		});
		$("#contrast").on("click",function(){
			fm_restraint_type_contrast();
		});
		$("#submit").on("click",function(){
			fm_restraint_type_submit();
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
function  fm_restraint_type_contrast(){
    var attrTab = $("#fmRestraintType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_RESTRAINT_TYPE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function fm_restraint_type_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"FM_RESTRAINT_TYPE"
            }, callback_fmRestraintTypeSubmit,"json");
    });
}

function  callback_fmRestraintTypeSubmit(json){
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
  	var prodTab = $("#fmRestraintType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmRestraintType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_RESTRAINT_TYPE").load();
}



