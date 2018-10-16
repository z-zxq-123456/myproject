<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_SALUTATION交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifSalutation.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">称呼代码：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="salutation" id="SALUTATION" name="salutation"  >
                        </div>
                        <label class="form-label">适用性别：</label>
                        <div class="formControls  span2">
                                <select id="GENDER_FLAG" name="genderFlag" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="M" >M-男</option>
                                        <option value="F" >F-女</option>
                                        <option value="U" >U-未知</option>
                                </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifSalutation" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>称呼代码</th>
                        <th>称呼代码说明</th>
                        <th>法人代码</th>
                        <th>适用性别</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
