<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div id="section1">
	<div id="section1_left">
		<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Quality of OTAs</b>
			</h3>
		</div>
		<div class="panel-body">
			<div style="width: 100%">
				<canvas id="cnvOtas" ></canvas>
			</div>
		</div>
		</div>
		<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Last Notifications Sent</b>
			</h3>
		</div>
		<div class="panel-body">
			 <ul>
				  <li>Coffee</li>
				  <li>Tea</li>
				  <li>Milk</li>
				</ul> 
		</div>
		</div>
	</div>
	<div id="section1_right">
		<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Most Used Hotels</b>
			</h3>
		</div>
		<div class="panel-body">
			<div style="width: 100%">
				<canvas id="cnvPrices" ></canvas>
			</div>
		</div>
		</div>
	</div>
</div>
<div id="section2">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Price Evolution</b>
			</h3>
		</div>
		<div class="panel-body">
			<div style="width: 100%">
					<iframe src="http://www.123compare.me/piwik/index.php?module=Widgetize&action=iframe&moduleToWidgetize=MultiSites&actionToWidgetize=standalone&idSite=1&period=week&date=yesterday" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>

			</div>
		</div>
	</div>
</div>
<script src="../js/information.js"></script>
</body>
</html>