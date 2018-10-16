<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>GL_CCY_RULE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/glr/js/GlCcyRule.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">买入币种：</label>
                        <div class="formControls  span2">
                                <select id="BUY_CCY" name="buyCcy" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="AUD" >AUD-澳大利亚元</option>
                                        <option value="CAD" >CAD-加拿大元</option>
                                        <option value="CHF" >CHF-瑞士法郎</option>
                                        <option value="CNY" >CNY-人民币</option>
                                        <option value="EUR" >EUR-欧元</option>
                                        <option value="GBP" >GBP-英镑</option>
                                        <option value="HKD" >HKD-港元</option>
                                        <option value="JPY" >JPY-日元</option>
                                        <option value="SGD" >SGD-新加坡元</option>
                                        <option value="USD" >USD-美元</option>
                                </select>
                        </div>
                        <label class="form-label">卖出币种：</label>
                        <div class="formControls  span2">
                                <select id="SELL_CCY" name="sellCcy" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="AUD" >AUD-澳大利亚元</option>
                                        <option value="CAD" >CAD-加拿大元</option>
                                        <option value="CHF" >CHF-瑞士法郎</option>
                                        <option value="CNY" >CNY-人民币</option>
                                        <option value="EUR" >EUR-欧元</option>
                                        <option value="GBP" >GBP-英镑</option>
                                        <option value="HKD" >HKD-港元</option>
                                        <option value="JPY" >JPY-日元</option>
                                        <option value="SGD" >SGD-新加坡元</option>
                                        <option value="USD" >USD-美元</option>
                                </select>
                        </div>
                        <label class="form-label">事件类型：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="eventType" id="EVENT_TYPE" name="eventType"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="glCcyRule" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>买入币种</th>
                        <th>卖出币种</th>
                        <th>事件类型</th>
                        <th>会计分录编号</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
