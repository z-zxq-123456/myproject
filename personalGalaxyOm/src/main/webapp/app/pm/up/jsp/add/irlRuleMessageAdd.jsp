<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>

<html>
	<head>
    <title>Sm@rtLi-Market-利率市场化</title>
    	<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlRuleMessageAdd.js"></script>
	</head>
	<body>
	   <div class="mr-10 ml-10">
       </div>
	   <div>
      <form id="getRuleConfig">
	 	    <div class="span-offset-4  span4 span-setoff-4 ">
        	 	<span class="l">
        			<a href="javascript:void(0);" onclick="finish()" class="button-select M ml-20 mr-10"><i class="iconfont">&#xe657;</i>提交</a>
        	 		<a href="javascript:location.replace(location.href);" onclick="clearForm()" class="button-select M ml-10 mr-20"><i class="iconfont">&#xe643;</i>重置</a>
        	 	</span>
         </div>
         <div id="tab-system" class="tabbable-custom content-no-border">
                 <ul class="nav nav-tabs"  >
                     <li class="active"><a href="#tab_1_1"  data-toggle="tab" >产品定义</a></li>
                     <li><a href="#tab_1_2" data-toggle="tab" >规则定义</a></li>
                     <li><a href="#tab_1_3"  data-toggle="tab">条件/浮动值定义</a></li>
                 </ul>
                 <div class="tab-content">
                     <div  class="tab-pane active" id="tab_1_1">
                         <div class="tabCon form form-horizontal">
                                    <div class="row">
                                         <label class="form-label span4"><span class="c-red">*</span>交易柜员：</label>
                                         <div class="formControls span4">
                                              <input type="text" class="input-text grid" id="userId" placeholder="交易柜员"  name="userId" datatype="*" nullmsg="柜员编号不能为空">
                                         </div>
                                         <div class="span4"></div>
                                    </div>
                                    <div class="row">
                                         <label class="form-label span4"><span class="c-red">*</span>规则分类1：</label>
                                         <div class="formControls span4">
                                                 <select name="ruleType" id="ruleType" size="1" class="select2" >
                                                          <option value="">请选择：</option>
                                                          <option value="INRATE">利率</option>
                                                          <option value="FEE">费率</option>
                                                          <option value="EXRATE">汇率</option>
                                                          <option value="DISC">折扣</option>
                                                 </select>
                                         </div>
                                         <div class="span4"></div>
                                    </div>

                                    <div class="row">
                                         <label class="form-label span4"><span class="c-red">*</span>规则分类2：</label>
                                         <div class="formControls span4" id="GroupLi"  >
                                                 <select name="prodGroupLi" id="prodGroupLi" size="1" class="select2"  style="width:235px;">
                                                          <option value="">请选择：</option>
                                                          <option value="RB">存款产品组</option>
                                                          <option value="CL">贷款产品组</option>
                                                          <option value="GLOBAL">全局</option>
                                                 </select>
                                         </div>
                                      <div class="formControls span4" id="GroupFee"  style="display:none" >
                                              <select name="prodGroupFee" id="prodGroupFee" size="1" class="select2"style="width:235px;" >
                                                  <option value="">请选择：</option>
                                                  <option value="GLOBAL">全局</option>
                                                  <option value="GROUP">产品组</option>
                                                  <option value="FEE">费率</option>
                                             </select>
                                      </div>
                                    <div class="formControls span4" id="GroupExch"  style="display:none " >
                                         <select name="prodGroupExch" id="prodGroupExch" size="1" class="select2" style="width:235px;">
                                                 <option value="">请选择：</option>
                                                    <option value="GLOBAL">全局</option>
                                                    <option value="GROUP">产品组</option>
                                                 <option value="RATETYPE">汇率类型</option>
                                        </select>
                                    </div>
                                   <div class="formControls span4" id="GroupDisc"  style="display:none " >
                                          <select name="prodGroupDisc" id="prodGroupDisc" size="1" class="select2"style="width:235px;" >
                                               <option value="">请选择：</option>
                                                 <option value="GLOBAL">全局</option>
                                                 <option value="GROUP">产品组</option>
                                               <option value="DISC">折扣</option>
                                          </select>
                                    </div>
                                         <div class="span4"></div>
                                    </div>
                                    <div class="row">
                                         <label class="form-label span4"><span class="c-red">*</span>规则分类3：</label>
                                         <div class="formControls span4" id="prod0">
                                               <select name="prodType" id="prodType" size="1" class="select2"  style="width:235px;">
                                                </select>
                                         </div>
                                          <div class="formControls span4" id="prod1"  style="display:none " >
                                                           <select name="prodGroupDisc" id="prodGroupRB" size="1" class="select2"style="width:235px;" >
                                                                  <option value="clglobal">贷款产品</option>
                                                                  <option value="rbglobal">存款产品</option>
                                                           </select>
                                             </div>

                                         <div class="span4"></div>
                                    </div>
                                 <div class="row" id="CCY" style="display:none">
                                      <label class="form-label span4">目标币种：</label>
                                      <div class="formControls span2">
                                            <select name="TCcy" id="TCcy" size="1" class="select2"  style="width:125px;">
                                                                                         </select>
                                      </div>
                                     <div class="span"></div>
                                      <label class="form-label span2">源币种：</label>
                                      <div class="formControls span2">
                                             <select name="SCcy" id="SCcy" size="1" class="select2"  style="width:125px;">
                                                                                                                     </select>
                                      </div>
                                      <div class="span"></div>
                                 </div>

                         </div>
                     </div>
                     <div  class="tab-pane" id="tab_1_2">
                         <div class="tabCon form form-horizontal">
                                         <div class="row">
                                              <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
                                              <div class="formControls span4">
                                                  <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMddHHmmss'})" id="startDate" name="startDate" class="input-text Wdate" style="width:275px;">
                                              </div>
                                              <div class="span4"></div>
                                         </div>
                                         <div class="row">
                                              <label class="form-label span4"><span class="c-red">*</span>失效日期：</label>
                                              <div class="formControls span4">
                                                     <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMddHHmmss'})" id="endDate" name="endDate" class="input-text Wdate" style="width:275px;">
                                              </div>
                                              <div class="span4"></div>
                                         </div>
                                         <div class="row">
                                              <label class="form-label span4"><span class="c-red">*</span>规则状态：</label>
                                              <div class="formControls span4">
                                                    <select name="status" id="status" size="1" class="select2"  style="width:275px;" >
                                                           <option value="">请选择：</option>
                                                           <option value="0">暂不生效</option>
                                                           <option value="1">立即生效</option>
                                                    </select>
                                              </div>
                                              <div class="span4"></div>
                                         </div>
                         </div>
                     </div>
                     <div  class="tab-pane" id="tab_1_3">
                         <div class="tabCon form form-horizontal" id="emptyCatch">
                                <div class="row" id="groupId0">
                                     <label class="form-label span4"><span class="c-red">*</span>分组类型：</label>
                                     <div class="formControls span4" >
                                           <select name="groupType" id="groupType" size="1" class="select2" style="width:100%">
                                           </select>
                                     </div>
                                  </div>

                             <div class="row">
                                 <label class="form-label span2"><span class="c-red">*</span>因子：</label>

                                  <div class="formControls span3"  id="info1">
                                      <select name="factInfo1" id="factInfo1" size="1" class="select2"  style="width:100%">
                                                                                 </select>
                                  </div>
                                <div class="formControls span3"  id="Attr" style="display:none">
                                   <input id="elementAttr" class="input-text grid"  name="elementAttr"   style="width:100%">
                                </div>
                                <div class="formControls span3"  id="matchType0" style="display:none">
                                   <input id="matchType" class="input-text grid"  name="matchType"   style="width:100%">
                                </div>
                                 <label class="form-label span2"><span class="c-red">*</span>取值：</label>
                                  <div class="formControls span3" id="DivinValue1">
                                       <select name="inValue" id="inValue" size="1" class="select2" style="width:100%">
                                       </select>
                                  </div>
                                  <div id="DivinValue2" style="display:none" class="formControls span3">
                                        <input id="inValue1" class="input-text grid"  name="inValue" >
                                  </div>
                            </div>
                             <div class="row">
                              <label class="form-label span2"><span class="c-red">*</span>条件：</label>
                              <div class="formControls span3">
                                     <select name="condition" id="condition" size="1" class="select2" style="width:100%">
                                              <option value="">请选择:</option>
                                              <option value="==">等于</option>
                                              <option value=">">大于</option>
                                              <option value="<">小于</option>
                                              <option value=">=">大于等于</option>
                                              <option value="<=">小于等于</option>
                                              <option value="!=">不等于</option>
                                              <option value="matches">matches</option>
                                     </select>
                              </div>

                                 <div class="span" id="float1"></div>
                                 <label class="form-label span2"><span class="c-red">*</span>浮动点：</label>
                                 <div class="formControls span3" >
                                             <input type="text" class="input-text grid" id="floatValue" placeholder="浮动点"  name="floatValue" datatype="*" nullmsg="金额不能为空">
                                 </div>
                                 <div class="span" id="type"></div>
                             </div>
                             <div class="row" id="float0">
                              <label class="form-label span2"><span class="c-red">*</span>浮动方式：</label>
                              <div class="formControls span3" >
                                  <select name="floatType" id="floatType" size="1" class="select2" style="width:100%">
                                           <option value="">请选择：</option>
                                           <option value="2">按固定值</option>
                                   </select>
                              </div>

                              <div class="formControls span3" id="ruleWeight0" style="display:none">
                               <input type="text" class="input-text grid" id="ruleWeight" placeholder="规则权重"  name="ruleWeight" datatype="n0-7">
                              </div>

                              <div class="span"></div>
                             </div>
                            <div id="tab-system" class="tabbable-custom " style="margin-left:100px;margin-right:100px;margin-top:40px">
                                <ul class="nav nav-tabs"  >
                                               <li class="active"><a href="#tab_1_4"  data-toggle="tab" >描述语句</a></li>
                                               <li><a href="#tab_1_5" data-toggle="tab" >规则代码</a></li>
                                </ul>
                                <div class="tab-content">
                                <div  class="tab-pane active" id="tab_1_4">
                                  <div class="tabCon form form-horizontal">
                                      <textarea id="details1" name="details1" cols="" rows=""   dragonfly="true"  style="width:450px;height:200px" ></textarea>
                                  </div>
                                </div>
                                <div  class="tab-pane" id="tab_1_5">
                                   <div class="tabCon form form-horizontal">
                                       <textarea id="details" name="details" cols="" rows=""  dragonfly="true" style="width:450px;height:200px"  ></textarea>
                                    </div>
                                </div>
                                </div>
                                <br />
                                <a href="javascript:void(0)"onclick="addClick()" id="btnAdd" name="btnAdd" class="button-select M">增加</a> &nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="clsClick()" id="btnCls" name="btnCls" class="button-select M">消除</a> &nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="andClick()" id="btnAddOR" name="btnAddOR" class="button-select M">&nbsp;与&nbsp;</a> &nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="orClick()"  id="btnAddOr" name="btnAddOr" class="button-select M">&nbsp;或&nbsp;</a> &nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="leftClick()" id="btnAddLeft" name="btnAddLeft" class="button-select M">&nbsp;(&nbsp;</a> &nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="rightClick()" id="rightClick" name="rightClick" class="button-select M">&nbsp;)&nbsp;</a>
                          </div>
                         </div>
                     </div>
                 </div>
             </div>
            </form>
        </div>
	</body>
</html>



  