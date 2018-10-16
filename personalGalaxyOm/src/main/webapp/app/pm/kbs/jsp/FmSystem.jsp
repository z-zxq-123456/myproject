<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_SYSTEM交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmSystem.js"></script>
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
            <table id="fmSystem" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>银行全称</th>
                        <th>所属机构</th>
                        <th>是否连续使用指定的数字区间标志</th>
                        <th>银行简称</th>
                        <th>下一运行日</th>
                        <th>SYMBOLS总账分离标志</th>
                        <th>本年年末日期</th>
                        <th>本月月末日期</th>
                        <th>半年末日期</th>
                        <th>批处理阶段标志</th>
                        <th>资料不全客户冻结周期</th>
                        <th>自动冻结黑名单客户</th>
                        <th>系统所处的阶段</th>
                        <th>是否自动生成客户号</th>
                        <th>运行日期</th>
                        <th>是否产品版30E计算天数</th>
                        <th>季末日期</th>
                        <th>是否自动生成抵质押编号</th>
                        <th>上一运行日期</th>
                        <th>是否记录出错时的业务数据信息</th>
                        <th>是否记录业务数据信息</th>
                        <th>限制币种</th>
                        <th>本币</th>
                        <th>总行层级代码</th>
                        <th>默认批处理用户</th>
                        <th>多法人机构间清算方式</th>
                        <th>多法人是否允许跨法人查询标志</th>
                        <th>敞口类型</th>
                        <th>报表币种</th>
                        <th>默认存款账户限制类型</th>
                        <th>是否多法人系统</th>
                        <th>分行间清算科目-同业存放</th>
                        <th>借贷检查标志</th>
                        <th>法人</th>
                        <th>客户号结构类型</th>
                        <th>投资资金</th>
                        <th>当前批处理的业务组编号</th>
                        <th>当前批处理的模块号</th>
                        <th>批处理检查标志</th>
                        <th>基础币种</th>
                        <th>DAC校验标志</th>
                        <th>默认机构</th>
                        <th>是否行内结售汇平盘</th>
                        <th>总行清算行内部客户</th>
                        <th>是否行内结售汇平盘</th>
                        <th>汇率浮动百分比</th>
                        <th>电子银行</th>
                        <th>默认本地汇率类型</th>
                        <th>本地币种汇率类型</th>
                        <th>默认利润中心</th>
                        <th>允许查询的历史天数</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
