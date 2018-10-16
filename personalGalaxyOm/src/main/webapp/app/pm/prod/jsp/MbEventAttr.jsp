<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
    <%@ include file="/form.jsp" %>
        <html>
            <head>
                    <link href="${ContextPath}/css/style.css" rel="stylesheet"type="text/css" />
                    <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
                    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css" />
                    <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js"></script>
                    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbEventAttr.js"></script>
                    <script src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
                    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css" />
                    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
                     <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
                    <script type="text/javascript" src="${ContextPath}/js/pf/prodFactory.js"></script>
            </head>
            <body>
            <div class="mr-10 ml-10">
              <nav class="breadcrumb" style="height:40px;"><i class="iconfont">&#xe61d;</i>系统首页<span class="c-gray en">&gt;</span>产品工厂<span class="c-gray en">&gt;</span>事件定义<a class="btn radius r mr-20 size-MINI" href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
            </div>
            <div class="step-body span-offset-1 span10 span-setoff-1 mt-20" id="myStep" >
                		<div class="step-header" >
                			<ul>
                				<li><p>基础信息</p></li>
                				<li><p>参数定义</p></li>
                				<li><p>指标定义</p></li>
                			</ul>
                		</div>
                		<div class="row" style="margin-top:60px;font-size:12px;">
                		   <label class="form-label" style="margin-top:3px;"><span class="c-red">*</span>操作类型：</label>
                		   <div class="formControls span2 mr-10">
                		   <select id="operateType" name="operateType" class="select2">
                            <option value="" selected="selected">请选择</option>
                            <option value="update">U--修改</option>
                            <option value="add">A--新增</option>
                            <option value="copy">C--复制</option>
                            </select>
                            </div>
                            <label class="form-label mt-5 ml-10">产品类别：</label>
                            <div class="formControls span2 mr-10">
                            <select id="Bmodule" name="Bmodule" disabled="true" style="height:28px;">
                             <option value="" selected="selected">请选择</option>
                             </select>
                             </div>
                            <label class="form-label ml-10" style="margin-top:7px;"> 产品类型：</label>
                             <div class="formControls span2">
                             <input type="text" class="input-text grid" value="" placeholder="产品类型" id="prodType" name="prodType" >
                             </div>
                             <label class="form-label mt-5"> 事件：</label>
                            <input type="text" class="input-text" value="" placeholder="事件类型" id="eventType"
                            name="eventType" style="width:12%;">
                            <input type="text" class="input-text" placeholder="事件描述" id="eventDesc"
                            name="eventDesc" style="width:12%;">
                		</div>
                		<div class="row mt-20">
                		       <div class="span8"></div>

                               <a id="save" class="button-edit M ml-20 mr-20">
                                    <i class="iconfont icon-writefill">
                                    </i>
                                    保存
                                </a>
                                <a id="attrList" class="button-edit M ">
                                    <i class="iconfont icon-writefill">
                                    </i>
                                    查看数据
                                </a>
                                <a id="nullifyAppNo" class="button-edit  M >
                                   <i class="iconfont icon-writefill">
                                                                    </i>
                                      操作作废
                                </a>
                               <a id="query"  style="margin-bottom:10px;display:none">
                               </a>
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                				<div class="text-c">
                				    <a id="ome" class="button-select">下一步</a>
                				    <div style="border:solid 3px #e5e5e5;height:300px;margin-top:20px;">
                				    <div class="row cl mt-20 " id="newEventDiv" style="text-align:right">
                                      <label class="form-label mt-5" style="width:10%">新事件类型：</label>
                                      <div class="formControls span2">
                                           <input type="text" class="input-text" value="" placeholder="新事件类型" id="newEventType" name="newEventType" datatype="/^[A-Z0-9\(_)]+$/" nullmsg="新事件类型不能为空" disabled="true" style="width:100%" >
                                      </div>
                                      <label class="form-label mt-5" style="width:10%">新产品描述：</label>
                                      <div class="formControls span2">
                                           <input type="text" placeholder="新事件类型描述"  value="" id="newEventDesc" name="newEventDesc" class="input-text" datatype="*1-40" nullmsg="新事件类型描述不能为空" disabled="true"  style="width:100%" >
                                      </div>
                                      <div class="span3"></div>
                                    </div>
                                     <div class="row cl mt-20 " >
                                       <label class="form-label mt-5" style="width:10%;text-align:right;">事件分类：</label>
                                       <div class="formControls span2">
                                       <select id="eventClass" name="eventClass" data-placeholder="事件分类"  datatype="*1-40" nullmsg="事件分类不能为空" disabled="true" class="select2" style="width:100%;">
                                           <option value="" selected="selected">请选择</option>
                                       </select>
                                       </div>
                                        <label class="form-label mt-5" style="width:10%;text-align:right;">使用方式：</label>
                                        <div class="formControls span2">
                                       <select id="processMethod" name="processMethod" data-placeholder="使用方式"  datatype="*1-40" nullmsg="使用方式不能为空" disabled="true" class="select2" style="width:100%;">
                                           <option value="" selected="selected">请选择</option>
                                           <option value="A">A-检查类</option>
                                           <option value="C">C-处理类</option>
                                       </select>
                                       </div>
                                       <label class="form-label mt-5" style="width:10%;text-align:right;">事件状态：</label>
                                       <div class="formControls span2">
                                       <select id="status" name="status" data-placeholder="指标状态" datatype="*1-40" nullmsg="指标状态不能为空" disabled="true"  class="select2" style="width:100%;">
                                           <option value="A">有效</option>
                                           <option value="F">无效</option>
                                       </select>
                                       </div>
                                    </div>
                                    </div>
                				</div>
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                				<div class="text-c"><!--2-->
                				    <a id="preOne" class="button-select">上一步</a>
                                    <a id="two" class="button-select" >下一步</a>
                					<div class="tabCon mt-20" style="border:solid 3px #e5e5e5">
                                         <div  style="width:100%;" style="margin-top:15px">
                                           <div class="tabbable-custom ">
                                               <ul class="nav nav-tabs " id ="list">
                                               </ul>
                                               <div class="tab-content" id ="tabDiv">
                                               </div>
                                           </div>
                                        </div>
                                    </div>
                				</div><!--2-->
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                			<div class="text-c"><!--3-->
                			     <a id="preTwo" class="button-select">上一步</a>
                				 <div class="tabCon cl mt-20" style="border:solid 3px #e5e5e5">
                                    <div class="span3">
                                        <div class="padding-20">
                                            <div class="portlet">
                                                 <ul id="partTree" class="ztree showIcon">
                                                 </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span9">
                                         <div class="padding-20" style="width:100%">
                                            <div class="portlet" id="sortable_portlets">
                                                <div class="portlet-body " style="margin-top:10px;fond-size:5px">
                                                    <div class="row" id="compontentP">
                                                    </div>
                                                </div>
                                            </div>
                                         </div>
                                    </div>
                                </div>
                			</div><!--3-->
                		</div>
                	</div>
</body>
</html>