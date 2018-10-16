<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_INT_RATE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlIntRateAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlIntRateAdd">
					<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>利率类型：</label>
                    						<div class="formControls span6">
                    								<select id="intType" name="intType" data-placeholder="利率类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                    								</select>
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
                						<label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
                						<div class="formControls span6">
                						<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd'})" id="effectDate" name="effectDate" class="input-text Wdate" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                						</div>
                						<div class="span2"> </div>
                					</div>
                	<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>失效日期：</label>
                    						<div class="formControls span6">
                    						<input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="endDate" name="endDate" class="input-text Wdate" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>年基准：</label>
						<div class="formControls span6">
								<select id="yearBasis" name="yearBasis" data-placeholder="年基准" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
									<option value="360" >360-360天</option>
									<option value="365" >365-365天</option>
									<option value="366" >366-366天</option>
                        		</select>
						</div>
						<div class="span2"> </div>
					</div>
				<div class="row cl">
                						<label class="form-label span4"><span class="c-red"></span>最后修改日期：</label>
                						<div class="formControls span6">
                						<input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="lastChgRunDate" name="lastChgRunDate" class="input-text Wdate" style="width:265px;">
                						</div>
                						<div class="span2"> </div>
                					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
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


