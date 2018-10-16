var selectIs=0;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdAccounting"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.scrollX = true;
	//判断是单表操作或者产品工厂操作
    if(parent.$("#operateType").val() === "add"){
      opt.ajax= {
               "url": contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() === "update"){
      opt.ajax= {
               "url": contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() === "copy"){
            if(parent.glProdAccountingData===true){
                                       opt.ajax= {
                                                     "url": contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#prodType").val(),
                                                     "type": "POST"
                                                 };
                                       opt.columnDefs=[
                                               {
                                                            "width":"100px",
                                                            "targets":[ 0 ],
                                                            "render":function(data ,type ,row){
                                                                    return parent.$("#newProdType").val();
                                                             }
                                                 }
                                       ];
                }else{
                  opt.ajax= {
                  "url": contextPath+"/glProdAccounting/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val()+"&reqNum="+parent.$(".breadcrumb").data("reqNum"),
                  "type": "POST"
                   };
                   parent.glProdAccountingData=true;
                }
    }
       opt.columns=[
    			{ "data": "prodType", "defaultContent":""},
    			{ "data": "accountingStatus", "defaultContent":""},
    			{ "data": "profitCentre", "defaultContent":""},
    			{ "data": "businessUnit", "defaultContent":""},
    			{ "data": "glCodeA", "defaultContent":""},
    			{ "data": "glCodeL", "defaultContent":""},
    			{ "data": "glCodeIntE", "defaultContent":""},
    			{ "data": "glCodeIntPay", "defaultContent":""},
    			{ "data": "glCodeIntI", "defaultContent":""},
    			{ "data": "glCodeIntRec", "defaultContent":""},
    			{ "data": "glCodeIntAcr", "defaultContent":""},
    			{ "data": "glCodeOdpI", "defaultContent":""},
    			{ "data": "glCodeOdpRec", "defaultContent":""},
    			{ "data": "glCOdeOdpAcr", "defaultContent":""},
    			{ "data": "glCodeOdiI", "defaultContent":""},
    			{ "data": "glCodeOdiRec", "defaultContent":""},
    			{ "data": "glCodeOdiAcr", "defaultContent":""},
    			{ "data": "glCodeALoss", "defaultContent":""},
                { "data": "glCodeAdjust", "defaultContent":""}
    	];
    $(".select2").select2();
	//渲染tables
	  opt.order = [[0, 'asc']];
	drawDataTable($("#glProdAccounting"),opt);
	dataTableUi("glProdAccounting",["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],["add",　"edit","delete","submit","contrast","refresh","return" ]);

     	$('#glProdAccounting tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdAccounting').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
         });
            if(parent.$(".breadcrumb").data("reqNum")!==null){
                         $("#return").hide();
                         $("#contrast").hide();
                         $("#submit").hide();
             if(parent.$("#operateType").val() === "update"){
             $("#refresh").on("click", function () {
                 selectAll_refresh();
             });
             $("#add").on("click", function () {
                 gl_prod_accounting_add('添加', 'add/glProdAccountingAddPf.jsp', '600', '520');
             });
             $("#edit").on("click", function () {
                 gl_prod_accounting_mod('修改', 'edit/glProdAccountingModPf.jsp', '600', '520');
             });
             $("#delete").on("click", function () {
                 gl_prod_accounting_del();
             });
             }else if(parent.$("#operateType").val() === "add" || parent.$("#operateType").val() === "copy"){
            $("#refresh").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_accounting_addN('添加', 'add/glProdAccountingAddPf.jsp', '600', '520');
            });
            $("#edit").on("click", function () {
                gl_prod_accounting_modN('修改', 'edit/glProdAccountingModPf.jsp', '600', '520');
            });
            $("#delete").on("click", function () {
                gl_prod_accounting_delN();
            });
           }
           }else{
               $('#glProdAccounting').on('page.dt', function (e) {
                           $('#glProdAccounting').find("tr").removeClass('selected');
                       });
                   $("#refresh").on("click",function(){
                       selectAll_refresh();
                   });
                   getPkList({
                   url: contextPath + "/baseCommon/pklistBase?tableName=GL_PROD_MAPPING&tableCol=PROD_TYPE,PROD_DESC",
                   id: "PROD_TYPE",
                   async: false
                   });
                   $(".select2").select2();
                   buttonStatus();
               $("#selectByPrimaryKey").click(function(){
                   if($("#PROD_TYPE").val()==="" &&$("#ACCOUNTING_STATUS").val()==="")
                   {
                       initData_refresh();
                   }else{
                       var attrTab = $("#glProdAccounting").dataTable();
                       var api = attrTab.api();
                       api.ajax.url(contextPath+"/baseCommon/selectBase?col1=PROD_TYPE&colV1="+$("#PROD_TYPE").val()+"&col2=ACCOUNTING_STATUS&colV2="+$("#ACCOUNTING_STATUS").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
                   }
               });

           }
   });

var layer_add_index, layer_edit_index;
/*增加*/
function gl_prod_accounting_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){

                         }
                 });
}
function gl_prod_accounting_add(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){

                         }
                 });
}
/*修改*/
function gl_prod_accounting_modN(title,url,w,h){
        if ($("#glProdAccounting").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
        }
        layer_edit_index = layer.open({
                    type: 2,
                    content: url,
                    area: [w+'px', h+'px'],
                     end: function(){
                   
                             }
                    });
 }
 function gl_prod_accounting_mod(title,url,w,h){
        if ($("#glProdAccounting").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
        }
        layer_edit_index = layer.open({
                    type: 2,
                    content: url,
                    area: [w+'px', h+'px'],
                     end: function(){

                             }
                    });
 }
/*删除*/
function  gl_prod_accounting_del(){
    if ($("#glProdAccounting").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdAccountingDel();
                });
}
function  gl_prod_accounting_delN(){
    if ($("#glProdAccounting").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdAccountingDelN();
                });
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
            gl_prod_accounting_contrast();
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
            gl_prod_accounting_add('添加','add/glProdAccountingAddPf.jsp','600','520');
        });
        $("#edit").on("click",function(){
            gl_prod_accounting_mod('修改','edit/glProdAccountingModPf.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                gl_prod_accounting_del();
            });
        }
        $("#submit").on("click",function(){
            gl_prod_accounting_submit();
        });
        $("#contrast").on("click",function(){
            gl_prod_accounting_contrast();
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
            gl_prod_accounting_contrast();
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
    else{
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").hide();
    }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}
/*查看差异数据*/
function  gl_prod_accounting_contrast(){
    var attrTab = $("#glProdAccounting").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=GL_PROD_ACCOUNTING" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}
function gl_prod_accounting_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"GL_PROD_ACCOUNTING"
            }, callback_glProdAccountingSubmit,"json");
    });
}
function  callback_glProdAccountingSubmit(json){
    if (json.success) {
        parent.afterSelectPara("view","提交成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }

}
function glProdAccountingDel(){
   var url;
   var rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
   if(parent.$(".breadcrumb").data("reqNum")!==null){
        url = contextPath+"/glProdAccounting/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
        sendPostRequest(url,rowData	, callback_glProdAccountingDel,"json");                //将获取数据发送给后台处理
   }else{
       var paraJson,keyFieldsJson;
         paraJson={};
         keyFieldsJson={};
         url = contextPath+"/baseCommon/updateForDelete";
         rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
         paraJson.tableName="GL_PROD_ACCOUNTING";
             keyFieldsJson.PROD_TYPE=rowData.PROD_TYPE;
             keyFieldsJson.ACCT_STATUS=rowData.ACCT_STATUS;
         paraJson.key = keyFieldsJson;
         paraJson.status=rowData.COLUMN_STATUS;
         paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
         paraJson.status=rowData.COLUMN_STATUS;
         var params = {
             paraJson:JSON.stringify(paraJson)
         };
         sendPostRequest(url,params, callback_glProdAccountingDel,"json");
   }
    }
function glProdAccountingDelN(){
        var url = contextPath+"/glProdAccounting/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
        var rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url,rowData	, callback_glProdAccountingDelN,"json");                //将获取数据发送给后台处理
    }

function callback_glProdAccountingDel(json){
 if(parent.$(".breadcrumb").data("reqNum")!==null){
     if (json.retStatus === 'F'){
         showMsg(json.retMsg,'info');
     } else if(json.retStatus === 'S'){
        $('#glProdAccounting').dataTable().api().row(".selected").remove().draw(false);
         showMsg(json.retMsg,'info');
     }
  }else{
  if (json.success) {
          $("#glProdAccounting").dataTable().api().row(".selected").remove().draw(false);
          showMsgDuringTime("删除成功！");
      } else if (json.errorMsg) {
          showMsg(json.errorMsg, 'errorMsg');
      }
      }
 }
 function callback_glProdAccountingDelN(json){
     if (json.retStatus === 'F'){
         showMsg(json.retMsg,'info');
     } else if(json.retStatus === 'S'){
             $('#glProdAccounting').dataTable().api().row(".selected").remove().draw(false);
         showMsg(json.retMsg,'info');
     }
     selectNewAll_refresh();
 }
 function showMsgDuringTime(msg)
 {
     layer.msg(msg);
     setTimeout(function(){
         layer.closeAll('dialog');
     }, 1000);
     if(msg==="添加成功") {
         layer.close(layer_add_index);
     }
     if(msg==="修改成功") {
         layer.close(layer_edit_index);
     }
 }
function selectAll_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
      if(parent.$(".breadcrumb").data("reqNum")!==null){
    api.ajax.url(contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#prodType").val()).load();
      }else{
        api.ajax.reload();
      }
}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#newProdType").val()).load();
}
function initData_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=GL_PROD_ACCOUNTING").load();
}