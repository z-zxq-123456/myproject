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
    	var opt = getDataTableOpt($("#mbStageDefine"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_STAGE_DEFINE",
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
                    "targets":11,
                    "render":function( data, type, row ) {
                        return row.TRANSFER_FLAG === "Y"?"是":"否";
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
                    "targets":16,
                    "render":function( data, type, row ) {
                        return row.PRE_WITHDRAW_FLAG === "Y"?"是":"否";
                    }
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
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "STAGE_CODE", "defaultContent":""}
                ,{ "data": "ISSUE_YEAR", "defaultContent":""}
                ,{ "data": "ISSUE_START_DATE", "defaultContent":""}
                ,{ "data": "PROD_TYPE", "defaultContent":""}
                ,{ "data": "ISSUE_END_DATE", "defaultContent":""}
                ,{ "data": "ISSUE_AMT", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "STAGE_CODE_DESC", "defaultContent":""}
                ,{ "data": "TERM", "defaultContent":""}
                ,{ "data": "TERM_TYPE", "defaultContent":""}
                ,{ "data": "TRANSFER_FLAG", "defaultContent":""}
                ,{ "data": "TRAN_BRANCH", "defaultContent":""}
                ,{ "data": "TRAN_DATE", "defaultContent":""}
                ,{ "data": "SALE_TYPE", "defaultContent":""}
                ,{ "data": "RESET_INT_FREQ", "defaultContent":""}
                ,{ "data": "PRE_WITHDRAW_FLAG", "defaultContent":""}
                ,{ "data": "PAY_INT_TYPE", "defaultContent":""}
                ,{ "data": "PART_WITHDRAW_NUM", "defaultContent":""}
                ,{ "data": "INT_CALC_TYPE", "defaultContent":""}
                ,{ "data": "USER_ID", "defaultContent":""}
                ,{ "data": "GET_INT_FREQ", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#mbStageDefine"),opt);
        $("#mbStageDefine").beautyUi({
            tableId:"mbStageDefine",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbStageDefine tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbStageDefine').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#mbStageDefine').on('page.dt', function (e) {
                $('#mbStageDefine').find("tr").removeClass('selected');
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
            var attrTab = $("#mbStageDefine").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function mb_stage_define_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_stage_define_mod(title,url,w,h){
    if ($("#mbStageDefine").find(".selected").length!==1){
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
function  mb_stage_define_del(){
    if ($("#mbStageDefine").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        mbStageDefineDel();
    });
}

function  mbStageDefineDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#mbStageDefine').DataTable().rows(".selected").data()[0];
        paraJson.tableName="MB_STAGE_DEFINE";
        keyFieldsJson.STAGE_CODE=rowData.STAGE_CODE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_mbStageDefineDel,"json");
}

function  callback_mbStageDefineDel(json){
    if (json.success) {
        $("#mbStageDefine").dataTable().api().row(".selected").remove().draw(false);
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
            mb_stage_define_contrast();
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
            mb_stage_define_add('添加','add/mbStageDefineAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_stage_define_mod('修改','edit/mbStageDefineMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_stage_define_del();
            });
        }
        $("#submit").on("click",function(){
            mb_stage_define_submit();
        });
        $("#contrast").on("click",function(){
            mb_stage_define_contrast();
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
            mb_stage_define_contrast();
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
            mb_stage_define_add('添加','add/mbStageDefineAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_stage_define_mod('修改','edit/mbStageDefineMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            mb_stage_define_del();
        });
        $("#contrast").on("click",function(){
            mb_stage_define_contrast();
        });
        $("#submit").on("click",function(){
            mb_stage_define_submit();
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
function  mb_stage_define_contrast(){
    var attrTab = $("#mbStageDefine").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_STAGE_DEFINE").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function mb_stage_define_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"MB_STAGE_DEFINE",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_mbStageDefineSubmit,"json");
    });
}

function  callback_mbStageDefineSubmit(json){
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
  	var prodTab = $("#mbStageDefine").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbStageDefine").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_STAGE_DEFINE").load();
}