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
               <script type="text/javascript" src="${ContextPath}/app/pf/js/partAttrList1.js"></script>
               <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
               type="text/css" />
               <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
               type="text/css" />
            </head>
            <body class="pos-r">
	      <table></br>
			    <tr>
    		<tr><td>
    			<label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>指标分类：</label>
    			<div class="formControls span5">
						<select class="select2" id="partClass" name="partClass" style="width:200px" disabled="true"> </select>
    			</div></td>
				<td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>指标状态：</label>
				<div class="formControls span5">
						<select class="select2" id="status" name="status" style="width:200px" disabled="true">
							<option value="A">A-有效</option>
							<option value="F">F-无效</option>
						</select>
				</div></td>
             </tr>
			 <tr>
				<td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>使用方式：</label>
				<div class="formControls span5">
						<select class="select2" id="processMethod" name="processMethod" style="width:200px" disabled="true">
							<option value="" selected="selected">请选择</option>
							<option value="A">A-检查类</option>
							<option value="C">C-处理类</option>
						</select>
				</div></td>
			    <td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>是否标准指标：</label>
				<div class="formControls span5">
						<select class="select2" id="isStandard" name="isStandard" style="width:200px" disabled="true">
							<option value="N">N-否</option>
							<option value="Y">Y-是</option>
						</select>
				</div>
			</td>
			</tr>
	  </table>
	       <br/>
        <table id="partList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th></th>
                    <th >参数代码</th>
                    <th >参数描述</th>
                    </tr>
            </thead>
        </table>
  </body>
</html>