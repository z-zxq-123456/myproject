<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_SYSTEM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmSystemAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmSystemAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>银行全称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="coyName" id="coyName" name="FM_SYSTEM" datatype="*1-40" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>所属机构：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="interBranchInd" id="interBranchInd" name="FM_SYSTEM" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否连续使用指定的数字区间标志：</label>
						<div class="formControls span6">
									<select id="continuousRun" name="continuousRun" data-placeholder="是否连续使用指定的数字区间标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>银行简称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="coyShort" id="coyShort" name="FM_SYSTEM" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>下一运行日：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="nextRunDate" id="nextRunDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>SYMBOLS总账分离标志：</label>
						<div class="formControls span6">
									<select id="glInd" name="glInd" data-placeholder="SYMBOLS总账分离标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本年年末日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="yrEndDate" id="yrEndDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>本月月末日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="mthEndDate" id="mthEndDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>半年末日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="halfEndDate" id="halfEndDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>批处理阶段标志：</label>
						<div class="formControls span6">
									<select id="processSplitInd" name="processSplitInd" data-placeholder="批处理阶段标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>资料不全客户冻结周期：</label>
						<div class="formControls span6">
									<select id="clientBlockFreq" name="clientBlockFreq" data-placeholder="资料不全客户冻结周期" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>自动冻结黑名单客户：</label>
						<div class="formControls span6">
									<select id="autoLockBlClient" name="autoLockBlClient" data-placeholder="自动冻结黑名单客户" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>系统所处的阶段：</label>
						<div class="formControls span6">
								<select id="systemPhase" name="systemPhase" data-placeholder="系统所处的阶段" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="INP" >INP-日间</option>
										<option value="EOD" >EOD-日终</option>
										<option value="SOD" >SOD-日始</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否自动生成客户号：</label>
						<div class="formControls span6">
									<select id="autoClientGen" name="autoClientGen" data-placeholder="是否自动生成客户号" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>运行日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="runDate" id="runDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否产品版30E计算天数：</label>
						<div class="formControls span6">
									<select id="product30e" name="product30e" data-placeholder="是否产品版30E计算天数" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>季末日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="qurEndDate" id="qurEndDate" name="FM_SYSTEM" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否自动生成抵质押编号：</label>
						<div class="formControls span6">
									<select id="autoCollGen" name="autoCollGen" data-placeholder="是否自动生成抵质押编号" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上一运行日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastRunDate" datatype="*0-8" id="lastRunDate" name="lastRunDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否记录出错时的业务数据信息：</label>
						<div class="formControls span6">
									<select id="isError" name="isError"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否记录业务数据信息：</label>
						<div class="formControls span6">
									<select id="isDebug" name="isDebug"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>限制币种：</label>
						<div class="formControls span6">
											<select id="limitCcy" name="limitCcy" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>本币：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="localCcy" datatype="*0-3" id="localCcy" name="localCcy" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>总行层级代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="mainBranchCode" datatype="*0-5" id="mainBranchCode" name="mainBranchCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认批处理用户：</label>
						<div class="formControls span6">
									<select id="batchDefaultUserId" name="batchDefaultUserId"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>多法人机构间清算方式：</label>
						<div class="formControls span6">
								<select id="multiCorporationMethod" name="multiCorporationMethod" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="I" >I-系统内清算模式</option>
										<option value="P" >P-支付清算模式</option>
										<option value="N" >N-不允许法人间通存通兑</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>多法人是否允许跨法人查询标志：</label>
						<div class="formControls span6">
									<select id="multiCorpQueryAllow" name="multiCorpQueryAllow"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>敞口类型：</label>
						<div class="formControls span6">
									<select id="npvGapType" name="npvGapType"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>报表币种：</label>
						<div class="formControls span6">
											<select id="reportCcy" name="reportCcy" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认存款账户限制类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="rbRestraintType" datatype="*0-3" id="rbRestraintType" name="rbRestraintType" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否多法人系统：</label>
						<div class="formControls span6">
									<select id="multiCorporationFlag" name="multiCorporationFlag"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>分行间清算科目-同业存放：</label>
						<div class="formControls span6">
									<select id="interBranchAcctHo" name="interBranchAcctHo"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>借贷检查标志：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="crDrCheckFlag" datatype="*0-1" id="crDrCheckFlag" name="crDrCheckFlag" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>法人：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户号结构类型：</label>
						<div class="formControls span6">
									<select id="clientNoStructureType" name="clientNoStructureType"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>投资资金：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="capitalFunds" id="capitalFunds" name="capitalFunds" datatype="n0-17"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>当前批处理的业务组编号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="batchUnit" datatype="*0-20" id="batchUnit" name="batchUnit" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>当前批处理的模块号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="batchModule" datatype="*0-2" id="batchModule" name="batchModule" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>批处理检查标志：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="batchCheckFlag" datatype="*0-1" id="batchCheckFlag" name="batchCheckFlag" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>基础币种：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="baseCcy" datatype="*0-3" id="baseCcy" name="baseCcy" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>DAC校验标志：</label>
						<div class="formControls span6">
									<select id="dacInd" name="dacInd"  class="select2"  tipmsg="格式错误!">
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
											<select id="defaultBranch" name="defaultBranch" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否行内结售汇平盘：</label>
						<div class="formControls span6">
									<select id="internalRateChargeFlag" name="internalRateChargeFlag"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>总行清算行内部客户：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="headOfficeClient" datatype="*0-12" id="headOfficeClient" name="headOfficeClient" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否行内结售汇平盘：</label>
						<div class="formControls span6">
									<select id="defaultChargeRateType" name="defaultChargeRateType"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>汇率浮动百分比：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="exchangeRateVariance" datatype="*0-15" id="exchangeRateVariance" name="exchangeRateVariance" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>电子银行：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ebhBranch" datatype="*0-6" id="ebhBranch" name="ebhBranch" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认本地汇率类型：</label>
						<div class="formControls span6">
											<select id="defaultRateTypeLocal" name="defaultRateTypeLocal" class="select2" tabindex="4" tipmsg="格式错误!">
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
						<label class="form-label span4"><span class="c-red"></span>默认利润中心：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="defaultProfitCentre" datatype="*0-12" id="defaultProfitCentre" name="defaultProfitCentre" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>允许查询的历史天数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allowBackqryDay" datatype="*0-5" id="allowBackqryDay" name="allowBackqryDay" tipmsg="格式错误!" >
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
