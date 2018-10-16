$(document).ready(function() {
$(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
	var opt = getDataTableOpt($("#irlCcyRate"));
	opt.stateSave=true;
	opt.processing=true;
	    opt.scrollX=true;
	opt.deferRender=true;
	opt.ajax= {
			 "url": contextPath+"/baseCommon/getList",
			 "type": "POST",
			 "data":{
                "tableName":"IRL_CCY_RATE",
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
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "BRANCH", "defaultContent":""}
                ,{ "data": "RATE_TYPE", "defaultContent":""}
                ,{ "data": "EFFECT_DATE", "defaultContent":""}
                ,{ "data": "EFFECT_TIME", "defaultContent":""}
                ,{ "data": "QUOTE_TYPE", "defaultContent":""}
                ,{ "data": "MIDDLE_RATE", "defaultContent":""}
                ,{ "data": "EXCH_BUY_RATE", "defaultContent":""}
                ,{ "data": "EXCH_SELL_RATE", "defaultContent":""}
                ,{ "data": "CENTRAL_BANK_RATE", "defaultContent":""}
                ,{ "data": "NOTES_BUY_RATE", "defaultContent":""}
                ,{ "data": "NOTES_SELL_RATE", "defaultContent":""}
                ,{ "data": "MAX_FLOAT_RATE", "defaultContent":""}

        ];
	//渲染tables
	opt.order = [[1, 'asc']];
	drawDataTable($("#irlCcyRate"),opt);
    $("#irlCcyRate").beautyUi({
        tableId:"irlCcyRate",
        buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
        buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
        });
    $('#irlCcyRate tbody').on('click', 'tr', function (e) {
         if ( $(this).hasClass('selected') ) {
             $(this).removeClass('selected');
         } else {
             $('#irlCcyRate').find("tr").removeClass('selected');
             $(this).addClass('selected');
         }
        });
    $('#irlCcyRate').on('page.dt', function (e) {
            $('#irlCcyRate').find("tr").removeClass('selected');
        });
    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
             getPkList({
               url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
               id: "BRANCH",
               async: false
             });
              getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
                id: "RATE_TYPE",
                async: false
              });
             getPkList({
               url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
               id: "CCY",
               async: false
             });

             $("#selectByPrimaryKey").click(function(){
                 if(1===1
                         &&$("#CCY").val()===""
                         &&$("#BRANCH").val()===""
                         &&$("#RATE_TYPE").val()===""
                     )
                 {
                     initData_refresh();
                 }else{
                     var attrTab = $("#irlCcyRate").dataTable();
                     var api = attrTab.api();
                     api.ajax.url(contextPath+"/baseCommon/selectBase?col1=CCY&colV1="+$("#CCY").val()+"&col2=BRANCH&colV2="+$("#BRANCH").val()+"&col3=RATE_TYPE&"+"colV3="+$("#RATE_TYPE").val()   ).load();
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
            irl_ccy_rate_contrast();
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
            irl_ccy_rate_add('添加','add/irlCcyRateAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            irl_ccy_rate_mod('修改','edit/irlCcyRateMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                irl_ccy_rate_del();
            });
        }
        $("#submit").on("click",function(){
            irl_ccy_rate_submit();
        });
        $("#contrast").on("click",function(){
            irl_ccy_rate_contrast();
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
            irl_ccy_rate_contrast();
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
			irl_ccy_rate_add('添加','add/irlCcyRateAdd.jsp','600','520');
		});
		$("#edit").on("click",function(){
			irl_ccy_rate_mod('修改','edit/irlCcyRateMod.jsp','600','520');
		});
		$("#delete").on("click",function(){
			irl_ccy_rate_del();
		});
		$("#contrast").on("click",function(){
			irl_ccy_rate_contrast();
		});
		$("#submit").on("click",function(){
			irl_ccy_rate_submit();
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
function  irl_ccy_rate_contrast(){
    var attrTab = $("#irlCcyRate").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=IRL_CCY_RATE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function irl_ccy_rate_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"IRL_CCY_RATE"
            }, callback_irlCcyRateSubmit,"json");
    });
}

function  callback_irlCcyRateSubmit(json){
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
function irl_ccy_rate_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function irl_ccy_rate_mod(title,url,w,h){
    if ($("#irlCcyRate").find(".selected").length!==1){
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
function  irl_ccy_rate_del(){
    if ($("#irlCcyRate").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         irlCcyRateDel();
    });
}

function  irlCcyRateDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#irlCcyRate').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="IRL_CCY_RATE";
        keyFieldsJson.BRANCH=rowData.BRANCH;
        keyFieldsJson.EFFECT_TIME=rowData.EFFECT_TIME;
        keyFieldsJson.RATE_TYPE=rowData.RATE_TYPE;
        keyFieldsJson.EFFECT_DATE=rowData.EFFECT_DATE;
        keyFieldsJson.CCY=rowData.CCY;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_irlCcyRateDel,"json");                //将获取数据发送给后台处理
}

function  callback_irlCcyRateDel(json){
    if (json.success) {
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function selectAll_refresh(){
  	var prodTab = $("#irlCcyRate").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#irlCcyRate").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=IRL_CCY_RATE").load();
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
