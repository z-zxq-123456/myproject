<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/glProdMappingPf.js"></script>
    <title>参数表GL_PROD_MAPPING</title>
</head>
<body>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="glProdMappingPrimaryKey">
    <div class="row cl" style="display:none;">
          <label class="form-label">映射产品类型：</label>
               <div class="formControls  span2">
                        <select id="MAPPING_TYPE"  class="select2" name="mappingType"   tabindex="4" >
                                        </select>
                          </div>
                              <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
                    </div>
     </form>
     <div class="mr-20 ml-20 mt-10">
            <table id="glProdMapping" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                     <th>映射产品类型</th>
                     <th>产品类型</th>
                     <th>映射名称</th>
                     <th>产品类型描述</th>
                    </tr>
                </thead>
            </table>
     </div>
</div>
</body>
</html>