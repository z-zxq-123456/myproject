<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_STRUCTURE_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmStructureTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmStructureTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>结构类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="structureType" name="structureType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结构长度：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="structureLength" id="structureLength" name="structureLength" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结构描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="structureDesc" id="structureDesc" name="structureDesc" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否设置完成：</label>
                    <div class="formControls span6">
                            <select id="completeInd" name="completeInd" data-placeholder="是否设置完成" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否设置分隔符：</label>
                    <div class="formControls span6">
                            <select id="delimiterInd" name="delimiterInd" data-placeholder="是否设置分隔符" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结构属性：</label>
                    <div class="formControls span6">
                        <select id="structureAttr" name="structureAttr" data-placeholder="结构属性" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A- </option>
                                <option value="N" >N- </option>
                                <option value="AN" >AN- </option>
                                <option value="ANS" >ANS- </option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结构分类：</label>
                    <div class="formControls span6">
                        <select id="structureClass" name="structureClass" data-placeholder="结构分类" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="CN" >CN-Client Number 客户号</option>
                                <option value="AN" >AN-Account Number 账号</option>
                                <option value="AL" >AL-Aall Objects所有对象都可以使用</option>
                                <option value="RF" >RF-Report Filename报表文件名称</option>
                                <option value="RS" >RS-Report Spool Path报表缓冲池路径</option>
                        </select>
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
                    <label class="form-label span4"><span class="c-red"></span>检查规则：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="checkDigitFormula" datatype="*0-3" id="checkDigitFormula" name="checkDigitFormula" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>不容许的分隔符：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="restrictedDelimiter" datatype="*0-2" id="restrictedDelimiter" name="restrictedDelimiter" tipmsg="格式错误!" >
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
