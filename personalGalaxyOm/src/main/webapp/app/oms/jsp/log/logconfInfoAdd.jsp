<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>日志配置信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/logconfInfoAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-logconfInfo-add" name="fileForm">
        <div class="row">
                    <label class="form-label span4"><span class="c-red">*</span>ZK集群：</label>
                    <div class="formControls span6">
                        <select type="text" class="select2" placeholder="ZK集群" name="midwareId" id="midwareZKId"
                        nullmsg="请选择ZK集群！"
                                ></select>
                    </div>
                    <div class="span2"></div>
                </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>日志平台模式：</label>
            <div class="formControls span6">
                <select type="text" class="select2"placeholder="日志平台模式" name="logPlatMode" id="logPlatMode"
                      nullmsg="请选择！"  ></select>
            </div>
            <div class="span2"></div>
        </div>

       <div class="row">
                   <label class="form-label span4"><span class="c-red">*</span>日志收集级别：</label>
                   <div class="formControls span6">
                       <select type="text" class="select2" placeholder="日志输出模式" name="outPrtnMode" id="outPrtnMode"
                           nullmsg="请选择！"   ></select>
                   </div>
                   <div class="span2"></div>
               </div>
        <div class="row"  >
                   <label class="form-label span4"><span class="c-red">*</span>卡夫卡集群：</label>
                   <div class="formControls span6">
                       <select type="text" class="select2" placeholder="选择kafka集群" name="kkMidwareId" id="kkMidwareId"
                           nullmsg="请选择！"   ></select>
               </div>
               <div class="span2"></div>
           </div>
        <div class="row">
                                  <label class="form-label span4"><span class="c-red">*</span>是否扫描：</label>
                                  <div class="formControls span6">
                                      <select type="text" class="select2" placeholder="是否扫描" name="isScan" id="isScan"
                                            ></select>
                           </div>
                           <div class="span2"></div>
                       </div>
        <div class="row">
                   <label class="form-label span4">扫描周期：</label>
                   <div class="formControls span6">
                       <input type="text" class="input-text grid"  placeholder="请输入0~2位数字"name="scanPeriod" id="scanPeriod"
                            datatype="n0-2">
                   </div>
                    <div class="span2"></div>
               </div>
        <div class="row" >
                   <label class="form-label span4">最大等待时间：</label>
                   <div >
                       <input type="text"  id="expiredTime"  style = "width:265px" placeholder="请输入1-5位数字"  name="expiredTime"
                            datatype="n1-5" nullmsg="请输入！" tipmsg="格式错误!"><font color="red"> 秒</font>
                   </div>
                    <div class="span2"></div>
               </div>
        <div class="row" >
            <label class="form-label span4">入库批量：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="请输入0~6位数字" name="queueBatchSize" id="queueBatchSize"
                        datatype="n0-6" >
            </div>
             <div class="span2"></div>
        </div>
        <div class="row" >
            <div class="span4"></div>
             <a onclick="logconfInfoAdd()" class="button-select M" title="提交">提交</a>
             <a onclick="logconfInfoAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
<script>

</script>