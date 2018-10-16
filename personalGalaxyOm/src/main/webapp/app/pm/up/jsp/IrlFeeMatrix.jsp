<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_FEE_MATRIX交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlFeeMatrix.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">矩阵序号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="matrixNo" id="MATRIX_NO" name="matrixNo"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlFeeMatrix" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th>矩阵序号</th>
                                <th>费用代码</th>
                                 <th>缺口值</th>
                                <th>收费金额</th>
                                <th>费率</th>
                                <th>利率类型</th>
                                <th>浮动利率</th>


                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
