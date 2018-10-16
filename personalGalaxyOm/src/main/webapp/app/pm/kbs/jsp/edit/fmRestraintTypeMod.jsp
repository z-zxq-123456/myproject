<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_RESTRAINT_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmRestraintTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmRestraintTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>限制类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="restraintType" name="restraintType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>EOD扣款标志：</label>
                    <div class="formControls span6">
                            <select id="eodImpoundFalg" name="eodImpoundFalg"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>手工冻结标志：</label>
                    <div class="formControls span6">
                            <select id="manualResFlag" name="manualResFlag"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>全额止付标志：</label>
                    <div class="formControls span6">
                            <select id="stopwtdfalg" name="stopwtdfalg"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>限制类型类别：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="restraintClass" datatype="*0-2" id="restraintClass" name="restraintClass" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>限制类型描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="restraintDesc" datatype="*0-30" id="restraintDesc" name="restraintDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否有权机关冻结：</label>
                    <div class="formControls span6">
                            <select id="ahBu" name="ahBu"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否系统专用标志：</label>
                    <div class="formControls span6">
                                <select id="systemUserFlag" name="systemUserFlag"  class="select2"  tipmsg="格式错误!">
                                    <option value="" selected="selected">空</option>
                                    <option value="Y" >是</option>
                                    <option value="N" >否</option>
                                </select>
                    </div>
                    <div class="span2"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>手工解冻标志：</label>
                    <div class="formControls span6">
                            <select id="manualUnresFlag" name="manualUnresFlag"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
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
