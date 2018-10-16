<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_LINK_CONDITION修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbLinkConditionMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbLinkConditionMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>链接条件规则ID：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="conditionId" name="conditionId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>规则描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="conditionDesc" id="conditionDesc" name="conditionDesc" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>链接条件规则：</label>
                    <div class="formControls span6">
                        <select id="conditionRule" name="conditionRule"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="OPEN" >OPEN-开户</option>
                                <option value="CLOSE" >CLOSE-销户</option>
                                <option value="BAL" >BAL-余额变化</option>
                                <option value="DUE" >DUE-到期</option>
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
