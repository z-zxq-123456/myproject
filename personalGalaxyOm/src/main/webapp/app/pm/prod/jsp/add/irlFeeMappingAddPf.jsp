<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/irlFeeMappingAddPf.js"></script>
<title>参数表IRL_FEE_MAPPING添加</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeeMappingAdd">
			<div class="row cl">
        			<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
        			<div class="formControls span6">
        			      <input type="text" class="input-text grid" value="" placeholder="prodTypeRule" datatype="*" id="prodTypeRule" name="prodTypeRule" nullmsg=" 请输入！" tipmsg="格式错误!" >
        				</div>
        			<div class="span2"> </div>
        	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>地区码：</label>
    			<div class="formControls span6">
                     <input type="text" class="input-text grid" value="" placeholder="areaRule" id="areaRule" name="areaRule" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
    			<div class="formControls span6">
                       <select id="branchRule" name="branchRule" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>分类类别：</label>
    			<div class="formControls span6">
                                      	<select id="categoryTypeRule" name="categoryTypeRule" data-placeholder="分类类别" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>币种：</label>
    			<div class="formControls span6">
                                      	<select id="ccyRule" name="ccyRule" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
    			<div class="formControls span6">
                                      	<select id="clientTypeRule" name="clientTypeRule" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>法人代表：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="companyRule" id="companyRule" name="companyRule" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>证件类型：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="docTypeRule" id="docTypeRule" name="docTypeRule" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="eventTypeRule" id="eventTypeRule" name="eventTypeRule" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>费用代码：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="irlSeqNo" id="irlSeqNo" name="irlSeqNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="feeType" id="feeType" name="feeType" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>跨行标志：</label>
    			<div class="formControls span6">
    		  	  	<select id="isLocalRule" name="isLocalRule" data-placeholder="跨行标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="LC" >LC–同城</option>
                              <option value="DP" >DP–异地</option>
                              <option value="OS" >OS–境外</option>
                              <option value="NA" >NA–通配</option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>是否使用规则：</label>
    			<div class="formControls span6">
    		  	  	<select id="isRule" name="isRule" data-placeholder="是否使用规则" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="Y" >Y-是</option>
                              <option value="N" >N-否</option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>凭证新状态：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="newStatusRule" id="newStatusRule" name="newStatusRule" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>凭证原状态：</label>
    			<div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="oldStatusRule" id="oldStatusRule" name="oldStatusRule" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!" >
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>所属模块：</label>
    			<div class="formControls span6">
                                      	<select id="prodGroupRule" name="prodGroupRule" data-placeholder="所属模块" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="RB" > RB-负债 </option>
                              <option value="CL" >CL-资产 </option>
                              <option value="GL" > GL-总账 </option>
                              <option value="MM" >MM-货币市场</option>
                              <option value="ALL" >ALL-全部使用 </option>
                                   	</select>
    				</div>
    			<div class="span2"> </div>
    	</div>
    	<div class="row cl">
            	<label class="form-label span4"><span class="c-red"></span>服务代码：</label>
            	<div class="formControls span6">
                                                      <input type="text" class="input-text grid" value="" placeholder="serviceIdRule" datatype="*0-20" id="serviceIdRule" name="serviceIdRule" tipmsg="格式错误!" >
            	</div>
            	<div class="span2"> </div>
        </div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
    			<div class="formControls span6">
                                      	<select id="sourceTypeRule" name="sourceTypeRule" data-placeholder="渠道类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
    			<div class="formControls span6">
                                      	<select id="tranTypeRule" name="tranTypeRule" data-placeholder="交易类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                      	        </select>
    				</div>
    			<div class="span2"> </div>
    	</div>
		<div class="row cl">
    			<label class="form-label span4"><span class="c-red">*</span>收费币种标识：</label>
    			<div class="formControls span6">
    		  	  	<select id="urgentFlagRule" name="urgentFlagRule" data-placeholder="收费币种标识" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                              <option value="Y" >Y-加急</option>
                              <option value="N" >N-不加急</option>
                              <option value="NA" >NA-通配</option>
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
