<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_RULE_MESSAGE交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlRuleMessage.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">序号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="irlSeqNo" id="IRL_SEQ_NO" name="irlSeqNo"  >
                        </div>
                        <label class="form-label">规则分类2：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="ruleClass2" id="RULE_CLASS_2" name="ruleClass2"  >
                        </div>
                        <label class="form-label">规则分类3：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="ruleClass3" id="RULE_CLASS_3" name="ruleClass3"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlRuleMessage" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th style="width:160px;">序号</th>
                                <th style="width:160px;">规则分类1</th>
                                <th style="width:160px;">规则分类2</th>
                                <th style="width:160px;">规则分类3</th>
                                <th style="width:160px;">生效日期</th>
                                <th style="width:160px;">失效日期</th>
                                <th style="width:160px;">规则描述</th>
                                <th style="width:160px;">规则表达式</th>
                                <th style="width:160px;">浮动类型</th>
                                <th style="width:160px;">浮动值</th>
                                <th style="width:160px;">交易柜员</th>
                                <th style="width:160px;">创建日期</th>
                                <th style="width:160px;">状态</th>
                                <th style="width:160px;">规则引用信息</th>
                                <th style="width:160px;">特殊规则浮动信息</th>
                                <th style="width:160px;">币种组</th>
                                <th style="width:160px;">分组类型</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
