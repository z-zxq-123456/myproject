var selectIs=0;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdAccounting"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.scrollX = true;

    if(parent.$("#operateType").val() == "add"){
      opt.ajax= {
               "url": contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "update"){
      opt.ajax= {
               "url": contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "copy"){
      opt.ajax= {
              "url": contextPath+"/glProdAccounting/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val(),
              "type": "POST"
          };
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
	];
    $(".select2").select2();
	//渲染tables
	drawDataTable($("#glProdAccounting"),opt);
	dataTableUi("glProdAccounting",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);

     	$('#glProdAccounting tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdAccounting').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
         });
             $("#nullify").hide();
             $("#reBack").hide();
             $("#contrast").hide();

             if(parent.$("#operateType").val() == "update"){
             $("#reference").on("click", function () {
                 selectAll_refresh();
             });
             $("#add").on("click", function () {
                 gl_prod_accounting_add('添加', 'glProdAccountingAddPf.jsp', '600', '520');
             });
             $("#edit").on("click", function () {
                 gl_prod_accounting_mod('修改', 'glProdAccountingModPf.jsp', '600', '520');
             });
             $("#delete").on("click", function () {
                 gl_prod_accounting_del();
             });
             }else if(parent.$("#operateType").val() == "add" || parent.$("#operateType").val() == "copy"){
            $("#reference").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_accounting_addN('添加', 'glProdAccountingAddPf.jsp', '600', '520');
            });
            $("#edit").on("click", function () {
                gl_prod_accounting_modN('修改', 'glProdAccountingModPf.jsp', '600', '520');
            });
            $("#delete").on("click", function () {
                gl_prod_accounting_delN();
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
                         selectNewAll_refresh();
                         }
                 });
}
function gl_prod_accounting_add(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){
                         selectAll_refresh();
                         }
                 });
}
/*修改*/
function gl_prod_accounting_modN(title,url,w,h){
        if ($("#glProdAccounting").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
        }
        layer_edit_index = layer.open({
                    type: 2,
                    content: url,
                    area: [w+'px', h+'px'],
                     end: function(){
                             selectNewAll_refresh();
                             }
                    });
 }
 function gl_prod_accounting_mod(title,url,w,h){
        if ($("#glProdAccounting").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
        }
        layer_edit_index = layer.open({
                    type: 2,
                    content: url,
                    area: [w+'px', h+'px'],
                     end: function(){
                             selectAll_refresh();
                             }
                    });
 }
/*删除*/
function  gl_prod_accounting_del(){
    if ($("#glProdAccounting").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdAccountingDel();
                });
}
function  gl_prod_accounting_delN(){
    if ($("#glProdAccounting").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdAccountingDelN();
                });
}
function glProdAccountingDel(){
        var url = contextPath+"/glProdAccounting/delete1";
        var rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url,rowData	, callback_glProdAccountingDel,"json");                //将获取数据发送给后台处理
    }
function glProdAccountingDelN(){
        var url = contextPath+"/glProdAccounting/delete1";
        var rowData = $('#glProdAccounting').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url,rowData	, callback_glProdAccountingDelN,"json");                //将获取数据发送给后台处理
    }

function callback_glProdAccountingDel(json){
     if (json.retStatus == 'F'){
         showMsg(json.retMsg,'info');
     } else if(json.retStatus == 'S'){
         showMsg(json.retMsg,'info');
     }
     selectAll_refresh();
 }
 function callback_glProdAccountingDelN(json){
     if (json.retStatus == 'F'){
         showMsg(json.retMsg,'info');
     } else if(json.retStatus == 'S'){
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
     if(msg=="添加成功") {
         layer.close(layer_add_index);
     }
     if(msg=="修改成功") {
         layer.close(layer_edit_index);
     }
 }
function selectAll_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#prodType").val()).load();
}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdAccounting").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdAccounting/getAll?prodType=" + parent.$("#newProdType").val()).load();
}
