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
    	var opt = getDataTableOpt($("#mbAccountingStatus"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_ACCOUNTING_STATUS",
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
                     "targets":1,
                     "render":function( data, type, row ) {
                         return row.WRITE_OFF === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":2,
                     "render":function( data, type, row ) {
                         return row.GRACE_DAY === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":3,
                     "render":function( data, type, row ) {
                         return row.HUNTING_STATUS === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":4,
                     "render":function( data, type, row ) {
                         return row.NON_PERFORMING === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":5,
                     "render":function( data, type, row ) {
                         return row.NON_PERFORMING_PRI === "Y"?"是":"否";
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
                         return row.SUSPEND_IND === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.TERMINATE_IND === "Y"?"是":"否";
                     }
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
                     "targets":12,
                     "render":function( data, type, row ) {
                         return row.AVAILABLE === "Y"?"是":"否";
                     }
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
                     "targets":20,
                     "render":function( data, type, row ) {
                         return row.AUTO_CHANGE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":21
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "WRITE_OFF", "defaultContent":""}
                ,{ "data": "GRACE_DAY", "defaultContent":""}
                ,{ "data": "HUNTING_STATUS", "defaultContent":""}
                ,{ "data": "NON_PERFORMING", "defaultContent":""}
                ,{ "data": "NON_PERFORMING_PRI", "defaultContent":""}
                ,{ "data": "PERIOD", "defaultContent":""}
                ,{ "data": "PERIOD_TYPE", "defaultContent":""}
                ,{ "data": "SUSPEND_IND", "defaultContent":""}
                ,{ "data": "TERMINATE_IND", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "CHANGE_TYPE", "defaultContent":""}
                ,{ "data": "AVAILABLE", "defaultContent":""}
                ,{ "data": "ACCOUNTING_STATUS_DESC", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_FEE", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_INT", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_ODI", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_ODP", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_PRI", "defaultContent":""}
                ,{ "data": "ALLOC_SEQ_TYPE", "defaultContent":""}
                ,{ "data": "AUTO_CHANGE", "defaultContent":""}
                ,{ "data": "ACCOUNTING_STATUS", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#mbAccountingStatus"),opt);
        $("#mbAccountingStatus").beautyUi({
            tableId:"mbAccountingStatus",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbAccountingStatus tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbAccountingStatus').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#mbAccountingStatus').on('page.dt', function (e) {
                $('#mbAccountingStatus').find("tr").removeClass('selected');
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
            var attrTab = $("#mbAccountingStatus").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function mb_accounting_status_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_accounting_status_mod(title,url,w,h){
    if ($("#mbAccountingStatus").find(".selected").length!==1){
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
function  mb_accounting_status_del(){
    if ($("#mbAccountingStatus").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         mbAccountingStatusDel();
    });
}

function  mbAccountingStatusDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#mbAccountingStatus').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="MB_ACCOUNTING_STATUS";
        keyFieldsJson.WRITE_OFF=rowData.WRITE_OFF;
        keyFieldsJson.GRACE_DAY=rowData.GRACE_DAY;
        keyFieldsJson.HUNTING_STATUS=rowData.HUNTING_STATUS;
        keyFieldsJson.NON_PERFORMING=rowData.NON_PERFORMING;
        keyFieldsJson.NON_PERFORMING_PRI=rowData.NON_PERFORMING_PRI;
        keyFieldsJson.PERIOD=rowData.PERIOD;
        keyFieldsJson.PERIOD_TYPE=rowData.PERIOD_TYPE;
        keyFieldsJson.SUSPEND_IND=rowData.SUSPEND_IND;
        keyFieldsJson.TERMINATE_IND=rowData.TERMINATE_IND;
        keyFieldsJson.COMPANY=rowData.COMPANY;
        keyFieldsJson.CHANGE_TYPE=rowData.CHANGE_TYPE;
        keyFieldsJson.AVAILABLE=rowData.AVAILABLE;
        keyFieldsJson.ACCOUNTING_STATUS_DESC=rowData.ACCOUNTING_STATUS_DESC;
        keyFieldsJson.ALLOC_SEQ_FEE=rowData.ALLOC_SEQ_FEE;
        keyFieldsJson.ALLOC_SEQ_INT=rowData.ALLOC_SEQ_INT;
        keyFieldsJson.ALLOC_SEQ_ODI=rowData.ALLOC_SEQ_ODI;
        keyFieldsJson.ALLOC_SEQ_ODP=rowData.ALLOC_SEQ_ODP;
        keyFieldsJson.ALLOC_SEQ_PRI=rowData.ALLOC_SEQ_PRI;
        keyFieldsJson.ALLOC_SEQ_TYPE=rowData.ALLOC_SEQ_TYPE;
        keyFieldsJson.AUTO_CHANGE=rowData.AUTO_CHANGE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_mbAccountingStatusDel,"json");                //将获取数据发送给后台处理
}

function  callback_mbAccountingStatusDel(json){
    if (json.success) {
        $("#mbAccountingStatus").dataTable().api().row(".selected").remove().draw(false);
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
            mb_accounting_status_contrast();
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
            mb_accounting_status_add('添加','add/mbAccountingStatusAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_accounting_status_mod('修改','edit/mbAccountingStatusMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_accounting_status_del();
            });
        }
        $("#submit").on("click",function(){
            mb_accounting_status_submit();
        });
        $("#contrast").on("click",function(){
            mb_accounting_status_contrast();
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
            mb_accounting_status_contrast();
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
			mb_accounting_status_add('添加','add/mbAccountingStatusAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			mb_accounting_status_mod('修改','edit/mbAccountingStatusMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			mb_accounting_status_del();
		});
		$("#contrast").on("click",function(){
			mb_accounting_status_contrast();
		});
		$("#submit").on("click",function(){
			mb_accounting_status_submit();
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
function  mb_accounting_status_contrast(){
    var attrTab = $("#mbAccountingStatus").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_ACCOUNTING_STATUS" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function mb_accounting_status_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"MB_ACCOUNTING_STATUS"
            }, callback_mbAccountingStatusSubmit,"json");
    });
}

function  callback_mbAccountingStatusSubmit(json){
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
  	var prodTab = $("#mbAccountingStatus").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbAccountingStatus").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_ACCOUNTING_STATUS").load();
}



