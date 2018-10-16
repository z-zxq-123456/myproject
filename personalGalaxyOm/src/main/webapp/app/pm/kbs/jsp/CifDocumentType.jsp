<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_DOCUMENT_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifDocumentType.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">证件类型：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="documentType" id="DOCUMENT_TYPE" name="documentType"  >
                        </div>
                        <label class="form-label">适用范围：</label>
                        <div class="formControls  span2">
                                <select id="APP_IND" name="appInd" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                        <option value="I" >I-个体客户</option>
                                        <option value="C" >C-非个体</option>
                                        <option value="B" >B-个体或者非个体</option>
                                </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifDocumentType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>证件类型</th>
                        <th>证件类型描述</th>
                        <th>适用范围</th>
                        <th>法人代码</th>
                        <th>证件类型简称</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>