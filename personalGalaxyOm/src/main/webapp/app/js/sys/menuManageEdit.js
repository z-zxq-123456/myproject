var form;
var valueId;
$(document).ready(function() {
   parent.$('#nestable_list_menu').find(".dd3-content").each(function(){  //从父页面获取选中行菜单的ID
      if($(this).css("background-color") === "rgb(255, 184, 72)"){
         valueId=this.id;
         $.ajax({    //从数据库获取数据
            url:contextPath+"/menu/selectByIdWithoutYN",
            data:{"menuId": valueId},
            dataType:"json",
            async:false,
            success:function(data){
                    $("#menuParentId").append("<option value='" +data.menuParentId+"'>" + data.menuParentName + "</option>");
               $("#menuName").val(data.menuName);
               $("#menuUrl").val(data.menuUrl);
               $("#menuStatus").val(data.menuStatus);
               $("#menuSeq").val(data.menuSeq);
            }
         });
      }
   });

   form = $("#form-menuManage-edit").Validform({
      tiptype:2,
      callback:function(){
         menuManageEdit();
         return false;
      }
   });
   $(".select2").select2();
});

function menuManageEdit(){
   var url = contextPath+"/menu/update";
   sendPostRequest(url,{
      menuId:valueId,
      menuParentId:$("#menuParentId").val(),
      menuName:$("#menuName").val(),
      menuUrl:$("#menuUrl").val(),
      menuStatus:$("#menuStatus").val(),
      menuSeq:$("#menuSeq").val()
   }, callbackMenuManageEdit,"json");
}

function callbackMenuManageEdit(json){
   if (json.success) {
      parent.showMsgDuringTime('编辑成功');
   } else if (json.errorMsg) {
      showMsg(json.errorMsg, 'errorMsg');
   }
}