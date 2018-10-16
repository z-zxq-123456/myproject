<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_STAGE_INT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbStageIntAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbStageIntAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqNo" id="seqNo" name="MB_STAGE_INT" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>期次代码：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="stageCode" id="stageCode" name="MB_STAGE_INT" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
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
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_STAGE_INT" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>发行年度：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="issueYear" id="issueYear" name="MB_STAGE_INT" datatype="*1-4" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>执行利率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="realRate" id="realRate" name="realRate" datatype="n0-23"  tipmsg="格式错误!">
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
						<label class="form-label span4"><span class="c-red"></span>浮动值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="floatRate" id="floatRate" name="floatRate" datatype="n0-21"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>事件类型：</label>
						<div class="formControls span6">
											<select id="eventType" name="eventType" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>实际利率：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="actualRate" id="actualRate" name="actualRate" datatype="n0-21"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>利率类型代码：</label>
						<div class="formControls span6">
											<select id="intType" name="intType" class="select2" tabindex="4" tipmsg="格式错误!">
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
