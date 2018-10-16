<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_INT_MATRIX修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlIntMatrixMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlIntMatrixMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>阶梯序号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="matrixNo" name="matrixNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
			   <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>序号：</label>
                                <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="irlSeqNo" id="irlSeqNo" name="irlSeqNo" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!">
            			    </div>
            			    <div class="span2"> </div>
            			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>阶梯金额：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="matrixAmt" id="matrixAmt" name="matrixAmt" datatype="n1-9|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>

	<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>频率类型：</label>
							<div class="formControls span6">
												<select id="periodFreq" name="periodFreq" class="select2" tabindex="4" tipmsg="格式错误!">
												</select>
							</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>每期天数：</label>
								<div class="formControls span6">
								<input type="text" class="input-text grid" value="" placeholder="dayNum" id="dayNum" name="dayNum" datatype="n0-6"  tipmsg="格式错误!">

								</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>基准利率类型：</label>
									<div class="formControls span6">
														<select id="intBasis" name="intBasis" class="select2" tabindex="4" tipmsg="格式错误!">
														</select>
									</div>

						<div class="span2"> </div>
					</div>
				    <div class="row cl" style="display:none">
                                    <label class="form-label span4"><span class="c-red"></span>基准利率：</label>
                                    <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="baseRate" id="baseRate" name="baseRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
                			    </div>
                			    <div class="span2"> </div>
					</div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>浮动点数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="spreadRate" id="spreadRate" name="spreadRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利率浮动百分比：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="spreadPercent" id="spreadPercent" name="spreadPercent" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
			      <div class="row cl">
                                    <label class="form-label span4"><span class="c-red">*</span>实际利率：</label>
                                    <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="actualRate" id="actualRate" name="actualRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
                			    </div>
                			    <div class="span2"> </div>
                			    </div>
                       <div class="row cl">
                                   <label class="form-label span4"><span class="c-red"></span>最小利率：</label>
                                   <div class="formControls span6">
                                               <input type="text" class="input-text grid" value="" placeholder="minRate" id="minRate" name="minRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>


                               <div class="row cl">
                                   <label class="form-label span4"><span class="c-red"></span>最大利率：</label>
                                   <div class="formControls span6">
                                               <input type="text" class="input-text grid" value="" placeholder="maxRate" id="maxRate" name="maxRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>

 					<div class="row cl">
 						<label class="form-label span4"><span class="c-red">*</span>子利率类型：</label>
 						<div class="formControls span6">
 											<select id="subIntType" name="subIntType"  class="select2" tabindex="4" tipmsg="格式错误!">
 											</select>
 						</div>
 						<div class="span2"> </div>
 					</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利率终结标志：</label>
                    <div class="formControls span6">
                             		<select id="isOver" name="isOver" data-placeholder="利率终结标志" class="select2"  tabindex="4" datatype="*0-1" tipmsg="格式错误!">
                             															<option value="" >空</option>
                             															<option value="Y" >Y-终结</option>
                             															<option value="N" >N-非终结</option>
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
