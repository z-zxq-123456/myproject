<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_FEE_MAPPING修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlFeeMappingMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeeMappingMod">
<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="irlSeqNo" id="irlSeqNo" name="IRL_FEE_MAPPING" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
						</div>
						<div class="span2"> </div>
					</div>
				  <div class="row cl">
                                    <label class="form-label span4"><span class="c-red">*</span>费率类型：</label>
                                    <div class="formControls span6">
                                                <select id="feeType" name="feeType" data-placeholder="费率类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                </select>
                			    </div>
                			    <div class="span2"> </div>
                			    </div>
                 <div class="row cl">
                                    <label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
                                    <div class="formControls span6">
                                                <select id="branchRule" name="branchRule" data-placeholder="机构代码" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                </select>
                			    </div>
                			    <div class="span2"> </div>
                			    </div>
                			      <div class="row cl">
                                                   <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
                                                   <div class="formControls span6">
                                                                 	<select id="eventTypeRule" name="eventTypeRule" datatype="*1-20"  class="select2" tabindex="4" tipmsg="格式错误!">
                                                                                         											</select>
                               			    </div>
                               			    <div class="span2"> </div>
                               			    </div>
                                <div class="row cl">
                                                   <label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
                                                   <div class="formControls span6">
                                                               <select id="tranTypeRule" name="tranTypeRule" data-placeholder="交易类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                               </select>
                               			    </div>
                               			    <div class="span2"> </div>
                               			    </div>
                               	 <div class="row cl">
                                                    <label class="form-label span4"><span class="c-red">*</span>产品组：</label>
                                                    <div class="formControls span6">
                                                                <select id="prodGrpRule" name="prodGrpRule" data-placeholder="产品组" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                                </select>
                                			    </div>
                                			    <div class="span2"> </div>
                                			    </div>
                                                <div class="row cl">
                                                    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
                                                    <div class="formControls span6">
                                                                <select id="prodTypeRule" name="prodTypeRule" data-placeholder="产品类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                                </select>
                                			    </div>
                                			    <div class="span2"> </div>
                                			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>加急标志：</label>
                    <div class="formControls span6">
                        <select id="urgentFlag" name="urgentFlag" data-placeholder="加急标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >Y-加急</option>
                                <option value="N" >N-不加急</option>
                                <option value="ALL" >ALL-通配</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
			    <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>跨行标志：</label>
                                   <div class="formControls span6">
               								<select id="isLocal" name="isLocal" data-placeholder="跨行标志" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                                <option value="B" >B-跨他行</option>
                                                <option value="O" >O-跨机构</option>
                                                <option value="S" >S-同机构</option>
                                                <option value="ALL" >ALL-通配</option>
               								</select>
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>地区：</label>
                                   <div class="formControls span6">
                                   <select id="areaRule" name="areaRule" data-placeholder="地区" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                       <option value="LC" >LC–同城</option>
                                       <option value="IP" >IP–省内异地</option>
                                       <option value="OP" >OP–省外异地</option>
                                       <option value="OS" >OS–境外</option>
                                       <option value="ALL" >ALL-通配</option>

                                   </select>
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                 <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>币种：</label>
                                   <div class="formControls span6">
                                               <select id="ccyRule" name="ccyRule" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                               </select>
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                 <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>客户类型：</label>
                                   <div class="formControls span6">
                                               <select id="clientTypeRule" name="clientTypeRule" data-placeholder="客户类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                               </select>
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
               			                   <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>客户类型细类：</label>
                    <div class="formControls span6">
                                <select id="categoryTypeRule" name="categoryTypeRule" data-placeholder="客户类型细类" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
   <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
                    <div class="formControls span6">
                        <select id="sourceTypeRule" name="sourceTypeRule" data-placeholder="渠道类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="docTypeRule" id="docTypeRule" name="docType" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                            <div class="row cl">
                                          <label class="form-label span4"><span class="c-red">*</span>凭证/票据变更前状态：</label>
                                          <div class="formControls span6">
                                                         <select id="oldStatusRule" name="oldStatusRule" data-placeholder="凭证/票据变更前状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                                                                                                                             											</select>
                      			    </div>
                      			    <div class="span2"> </div>
                      			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证/票据变更后状态：</label>
                    <div class="formControls span6">
                                   	<select id="newStatusRule" name="newStatusRule" data-placeholder="凭证/票据变更前状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
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
                    <label class="form-label span4"><span class="c-red"></span>服务代码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="serviceIdRule" datatype="*0-20" id="serviceIdRule" name="serviceIdRule" tipmsg="格式错误!" >
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

