<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_SYSTEM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlSystemAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlSystemAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>银行全称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="coyName" id="coyName" name="IRL_SYSTEM" datatype="*1-40" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>银行简称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="coyShort" id="coyShort" name="IRL_SYSTEM" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>运行日期：</label>
						<div class="formControls span6">
								<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd'})" id="runDate" name="runDate" class="input-text Wdate" style="width:265px;">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>基础币种：</label>
						<div class="formControls span6">
											<select id="baseCcy" name="baseCcy" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>当地币种：</label>
						<div class="formControls span6">
											<select id="localCcy" name="localCcy" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认机构：</label>
						<div class="formControls span6">
											<select id="defaultBranch" name="defaultBranch" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上一运行日期：</label>
						<div class="formControls span6">
							 <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="lastRunDate" name="lastRunDate" class="input-text Wdate" style="width:265px;">
									</div>
						<div class="span2"> </div>
					</div>


					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>下一运行日：</label>
						<div class="formControls span6">
													 <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="nextRunDate" name="nextRunDate" class="input-text Wdate" style="width:265px;">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>默认汇率类型：</label>
						<div class="formControls span6">
											<select id="defaultRateType" name="defaultRateType" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
					<label class="form-label span4"><span class="c-red"></span>报价余额类型：</label>
					<div class="formControls span6">
										<select id="quoteBalanceType" name="quoteBalanceType" datatype="*0-2" class="select2" tabindex="4" tipmsg="格式错误!">
											<option value="CA" >CA-现钞</option>
											<option value="TT" >TT-现汇</option>
										</select>
					</div>
					<div class="span2"> </div>
				</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>计提事件类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="intEventValue" datatype="*0-10" id="intEventValue" name="intEventValue" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>计提是否并标志：</label>
						<div class="formControls span6">
								<select id="glMergeType" name="glMergeType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="Y" >Y-是</option>
										<option value="N" >N-否</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>


					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>结售汇内部平盘汇率类型：</label>
						<div class="formControls span6">
											<select id="defaultChargeRateType" name="defaultChargeRateType" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
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


