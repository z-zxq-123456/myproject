<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>IRL_INT_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlIntType.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span>&gt;</span><span>参数录入查看</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
        <div class="row cl">
            <label class="form-label">利率税率类型：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="intTaxType" id="INT_TAX_TYPE"
                       name="intTaxType">
            </div>
            <label class="form-label">利息计算模型：</label>
            <div class="formControls  span2">
                <select id="RATE_LADDER" name="rateLadder" class="select2" size="1" style="width:100%">
                    <option value="" selected="selected">空</option>
                    <option value="F">F-固定</option>
                    <option value="S">S-超额累进</option>
                    <option value="Q">Q-超额期限累进</option>
                    <option value="T">T-全额累进</option>
                    <option value="M">M-矩阵</option>
                    <option value="D">D-真实折扣</option>
                    <option value="R">R-第78条例</option>
                </select>
            </div>
            <label class="form-label">产品组：</label>
            <div class="formControls  span2">
                <select id="PROD_GRP" class="select2" name="prodGrp" tabindex="4" size="1" style="width:100%">
                </select>
            </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i> 查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="irlIntType" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>操作类型</th>
                <th>利率税率类型</th>
                <th>利率税率类型描述</th>
                <th>产品组</th>
                <th>利率税率标志</th>
                <th>利息计算模型</th>
                <th>税率计算模型</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
