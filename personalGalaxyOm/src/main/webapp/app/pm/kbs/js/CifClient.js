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
    	var opt = getDataTableOpt($("#cifClient"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CIF_CLIENT",
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
                     "targets":13,
                     "render":function( data, type, row ) {
                         return row.TEMP_CLIENT === "Y"?"是":"否";
                     }
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
                      "targets":22
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "CLIENT_NO", "defaultContent":""}
                ,{ "data": "TRAN_STATUS", "defaultContent":""}
                ,{ "data": "ACCT_EXEC", "defaultContent":""}
                ,{ "data": "CATEGORY_TYPE", "defaultContent":""}
                ,{ "data": "STATE_LOC", "defaultContent":""}
                ,{ "data": "COUNTRY_RISK", "defaultContent":""}
                ,{ "data": "COUNTRY_LOC", "defaultContent":""}
                ,{ "data": "COUNTRY_CITIZEN", "defaultContent":""}
                ,{ "data": "CLIENT_TYPE", "defaultContent":""}
                ,{ "data": "CLIENT_SHORT", "defaultContent":""}
                ,{ "data": "INLAND_OFFSHORE", "defaultContent":""}
                ,{ "data": "LOCATION", "defaultContent":""}
                ,{ "data": "TEMP_CLIENT", "defaultContent":""}
                ,{ "data": "CR_RATING", "defaultContent":""}
                ,{ "data": "CTRL_BRANCH", "defaultContent":""}
                ,{ "data": "CLIENT_CITY", "defaultContent":""}
                ,{ "data": "GLOBAL_ID_TYPE", "defaultContent":""}
                ,{ "data": "CLIENT_STATUS", "defaultContent":""}
                ,{ "data": "GLOBAL_ID", "defaultContent":""}
                ,{ "data": "CH_CLIENT_NAME", "defaultContent":""}
                ,{ "data": "CLIENT_NAME", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#cifClient"),opt);
        $("#cifClient").beautyUi({
            tableId:"cifClient",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cifClient tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cifClient').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#cifClient').on('page.dt', function (e) {
                $('#cifClient').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_DOCUMENT_TYPE&tableCol=DOCUMENT_TYPE,DOCUMENT_TYPE",
        id: "GLOBAL_ID_TYPE",
        async: false
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#CLIENT_NO").val()===""
        &&$("#LOCATION").val()===""
        &&$("#GLOBAL_ID_TYPE").val()===""
    )
        {
            initData_refresh();
        }else{
            var attrTab = $("#cifClient").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=CLIENT_NO&colV1="+$("#CLIENT_NO").val()+"&col2=LOCATION&colV2="+$("#LOCATION").val()+"&col3=GLOBAL_ID_TYPE&"+"colV3="+$("#GLOBAL_ID_TYPE").val()   ).load();
        }
    });

});

/*增加*/
function cif_client_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cif_client_mod(title,url,w,h){
    if ($("#cifClient").find(".selected").length!==1){
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
function  cif_client_del(){
    if ($("#cifClient").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         cifClientDel();
    });
}

function  cifClientDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#cifClient').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="CIF_CLIENT";
        keyFieldsJson.CLIENT_NO=rowData.CLIENT_NO;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_cifClientDel,"json");                //将获取数据发送给后台处理
}

function  callback_cifClientDel(json){
    if (json.success) {
        $("#cifClient").dataTable().api().row(".selected").remove().draw(false);
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
            cif_client_contrast();
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
            cif_client_add('添加','add/cifClientAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cif_client_mod('修改','edit/cifClientMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cif_client_del();
            });
        }
        $("#submit").on("click",function(){
            cif_client_submit();
        });
        $("#contrast").on("click",function(){
            cif_client_contrast();
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
            cif_client_contrast();
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
			cif_client_add('添加','add/cifClientAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			cif_client_mod('修改','edit/cifClientMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			cif_client_del();
		});
		$("#contrast").on("click",function(){
			cif_client_contrast();
		});
		$("#submit").on("click",function(){
			cif_client_submit();
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
function  cif_client_contrast(){
    var attrTab = $("#cifClient").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CIF_CLIENT" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function cif_client_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"CIF_CLIENT"
            }, callback_cifClientSubmit,"json");
    });
}

function  callback_cifClientSubmit(json){
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
  	var prodTab = $("#cifClient").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cifClient").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CIF_CLIENT").load();
}



