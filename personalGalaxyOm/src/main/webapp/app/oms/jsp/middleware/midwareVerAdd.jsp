<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String isRedirect = request.getParameter("isRedirect");
    String isErrorMsg = request.getParameter("isErrorMsg");
%>
<html>
<head>
    <title>版本信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareVerAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form" name="fileForm" enctype="multipart/form-data">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>中间件类型：</label>
            <div class="formControls span6">
                <select type="text" class="select2" name="midwareType" id="midwareType" datatype="*1-60"></select>
            </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>版本号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="版本号" name="midwareVerNo" id="midwareVerNo"
                       datatype="s1-20" nullmsg="请输入">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>版本描述：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="版本描述" name="midwareVerDesc" id="midwareVerDesc"
                       datatype="*1-100" nullmsg="请输入">
            </div>
            <div class="span2"></div>

        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用安装文件：</label>
            <div class="formControls span6">
                <span class="btn-upload form-group">
                <input class="input-text upload-url" type="text" readonly datatype="*"
                       nullmsg="请添加附件！" style="width:180px">
                <a class="button-add M btnNoRadius upload-btn">浏览文件</a>
                <input type="file" multiple name="sourceFile" id="sourceFile" class="input-file"></span>
            </div>
        </div>


        <div class="row">
            <div class="span5"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="appInfoAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function () {
//判断是否是重定向到此页面的，如果是，则关闭。
if(<%=isRedirect%>){
parent.showMsgDuringTime("添加成功");
}
if(<%=isErrorMsg%>){
showMsg("数据库操作异常","errorMsg");
}
getPkList({
url: contextPath + "/findParaCombox?paraParentId=0006&&isDefault=false",
id: "midwareType",
async: false
});

form = $("#form").Validform({
tiptype: 2,
callback: function (form) {
appInfoAdd();
appInfoAddCancel();
return false;
}
});
$(".select2").select2();
});
</script>
</html>