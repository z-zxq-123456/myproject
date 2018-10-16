<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_STAGE_DEFINE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbStageDefineAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbStageDefineAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期次代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stageCode" id="stageCode" name="MB_STAGE_DEFINE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行年度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueYear" id="issueYear" name="MB_STAGE_DEFINE" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行起始日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueStartDate" id="issueStartDate" name="MB_STAGE_DEFINE" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_STAGE_DEFINE" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行终止日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueEndDate" id="issueEndDate" name="MB_STAGE_DEFINE" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行额度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueAmt" id="issueAmt" name="MB_STAGE_DEFINE" datatype="*1-19" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期次描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stageCodeDesc" datatype="*0-100" id="stageCodeDesc" name="stageCodeDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期限：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="term" datatype="*0-2" id="term" name="term" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期限类型：</label>
						<div class="formControls span6">
								<select id="termType" name="termType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="Y" >Y-年</option>
										<option value="M" >M-月</option>
										<option value="D" >D-日</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>转让标识：</label>
						<div class="formControls span6">
									<select id="transferFlag" name="transferFlag"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易支行：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranBranch" datatype="*0-20" id="tranBranch" name="tranBranch" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>交易日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tranDate" datatype="*0-8" id="tranDate" name="tranDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>销售方式：</label>
						<div class="formControls span6">
								<select id="saleType" name="saleType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="C" >C-竞售</option>
										<option value="P" >P-配额</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利率重置频率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="resetIntFreq" datatype="*0-2" id="resetIntFreq" name="resetIntFreq" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否允许提前支取：</label>
						<div class="formControls span6">
									<select id="preWithdrawFlag" name="preWithdrawFlag"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>付息方式：</label>
						<div class="formControls span6">
								<select id="payIntType" name="payIntType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1-一次还本付息和定期付息</option>
										<option value="2" >2- 到期还本</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>部提次数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="partWithdrawNum" datatype="*0-3" id="partWithdrawNum" name="partWithdrawNum" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>计息方式：</label>
						<div class="formControls span6">
								<select id="intCalcType" name="intCalcType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="0" >0-零息</option>
										<option value="1" >1-浮动计息</option>
										<option value="2" >2-固定利率</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>柜员ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="userId" datatype="*0-30" id="userId" name="userId" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>取息频率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="getIntFreq" datatype="*0-2" id="getIntFreq" name="getIntFreq" tipmsg="格式错误!" >
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
