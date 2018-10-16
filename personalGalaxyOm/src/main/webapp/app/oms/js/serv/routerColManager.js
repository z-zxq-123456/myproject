var layer_add_index, layer_edit_index;
$(document).ready(function() {
     	var opt = getDataTableOpt($("#routerColList"));


     	opt.ajax= {
              "url": contextPath + "/findRouterCol",
              "type": "POST"
          };
     	opt.columns=[
             { "data": "routerColCn",
                 "defaultContent" : "" },
             { "data": "routerColCdn",
                 "defaultContent" : "" },
             { "data": "routerColTypeName",
                 "defaultContent" : ""  }
         ];
     	//渲染tables
     	setDataTableOpt($("#routerColList"),opt);
     	//界面美化tables
     	$("#routerColList").beautyUi({
     	    tableId:"routerColList",
         });
         $("#add").on("click",function(){
             midwareVer_Add('路由字段信息添加','routerColAdd.jsp','600','400');
         });
         $("#edit").on("click",function(){
             midwareVer_Edit('路由字段信息修改','routerColEdit.jsp','600','400');
         });
         $("#delete").on("click",function(){
             midwareVer_Del();
         });
         $(".select2").select2();
});
function midwareVer_Add(title,url,w,h){
    layer_add_index =  layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
function midwareVer_Edit(title,url,w,h){
    if ($("#routerColList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
     }
   layer_edit_index =  layer.open({
    type: 2,
    content: url,
    title:title,
    area: [w+'px', h+'px']
    });
}
function midwareVer_Del(){
    if ($("#routerColList").find(".selected").length!=1){
        showMsg('请选择一行记录！','warning');
        return;
    }

    layer.confirm('确认要删除吗？',function(){
        midwareVerDel();
    });
}
function midwareVerDel(){
    var url = contextPath+"/deleteEcmRouterCol";
    var rowData = $('#routerColList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url,rowData	, callback_midwareVerDel,"json");                //将获取数据发送给后台处理
}

function callback_midwareVerDel(json){
    if (json.success){
        $('#routerColList').dataTable().api().row(".selected").remove().draw(false);
         showMsgDuringTime('删除成功！');
    } else if(json.errorMsg){
        showMsg(json.errorMsg,'errorMsg');
    }
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}