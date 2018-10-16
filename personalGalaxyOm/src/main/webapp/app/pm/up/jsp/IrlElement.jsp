<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_ELEMENT交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlElement.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">因子名称：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="elementId" id="ELEMENT_ID" name="elementId"  >
                        </div>
                        <label class="form-label">因子类型：</label>
                        <div class="formControls  span2">
                                <select id="ELEMENT_TYPE" name="elementType" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="F" >F-费用因子</option>
                                        <option value="D" >D-折扣因子</option>
                                        <option value="L" >L-利率因子</option>
                                        <option value="E" >E-汇率因子</option>
                                </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlElement" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th>因子名称</th>
                                <th>因子类型</th>
                                <th>因子数据类型</th>
                                <th>因子长度</th>
                                <th>因子描述</th>
                                <th>因子取值对应的表</th>
                                <th>因子取值对应的字段</th>

                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
