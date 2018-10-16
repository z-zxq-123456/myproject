<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表AC_SUBJECT修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/edit/acSubjectMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="acSubjectMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>科目代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="subjectCode" name="subjectCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>转账标志：</label>
                    <div class="formControls span6">
                            <select id="tfrInd" name="tfrInd" data-placeholder="转账标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>余额方向：</label>
                    <div class="formControls span6">
                        <select id="balanceWay" name="balanceWay" data-placeholder="余额方向" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-实际</option>
                                <option value="D" >D-借方</option>
                                <option value="C" >C-贷方</option>
                                <option value="B" >B-双向不轧差</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>BSPL类型：</label>
                    <div class="formControls span6">
                        <select id="bsplType" name="bsplType" data-placeholder="BSPL类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-Asset资产</option>
                                <option value="L" >L-Liability负债 </option>
                                <option value="E" >E-Expense费用/支出</option>
                                <option value="I" >I-Income收入</option>
                                <option value="C" >C-表外或有</option>
                                <option value="M" >M-表外备忘</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>科目类型：</label>
                    <div class="formControls span6">
                        <select id="subjectType" name="subjectType" data-placeholder="科目类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="C" >C-Control控制类</option>
                                <option value="S" >S-Subject 实际科目类</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>科目状态：</label>
                    <div class="formControls span6">
                        <select id="subjectStatus" name="subjectStatus" data-placeholder="科目状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-正常</option>
                                <option value="D" >D-注销</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>科目描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="subjectDesc" id="subjectDesc" name="subjectDesc" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否允许手工批量记账：</label>
                    <div class="formControls span6">
                            <select id="manualBatchRes" name="manualBatchRes" data-placeholder="是否允许手工批量记账" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>账户类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="glType" id="glType" name="glType" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否计提营业税：</label>
                    <div class="formControls span6">
                            <select id="operatingTax" name="operatingTax"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>账套：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="businessUnit" datatype="*0-50" id="businessUnit" name="businessUnit" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>转账指示：</label>
                    <div class="formControls span6">
                            <select id="ofTrf" name="ofTrf"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>科目英文描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="subjectDescEn" datatype="*0-100" id="subjectDescEn" name="subjectDescEn" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否允许手工记账：</label>
                    <div class="formControls span6">
                            <select id="manualAccount" name="manualAccount"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否内部科目标志：</label>
                    <div class="formControls span6">
                            <select id="internal" name="internal"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>科目级别：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="subjectLevel" datatype="*0-10" id="subjectLevel" name="subjectLevel" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上级科目：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="controlSubject" datatype="*0-20" id="controlSubject" name="controlSubject" tipmsg="格式错误!" >
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
