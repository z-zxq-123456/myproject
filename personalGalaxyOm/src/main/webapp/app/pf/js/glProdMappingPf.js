var selectIs=0;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdMapping"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
   if(parent.$("#operateType").val() == "add"){
      opt.ajax= {
               "url": contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "update"){
      opt.ajax= {
               "url": contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "copy"){
      opt.ajax= {
              "url": contextPath+"/glProdMapping/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val(),
              "type": "POST"
          };
    }
    opt.columns=[
			{ "data": "mappingType", "defaultContent":""},
			{ "data": "prodType", "defaultContent":""},
			{ "data": "mappingDesc", "defaultContent":""},
			{ "data": "prodDesc", "defaultContent":""},
	];
     $(".select2").select2();
	//渲染tables
	drawDataTable($("#glProdMapping"),opt);
	dataTableUi("glProdMapping",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);
     	$('#glProdMapping tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdMapping').find("tr").removeClass('selected');
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
                 gl_prod_mapping_add('添加', 'glProdMappingAddPf.jsp', '600', '350');
             });
             $("#edit").on("click", function () {
                 gl_prod_mapping_mod('修改', 'glProdMappingModPf.jsp', '600', '350');
             });
             $("#delete").on("click", function () {
                 gl_prod_mapping_del();
             });
             }else if(parent.$("#operateType").val() == "add" || parent.$("#operateType").val() == "copy"){
            $("#reference").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_mapping_addN('添加', 'glProdMappingAddPf.jsp', '600', '350');
            });
            $("#edit").on("click", function () {
                gl_prod_mapping_modN('修改', 'glProdMappingModPf.jsp', '600', '350');
            });
            $("#delete").on("click", function () {
                gl_prod_mapping_delN();
            });
           }
   });
 var layer_add_index, layer_edit_index;
/*增加*/
function gl_prod_mapping_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){
                         selectNewAll_refresh();
                         }
                 });
}
function gl_prod_mapping_add(title,url,w,h){
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
function gl_prod_mapping_modN(title,url,w,h){
       if ($("#glProdMapping").find(".selected").length!=1){
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
function gl_prod_mapping_mod(title,url,w,h){
       if ($("#glProdMapping").find(".selected").length!=1){
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
function  gl_prod_mapping_del(){
     if ($("#glProdMapping").find(".selected").length!=1){
                 showMsg('请选择一行记录！','warning');
                 return;
      }
     layer.confirm('确认要删除吗？',function(){
                  glProdMappingDel();
                 });
 }
 function  gl_prod_mapping_delN(){
     if ($("#glProdMapping").find(".selected").length!=1){
                 showMsg('请选择一行记录！','warning');
                 return;
      }
     layer.confirm('确认要删除吗？',function(){
                  glProdMappingDelN();
                 });
 }

 function glProdMappingDel(){
         var url = contextPath+"/glProdMapping/delete1";
         var rowData = $('#glProdMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdMappingDel,"json");                //将获取数据发送给后台处理
     }
 function glProdMappingDelN(){
         var url = contextPath+"/glProdMapping/delete1";
         var rowData = $('#glProdMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdMappingDelN,"json");                //将获取数据发送给后台处理
     }
 function callback_glProdMappingDel(json){
      if (json.retStatus == 'F'){
          showMsg(json.retMsg,'info');
      } else if(json.retStatus == 'S'){
          showMsg(json.retMsg,'info');
      }
      selectAll_refresh();
  }
   function callback_glProdMappingDelN(json){
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
  	var prodTab = $("#glProdMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#prodType").val()).load();

}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#newProdType").val()).load();
}

