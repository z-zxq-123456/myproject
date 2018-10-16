<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CIF_DOCUMENT_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/cifDocumentTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cifDocumentTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>证件类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="documentType" name="documentType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>证件类型描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="documentTypeDesc" id="documentTypeDesc" name="documentTypeDesc" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>适用范围：</label>
                    <div class="formControls span6">
                        <select id="appInd" name="appInd" data-placeholder="适用范围" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="I" >I-个体客户</option>
                                <option value="C" >C-非个体</option>
                                <option value="B" >B-个体或者非个体</option>
                        </select>
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
                    <label class="form-label span4"><span class="c-red"></span>证件类型简称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="documentTypeShort" datatype="*0-30" id="documentTypeShort" name="documentTypeShort" tipmsg="格式错误!" >
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
