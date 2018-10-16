<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用指标查询</title>
    <script type="text/javascript" charset="UTF-8" src="${ContextPath}/app/oms/js/shshpoc/appIndexQueryHistory.js"></script>
            <script type="text/javascript" src="${ContextPath}/js/application.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">管理视图</a><span>&gt;</span><span>应用指标查询</span><a href="javascript:location.replace(location.href);"
                                                                    title="刷新"><i class="iconfont">&#xe61e;</i></a>
    </nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">起始日期：</label>
        <div class="formControls span3 ">
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})"
                   id="startTime" name="startTime" class="input-text Wdate " style="width:250px;margin-left:0px;">
        </div>
        <label class="form-label span2">终止日期：</label>
        <div class="formControls span3">
            <input type="text"
                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})"
                   id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
        </div>
    </div>
    <div class="row">
        <label class="form-label span2">监控维度：</label>
        <div class="formControls span3">
            <select name="appIndexrecDime" id="qeryAppIndexrecDime" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
        <div class="span1"></div>
        <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
            <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
</form>
 <div class="span1"    id = "queryDataMessage"></div>
 <div class="mr-20 ml-20 mt-10" id="indexTableDiv">
 </div>
</body>
</html>
