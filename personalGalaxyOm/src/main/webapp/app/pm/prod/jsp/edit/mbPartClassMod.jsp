<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_PART_CLASS修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/mbPartClassMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbPartClassMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>指标分类：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="partClass" name="partClass" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>指标分类描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="partClassDesc" id="partClassDesc" name="partClassDesc" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上级指标分类：</label>
                    <div class="formControls span6">
                       <select id="parentPartClass" name="parentPartClass" data-placeholder="上级指标分类" class="select2" tabindex="4" tipmsg="格式错误!">
                            <option value="" selected="selected">请选择</option>
                          </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>指标分类层级：</label>
                    <div class="formControls span6">
                        <select id="partClassLevel" name="partClassLevel"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="1" >1</option>
                                <option value="2" >2</option>
                                <option value="3" >3</option>
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
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>