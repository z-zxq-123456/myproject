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
    	var opt = getDataTableOpt($("#cmcCardNoRoleInfo"));
    	opt.stateSave=true;
    	opt.processing=true;
    	opt.deferRender=true;
    	opt.scrollX=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CMC_CARD_NO_ROLE_INFO",
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
                "width":"60px",
                "targets":1
            }
            ,{
                "width":"60px",
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
                "width":"150px",
                "targets":5
            }
            ,{
                "width":"110px",
                "targets":6
            }
            ,{
                "width":"110px",
                "targets":7
            }
            ,{
                "width":"80px",
                "targets":8
            }
            ,{
                "width":"110px",
                "targets":9
            }
            ,{
                "width":"80px",
                "targets":10
            }
            ,{
                "width":"150px",
                "targets":11
            }
            ,{
            	"width":"110px",
                "targets":12
            }
            ,{
            	"width":"110px",
                "targets":13
            }
            ,{
            	"width":"110px",
                "targets":14
            }
            /*,{
                "targets": [ 14 ],
                "visible": false
            }
            ,{
                "targets": [ 15 ],
                "visible": false
            }
            ,{
                "targets": [ 16 ],
                "visible": false
            }
            ,{
                "targets": [ 17 ],
                "visible": false
            }
            ,{
                "targets": [ 18 ],
                "visible": false
            }
            ,{
                "targets": [ 19 ],
                "visible": false
            }
            ,{
                "targets": [ 20 ],
                "visible": false
            }
            ,{
                "targets": [ 21 ],
                "visible": false
            }
            ,{
                "targets": [ 22 ],
                "visible": false
            }
            ,{
                "targets": [ 23 ],
                "visible": false
            }
            ,{
                "targets": [ 24 ],
                "visible": false
            }
            ,{
                "targets": [ 25 ],
                "visible": false
            }
            ,{
                "targets": [ 26 ],
                "visible": false
            }
            ,{
                "targets": [ 27 ],
                "visible": false
            }
            ,{
                "targets": [ 28 ],
                "visible": false
            }
            ,{
                "targets": [ 29 ],
                "visible": false
            }
            ,{
                "targets": [ 30 ],
                "visible": false
            }
            ,{
                "targets": [ 31 ],
                "visible": false
            }
            ,{
                "targets": [ 32 ],
                "visible": false
            }
            ,{
                "targets": [ 33 ],
                "visible": false
            }
            ,{
                "targets": [ 34 ],
                "visible": false
            }
            ,{
                "targets": [ 35 ],
                "visible": false
            }
            ,{
                "targets": [ 36 ],
                "visible": false
            }
            ,{
                "targets": [ 37 ],
                "visible": false
            }
            ,{
                "targets": [ 38 ],
                "visible": false
            }
            ,{
                "targets": [ 39 ],
                "visible": false
            }
            ,{
                "targets": [ 40 ],
                "visible": false
            }
            ,{
                "targets": [ 41 ],
                "visible": false
            }
            ,{
                "targets": [ 42 ],
                "visible": false
            }
            ,{
                "targets": [ 43 ],
                "visible": false
            }
            ,{
                "targets": [ 44 ],
                "visible": false
            }
            ,{
                "targets": [ 45 ],
                "visible": false
            }
            ,{
                "targets": [ 46 ],
                "visible": false
            }
            ,{
                "targets": [ 47 ],
                "visible": false
            }
            ,{
                "targets": [ 48 ],
                "visible": false
            }
            ,{
                "targets": [ 49 ],
                "visible": false
            }*/
                ];

        opt.columns=[
             {"data": "OPERATE_TYPE", "defaultContent": ""}
            ,{"data": "CARD_NO_ROLE_CODE", "defaultContent": ""}
            ,{ "data": "LEN_OF_CARD_NO", "defaultContent":""}
            ,{ "data": "CARD_GEN_ID", "defaultContent":""}
            ,{ "data": "SEQ_NO_LEN", "defaultContent":""}
            ,{ "data": "FLDNUM1", "defaultContent":""}
            ,{ "data": "FLDSRCTBL1", "defaultContent":""}
            ,{ "data": "FLDSRCFLD1", "defaultContent":""}
            ,{ "data": "FLDLEN1", "defaultContent":""}
            ,{ "data": "FLDWHR1", "defaultContent":""}
            ,{ "data": "FLDSIGN1", "defaultContent":""}
            /*,{ "data": "FLDSRCTBL2", "defaultContent":""}
            ,{ "data": "FLDSRCFLD2", "defaultContent":""}
            ,{ "data": "FLDLEN2", "defaultContent":""}
            ,{ "data": "FLDWHR2", "defaultContent":""}
            ,{ "data": "FLDSIGN2", "defaultContent":""}
            ,{ "data": "FLDSRCTBL3", "defaultContent":""}
            ,{ "data": "FLDSRCFLD3", "defaultContent":""}
            ,{ "data": "FLDLEN3", "defaultContent":""}
            ,{ "data": "FLDWHR3", "defaultContent":""}
            ,{ "data": "FLDSIGN3", "defaultContent":""}
            ,{ "data": "FLDSRCTBL4", "defaultContent":""}
            ,{ "data": "FLDSRCFLD4", "defaultContent":""}
            ,{ "data": "FLDLEN4", "defaultContent":""}
            ,{ "data": "FLDWHR4", "defaultContent":""}
            ,{ "data": "FLDSIGN4", "defaultContent":""}
            ,{ "data": "FLDSRCTBL5", "defaultContent":""}
            ,{ "data": "FLDSRCFLD5", "defaultContent":""}
            ,{ "data": "FLDLEN5", "defaultContent":""}
            ,{ "data": "FLDWHR5", "defaultContent":""}
            ,{ "data": "FLDSIGN5", "defaultContent":""}*/
            ,{ "data": "FLDNUM2", "defaultContent":""}
           /* ,{ "data": "FLDSRCTBL6", "defaultContent":""}
            ,{ "data": "FLDSRCFLD6", "defaultContent":""}
            ,{ "data": "FLDLEN6", "defaultContent":""}
            ,{ "data": "FLDWHR6", "defaultContent":""}*/
            ,{ "data": "FLDSIGN6", "defaultContent":""}
            /*,{ "data": "FLDSRCTBL7", "defaultContent":""}
            ,{ "data": "FLDSRCFLD7", "defaultContent":""}
            ,{ "data": "FLDLEN7", "defaultContent":""}
            ,{ "data": "FLDWHR7", "defaultContent":""}
            ,{ "data": "FLDSIGN7", "defaultContent":""}
            ,{ "data": "FLDSRCTBL8", "defaultContent":""}
            ,{ "data": "FLDSRCFLD8", "defaultContent":""}
            ,{ "data": "FLDLEN8", "defaultContent":""}
            ,{ "data": "FLDWHR8", "defaultContent":""}
            ,{ "data": "FLDSIGN8", "defaultContent":""}*/
            ,{ "data": "VRY_BIT_METH", "defaultContent":""}
            ,{ "data": "VRY_BIT_LEN", "defaultContent":""}
//            ,{ "data": "VRY_GEN_FUNC", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#cmcCardNoRoleInfo"),opt);
        $("#cmcCardNoRoleInfo").beautyUi({
            tableId:"cmcCardNoRoleInfo",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cmcCardNoRoleInfo tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cmcCardNoRoleInfo').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#cmcCardNoRoleInfo').on('page.dt', function (e) {
                $('#cmcCardNoRoleInfo').find("tr").removeClass('selected');
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
            var attrTab = $("#cmcCardNoRoleInfo").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function cmc_card_no_role_info_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_card_no_role_info_mod(title,url,w,h){
    if ($("#cmcCardNoRoleInfo").find(".selected").length!==1){
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
function  cmc_card_no_role_info_del(){
    if ($("#cmcCardNoRoleInfo").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcCardNoRoleInfoDel();
    });
}

function  cmcCardNoRoleInfoDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#cmcCardNoRoleInfo').DataTable().rows(".selected").data()[0];
        paraJson.tableName="CMC_CARD_NO_ROLE_INFO";
        keyFieldsJson.CARD_NO_ROLE_CODE=rowData.CARD_NO_ROLE_CODE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_cmcCardNoRoleInfoDel,"json");
}

function  callback_cmcCardNoRoleInfoDel(json){
    if (json.success) {
        $("#cmcCardNoRoleInfo").dataTable().api().row(".selected").remove().draw(false);
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
            cmc_card_no_role_info_contrast();
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
            cmc_card_no_role_info_add('添加','add/cmcCardNoRoleInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_info_mod('修改','edit/cmcCardNoRoleInfoMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cmc_card_no_role_info_del();
            });
        }
        $("#submit").on("click",function(){
            cmc_card_no_role_info_submit();
        });
        $("#contrast").on("click",function(){
            cmc_card_no_role_info_contrast();
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
            cmc_card_no_role_info_contrast();
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
            cmc_card_no_role_info_add('添加','add/cmcCardNoRoleInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_info_mod('修改','edit/cmcCardNoRoleInfoMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            cmc_card_no_role_info_del();
        });
        $("#contrast").on("click",function(){
            cmc_card_no_role_info_contrast();
        });
        $("#submit").on("click",function(){
            cmc_card_no_role_info_submit();
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
function  cmc_card_no_role_info_contrast(){
    var attrTab = $("#cmcCardNoRoleInfo").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CMC_CARD_NO_ROLE_INFO").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function cmc_card_no_role_info_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"CMC_CARD_NO_ROLE_INFO",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_cmcCardNoRoleInfoSubmit,"json");
    });
}

function  callback_cmcCardNoRoleInfoSubmit(json){
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
  	var prodTab = $("#cmcCardNoRoleInfo").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cmcCardNoRoleInfo").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CMC_CARD_NO_ROLE_INFO").load();
}