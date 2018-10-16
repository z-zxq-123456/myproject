<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_PROD_ACCOUNTING添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glProdAccountingAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glProdAccountingAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>核算状态：</label>
						<div class="formControls span6">
								<select id="accountingStatus" name="accountingStatus" data-placeholder="核算状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="ZHC" >ZHC-正常</option>
										<option value="SUS" >SUS-久悬</option>
										<option value="WRN" >WRN-核销</option>
										<option value="FYJ" >FYJ-非应计</option>
										<option value="FY" >FY-非应计(手工)</option>
                                        <option value="YUQ" >YUQ-逾期</option>
                                        <option value="TER" >TER-终止</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
						                    <select id="prodType" name="prodType" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
                                            											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应收罚息科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdpRec" name="glCodeOdpRec" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>罚息收入科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdpI" name="glCodeOdpI" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应计罚息科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdpAcr" name="glCodeOdpAcr" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应收复利科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdiRec" name="glCodeOdiRec" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>复利收入科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdiI" name="glCodeOdiI" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应计复利科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeOdiAcr" name="glCodeOdiAcr" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>负债科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeL" name="glCodeL" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应收利息科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeIntRec" name="glCodeIntRec" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应付利息科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeIntPay" name="glCodeIntPay" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利息收入科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeIntI" name="glCodeIntI" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利息支出科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeIntE" name="glCodeIntE" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>应计利息科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeIntAcr" name="glCodeIntAcr" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>资产损失科目：</label>
						<div class="formControls span6">
											<select id="glCodeALoss" name="glCodeALoss" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>科目调整：</label>
						<div class="formControls span6">
											<select id="glCodeAdjust" name="glCodeAdjust" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>资产科目代码：</label>
						<div class="formControls span6">
											<select id="glCodeA" name="glCodeA" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>账套：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="businessUnit" datatype="*0-50" id="businessUnit" name="businessUnit" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利润中心：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="profitCentre" datatype="*0-12" id="profitCentre" name="profitCentre" tipmsg="格式错误!" >
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
