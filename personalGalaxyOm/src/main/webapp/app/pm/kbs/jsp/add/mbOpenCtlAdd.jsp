<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_OPEN_CTL添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbOpenCtlAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbOpenCtlAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
						<div class="formControls span6">
											<select id="clientType" name="clientType" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>控制要素：</label>
						<div class="formControls span6">
								<select id="ctrlAttr" name="ctrlAttr" data-placeholder="控制要素" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="P" >P-产品类型 </option>
										<option value="A" >A-账户属性</option>
										<option value="D" >D-凭证类型 </option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>要素取值：</label>
						<div class="formControls span6">
							<select id="ctrlValue" name="ctrlValue" data-placeholder="" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
							</select>

						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易处理方式取值：</label>
						<div class="formControls span6">
								<select id="dealFlow" name="dealFlow" data-placeholder="交易处理方式取值" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-授权处理</option>
										<option value="B" >B-拒绝处理</option>
										<option value="D" >D-提醒处理；</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>数量：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="quantity" id="quantity" name="MB_OPEN_CTL" datatype="n1-10,/^[1-9]\d*$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
						<div class="formControls span6">
											<select id="company" name="company" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<%--<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易时间：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranTime" id="tranTime" name="tranTime" datatype="n0-11"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>--%><%--
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易时间戳：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranTimestamp" datatype="*0-17" id="tranTimestamp" name="tranTimestamp" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>--%>
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
