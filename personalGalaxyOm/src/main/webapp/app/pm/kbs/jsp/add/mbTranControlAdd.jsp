<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_TRAN_CONTROL添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbTranControlAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbTranControlAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡客户等级：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cdCustGrade" id="cdCustGrade" name="MB_TRAN_CONTROL" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_TRAN_CONTROL" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>渠道：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodChannel" id="prodChannel" name="MB_TRAN_CONTROL" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>区域代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cdAreaCode" id="cdAreaCode" name="MB_TRAN_CONTROL" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>单次交易限额：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="singleTranLim" id="singleTranLim" name="singleTranLim" datatype="n0-17"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易笔数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranCount" id="tranCount" name="tranCount" datatype="n0-5"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>日累计限额：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="dayTranLim" id="dayTranLim" name="dayTranLim" datatype="n0-17"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>密码控制：</label>
						<div class="formControls span6">
									<select id="passwordCtr" name="passwordCtr"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
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
