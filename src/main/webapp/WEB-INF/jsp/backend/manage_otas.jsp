<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="panel panel-default">
<div class="panel-heading"><h3 class="panel-title"><b>Available OTAs</b></h3></div>
<div class="panel-body">  
<table class="table table-hover" name="tableOtas">
<tbody>
	<tr>
		<td><b>ID</b></td>
		<td><b>NAME</b></td>
		<td><b>ICON PATH</b></td>
		<td><b>QUALITY*</b></td>
		<td><b>ACTIONS</b></td>
	</tr>
	<s:iterator value="otas">
	<tr>
		<td><s:property value="id"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="icon"/></td>
		<td>
			<b>
			<s:if test="%{quality > 75}">
				<font color="#01A611"><s:property value="quality"/> %</font>
			</s:if>
			<s:if test="%{quality <= 75 && quality >= 50}">
				<font color="#FF8C00"><s:property value="quality"/> %</font>
			</s:if>
			<s:if test="%{quality < 50}">
				<font color="#FF0000"><s:property value="quality"/> %</font>
			</s:if>
			</b>
		</td>
		<td><a href="editOta">Edit</a>&nbsp;<a href="delOta">Delete</a></td>
	</tr>
	</s:iterator>
</tbody>
</table>
* Quality value represents how many times a query for a given ota was failed.
</div>
</div>
</body>
</html>