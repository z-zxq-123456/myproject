var form;
var valueId,menuName;
$(document).ready(function() {
    parent.$('#nestable_list_menu').find(".dd3-content").each(function(){  //从父页面获取选中行菜单的ID
        if($(this).css("background-color") === "rgb(255, 184, 72)"){
            valueId=this.id;
            menuName = this.textContent;
        }
    });
    $("#menuParentId").val(valueId);
   $("#menuStatus").val("Y");
   form = $("#form-menuManage-add").Validform({
      tiptype:2,
      callback:function(){
         buttonManageAdd();
         return false;
      }
   });
   $("#menuParentId").append("<option value='"+valueId+"' selected>"+menuName+"</option>");
    $(".select2").select2();
});

function buttonManageAdd(){
   var url = contextPath+"/menu/insert";
    sendPostRequest(url,{
        menuParentId:$("#menuParentId").val(),
        menuName:$("#menuName").val(),
        menuUrl:"button",
        menuAction:$("#menuAction").val(),
        menuStatus:"Y"
    }, callbackButtonManageAdd,"json");
}

function callbackButtonManageAdd(json){
   if (json.success) {
      layer_close();
   } else if (json.errorMsg) {
      showMsg(json.errorMsg, 'errorMsg');
   }
}