<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
    <title>首页</title>
    <link href="${ContextPath}/css/app/galaxy.index.css" rel="stylesheet" type="text/css" />
    <link href="${ContextPath}/css/app/galaxy.css" rel="stylesheet" type="text/css" />
    <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body style="background: #d4d4d4; overflow:auto;">
<div class="pt-20">
    <div id="mainPanle" region="center" >
        <div class="all fastDisplay">
            <ul class="ul_1">
                <li id="u1_l1"><a _href="app/pm/jsp/paraIn/paraFlowService.jsp" href="javascript:void(0)" class="a1" >参数录入</a><a class="a2 flowColor">参数管理</a></li>
                <li id="u1_l2"><a _href="app/pm/jsp/paraIn/publishInfo.jsp" href="javascript:void(0)" class="a1" >参数发布</a><a class="a2 flowColor">参数管理</a></li>
                <li id="u1_l3"><a _href="app/bh/jsp/batchList.jsp" href="javascript:void(0)" class="a1">批处理配置</a><a class="a2 codeColor">批处理管理</a></li>
                <li id="u1_l4"><a _href="app/bh/jsp/batchRunList.jsp" href="javascript:void(0)" class="a1">批处理运行</a><a class="a2 codeColor">批处理管理</a></li>
            </ul>
            <ul class="ul_3">
                <li id="u3_l1"><a _href="app/pm/prod/jsp/MbAttrType.jsp" style='margin-left:45px' href="javascript:void(0) " class="a3">参数定义</a><a class="a4">产品工厂</a></li>
                <li id="u3_l2"><a _href="app/pm/prod/jsp/MbAttrValue.jsp" href="javascript:void(0)" style='margin-left:45px' class="a3">参数值定义</a><a class="a4">产品工厂</a></li>
                <li id="u3_l3"><a _href="app/pm/prod/jsp/MbProdDefine.jsp" href="javascript:void(0)" style='margin-left:45px' class="a3">单一产品定义</a><a class="a4">产品工厂</a></li>
             </ul>

        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function(){
        $.ajax({
            url:contextPath+"/TestResult/getTestResult",
            type : "post",
            dataType : "json",
            success:function(data) {
                var succeedTest = data.succeedTest;
                var failureTest = data.failureTest;
                var succeedCompound = data.succeedCompound;
                var failureCompound = data.failureCompound;
                var testNum = succeedTest + failureTest;
                var compoundNum = succeedCompound + failureCompound;
                $("#succeedTest").html(succeedTest);
                $("#failureTest").html(failureTest);
                $("#succeedCompound").html(succeedCompound);
                $("#failureCompound").html(failureCompound);
                if(testNum){
                    var test = succeedTest/(succeedTest+failureTest);
                }else{
                    var test = 0;
                }
                if(compoundNum){
                    var compound = 100*succeedCompound/(succeedCompound+failureCompound);
                }else{
                    var   compound = 0;
                }
                $("#testBar").attr("style","width:"+test+"%");
                $("#compoundBar").attr("style","width:"+compound+"%");
            }
        });
    });
    $("#test").on('click',function(){
        $("#test").attr("name","单实例测试");
    });
    $("#compound").on('click',function(){
        $("#compound").attr("name","回归测试案例");
    });
</script>