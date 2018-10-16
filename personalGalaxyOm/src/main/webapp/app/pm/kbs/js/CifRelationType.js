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
    	var opt = getDataTableOpt($("#cifRelationType"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CIF_RELATION_TYPE",
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
                         return row.AUTHORISED === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":3,
                     "render":function( data, type, row ) {
                         return row.JOINT_ACCT === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":4,
                     "render":function( data, type, row ) {
                         return row.RELATIVE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":5
                }
                 ,{
                     "width":"100px",
                     "targets":6,
                     "render":function( data, type, row ) {
                         return row.EMPLOYMENT === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":7,
                     "render":function( data, type, row ) {
                         return row.EQUITY === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":8,
                     "render":function( data, type, row ) {
                         return row.EXPOSURE === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.SYMMENTRIC === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":10,
                     "render":function( data, type, row ) {
                         return row.JOIN_COLLAT === "Y"?"是":"否";
                     }
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "RELATION_TYPE", "defaultContent":""}
                ,{ "data": "AUTHORISED", "defaultContent":""}
                ,{ "data": "JOINT_ACCT", "defaultContent":""}
                ,{ "data": "RELATIVE", "defaultContent":""}
                ,{ "data": "RELATION_DESC", "defaultContent":""}
                ,{ "data": "EMPLOYMENT", "defaultContent":""}
                ,{ "data": "EQUITY", "defaultContent":""}
                ,{ "data": "EXPOSURE", "defaultContent":""}
                ,{ "data": "SYMMENTRIC", "defaultContent":""}
                ,{ "data": "JOIN_COLLAT", "defaultContent":""}
                ,{ "data": "RELATION_TYPE_FLAG", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "COUNTER_REL", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#cifRelationType"),opt);
        $("#cifRelationType").beautyUi({
            tableId:"cifRelationType",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cifRelationType tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cifRelationType').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#cifRelationType').on('page.dt', function (e) {
                $('#cifRelationType').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#RELATION_TYPE").val()===""
        &&$("#EXPOSURE").val()===""
        &&$("#JOINT_ACCT").val()===""
    )
        {
            initData_refresh();
        }else{
            var attrTab = $("#cifRelationType").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=RELATION_TYPE&colV1="+$("#RELATION_TYPE").val()+"&col2=EXPOSURE&colV2="+$("#EXPOSURE").val()+"&col3=JOINT_ACCT&"+"colV3="+$("#JOINT_ACCT").val()   ).load();
        }
    });

});

/*增加*/
function cif_relation_type_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cif_relation_type_mod(title,url,w,h){
    if ($("#cifRelationType").find(".selected").length!==1){
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
function  cif_relation_type_del(){
    if ($("#cifRelationType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         cifRelationTypeDel();
    });
}

function  cifRelationTypeDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#cifRelationType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="CIF_RELATION_TYPE";
        keyFieldsJson.RELATION_TYPE=rowData.RELATION_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_cifRelationTypeDel,"json");                //将获取数据发送给后台处理
}

function  callback_cifRelationTypeDel(json){
    if (json.success) {
        $("#cifRelationType").dataTable().api().row(".selected").remove().draw(false);
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
            cif_relation_type_contrast();
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
            cif_relation_type_add('添加','add/cifRelationTypeAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cif_relation_type_mod('修改','edit/cifRelationTypeMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cif_relation_type_del();
            });
        }
        $("#submit").on("click",function(){
            cif_relation_type_submit();
        });
        $("#contrast").on("click",function(){
            cif_relation_type_contrast();
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
            cif_relation_type_contrast();
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
			cif_relation_type_add('添加','add/cifRelationTypeAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			cif_relation_type_mod('修改','edit/cifRelationTypeMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			cif_relation_type_del();
		});
		$("#contrast").on("click",function(){
			cif_relation_type_contrast();
		});
		$("#submit").on("click",function(){
			cif_relation_type_submit();
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
function  cif_relation_type_contrast(){
    var attrTab = $("#cifRelationType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CIF_RELATION_TYPE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function cif_relation_type_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"CIF_RELATION_TYPE"
            }, callback_cifRelationTypeSubmit,"json");
    });
}

function  callback_cifRelationTypeSubmit(json){
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
  	var prodTab = $("#cifRelationType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cifRelationType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CIF_RELATION_TYPE").load();
}



