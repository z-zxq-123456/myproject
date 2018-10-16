var form;
$(document).ready(function() {
   form = $("#form-role-add").Validform({
      tiptype:2,
      callback:function(){
         roleAdd();
         return false;
      }
   });
});

function roleAdd(){
   var url = contextPath+"/role/insert";
   sendPostRequest(url,{
   roleName:$("#roleName").val(),
   roleDesc:$("#roleDesc").val()
    }, callbackRoleAdd,"json");
}

function callbackRoleAdd(json){
   if (json.retStatus === 'S') {
      var dataTable=parent.$('#roleList').DataTable();
      dataTable.row.add({
         roleId:json.roleId,
         roleName:$("#roleName").val(),
         roleDesc:$("#roleDesc").val()
      }).draw(false);
      parent.showMsgDuringTime("添加成功");
       } else if (json.errorMsg) {
          showMsg(json.errorMsg, 'errorMsg');
       }
       form.resetForm();
}
function roleAddCancel(){
   var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}