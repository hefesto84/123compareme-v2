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
<div class="panel-heading" style="height:40px">
	<div class="panel-heading-left">
		<h3 class="panel-title"><b>Available Hotels</b></h3>
	</div>
	<div class="panel-heading-right">
		<a href="#" class="btn btn-success" style="margin-top:-7px;">Add Hotel</a>
	</div>
</div>
<div class="panel-body">  
<table class="table table-hover" name="tableHotels">
<tbody>
	<tr>
		<td><b>ID</b></td>
		<td><b>NAME</b></td>
		<td><b>CUSTOMER ID</b></td>
		<td><b>CUSTOMER NAME</b></td>
		<td><b>CURRENCY</b></td>
	</tr>
	<s:iterator value="hotels">
	<tr>
		<td><s:property value="id"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="customerId"/></td>
		<td><s:property value="customerName"/></td>
		<td><s:property value="currency"/></td>
	</tr>
	</s:iterator>
</tbody>
</table>
</div>
</div>
</body>
</html>