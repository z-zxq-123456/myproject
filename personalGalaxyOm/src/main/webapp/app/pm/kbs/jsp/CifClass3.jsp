<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_CLASS_3交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifClass3.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">用户自定义的分类代码：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="class3" id="CLASS_3" name="class3"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifClass3" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>用户自定义的分类代码</th>
                        <th>对分类代码的描述</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>