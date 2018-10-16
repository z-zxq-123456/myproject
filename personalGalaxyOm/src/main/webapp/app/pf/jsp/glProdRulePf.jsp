<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/glProdRulePf.js"></script>
    <title>参数表GL_PROD_RULE</title>
</head>
<body>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="glProdRulePrimaryKey">
    <div class="row cl" style="display:none;">
                  <label class="form-label">产品类型：</label>
                       <div class="formControls  span2">
                            <select id="PROD_TYPE"  class="select2" name="prodType"   tabindex="4" >
                                            </select>
                                  </div>
                          <label class="form-label">事件类型：</label>
                               <div class="formControls  span2">
                                <select id="TRAN_EVENT_TYPE"  class="select2" name="tranEventType"   tabindex="4" >
                                                </select>
                                    </div>
                          <label class="form-label">交易类型：</label>
                             <div class="formControls  span2">
                                <select id="TRAN_TYPE" name="tranType" class="select2"     >
                                     <option value="" selected="selected">空</option>
                                   <option value="1000" >1000-现金存入</option>
                                   <option value="1003" >1003-现金支取</option>
                                   <option value="4183" >4183-行内转账存入</option>
                                   <option value="4180" >4180-行内转账支取</option>
                                   <option value="ACR" >ACR-计提</option>
                                   <option value="CYCLE" >CYCLE-结息</option>
                                 </select>
                             </div>
                              <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                           <i class="iconfont">&#xe624;</i>  查询</a>
                    </div>
     </form>
     <div class="mr-20 ml-20 mt-10">
            <table id="glProdRule" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                     <th>产品类型</th>
                     <th>系统名称</th>
                     <th>渠道类型</th>
                     <th>客户分类</th>
                     <th>核算状态</th>
                     <th>事件类型</th>
                     <th>会计分录编号</th>
                     <th>币种</th>
                     <th>自定义规则编号</th>
                     <th>分录描述</th>
                     <th>交易类型</th>
                    </tr>
                </thead>
            </table>
     </div>
</div>
</body>
</html>