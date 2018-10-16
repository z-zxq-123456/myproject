<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String appId = request.getParameter("id");
    String flag = request.getParameter("flag");
    String urlArgs = "";
    if (appId != null) {
        urlArgs = "?appId=" + appId;
    }
%>

<html>
<head>
    <title>先升级配置调整</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appEarlyConfig.js"></script>
</head>
<body>
<div class="step-list">
            <div class="text-c">
                <form method="post" class="form form-horizontal" id="selectForm1" name="selectForm">
                    <div class="row">
                        <label class="form-label span2">应用实例名称：</label>
                        <div class="formControls span3">
                            <select class="select2"
                                    name="appIntantId"
                                    id="appIntantId" style="width:200px;"></select>
                        </div>
                       <label class="form-label span2"><span class="c-red">*</span>配置文件名称：</label>
                             <div class="span3">
                                  <select name="fileName" id="fileName" size="1" class="select2" style="margin-top:5px">
                                  </select>
                             </div>
                        <div class="span2">
                            <a class="button-select S " onclick="searchEarlyConfig()"><i class="iconfont icon-4"></i>搜索</a>
                        </div>
                    </div>
                </form>
                <div class="mt-10" style="text-align:left;">
                    <table id="earlyConfigList"
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
            </div><!--2-->
        </div>

</body>
</html>
<script type="text/javascript" >
$("#appIntantId").change(function () {
          var appIntantId = $("#appIntantId").val();
          if (appIntantId != "") {
              getPkList({
                  url: contextPath + "/findEcmAppFile",
                  id: "fileName",
                  params: {appIntantId: appIntantId},
                  async: false
              });
          }
  });
 var appId = '<%=appId%>';
 var flag = '<%=flag%>';
</script>
