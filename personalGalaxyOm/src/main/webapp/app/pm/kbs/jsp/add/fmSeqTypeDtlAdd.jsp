<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_SEQ_TYPE_DTL添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmSeqTypeDtlAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmSeqTypeDtlAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqType" id="seqType" name="FM_SEQ_TYPE_DTL" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序列重置参数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqResetParam" id="seqResetParam" name="FM_SEQ_TYPE_DTL" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>终止号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="endNo" id="endNo" name="FM_SEQ_TYPE_DTL" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>开始号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="startNo" id="startNo" name="FM_SEQ_TYPE_DTL" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>最后使用号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastNo" datatype="*0-30" id="lastNo" name="lastNo" tipmsg="格式错误!" >
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
