<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_EXTERNAL_BRANCH修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmExternalBranchMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmExternalBranchMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>银行代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="bankCode" name="bankCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="branchCode" name="branchCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>国家：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="country" datatype="*0-3" id="country" name="country" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>省市：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="state" datatype="*0-2" id="state" name="state" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>机构名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="branchName" datatype="*0-60" id="branchName" name="branchName" tipmsg="格式错误!" >
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
