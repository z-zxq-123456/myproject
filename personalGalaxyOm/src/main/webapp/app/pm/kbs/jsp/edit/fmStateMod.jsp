<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_STATE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmStateMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmStateMod">

			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>省市：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="state" name="state" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>国家：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="country" name="country" >
			    </div>
			    <div class="span2"> </div>
			</div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>省名称：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="stateDesc" id="stateDesc" name="stateDesc" datatype="*1-90" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>周末2：</label>
                    <div class="formControls span6">
                        <select id="weekend2" name="weekend2"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="FRI" >FRI-周五</option>
                                <option value="MON" >MON-周一.SAT-周六</option>
                                <option value="SUN" >SUN-周日</option>
                                <option value="THU" >THU-周四</option>
                                <option value="TUE" >TUE-周二</option>
                                <option value="WED" >WED-周三</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>周末1：</label>
                    <div class="formControls span6">
                        <select id="weekend1" name="weekend1"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="FRI" >FRI-周五</option>
                                <option value="MON" >MON-周一.SAT-周六</option>
                                <option value="SUN" >SUN-周日</option>
                                <option value="THU" >THU-周四</option>
                                <option value="TUE" >TUE-周二</option>
                                <option value="WED" >WED-周三</option>
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
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
