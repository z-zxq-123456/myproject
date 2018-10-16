<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
  String appId=request.getParameter("id");
  String appName = request.getParameter("name");
  String flag = request.getParameter("flag");
%>
<html>
<head>
    <title>先升级部署</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appLateConfig.js"></script>
</head>
<body>
<div class="step-list">
            <div class="text-c">
                <form method="post" class="form form-horizontal" id="selectForm1" name="selectForm">
                                    <div class="row">
                                        <label class="form-label span2">应用实例名称：</label>
                                        <div class="formControls span3">
                                            <select class="select2"
                                                    name="queryAppIntantId"
                                                    id="queryAppIntantId" style="width:200px;"></select>
                                        </div>
                                        <label class="form-label span2">配置文件名称：</label>
                                        <div class="formControls span3">
                                            <select class="select2" name="queryFileName"
                                                    id="queryFileName" style="width:200px;"></select>
                                        </div>
                                        <div class="span2">
                                            <a class="button-select S " onclick="searchLateConfig()"><i class="iconfont icon-4"></i>搜索</a>
                                        </div>
                                    </div>
                                </form>
                <div class="mt-10" style="text-align:left;">
                    <table id="lateConfigList"
                           class="table table-border table-bordered table-hover table-bg table-sort">
                        <thead>
                        <tr>
                            <th>配置文件名称</th>
                            <th>配置参数名称</th>
                            <th>配置参数值</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div><!--5-->
        </div>
</html>
<script type="text/javascript" >
 var appId = "<%=appId %>";
 var appName="<%=appName%>";
 var flag=<%=flag%>
 $("#queryAppIntantId").change(function () {
       var appIntantId = $("#queryAppIntantId").val();
       if (appIntantId != "") {
           getPkList({
               url: contextPath + "/findEcmAppFile",
               id: "queryFileName",
               params: {appIntantId: appIntantId},
               async: false
           });
       }
});
</script>