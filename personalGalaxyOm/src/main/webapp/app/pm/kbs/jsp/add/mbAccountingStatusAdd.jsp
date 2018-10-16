<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ACCOUNTING_STATUS添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbAccountingStatusAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAccountingStatusAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>核算状态：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="accountingStatus" id="accountingStatus" name="MB_ACCOUNTING_STATUS" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否考虑宽限期：</label>
						<div class="formControls span6">
									<select id="graceDay" name="graceDay"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>持续扣款标志：</label>
						<div class="formControls span6">
									<select id="huntingStatus" name="huntingStatus"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否涉及利息：</label>
						<div class="formControls span6">
									<select id="nonPerforming" name="nonPerforming"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否涉及本金：</label>
						<div class="formControls span6">
									<select id="nonPerformingPri" name="nonPerformingPri"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期间：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="period" datatype="*0-5" id="period" name="period" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>期间类型：</label>
						<div class="formControls span6">
								<select id="periodType" name="periodType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="D" >D-天</option>
										<option value="M" >M-月</option>
										<option value="Y" >Y-年</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否久悬：</label>
						<div class="formControls span6">
									<select id="suspendInd" name="suspendInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否终止：</label>
						<div class="formControls span6">
									<select id="terminateInd" name="terminateInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
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
						<label class="form-label span4"><span class="c-red"></span>变化类型：</label>
						<div class="formControls span6">
								<select id="changeType" name="changeType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="PPD" >PPD-本金逾期</option>
										<option value="IPD" >IPD-利息逾期</option>
										<option value="APD" >APD-本息逾期</option>
										<option value="INA" >INA-不活动</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否可交易标识：</label>
						<div class="formControls span6">
									<select id="available" name="available"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>核算状态描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="accountingStatusDesc" datatype="*0-50" id="accountingStatusDesc" name="accountingStatusDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>费用顺序：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allocSeqFee" datatype="*0-1" id="allocSeqFee" name="allocSeqFee" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利息顺序：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allocSeqInt" datatype="*0-1" id="allocSeqInt" name="allocSeqInt" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>复利顺序：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allocSeqOdi" datatype="*0-1" id="allocSeqOdi" name="allocSeqOdi" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>罚息顺序：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allocSeqOdp" datatype="*0-1" id="allocSeqOdp" name="allocSeqOdp" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>本金顺序：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="allocSeqPri" datatype="*0-1" id="allocSeqPri" name="allocSeqPri" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>自动还款类型：</label>
						<div class="formControls span6">
								<select id="allocSeqType" name="allocSeqType" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="B" >B-大本大息</option>
										<option value="S" >S-小本小息</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>自动变化标志：</label>
						<div class="formControls span6">
									<select id="autoChange" name="autoChange"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否核销：</label>
						<div class="formControls span6">
									<select id="writeOff" name="writeOff"  class="select2"  tipmsg="格式错误!">
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
