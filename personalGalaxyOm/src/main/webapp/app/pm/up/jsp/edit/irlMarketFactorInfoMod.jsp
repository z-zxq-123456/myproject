<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_MARKET_FACTOR_INFO修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlMarketFactorInfoMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlMarketFactorInfoMod">
			<div  class="row cl" id="IRLSEQNO">
			    <label class="form-label span4"><span class="c-red">*</span>指数序号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="irlSeqNo" name="irlSeqNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
			 <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>市场挂钩类型代码：</label>
                                <div class="formControls span6">
									<select id="marketType" name="marketType" data-placeholder="利率类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
									</select>
													</div>
            			    <div class="span2"> </div>
            			    </div>
               <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>指数值：</label>
                                <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="marketValue" id="marketValue" name="marketValue" datatype="/^([0-9][0-9]*)+(.[0-9]{1,8})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
            			    </div>
            			    <div class="span2"> </div>
            			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
                    <div class="formControls span6">
 			   						<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd'})" id="effectDate" name="effectDate" class="input-text Wdate" style="width:275px;" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
			     </div>
			    <div class="span2"> </div>
			    </div>


                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>失效日期：</label>
                    <div class="formControls span6">
						<input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="endDate" name="endDate" class="input-text Wdate" style="width:275px;" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>状态：</label>
                    <div class="formControls span6">
                        <select id="facStatus" name="facStatus"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>备注：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="remark" datatype="*0-100" id="remark" name="remark" tipmsg="格式错误!" >
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