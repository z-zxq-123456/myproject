<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareInfoEdit.js"></script>
</head>
<body>
<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-midwareInfo-edit">
        	    <div class="row">
                    <label class="form-label span4"><span class="c-red">*</span>中间件名称：</label>
                    <div class="formControls span6">
                        <input type="text" class="input-text grid" id="midwareName" placeholder="请输入1-20位字符"  name="midwareName" datatype="s1-20" nullmsg="请输入！" tipmsg="格式错误!">
                    </div>
                    <div class="span2"></div>
                </div>
        		<div class="row">
        			 <label class="form-label span4"><span class="c-red">*</span>中间件类型：</label>
        			 <div class="formControls span6">
        				  <select name="queryMidwareType" id="queryMidwareType" size="1" class="select2" style="margin-top:5px">
        				  </select>
        			 </div>
        			 <div class="span2"></div>
        		</div>
        		<div class="row"  >
					<label class="form-label span4"><span class="c-red">*</span>ZK集群：</label>
					<div class="formControls span6">
						<select type="text" class="select2" placeholder="ZK集群" datatype="*1-50" name="kfkZksId" id="kfkZksId"
						nullmsg="请选择ZK集群！"
						></select>
					</div>
					<div class="span2"></div>
				</div>
        		<div class="row">
        			<label class="form-label span4"><span class="c-red">*</span>版本号：</label>
        			<div class="formControls span6">
        				<select name="midwareVerNo" id="midwareVerNo" size="1" class="select2" style="margin-top:5px">
                        </select>
        			</div>
        			<div class="span2"></div>
        		</div>
        		<div class="row">
        			<label class="form-label span4"><span class="c-red">*</span>中间件安装路径：</label>
        			<div class="formControls span6">
        				<input type="text" class="input-text grid" id="midwarePath" placeholder="请输入3-200位字符"  name="midwarePath" datatype="*3-200" nullmsg="请输入！" tipmsg="格式错误!">
        			</div>
        			<div class="span2"></div>
        		</div>
        	    <div class="row">
                     <label class="form-label span4"><span class="c-red">*</span>是否设为默认：</label>
                     <div class="formControls span6">
                          <select name="isDefaultParaName" id="isDefaultParaName" size="1" class="select2" style="margin-top:5px">
                          </select>
                     </div>
                     <div class="span2"></div>
                </div>
        		<div class="row formBtnArea">
        			<div class="span-offset-1 span10 span-offset-1 mt-10">
        				<input type="submit" class="button-select L smartButton" title="修改"  value="修改">
        			</div>
        		</div>
        </form>
</div>
</body>
</html>
