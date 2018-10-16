<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表PROD_MUTUAL_EXCLUSIVE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/prodMutualExclusiveMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="prodMutualExclusiveMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>客户类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="clientTypeMe" name="clientTypeMe" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>互斥产品类型：</label>
                    <div class="formControls span6">
                                <select id="prodTypeExclusive" name="prodTypeExclusive" data-placeholder="互斥产品类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
                    <div class="formControls span6">
                                <select id="prodTypeMut" name="prodTypeMut" data-placeholder="产品类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
