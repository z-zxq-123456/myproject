var form;
$(document).ready(function() {
   var rowData;
   if (parent.$("#roleList").find(".selected").length === 1) {
      rowData = parent.$('#roleList').DataTable().rows(".selected").data()[0];
   }
   if (rowData) {
      $("#roleId").val(rowData.roleId);
      $("#roleName").val(rowData.roleName);
      $("#roleDesc").val(rowData.roleDesc);
      form = $("#form-role-edit").Validform({
         tiptype: 2,
         callback: function () {
            roleEdit();
            return false;
         }
      });
   }
});

function roleEdit(){
   var url = contextPath+"/role/update";
   sendPostRequest(url,{
      roleId:$("#roleId").val(),
      roleName:$("#roleName").val(),
      roleDesc:$("#roleDesc").val()
   }, callbackRoleEdit,"json");
}

function callbackRoleEdit(json){
   if (json.success) {
      var dataTable=parent.$("#roleList").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            roleId:$("#roleId").val(),
            roleName:$("#roleName").val(),
            roleDesc:$("#roleDesc").val()
      }).draw(false);
        parent.showMsgDuringTime("编辑成功");
   } else if (json.errorMsg) {
      showMsg(json.errorMsg, 'errorMsg');
   }
}
function roleEditCancel(){
   var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}