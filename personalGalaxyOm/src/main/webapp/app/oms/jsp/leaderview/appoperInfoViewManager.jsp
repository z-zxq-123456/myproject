<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用操作视图</title>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/leaderview/appoperInfoViewManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">操作流水</a><span >&gt;</span><span >应用监控流水</span>
          			 <span >&gt;</span><span >应用操作流水</span>
          			 <a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">部署人：</label>
        <div class="formControls span3">
            <select name="userId" id="queryUserId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
        <label class="form-label span2">应用名称：</label>
        <div class="formControls span3">
            <select name="appId" id="queryAppId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
    </div>
    <div class="row">
        <label class="form-label span2">起始日期：</label>
        <div class="formControls span3 ">
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                   id="startTime" name="startTime" class="input-text Wdate " style="width:250px;margin-left:0px;">
        </div>
        <label class="form-label span2">终止日期：</label>
        <div class="formControls span3">
            <input type="text"
                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                   id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
        </div>
    </div>
    <div class="row">
        <label class="form-label span2">操作类型：</label>
        <div class="formControls span3">
            <select name="ecmAppoperType" id="queryParaCode" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
        <div class="span1"></div>
        <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
            <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
</form>
<div class="mr-20 ml-20 mt-10">
    <table id="operInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
            <th>操作日期</th>
            <th>应用名称</th>
            <th>实例名称</th>
            <th>实例IP</th>
            <th>实例版本号</th>
            <th>应用安装路径</th>
            <th>操作类型</th>
            <th>操作人</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
  