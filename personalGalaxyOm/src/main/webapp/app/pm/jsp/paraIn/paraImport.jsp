<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.dcits.dynamic.web.util.ResourcesUtils" %>
<%@ include file="/form.jsp" %>
<%
    String isRedirect = request.getParameter("isRedirect");
    String isErrorMsg = request.getParameter("isErrorMsg");
    String creator = ResourcesUtils.getSystemUser(request);
    String ipAddress  = ResourcesUtils.getClientIP(request);
%>
<html>
<head>
    <title>用户列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraIn/paraImport.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理入口</a><span>&gt;</span><span>参数导入服务</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form id="uploadForm" method="post" class="form form-horizontal" name="uploadForm" enctype="multipart/form-data">
        <div class="mt-20 mr-20 ml-20 cl">
            <div class="row cl mb-20">
                <label class="form-label span2 forSelect2"><span class="c-red">*</span>上传文件：</label>
                <span class="btn-upload form-group span6">
                    <input class="input-text upload-url" id="inputFile" type="text" readonly style="width:400px;">
                    <a class="button-select M btnNoRadius upload-btn">浏览文件</a>
                    <input type="file" multiple name="sourceFile" id="sourceFile" class="input-file">
                </span>
            </div>
            <div class="row cl mb-20">
                <div class="formControls span2"></div>
                <div class="formControls span3">
                    <a id="upload" class="button-select">上传</a>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    var indexShade;
    $(document).ready(function () {
        if (<%=isRedirect%>) {
            layer.close(indexShade);
            parent.showMsgDuringTime("导入成功");
        }
        if (<%=isErrorMsg%>) {
            layer.close(indexShade);
            layer.open({
                type: 2,
                content: contextPath + "/app/"+"<%=creator%>"+"_"+"<%=ipAddress%>"+".jsp",
                area: ['600px', '500px']
            });
        }
        $("#upload").click(function (event) {
            if ($("#inputFile").val() == "") {
                showMsg("请选择需要上传的文件");
                return;
            }
            var ext = $("#inputFile").val().indexOf(".xls");
            if (ext == -1) {
                showMsg("请选择标准的Excel文件！");
                return;
            }
            indexShade = layer.load(4, {
                shade: [0.4,'#777777'] //0.5透明度的白色背景
            });
            $('#uploadForm').attr("action", contextPath + "/paraFlowService/uploadParaFile?systemId=" + parent.systemId + "&&reqNum=" + parent.$("#appSeriesNumber").val() + "&&transactionId=" + parent.transactionId+ "&&transactionDesc=" + parent.transactionDesc);
            $('#uploadForm').submit();
        });
    });
</script>

