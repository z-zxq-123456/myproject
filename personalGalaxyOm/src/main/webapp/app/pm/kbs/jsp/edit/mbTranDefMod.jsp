<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_TRAN_DEF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbTranDefMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbTranDefMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="tranType" name="tranType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>现金交易：</label>
                    <div class="formControls span6">
                            <select id="cashTran" name="cashTran" data-placeholder="现金交易" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>余额类型次序：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="balTypePriority" datatype="*0-2" id="balTypePriority" name="balTypePriority" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>借贷标志：</label>
                    <div class="formControls span6">
                        <select id="crDrMaintInd" name="crDrMaintInd"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="D" >D-借方</option>
                                <option value="C" >C-贷方</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>更正交易：</label>
                    <div class="formControls span6">
                            <select id="isCorrect" name="isCorrect"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>多种冲正方式标志：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="multiRvsTranTypeInd" datatype="*0-20" id="multiRvsTranTypeInd" name="multiRvsTranTypeInd" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>对方交易类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="othTranType" datatype="*0-4" id="othTranType" name="othTranType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>凭证打印交易描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="printTranDesc" datatype="*0-20" id="printTranDesc" name="printTranDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易类型与交易界面对应关系：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="programIdGroup" datatype="*0-100" id="programIdGroup" name="programIdGroup" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>重新计算余额止付标志：</label>
                    <div class="formControls span6">
                            <select id="recalcAcctStopPay" name="recalcAcctStopPay"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>重新计算限制金额标志：</label>
                    <div class="formControls span6">
                            <select id="recalcResAmt" name="recalcResAmt"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>冻结级别(限制级别)：</label>
                    <div class="formControls span6">
                        <select id="resPriority" name="resPriority"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="1" >1-1</option>
                                <option value="2" >2-2</option>
                                <option value="3" >3-3</option>
                                <option value="4" >4-4</option>
                                <option value="5" >5-5</option>
                                <option value="6" >6-6</option>
                                <option value="7" >7-7</option>
                                <option value="8" >8-8</option>
                                <option value="9" >9-9</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>冲正交易标志：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="reversalTranFlag" datatype="*0-1" id="reversalTranFlag" name="reversalTranFlag" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>冲正交易类型：</label>
                    <div class="formControls span6">
                            <select id="reversalTranType" name="reversalTranType"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>渠道类型：</label>
                    <div class="formControls span6">
                                <select id="sourceType" name="sourceType" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易类别：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranClass" datatype="*0-10" id="tranClass" name="tranClass" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranTime" id="tranTime" name="tranTime" datatype="n0-11"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易时间戳：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranTimestamp" datatype="*0-17" id="tranTimestamp" name="tranTimestamp" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易类型描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranTypeDesc" datatype="*0-50" id="tranTypeDesc" name="tranTypeDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否更新尾箱：</label>
                    <div class="formControls span6">
                            <select id="updTrailboxFlag" name="updTrailboxFlag"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>收付款标志：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="availbalCalcType" datatype="*0-10" id="availbalCalcType" name="availbalCalcType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>余额标志：</label>
                    <div class="formControls span6">
                        <select id="balanceFlag" name="balanceFlag"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="B" >B-余额</option>
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
