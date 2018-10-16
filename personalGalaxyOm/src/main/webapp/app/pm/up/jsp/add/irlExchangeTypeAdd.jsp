<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_EXCHANGE_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlExchangeTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlExchangeTypeAdd">
					<div class="pd-20">
                    		<form action="" method="post" class="form form-horizontal" id="irlExchangeTypeAdd">
                    					<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>汇率类型：</label>
                    						<div class="formControls span6">
                    											<input type="text" class="input-text grid" value="" placeholder="rateType" id="rateType" name="IRL_EXCHANGE_TYPE" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
									<div class="row cl">
										<label class="form-label span4"><span class="c-red">*</span>汇率类型描述：</label>
										<div class="formControls span6">
															<input type="text" class="input-text grid" value="" placeholder="rateTypeDesc" id="rateTypeDesc" name="IRL_EXCHANGE_TYPE" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
										</div>
										<div class="span2"> </div>
									</div>
                    					<div class="row cl" id="CCY" >
                    						<label class="form-label span4"><span class="c-red">*</span>报价币种：</label>
                    						<div class="formControls span6">
                    											<select id="quoteCcy" name="quoteCcy" datatype="*0-20" class="select2" tabindex="4"  datatype="*1-3" nullmsg=" 请输入！" tipmsg="格式错误!">
                    											</select>
                    							</div>
                    						<div class="span2"> </div>
                    					</div>

                    					<div class="row cl">
                    						<label class="form-label span4"><span class="c-red"></span>浮动规则：</label>
                    						<div class="formControls span6">
                    								<select id="floatType" name="floatType" class="select2" tipmsg="格式错误!">
                                       			<option value="02" >02-取最大</option>
                                                <option value="03" >03-取最小</option>
                                                <option value="04" >04-取平均值</option>
                                                <option value="05" >05-取叠加</option>
                                                <option value="06" >06-取权重</option>
                    								</select>
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>货币对标志：</label>
						<div class="formControls span6">
								<select id="hbdFlag" name="hbdFlag" class="select2" tipmsg="格式错误!">
										<option value="N" >N-否</option>
										<option value="Y" >Y-是</option>

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

