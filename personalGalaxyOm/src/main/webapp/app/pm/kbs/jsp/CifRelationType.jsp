<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>CIF_RELATION_TYPE交易</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/CifRelationType.js"></script>
</head>
<body>
<div class="mb-10">
	<nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                        <label class="form-label">类型：</label>
                        <div class="formControls  span2">
                                        <input type="text"  value="" class="input-text grid" placeholder="relationType" id="RELATION_TYPE" name="relationType"  >
                        </div>
                        <label class="form-label">联合帐户：</label>
                        <div class="formControls  span2">
                                    <select id="JOINT_ACCT"  class="select2" name="jointAcct" size="1"  style="width:100%">
                                        <option value="" selected="selected">空</option>
                                        <option value="Y" >是</option>
                                        <option value="N" >否</option>
                                    </select>
                        </div>
                        <label class="form-label">风险：</label>
                        <div class="formControls  span2">
                                    <select id="EXPOSURE"  class="select2" name="exposure" size="1"  style="width:100%">
                                        <option value="" selected="selected">空</option>
                                        <option value="Y" >是</option>
                                        <option value="N" >否</option>
                                    </select>
                        </div>
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="cifRelationType" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                        <th>类型</th>
                        <th>授权方</th>
                        <th>联合帐户</th>
                        <th>亲戚</th>
                        <th>类型描述</th>
                        <th>雇佣</th>
                        <th>股权</th>
                        <th>风险</th>
                        <th>对等</th>
                        <th>联合体</th>
                        <th>关系类型标识</th>
                        <th>法人代码</th>
                        <th>反相关系类型</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
