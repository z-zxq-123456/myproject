<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
    <%@ include file="/form.jsp" %>
        <html>
            <head>
                    <link href="${ContextPath}/css/style.css" rel="stylesheet"type="text/css" />
                    <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
                    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css" />
                    <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js"></script>
                    <script type="text/javascript" src="${ContextPath}/app/pf/js/mbCombProdDefine.js"></script>
                    <script src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
                    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css" />
                     <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
                    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
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
                				<li><p>业务描述</p></li>
                				<li><p>行为描述</p></li>
                				<li><p>关系定义</p></li>
                			</ul>
                		</div>
                		<div class="row" style="margin-top:60px;">
                		   <label class="form-label mt-5"><span class="c-red">*</span>操作类型：</label>
                		   <div class="formControls span2  ml-5">
                		   <select id="operateType" name="operateType" class="select2">
                            <option value="" selected="selected">请选择</option>
                            <option value="update">U--修改</option>
                            <option value="add">A--新增</option>
                            <option value="copy">C--复制</option>
                            </select>
                            </div>
                            <label class="form-label mt-5">产品类别：</label>
                            <div class="formControls span2  ml-5">
                            <select id="Bmodule" name="Bmodule" disabled="true" style="height:28px;">
                             <option value="" selected="selected">请选择</option>
                             </select>
                             </div>
                            <label class="form-label mt-5"> 产品:</label>
                             <input type="text" class="input-text ml-5" value="" placeholder="产品类型" id="prodType" name="prodType" style="width:12%;" >
                             <input type="text" class="input-text ml-5" value="" placeholder="产品描述" id="prodDesc"
                             name="prodDesc" style="width:12%;">
                             <a id="save" class="button-edit M ml-20">
                                  <i class="iconfont icon-writefill">
                                  </i>
                                  保存
                              </a>
                             <a id="query"  style="margin-bottom:10px;display:none">
                             </a>
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                				<div class="text-c">
                				    <a id="ome" class="button-select">下一步</a>
                				    <div class="mt-20" style="border:solid 3px #e5e5e5;height:300px;">
                				    <div class="row cl mt-20 " id="newProdDiv" style="text-align:right">
                                     <label class="form-label mt-5" style="width:10%">新产品类型：</label>
                                     <div class="formControls span2">
                                          <input type="text" class="input-text" value="" placeholder="新产品类型" id="newProdType" name="newProdType" datatype="/^[A-Z0-9\(_)]+$/" nullmsg="新产品类型不能为空" disabled="true" style="width:100%" >
                                     </div>
                                     <label class="form-label mt-5" style="width:10%">新产品描述：</label>
                                     <div class="formControls span2">
                                          <input type="text" placeholder="新产品类型描述"  value="" id="newProdDesc" name="newProdDesc" class="input-text" datatype="*1-40" nullmsg="新产品类型描述不能为空" disabled="true"  style="width:100%" >
                                     </div>
                                     <div class="span3"></div>
                                   </div>
                                    <div class="row cl mt-20 " >
                                      <label class="form-label mt-5" style="width:10%;text-align:right;">产品分类：</label>
                                      <div class="formControls span2">
                                      <select id="prodClass" name="prodClass" data-placeholder="产品分类"  datatype="*1-40" nullmsg="产品分类不能为空" disabled="true" class="select2" style="width:100%;">
                                          <option value="" selected="selected">请选择</option>
                                      </select>
                                      </div>
                                       <label class="form-label mt-5" style="width:10%;text-align:right;">产品属性：</label>
                                       <div class="formControls span2">
                                      <select id="prodRange" name="prodRange" data-placeholder="产品属性"  datatype="*1-40" nullmsg="产品属性不能为空" disabled="true" class="select2" style="width:100%;">
                                          <option value="" selected="selected">请选择</option>
                                          <option value="B">B-基础产品</option>
                                          <option value="S">S-可售产品</option>
                                      </select>
                                      </div>
                                   </div>
                                   <div class="row cl mt-20 " >
                                      <label class="form-label mt-5" style="width:10%;text-align:right;">组合产品：</label>
                                      <div class="formControls span2">
                                      <select id="prodGroup" name="prodGroup" data-placeholder="组合产品"  datatype="*1-40" nullmsg="组合产品不能为空" disabled="true" class="select2" style="width:100%;">
                                           <option value="Y" selected="selected">Y-是</option>
                                            <option value="N" >N-否</option>
                                      </select>
                                      </div>
                                       <label class="form-label mt-5" style="width:10%;text-align:right;">基础产品：</label>
                                       <div class="formControls span2">
                                      <select id="baseProdType" name="baseProdType" data-placeholder="基础产品"  datatype="*1-40" nullmsg="基础产品不能为空" disabled="true" class="select2" style="width:100%;">
                                          <option value="" selected="selected">请选择</option>
                                      </select>
                                      </div>
                                       <label class="form-label mt-5" style="width:10%;text-align:right;">产品状态：</label>
                                      <div class="formControls span2">
                                     <select id="status" name="status" data-placeholder="产品状态"  datatype="*1-40" nullmsg="产品状态不能为空" disabled="true" class="select2" style="width:100%;">
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
                					<div class="tabCon mt-20" style="border:solid 3px #e5e5e5;">
                                          <div class="tabbable-custom ">
                                              <ul class="nav nav-tabs " id ="list">
                                              </ul>
                                              <div class="tab-content" id ="tabDiv">
                                              </div>
                                          </div>
                                    </div>
                				</div><!--2-->
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                			<div class="text-c"><!--3-->
                			     <a id="preTwo" class="button-select">上一步</a>
                			     <a id="three" class="button-select">下一步</a>
                				 <div class="tabCon cl mt-20" style="border:solid 3px #e5e5e5;">
                                    <div class="span3">
                                        <div class="padding-20">
                                            <div class="portlet">
                                                <ul id="eventTree" class="ztree showIcon">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span9">
                                        <div class="padding-20" style="width:100%">
                                            <div class="portlet" id="sortable_portlets">
                                                <div class="portlet-body " style="margin-top:10px">
                                                    <div class=" row " id="compontentE">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                			</div><!--3-->
                		</div>
                		<div class="step-list" style="margin-top:30px;">
                            <div class="text-c"><!--4-->
                                 <a id="preThree" class="button-select">上一步</a>
                                 <div class="mt-20" style="border:solid 3px #e5e5e5;">
                                 <div class="row mt-20">
                                    <label class="form-label mt-5 ml-5">产品关系：</label>
                                    <div class="formControls span2 ml-5">
                                    <select id="prodClassR" name="prodClassR" data-placeholder="产品关系"  datatype="*1-40" nullmsg="产品关系不能为空" class="select2" style="width:100%;">
                                       <option value="" selected="selected">请选择</option>
                                       <option value="0">0-指标规则</option>
                                       <option value="1">1-事件规则</option>
                                    </select>
                                    </div>
                                    <label class="form-label mt-5 ml-10">子产品：</label>
                                    <div class="formControls span2 ml-5">
                                        <select id="prodC" name="prodC" data-placeholder="子产品类型"  datatype="*1-40" nullmsg="子产品类型" disabled ="true" class="select2" style="width:100%;">
                                           <option value="" selected="selected">请选择</option>
                                        </select>
                                    </div>
                                    <button id="listAdd" href="#" class="button-add M" style="margin-bottom:10px; width:50px">
                                      <i class="iconfont">&#xe60e;
                                      </i>
                                    </button>
                                    </div>
                                    <div class="m2-10" style="margin:0px 8.5%" id="divChildProdList">
                                       <table id="childProdList" class="table table-border table-bordered table-hover table-bg table-sort">
                                          <thead><tr class="text-c"><th>子产品代码</th><th>子产品描述</th><th>是否基础产品</th><th>操作</th></tr>
                                          </thead>
                                       </table>
                                    </div>
                                   ---------------------------------------------------------------------------------------------------------------------------------------------------
                                  <div class=" mt-20" style="font-size:12px;text-align:left;" id="comb">
                                         <div style="margin-left:3%">事件关系定义</div>
                                               </br></br>
                                      <div class="row cl">
                                      <div class="span2"></div>
                                      <label class="form-label mt-5">联动产品A：</label>
                                      <div class="formControls span2">
                                      <select id="prodA" name="prodA" data-placeholder="联动产品A类型"  datatype="*0-40" nullmsg="联动产品A类型"  style="width:100%;">
                                         <option value="">请选择</option>
                                      </select>
                                      </div>
                                       <label class="form-label mt-5">A产品事件：</label>
                                        <div class="formControls span2">
                                        <select id="eventA" name="eventA" data-placeholder="联动产品A" datatype="*1-40" nullmsg="A产品事件"  style="width:100%;">
                                           <option value="">请选择</option>
                                        </select>
                                        </div>
                                        <label class="form-label mt-5">规则：</label>
                                        <div class="formControls span2">
                                        <select id="rule" name="rule" data-placeholder="规则"datatype="*1-40" nullmsg="规则"  style="width:100%;">
                                           <option value="">请选择</option>
                                        </select>
                                        </div>
                                      </div>
                                       <div class="row cl mt-20">
                                        <div class="span2"></div>
                                         <label class="form-label mt-5">联动产品B：</label>
                                        <div class="formControls span2">
                                        <select id="prodB" name="prodB" data-placeholder="联动产品B类型"  datatype="*1-40" nullmsg="联动产品B类型"  style="width:100%;">
                                            <option value="">请选择</option>
                                        </select>
                                        </div>
                                        <label class="form-label mt-5">B产品事件：</label>
                                        <div class="formControls span2">
                                          <select id="eventB" name="eventB" data-placeholder="B产品事件"  datatype="*1-40" nullmsg="B产品事件"  style="width:100%;" >
                                             <option value="">请选择</option>
                                          </select>
                                        </div>
                                      <button id="prodMAdd" href="#" class="button-add M" style="margin-bottom:10px; width:50px">
                                        <i class="iconfont">&#xe60e;
                                        </i>
                                      </button>
                                      </div>
                                      <div class="mt-20" style="margin:0px 8.5%">
                                          <div class="tabbable-custom " id="divProdTableM">
                                              <table id="prodTableM" class="table table-border table-bordered table-hover table-bg table-sort">
                                              <thead>
                                              <tr class="text-c"><th>联动产品A</th><th>产品A事件</th><th>规则</th><th>联动产品B</th><th>产品B事件</th><th>操作</th></tr>
                                              </thead>
                                              </table>
                                          </div>
                                      </div>
                                   </div>
                                  <div class="mt-20" style="font-size:12px;text-align:left;" id="master">
                                      <div style="margin-left:3%">指标关系定义</div>
                                      </br></br>
                                        <div class="mt-20" style="margin:0px 8.5%">
                                            <div class="tabbable-custom ">
                                             <div class="tab-pane active" id="divCombProdList">
                                              <table id="combProdList" class="table table-border table-bordered table-hover table-bg table-sort">
                                               <thead><tr class="text-c"><th>事件类型</th><th>指标类型</th><th>指标代码</th><th>主子产品标识</th></tr>
                                               </thead>
                                              </table>
                                              </div>
                                            </div>
                                        </div>
                                        </div>
                                         <input type="text" class="input-query" value="" placeholder="产品类型" id="prodTypeCom" name="prodTypeCom"
                                                                            style="font-size:12px; display:none;">
                                   </div>
                            </div><!--4-->
                        </div>
                	</div>
</body>
</html>