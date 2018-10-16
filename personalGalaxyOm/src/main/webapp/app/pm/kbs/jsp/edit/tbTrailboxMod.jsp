<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_TRAILBOX修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/TbTrailboxMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbTrailboxMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>尾箱代号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="trailboxId" name="trailboxId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
                    <div class="formControls span6">
                                <select id="branch" name="branch" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>尾箱类型：</label>
                    <div class="formControls span6">
                        <select id="trailboxType" name="trailboxType" data-placeholder="尾箱类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="B" >B-机构尾箱</option>
                                <option value="T" >T-柜员尾箱</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>尾箱状态：</label>
                    <div class="formControls span6">
                        <select id="trailboxStatus" name="trailboxStatus" data-placeholder="尾箱状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="N" >N-未使用</option>
                                <option value="Y" >Y-已使用</option>
                                <option value="D" >D-已封存</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>凭证碰库时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="voucherEqualDate" datatype="*0-8" id="voucherEqualDate" name="voucherEqualDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>柜员ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="userId" datatype="*0-30" id="userId" name="userId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>更新日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="updateDate" datatype="*0-8" id="updateDate" name="updateDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>尾箱属性：</label>
                    <div class="formControls span6">
                        <select id="trailboxProperty" name="trailboxProperty"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="C" >C-现金尾箱</option>
                                <option value="V" >V-凭证尾箱</option>
                                <option value="B" >B-组合尾箱</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>分配柜员：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="assignUserId" datatype="*0-30" id="assignUserId" name="assignUserId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上一使用柜员：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="lastUserId" datatype="*0-30" id="lastUserId" name="lastUserId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>创建日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="createDate" datatype="*0-8" id="createDate" name="createDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"   class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>现金碰库会计日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cashRunDate" datatype="*0-8" id="cashRunDate" name="cashRunDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>现金碰库时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cashEqualDate" datatype="*0-8" id="cashEqualDate" name="cashEqualDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>凭证碰库会计日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="voucherRunDate" datatype="*0-8" id="voucherRunDate" name="voucherRunDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
