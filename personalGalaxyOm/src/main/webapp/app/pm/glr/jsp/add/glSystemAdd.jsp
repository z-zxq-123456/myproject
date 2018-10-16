<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_SYSTEM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glSystemAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glSystemAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqNo" id="seqNo" name="GL_SYSTEM" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>GL：</label>
						<div class="formControls span6">
									<select id="twoBook" name="twoBook" data-placeholder="GL" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>总账账户月末余额信息保留期限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctHistPeriodNo" id="acctHistPeriodNo" name="GL_SYSTEM" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否自动创建内部帐户：</label>
						<div class="formControls span6">
									<select id="autoIntAcctCreation" name="autoIntAcctCreation" data-placeholder="是否自动创建内部帐户" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>税率帐户类型：</label>
						<div class="formControls span6">
								<select id="taxAcctType" name="taxAcctType" data-placeholder="税率帐户类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-内部</option>
										<option value="C" > C-客户级</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>运行日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="runDate" id="runDate" name="GL_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>损益评估：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="revalByPost" id="revalByPost" name="GL_SYSTEM" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利润中心：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentre" id="profitCentre" name="GL_SYSTEM" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>GL：</label>
						<div class="formControls span6">
									<select id="osCloseInd" name="osCloseInd" data-placeholder="GL" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>下一运行日：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="nextRunDate" id="nextRunDate" name="GL_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>MUTI_SETTLE_MODE：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="mutiSettleMode" id="mutiSettleMode" name="GL_SYSTEM" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>最大将来起息天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxFuturedateDay" id="maxFuturedateDay" name="GL_SYSTEM" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>最大倒起息天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxBackdateDay" id="maxBackdateDay" name="GL_SYSTEM" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>上一运行日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastRunDate" id="lastRunDate" name="GL_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否批处理：</label>
						<div class="formControls span6">
									<select id="isBatchFinished" name="isBatchFinished" data-placeholder="是否批处理" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>应收利息科目：</label>
						<div class="formControls span6">
											<select id="intReceivable" name="intReceivable" data-placeholder="应收利息科目" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利息帐户类型：</label>
						<div class="formControls span6">
								<select id="intAcctType" name="intAcctType" data-placeholder="利息帐户类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="I" >I-内部</option>
										<option value="C" >C-客户级</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>总账历史保存期类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="histPeriodType" id="histPeriodType" name="GL_SYSTEM" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>总账历史保存期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="histPeriodNo" id="histPeriodNo" name="GL_SYSTEM" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>法人：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="company" id="company" name="GL_SYSTEM" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>营业税缴税频率：</label>
						<div class="formControls span6">
											<select id="taxFreq" name="taxFreq" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>基础币种：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="baseCcy" datatype="*1-3" id="baseCcy" name="baseCcy" tipmsg="格式错误!" nullmsg=" 请输入！">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否自动产生交易参考号：</label>
						<div class="formControls span6">
									<select id="autoRef" name="autoRef"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>资产类型挂账科目：</label>
						<div class="formControls span6">
											<select id="suspAssetAcct" name="suspAssetAcct" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否合并清算分录：</label>
						<div class="formControls span6">
									<select id="multisettleInd" name="multisettleInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>表外过度科目-资产类：</label>
						<div class="formControls span6">
											<select id="suspContAsset" name="suspContAsset" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>营业税下个缴税日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="nextCycleDate" datatype="*0-8" id="nextCycleDate" name="nextCycleDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>表外过度科目-负债类：</label>
						<div class="formControls span6">
											<select id="suspContLiab" name="suspContLiab" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>销户挂账科目：</label>
						<div class="formControls span6">
											<select id="osCloseBal" name="osCloseBal" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>历史数据起始日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastPostingRetentionDate" datatype="*0-8" id="lastPostingRetentionDate" name="lastPostingRetentionDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>结算挂账科目-负债：</label>
						<div class="formControls span6">
											<select id="suspSettleLiab" name="suspSettleLiab" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>未知：</label>
						<div class="formControls span6">
											<select id="retainEarnings" name="retainEarnings" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应付利息科目：</label>
						<div class="formControls span6">
											<select id="intPayable" name="intPayable" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易参考号前缀 默认GL：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="refPrefix" datatype="*0-3" id="refPrefix" name="refPrefix" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>跨帐套过渡科目：</label>
						<div class="formControls span6">
											<select id="interAcctgBk" name="interAcctgBk" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>结算挂账科目-资产：</label>
						<div class="formControls span6">
											<select id="suspSettleAsset" name="suspSettleAsset" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>营业税上个缴税日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prevCycleDate" datatype="*0-8" id="prevCycleDate" name="prevCycleDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>其他应付款科目：</label>
						<div class="formControls span6">
											<select id="glCashCode" name="glCashCode" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>本地币种汇率类型：</label>
						<div class="formControls span6">
											<select id="defaultRateType" name="defaultRateType" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否行内结售汇平盘：</label>
						<div class="formControls span6">
									<select id="defaultChargeRateType" name="defaultChargeRateType"  class="select2"  tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认机构：</label>
						<div class="formControls span6">
											<select id="defaultBranch" name="defaultBranch" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>负债类挂账科目：</label>
						<div class="formControls span6">
											<select id="suspLiabAcct" name="suspLiabAcct" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！" datatype="*">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>币种控制帐户：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ccyCtrlAcct" datatype="*0-16" id="ccyCtrlAcct" name="ccyCtrlAcct" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>联机收取/批量收取：</label>
						<div class="formControls span6">
								<select id="boInd" name="boInd" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="O" >O-联机</option>
										<option value="B" >B-批量</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否合并清算流水：</label>
						<div class="formControls span6">
									<select id="batchConsInd" name="batchConsInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>本币：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="localCcy" datatype="*1-3" id="localCcy" name="localCcy" tipmsg="格式错误!" nullmsg=" 请输入！">
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
