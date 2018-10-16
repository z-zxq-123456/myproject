<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表RC_LIST_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/rcListTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="rcListTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>名单类型代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="listType" name="listType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>名单种类：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="listCategory" datatype="*0-12" id="listCategory" name="listCategory" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>核实期限：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="verifyTerm" datatype="*0-5" id="verifyTerm" name="verifyTerm" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否需要核实本身：</label>
                    <div class="formControls span6">
                            <select id="verifyInd" name="verifyInd"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否需要核实关联账户：</label>
                    <div class="formControls span6">
                            <select id="verifyAcctInd" name="verifyAcctInd"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否关联同一客户下的其他账户标识：</label>
                    <div class="formControls span6">
                            <select id="relateAcctInd" name="relateAcctInd"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>名单类型描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="listTypeDesc" datatype="*0-500" id="listTypeDesc" name="listTypeDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>发送机构：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="listOrg" datatype="*0-12" id="listOrg" name="listOrg" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>核实期限类型：</label>
                    <div class="formControls span6">
                        <select id="verifyTermType" name="verifyTermType"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="Y" >Y-年</option>
                                <option value="M" >M-月</option>
                                <option value="D" >D-日</option>
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
