<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>复核信息列表</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraIn/flowInfoShow.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span>参数管理入口<span >&gt;</span><span >查看流程信息</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="cifBusinessPrimaryKey">
            <div class="row cl">
                <label class="form-label">起始日期：</label>
                <div class="formControls span3">
                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd'})" id="startDate" name="startDate" class="input-text Wdate" >
                </div>
                <label class="form-label">终止日期：</label>
                <div class="formControls span3">
                    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd'})" id="endDate" name="endDate" class="input-text Wdate" >
                </div>
                <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px"><i class="iconfont">&#xe624;</i> 查询</a>
            </div>
        </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="flowInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th>操作时间</th>
                    <th>交易</th>
                    <th>复核发布意见</th>
                    <th>操作类型</th>
                    <th>是否通过</th>
                    <th>操作人ID</th>
                    <th>操作人IP</th>
                    <th>流水编号</th>
                    <th>交易编号</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
