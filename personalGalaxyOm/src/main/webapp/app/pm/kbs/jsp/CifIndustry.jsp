<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_INDUSTRY交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifIndustry.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">通用行业代码：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="industry" id="INDUSTRY" name="industry"  >
                        </div>
                        <label class="form-label">层级：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="industryLevel" id="INDUSTRY_LEVEL" name="industryLevel"  >
                        </div>
                        <label class="form-label">风险等级：</label>
                        <div class="formControls  span2">
                                <select id="RISK_LEVEL" name="riskLevel" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="H" >H-高</option>
                                        <option value="L" >L-低</option>
                                </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifIndustry" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>通用行业代码</th>
                        <th>是否明细代码</th>
                        <th>说明</th>
                        <th>层级</th>
                        <th>是否国际代码</th>
                        <th>风险等级</th>
                        <th>上级通用行业代码</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
