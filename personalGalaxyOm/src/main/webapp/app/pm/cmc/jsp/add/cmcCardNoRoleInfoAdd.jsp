<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CMC_CARD_NO_ROLE_INFO添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/add/cmcCardNoRoleInfoAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cmcCardNoRoleInfoAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>规则序号：</label>
						<div class="formControls span6">
											<%-- <input type="text" class="input-text grid" value="" placeholder="cardNoRoleCode" id="cardNoRoleCode" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat"> --%>
											<select id="cardNoRoleCode" name="CMC_CARD_NO_ROLE_INFO" data-placeholder="cardNoRoleCode" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
					</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>卡号长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="lenOfCardNo" id="lenOfCardNo" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>校验位位置：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="cardGenId" id="cardGenId" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>顺序号长度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqNoLen" id="seqNoLen" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>顺序号前域的个数：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldnum1" id="fldnum1" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域1来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="cmc_card_no_role_ex" placeholder="fldsrctbl1" datatype="*0-30" id="fldsrctbl1" name="fldsrctbl1" tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域1来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="PARAM_VALUE" placeholder="fldsrcfld1" datatype="*0-30" id="fldsrcfld1" name="fldsrcfld1" tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域1长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="2" placeholder="fldlen1" datatype="*0-10" id="fldlen1" name="fldlen1" tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域1条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="PARAM_NAME" placeholder="fldwhr1" datatype="*0-128" id="fldwhr1" name="fldwhr1" tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
				    <div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域1方式：</label>
						<div class="formControls span6">
							<select id="fldsign1" name="fldsign1" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<!--
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域2来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl2" datatype="*0-30" id="fldsrctbl2" name="fldsrctbl2" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域2来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld2" datatype="*0-30" id="fldsrcfld2" name="fldsrcfld2" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域2长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen2" datatype="*0-10" id="fldlen2" name="fldlen2" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域2条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr2" datatype="*0-128" id="fldwhr2" name="fldwhr2" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域2方式：</label>
						<div class="formControls span6">
							<select id="fldsign2" name="fldsign2" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域3来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl3" datatype="*0-30" id="fldsrctbl3" name="fldsrctbl3" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域3来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld3" datatype="*0-30" id="fldsrcfld3" name="fldsrcfld3" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域3长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen3" datatype="*0-10" id="fldlen3" name="fldlen3" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域3条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr3" datatype="*0-128" id="fldwhr3" name="fldwhr3" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域3方式：</label>
						<div class="formControls span6">
							<select id="fldsign3" name="fldsign3" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域4来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl4" datatype="*0-30" id="fldsrctbl4" name="fldsrctbl4" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域4来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld4" datatype="*0-30" id="fldsrcfld4" name="fldsrcfld4" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域4长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen4" datatype="*0-10" id="fldlen4" name="fldlen4" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域4条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr4" datatype="*0-128" id="fldwhr4" name="fldwhr4" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域4方式：</label>
						<div class="formControls span6">
							<select id="fldsign4" name="fldsign4" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域5来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl5" datatype="*0-30" id="fldsrctbl5" name="fldsrctbl5" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域5来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld5" datatype="*0-30" id="fldsrcfld5" name="fldsrcfld5" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域5长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen5" datatype="*0-10" id="fldlen5" name="fldlen5" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域5条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr5" datatype="*0-128" id="fldwhr5" name="fldwhr5" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域5方式：</label>
						<div class="formControls span6">
							<select id="fldsign5" name="fldsign5" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div> -->
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>顺序号后域的个数：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="0" placeholder="fldnum2" datatype="*1-10" id="fldnum2" name="CMC_CARD_NO_ROLE_INFO"  nullmsg=" 请输入！" tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
					<!-- <div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域6来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl6" datatype="*0-30" id="fldsrctbl6" name="fldsrctbl6" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域6来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld6" datatype="*0-30" id="fldsrcfld6" name="fldsrcfld6" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域6长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen6" datatype="*0-10" id="fldlen6" name="fldlen6" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域6条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr6" datatype="*0-128" id="fldwhr6" name="fldwhr6" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div> -->
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域6方式：</label>
						<div class="formControls span6">
							<select id="fldsign6" name="fldsign6" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<!-- <div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域7来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl7" datatype="*0-30" id="fldsrctbl7" name="fldsrctbl7" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域7来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld7" datatype="*0-30" id="fldsrcfld7" name="fldsrcfld7" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域7长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen7" datatype="*0-10" id="fldlen7" name="fldlen7" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域7条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr7" datatype="*0-128" id="fldwhr7" name="fldwhr7" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域7方式：</label>
						<div class="formControls span6">
							<select id="fldsign7" name="fldsign7" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域8来源表：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrctbl8" datatype="*0-30" id="fldsrctbl8" name="fldsrctbl8" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域8来源域：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldsrcfld8" datatype="*0-30" id="fldsrcfld8" name="fldsrcfld8" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域8长度：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldlen8" datatype="*0-10" id="fldlen8" name="fldlen8" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域8条件：</label>
						<div class="formControls span6">
							<input type="text" class="input-text grid" value="" placeholder="fldwhr8" datatype="*0-128" id="fldwhr8" name="fldwhr8" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>域8方式：</label>
						<div class="formControls span6">
							<select id="fldsign8" name="fldsign8" class="select2" tipmsg="格式错误!">
								<option value="" selected="selected">空</option>
								<option value="1" >1-数据库配置</option>
								<option value="2" >2-常量</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div> -->
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>校验位算法：</label>
						<div class="formControls span6">
							<select id="vryBitMeth" name="vryBitMeth" data-placeholder="校验位算法" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
								<option value="0" >00-luhn算法</option>
								<option value="1" >01-verhoeff算法</option>
							</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>校验位长度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="1" placeholder="vryBitLen" id="vryBitLen" name="CMC_CARD_NO_ROLE_INFO" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" disabled="disabled">
						</div>
						<div class="span2"> </div>
					</div>
					<!-- <div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>校验位生成函数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="vryGenFunc" datatype="*0-40" id="vryGenFunc" name="vryGenFunc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div> -->

			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
