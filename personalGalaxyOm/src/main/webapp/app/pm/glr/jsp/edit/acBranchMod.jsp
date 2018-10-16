<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表AC_BRANCH修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/acBranchMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="acBranchMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="branch" name="branch" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>清算层级：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="settleLevel" id="settleLevel" name="settleLevel" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上级清算科目：</label>
                    <div class="formControls span6">
                                <select id="settleSubjectUp" name="settleSubjectUp" datatype="*"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>清算科目：</label>
                    <div class="formControls span6">
                                <select id="settleSubject" name="settleSubject" datatype="*"  class="select2" tipmsg="格式错误!"  >
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
                                <select id="settleBranch" name="settleBranch" datatype="*"  class="select2" tipmsg="格式错误!"  >
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
