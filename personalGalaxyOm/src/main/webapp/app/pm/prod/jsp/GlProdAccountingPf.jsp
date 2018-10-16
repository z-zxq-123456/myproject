<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/GlProdAccountingPf.js"></script>
    <title>参数表GL_PROD_ACCOUNTING</title>
</head>
<body>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="glProdAccountingPrimaryKey">
    <div class="row cl" style="display:none">
                                  <label class="form-label">产品类型：</label>
                                       <div class="formControls  span2">
                                            <select id="PROD_TYPE"  class="select2" name="prodType"   tabindex="4" >
                                                            </select>
                                                  </div>
                                          <label class="form-label">核算状态：</label>
                                               <div class="formControls  span2">
                                                <select id="ACCT_STATUS" name="acctStatus" class="select2"     >
                                                     <option value="" selected="selected">空</option>
                                                   <option value="ZHC" >ZHC-正常</option>
                                                   <option value="SUS" >SUS-久悬</option>
                                                   <option value="WRN" >WRN-核销</option>
                                                                </select>
                                                 	      </div>
                              <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                                 <i class="iconfont">&#xe624;</i>  查询</a>
                    </div>
     </form>
     <div class="mr-20 ml-20 mt-10">
            <table id="glProdAccounting" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                     <th>产品类型</th>
                     <th>核算状态</th>
                     <th>利润中心</th>
                     <th>账套</th>
                     <th>资产科目代码</th>
                     <th>负债科目代码</th>
                     <th>利息支出科目代码</th>
                     <th>应付利息科目代码</th>
                     <th>利息收入科目代码</th>
                     <th>应收利息科目代码</th>
                     <th>应计利息科目代码</th>
                     <th>罚息收入科目代码</th>
                     <th>应收罚息科目代码</th>
                     <th>应计罚息科目代码</th>
                     <th>复利收入科目代码</th>
                     <th>应收利息科目代码</th>
                     <th>应计利息科目代码</th>
                     <th>资产损失科目</th>
                     <th>科目调整</th>
                    </tr>
                </thead>
            </table>
     </div>
</div>
</body>
</html>