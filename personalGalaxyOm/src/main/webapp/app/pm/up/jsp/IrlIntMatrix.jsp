<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_INT_MATRIX交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlIntMatrix.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">阶梯序号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="matrixNo" id="MATRIX_NO" name="matrixNo"  >
                        </div>
                        <label class="form-label">阶梯金额：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="matrixAmt" id="MATRIX_AMT" name="matrixAmt"  >
                        </div>
                        <label class="form-label">序号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="irlSeqNo" id="IRL_SEQ_NO" name="irlSeqNo"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlIntMatrix" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th style="width:160px;">阶梯序号</th>
                                <th style="width:160px;">序号</th>
                                <th style="width:160px;">阶梯金额</th>
                                <th style="width:160px;">频率类型</th>
                                <th style="width:160px;">每期天数</th>
                                <th style="width:160px;">基准利率类型</th>
                                 <th style="width:160px;">基准利率</th>
                                <th style="width:160px;">浮动点数</th>
                                <th style="width:160px;">利率浮动百分比</th>
                                <th style="width:160px;">实际利率</th>
                                 <th style="width:160px;">最小利率</th>
                                 <th style="width:160px;">最大利率</th>
                                 <th style="width:160px;">子利率类型</th>
                                <th style="width:160px;">利率终结标志</th>



                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
