<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_PRODUCT_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/cmc/js/add/cmcProductInfoAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcProductInfoAdd">
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardProductCode" id="cardProductCode" name="CMC_PRODUCT_INFO" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡产品名称：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardProductName" id="cardProductName" name="CMC_PRODUCT_INFO" datatype="*0-50" tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡BIN序号：</label>
				<div class="formControls span6">
					<select id="binOrder" name="CMC_PRODUCT_INFO" data-placeholder="卡BIN序号" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>发行渠道类型：</label>
				<div class="formControls span6">
					<select id="publishChannel" name="CMC_PRODUCT_INFO" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">请选择</option>
						<option value="1" >1-本行渠道发卡</option>
						<option value="2" >2-行业合作发卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>产品系列编号：</label>
				<div class="formControls span6">
					<select id="cardTypeCode" name="CMC_PRODUCT_INFO" data-placeholder="产品系列编号" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
					</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>产品系列名称：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardTypeName" id="cardTypeName" name="CMC_PRODUCT_INFO" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="disabled">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>产品种类：</label>
				<div class="formControls span6">
					<select id="cardProductType" name="CMC_PRODUCT_INFO" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">请选择</option>
						<option value="0" >0-虚户类</option>
						<option value="1" >1-实户类</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>借贷标识：</label>
				<div class="formControls span6">
					<select id="cardCrdbflg" name="CMC_PRODUCT_INFO" class="select2" datatype="/^\d{1}$/" tipmsg="格式错误!" nullmsg="请选择！">
						<option value="" selected="selected">请选择</option>
						<option value="1" >1-借记</option>
						<option value="2" >2-贷记</option>
						<option value="3" >3-借贷合一</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>规则序号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardNoType" id="cardNoType" name="CMC_PRODUCT_INFO" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="disabled">
				</div>
				<div class="span2"> </div>
			</div>

			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>启用标志：</label>
				<div class="formControls span6">
					<select id="enableFlag" name="CMC_PRODUCT_INFO" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">请选择</option>
						<option value="0" >0-启用</option>
						<option value="1" >1-不启用</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>

			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>启用日期：</label>
				<div class="formControls span3">
					<input type="text" onfocus="WdatePicker()" id="enableDate" name="enableDate" class="input-text Wdate">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡片物理性质：</label>
				<div class="formControls span6">
					<select id="cardPhySort" name="CMC_PRODUCT_INFO" class="select2" tipmsg="格式错误!" disabled="true">
						<option value="1" selected="selected">1-虚拟卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>atm密码错误限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="0" placeholder="atmErrNum" id="atmErrNum" name="CMC_PRODUCT_INFO" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="true" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>密码错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="totalErrNum" id="totalErrNum" name="CMC_PRODUCT_INFO" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>cvn错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="0" placeholder="cvnTotErrNum" id="cvnTotErrNum" name="CMC_PRODUCT_INFO" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="true" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>cvn2错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cvn2TotErrNum" id="cvn2TotErrNum" name="CMC_PRODUCT_INFO" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡号校验位错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="0" placeholder="lastTotErrNum" id="lastTotErrNum" name="CMC_PRODUCT_INFO" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="true" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>主帐户的币种：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="CCY" placeholder="ccy" id="ccy" name="CMC_PRODUCT_INFO" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="true" >
				</div>
				<div class="span2"> </div>
			</div>


			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>最大持卡量：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="maxHoldNo" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" id="maxHoldNo" name="CMC_PRODUCT_INFO" tipmsg="格式错误!" nullmsg=" 请输入！" >
				</div>
				<div class="span2"> </div>
			</div>


			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡有效期使用方式：</label>
				<div class="formControls span6">
					<select id="setFixExDate" name="CMC_PRODUCT_INFO" class="select2" tipmsg="格式错误!" nullmsg=" 请选择！">
						<option value="" selected="selected">请选择</option>
						<option value="0" >0-使用有效期</option>
						<option value="1" >1-使用固定有效期</option>
						<option value="2" >2-不使用有效期</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡固定有效期：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="20991231" placeholder="fixExDate" datatype="*0-8" id="fixExDate" name="CMC_PRODUCT_INFO" tipmsg="格式错误!" disabled="disabled">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加" value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
