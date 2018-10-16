<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_CLIENT交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifClient.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">客户号：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="clientNo" id="CLIENT_NO" name="clientNo"  >
                        </div>
                        <label class="form-label">地址：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="location" id="LOCATION" name="location"  >
                        </div>
                        <label class="form-label">证件类型：</label>
                        <div class="formControls  span2">
                                        <select id="GLOBAL_ID_TYPE" class="select2" name="globalIdType" tabindex="4" size="1"  style="width:100%">
                                        </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifClient" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>客户号</th>
                        <th>交易状态 A-活动 B-冻结 C-关闭</th>
                        <th>客户经理</th>
                        <th>分类类别</th>
                        <th>省、州</th>
                        <th>风险控制国家</th>
                        <th>国籍</th>
                        <th>居住国家</th>
                        <th>客户分类</th>
                        <th>客户简称</th>
                        <th>境内境外 I-境内 O-境外</th>
                        <th>地址</th>
                        <th>是否临时客户 Y-临时客户 N-正式客户</th>
                        <th>客户等级</th>
                        <th>控制分行</th>
                        <th>客户城市</th>
                        <th>证件类型</th>
                        <th>状态</th>
                        <th>证件号码</th>
                        <th>客户名称(中)</th>
                        <th>客户名称(英)</th>
                        <th>法人代码</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
