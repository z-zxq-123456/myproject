<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_USER添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmUserAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmUserAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>柜员ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="userId" id="userId" name="FM_USER" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>是否批处理用户：</label>
						<div class="formControls span6">
									<select id="eodSodEnabled" name="eodSodEnabled" data-placeholder="是否批处理用户" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>柜员状态：</label>
						<div class="formControls span6">
								<select id="accountStatus" name="accountStatus" data-placeholder="柜员状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-有效</option>
										<option value="D" >D-删除</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>柜员类别：</label>
						<div class="formControls span6">
								<select id="userType" name="userType" data-placeholder="柜员类别" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="DUMMY_TELLER" >DUMMY_TELLER-虚拟柜员</option>
										<option value="TELLER_USER" >TELLER_USER-普通柜员</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>复核日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="checkDate" datatype="*0-8" id="checkDate" name="checkDate" tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>部门代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="department" datatype="*0-3" id="department" name="department" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>证件号码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="documentId" datatype="*0-25" id="documentId" name="documentId" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>证件类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="documentType" datatype="*0-3" id="documentType" name="documentType" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>所属机构：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="interBranchInd" datatype="*0-1" id="interBranchInd" name="interBranchInd" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>创建柜员：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maker" datatype="*0-30" id="maker" name="maker" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>创建日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="makeDate" datatype="*0-8" id="makeDate" name="makeDate" tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>账薄：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tbook" datatype="*0-2" id="tbook" name="tbook" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>柜员描述信息：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="userDesc" datatype="*0-300" id="userDesc" name="userDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>柜员语言：</label>
						<div class="formControls span6">
								<select id="userLang" name="userLang" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="E" >E-英文</option>
										<option value="C" >C-中文</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>柜员级别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="userLevel" datatype="*0-1" id="userLevel" name="userLevel" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>柜员名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="userName" datatype="*0-50" id="userName" name="userName" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>复核柜员：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="checker" datatype="*0-30" id="checker" name="checker" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>授权级别：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="authLevel" datatype="*0-1" id="authLevel" name="authLevel" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否应用柜员：</label>
						<div class="formControls span6">
									<select id="applicationUser" name="applicationUser"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>客户经理：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="acctExec" datatype="*0-30" id="acctExec" name="acctExec" tipmsg="格式错误!" >
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
