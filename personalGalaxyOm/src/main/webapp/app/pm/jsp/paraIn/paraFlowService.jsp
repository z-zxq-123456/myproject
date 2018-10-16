<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
	<title>参数管理</title>
	<link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraIn/paraFlowService.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理入口</a><span >&gt;</span><span >参数流程服务</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="pd-20">
	<div class="row cl span-offset-1 span12 span-setoff-2">
        <label  class="form-label mt-5">交易：</label>
        <div class="span3"><input type="text" class="input-text grid" id="tableNameDisplay" style="border:none;display:none;" placeholder="表名"  name="tableNameDisplay" disabled = "true"></div>
        <label class="form-label mt-5">流水编号：</label>
        <div class="span3"><input type="text" class="input-text grid" id="appSeriesNumber" style="border:none;display:none;" placeholder="申请单号"  name="appSeriesNumber" disabled = "true"></div>
        <label class="form-label mt-5">当前状态：</label>
        <div class="span2"><input type="text" class="input-text grid" id="tableStatus" style="border:none;display:none;" placeholder="当前状态"  name="tableStatus" disabled = "true"></div>
	</div>
	<div class="step-body span-offset-1 span10 span-setoff-1 mt-20" id="handleTransactionStep" >
    		<div class="step-header" >
    			<ul>
    				<li><p>选择系统</p></li>
    				<li><p>选择模块</p></li>
    				<li><p>选择交易</p></li>
                    <li><p>查看参数</p></li>
    				<li><p>操作参数</p></li>
    			</ul>
    		</div>
    		<div class="mt-50 step-list">
    				<div class="text-c">
						<label class="form-label span2 forSelect2"><span class="c-red">*</span>目标系统ID：</label>
						<div class="formControls span4">
							<select class="select2" size="1" id="systemId" name="systemId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" >
							 </select>
						</div>
    					<a id="selectSystem" class="button-select disabled"  style="display:none;">下一步</a>
    				</div>
    		</div>
    		<div class="mt-50 step-list">
    				<div class="text-c"><!--2-->
    					<label class="form-label span2 forSelect2"><span class="c-red">*</span>模块代码：</label>
						<div class="formControls span4">
							<select style="width:100%" class="select2" size="1" id="moduleId" name="moduleId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
							 </select>
						</div>
    					<a id="selSystemBack" class="button-select">上一步</a>
    					<a id="selectModule" class="button-select disabled" style="display:none;">下一步</a>
    				</div><!--2-->
    		</div>
    		<div class="mt-50 step-list">
    			<div class="text-c"><!--3-->
    				<label class="form-label span2 forSelect2"><span class="c-red">*</span>交易名：</label>
						<div class="formControls span4">
							<select style="width:100%" class="select2" size="1" id="transactionId" name="transactionId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" >
							</select>
						</div>
					<a id="selModuleBack" class="button-select">上一步</a>
    				<a id="viewDataNext" class="button-select disabled" style="display:none;">查看数据</a>
                    <a id="handleData" class="button-select disabled" style="display:none;">下一步</a>
    			</div><!--3-->
    		</div>
    		<div class="mt-50 step-list">
    			<div class="text-c"><!--4-->
    				<a id="selTableBack" class="button-select">上一步</a>
                    <a id="viewData" class="button-select disabled" style="display:none;">查看数据</a>
                    <a id="importDataNext" class="button-select disabled" style="display:none;">导入数据</a>
                    <a id="editDataNext" class="button-select disabled" style="display:none;">录入数据</a>
                    <a id="nullifyDataNext" class="button-select disabled" style="display:none;">作废交易</a>
    			</div><!--4-->
    		</div>
            <div class="mt-50 step-list">
                <div class="text-c"><!--5-->
                    <a id="viewTableBack" class="button-select">上一步</a>
                    <a id="importData" class="button-select disabled"  style="display:none;">导入数据</a>
                    <a id="editData" class="button-select disabled"  style="display:none;">录入数据</a>
                    <a id="nullifyData" class="button-select disabled"  style="display:none;">作废交易</a>
                </div><!--5-->
            </div>
    	</div>
</div>
</body>
</html>
