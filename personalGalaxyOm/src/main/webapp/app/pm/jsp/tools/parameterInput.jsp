<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<link href="${ContextPath}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="${ContextPath}/css/upload/upload.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ContextPath}/lib/webuploader/0.1.5/webuploader.js"></script>
<html>
<head>
	<!--<title>导入导出服务</title>-->
	<title>导入服务</title>
	<script type="text/javascript" src="${ContextPath}/app/pm/js/tools/parameterInput.js"></script>
    </head>
    <body>
    <div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i> 系统首页 <span>&gt;</span><a href="#">接口定义</a> <span>&gt;</span> <span>导入服务</span><a   href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
    </div>

        <form action="" method="post" class="form form-horizontal" id="form-uploadfile-add">
          <div id="wrapper" style="display:none;">
                         <div id="container">
                             <div id="uploader">
                                 <div class="queueList">
                                     <div id="dndArea" class="placeholder" style="width:100%">
                                         <div id="filePicker"></div>
                                         <p>您可以尝试文件拖拽，然后激活窗口后粘贴，或者点击添加按钮</p>
                                     </div>
                                 </div>
                                 <div class="statusBar" style="display: none;">
                                     <div class="progress">
                                         <span class="text">0%</span> <span class="percentage"></span>
                                     </div>
                                     <div class="info"></div>
                                     <div class="btns">
                                         <div id="filePicker2"  size-MINI></div>
                                         <div class="uploadBtn"  size-MINI>开始导入</div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
            </form>
        </div>
</body>
</html>