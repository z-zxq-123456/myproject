<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_FEE_MAPPING交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlFeeMapping.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
        <div class="row cl">
            <label class="form-label">序号：</label>
            <div class="formControls  span2">
                <input type="text"  value="" class="input-text grid" placeholder="irlSeqNo" id="IRL_SEQ_NO" name="irlSeqNo"  >
            </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i>  查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="irlFeeMapping" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>操作类型</th>
                <th>序号</th>
                <th>费率类型</th>
                <th>机构代码规则</th>
                <th>事件类型规则</th>
                <th>交易类型规则</th>
                <th>产品组规则</th>
                <th>产品类型规则</th>
                <th>加急标志</th>
                <th>跨行标志</th>
                <th>地区规则</th>
                <th>币种规则</th>
                <th>客户类型规则</th>
                <th>客户类型细类规则</th>
                <th>渠道类型规则</th>
                <th>凭证类型规则</th>
                <th>凭证/票据变更前状态规则</th>
                <th>凭证/票据变更后状态规则</th>
                <th>是否使用规则</th>
                <th>服务代码规则</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
