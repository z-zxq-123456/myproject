var form;
var valueId;
$(document).ready(function() {
    var selectMenu = 0;
    valueId = 0;
    parent.$('#nestable_list_menu').find(".dd3-content").each(function(){  //从父页面获取选中行菜单的ID
        if($(this).css("background-color") === "rgb(255, 184, 72)" && (selectMenu === 0)){
                valueId = this.id;
                selectMenu = 1;
        }
    });
    if(selectMenu === 1)
    {
        $.ajax({
            url:contextPath+"/menu/selectByIdWithoutYN",
            data:{"menuParentId": valueId},
            dataType:"json",
            async:false,
            success:function(data){
                $("#menuParentId").append("<option value='" +data.menuId+"'>" + data.menuName + "</option>");
            }
        });
    }else if(selectMenu === 0)
    {
        $("#menuParentId").append("<option value='0'>顶层</option>");
    }

   $("#menuStatus").val("Y");
   form = $("#form-menuManage-add").Validform({
      tiptype:2,
      callback:function(){
         menuManageAdd();
         return false;
      }
   });

    $(".select2").select2();
});

function menuManageAdd(){
   var url = contextPath+"/menu/insert";
    sendPostRequest(url,{
        menuParentId:$("#menuParentId").val(),
        menuName:$("#menuName").val(),
        menuUrl:$("#menuUrl").val(),
        menuStatus:$("#menuStatus").val()
    }, callbackMenuManageAdd,"json");
}

function callbackMenuManageAdd(json){
   if (json.success) {
      parent.showMsgDuringTime('添加成功');
   } else if (json.errorMsg) {
      showMsg(json.errorMsg, 'errorMsg');
   }
}