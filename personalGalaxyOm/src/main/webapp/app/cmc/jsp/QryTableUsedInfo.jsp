<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8"%>--%>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
    <%--request.getSession().setAttribute("basePath", basePath);--%>
    <%--request.setCharacterEncoding("utf-8");--%>
    <%--response.setContentType("text/html;charset=utf-8");--%>
<%--%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>预制卡已使用量统计</title>
    <!--script type="text/javascript" src=${ContextPath}/lib/chart-js/jquery-3.3.1.min.js></script-->
    <script type="text/javascript" src=${ContextPath}/lib/chart-js/highcharts.js></script>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/QryTableUsedInfo.js"></script>
</head>

<body>

<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>已发卡管理</a><span >&gt;</span><span >预制卡已使用量统计</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>

<div class="padding-10">
    <tr>
        <td>
        <label class="form-label mt-5">卡号规则：</label>
        <input type="text" class="input-text" value="" placeholder="卡号规则" id="productRuleNo" name="productRuleNo"style="width:15%;" ></br> </br>
        </td>
        <td>
        <label class="form-label mt-5">预制卡表：</label>
        <input type="text" class="input-text" value="" placeholder="预制卡表" id="tableName" name="tableName"style="width:15%;" >
        </td>
        <a id="selectByProductRuleNo" class="button-sure M" >查询</a>
    </tr>
</div>
<div class="padding-10">
    <tr>
    <td>
        <label class="form-label mt-5">已使用量：</label>
        <input type="text" class="input-text" value="" placeholder="已使用量" id="usedNum" name="usedNum" style="width:15%;" disabled ="disabled"></br> </br>
    </td>
    <td>
        <label class="form-label mt-5">总数量：</label>
        <input type="text" class="input-text" value="" placeholder="总数量" id="totalNum" name="totalNum" style="width:15%;" disabled ="disabled">
    </td>
    </tr>
</div>
<div id="container" style="min-width:300px;height:300px">

</div>

</body>
</html>