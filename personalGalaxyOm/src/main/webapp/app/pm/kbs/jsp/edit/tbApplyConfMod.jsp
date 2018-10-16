<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_APPLY_CONF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbApplyConfMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbApplyConfMod">
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>机构代码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="branch" datatype="*1-20" id="branch" name="branch" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>现金管理机构：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cbranch" datatype="*0-6" id="cbranch" name="cbranch" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>现金人行/同业方向B：</label>
                    <div class="formControls span6">
                        <select id="cbsflag" name="cbsflag"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="B" >B-人行</option>
                                <option value="S" >S-同业</option>
                                <option value="N" >N-没有一个方向可行</option>
                                <option value="A" >A-两个方向都可以</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否进行现金预约：</label>
                    <div class="formControls span6">
                        <select id="isCashApply" name="isCashApply"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="Y" >Y-必须进行预约</option>
                                <option value="N" >N-不用进行预约</option>
                                <option value="A" >A-可以预约也可以不预约</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否进行凭证预约：</label>
                    <div class="formControls span6">
                        <select id="isVoucherApply" name="isVoucherApply"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="Y" >Y-必须进行预约</option>
                                <option value="N" >N-不用进行预约</option>
                                <option value="A" >A-可以预约也可以不预约</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>凭证管理机构：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="vbranch" datatype="*0-6" id="vbranch" name="vbranch" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>凭证人行/同业方向：</label>
                    <div class="formControls span6">
                        <select id="vbsflag" name="vbsflag"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="B" >B-人行</option>
                                <option value="S" >S-同业</option>
                                <option value="N" >N-没有一个方向可行</option>
                                <option value="A" >A-两个方向都可以</option>
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
