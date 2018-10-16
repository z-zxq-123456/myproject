<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_FEE_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlFeeTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeeTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>费用类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="feeType" name="feeType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			 <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>费用类型描述：</label>
                                <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="feeDesc" id="feeDesc" name="feeDesc" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!">
            			    </div>
            			    <div class="span2"> </div>
            			    </div>
            			       <div class="row cl">
                                                <label class="form-label span4"><span class="c-red">*</span>产品组：</label>
                                                <div class="formControls span6">
                                                            <select id="prodGrp" name="prodGrp" data-placeholder="产品组" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                                            </select>
                            			    </div>
                            			    <div class="span2"> </div>
                            			    </div>
            			           <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>收费方式：</label>
                    <div class="formControls span6">
                        <select id="feeMode" name="feeMode" data-placeholder="收费方式" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="F" >F-固定金额</option>
                                <option value="R" >R-固定比例</option>
                                <option value="B" >B-固定金额+比例</option>
                                <option value="S" >S-差额累进</option>
                                <option value="T" >T-全额累进</option>
                                <option value="L" >L-按基准利率收费</option>
                                <option value="A" >A-按数量</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
    <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缺口计算金额编码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="boundaryAmtId" datatype="*0-30" id="boundaryAmtId" name="boundaryAmtId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
   <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>费用计算金额编码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="feeAmtId" datatype="*0-30" id="feeAmtId" name="feeAmtId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
			   <div class="row cl">
                                 <label class="form-label span4"><span class="c-red"></span>缺口描述：</label>
                                 <div class="formControls span6">
                                             <input type="text" class="input-text grid" value="" placeholder="boundaryDesc" datatype="*0-100" id="boundaryDesc" name="boundaryDesc" tipmsg="格式错误!" >
             			    </div>
             			    <div class="span2"> </div>
             			    </div>
   <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>收费币种标识：</label>
                    <div class="formControls span6">
                        <select id="ccyFlag" name="ccyFlag" data-placeholder="收费币种标识" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="T" >T-交易币种收费</option>
                                <option value="S" >S-指定币种收费</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
	     <div class="row cl">
                        <label class="form-label span4"><span class="c-red"></span>目标收费币种：</label>
                        <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="mbCcyType" datatype="*0-3" id="mbCcyType" name="mbCcyType" tipmsg="格式错误!" >
    			    </div>
    			    <div class="span2"> </div>
    			    </div>
    	   <div class="row cl">
                            <label class="form-label span4"><span class="c-red"></span>折算标志：</label>
                            <div class="formControls span6">
                                <select id="convertFlag" name="convertFlag"  class="select2" tipmsg="格式错误!" >
                                    <option value="" selected="selected">空</option>
                                        <option value="N" >N-不折算</option>
                                        <option value="B" >B-比率前折算</option>
                                        <option value="A" >A-比率后折算</option>
                                </select>
        			    </div>
        			    <div class="span2"> </div>
        			    </div>
   <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>日终/联机标志：</label>
                    <div class="formControls span6">
                        <select id="boInd" name="boInd" data-placeholder="日终/联机标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="O" >O-联机</option>
                                <option value="B" >B-批量</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>折扣方式：</label>
                    <div class="formControls span6">
                        <select id="disType" name="disType" data-placeholder="折扣方式" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
           										<option value="01" >01-折上折</option>
           										<option value="02" >02-取最大折扣</option>
           										<option value="03" >03-取最优折扣</option>
           										<option value="04" >04-取平均值折扣</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>增值税类型：</label>
                    <div class="formControls span6">
                                        <select id="taxType" name="taxType" datatype="*0-3" class="select2" tabindex="4" tipmsg="格式错误!">
                                        </select>
                    </div>
                    <div class="span2"> </div>
                </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>费用项目代码：</label>
            <div class="formControls span6">
                <select id="feeItem" name="feeItem"  class="select2" tabindex="4" tipmsg="格式错误!">
                 </select>
            </div>
            <div class="span2"> </div>
        </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
											<select id="company" name="company" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
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
