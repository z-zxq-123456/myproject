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
    	var opt = getDataTableOpt($("#cifClientNatureDef"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"CIF_CLIENT_NATURE_DEF",
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
                ,{ "data": "CLIENT_NATURE", "defaultContent":""}
                ,{ "data": "LOSS_IND", "defaultContent":""}
                ,{ "data": "CLIENT_NATURE_DESC", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#cifClientNatureDef"),opt);
        $("#cifClientNatureDef").beautyUi({
            tableId:"cifClientNatureDef",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#cifClientNatureDef tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cifClientNatureDef').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#cifClientNatureDef').on('page.dt', function (e) {
                $('#cifClientNatureDef').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
        &&$("#CLIENT_NATURE").val()===""
        &&$("#LOSS_IND").val()===""
)
        {
            initData_refresh();
        }else{
            var attrTab = $("#cifClientNatureDef").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=CLIENT_NATURE&colV1="+$("#CLIENT_NATURE").val()+"&col2=LOSS_IND&colV2="+$("#LOSS_IND").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });

});

/*增加*/
function cif_client_nature_def_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cif_client_nature_def_mod(title,url,w,h){
    if ($("#cifClientNatureDef").find(".selected").length!==1){
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
function  cif_client_nature_def_del(){
    if ($("#cifClientNatureDef").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cifClientNatureDefDel();
    });
}

function  cifClientNatureDefDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#cifClientNatureDef').DataTable().rows(".selected").data()[0];
        paraJson.tableName="CIF_CLIENT_NATURE_DEF";
        keyFieldsJson.CLIENT_NATURE=rowData.CLIENT_NATURE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_cifClientNatureDefDel,"json");
}

function  callback_cifClientNatureDefDel(json){
    if (json.success) {
        $("#cifClientNatureDef").dataTable().api().row(".selected").remove().draw(false);
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
            cif_client_nature_def_contrast();
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
            cif_client_nature_def_add('添加','add/cifClientNatureDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cif_client_nature_def_mod('修改','edit/cifClientNatureDefMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cif_client_nature_def_del();
            });
        }
        $("#submit").on("click",function(){
            cif_client_nature_def_submit();
        });
        $("#contrast").on("click",function(){
            cif_client_nature_def_contrast();
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
            cif_client_nature_def_contrast();
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
   else if(parent.$(".breadcrumb").data("needButton") == "NoEdit" ){
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
               cif_client_nature_def_add('添加','add/cifClientNatureDefAdd.jsp','600','520');
           });
           $("#edit").on("click",function(){
               cif_client_nature_def_mod('修改','edit/cifClientNatureDefMod.jsp','600','520');
           });
           $("#delete").on("click",function(){
               cif_client_nature_def_del();
           });
           $("#contrast").on("click",function(){
               cif_client_nature_def_contrast();
           });
           $("#submit").on("click",function(){
               cif_client_nature_def_submit();
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
function  cif_client_nature_def_contrast(){
    var attrTab = $("#cifClientNatureDef").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CIF_CLIENT_NATURE_DEF" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function cif_client_nature_def_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"CIF_CLIENT_NATURE_DEF",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_cifClientNatureDefSubmit,"json");
    });
}

function  callback_cifClientNatureDefSubmit(json){
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
  	var prodTab = $("#cifClientNatureDef").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#cifClientNatureDef").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=CIF_CLIENT_NATURE_DEF").load();
}



