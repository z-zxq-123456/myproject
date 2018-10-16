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
    	var opt = getDataTableOpt($("#cifCategoryType"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CIF_CATEGORY_TYPE",
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
                         return row.REP_OFFICE === "Y"?"是":"否";
                     }
                 }
                ,{
                      "width":"100px",
                      "targets":3
                }
                 ,{
                     "width":"100px",
                     "targets":4,
                     "render":function( data, type, row ) {
                         return row.CENTRAL_BANK === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":5,
                     "render":function( data, type, row ) {
                         return row.BANK === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":6,
                     "render":function( data, type, row ) {
                         return row.BROKER === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":7,
                     "render":function( data, type, row ) {
                         return row.OTHER === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":8,
                     "render":function( data, type, row ) {
                         return row.CORPORATION === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":9,
                     "render":function( data, type, row ) {
                         return row.FIN_INSTITUTION === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":10,
                     "render":function( data, type, row ) {
                         return row.GOVERNMENT === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":11,
                     "render":function( data, type, row ) {
                         return row.INDIVIDUAL === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":12,
                     "render":function( data, type, row ) {
                         return row.INTL_INSTITUTION === "Y"?"是":"否";
                     }
                 }
                 ,{
                     "width":"100px",
                     "targets":13,
                     "render":function( data, type, row ) {
                         return row.JOINT === "Y"?"是":"否";
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "CATEGORY_TYPE", "defaultContent":""}
                ,{ "data": "REP_OFFICE", "defaultContent":""}
                ,{ "data": "CATEGORY_DESC", "defaultContent":""}
                ,{ "data": "CENTRAL_BANK", "defaultContent":""}
                ,{ "data": "BANK", "defaultContent":""}
                ,{ "data": "BROKER", "defaultContent":""}
                ,{ "data": "OTHER", "defaultContent":""}
                ,{ "data": "CORPORATION", "defaultContent":""}
                ,{ "data": "FIN_INSTITUTION", "defaultContent":""}
                ,{ "data": "GOVERNMENT", "defaultContent":""}
                ,{ "data": "INDIVIDUAL", "defaultContent":""}
                ,{ "data": "INTL_INSTITUTION", "defaultContent":""}
                ,{ "data": "JOINT", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "CLIENT_TYPE", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#cifCategoryType"),opt);
        $("#cifCategoryType").beautyUi({
            tableId:"cifCategoryType",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cifCategoryType tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cifCategoryType').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#cifCategoryType').on('page.dt', function (e) {
                $('#cifCategoryType').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CATEGORY_TYPE&tableCol=CATEGORY_TYPE,CATEGORY_DESC",
        id: "CATEGORY_TYPE",
        async: false
        });
        getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
        id: "CLIENT_TYPE",
        async: false
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#CATEGORY_TYPE").val()===""
        &&$("#CLIENT_TYPE").val()===""
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#cifCategoryType").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=CATEGORY_TYPE&colV1="+$("#CATEGORY_TYPE").val()+"&col2=CLIENT_TYPE&colV2="+$("#CLIENT_TYPE").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function cif_category_type_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cif_category_type_mod(title,url,w,h){
    if ($("#cifCategoryType").find(".selected").length!==1){
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
function  cif_category_type_del(){
    if ($("#cifCategoryType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         cifCategoryTypeDel();
    });
}

function  cifCategoryTypeDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#cifCategoryType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="CIF_CATEGORY_TYPE";
        keyFieldsJson.CATEGORY_TYPE=rowData.CATEGORY_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_cifCategoryTypeDel,"json");                //将获取数据发送给后台处理
}

function  callback_cifCategoryTypeDel(json){
    if (json.success) {
        $("#cifCategoryType").dataTable().api().row(".selected").remove().draw(false);
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
            cif_category_type_contrast();
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
            cif_category_type_add('添加','add/cifCategoryTypeAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cif_category_type_mod('修改','edit/cifCategoryTypeMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cif_category_type_del();
            });
        }
        $("#submit").on("click",function(){
            cif_category_type_submit();
        });
        $("#contrast").on("click",function(){
            cif_category_type_contrast();
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
            cif_category_type_contrast();
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
			cif_category_type_add('添加','add/cifCategoryTypeAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			cif_category_type_mod('修改','edit/cifCategoryTypeMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			cif_category_type_del();
		});
		$("#contrast").on("click",function(){
			cif_category_type_contrast();
		});
		$("#submit").on("click",function(){
			cif_category_type_submit();
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
function  cif_category_type_contrast(){
    var attrTab = $("#cifCategoryType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CIF_CATEGORY_TYPE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function cif_category_type_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"CIF_CATEGORY_TYPE"
            }, callback_cifCategoryTypeSubmit,"json");
    });
}

function  callback_cifCategoryTypeSubmit(json){
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
  	var prodTab = $("#cifCategoryType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cifCategoryType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CIF_CATEGORY_TYPE").load();
}



