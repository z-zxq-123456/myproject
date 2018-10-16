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
    <title>先升级部署</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appEarlyInstant.js"></script>
</head>
<body>
<div class="pb-20">
    <div class="pb-20" id="myStep" style="margin-left:1px;margin-right:1px">
        <div class="step-list">
            <div class="text-c">
                <form method="post" class="form form-horizontal" id="selectForm" name="selectForm">
                    <div class="row">
                        <label class="form-label span2">部署版本号：</label>
                        <div class="formControls span3">
                            <select class="select2"
                                    name="demoAppIntantVer"
                                    id="demoAppIntantVer" style="width:200px;" >
                                    <option  value="99"  select = "true">请选择</option>
                                    </select>
                        </div>
                        <label class="form-label span4">配置文件选择：</label>
                        <div class="formControls span3">
                            <select class="select2" name="isRemainConfig"
                                    id="isRemainConfig" style="width:200px;"></select>
                        </div>
                    </div>
                </form>
                <div class="mr-5 ml-5 mt-5" style="text-align:left;">
                    <table id="earlyInstantList"
                           class="table table-border table-bordered table-hover table-bg table-sort">
                        <thead>
                        <tr>
                            <th>
                                 <input type="checkbox" id="check_all" name="" value="">
                            </th>
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
                <form method="post" class="form form-horizontal" id="appVerForm" name="appVerForm">
                    <div class="row" style="display:none;">
                        <label class="form-label span2">应用版本ID：</label>
                        <div class="formControls span2">
                            <input type="text" class="input-text" datatype="*" name="appVerId" id="appVerId"  >
                        </div>
                        <label class="form-label span2">应用版本路径：</label>
                        <div class="formControls span2">
                            <input type="text" class="input-text" datatype="*"  name="appVerPath" id="appVerPath">
                        </div>
                        <label class="form-label span2">应用版本类型：</label>
                        <div class="formControls span2">
                            <input type="text" class="input-text" datatype="*"  name="appVerType" id="appVerType">
                        </div>
                    </div>
                </form>
            </div>
        </div>
     </div>
  </div>
</body>
</html>