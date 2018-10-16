<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_CLIENT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifClientAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifClientAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientNo" id="clientNo" name="CIF_CLIENT" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>交易状态 A-活动 B-冻结 C-关闭：</label>
						<div class="formControls span6">
								<select id="tranStatus" name="tranStatus" data-placeholder="交易状态 A-活动 B-冻结 C-关闭" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-活动</option>
										<option value="B" >B-冻结</option>
										<option value="C" >C-关闭</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户经理：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctExec" id="acctExec" name="CIF_CLIENT" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分类类别：</label>
						<div class="formControls span6">
											<select id="categoryType" name="categoryType" data-placeholder="分类类别" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>省、州：</label>
						<div class="formControls span6">
											<select id="stateLoc" name="stateLoc" data-placeholder="省、州" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>风险控制国家：</label>
						<div class="formControls span6">
											<select id="countryRisk" name="countryRisk" data-placeholder="风险控制国家" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>国籍：</label>
						<div class="formControls span6">
											<select id="countryLoc" name="countryLoc" data-placeholder="国籍" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>居住国家：</label>
						<div class="formControls span6">
											<select id="countryCitizen" name="countryCitizen" data-placeholder="居住国家" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
						<div class="formControls span6">
											<select id="clientType" name="clientType" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>客户简称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientShort" id="clientShort" name="CIF_CLIENT" datatype="*1-120" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>境内境外 I-境内 O-境外：</label>
						<div class="formControls span6">
								<select id="inlandOffshore" name="inlandOffshore" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="I" >I-境内</option>
										<option value="O" >O-境外</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>地址：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="location" datatype="*0-140" id="location" name="location" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否临时客户 Y-临时客户 N-正式客户：</label>
						<div class="formControls span6">
									<select id="tempClient" name="tempClient"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户等级：</label>
						<div class="formControls span6">
											<select id="crRating" name="crRating" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>控制分行：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ctrlBranch" datatype="*0-20" id="ctrlBranch" name="ctrlBranch" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户城市：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientCity" datatype="*0-6" id="clientCity" name="clientCity" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>证件类型：</label>
						<div class="formControls span6">
											<select id="globalIdType" name="globalIdType" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>状态：</label>
						<div class="formControls span6">
											<select id="clientStatus" name="clientStatus" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>证件号码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="globalId" datatype="*0-25" id="globalId" name="globalId" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户名称(中)：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="chClientName" datatype="*0-300" id="chClientName" name="chClientName" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户名称(英)：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="clientName" datatype="*0-300" id="clientName" name="clientName" tipmsg="格式错误!" >
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
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
