var form;
var valueId;
$(document).ready(function() {
   $("#menuParentId").append("<option value='0'>无</option>");
   parent.$('#nestable_list_menu').find(".dd3-content").each(function(){  //从父页面获取选中行菜单的ID
      if($(this).css("background-color") === "rgb(255, 184, 72)"){
         valueId=this.id;
      }
   });
   $.ajax({    //从数据库获取数据
      url:contextPath+"/menu/selectByIdWithoutYN",
      data:{"menuId": valueId},
      dataType:"json",
      async:false,
      success:function(data){
         $("#menuName").val(data.menuName);
            $("#menuStatus").val(data.menuStatus);
         $("#menuAction").val(data.menuAction);
      }
   });
   form = $("#form-buttonManage-edit").Validform({
      tiptype:2,
      callback:function(){
         buttonManageEdit();
         return false;
      }
   });
   $(".select2").select2();
});

function buttonManageEdit(){
   var url = contextPath+"/menu/update";
   sendPostRequest(url,{
      menuId:valueId,
      menuName:$("#menuName").val(),
        menuStatus:$("#menuStatus").val(),
      menuAction:$("#menuAction").val()
   }, callbackButtonManageEdit,"json");
}

function callbackButtonManageEdit(json){
   if (json.success) {
      layer_close();
   } else if (json.errorMsg) {
      showMsg(json.errorMsg, 'errorMsg');
   }
}