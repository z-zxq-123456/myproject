<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
    <%@ include file="/form.jsp" %>
        <html>
            <style type="text/css">
                 td.details-control {
                    background: url("${ContextPath}/lib/datatables/1.10.7/images/details_open.png") no-repeat center center;
                    cursor: pointer;
                 }
                 tr.shown td.details-control {
                    background: url("${ContextPath}/lib/datatables/1.10.7/images/details_close.png") no-repeat center center;
                 }
             </style>
            <head>
                  <script type="text/javascript" src="${ContextPath}/app/pf/js/comProdAttrList1.js"></script>
                  <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
                  type="text/css" />
                  <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
                  type="text/css" />
            </head>
            <body class="pos-r">
	       <table>
	            </br>
                <tr><td>
                <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品代码：</label>
                <div class="formControls span4">
                    <input type="text" class="input-text size-MINI" value="" placeholder="prodType" id="prodType" name="prodType" disabled="true">
                </div></td>
				<td>
                <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品描述：</label>
                <div class="formControls span4">
                    <input type="text" placeholder="prodDesc"  value="" id="prodDesc" name="prodDesc" class="input-text size-MINI" disabled="true">
                </div></td>
             </tr>
    		<tr><td>
    			<label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品分类：</label>
    			<div class="formControls span5">
						<select class="select2" id="prodClass" name="prodClass" style="width:200px" disabled="true"></select>
    			</div></td>
				<td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品状态：</label>
				<div class="formControls span5">
						<select class="select2" id="status" name="status" style="width:200px" disabled="true">
							<option value="A">A-有效</option>
							<option value="F">F-无效</option>
						</select>
				</div></td>
             </tr>
	  </table>
       <br/>
       <table id="prodAttrList" class="table table-border table-bordered table-hover table-bg table-sort">
           <thead>
               <tr class="text-c">
                   <th></th>
                   <th>产品代码</th>
                   <th>类型</th>
                   <th>代码</th>
                   <th>描述值</th>
                   </tr>
           </thead>
       </table>
     </body>
 </html>