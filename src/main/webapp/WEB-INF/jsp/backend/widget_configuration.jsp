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
<div class="panel-heading"><h3 class="panel-title"><b>User Configuration</b></h3></div>
<div class="panel-body">  

<label for="c.name">Customer Name:</label><br /> 
<input type="text" name="c.name" class="form-control" id="c.name" value="<s:property value="c.name"/>"/> <br /> 
<label for="c.username">Username:</label><br /> 
<input type="text" name="c.username" class="form-control" id="c.username" value="<s:property value="c.username"/>"/> <br /> 
<label for="c.password">Password:</label><br /> 
<input type="text" name="c.password" class="form-control" id="c.password" value="<s:property value="c.password"/>"/> <br /> 
<button type="submit" class="btn btn-success">Save Settings</button>
</div>
</div>
<div class="panel panel-default">
<div class="panel-heading"><h3 class="panel-title"><b>Mail Configuration</b></h3></div>
<div class="panel-body">  

<label for="c.musername">Mail user:</label><br /> 
<input type="text" name="c.musername" class="form-control" id="c.musername" value="<s:property value="c.musername"/>"/> <br /> 
<label for="c.mpassword">Mail password:</label><br /> 
<input type="text" name="c.mpassword" class="form-control" id="c.mpassword" value="<s:property value="c.mpassword"/>"/> <br /> 
<label for="c.mhost">Mail hostname:</label><br /> 
<input type="text" name="c.mhost" class="form-control" id="c.mhost" value="<s:property value="c.mhost"/>"/> <br /> 
<label for="c.contact">Contact Address:</label><br /> 
<input type="text" name="c.contact" class="form-control" id="c.contact" value="<s:property value="c.contact"/>"/> <br /> 

<button type="submit" class="btn btn-success">Test Mail Configuration</button>
<button type="submit" class="btn btn-success">Save Settings</button>
</div>
</div>

<div class="panel panel-default">
<div class="panel-heading"><h3 class="panel-title"><b>Notifications Configuration</b></h3></div>
<div class="panel-body">  

OTA Price is lower than customer price:
<input type="checkbox" name="notifyota" class="form-control" id="notifyota">
<label for="c.username">Username:</label><br /> 
<input type="text" name="c.username" class="form-control" id="c.username" value="<s:property value="c.username"/>"/> <br /> 
<label for="c.password">Password:</label><br /> 

<input type="text" name="c.password" class="form-control" id="c.password" value="<s:property value="c.password"/>"/> <br /> 
<button type="submit" class="btn btn-success">Save Settings</button>
</div>
</div>

<div class="panel panel-default">
<div class="panel-heading"><h3 class="panel-title"><b>Widget Configuration</b></h3></div>
<div class="panel-body">  

<label for="c.name">Customer Name:</label><br /> 
<input type="text" name="c.name" class="form-control" id="c.name" value="<s:property value="c.name"/>"/> <br /> 
<label for="c.username">Username:</label><br /> 
<input type="text" name="c.username" class="form-control" id="c.username" value="<s:property value="c.username"/>"/> <br /> 
<label for="c.password">Password:</label><br /> 
<input type="text" name="c.password" class="form-control" id="c.password" value="<s:property value="c.password"/>"/> <br /> 
<button type="submit" class="btn btn-success">Save Settings</button>
</div>
</div>
</body>
</html>