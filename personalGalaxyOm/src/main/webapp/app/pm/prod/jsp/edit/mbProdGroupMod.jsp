<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_PROD_GROUP修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbProdGroupMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbProdGroupMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品子类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodSubType" name="prodSubType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>序号：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="seqNo" id="seqNo" name="seqNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否基础产品：</label>
                    <div class="formControls span6">
                            <select id="defaultProd" name="defaultProd"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
				<div class="row cl">
					<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
					<div class="formControls span6">
											<select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!">
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
