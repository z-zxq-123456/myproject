<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>IRL_MARKET_FACTOR_INFO交易</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/IrlMarketFactorInfo.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">

                        <label class="form-label">市场挂钩类型代码：</label>
                        <div class="formControls  span2">
                                      <select id="MARKET_TYPE" name="marketType"  class="select2" datatype="*1-3" tipmsg="格式错误!">
                                                              										<option value="HSI" >HSI-恒生指数</option>
                                                              										<option value="SSI" >SSI-沪深指数</option>
                                                              								</select>
                         </div>
                        <label class="form-label">指数值：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="marketValue" id="MARKET_VALUE" name="marketValue"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="irlMarketFactorInfo" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                                <th>市场指数类型代码</th>
                                <th>指数值</th>
                                  <th>生效日期</th>
                                <th>失效日期</th>
                                <th>状态</th>
                                <th>备注</th>

                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
