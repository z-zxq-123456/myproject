<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%

    String userName = (String)request.getSession().getAttribute("UserName");
    if (null == userName) {
        response.sendRedirect(request.getSession().getAttribute("ContextPath") + "/login.jsp");
    }
%>
<link href="${ContextPath}/css/base/galaxy.base.css" rel="stylesheet" type="text/css" />
<link href="${ContextPath}/lib/galaxy-iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ContextPath}/css/app/galaxy.admin.css" rel="stylesheet" type="text/css" />
<link href="${ContextPath}/css/app/galaxy.skin.css" rel="stylesheet" type="text/css" />
<link href="${ContextPath}/lib/select2-4.0.2/dist/css/select2.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]>
<script type="text/javascript" src="${ContextPath}/lib/html5.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/respond.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="${ContextPath}/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->