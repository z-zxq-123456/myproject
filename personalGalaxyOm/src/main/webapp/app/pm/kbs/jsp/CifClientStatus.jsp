<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_CLIENT_STATUS交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifClientStatus.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">状态：</label>
                        <div class="formControls  span2">
                                        <select id="CLIENT_STATUS" class="select2" name="clientStatus" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                        <label class="form-label">是否为劣质客户：</label>
                        <div class="formControls  span2">
                                    <select id="BAD_CLIENT_IND"  class="select2" name="badClientInd" size="1"  style="width:100%">
                                        <option value="" selected="selected">空</option>
                                        <option value="Y" >是</option>
                                        <option value="N" >否</option>
                                    </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifClientStatus" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>状态</th>
                        <th>状态描述</th>
                        <th>是否为劣质客户</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
