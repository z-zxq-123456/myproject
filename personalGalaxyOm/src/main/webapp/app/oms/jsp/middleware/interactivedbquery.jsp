<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>db交互查询</title>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/middleware/interactivedbquery.js"></script>
</head>
<body>
<div class="mb-10">
      <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>中间件管理</a><span>&gt;</span>Database</a><span>&gt;</span><span>db交互查询</span><a
                           href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">DB集群选择：</label>
        <div class="formControls span3">
            <select name="midwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
          <div class="span1"></div>
                <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
                   <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
    <div class="row">
        <label class="form-label span2">SQL：</label>
        <div class="formControls span3">
                     <input class="input-text grid" name="sql" id="querySql" type="text" style="width:700px;height:60px"">
                 </div>
    </div>
</form>
 <div id="queryDataMessage"></div>
 		</div>
 <div class="mr-20 ml-20 mt-10" id="sqlTableDiv">
 </div>
</body>
</html>
  