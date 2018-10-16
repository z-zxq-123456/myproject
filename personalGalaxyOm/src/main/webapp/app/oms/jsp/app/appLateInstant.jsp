<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
  String appId=request.getParameter("id");
  String appName = request.getParameter("name");
%>
<script type="text/javascript" >
 var appId = "<%=appId %>";
 var appName="<%=appName%>";
</script>
<html>
<head>
    <title>后升级部署</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appLateInstant.js"></script>
</head>
<body>
<div class="pb-20">
 <div class="pb-20" id="myStep" style="margin-left:5px;margin-right:5px">
 <div class="step-list">
            <div class="text-c">
                <form method="post" class="form form-horizontal" id="selectForm2" name="selectForm">
                    <div class="row">
                        <label class="form-label span2">配置文件选择：</label>
                        <div class="formControls span3">
                            <select class="select2" name="lateIsRemainConfig"
                                    id="lateIsRemainConfig" style="width:200px;"></select>
                        </div>
                    </div>
                </form>
                <div class="mt-10" style="text-align:left;">
                    <table id="lateInstantList"
                           class="table table-border table-bordered table-hover table-bg table-sort">
                        <thead>
                        <tr>
                            <th>
                                实例IP
                            </th>
                            <th>
                                实例名称
                            </th>
                            <th>
                                实例版本号
                            </th>
                            <th>
                                最新操作
                            </th>
                            <th>
                                当前状态
                            </th>
                            <th>
                                健康信息
                            </th>
                            <th>
                                工作目录
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div><!--4-->
        </div>
     </div>
    </div>
</body>
</html>
