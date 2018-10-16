<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户修改</title>
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="changePassword">
		<div class="row" id="userNameDiv">
			<label class="form-label span4"><span class="c-red">*</span>用户名：</label>
			<div class="formControls span5">
				<input type="text" class="input-text grid" id="userName" placeholder="姓名"  name="userName" disabled=true;>
            </div>
			<!--validform提示信息空间位置-->
			<div class="span3"></div>
		</div>
		<div class="row">
                    <label class="form-label span4">旧密码：</label>
                    <div class="formControls span5">
                        <input type="password" class="input-text grid" placeholder="旧密码" value="" id="oldPassword" name="oldPassword" datatype="*6-20" nullmsg="请输入" ajaxurl="user/vaildUserPassword" >
                    </div>
                    <div class="span3"> </div>
                </div>
        <div class="row">
            <label class="form-label span4">新密码：</label>
            <div class="formControls span5">
                <input type="password" class="input-text grid" placeholder="新密码" value="" id="newPassword" name="newPassword"  datatype="*6-20" nullmsg="请输入">
            </div>
            <div class="span3"> </div>
        </div>
        <div class="row">
            <label class="form-label span4">确认新密码：</label>
            <div class="formControls span5">
                <input type="password" class="input-text grid" placeholder="确认新密码" value="" id="passwordConf" name="passwordConf" datatype="*6-20" nullmsg="请输入"  errormsg="输入密码要一致" recheck="newPassword">
            </div>
            <div class="span3"> </div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
               	<input type="submit" class="button-select L smartButton"  value="修改">
          	</div>
        </div>
	</form>
</div>
</body>
</html>
<script type="text/javascript">
var form;
$(document).ready(function() {
        $("#userNameDiv").hide();
		$("#userName").val("${UserName}");
		form = $("#changePassword").Validform({
			tiptype:2,
			callback:function(form){
				userEdit();
				return false;
			}
		});
});

function userEdit(){
		var url = contextPath+"/user/updateUser";
		sendPostRequest(url,{
			userName:$("#userName").val(),
			password:$("#newPassword").val(),
		}, callback_userEdit,"json");

}

function callback_userEdit(json){
	if (json.retStatus == 'F'){
	   	showMsg(json.retMsg,'info');
	} else if(json.retStatus == 'S'){
	   layer.confirm('修改个人密码成功!', {
			 btn: ['回到登陆界面', '回到首页'] //可以无限个按钮
	   }, function(index, layero){
		  $.ajax({
				url : contextPath + "/loginOff",
				success : function(json){
					parent.parent.location.reload();
				}
		  });
	   }, function(index){
		   $.ajax({
				url : contextPath + "/loginOff",
				success : function(json){
					layer_close();
				}
		   });
	   });
	}
}
function userEditCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
</script>

