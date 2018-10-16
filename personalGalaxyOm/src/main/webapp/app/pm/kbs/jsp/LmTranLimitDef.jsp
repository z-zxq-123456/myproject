<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>LM_TRAN_LIMIT_DEF交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/LmTranLimitDef.js"></script>
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
                                        <input type="text"  value="" class="input-text grid" placeholder="limitRef" id="LIMIT_REF" name="limitRef"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="lmTranLimitDef" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>交易限额编码</th>
                        <th>币种</th>
                        <th>交易处理方式取值</th>
                        <th>生效日期</th>
                        <th>交易限额编码说明</th>
                        <th>限额类型</th>
                        <th>最大额</th>
                        <th>最小额</th>
                        <th>状态</th>
                        <th>其他级别</th>
                        <th>是否允许自定义</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
