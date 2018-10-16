<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_SYSTEM交易</title>
            <script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlSystem.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">银行全称：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="coyName" id="COY_NAME" name="coyName"  >
                        </div>
                        <label class="form-label">银行简称：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="coyShort" id="COY_SHORT" name="coyShort"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlSystem" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                            <th>银行全称</th>
                            <th>银行简称</th>
                            <th>运行日期</th>
                            <th>基础币种</th>
                            <th>当地币种</th>
                            <th>默认机构</th>
                            <th>上一运行日期</th>
                            <th>下一运行日</th>
                            <th>默认汇率类型</th>
                             <th>报价余额类型</th>
                            <th>计提事件类型</th>
                            <th>计提是否并标志</th>
                            <th>结售汇内部平盘汇率类型</th>






                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>

