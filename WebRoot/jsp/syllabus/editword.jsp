<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'editword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <!--**************   ׿�� PageOffice �ͻ��˴��뿪ʼ    ************************-->
	<script language="javascript" type="text/javascript">
	    function SaveDocument() {
	        document.getElementById("PageOfficeCtrl1").WebSave();
	        //alert(document.getElementById("PageOfficeCtrl1").CustomSaveResult);
	    }
	    function ShowPrintDlg() {
	        document.getElementById("PageOfficeCtrl1").ShowDialog(4);
	    }
	    function SetFullScreen() {
	        document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
	    }
	    function AddSeal() {
	        document.getElementById("PageOfficeCtrl1").ZoomSeal.AddSeal();
	    }
	    function AddHandSign() {
	        document.getElementById("PageOfficeCtrl1").ZoomSeal.AddHandSign();
	    }
	    function VerifySeal() {
	        try
	        {
	            document.getElementById("PageOfficeCtrl1").ZoomSeal.VerifySeal();
	        }
	        catch(e)
	        {
	        }
	    }
	</script>
    <!--**************   ׿�� PageOffice �ͻ��˴������    ************************-->
    <div style="width:auto;height: auto">
      <po:PageOfficeCtrl id="PageOfficeCtrl1" />
    </div>
  </body>
</html>
