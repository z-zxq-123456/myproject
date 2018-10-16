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
                 <script type="text/javascript" src="${ContextPath}/app/pf/js/eventAttrList1.js"></script>
                  <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
                  type="text/css" />
                  <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
                  type="text/css" />
            </head>
            <body class="pos-r">
	       <table></br>
			    <tr>
                <tr style="display:none"><td></td>
                <td>
                <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>所属业务模块：</label>
                <div class="formControls span5">
                    <select id="Bmodule" name="Bmodule" data-placeholder="所属业务模块" class="select2" style="width:200px" disabled="true">
                       <option value="" selected="selected">请选择</option>
                    </select>
                 </div></td>
             </tr>
                <tr><td>
                <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>事件代码：</label>
                <div class="formControls span4">
                    <input type="text" class="input-text size-MINI" value="" placeholder="eventType" id="eventType" name="eventType" disabled="true">
                </div></td>
				<td>
                <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>事件描述：</label>
                <div class="formControls span4">
                    <input type="text" placeholder="eventDesc"  value="" id="eventDesc" name="eventDesc" class="input-text size-MINI" disabled="true">
                </div></td>
             </tr>
    		<tr><td>
    			<label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>事件分类：</label>
    			<div class="formControls span5">
						<select class="select2" id="eventClass" name="eventClass" style="width:200px" disabled="true"></select>
    			</div></td>
				<td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>事件状态：</label>
				<div class="formControls span5">
						<select class="select2" id="status" name="status" style="width:200px" disabled="true">
							<option value="A">A-有效</option>
							<option value="F">F-无效</option>
						</select>
				</div></td>
             </tr>
	  </table>
	       <br/>
        <table id="eventAttrList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th></th>
                    <th>事件代码</th>
                    <th>类型</th>
                    <th>代码</th>
                    <th>描述值</th>
                    </tr>
            </thead>
        </table>
 </body>
</html>