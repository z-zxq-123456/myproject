<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>Redis缓存主键维护</title>
    <link type="text/css" rel="stylesheet" href="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.css"/>
     <script type="text/javascript" src="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.js"></script>
     <script type="text/javascript" src="${ContextPath}/app/oms/js/middleware/seqManager.js"></script>
</head>
<body class="easyui-layout">
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i> 系统首页 <span>&gt;</span>中间件管理</a><span>&gt;</span>Redis</a><span>&gt;</span><span> Redis监控</span><a   href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
      </div>
<div class="padding-10">
    <div class="row cl pd-5 bg-1 bk-gray mt-10 mb-10">
        <label class="form-label span2"><span class="c-red">*</span>redis集群名称：</label>
         <div class="span3">
              <select name="queryMidwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
              </select>
         </div>
    </div>
    <div  class="row cl pd-5 bg-1 bk-gray mt-10 mb-10">
            <label class="form-label span2"><span class="c-red">*</span>redis哨兵池：</label>
            <div class="formControls span7">
                <input type="text" class="input-text" style="width:100%;" value="" placeholder="选择Redis地址或者输入Redis地址" id="redisUrl" name="redisUrl" placeholder="host IP:host port" datatype="*6-600" nullmsg="Redis环境URL必须输入">
            </div>
    </div>
    <div class="row cl pd-5 bg-1 bk-gray mt-10 mb-10">
            <label class="form-label span2"><span class="c-red">*</span>主节点：</label>
            <div class="formControls span4">
                        <select class="select2" value="" placeholder="选择Redis地址或者输入Redis服务器的名称" id="masterName" name="masterName" placeholder="Master Name" datatype="*2-100" nullmsg="Redis服务器的名称必须输入"></select>
             </div>
     </div>
    <div id="tab-system" class="tabbable-custom content-no-border">
        <ul class="nav nav-tabs"  >
            <li class="active"><a href="#tab_1_1"  data-toggle="tab" >主键维护</a></li>
            <li><a href="#tab_1_3"  data-toggle="tab">缓存清理</a></li>
        </ul>
        <div class="tab-content">
            <div  class="tab-pane active" id="tab_1_1">
                <div class="tabCon form form-horizontal">
                  <div class="mt-20">
                       <table id="redisKeyList" class="table table-border table-bordered table-hover table-bg table-sort">
                           <thead>
                            <tr class="text-c">
                             <th>主键名称</th>
                             <th>主键值</th>
                            </tr>
                           </thead>
                       </table>
                   </div>
                </div>
            </div>
            <div  class="tab-pane" id="tab_1_2">
                <div class="tabCon form form-horizontal">
                      <div class="row">
                            <label class="form-label span3 "></span>选择缓存数据类型：</label>
                            <div class="formControls span3">
                                    <select class="select2"  id="cacheType" name="cacheType"  style="width:200px;"></select>
                            </div>
                            <label class="form-label span2 "></span>键数据：</label>
                            <div class="formControls span3">
                                <input type="text" class="input-text size-MINI" value="" placeholder="输入键数据" id="cacheKey" name="cacheKey" placeholder="host IP:host port" datatype="*6-600" nullmsg="Redis环境URL必须输入">
                            </div>
                      </div>
                      <div class="mt-20">
                          <table id="cacheDataList" class="table table-border table-bordered table-hover table-bg table-sort">
                            <thead>
                                <tr class="text-c">
                                    <th  style="width:100px;">缓存数据类型</th>
                                    <th>键数据</th>
                                    <th>值数据</th>
                                </tr>
                            </thead>
                          </table>
                      </div>
                </div>
            </div>
            <div  class="tab-pane" id="tab_1_3">
                 <div class="tabCon form form-horizontal" id="emptyCatch">
                    <div class="cl pd-5 bg-1 bk-gray">
                        <span class="l">
                            <a  onclick="findRedisCatch()" class="button-select S btnNoRadius"><i class="iconfont">&#xe624;</i> 查询</a>
                            <a  onclick="clearServerCatch()" class="button-del S btnNoRadius"><i class="iconfont">&#xe61c;</i> 清空</a>
                        </span>
                    </div>
                    <div class="row cl mb-10">
                         <label class="form-label col-3 "></span>缓存数量：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                       <div class="formControls col-5">
                             <input type="text" class="input-text size-MINI" value=""  id="keyNum" name="keyNum"  disabled=true>
                       </div>
                    </div>
                     <div class="row cl mb-10">
                         <label class="form-label col-3 "></span>最大使用内存：&nbsp</label>
                       <div class="formControls col-5">
                             <input type="text" class="input-text size-MINI" value=""  id="allMemory" name="allMemory"  disabled=true>
                       </div>
                    </div>
                    <div class="row cl mb-10">
                        <label class="form-label col-3 "></span>已使用内存：&nbsp&nbsp&nbsp&nbsp</label>
                        <div class="formControls col-5">
                          <input type="text" class="input-text size-MINI" value=""  id="useMemory" name="useMemory" disabled=true>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
  