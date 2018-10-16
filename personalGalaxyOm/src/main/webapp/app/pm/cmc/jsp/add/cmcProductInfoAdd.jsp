<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_PRODUCT_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/add/cmcProductInfoAdd.js"></script>
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
					<input type="text" class="input-text grid" value="" placeholder="cardProductName" datatype="*0-50" id="cardProductName" name="cardProductName" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡BIN序号：</label>
				<div class="formControls span6">
					<select id="binOrder" name="binOrder" data-placeholder="卡BIN序号" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>发行渠道类型：</label>
				<div class="formControls span6">
					<select id="publishChannel" name="publishChannel" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="1" >1-本行渠道发卡</option>
						<option value="2" >2-行业合作发卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>产品系列编号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardTypeCode" id="cardTypeCode" name="CMC_PRODUCT_INFO" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>产品系列名称：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardTypeName" datatype="*0-128" id="cardTypeName" name="cardTypeName" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>产品种类：</label>
				<div class="formControls span6">
					<select id="cardProductType" name="cardProductType" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="0" >0-虚户类</option>
						<option value="1" >1-实户类</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>借贷标识：</label>
				<div class="formControls span6">
					<select id="cardCrdbflg" name="cardCrdbflg" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="1" >1-借记</option>
						<option value="2" >2-贷记</option>
						<option value="3" >3-借贷合一</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>规则序号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardNoType" datatype="*0-4" id="cardNoType" name="cardNoType" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>年费等级：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="feeLevel" datatype="*0-4" id="feeLevel" name="feeLevel" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>级别编号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cardLevel" datatype="*0-1" id="cardLevel" name="cardLevel" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>启用标志：</label>
				<div class="formControls span6">
					<select id="enableFlag" name="enableFlag" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="0" >0-启用</option>
						<option value="1" >1-不启用</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>启用日期：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="enableDate" datatype="*0-8" id="enableDate" name="enableDate" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>序号启用标志：</label>
				<div class="formControls span6">
					<select id="orderFlag" name="orderFlag" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="1" >1-全部做序号</option>
						<option value="2" >2-部分（使用起始序号和终止序号）</option>
						<option value="3" >3-有吉祥号可选</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>起始序号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="beginSeq" datatype="*0-12" id="beginSeq" name="beginSeq" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>终止序号：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="endSeq" datatype="*0-12" id="endSeq" name="endSeq" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡片物理性质：</label>
				<div class="formControls span6">
					<select id="cardPhySort" name="cardPhySort" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="1" >1-虚拟卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>atm密码错误限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="atmErrNum" id="atmErrNum" name="CMC_PRODUCT_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>密码错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="totalErrNum" id="totalErrNum" name="CMC_PRODUCT_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>cvn错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cvnTotErrNum" id="cvnTotErrNum" name="CMC_PRODUCT_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>cvn2错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="cvn2TotErrNum" id="cvn2TotErrNum" name="CMC_PRODUCT_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>卡号校验位错误总限制次数：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="lastTotErrNum" id="lastTotErrNum" name="CMC_PRODUCT_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>主帐户的币种：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="ccy" id="ccy" name="CMC_PRODUCT_INFO" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>记名卡/非记名卡标志：</label>
				<div class="formControls span6">
					<select id="markFlg" name="markFlg" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="a" >a-全部都可以</option>
						<option value="0" >0-非记名卡</option>
						<option value="1" >1- 记名卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>开卡手续费：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="activateFee" datatype="*0-20" id="activateFee" name="activateFee" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>最大持卡量：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="maxHoldNo" datatype="*0-10" id="maxHoldNo" name="maxHoldNo" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>密码标志：</label>
				<div class="formControls span6">
					<select id="pswdMark" name="pswdMark" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="0" >0-不检查密码</option>
						<option value="1" >1-按交易判断</option>
						<option value="2" >2-必须检查密码</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>挂失标志：</label>
				<div class="formControls span6">
					<select id="lostMark" name="lostMark" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="0" >0-挂失</option>
						<option value="1" >1-不挂失</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡有效期期限：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="exDate" datatype="*0-10" id="exDate" name="exDate" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡有效期使用方式：</label>
				<div class="formControls span6">
					<select id="setFixExDate" name="setFixExDate" class="select2" tipmsg="格式错误!">
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
					<input type="text" class="input-text grid" value="" placeholder="fixExDate" datatype="*0-8" id="fixExDate" name="fixExDate" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>卡产品固定有效期：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="invalidDate" datatype="*0-8" id="invalidDate" name="invalidDate" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>限制接口：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="interfacePermit" datatype="*0-100" id="interfacePermit" name="interfacePermit" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>发卡对象：</label>
				<div class="formControls span6">
					<select id="issueTarget" name="issueTarget" class="select2" tipmsg="格式错误!">
						<option value="" selected="selected">空</option>
						<option value="1" >1-个人卡</option>
						<option value="2" >2-单位卡</option>
					</select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>起存金额：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="beginAmt" datatype="*0-21" id="beginAmt" name="beginAmt" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>限制贡献值：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="convalue" datatype="*0-10" id="convalue" name="convalue" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>最后更新日期：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="lastTranDate" datatype="*0-8" id="lastTranDate" name="lastTranDate" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>交易时间戳：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="tranTimestamp" datatype="*0-17" id="tranTimestamp" name="tranTimestamp" tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
		<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易时间：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranTime" datatype="*0-20" id="tranTime" name="tranTime" tipmsg="格式错误!" >
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
