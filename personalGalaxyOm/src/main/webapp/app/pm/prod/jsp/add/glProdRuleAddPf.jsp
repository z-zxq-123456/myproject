<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/glProdRuleAddPf.js"></script>
<title>参数表GL_PROD_RULE添加</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glProdRuleAdd">
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
    			<div class="formControls span6">
    			      <input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="prodType"  datatype="*1-20" nullmsg=" 请输入！" tipmsg="格式错误!">
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>系统名称：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="sysName" id="sysName" name="sysName" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
    			<div class="formControls span6">
    		  	  	<select id="sourceType" name="sourceType" data-placeholder="渠道类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="1" >1-柜面</option>
                              <option value="2" >2-网银</option>
                              <option value="3" >3-电话银行</option>
                              <option value="4" >4-手机银行</option>
                              <option value="99" >99-其他</option>
						      <option value="ALL" >ALL-全部</option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
    			<div class="formControls span6">
    		  	  	<select id="clientType" name="clientType" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="100" >100-个人</option>
                              <option value="200" >200-公司</option>
                              <option value="300" >300-金融机构</option>
                              <option value="400" >400-政府</option>
                              <option value="500" >500-其他</option>
							  <option value="600" >600-内部</option>
							  <option value="ALL" >ALL-全部</option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>核算状态：</label>
    			<div class="formControls span6">
    		  	  	<select id="accountingStatus" name="accountingStatus" data-placeholder="核算状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
							<option value="ZHC" >ZHC-正常</option>
							<option value="SUS" >SUS-久悬</option>
							<option value="WRN" >WRN-核销</option>
							<option value="FYJ" >FYJ-非应计</option>
							<option value="FY" >FY-非应计(手工)</option>
							<option value="YUQ" >YUQ-逾期</option>
							<option value="TER" >TER-终止</option>
							<option value="ALL" >ALL-全部</option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
    			<div class="formControls span6">
                                      	<select id="tranEventType" name="tranEventType" data-placeholder="事件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>会计分录编号：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="accountingNo" id="accountingNo" name="accountingNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>币种：</label>
    			<div class="formControls span6">
                                      	<select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
    	<div class="row cl">
            	<label class="form-label span4"><span class="c-red"></span>自定义规则编号：</label>
            	<div class="formControls span6">
                               <input type="text" class="input-text grid" value="" placeholder="customRule" datatype="*0-30" id="customRule" name="customRule" tipmsg="格式错误!" >
            	</div>
            	<div class="span2"> </div>
        </div>
    	<div class="row cl">
            	<label class="form-label span4"><span class="c-red"></span>分录描述：</label>
            	<div class="formControls span6">
                        <input type="text" class="input-text grid" value="" placeholder="accountingDesc" datatype="*0-100" id="accountingDesc" name="accountingDesc" tipmsg="格式错误!" >
            	</div>
            	<div class="span2"> </div>
        </div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
    			<div class="formControls span6">
    		  	  	<select id="tranType" name="tranType" data-placeholder="交易类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
    	<div class="row cl">
            	<label class="form-label span4"><span class="c-red">*</span>源类型：</label>
            	<div class="formControls span6">
                        <input type="text" class="input-text grid" value="" placeholder="sourceModule" datatype="*" id="sourceModule" name="sourceModule" tipmsg="格式错误!" >
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