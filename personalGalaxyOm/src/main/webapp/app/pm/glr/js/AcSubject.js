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
    	var opt = getDataTableOpt($("#acSubject"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.scrollX=true;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"AC_SUBJECT",
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
                        return row.TFR_IND === "Y"?"是":"否";
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
                    "targets":8,
                    "render":function( data, type, row ) {
                        return row.MANUAL_BATCH_RES === "Y"?"是":"否";
                    }
                }
                ,{
                    "width":"100px",
                    "targets":9
                }
                ,{
                    "width":"100px",
                    "targets":10,
                    "render":function( data, type, row ) {
                        return row.OPERATING_TAX === "Y"?"是":"否";
                    }
                }
                ,{
                    "width":"100px",
                    "targets":11
                }
                ,{
                    "width":"100px",
                    "targets":12,
                    "render":function( data, type, row ) {
                        return row.OF_TRF === "Y"?"是":"否";
                    }
                }
                ,{
                    "width":"100px",
                    "targets":13
                }
                ,{
                    "width":"100px",
                    "targets":14,
                    "render":function( data, type, row ) {
                        return row.MANUAL_ACCOUNT === "Y"?"是":"否";
                    }
                }
                ,{
                    "width":"100px",
                    "targets":15,
                    "render":function( data, type, row ) {
                        return row.INTERNAL === "Y"?"是":"否";
                    }
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
                ,{ "data": "SUBJECT_CODE", "defaultContent":""}
                ,{ "data": "TFR_IND", "defaultContent":""}
                ,{ "data": "BALANCE_WAY", "defaultContent":""}
                ,{ "data": "BSPL_TYPE", "defaultContent":""}
                ,{ "data": "SUBJECT_TYPE", "defaultContent":""}
                ,{ "data": "SUBJECT_STATUS", "defaultContent":""}
                ,{ "data": "SUBJECT_DESC", "defaultContent":""}
                ,{ "data": "MANUAL_BATCH_RES", "defaultContent":""}
                ,{ "data": "GL_TYPE", "defaultContent":""}
                ,{ "data": "OPERATING_TAX", "defaultContent":""}
                ,{ "data": "BUSINESS_UNIT", "defaultContent":""}
                ,{ "data": "OF_TRF", "defaultContent":""}
                ,{ "data": "SUBJECT_DESC_EN", "defaultContent":""}
                ,{ "data": "MANUAL_ACCOUNT", "defaultContent":""}
                ,{ "data": "INTERNAL", "defaultContent":""}
                ,{ "data": "SUBJECT_LEVEL", "defaultContent":""}
                ,{ "data": "CONTROL_SUBJECT", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#acSubject"),opt);
        $("#acSubject").beautyUi({
            tableId:"acSubject",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#acSubject tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#acSubject').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#acSubject').on('page.dt', function (e) {
                $('#acSubject').find("tr").removeClass('selected');
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
            var attrTab = $("#acSubject").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function ac_subject_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function ac_subject_mod(title,url,w,h){
    if ($("#acSubject").find(".selected").length!==1){
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
function  ac_subject_del(){
    if ($("#acSubject").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        acSubjectDel();
    });
}

function  acSubjectDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#acSubject').DataTable().rows(".selected").data()[0];
        paraJson.tableName="AC_SUBJECT";
        keyFieldsJson.SUBJECT_CODE=rowData.SUBJECT_CODE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_acSubjectDel,"json");
}

function  callback_acSubjectDel(json){
    if (json.success) {
        $("#acSubject").dataTable().api().row(".selected").remove().draw(false);
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
            ac_subject_contrast();
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
            ac_subject_add('添加','add/acSubjectAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            ac_subject_mod('修改','edit/acSubjectMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                ac_subject_del();
            });
        }
        $("#submit").on("click",function(){
            ac_subject_submit();
        });
        $("#contrast").on("click",function(){
            ac_subject_contrast();
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
            ac_subject_contrast();
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
            ac_subject_add('添加','add/acSubjectAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            ac_subject_mod('修改','edit/acSubjectMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            ac_subject_del();
        });
        $("#contrast").on("click",function(){
            ac_subject_contrast();
        });
        $("#submit").on("click",function(){
            ac_subject_submit();
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
function  ac_subject_contrast(){
    var attrTab = $("#acSubject").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=AC_SUBJECT").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function ac_subject_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"AC_SUBJECT",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_acSubjectSubmit,"json");
    });
}

function  callback_acSubjectSubmit(json){
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
  	var prodTab = $("#acSubject").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#acSubject").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=AC_SUBJECT").load();
}