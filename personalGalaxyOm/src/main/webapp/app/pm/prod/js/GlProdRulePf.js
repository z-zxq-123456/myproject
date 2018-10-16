var selectIs=0;
    var layer_add_index, layer_edit_index;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#glProdRule"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
   if(parent.$("#operateType").val() === "add"){
      opt.ajax= {
               "url": contextPath+"/glProdRule/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() === "update"){
      opt.ajax= {
               "url": contextPath+"/glProdRule/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() === "copy"){
                if(parent.glProdRuleData===true){
                                       opt.ajax= {
                                                     "url": contextPath+"/glProdRule/getAll?prodType=" + parent.$("#prodType").val(),
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
                "url": contextPath+"/glProdRule/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val()+"&reqNum="+parent.$(".breadcrumb").data("reqNum"),
                "type": "POST"
                 };
                 parent.glProdRuleData=true;
          }
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
			{ "data": "sourceModule", "defaultContent":""}
	];
         $(".select2").select2();
         	  opt.order = [[0, 'asc']];
	//渲染tables
	drawDataTable($("#glProdRule"),opt);
	dataTableUi("glProdRule",["添加", "修改", "删除", "提交", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "submit", "contrast", "refresh", "return"]);
     	$('#glProdRule tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#glProdRule').find("tr").removeClass('selected');
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
                 gl_prod_rule_add('添加', 'add/glProdRuleAddPf.jsp', '600', '520');
             });
             $("#edit").on("click", function () {
                 gl_prod_rule_mod('修改', 'edit/glProdRuleModPf.jsp', '600', '520');
             });
             $("#delete").on("click", function () {
                 gl_prod_rule_del();
             });
             }else if(parent.$("#operateType").val() === "add" || parent.$("#operateType").val() === "copy"){
            $("#refresh").on("click", function () {
                selectNewAll_refresh();
            });
            $("#add").on("click", function () {
                gl_prod_rule_addN('添加', 'add/glProdRuleAddPf.jsp', '600', '520');
            });
            $("#edit").on("click", function () {
                gl_prod_rule_modN('修改', 'edit/glProdRuleModPf.jsp', '600', '520');
            });
            $("#delete").on("click", function () {
                gl_prod_rule_delN();
            });
           }
     }else{
         $('#glProdRule').on('page.dt', function (e) {
                     $('#glProdRule').find("tr").removeClass('selected');
                 });
             $("#refresh").on("click",function(){
                 selectAll_refresh();
             });
             getPkList({
             url: contextPath + "/baseCommon/pklistBase?tableName=GL_EVENT_MAPPING&tableCol=EVENT_TYPE,EVENT_TYPE_DESC",
             id: "TRAN_EVENT_TYPE",
             async: false
             });
             getPkList({
             url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_DESC",
             id: "TRAN_TYPE",
             async: false
             });
             getPkList({
             url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
             id: "PROD_TYPE",
             async: false
             });
             $(".select2").select2();
             buttonStatus();
         $("#selectByPrimaryKey").click(function(){
             if($("#PROD_TYPE").val()==""&&$("#TRAN_EVENT_TYPE").val()==""&&$("#TRAN_TYPE").val()=="")
             {
                 initData_refresh();
             }else{
                 var attrTab = $("#glProdRule").dataTable();
                 var api = attrTab.api();
                 api.ajax.url(contextPath+"/baseCommon/selectBase?col1=PROD_TYPE&colV1="+$("#PROD_TYPE").val()+"&col2=TRAN_EVENT_TYPE&colV2="+$("#TRAN_EVENT_TYPE").val()+"&col3=TRAN_TYPE&"+"colV3="+$("#TRAN_TYPE").val()   ).load();
             }
         });
     }
   });


/*增加*/
function gl_prod_rule_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                  end: function(){
                          }
                 });
}
function gl_prod_rule_add(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                  end: function(){
                          }
                 });
}
/*修改*/
function gl_prod_rule_modN(title,url,w,h){
       if ($("#glProdRule").find(".selected").length!==1){
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
function gl_prod_rule_mod(title,url,w,h){
       if ($("#glProdRule").find(".selected").length!==1){
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
function  gl_prod_rule_del(){
    if ($("#glProdRule").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdRuleDel();
                });
}
function  gl_prod_rule_delN(){
    if ($("#glProdRule").find(".selected").length!==1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 glProdRuleDelN();
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
 function glProdRuleDelN(){
         var url = contextPath+"/glProdRule/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
         var rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData, callback_glProdRuleDelN,"json");                //将获取数据发送给后台处理
     }
 function glProdRuleDel(){
        var url;
         var rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
          if(parent.$(".breadcrumb").data("reqNum")!==null){
          url = contextPath+"/glProdRule/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
          sendPostRequest(url,rowData, callback_glProdRuleDel,"json");                //将获取数据发送给后台处理
          }  else{
           var paraJson,keyFieldsJson;
            paraJson={};
            keyFieldsJson={};
            url = contextPath+"/baseCommon/updateForDelete";
            rowData = $('#glProdRule').DataTable().rows(".selected").data()[0];  //已经获取数据
            paraJson.tableName="GL_PROD_RULE";
                keyFieldsJson.TRAN_EVENT_TYPE=rowData.TRAN_EVENT_TYPE;
                keyFieldsJson.CLIENT_TYPE=rowData.CLIENT_TYPE;
                keyFieldsJson.ACCT_STATUS=rowData.ACCT_STATUS;
                keyFieldsJson.CCY=rowData.CCY;
                keyFieldsJson.SYS_NAME=rowData.SYS_NAME;
                keyFieldsJson.TRAN_TYPE=rowData.TRAN_TYPE;
                keyFieldsJson.PROD_TYPE=rowData.PROD_TYPE;
                keyFieldsJson.SOURCE_TYPE=rowData.SOURCE_TYPE;
                keyFieldsJson.SOURCE_MODULE=rowData.SOURCE_MODULE;
            paraJson.key = keyFieldsJson;
            paraJson.status=rowData.COLUMN_STATUS;
            paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
            paraJson.status=rowData.COLUMN_STATUS;
            var params = {
                paraJson:JSON.stringify(paraJson)
            };
            sendPostRequest(url,params, callback_glProdRuleDel,"json");                //将获取数据发送给后台处理
        }
            }
 function callback_glProdRuleDel(json){
      if(parent.$(".breadcrumb").data("reqNum")!==null){
      if (json.retStatus === 'F'){
          showMsg(json.retMsg,'info');
      } else if(json.retStatus === 'S'){
       $('#glProdRule').dataTable().api().row(".selected").remove().draw(false);
          showMsg(json.retMsg,'info');
      }
      }else{
       if (json.success) {
               $("#glProdRule").dataTable().api().row(".selected").remove().draw(false);
               showMsgDuringTime("删除成功！");
           } else if (json.errorMsg) {
               showMsg(json.errorMsg, 'errorMsg');
           }
      }

  }
   function callback_glProdRuleDelN(json){
        if (json.retStatus === 'F'){
            showMsg(json.retMsg,'info');
        } else if(json.retStatus === 'S'){
          $('#glProdRule').dataTable().api().row(".selected").remove().draw(false);
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
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
        if(parent.$(".breadcrumb").data("reqNum")!==null){
    api.ajax.url(contextPath+"/glProdRule/getAll?prodType=" + parent.$("#prodType").val()).load();
    }else{
        api.ajax.reload();
    }
}
function selectNewAll_refresh(){
  	var prodTab = $("#glProdRule").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/glProdRule/getAll?prodType=" + parent.$("#newProdType").val()).load();
}
