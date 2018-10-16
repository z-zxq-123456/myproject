<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlAmtExpressionMod.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="irlAmtExpressionMod">

        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>表达式编码：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="expressId" name="expressId">
            </div>
            <div class="span2"></div>
        </div>


        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>表达式说明：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="expressionDesc" id="expressionDesc"
                       name="expressionDesc" datatype="*1-500" nullmsg=" 请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
<div class="row">
                 <label class="form-label span4"><span class="c-red"></span>金额类型：</label>
                 <div class="formControls span6">
                       <select name="amtType" id="amtType" size="1" class="select2" >
                        </select>
                 </div>
                 <div class="span"></div>
            </div>
             <div class="row">
                  <label class="form-label span4"><span class="c-red"></span>符号：</label>
                  <div class="formControls span2">
                      <select name="symbol" id="symbol" size="1" class="select2" style="width:100%">
                               <option value=""></option>
                               <option value="+">加</option>
                               <option value="-">减</option>
                               <option value="*">乘</option>
                               <option value="/">除</option>
                       </select>
                  </div>
                 <div class="span"></div>
                  <label class="form-label span2"><span class="c-red"></span>数字：</label>
                  <div class="formControls span2">
                      <input type="text" class="input-text grid" value="" placeholder="数字" id="number"
                             name="number" datatype="n0-9"  tipmsg="格式错误!">
                  </div>
                  <div class="span"></div>
             </div>
            <div class="row cl">
                <label class="form-label span4"><span class="c-red">*</span>表达式：</label>
                <div class="formControls span6">
                    <input type="text" class="input-text grid" value="" placeholder="expression" id="expression"
                           name="IRL_AMT_EXPRESSION" datatype="*1-500" nullmsg=" 请输入！" tipmsg="格式错误!"
                           ajaxurl="${ContextPath}/baseCommon/verifyExpressFormat">
                </div>
               <div class="span2"></div>
            </div>
              <div class="row cl" style="margin-left:200px;">
                <a href="javascript:void(0)"onclick="addClick()" id="btnAdd" name="btnAdd" class="button-select M">增加</a> &nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="clsClick()" id="btnCls" name="btnCls" class="button-select M">消除</a> &nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="leftClick()" id="btnAddLeft" name="btnAddLeft" class="button-select M">&nbsp;(&nbsp;</a> &nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="rightClick()" id="rightClick" name="rightClick" class="button-select M">&nbsp;)&nbsp;</a>
              </div>

        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改" value="修改">
            </div>
        </div>
    </form>
</div>
</body>
</html>

