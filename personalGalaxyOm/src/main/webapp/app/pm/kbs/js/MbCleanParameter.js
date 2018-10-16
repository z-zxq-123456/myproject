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
    	var opt = getDataTableOpt($("#mbCleanParameter"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_CLEAN_PARAMETER",
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
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "SEQ_NO", "defaultContent":""}
                ,{ "data": "ACCT_STATUS", "defaultContent":""}
                ,{ "data": "TERM_PERIOD", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "START_TIME", "defaultContent":""}
                ,{ "data": "PROD_TYPE", "defaultContent":""}
                ,{ "data": "PERIOD_FREQ_TYPE", "defaultContent":""}
                ,{ "data": "PERIOD_FREQ", "defaultContent":""}
                ,{ "data": "NEXT_CLEAN_DATE", "defaultContent":""}
                ,{ "data": "LAST_CLEAN_DATE", "defaultContent":""}
                ,{ "data": "END_TIME", "defaultContent":""}
                ,{ "data": "CLEAN_TYPE", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "BALANCE", "defaultContent":""}
                ,{ "data": "AGREEMENT_TYPE", "defaultContent":""}
                ,{ "data": "ACCT_TYPE", "defaultContent":""}
                ,{ "data": "TERM_TYPE", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#mbCleanParameter"),opt);
        $("#mbCleanParameter").beautyUi({
            tableId:"mbCleanParameter",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbCleanParameter tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbCleanParameter').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#mbCleanParameter').on('page.dt', function (e) {
                $('#mbCleanParameter').find("tr").removeClass('selected');
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
            var attrTab = $("#mbCleanParameter").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function mb_clean_parameter_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_clean_parameter_mod(title,url,w,h){
    if ($("#mbCleanParameter").find(".selected").length!==1){
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
function  mb_clean_parameter_del(){
    if ($("#mbCleanParameter").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        mbCleanParameterDel();
    });
}

function  mbCleanParameterDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#mbCleanParameter').DataTable().rows(".selected").data()[0];
        paraJson.tableName="MB_CLEAN_PARAMETER";
        keyFieldsJson.SEQ_NO=rowData.SEQ_NO;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_mbCleanParameterDel,"json");
}

function  callback_mbCleanParameterDel(json){
    if (json.success) {
        $("#mbCleanParameter").dataTable().api().row(".selected").remove().draw(false);
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
            mb_clean_parameter_contrast();
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
            mb_clean_parameter_add('添加','add/mbCleanParameterAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_clean_parameter_mod('修改','edit/mbCleanParameterMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_clean_parameter_del();
            });
        }
        $("#submit").on("click",function(){
            mb_clean_parameter_submit();
        });
        $("#contrast").on("click",function(){
            mb_clean_parameter_contrast();
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
            mb_clean_parameter_contrast();
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
            mb_clean_parameter_add('添加','add/mbCleanParameterAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_clean_parameter_mod('修改','edit/mbCleanParameterMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            mb_clean_parameter_del();
        });
        $("#contrast").on("click",function(){
            mb_clean_parameter_contrast();
        });
        $("#submit").on("click",function(){
            mb_clean_parameter_submit();
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
function  mb_clean_parameter_contrast(){
    var attrTab = $("#mbCleanParameter").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_CLEAN_PARAMETER").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function mb_clean_parameter_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"MB_CLEAN_PARAMETER",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_mbCleanParameterSubmit,"json");
    });
}

function  callback_mbCleanParameterSubmit(json){
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
  	var prodTab = $("#mbCleanParameter").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbCleanParameter").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_CLEAN_PARAMETER").load();
}