<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/IrlProdTypeInFormT.js"></script>
    <title>参数表IRL_PROD_TYPE</title>
</head>
<body>
<div class="padding-10" style="font-size:12px;text-align:left;">
    <form action="" method="post" class="form form-horizontal" id="irlProdTypePrimaryKey">
        <div class="row cl" style="display:none">
            <label class="form-label">利率启用方式：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="intApplType" id="INT_APPL_TYPE"
                       name="intApplType">
            </div>
            <label class="form-label">产品组：</label>
            <div class="formControls  span2">
                <select id="PROD_GRP" name="prodGrp" class="select2">
                    <option value="" selected="selected">空</option>
                    <option value="RB">RB-负债</option>
                    <option value="CL">CL-资产</option>
                    <option value="GL">GL-总账</option>
                    <option value="MM">MM-货币市场</option>
                </select>
            </div>
            <label class="form-label">产品类型：</label>
            <div class="formControls  span2">
                <input type="text" value="" class="input-text grid" placeholder="prodType" id="PROD_TYPE" name="prodType">
            </div>
            <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                <i class="iconfont">&#xe624;</i> 查询</a>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="irlProdType" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>产品类型</th>
                <th>产品类型描述</th>
                <th>产品组</th>
                <th>利率生效方式</th>
                <th>账户子类型</th>
                <th>法人代表</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>