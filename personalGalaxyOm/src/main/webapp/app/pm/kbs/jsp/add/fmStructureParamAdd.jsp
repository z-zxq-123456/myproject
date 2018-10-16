<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_STRUCTURE_PARAM添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmStructureParamAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmStructureParamAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>参数类型：</label>
						<div class="formControls span6">
								<select id="paramType" name="paramType" data-placeholder="参数类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="AN" >AN-Basic Account Number 基本账号</option>
										<option value="BK" >BK-Bank Code</option>
										<option value="BR" >BR-Branch Code 机构号</option>
										<option value="CC" >CC-Country Code 国家代码</option>
										<option value="CD" >CD-Check Digit 校验数字</option>
										<option value="CL" >CL-Client Number </option>
										<option value="CY" >CY-Currency Code 币种</option>
										<option value="DT" >DT-Date Value 生效日期</option>
										<option value="PC" >PC-Padding Character</option>
										<option value="PF" >PF-product family 产品家族</option>
										<option value="PI" >PI-Program ID 程序编号</option>
										<option value="PT" >PT-Product Type 产品类型 </option>
										<option value="RN" >RN-region 地区号</option>
										<option value="SN" >SN-Sequence Number 序列号</option>
										<option value="SP" >SP-System Phase 系统阶段</option>
										<option value="ST" >ST-String Value 字符串值</option>
										<option value="UI" >UI-User ID 柜员ID</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>起始位置：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="startPos" id="startPos" name="FM_STRUCTURE_PARAM" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>结构类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="structureType" id="structureType" name="FM_STRUCTURE_PARAM" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>长度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="length" id="length" name="FM_STRUCTURE_PARAM" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>结束位置：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="endPos" id="endPos" name="FM_STRUCTURE_PARAM" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>填充字符：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="paddingChar" datatype="*0-1" id="paddingChar" name="paddingChar" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>字符值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stringValue" datatype="*0-99" id="stringValue" name="stringValue" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>序号类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqType" datatype="*0-3" id="seqType" name="seqType" tipmsg="格式错误!" >
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
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
