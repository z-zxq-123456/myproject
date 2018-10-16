<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/mbEventClassAdd.js"></script>
<title>属性添加</title>
</head>
<body>
<div class="padding-20">
	<form action="" method="post" class="form form-horizontal" id="form-event-add">
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>事件分类代码：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="eventClass" id="eventClass" name="eventClass" datatype="/^[A-Z]+$/" nullmsg="事件分类不能为空">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>事件分类描述：</label>
			<div class="formControls span6">
				<input type="text" placeholder="eventClassDesc"  value="" id="eventClassDesc" name="eventClassDesc" class="input-text grid" datatype="*1-40" nullmsg="事件分类描述不能为空">
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
			<label class="form-label span4"><span class="c-red">*</span>事件分类层级：</label>
			<div class="formControls span6">
			    <select id="eventClassLevel" name="eventClassLevel" data-placeholder="事件分类层级" class="select2 " datatype="*1-40" nullmsg="事件分类层级不能为空">
			          <option value="" selected="selected">请选择</option>
			          <option value="1" > 1 </option>
                      <option value="2" > 2 </option>
                 </select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
        			<label class="form-label span4">上级事件分类：</label>
        			<div class="formControls span6">
         		        <select id="parentEventClass" name="parentEventClass" data-placeholder="上级事件分类" multiple="multiple"  class="select2" tabindex="4" tipmsg="格式错误!">
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