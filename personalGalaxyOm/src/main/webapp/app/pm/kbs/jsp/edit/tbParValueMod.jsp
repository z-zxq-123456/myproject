<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_PAR_VALUE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbParValueMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbParValueMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>券别代号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="parValueId" name="parValueId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
                    <div class="formControls span6">
                                <select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否残损币：</label>
                    <div class="formControls span6">
                            <select id="isSpall" name="isSpall" data-placeholder="是否残损币" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>券别描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="parDesc" datatype="*0-60" id="parDesc" name="parDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>券别类型：</label>
                    <div class="formControls span6">
                        <select id="parType" name="parType"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="S" >S-纸币</option>
                                <option value="C" >C-硬币</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>券别：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="parValue" id="parValue" name="parValue" datatype="n0-17"  tipmsg="格式错误!">
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
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
