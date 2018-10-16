<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>用户添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/js/paraSetting/add/paraNamespaceAdd.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-namespace-add">
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>交易名：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" placeholder="请输入1-50位字符！" value="" id="transactionId" name="transactionId" datatype="*1-50" nullmsg="请输入" tipmsg="格式错误!">
                </div>
                <div class="span2"> </div>
            </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>交易名中文描述：</label>
                 <div class="formControls span6">
                    <input type="text" class="input-text grid" placeholder="请输入1-100位字符！" value="" id="transactionDesc" name="transactionDesc" datatype="*1-100" nullmsg"请输入"tipmsg="格式错误！"/>
                 </div>
                <div class="span2"></div>
                </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>目标系统ID：</label>
                <div class="formControls span6">
                    <select class="select2" size="1" id="systemId" name="systemId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!" >
                     </select>
                </div>
                <div class="span2"></div>
            </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>模块代码：</label>
                <div class="formControls span6">
                    <select class="select2" size="1" id="moduleId" name="moduleId" datatype="*" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
                     </select>
                </div>
                <div class="span2"></div>
            </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>JSP路径：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" placeholder="请输入0-100位字符！" value="" datatype="*0-100" id="jspUrl" name="jspUrl" >
                </div>
                <div class="span2"> </div>
            </div>
            <div class="row">
				<label class="form-label span4"><span class="c-red">*</span>录入复核能否同一人：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="bandEnteringcheck" name="bandEnteringcheck" datatype="*1-10" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
					         <option value="Y" selected>能</option>
					         <option value="N">否</option>
                     </select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
				<label class="form-label span4"><span class="c-red">*</span>能否删除数据：</label>
				<div class="formControls span6">
					<select class="select2" size="1" id="deleteAuth" name="deleteAuth" datatype="*1-10" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
							 <option value="Y">能</option>
							 <option value="N">否</option>
					 </select>
				</div>
				<div class="span2"> </div>
			</div>
			<div class="row">
                <label class="form-label span4"><span class="c-red">*</span>能否为单表：</label>
                <div class="formControls span6">
                    <select class="select2" size="1" id="isTbname" name="isTbname" datatype="*1-10" placeholder="不能为空！" nullmsg="请选择！" tipmsg="格式错误!">
                             <option value="Y">是</option>
                             <option value="N">否</option>
                     </select>
                </div>
                <div class="span2"> </div>
            </div>
            <div class="row">
                <label class="form-label span4"><span class="c-red">*</span>命名空间名称：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" placeholder="com.dcits.ensemble.om.pm.dao.utils.WebBaseDao" value="com.dcits.ensemble.om.pm.dao.utils.WebBaseDao" datatype="*1-60" id="namespaceName" name="namespaceName" >
                </div>
                <div class="span2"> </div>
            </div>
            <div class="row" style="display: none">
                <label class="form-label span4">命名空间描述：</label>
                <div class="formControls span6">
                    <textarea name="" cols="" rows=""  id="namespaceDesc" name="namespaceDesc"   style="width:265px" placeholder="请输入0-100位字符" dragonfly="true" datatype="*0-100" nullmsg="请输入!"  tipmsg="格式错误!" onKeyUp="textarealength(this,100)" ></textarea>
                    <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
                </div>
                <div class="span2"> </div>
            </div>
            <div class="row" id="VFJSP">
                <label class="form-label span4">JSP查看路径：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" placeholder="请输入0-100位字符！" value="" datatype="*0-100" id="jspViewurl" name="jspViewurl" >
                </div>
                <div class="span2"> </div>
            </div>
     		<div class="row formBtnArea">
     			<div class="span-offset-1 span10 span-offset-1 mt-10">
     				<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
     			</div>
     		</div>
    </form>
</div>
</body>
</html>
