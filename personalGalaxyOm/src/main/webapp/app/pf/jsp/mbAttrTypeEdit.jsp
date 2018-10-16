<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数类型修改</title>
<script type="text/javascript" src="${ContextPath}/app/pf/js/mbAttrTypeEdit.js"></script>
<link href="${ContextPath}/lib/select2-3.4.2/select2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="form-attr-edit">
			<div class="row cl">
        			<label class="form-label span4"><span class="c-red">*</span>参数代码：</label>
        			<div class="formControls span6">
        				<input type="text" class="input-text grid" value="" placeholder="attrKey" id="attrKey" name="attrKey" datatype="*1-40" nullmsg="属性定义不能为空" disabled="true">
        			</div>
        			<div class="span2"> </div>
        		</div>
        		<div class="row cl">
        			<label class="form-label span4"><span class="c-red">*</span>参数描述：</label>
        			<div class="formControls span6">
        				<input type="text" placeholder="attrDesc"  value="" id="attrDesc" name="attrDesc" class="input-text grid" datatype="*1-40" nullmsg="属性描述不能为空">
        			</div>
        			<div class="span2"> </div>
        		</div>
		    <div class="row cl">
        	    <label class="form-label span4"><span class="c-red">*</span>业务分类：</label>
        	   <div class="formControls span6">
        			<select id="busiCategory" name="busiCategory" data-placeholder="业务分类" class="select2" datatype="*" multiple="multiple" nullmsg="业务分类不能为空" >
                    </select>
                </div>
        	<div class="span2"> </div>
            </div>
        		<div class="row cl">
        			<label class="form-label span4"><span class="c-red">*</span>参数分类：</label>
        			<div class="formControls span6">
							<select class="select2" id="attrClass" name="attrClass" datatype="*1-40" nullmsg="参数分类不能为空"></select>
					</div>
        			<div class="span2"> </div>
        		</div>
		<div class="row cl">
			<label class="form-label span4">参数类型：</label>
			<div class="formControls span6">
			    <select id="attrType" name="attrType" data-placeholder="参数类型" class="select2 " tabindex="4" tipmsg="格式错误!" disabled="true">
			          <option value="" selected="selected">请选择</option>
                      <option value="STRING" > STRING-字符型 </option>
                      <option value="DOUBLE" > DOUBLE-数值型 </option>
                 </select>
			</div>
			<div class="span2"> </div>
		</div>
        		<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>使用方式：</label>
						<div class="formControls span6">
								<select class="select2"   id="useMethod" name="useMethod" datatype="*1-40" nullmsg="使用方式不能为空">
								    <option value="" selected="selected">请选择</option>
									<option value="EVAL">EVAL-赋值类</option>
									<option value="CTR">CTR-控制类</option>
									<option value="IND">IND-独立逻辑</option>
									<option value="DESC">DESC-描述类</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>取值方式：</label>
					<div class="formControls span6">
							<select class="select2" id="valueMethod" name="valueMethod" datatype="*1-40" nullmsg="取值方式不能为空">
							<option value="" selected="selected">请选择</option>
							<option value="FD">FD-取自固定值</option>
							<option value="YN">YN-取值Y或N</option>
							<option value="VL">VL-取自列表值</option>
							<option value="RF">RF-取自数据表</option>
							</select>
					</div>
					<div class="span2"> </div>
			</div>
                       <div class="row cl">
                         			<label class="form-label span4"><span class="c-red"></span>状态：</label>
                         			<div class="formControls span6">
											<select class="select2" id="status" name="status">
											    <option value="" selected="selected">请选择</option>
												<option value="A">有效</option>
												<option value="F">无效</option>
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
