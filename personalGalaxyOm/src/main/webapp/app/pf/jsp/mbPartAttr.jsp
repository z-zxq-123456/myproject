<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
    <%@ include file="/form.jsp" %>
        <html>
            <head>
                <link href="${ContextPath}/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
                <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
                type="text/css" />
                <link href="${ContextPath}/lib/bootstrap/css/bootstrap-responsive.min.css"
                rel="stylesheet" type="text/css" />
                <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
                type="text/css" />
                <link href="${ContextPath}/lib/bootstrap/css/style-responsive.css" rel="stylesheet"
                type="text/css" />
                <script type="text/javascript" src="${ContextPath}/app/pf/js/mbPartAttr.js"></script>
                <script type="text/javascript" src="${ContextPath}/lib/bootstrap/js/jquery.slimscroll.min.js">
                </script>
                <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js">
                </script>
                <script type="text/javascript" src="${ContextPath}/js/pf/prodFactory.js">
                </script>
                <link href="${ContextPath}/lib/jquery/jquery-ui.min.css" rel="stylesheet" type="text/css" />
                <link href="${ContextPath}/lib/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css" />
                <link href="${ContextPath}/css/style.css" rel="stylesheet" type="text/css"/>
                <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
                <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css" />
                <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
                <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
                <script type="text/javascript" src="${ContextPath}/lib/zTree/json2.js" charset="utf-8"></script>
                <script type="text/javascript" src="${ContextPath}/lib/zTree/util.js" charset="utf-8"></script>
            </head>
            <body>
                <div class="mr-10 ml-10">
                  <nav class="breadcrumb" style="height:40px;"><i class="iconfont">&#xe61d;</i>系统首页<span class="c-gray en">&gt;</span>产品工厂<span class="c-gray en">&gt;</span>指标定义<a class="btn radius r mr-20 size-MINI" href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
                </div>
                <div class="row mt-20" style="margin-left:3%;">
                     <label class="form-label mt-5 ml-20 mr-5"><span class="c-red">*</span>操作类型：</label>
                     <select id="operateType" name="operateType" class="select2" style="width:12%;">
                      <option value="" selected="selected">请选择</option>
                      <option value="update">U--修改</option>
                      <option value="add">A--新增</option>
                      <option value="copy">C--复制</option>
                      </select>
                    <span style="margin-left:3%;font-size:12px;">指标分类：</span>
                    <select id="Bmodule" name="Bmodule" disabled="true"  style="width:12%;height:28px;">
                     <option value="" selected="selected">请选择</option>
                     </select>
                     <span  style="font-size:12px;margin-left:4%;">指标：</span>
                     <input type="text" class="input-text" value="" placeholder="指标类型" id="partType"
                     name="partType" style="width:12%;">
                     <input type="text" class="input-text " placeholder="指标描述" value="" id="partDesc"
                     name="partDesc" style="width:12%;">
                    <a id="save" class="button-edit  M" style="margin:0px 1%;">
                        <i class="iconfont icon-writefill">
                        </i>
                        保存
                    </a>
                    <a id="query"  style="margin-bottom:10px;display:none">
                                                 </a>
                </div>
                 <div class="row mt-20" id="newPartDiv" style="font-size:12px;" >
                                    <span class="span3"></span>
                                    新指标类型：
                                   <input type="text" class="input-text" value="" placeholder="新指标类型" id="newPartType" name="newPartType" datatype="/^[A-Z\(_)]+$/" nullmsg="新指标类型不能为空" disabled="true" style="width:12%">
                                    <span style="margin-left:0.5%;font-size:12px;">新指标描述：</span>
                                   <input type="text" placeholder="新指标描述"  value="" id="newPartDesc" name="newPartDesc" class="input-text" datatype="*1-40" nullmsg="新指标类型描述不能为空" disabled="true"  style="width:12%">
                                </div>
                <div class="row mt-20" style="font-size:12px;">
                   <div class="span3"></div>
                     <span style="margin-left:1%;font-size:12px;">使用方式：</span>
                    <select class="select2" id="processMethod" name="processMethod" data-placeholder="使用方式" style="width:12%"  disabled="true">
                        <option value="" selected="selected">请选择</option>
                        <option value="A">A-检查类</option>
                        <option value="C">C-处理类</option>
                    </select>
                    <span style="margin-left:1.5%;font-size:12px;">指标状态：</span>
                    <select id="status" class="select2" name="status" data-placeholder="指标状态" style="width:12%"  disabled="true">
                        <option value="A">有效</option>
                        <option value="F">无效</option>
                    </select>
                     <span style="margin-left:1.5%;font-size:12px;">所属分类：</span>
                        <select class="select2"  name="busiCategory" id ="busiCategory" multiple="multiple" data-placeholder="所属分类" style="width:12%" disabled="true">
                        </select>
                </div>
                <div class="row cl span12">
                       <div class="mt-10">
                         <br/>
                        <p class="line mb-10" style="margin-left:60px;"></p>
                        <div class="text-c" style="margin-left:30px;">
                            <div class="tabCon cl" style="border:solid 3px #e5e5e5">
                                <div class="span3">
                                    <div class="padding-20">
                                        <div class="portlet">
                                             <ul id="attrTree" class="ztree showIcon">
                                             </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="span9">
                                     <div class="padding-20" style="width:100%">
                                        <div class="portlet" id="sortable_portlets">
                                            <div class="portlet-body " style="margin-top:10px;fond-size:5px;margin-left:30px;">
                                                <div class="row" id="compontent">
                                                </div>
                                            </div>
                                        </div>
                                     </div>
                                </div>
                            </div>
                        </div>
                       </div>
            </body>
        </html>