<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易FM_PASSWORD_FAILURE_TIMES添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/fmPasswordFailureTimesAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="fmPasswordFailureTimesAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>渠道集合
多个渠道之间用,分隔，如果是所有渠道，则用"ALL"表示：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="channel" id="channel" name="FM_PASSWORD_FAILURE_TIMES" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>密码类型编号：：</label>
						<div class="formControls span6">
								<select id="passwordType" name="passwordType" data-placeholder="密码类型编号：" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="WD" >WD-支取密码</option>
										<option value="QY" >QY-查询密码</option>
										<option value="MA" >MA-账户管理密码</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>机构：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" class="select2" tabindex="4" tipmsg="格式错误!">
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
						<label class="form-label span4"><span class="c-red"></span>上次修改日期：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastChangeDate" datatype="*0-8" id="lastChangeDate" name="lastChangeDate" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上次修改柜员：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastChangeOfficer" datatype="*0-30" id="lastChangeOfficer" name="lastChangeOfficer" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上次修改时间：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="lastChangeTime" datatype="*0-17" id="lastChangeTime" name="lastChangeTime" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>密码最大错误次数：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="maxFailuerTimes" datatype="*0-3" id="maxFailuerTimes" name="maxFailuerTimes" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否当日清零方式：：</label>
						<div class="formControls span6">
								<select id="resetInd" name="resetInd" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1-当日清零</option>
										<option value="0" >0-非当日清零</option>
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
