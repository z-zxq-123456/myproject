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
    	var opt = getDataTableOpt($("#mbAcctNatureRestraints"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_ACCT_NATURE_RESTRAINTS",
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "ACCT_NATURE", "defaultContent":""}
                ,{ "data": "RESTRAINT_TYPE", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#mbAcctNatureRestraints"),opt);
        $("#mbAcctNatureRestraints").beautyUi({
            tableId:"mbAcctNatureRestraints",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbAcctNatureRestraints tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbAcctNatureRestraints').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#mbAcctNatureRestraints').on('page.dt', function (e) {
                $('#mbAcctNatureRestraints').find("tr").removeClass('selected');
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
            var attrTab = $("#mbAcctNatureRestraints").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function mb_acct_nature_restraints_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_acct_nature_restraints_mod(title,url,w,h){
    if ($("#mbAcctNatureRestraints").find(".selected").length!==1){
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
function  mb_acct_nature_restraints_del(){
    if ($("#mbAcctNatureRestraints").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         mbAcctNatureRestraintsDel();
    });
}

function  mbAcctNatureRestraintsDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#mbAcctNatureRestraints').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="MB_ACCT_NATURE_RESTRAINTS";
        keyFieldsJson.ACCT_NATURE=rowData.ACCT_NATURE;
        keyFieldsJson.RESTRAINT_TYPE=rowData.RESTRAINT_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_mbAcctNatureRestraintsDel,"json");                //将获取数据发送给后台处理
}

function  callback_mbAcctNatureRestraintsDel(json){
    if (json.success) {
        $("#mbAcctNatureRestraints").dataTable().api().row(".selected").remove().draw(false);
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
            mb_acct_nature_restraints_contrast();
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
            mb_acct_nature_restraints_add('添加','add/mbAcctNatureRestraintsAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_acct_nature_restraints_mod('修改','edit/mbAcctNatureRestraintsMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_acct_nature_restraints_del();
            });
        }
        $("#submit").on("click",function(){
            mb_acct_nature_restraints_submit();
        });
        $("#contrast").on("click",function(){
            mb_acct_nature_restraints_contrast();
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
            mb_acct_nature_restraints_contrast();
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
			mb_acct_nature_restraints_add('添加','add/mbAcctNatureRestraintsAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			mb_acct_nature_restraints_mod('修改','edit/mbAcctNatureRestraintsMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			mb_acct_nature_restraints_del();
		});
		$("#contrast").on("click",function(){
			mb_acct_nature_restraints_contrast();
		});
		$("#submit").on("click",function(){
			mb_acct_nature_restraints_submit();
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
function  mb_acct_nature_restraints_contrast(){
    var attrTab = $("#mbAcctNatureRestraints").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_ACCT_NATURE_RESTRAINTS" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function mb_acct_nature_restraints_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"MB_ACCT_NATURE_RESTRAINTS"
            }, callback_mbAcctNatureRestraintsSubmit,"json");
    });
}

function  callback_mbAcctNatureRestraintsSubmit(json){
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
  	var prodTab = $("#mbAcctNatureRestraints").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbAcctNatureRestraints").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_ACCT_NATURE_RESTRAINTS").load();
}



