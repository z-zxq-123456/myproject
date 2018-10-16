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
    	var opt = getDataTableOpt($("#cifClass1"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CIF_CLASS_1",
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "CLASS_1", "defaultContent":""}
                ,{ "data": "CLASS_1_DESC", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
                ,{ "data": "COUNTER_PARTY", "defaultContent":""}
            ];
    	//渲染tables
	opt.order = [[1, 'asc']];
    	drawDataTable($("#cifClass1"),opt);
        $("#cifClass1").beautyUi({
            tableId:"cifClass1",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cifClass1 tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cifClass1').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#cifClass1').on('page.dt', function (e) {
                $('#cifClass1').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#CLASS_1").val()===""
        &&$("#COUNTER_PARTY").val()===""
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#cifClass1").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=CLASS_1&colV1="+$("#CLASS_1").val()+"&col2=COUNTER_PARTY&colV2="+$("#COUNTER_PARTY").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function cif_class_1_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cif_class_1_mod(title,url,w,h){
    if ($("#cifClass1").find(".selected").length!==1){
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
function  cif_class_1_del(){
    if ($("#cifClass1").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         cifClass1Del();
    });
}

function  cifClass1Del(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#cifClass1').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="CIF_CLASS_1";
        keyFieldsJson.CLASS_1=rowData.CLASS_1;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_cifClass1Del,"json");                //将获取数据发送给后台处理
}

function  callback_cifClass1Del(json){
    if (json.success) {
        $("#cifClass1").dataTable().api().row(".selected").remove().draw(false);
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
            cif_class_1_contrast();
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
            cif_class_1_add('添加','add/cifClass1Add.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cif_class_1_mod('修改','edit/cifClass1Mod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cif_class_1_del();
            });
        }
        $("#submit").on("click",function(){
            cif_class_1_submit();
        });
        $("#contrast").on("click",function(){
            cif_class_1_contrast();
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
            cif_class_1_contrast();
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
			cif_class_1_add('添加','add/cifClass1Add.jsp','600','520');
		});
		$("#edit").on("click",function(){
			cif_class_1_mod('修改','edit/cifClass1Mod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			cif_class_1_del();
		});
		$("#contrast").on("click",function(){
			cif_class_1_contrast();
		});
		$("#submit").on("click",function(){
			cif_class_1_submit();
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
function  cif_class_1_contrast(){
    var attrTab = $("#cifClass1").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CIF_CLASS_1" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function cif_class_1_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"CIF_CLASS_1"
            }, callback_cifClass1Submit,"json");
    });
}

function  callback_cifClass1Submit(json){
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
  	var prodTab = $("#cifClass1").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cifClass1").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CIF_CLASS_1").load();
}



