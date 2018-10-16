<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/IrlFeeMappingPf.js"></script>
    <title>参数表IRL_FEE_MAPPING</title>
</head>
<body>
<div class="padding-10">

    <form action="" method="post" class="form form-horizontal" id="irlFeeMappingPrimaryKey">
    <div class="row cl" style="display:none">
                                          <label class="form-label">费用代码：</label>
                                               <div class="formControls  span2">

                                                                           <input type="text"  value="" class="input-text grid" placeholder="feeNo" id="FEE_NO" name="feeNo"  >

                                                 	      </div>

                              <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                                                                                                                <i class="iconfont">&#xe624;</i>  查询</a>
                    </div>
     </form>
     <div class="mr-20 ml-20 mt-10">
            <table id="irlFeeMapping" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                     <th>产品类型</th>
                     <th>地区码</th>
                     <th>机构代码</th>
                     <th>分类类别</th>
                     <th>币种</th>
                     <th>客户分类</th>
                     <th>法人代表</th>
                     <th>证件类型</th>
                     <th>事件类型</th>
                     <th>费用代码</th>
                     <th>费率类型</th>
                     <th>跨行标志</th>
                     <th>是否使用规则</th>
                     <th>凭证新状态</th>
                     <th>凭证原状态</th>
                     <th>产品组</th>
                     <th>服务代码</th>
                     <th>渠道类型</th>
                     <th>交易类型</th>
                     <th>收费币种标识</th>
                    </tr>
                </thead>
            </table>
     </div>
</div>
</body>
</html>
