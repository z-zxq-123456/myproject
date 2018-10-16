<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_COUNTRY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmCountryAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmCountryAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>国家：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="country" id="country" name="FM_COUNTRY" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="countryDesc" id="countryDesc" name="FM_COUNTRY" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>SAFE编码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="safeCode" datatype="*0-3" id="safeCode" name="safeCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>ISO编码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="isoCode" datatype="*0-3" id="isoCode" name="isoCode" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>非合作国家：</label>
						<div class="formControls span6">
									<select id="ncct" name="ncct"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>政治敏感国家：</label>
						<div class="formControls span6">
									<select id="psc" name="psc"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>地区：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="region" datatype="*0-2" id="region" name="region" tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red"></span>币种：</label>
						<div class="formControls span6">
											<select id="ccy" name="ccy" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>国家电话区号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="countryTel" datatype="*0-4" id="countryTel" name="countryTel" tipmsg="格式错误!" >
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
