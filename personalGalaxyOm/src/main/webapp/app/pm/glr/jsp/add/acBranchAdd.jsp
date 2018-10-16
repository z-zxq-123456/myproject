<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易AC_BRANCH添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/acBranchAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="acBranchAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>清算层级：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="settleLevel" id="settleLevel" name="AC_BRANCH" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上级清算科目：</label>
						<div class="formControls span6">
											<select id="settleSubjectUp" name="settleSubjectUp" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>清算科目：</label>
						<div class="formControls span6">
											<select id="settleSubject" name="settleSubject" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上级清算账户序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="settleAcctSeqUp" datatype="*0-20" id="settleAcctSeqUp" name="settleAcctSeqUp" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>清算账户序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="settleAcctSeq" datatype="*0-20" id="settleAcctSeq" name="settleAcctSeq" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>清算机构：</label>
						<div class="formControls span6">
											<select id="settleBranch" name="settleBranch" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
