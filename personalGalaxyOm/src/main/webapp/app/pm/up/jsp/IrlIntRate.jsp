<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_INT_RATE交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlIntRate.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">币种：</label>
                        <div class="formControls  span2">
                                        <select id="CCY" class="select2" name="ccy" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">利率类型：</label>
                        <div class="formControls  span2">
                         <select id="INT_TYPE" class="select2" name="intType" tabindex="4" size="1"  style="width:100%">
                                                              </select>

                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlIntRate" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>序号</th>
                                <th>利率类型</th>
                                <th>币种</th>
                                <th>生效日期</th>
                                <th>失效日期</th>
                                <th>年基准</th>
                                <th>最后修改日期</th>
                                <th>机构代码</th>


                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
