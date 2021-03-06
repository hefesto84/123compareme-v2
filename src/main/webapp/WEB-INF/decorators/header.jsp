<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<s:url value='/js/jquery-2.1.4.min.js'/>">"></script>
<link rel="stylesheet" href="<s:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<s:url value='/css/bootstrap-theme.min.css'/>">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<s:url value='/css/main.css'/>">
<script src="<s:url value='/js/bootstrap.min.js'/>"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<s:url value='/js/jquery-ui-datapicker-es.js'/>"></script>
<script src="<s:url value='/js/Chart.min.js'/>"></script>
<title>123Compare.me v.2</title>
</head>
<body  style="margin:1em;">

<nav class="navbar navbar-inverse" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-ex1-collapse">
      <span class="sr-only">Desplegar navegación</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">123 Compare.Me v.2</a>
  </div>
 
  <!-- Agrupar los enlaces de navegación, los formularios y cualquier
       otro elemento que se pueda ocultar al minimizar la barra -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <li class="active"><a href="dashboard">Dashboard</a></li>
      <li><a href="fast_price_check">Fast Price Check</a></li>
      <li class="dropdown">
        <a href="tools" class="dropdown-toggle" data-toggle="dropdown">
          Tools <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="download_statistics">Download Statistics</a></li>
          <li><a href="ht_manage_hotels">Manage Hotels</a></li>
          <li><a href="ht_manage_otas">OTA Status</a></li>
        </ul>
      </li>
      <li><a href="widget_configuration">Configuration</a></li>
      <!-- <li class="dropdown">
        <a href="widget" class="dropdown-toggle" data-toggle="dropdown">
          Widget <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="<s:url value='/backend/widget-custom-css/list'/>">Custom CSS</a></li>
          <li><a href="<s:url value='/backend/widget-custom-html/list'/>">Custom HTML</a></li>
          <li><a href="<s:url value='/backend/widget-translations/list'/>">Translations</a></li>
        </ul>
      </li> -->
    </ul>

    <ul class="nav navbar-nav navbar-right">
	  <!--        
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          Help<b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="ht_check_dbstatus">How to check DB status</a></li>
          <li class="divider"></li>
          <li><a href="contact">Contact with us</a></li>
        </ul>
        
      </li>
      -->
      <li><a href="<s:url value='/login/signout'/>"><b><font color="#FF8C00">Logout</font></b></a></li>
    </ul>
  </div>
</nav>

<decorator:body>
</decorator:body>
</body>
</html>