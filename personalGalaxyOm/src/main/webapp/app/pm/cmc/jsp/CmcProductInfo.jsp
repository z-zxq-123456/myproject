<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CMC_PRODUCT_INFO交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/CmcProductInfo.js"></script>
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
            <table id="cmcProductInfo" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>卡产品编号</th>
                         <th>卡产品名称</th>
                         <th>卡BIN序号</th>
                         <th>发行渠道类型</th>
                        <th>产品系列编号</th>
                         <th>产品系列名称</th>
                         <th>产品种类</th>
                         <th>借贷标识</th>
                         <th>规则序号</th>
                         <th>年费等级</th>
                         <th>级别编号</th>
                         <th>启用标志</th>
                         <th>启用日期</th>
                         <th>序号启用标志</th>
                         <th>起始序号</th>
                         <th>终止序号</th>
                         <th>卡片物理性质</th>
                         <th>atm密码错误限制次数</th>
                         <th>密码错误总限制次数</th>
                         <th>cvn错误总限制次数</th>
                         <th>cvn2错误总限制次数</th>
                        <th>卡号校验位错误总限制次数</th>
                         <th>主帐户的币种</th>
                         <th>记名卡/非记名卡标志</th>
                         <th>开卡手续费</th>
                         <th>最大持卡量</th>
                         <th>密码标志</th>
                         <th>挂失标志</th>
                         <th>卡有效期期限</th>
                         <th>卡有效期使用方式</th>
                         <th>卡固定有效期</th>
                         <th>卡产品固定有效期</th>
                         <th>限制接口</th>
                         <th>发卡对象</th>
                         <th>起存金额</th>
                         <th>限制贡献值</th>
                         <th>最后更新日期</th>
                         <th>交易时间</th>
                         <th>交易时间戳</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
