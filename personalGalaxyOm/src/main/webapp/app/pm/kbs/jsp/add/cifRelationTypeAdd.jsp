<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易CIF_RELATION_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/cifRelationTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="cifRelationTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="relationType" id="relationType" name="CIF_RELATION_TYPE" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>授权方：</label>
						<div class="formControls span6">
									<select id="authorised" name="authorised" data-placeholder="授权方" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联合帐户：</label>
						<div class="formControls span6">
									<select id="jointAcct" name="jointAcct" data-placeholder="联合帐户" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>亲戚：</label>
						<div class="formControls span6">
									<select id="relative" name="relative" data-placeholder="亲戚" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="relationDesc" id="relationDesc" name="CIF_RELATION_TYPE" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>雇佣：</label>
						<div class="formControls span6">
									<select id="employment" name="employment" data-placeholder="雇佣" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>股权：</label>
						<div class="formControls span6">
									<select id="equity" name="equity" data-placeholder="股权" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>风险：</label>
						<div class="formControls span6">
									<select id="exposure" name="exposure" data-placeholder="风险" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>对等：</label>
						<div class="formControls span6">
									<select id="symmentric" name="symmentric" data-placeholder="对等" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联合体：</label>
						<div class="formControls span6">
									<select id="joinCollat" name="joinCollat" data-placeholder="联合体" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>关系类型标识：</label>
						<div class="formControls span6">
								<select id="relationTypeFlag" name="relationTypeFlag" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1-个人_个人</option>
										<option value="2" >2-个人_非个人</option>
										<option value="3" >3-非个人_非个人</option>
										<option value="4" >4-非个人_个人</option>
										<option value="5" >5-个人_非个人 或 非个人_非个人</option>
										<option value="6" >6-非个人_非个人 或 非个人_个人</option>
								</select>
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
						<label class="form-label span4"><span class="c-red"></span>反相关系类型：</label>
						<div class="formControls span6">
											<select id="counterRel" name="counterRel" datatype="*" class="select2" tabindex="4" tipmsg="格式错误!">
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
