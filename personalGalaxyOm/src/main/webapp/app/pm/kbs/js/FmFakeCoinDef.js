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
    	var opt = getDataTableOpt($("#fmFakeCoinDef"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"FM_FAKE_COIN_DEF",
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
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "BONDNOTES", "defaultContent":""}
                ,{ "data": "BONDNUMBER", "defaultContent":""}
                ,{ "data": "BONDTYPEID", "defaultContent":""}
                ,{ "data": "BONDVERSIONNUM", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "BONDNAME", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#fmFakeCoinDef"),opt);
        $("#fmFakeCoinDef").beautyUi({
            tableId:"fmFakeCoinDef",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#fmFakeCoinDef tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#fmFakeCoinDef').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#fmFakeCoinDef').on('page.dt', function (e) {
                $('#fmFakeCoinDef').find("tr").removeClass('selected');
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
            var attrTab = $("#fmFakeCoinDef").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=&colV1="+$("#").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
        }
    });
});

/*增加*/
function fm_fake_coin_def_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function fm_fake_coin_def_mod(title,url,w,h){
    if ($("#fmFakeCoinDef").find(".selected").length!==1){
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
function  fm_fake_coin_def_del(){
    if ($("#fmFakeCoinDef").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        fmFakeCoinDefDel();
    });
}

function  fmFakeCoinDefDel(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#fmFakeCoinDef').DataTable().rows(".selected").data()[0];
        paraJson.tableName="FM_FAKE_COIN_DEF";
        keyFieldsJson.BONDNOTES=rowData.BONDNOTES;
        keyFieldsJson.BONDNUMBER=rowData.BONDNUMBER;
        keyFieldsJson.BONDTYPEID=rowData.BONDTYPEID;
        keyFieldsJson.BONDVERSIONNUM=rowData.BONDVERSIONNUM;
        keyFieldsJson.CCY=rowData.CCY;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_fmFakeCoinDefDel,"json");
}

function  callback_fmFakeCoinDefDel(json){
    if (json.success) {
        $("#fmFakeCoinDef").dataTable().api().row(".selected").remove().draw(false);
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
            fm_fake_coin_def_contrast();
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
            fm_fake_coin_def_add('添加','add/fmFakeCoinDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_fake_coin_def_mod('修改','edit/fmFakeCoinDefMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                fm_fake_coin_def_del();
            });
        }
        $("#submit").on("click",function(){
            fm_fake_coin_def_submit();
        });
        $("#contrast").on("click",function(){
            fm_fake_coin_def_contrast();
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
            fm_fake_coin_def_contrast();
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
            fm_fake_coin_def_add('添加','add/fmFakeCoinDefAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            fm_fake_coin_def_mod('修改','edit/fmFakeCoinDefMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            fm_fake_coin_def_del();
        });
        $("#contrast").on("click",function(){
            fm_fake_coin_def_contrast();
        });
        $("#submit").on("click",function(){
            fm_fake_coin_def_submit();
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
function  fm_fake_coin_def_contrast(){
    var attrTab = $("#fmFakeCoinDef").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=FM_FAKE_COIN_DEF").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function fm_fake_coin_def_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"FM_FAKE_COIN_DEF",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_fmFakeCoinDefSubmit,"json");
    });
}

function  callback_fmFakeCoinDefSubmit(json){
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
  	var prodTab = $("#fmFakeCoinDef").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#fmFakeCoinDef").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=FM_FAKE_COIN_DEF").load();
}