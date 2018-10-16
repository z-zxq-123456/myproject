<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/mbPartClassAdd.js"></script>
<title>部件添加</title>
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="form-part-add">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>指标分类代码：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="partClass" id="partClass" name="partClass" datatype="/^[A-Z\(_)]+$/" nullmsg="部件分类不能为空">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>指标分类描述：</label>
			<div class="formControls span6">
				<input type="text" placeholder="partClassDesc"  value="" id="partClassDesc" name="partClassDesc" class="input-text grid" datatype="*1-40" nullmsg="部件分类描述不能为空">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
        	<label class="form-label span4"><span class="c-red"></span>业务分类：</label>
        	<div class="formControls span6">
        			<select id="Bmodule" name="Bmodule" data-placeholder="业务分类" class="select2"  nullmsg="业务分类不能为空">
                       <option value="" selected="selected">请选择</option>
                    </select>
        </div>
        	<div class="span2"> </div>
        </div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>指标分类层级：</label>
			<div class="formControls span6">
			    <select id="partClassLevel" name="partClassLevel" data-placeholder="指标分类层级" class="select2 " datatype="*1-40" nullmsg="指标分类层级不能为空">
			          <option value="" selected="selected">请选择</option>
                      <option value="1" > 1 </option>
                      <option value="2" > 2 </option>
                 </select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4">上级指标分类：</label>
			<div class="formControls span6">
        		<select id="parentPartClass" name="parentPartClass" data-placeholder="上级指标分类" class="select2" tabindex="4" tipmsg="格式错误!">
                    <option value="" selected="selected">请选择</option>
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