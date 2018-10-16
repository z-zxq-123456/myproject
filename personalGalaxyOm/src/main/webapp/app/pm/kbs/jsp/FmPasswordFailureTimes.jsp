<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_PASSWORD_FAILURE_TIMES交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmPasswordFailureTimes.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="fmPasswordFailureTimes" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>渠道集合
多个渠道之间用,分隔，如果是所有渠道，则用"ALL"表示</th>
                        <th>密码类型编号：</th>
                        <th>机构</th>
                        <th>法人代码</th>
                        <th>上次修改日期</th>
                        <th>上次修改柜员</th>
                        <th>上次修改时间</th>
                        <th>密码最大错误次数</th>
                        <th>是否当日清零方式：</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
