<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_CARD_NO_ROLE_INFO交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/CmcCardNoRoleInfo.js"></script>
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
            <table id="cmcCardNoRoleInfo" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>规则序号</th>
                        <th>卡号长度</th>
                        <th>校验位位置</th>
                        <th>顺序号长度</th>
                        <th>顺序号前域的个数</th>
                        <th>域1来源表</th>
                        <th>域1来源域</th>
                        <th>域1长度</th>
                        <th>域1条件</th>
                        <th>域1方式</th>
                         <!--  <th>域2来源表</th>
                         <th>域2来源域</th>
                         <th>域2长度</th>
                         <th>域2条件</th>
                         <th>域2方式</th>
                         <th>域3来源表</th>
                         <th>域3来源域</th>
                         <th>域3长度</th>
                         <th>域3条件</th>
                         <th>域3方式</th>
                         <th>域4来源表</th>
                         <th>域4来源域</th>
                         <th>域4长度</th>
                         <th>域4条件</th>
                         <th>域4方式</th>
                         <th>域5来源表</th>
                         <th>域5来源域</th>
                         <th>域5长度</th>
                         <th>域5条件</th>
                         <th>域5方式</th> -->
                        <th>顺序号后域的个数</th>
                        <!-- <th>域6来源表</th>
                        <th>域6来源域</th>
                        <th>域6长度</th>
                        <th>域6条件</th> -->
                        <th>域6方式</th>
                        <!-- <th>域7来源表</th>
                        <th>域7来源域</th>
                        <th>域7长度</th>
                        <th>域7条件</th>
                        <th>域7方式</th>
                        <th>域8来源表</th>
                        <th>域8来源域</th>
                        <th>域8长度</th>
                        <th>域8条件</th>
                        <th>域8方式</th> -->
                        <th>校验位算法</th>
                        <th>校验位长度</th>
                       <!--  <th>校验位生成函数</th> -->
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
