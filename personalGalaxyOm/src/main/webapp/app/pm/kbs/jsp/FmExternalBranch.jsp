<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_EXTERNAL_BRANCH交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmExternalBranch.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">银行代码：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="bankCode" id="BANK_CODE" name="bankCode"  >
                        </div>
                        <label class="form-label">机构代码：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="branchCode" id="BRANCH_CODE" name="branchCode"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="fmExternalBranch" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>银行代码</th>
                        <th>机构代码</th>
                        <th>国家</th>
                        <th>法人代码</th>
                        <th>省市</th>
                        <th>机构名称</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
