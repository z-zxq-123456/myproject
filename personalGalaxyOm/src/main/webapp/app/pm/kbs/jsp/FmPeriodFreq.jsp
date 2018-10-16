<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_PERIOD_FREQ交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmPeriodFreq.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">期限类型：</label>
                        <div class="formControls  span2">
                                        <select id="PERIOD_FREQ" class="select2" name="periodFreq" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="fmPeriodFreq" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>期限类型</th>
                        <th>每期数量</th>
                        <th>期初/期末</th>
                        <th>描述</th>
                        <th>半月标识</th>
                        <th>每期单位</th>
                        <th>每期天数</th>
                        <th>节假日是否顺延</th>
                        <th>客户浮动</th>
                        <th>期限单位值</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
