<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*" %>
<%
    FileSaver fs = (FileSaver) (request.getAttribute("FileSaver"));
    fs.close();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>SaveFile</title>
</head>

<body>
<h2><font color=red>${message}</font></h2>
</body>
</html>
