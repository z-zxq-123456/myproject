var selectIs=0;
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#irlFeeMapping"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    if(parent.$("#operateType").val() == "add"){
      opt.ajax= {
               "url": contextPath+"/irlFeeMapping/getAll?prodType=" + parent.$("#newProdType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "update"){
      opt.ajax= {
               "url": contextPath+"/irlFeeMapping/getAll?prodType=" + parent.$("#prodType").val(),
               "type": "POST"
           };
    }else if(parent.$("#operateType").val() == "copy"){
      opt.ajax= {
              "url": contextPath+"/irlFeeMapping/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val(),
              "type": "POST"
          };
    }
    opt.columns=[
            { "data": "prodType", "defaultContent":""},
			{ "data": "areaCode", "defaultContent":""},
			{ "data": "branch", "defaultContent":""},
			{ "data": "categoryType", "defaultContent":""},
			{ "data": "ccy", "defaultContent":""},
			{ "data": "clientType", "defaultContent":""},
			{ "data": "company", "defaultContent":""},
			{ "data": "docType", "defaultContent":""},
			{ "data": "eventType", "defaultContent":""},
			{ "data": "feeNo", "defaultContent":""},
			{ "data": "feeType", "defaultContent":""},
			{ "data": "isLocal", "defaultContent":""},
			{ "data": "isRule", "defaultContent":""},
			{ "data": "newStatus", "defaultContent":""},
			{ "data": "oldStatus", "defaultContent":""},
			{ "data": "prodGrp", "defaultContent":""},
			{ "data": "serviceId", "defaultContent":""},
			{ "data": "sourceType", "defaultContent":""},
			{ "data": "tranType", "defaultContent":""},
			{ "data": "urgentFlag", "defaultContent":""},
	];
        $(".select2").select2();
	//渲染tables
	drawDataTable($("#irlFeeMapping"),opt);
	dataTableUi("irlFeeMapping",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);
     	$('#irlFeeMapping tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#irlFeeMapping').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
         });
        $("#nullify").hide();
        $("#reBack").hide();
        $("#contrast").hide();
     if(parent.$("#operateType").val() == "update"){
        $("#reference").on("click",function(){
            selectAll_refresh();
        });
        $("#add").on("click",function(){
           irl_fee_mapping_add('添加','irlFeeMappingAddPf.jsp','600','520');
        });
        $("#edit").on("click",function(){
          irl_fee_mapping_mod('修改','irlFeeMappingModPf.jsp','600','520');
        });
        $("#delete").on("click",function(){
            irl_fee_mapping_del();
        });
     }else if(parent.$("#operateType").val() == "add" || parent.$("#operateType").val() == "copy"){
        $("#reference").on("click",function(){
            selectNewAll_refresh();
        });
        $("#add").on("click",function(){
           irl_fee_mapping_addN('添加','irlFeeMappingAddPf.jsp','600','520');
        });
        $("#edit").on("click",function(){
          irl_fee_mapping_modN('修改','irlFeeMappingModPf.jsp','600','520');
        });
        $("#delete").on("click",function(){
            irl_fee_mapping_delN();
        });
     }
   });

var layer_add_index, layer_edit_index;
/*增加*/
function irl_fee_mapping_add(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){
                      selectAll_refresh();
                   }
                 });
}
/*增加*/
function irl_fee_mapping_addN(title,url,w,h){
     layer_add_index = layer.open({
                 type: 2,
                 content: url,
                 area: [w+'px', h+'px'],
                 end: function(){
                      selectNewAll_refresh();
                   }
                 });
}
/*修改*/
function irl_fee_mapping_mod(title,url,w,h){
       if ($("#irlFeeMapping").find(".selected").length!=1){
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
function irl_fee_mapping_modN(title,url,w,h){
       if ($("#irlFeeMapping").find(".selected").length!=1){
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
/*删除*/
function  irl_fee_mapping_del(){
    if ($("#irlFeeMapping").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 irlFeeMappingDel();
                });
}
function  irl_fee_mapping_delN(){
    if ($("#irlFeeMapping").find(".selected").length!=1){
                showMsg('请选择一行记录！','warning');
                return;
     }
    layer.confirm('确认要删除吗？',function(){
                 irlFeeMappingDelN();
                });
}
function irlFeeMappingDel(){
        var url = contextPath+"/irlFeeMapping/delete1";
        var rowData = $('#irlFeeMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
        sendPostRequest(url,rowData	, callback_irlFeeMappingDel,"json");                //将获取数据发送给后台处理
    }
 function irlFeeMappingDelN(){
         var url = contextPath+"/irlFeeMapping/delete1";
         var rowData = $('#irlFeeMapping').DataTable().rows(".selected").data()[0];  //已经获取数据
         sendPostRequest(url,rowData,callback_irlFeeMappingDelN,"json");                //将获取数据发送给后台处理
     }

function  callback_irlFeeMappingDel(json){
    	if (json.retStatus == 'F'){
              	   	showMsg(json.retMsg,'info');
              	} else if(json.retStatus == 'S'){
              		showMsg(json.retMsg,'info');
              	}
      selectAll_refresh();
}
function  callback_irlFeeMappingDelN(json){
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
  	var prodTab = $("#irlFeeMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/irlFeeMapping/getAll?prodType=" + parent.$("#prodType").val()).load();
}
function selectNewAll_refresh(){
  	var prodTab = $("#irlFeeMapping").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/irlFeeMapping/getAll?prodType=" + parent.$("#newProdType").val()).load();
}
function selectAll_close(type,msg){
  parent.afterSelectPara(type,msg);
}
$("#selectByPrimaryKey").click(function(){
      if(selectIs==0){
        if(1==1
        &&$("#FEE_NO").val()==""
        ){
        alert("查询条件不能为空");
        }else{
        var attrTab = $("#irlFeeMapping").dataTable();
        var api = attrTab.api();
        api.ajax.url(contextPath+"/baseCommon/selectBase?col1=FEE_NO&colV1="+$("#FEE_NO").val()+"&col2=&colV2="+$("#").val()+"&col3=&"+"colV3="+$("#").val()   ).load();

       }
      }else{
      alert("请退回查看页面");
      }
});

