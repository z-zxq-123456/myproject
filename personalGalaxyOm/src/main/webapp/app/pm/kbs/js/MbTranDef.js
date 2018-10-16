var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    if(msg=="添加成功") {
        selectAll_refresh();
    }
}

$(document).ready(function() {
        $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
    	var opt = getDataTableOpt($("#mbTranDef"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_TRAN_DEF",
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
                        return row.CASH_TRAN === "Y"?"是":"否";
                    }
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
                    "targets":5,
                    "render":function( data, type, row ) {
                        return row.IS_CORRECT === "Y"?"是":"否";
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
                    "targets":8
                }
                ,{
                    "width":"100px",
                    "targets":9
                }
                ,{
                    "width":"100px",
                    "targets":10,
                    "render":function( data, type, row ) {
                        return row.RECALC_ACCT_STOP_PAY === "Y"?"是":"否";
                    }
                }
                ,{
                    "width":"100px",
                    "targets":11,
                    "render":function( data, type, row ) {
                        return row.RECALC_RES_AMT === "Y"?"是":"否";
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
                    "targets":14,
                    "render":function( data, type, row ) {
                        return row.REVERSAL_TRAN_TYPE === "Y"?"是":"否";
                    }
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
                        return row.UPD_TRAILBOX_FLAG === "Y"?"是":"否";
                    }
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
                ,{ "data": "TRAN_TYPE", "defaultContent":""}
                ,{ "data": "CASH_TRAN", "defaultContent":""}
                ,{ "data": "BAL_TYPE_PRIORITY", "defaultContent":""}
                ,{ "data": "CR_DR_MAINT_IND", "defaultContent":""}
                ,{ "data": "IS_CORRECT", "defaultContent":""}
                ,{ "data": "MULTI_RVS_TRAN_TYPE_IND", "defaultContent":""}
                ,{ "data": "OTH_TRAN_TYPE", "defaultContent":""}
                ,{ "data": "PRINT_TRAN_DESC", "defaultContent":""}
                ,{ "data": "PROGRAM_ID_GROUP", "defaultContent":""}
                ,{ "data": "RECALC_ACCT_STOP_PAY", "defaultContent":""}
                ,{ "data": "RECALC_RES_AMT", "defaultContent":""}
                ,{ "data": "RES_PRIORITY", "defaultContent":""}
                ,{ "data": "REVERSAL_TRAN_FLAG", "defaultContent":""}
                ,{ "data": "REVERSAL_TRAN_TYPE", "defaultContent":""}
                ,{ "data": "SOURCE_TYPE", "defaultContent":""}
                ,{ "data": "TRAN_CLASS", "defaultContent":""}
                ,{ "data": "TRAN_TIME", "defaultContent":""}
                ,{ "data": "TRAN_TIMESTAMP", "defaultContent":""}
                ,{ "data": "TRAN_TYPE_DESC", "defaultContent":""}
                ,{ "data": "UPD_TRAILBOX_FLAG", "defaultContent":""}
                ,{ "data": "AVAILBAL_CALC_TYPE", "defaultContent":""}
                ,{ "data": "BALANCE_FLAG", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#mbTranDef"),opt);
        $("#mbTranDef").beautyUi({
            tableId:"mbTranDef",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbTranDef tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbTranDef').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#mbTranDef').on('page.dt', function (e) {
                $('#mbTranDef').find("tr").removeClass('selected');
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
            var attrTab = $("#mbTranDef").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function mb_tran_def_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_tran_def_mod(title,url,w,h){
    if ($("#mbTranDef").find(".selected").length!==1){
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
function  mb_tran_def_del(){
    if ($("#mbTranDef").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        mbTranDefDel();
    });
}

function  mbTranDefDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#mbTranDef').DataTable().rows(".selected").data()[0];
        paraJson.tableName="MB_TRAN_DEF";
        keyFieldsJson.TRAN_TYPE=rowData.TRAN_TYPE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_mbTranDefDel,"json");
}

function  callback_mbTranDefDel(json){
    if (json.success) {
        $("#mbTranDef").dataTable().api().row(".selected").remove().draw(false);
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
            mb_tran_def_contrast();
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
            mb_tran_def_add('添加','add/mbTranDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_tran_def_mod('修改','edit/mbTranDefMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_tran_def_del();
            });
        }
        $("#submit").on("click",function(){
            mb_tran_def_submit();
        });
        $("#contrast").on("click",function(){
            mb_tran_def_contrast();
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
            mb_tran_def_contrast();
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
            mb_tran_def_add('添加','add/mbTranDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_tran_def_mod('修改','edit/mbTranDefMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            mb_tran_def_del();
        });
        $("#contrast").on("click",function(){
            mb_tran_def_contrast();
        });
        $("#submit").on("click",function(){
            mb_tran_def_submit();
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
function  mb_tran_def_contrast(){
    var attrTab = $("#mbTranDef").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_TRAN_DEF").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function mb_tran_def_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"MB_TRAN_DEF",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_mbTranDefSubmit,"json");
    });
}

function  callback_mbTranDefSubmit(json){
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
  	var prodTab = $("#mbTranDef").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbTranDef").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_TRAN_DEF").load();
}