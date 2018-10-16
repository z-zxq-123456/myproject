<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_BRANCH交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmBranch.js"></script>
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
            <table id="fmBranch" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>机构代码</th>
                        <th>国家</th>
                        <th>省市</th>
                        <th>机构简称</th>
                        <th>结售汇平盘机构</th>
                        <th>是否签发支票</th>
                        <th>城市代码</th>
                        <th>业务单元(人民币)</th>
                        <th>法人代码</th>
                        <th>区号</th>
                        <th>日终标识</th>
                        <th>外汇金融机构代码</th>
                        <th>层级代码</th>
                        <th>业务单元(港币)</th>
                        <th>内部客户号</th>
                        <th>机构IP地址</th>
                        <th>当地币种</th>
                        <th>人行备付金检查标志</th>
                        <th>邮编</th>
                        <th>利润中心</th>
                        <th>状态</th>
                        <th>分行代码</th>
                        <th>机构名称</th>
                        <th>自贸区代码</th>
                        <th>是否自贸区机构</th>
                        <th>是否营业机构</th>
                        <th>所属机构</th>
                        <th>是否限制凭证入库柜员</th>
                        <th>基础币种</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
