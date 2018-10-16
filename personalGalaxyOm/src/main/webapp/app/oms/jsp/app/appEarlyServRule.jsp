<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
String appId=request.getParameter("id");
String appName = request.getParameter("name");
String flag = request.getParameter("flag");
%>
<script type="text/javascript" >
var appId=<%=appId%>;
var flag=<%=flag%>
</script>
<html>
<head>
    <title>先升级部署</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appEarlyServRule.js"></script>
</head>
<body>
 <div class="step-list">
            <div class="text-c">
                <div class="mt-10" style="text-align:left;">
                    <table id="earlyServRuleList"
                            class="table table-border table-bordered table-hover table-bg table-sort">
                        <thead>
                        <tr>
                            <th>
                                服务名称
                            <th>
                                服务类名
                            </th>
                            <th>
                                方法中文名
                            </th>
                            <th>
                                方法代码名
                            </th>
                            <th>
                                条件名称
                            </th>
                            <th>
                                自定义规则
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div><!--3-->
        </div>
</body>
</html>