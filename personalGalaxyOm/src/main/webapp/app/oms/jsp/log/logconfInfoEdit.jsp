<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>日志配置修改</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/logconfInfoEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-logconfInfo-edit" name="fileForm">
        <div class="row" style="display:none;">
                    <label class="form-label span4"><span class="c-red">*</span>配置信息ID：</label>
                    <div class="formControls span4">
                        <input type="text" disabled="true" class="input-text grid" placeholder="配置信息ID" name="logconfId" id="logconfId"
                              >
                    </div>
                    <div class="span4"></div>
                </div>
        <div class="row">
                    <label class="form-label span4"><span class="c-red">*</span>ZK集群：</label>
                    <div class="formControls span6">
                        <select type="text" class="select2" placeholder="ZK集群" name="midwareZKId" id="midwareZKId"
                                ></select>
                    </div>
                    <div class="span2"></div>
                </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>日志平台模式：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="日志平台模式" name="logPlatMode" id="logPlatMode"
                      ></select>
            </div>
            <div class="span2"></div>
        </div>

       <div class="row">
                   <label class="form-label span4"><span class="c-red">*</span>日志收集级别：</label>
                   <div class="formControls span6">
                       <select type="text" class="select2" placeholder="日志输出模式" name="outPrtnMode" id="outPrtnMode"
                              ></select>
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
       <div class="row"   id = "kkMidwareIdDiv">
                 <label class="form-label span4"><span class="c-red">*</span>卡夫卡集群：</label>
                 <div class="formControls span6">
                     <select type="text" class="select2" placeholder="选择kafka集群" name="kkMidwareId" id="kkMidwareId"
                           ></select>
                 </div>
          <div class="span2"></div>
       </div>
        <div class="row">
                   <label class="form-label span4">扫描周期：</label>
                   <div class="formControls span6">
                       <input type="text" class="input-text grid" placeholder="请输入0-2位数字" name="scanPeriod" id="scanPeriod"
                             datatype="n0-2" >
                   </div>
                   <div class="span2"></div>
               </div>
        <div class="row"  id ="expiredTimeDiv">
               <label class="form-label span4">最大等待时间：</label>
               <div >
                   <input type="text"  id="expiredTime"  style = "width:265px" placeholder="请输入1-5位数字"  name="expiredTime"
                       datatype="n1-5" nullmsg="请输入！" tipmsg="格式错误!"><font color="red"> 秒</font>
               </div>
               <div class="span2"></div>
           </div>
           <div class="row"  id ="queueBatchSizeDiv">
            <label class="form-label span4">入库容量：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="请输入0-6位数字" name="queueBatchSize" id="queueBatchSize"
                   datatype="n0-6"   >
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit"  onclick="dataEdit()"  class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="dataEditCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>