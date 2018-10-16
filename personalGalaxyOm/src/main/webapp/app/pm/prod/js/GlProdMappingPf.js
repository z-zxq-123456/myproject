var selectIs=0;
 var layer_add_index, layer_edit_index;
 function showMsgDuringTime(msg)
 {
     showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
     if(msg==="添加成功") {
         selectAll_refresh();
     }
 }
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdMapping"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
   if(parent.$("#operateType").val() === "add"){
      opt.ajax= {
               "url": contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() === "update"){
      opt.ajax= {
               "url": contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
     }else if(parent.$("#operateType").val() === "copy"){
                if(parent.glProdMappingData===true){
                                       opt.ajax= {
                                                     "url": contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#prodType").val(),
                                                     "type": "POST"
                                                 };

                }else{
                 opt.ajax= {
                  "url": contextPath+"/glProdMapping/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val()+"&newProdDesc="+parent.$("#newProdDesc").val()+"&reqNum="+parent.$(".breadcrumb").data("reqNum"),
                 "type": "POST"
                 };
                 parent.glProdMappingData=true;
                }
       opt.columnDefs=[
           {
               "width":"100px",
               "targets":[ 0 ],
               "render":function(data ,type ,row){
                   return parent.$("#newProdType").val();
               }
           },
           {
               "width":"100px",
               "targets":[ 1 ],
               "render":function(data ,type ,row){
                   return parent.$("#newProdType").val();
               }
           },
           {
               "width":"100px",
               "targets":[ 2 ],
               "render":function(data ,type ,row){
                   return parent.$("#newProdDesc").val();
               }
           },
           {
               "width":"100px",
               "targets":[ 3 ],
               "render":function(data ,type ,row){
                   return parent.$("#newProdDesc").val();
               }
           }
       ];
    }
    opt.columns=[
			{ "data": "mappingType", "defaultContent":""},
			{ "data": "prodType", "defaultContent":""},
			{ "data": "mappingDesc", "defaultContent":""},
			{ "data": "prodDesc", "defaultContent":""}
	];

     $(".select2").select2();
     	  opt.order = [[0, 'asc']];
	//渲染tables
	drawDataTable($("#glProdMapping"),opt);
	dataTableUi("glProdMapping",["添加", "修改", "删除", "提交", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "submit", "contrast", "refresh", "return"]);
     	$('#glProdMapping tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdMapping').find("tr").removeClass('selected');
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
                 gl_prod_mapping_add('添加', 'add/glProdMappingAddPf.jsp', '600', '350');
             });
             $("#edit").on("click", function () {
                 gl_prod_mapping_mod('修改', 'edit/glProdMappingModPf.jsp', '600', '350');
             });
             $("#delete").on("click", function () {
                 gl_prod_mapping_del();
             });
             }else if(parent.$("#operateType").val() === "add" || parent.$("#operateType").val() === "copy"){
            $("#refresh").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_mapping_addN('添加', 'add/glProdMappingAddPf.jsp', '600', '350');
            });
            $("#edit").on("click", function () {
                gl_prod_mapping_modN('修改', 'edit/glProdMappingModPf.jsp', '600', '350');
            });
            $("#delete").on("click", function () {
                gl_prod_mapping_delN();
            });
           }
           }else{
                   $('#glProdMapping').on('page.dt', function (e) {
                           $('#glProdMapping').find("tr").removeClass('selected');
                       });
              $("#refresh").on("click",function(){
                       selectAll_refresh();
                   });
                   getPkList({
                   url: contextPath + "/baseCommon/pklistBase?tableName=GL_PROD_MAPPING&tableCol=MAPPING_TYPE,MAPPING_DESC",
                   id: "MAPPING_TYPE",
                   async: false
                   });
                   $(".select2").select2();
                   buttonStatus();
               $("#selectByPrimaryKey").click(function(){
                   if($("#MAPPING_TYPE").val()==="")
                   {
                       initData_refresh();
                   }else{
                       var attrTab = $("#glProdMapping").dataTable();
                       var api = attrTab.api();
                       api.ajax.url(contextPath+"/baseCommon/selectBase?col1=MAPPING_TYPE&colV1="+$("#MAPPING_TYPE").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();
                   }
               });
           }
   });

/*增加*/
function gl_prod_mapping_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){

                         }
                 });
}
function gl_prod_mapping_add(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){
                         }
                 });
}
/*修改*/
function gl_prod_mapping_modN(title,url,w,h){
       if ($("#glProdMapping").find(".selected").length!==1){
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
function gl_prod_mapping_mod(title,url,w,h){
       if ($("#glProdMapping").find(".selected").length!==1){
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
function  gl_prod_mapping_del(){
     if ($("#glProdMapping").find(".selected").length!==1){
                 showMsg('请选择一行记录！','warning');
                 return;
      }
     layer.confirm('确认要删除吗？',function(){
                  glProdMappingDel();
                 });
 }
 function  gl_prod_mapping_delN(){
     if ($("#glProdMapping").find(".selected").length!==1){
                 showMsg('请选择一行记录！','warning');
                 return;
      }
     layer.confirm('确认要删除吗？',function(){
                  glProdMappingDelN();
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
            gl_prod_mapping_contrast();
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
            gl_prod_mapping_add('添加','add/glProdMappingAddPf.jsp','600','520');
        });
        $("#edit").on("click",function(){
            gl_prod_mapping_mod('修改','edit/glProdMappingModPf.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                gl_prod_mapping_del();
            });
        }
        $("#submit").on("click",function(){
            gl_prod_mapping_submit();
        });
        $("#contrast").on("click",function(){
            gl_prod_mapping_contrast();
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
            gl_prod_mapping_contrast();
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
function  gl_prod_mapping_contrast(){
    var attrTab = $("#glProdMapping").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=GL_PROD_MAPPING" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

 function glProdMappingDel(){
   var url;
    var rowData = $('#glProdMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
   if(parent.$(".breadcrumb").data("reqNum")!==null){
          url  = contextPath+"/glProdMapping/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
         sendPostRequest(url,rowData, callback_glProdMappingDel,"json");                //将获取数据发送给后台处理
    }else{
      var paraJson,keyFieldsJson;
        paraJson={};
        keyFieldsJson={};
        url = contextPath+"/baseCommon/updateForDelete";
        rowData = $('#glProdMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
        paraJson.tableName="GL_PROD_MAPPING";
            keyFieldsJson.MAPPING_TYPE=rowData.MAPPING_TYPE;
        paraJson.key = keyFieldsJson;
        paraJson.status=rowData.COLUMN_STATUS;
        paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
        paraJson.status=rowData.COLUMN_STATUS;
        var params = {
            paraJson:JSON.stringify(paraJson)
        };
        sendPostRequest(url,params, callback_glProdMappingDel,"json");                //将获取数据发送给后台处理
    }
     }
 function glProdMappingDelN(){
         var url = contextPath+"/glProdMapping/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
         var rowData = $('#glProdMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdMappingDelN,"json");                //将获取数据发送给后台处理
     }
 function callback_glProdMappingDel(json){
  if(parent.$(".breadcrumb").data("reqNum")!==null){
      if (json.retStatus === 'F'){
          showMsg(json.retMsg,'info');
      } else if(json.retStatus === 'S'){
       $('#glProdMapping').dataTable().api().row(".selected").remove().draw(false);
          showMsg(json.retMsg,'info');
      }
  }else{
     if (json.success) {
          $("#glProdMapping").dataTable().api().row(".selected").remove().draw(false);
          showMsgDuringTime("删除成功！");
      } else if (json.errorMsg) {
          showMsg(json.errorMsg, 'errorMsg');
      }
  }
  }
   function callback_glProdMappingDelN(json){
        if (json.retStatus === 'F'){
            showMsg(json.retMsg,'info');
        } else if(json.retStatus === 'S'){
         $('#glProdMapping').dataTable().api().row(".selected").remove().draw(false);
            showMsg(json.retMsg,'info');
        }
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
 function gl_prod_mapping_submit(){
     layer.confirm('确认要提交吗？',function(){
         var url = contextPath+"/baseCommon/submitParaData";
         sendPostRequest(url,{
             tableName:"GL_PROD_MAPPING"
             }, callback_glProdMappingSubmit,"json");
     });
 }

 function  callback_glProdMappingSubmit(json){
     if (json.success) {
         parent.afterSelectPara("view","提交成功");
     } else if (json.errorMsg) {
         showMsg(json.errorMsg, 'errorMsg');
     }

 }
function selectAll_refresh(){
  	var prodTab = $("#glProdMapping").dataTable();
    var api = prodTab.api();
          if(parent.parent.$(".breadcrumb").data("reqNum")!==null){
    api.ajax.url(contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#prodType").val()).load();
    }
    else{
       api.ajax.reload();
       }
}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdMapping/getAll?prodType=" + parent.$("#ProdType").val()).load();
}
function initData_refresh(){
  	var prodTab = $("#glProdMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=GL_PROD_MAPPING").load();
}



