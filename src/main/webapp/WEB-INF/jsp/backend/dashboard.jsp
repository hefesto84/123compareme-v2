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
<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Overview <s:property value="otas.size()"/></b>
			</h3>
		</div>
		<div class="panel-body">
			<div style="width: 50%">
				<canvas id="canvas" height="450" width="600"></canvas>
			</div>
		</div>
</div>
<script src="../js/otaInformation.js"></script>
</body>
</html>