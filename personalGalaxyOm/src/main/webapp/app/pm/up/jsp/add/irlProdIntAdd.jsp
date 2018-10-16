<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_PROD_INT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlProdIntAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlProdIntAdd">

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<select id="prodType" name="prodType" data-placeholder="产品类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
						<div class="formControls span6">
											<select id="eventType" name="eventType" data-placeholder="事件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
				<div class="row cl">
                						<label class="form-label span4"><span class="c-red"></span>利率类型代码：</label>
                						<div class="formControls span6">
                											<select id="intType" name="intType" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
                											</select>
                						</div>
                						<div class="span2"> </div>
                					</div>
                <div class="row cl">
                						<label class="form-label span4"><span class="c-red">*</span>计息类型：</label>
                						<div class="formControls span6">
                											<select id="intClass" name="intClass" data-placeholder="计息类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                											</select>
                						</div>
                						<div class="span2"> </div>
                					</div>
               <div class="row cl">
               						<label class="form-label span4"><span class="c-red"></span>税率类型代码：</label>
               						<div class="formControls span6">
               											<select id="taxType" name="taxType" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
               											</select>
               						</div>
               						<div class="span2"> </div>
               					</div>
               								<div class="row cl">
                                            						<label class="form-label span4"><span class="c-red">*</span>利率计算金额编号：</label>
                                            						<div class="formControls span6">
                                            											<select id="rateAmtId" name="rateAmtId" data-placeholder="利率计算金额ID" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                            											</select>
                                            						</div>
                                            						<div class="span2"> </div>
                                            					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>利息计算金额编号：</label>
						<div class="formControls span6">
											<select id="intAmtId" name="intAmtId" data-placeholder="利息计算金额ID" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>重算利息方法：</label>
						<div class="formControls span6">
								<select id="recalMethod" name="recalMethod" data-placeholder="重算利息方法" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="N" >N-重新按模型计算</option>
										<option value="H" >H-取历史利率计算</option>
										<option value="I" >I-取历史</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>计息起始日期取值方法：</label>
						<div class="formControls span6">
								<select id="intStart" name="intStart" class="select2" datatype="*0-20" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="O" >O-开户日期</option>
										<option value="M" >M-到期日</option>
										<option value="I" >I-计提日期</option>
										<option value="C" >C-上一结息日</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>靠档天数计算方式：</label>
						<div class="formControls span6">
								<select id="intDaysType" name="intDaysType" class="select2" datatype="*0-20" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="A" >A-按存期</option>
										<option value="B" >B-实际天数</option>
										<option value="C" >C-计提天数</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
				<div class="row cl">
                						<label class="form-label span4"><span class="c-red"></span>利息计算方法：</label>
                						<div class="formControls span6">
                								<select id="intCalcBal" name="intCalcBal" class="select2" datatype="*0-20" tipmsg="格式错误!">
                									<option value="" selected="selected">空</option>
                										<option value="AB" >AB-积数计息</option>
                										<option value="EB" >EB-分段计息</option>
                										<option value="BS" >BS-差减法计息</option>
                								</select>
                						</div>
                						<div class="span2"> </div>
                					</div>
                	<div class="row cl">
                						<label class="form-label span4"><span class="c-red"></span>利率启用方式：</label>
                						<div class="formControls span6">
                								<select id="intApplType" name="intApplType" class="select2" datatype="*0-20" tipmsg="格式错误!">
                									<option value="" selected="selected">空</option>
                										<option value="N" >N-利率不变更</option>
                										<option value="A" >A-利率生效日当天生效</option>
                										<option value="S" >S-从下一个计息周期开始生效</option>
                										<option value="R" >R-按照约定的生效周期开始生效</option>
                								</select>
                						</div>
                						<div class="span2"> </div>
                					</div>
                	<div class="row cl">
                    						<label class="form-label span4"><span class="c-red"></span>利率变更周期：</label>
                    						<div class="formControls span6">
                    											<select id="rollFreq" name="rollFreq" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
                    											</select>
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
	<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利率变更日：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="rollDay" id="rollDay" name="rollDay" datatype="n0-2"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>最小利率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="minRate" id="minRate" name="minRate" datatype="n0-21"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>最大利率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxRate" id="maxRate" name="maxRate" datatype="n0-21"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
	<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利率靠档标志：</label>
						<div class="formControls span6">
								<select id="intRateInd" name="intRateInd" class="select2" datatype="*0-20" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="F" >F-靠下档</option>
										<option value="C" >C-靠上档</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>


					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>月基准天数类型：</label>
						<div class="formControls span6">
								<select id="monthBasis" name="monthBasis" class="select2" datatype="*0-21" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="ACT" >ACT-按实际天数</option>
										<option value="30" >30-按整数;</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
		<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>分组规则关系：</label>
						<div class="formControls span6">
								<select id="groupRuleType" name="groupRuleType" class="select2" datatype="*0-21" tipmsg="格式错误!">
									<option value="02" selected="selected">取最大</option>
										<option value="03" >取最小</option>
										<option value="04" >取平均值</option>
										<option value="05" >取叠加</option>
										<option value="06" >取权重</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>分段ID：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="splitId" id="splitId" name="splitId"  datatype="*1-5"  tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red"></span>分段类型：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="splitType" id="splitType" name="splitType" datatype="*0-3" tipmsg="格式错误!">
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>规则ID：</label>
				<div class="formControls span6">
					<input type="text" class="input-text grid" value="" placeholder="ruleid" id="ruleid" name="ruleid" datatype="*1-5"  tipmsg="格式错误!">
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

