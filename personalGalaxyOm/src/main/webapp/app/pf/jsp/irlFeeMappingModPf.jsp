<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/irlFeeMappingModPf.js"></script>
<title>参数表IRL_FEE_MAPPING修改</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeeMappingMod">
		<div class="row cl">
            	<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
            	<div class="formControls span6">
                                             	<select id="prodType" name="prodType" data-placeholder="产品类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                                </select>
            	</div>
            	<div class="span2"> </div>
        </div>
	 <div class="row cl">
             	<label class="form-label span4"><span class="c-red">*</span>费用代码：</label>
             	<div class="formControls span6">
             		<input type="text" class="input-text grid" value=""  id="feeNo" name="feeNo" >
             	</div>
             	<div class="span2"> </div>
     </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>地区码：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="areaCode" id="areaCode" name="areaCode" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
        	<div class="formControls span6">
                                         	<select id="branch" name="branch" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>分类类别：</label>
        	<div class="formControls span6">
                                         	<select id="categoryType" name="categoryType" data-placeholder="分类类别" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>币种：</label>
        	<div class="formControls span6">
                                         	<select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
        	<div class="formControls span6">
                                         	<select id="clientType" name="clientType" data-placeholder="客户分类" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>法人代表：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="company" id="company" name="company" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>证件类型：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="docType" id="docType" name="docType" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="eventType" id="eventType" name="eventType" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="feeType" id="feeType" name="feeType" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>跨行标志：</label>
        	<div class="formControls span6">

                		  	  	<select id="isLocal" name="isLocal" data-placeholder="跨行标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
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

                		  	  	<select id="isRule" name="isRule" data-placeholder="是否使用规则" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                          <option value="Y" >Y-是</option>
                                          <option value="N" >N-否</option>

                                               	</select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>凭证新状态：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="newStatus" id="newStatus" name="newStatus" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>凭证原状态：</label>
        	<div class="formControls span6">
                                                  <input type="text" class="input-text grid" value="" placeholder="oldStatus" id="oldStatus" name="oldStatus" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>所属模块：</label>
        	<div class="formControls span6">
                                         	<select id="prodGrp" name="prodGrp" data-placeholder="所属模块" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
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
                                                   <input type="text" class="input-text grid" value="" placeholder="serviceId" datatype="*0-20" id="serviceId" name="serviceId" tipmsg="格式错误!" >
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
        	<div class="formControls span6">
                                         	<select id="sourceType" name="sourceType" data-placeholder="渠道类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
        	<div class="formControls span6">
                                         	<select id="tranType" name="tranType" data-placeholder="交易类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
        	</div>
        	<div class="span2"> </div>
    </div>
	<div class="row cl">
        	<label class="form-label span4"><span class="c-red">*</span>收费币种标识：</label>
        	<div class="formControls span6">

                		  	  	<select id="urgentFlag" name="urgentFlag" data-placeholder="收费币种标识" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                          <option value="Y" >Y-加急</option>
                                          <option value="N" >N-不加急</option>
                                          <option value="NA" >NA-通配</option>

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
