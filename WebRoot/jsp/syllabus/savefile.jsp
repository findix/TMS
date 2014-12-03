<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*" %>
<%
    FileSaver fs = (FileSaver) (request.getAttribute("FileSaver"));
    fs.close();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>保存</title>
</head>

<body>
<div align="center">
    <h2>${message}</h2>
</div>
</body>
</html>
