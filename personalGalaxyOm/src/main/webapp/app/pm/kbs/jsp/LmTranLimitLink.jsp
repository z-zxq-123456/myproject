<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>LM_TRAN_LIMIT_LINK交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/LmTranLimitLink.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">交易限额编码：</label>
                        <div class="formControls  span2">
                                        <select id="LIMIT_REF" class="select2" name="limitRef" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="lmTranLimitLink" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>交易限额编码</th>
                        <th>交易类型</th>
                        <th>客户分类</th>
                        <th>产品类型</th>
                        <th>渠道类型</th>
                        <th>余额类型</th>
                        <th>地区码</th>
                        <th>客户境内外标志</th>
                        <th>是否附属卡</th>
                        <th>机构</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
