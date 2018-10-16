<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>FM_REF_CODE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/FmRefCode.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">取值范围：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="fieldValue" id="FIELD_VALUE" name="fieldValue"  >
                        </div>
                        <label class="form-label">语言：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="refLang" id="REF_LANG" name="refLang"  >
                        </div>
                        <label class="form-label">表字段：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="domain" id="DOMAIN" name="domain"  >
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="fmRefCode" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>取值范围</th>
                        <th>语言</th>
                        <th>表字段</th>
                        <th>取值的含义说明</th>
                        <th>参数行数</th>
                        <th>参数标志</th>
                        <th>法人代码</th>
                        <th>缩写</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
