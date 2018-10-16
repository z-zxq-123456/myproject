<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_BRANCH添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmBranchAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmBranchAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="branch" id="branch" name="FM_BRANCH"  datatype="*1-20" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>国家：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="country" id="country" name="FM_BRANCH" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>省市：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="state" id="state" name="FM_BRANCH" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构简称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="branchShort" datatype="*" id="branchShort" name="branchShort" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>结售汇平盘机构：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ccyCtrlBranch" datatype="*0-20" id="ccyCtrlBranch" name="ccyCtrlBranch" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否签发支票：</label>
						<div class="formControls span6">
									<select id="chequeIssuingBranch" name="chequeIssuingBranch"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>城市代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="city" datatype="*0-8" id="city" name="city" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>业务单元(人民币)：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cnyBusinessUnit" datatype="*0-5" id="cnyBusinessUnit" name="cnyBusinessUnit" tipmsg="格式错误!" >
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
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>区号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="district" datatype="*0-10" id="district" name="district" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>日终标识：</label>
						<div class="formControls span6">
									<select id="eodInd" name="eodInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>外汇金融机构代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="fxOrganCode" datatype="*0-12" id="fxOrganCode" name="fxOrganCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>层级代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="hierarchyCode" datatype="*0-5" id="hierarchyCode" name="hierarchyCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>业务单元(港币)：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="hkdBusinessUnit" datatype="*0-5" id="hkdBusinessUnit" name="hkdBusinessUnit" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>内部客户号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="internalClient" datatype="*0-12" id="internalClient" name="internalClient" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>机构IP地址：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ipAddr" datatype="*0-100" id="ipAddr" name="ipAddr" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>当地币种：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="localCcy" datatype="*0-3" id="localCcy" name="localCcy" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>人行备付金检查标志：</label>
						<div class="formControls span6">
									<select id="pbocFundCheckFalg" name="pbocFundCheckFalg"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>邮编：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="postalCode" datatype="*0-10" id="postalCode" name="postalCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利润中心：</label>
						<div class="formControls span6">
								<select id="profitCentre" name="profitCentre" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="01" >01-会计结算部</option>
										<option value="02" >02-计划财务部</option>
										<option value="03" >03-个人业务部</option>
										<option value="04" >04-电子银行部</option>
										<option value="05" >05-国际业务部</option>
										<option value="06" >06-资金运营部</option>
										<option value="07" >07-公司业务部</option>
										<option value="08" >08-审计部</option>
										<option value="09" >09-授信部</option>
										<option value="10" >10-合规风险部</option>
										<option value="99" >99-缺省</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="A" >A-有效</option>
										<option value="C" >C-关闭</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>分行代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="subBranchCode" datatype="*0-10" id="subBranchCode" name="subBranchCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="branchName" datatype="*" id="branchName" name="branchName" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>自贸区代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaCode" datatype="*0-10" id="ftaCode" name="ftaCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否自贸区机构：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="ftaFlag" datatype="*0-1" id="ftaFlag" name="ftaFlag" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否营业机构：</label>
						<div class="formControls span6">
									<select id="tranBrInd" name="tranBrInd"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>所属机构：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attachedTo" datatype="*0-20" id="attachedTo" name="attachedTo" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
							<label class="form-label span4"><span class="c-red"></span>是否限制凭证入库柜员：</label>
							<div class="formControls span6">
										<select id="voucherUserCoutral" name="voucherUserCoutral"  class="select2"  tipmsg="格式错误!">
											<option value="Y" >Y-限制</option>
											<option value="N" >N-不限制</option>
										</select>
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
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
