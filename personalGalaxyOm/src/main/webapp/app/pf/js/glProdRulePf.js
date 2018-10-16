var selectIs=0;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdRule"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
   if(parent.$("#operateType").val() == "add"){
      opt.ajax= {
               "url": contextPath+"/glProdRule/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "update"){
      opt.ajax= {
               "url": contextPath+"/glProdRule/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "copy"){
      opt.ajax= {
              "url": contextPath+"/glProdRule/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val(),
              "type": "POST"
          };
    }
    opt.columns=[
			{ "data": "prodType", "defaultContent":""},
			{ "data": "sysName", "defaultContent":""},
			{ "data": "sourceType", "defaultContent":""},
			{ "data": "clientType", "defaultContent":""},
			{ "data": "accountingStatus", "defaultContent":""},
			{ "data": "tranEventType", "defaultContent":""},
			{ "data": "accountingNo", "defaultContent":""},
			{ "data": "ccy", "defaultContent":""},
			{ "data": "customRule", "defaultContent":""},
			{ "data": "accountingDesc", "defaultContent":""},
			{ "data": "tranType", "defaultContent":""},
	];
         $(".select2").select2();
	//渲染tables
	drawDataTable($("#glProdRule"),opt);
	dataTableUi("glProdRule",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);
     	$('#glProdRule tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdRule').find("tr").removeClass('selected');
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
                 gl_prod_rule_add('添加', 'glProdRuleAddPf.jsp', '600', '520');
             });
             $("#edit").on("click", function () {
                 gl_prod_rule_mod('修改', 'glProdRuleModPf.jsp', '600', '520');
             });
             $("#delete").on("click", function () {
                 gl_prod_rule_del();
             });
             }else if(parent.$("#operateType").val() == "add" || parent.$("#operateType").val() == "copy"){
            $("#reference").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_rule_addN('添加', 'glProdRuleAddPf.jsp', '600', '520');
            });
            $("#edit").on("click", function () {
                gl_prod_rule_modN('修改', 'glProdRuleModPf.jsp', '600', '520');
            });
            $("#delete").on("click", function () {
                gl_prod_rule_delN();
            });
           }
   });
    var layer_add_index, layer_edit_index;
/*增加*/
function gl_prod_rule_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                  end: function(){
                          selectNewAll_refresh();
                          }
                 });
}
function gl_prod_rule_add(title,url,w,h){
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
function gl_prod_rule_modN(title,url,w,h){
       if ($("#glProdRule").find(".selected").length!=1){
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
function gl_prod_rule_mod(title,url,w,h){
       if ($("#glProdRule").find(".selected").length!=1){
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
function  gl_prod_rule_del(){
    if ($("#glProdRule").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdRuleDel();
                });
}
function  gl_prod_rule_delN(){
    if ($("#glProdRule").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdRuleDelN();
                });
}
 function glProdRuleDelN(){
         var url = contextPath+"/glProdRule/delete1";
         var rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdRuleDelN,"json");                //将获取数据发送给后台处理
     }
 function glProdRuleDel(){
         var url = contextPath+"/glProdRule/delete1";
         var rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdRuleDel,"json");                //将获取数据发送给后台处理
     }
 function callback_glProdRuleDel(json){
      if (json.retStatus == 'F'){
          showMsg(json.retMsg,'info');
      } else if(json.retStatus == 'S'){
          showMsg(json.retMsg,'info');
      }
      selectAll_refresh();
  }
   function callback_glProdRuleDelN(json){
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
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdRule/getAll?prodType=" + parent.$("#prodType").val()).load();
}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdRule/getAll?prodType=" + parent.$("#newProdType").val()).load();
}
