<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_PROFIT_CENTRE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmProfitCentre.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">利润中心：</label>
                        <div class="formControls  span2">
                                <select id="PROFIT_CENTRE" name="profitCentre" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="01" >01-会计结算部</option>
                                        <option value="02" >02-计划财务部</option>
                                        <option value="03" >03-个人业务部</option>
                                        <option value="04" >04-电子银行部</option>
                                        <option value="05" >05-国际业务部</option>
                                        <option value="06" >06-资金运营部</option>
                                        <option value="07" >07-公司业务部</option>
                                        <option value="08" >08-审计部</option>
                                        <option value="09" >09-授信部</option>
                                        <option value="10" >10-合规风险部</option>
                                        <option value="99" >99-缺省</option>
                                </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="fmProfitCentre" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>利润中心</th>
                        <th>中文说明</th>
                        <th>利润中心类型</th>
                        <th>英文说明</th>
                        <th>利润中心级别</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
