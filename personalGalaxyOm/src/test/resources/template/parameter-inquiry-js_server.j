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
    	var opt = getDataTableServerSideOpt($("#${beanName}"));
    	opt.stateSave=true;
    	opt.processing=true;
    <#if  (fixWidth == 'Y')>
        opt.scrollX=true;
    <#else>
        opt.autoWidth=false;
    </#if>
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"${TableName}",
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
    <#list insertCol as insert>
        <#if  (insert.valueMethod == 'YN' )>
                ,{
                    "width":"100px",
                    "targets":${insert.colIndex},
                    "render":function( data, type, row ) {
                        return row.${insert.colName} === "Y"?"是":"否";
                    }
                }
        <#else>
                ,{
                    "width":"100px",
                    "targets":${insert.colIndex}
                }
        </#if>
    </#list>
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
            <#list insertCol as insert>
                ,{ "data": "${insert.colName}", "defaultContent":""}
            </#list>
            ];
    	//渲染tables
    	drawDataTableServerSide($("#${beanName}"),opt,10);
        $("#${beanName}").beautyUi({
            tableId:"${beanName}",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ],
            serverSide:true
            });
        $('#${beanName} tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#${beanName}').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#${beanName}').on('page.dt', function (e) {
                $('#${beanName}').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
            });
<#list insertCol as insert>
<#if (insert.YnSelect == "Y")>
    <#if insert.valueMethod == 'RF'>
        getPkList({
            url: contextPath + "/baseCommon/pklistBase?tableName=${insert.refTable}&tableCol=${insert.refCol}",
            id: "${insert.colName}",
            async: false
        });
    </#if>
</#if>
</#list>
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
        if(1===1
    <#if selectData.SELECT1!="">
        &&$("#${selectData.SELECT1}").val()==""
    </#if>
    <#if selectData.SELECT2!="">
        &&$("#${selectData.SELECT2}").val()==""
    </#if>
    <#if selectData.SELECT3!="">
        &&$("#${selectData.SELECT3}").val()==""
    </#if>)
        {
            initData_refresh();
        }else{
            var attrTab = $("#${beanName}").dataTable();
            var api = attrTab.api();
            api.ajax.url(contextPath+"/baseCommon/selectBase?col1=${selectData.SELECT1}&colV1="+$("#${selectData.SELECT1}").val()+"&col2=${selectData.SELECT2}&colV2="+$("#${selectData.SELECT2}").val()+"&col3=${selectData.SELECT3}&"+"colV3="+$("#${selectData.SELECT3}").val()   ).load();
        }
    });
});

/*增加*/
function ${tableName}_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function ${tableName}_mod(title,url,w,h){
    if ($("#${beanName}").find(".selected").length!==1){
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
function  ${tableName}_del(){
    if ($("#${beanName}").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        ${beanName}Del();
    });
}

function  ${beanName}Del(){
        var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        var url = contextPath+"/baseCommon/updateForDelete";
        var rowData = $('#${beanName}').DataTable().rows(".selected").data()[0];
        paraJson.tableName="${TableName}";
    <#list pks as pk>
        keyFieldsJson.${pk.colName}=rowData.${pk.colName};
    </#list>
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_${beanName}Del,"json");
}

function  callback_${beanName}Del(json){
    if (json.success) {
        $("#${beanName}").dataTable().api().row(".selected").remove().draw(false);
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
            ${tableName}_contrast();
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
            ${tableName}_add('添加','add/${beanName}Add.jsp','600','520');
        });
        $("#edit").on("click",function(){
            ${tableName}_mod('修改','edit/${beanName}Mod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                ${tableName}_del();
            });
        }
        $("#submit").on("click",function(){
            ${tableName}_submit();
        });
        $("#contrast").on("click",function(){
            ${tableName}_contrast();
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
            ${tableName}_contrast();
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
            ${tableName}_add('添加','add/${beanName}Add.jsp','600','520');
        });
        $("#edit").on("click",function(){
            ${tableName}_mod('修改','edit/${beanName}Mod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            ${tableName}_del();
        });
        $("#contrast").on("click",function(){
            ${tableName}_contrast();
        });
        $("#submit").on("click",function(){
            ${tableName}_submit();
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
function  ${tableName}_contrast(){
    var attrTab = $("#${beanName}").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=${TableName}").load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
}

function ${tableName}_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            "tableName":"${TableName}",
            "reqNum": parent.$(".breadcrumb").data("reqNum")
            }, callback_${beanName}Submit,"json");
    });
}

function  callback_${beanName}Submit(json){
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
  	var prodTab = $("#${beanName}").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#${beanName}").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=${TableName}").load();
}