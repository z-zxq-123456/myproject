<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>GL_SYSTEM交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/GlSystem.js"></script>
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
            <table id="glSystem" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>序号</th>
                        <th>GL</th>
                        <th>总账账户月末余额信息保留期限</th>
                        <th>是否自动创建内部帐户</th>
                        <th>税率帐户类型</th>
                        <th>运行日期</th>
                        <th>损益评估</th>
                        <th>利润中心</th>
                        <th>GL</th>
                        <th>下一运行日</th>
                        <th>MUTI_SETTLE_MODE</th>
                        <th>最大将来起息天数</th>
                        <th>最大倒起息天数</th>
                        <th>上一运行日期</th>
                        <th>是否批处理</th>
                        <th>应收利息科目</th>
                        <th>利息帐户类型</th>
                        <th>总账历史保存期类型</th>
                        <th>总账历史保存期</th>
                        <th>法人</th>
                        <th>营业税缴税频率</th>
                        <th>基础币种</th>
                        <th>是否自动产生交易参考号</th>
                        <th>资产类型挂账科目</th>
                        <th>是否合并清算分录</th>
                        <th>表外过度科目-资产类</th>
                        <th>营业税下个缴税日期</th>
                        <th>表外过度科目-负债类</th>
                        <th>销户挂账科目</th>
                        <th>历史数据起始日期</th>
                        <th>结算挂账科目-负债</th>
                        <th>未知</th>
                        <th>应付利息科目</th>
                        <th>交易参考号前缀 默认GL</th>
                        <th>跨帐套过渡科目</th>
                        <th>结算挂账科目-资产</th>
                        <th>营业税上个缴税日期</th>
                        <th>其他应付款科目</th>
                        <th>本地币种汇率类型</th>
                        <th>是否行内结售汇平盘</th>
                        <th>默认机构</th>
                        <th>负债类挂账科目</th>
                        <th>币种控制帐户</th>
                        <th>联机收取/批量收取</th>
                        <th>是否合并清算流水</th>
                        <th>本币</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
