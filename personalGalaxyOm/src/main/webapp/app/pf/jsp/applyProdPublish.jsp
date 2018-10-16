<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_CR_RATING交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pf/js/applyProdPublish.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>产品工厂</a><span >&gt;</span><span >产品参数变更明细查询</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
        <div class="row cl">

            <div class="row cl">
                <label class="form-label">产品代码：</label>
                <div class="formControls  span2">
                    <select id="prodType" class="select2" name="prodType" tabindex="4" size="1"  style="width:100%">
                    </select>
                </div>
                <label class="form-label">起始日期：</label>
                <div class="formControls span3">
                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd'})" id="startDate" name="startDate" class="input-text Wdate" >
                </div>
                <label class="form-label">终止日期：</label>
                <div class="formControls span3">
                    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd'})" id="endDate" name="endDate" class="input-text Wdate" >
                </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i>  查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="applyProdPublish" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>产品代码</th>
                <th>变更日期</th>
                <th>摘要</th>
                <th>修改项</th>
                <th>变更前值</th>
                <th>变更后值</th>
                <th>交易柜员</th>
                <th>交易时间</th>
                <th>复核柜员</th>
                <th>复核时间</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
