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
    	var opt = getDataTableOpt($("#fmBranch"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_BRANCH",
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
                     "targets":6,
                     "render":function( data, type, row ) {
                         return row.CHEQUE_ISSUING_BRANCH === "Y"?"是":"否";
                     }
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
                     "targets":11,
                     "render":function( data, type, row ) {
                         return row.EOD_IND === "Y"?"是":"否";
                     }
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
                     "targets":18,
                     "render":function( data, type, row ) {
                         return row.PBOC_FUND_CHECK_FALG === "Y"?"是":"否";
                     }
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
                      "targets":23
                }
                 ,{
                     "width":"100px",
                     "targets":24,
                     "render":function( data, type, row ) {
                         return row.TRAN_BR_IND === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":25
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "BRANCH", "defaultContent":""}
                ,{ "data": "COUNTRY", "defaultContent":""}
                ,{ "data": "STATE", "defaultContent":""}
                ,{ "data": "BRANCH_SHORT", "defaultContent":""}
                ,{ "data": "CCY_CTRL_BRANCH", "defaultContent":""}
                ,{ "data": "CHEQUE_ISSUING_BRANCH", "defaultContent":""}
                ,{ "data": "CITY", "defaultContent":""}
                ,{ "data": "CNY_BUSINESS_UNIT", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "DISTRICT", "defaultContent":""}
                ,{ "data": "EOD_IND", "defaultContent":""}
                ,{ "data": "FX_ORGAN_CODE", "defaultContent":""}
                ,{ "data": "HIERARCHY_CODE", "defaultContent":""}
                ,{ "data": "HKD_BUSINESS_UNIT", "defaultContent":""}
                ,{ "data": "INTERNAL_CLIENT", "defaultContent":""}
                ,{ "data": "IP_ADDR", "defaultContent":""}
                ,{ "data": "LOCAL_CCY", "defaultContent":""}
                ,{ "data": "PBOC_FUND_CHECK_FALG", "defaultContent":""}
                ,{ "data": "POSTAL_CODE", "defaultContent":""}
                ,{ "data": "PROFIT_CENTRE", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "SUB_BRANCH_CODE", "defaultContent":""}
                ,{ "data": "BRANCH_NAME", "defaultContent":""}
                ,{ "data": "FTA_CODE", "defaultContent":""}
                ,{ "data": "FTA_FLAG", "defaultContent":""}
                ,{ "data": "TRAN_BR_IND", "defaultContent":""}
                ,{ "data": "ATTACHED_TO", "defaultContent":""}
                 ,{ "data": "VOUCHER_USER_CONTRAL", "defaultContent":""}
                ,{ "data": "BASE_CCY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#fmBranch"),opt);
        $("#fmBranch").beautyUi({
            tableId:"fmBranch",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmBranch tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmBranch').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#fmBranch').on('page.dt', function (e) {
                $('#fmBranch').find("tr").removeClass('selected');
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
            var attrTab = $("#fmBranch").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function fm_branch_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_branch_mod(title,url,w,h){
    if ($("#fmBranch").find(".selected").length!==1){
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
function  fm_branch_del(){
    if ($("#fmBranch").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         fmBranchDel();
    });
}

function  fmBranchDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#fmBranch').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="FM_BRANCH";
        keyFieldsJson.BRANCH=rowData.BRANCH;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_fmBranchDel,"json");                //将获取数据发送给后台处理
}

function  callback_fmBranchDel(json){
    if (json.success) {
        $("#fmBranch").dataTable().api().row(".selected").remove().draw(false);
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
            fm_branch_contrast();
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
            fm_branch_add('添加','add/fmBranchAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_branch_mod('修改','edit/fmBranchMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_branch_del();
            });
        }
        $("#submit").on("click",function(){
            fm_branch_submit();
        });
        $("#contrast").on("click",function(){
            fm_branch_contrast();
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
            fm_branch_contrast();
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
			fm_branch_add('添加','add/fmBranchAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			fm_branch_mod('修改','edit/fmBranchMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			fm_branch_del();
		});
		$("#contrast").on("click",function(){
			fm_branch_contrast();
		});
		$("#submit").on("click",function(){
			fm_branch_submit();
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
function  fm_branch_contrast(){
    var attrTab = $("#fmBranch").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_BRANCH" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function fm_branch_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"FM_BRANCH"
            }, callback_fmBranchSubmit,"json");
    });
}

function  callback_fmBranchSubmit(json){
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
  	var prodTab = $("#fmBranch").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmBranch").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_BRANCH").load();
}



