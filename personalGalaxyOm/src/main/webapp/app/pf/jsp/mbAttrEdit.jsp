<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>属性修改</title>
    <script type="text/javascript" src="${ContextPath}/app/pf/js/mbAttrEdit.js"></script>
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="form-attr-edit">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>参数分类代码：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="attrClass" id="attrClass" name="attrClass" datatype="*1-20" nullmsg="属性分类不能为空" disabled="true">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>参数分类描述：</label>
			<div class="formControls span6">
				<input type="text" placeholder="attrClassDesc"  value="" id="attrClassDesc" name="attrClassDesc" class="input-text grid" datatype="*1-40" nullmsg="属性描述不能为空">
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
			<label class="form-label span4"><span class="c-red">*</span>参数分类层级：</label>
			<div class="formControls span6">
			    <select id="attrClassLevel" name="attrClassLevel" data-placeholder="参数分类层级" class="select2 " datatype="*1-40" nullmsg="参数分类层级不能为空">
                			          <option value="" selected="selected">请选择</option>
                			          <option value="1" > 1 </option>
                                      <option value="2" > 2 </option>
                                      <option value="3" > 3 </option>
                </select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
        			<label class="form-label span4">上级参数分类：</label>
        			<div class="formControls span6">
                       <select id="parentAttrClass" name="parentAttrClass" data-placeholder="上级参数分类" class="select2" tabindex="4" tipmsg="格式错误!">
                             <option value="" selected="selected">请选择</option>
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
