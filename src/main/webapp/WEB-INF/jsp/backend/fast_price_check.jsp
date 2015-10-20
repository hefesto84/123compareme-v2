<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="panel panel-default">
	  <div class="panel-heading"><h3 class="panel-title"><b>Fast Price Check Tool</b></h3></div>
	  <div class="panel-body">  
		<form action="fast_price_check" method="GET">
		<s:hidden name="rooms" value="10" id="rooms"/>
		  <div class="form-group">
		    <label for="otaName">Select an available OTA:</label>
		    <select class="form-control" id="otaId" name="otaId">
		    	<s:iterator value="otas">
				    <option value="<s:property value="id"/>"><s:property value="name"/></option>
			    </s:iterator>
			</select>
		  </div>
		  <div class="form-group">
		    <label for="hotelName">Select an available Hotel:</label>
		    <select class="form-control" id="hotelId" name="hotelId">
			    <s:iterator value="hotels">
				    <option value="<s:property value="id"/>"><s:property value="name"/></option>
			    </s:iterator>
			</select>
		  </div>
		  <div class="form-group">
		    <label for="rooms">Number of rooms:</label>
		   	<input type="text" name="rooms_" id="rooms_"/>
		   	<br/>
		   	<label for="guests">Number of guests:</label>
		   	<input type="text" name="guests" id="guests"/>
		   	<br/>
		   	<br/>
		   	<label for="dateIn">Date in:</label>
		   	<input type="text" name="datein" id="datein"/>
		   	<br/>
		   	<label for="dateOut">Date out:</label>
		   	<input type="text" name="dateout" id="dateout"/>
		   	<br/><br/>
		   	<label for="language">Select language:</label>
		   	<select class="form-control" id="lang" name="lang">
			    <option value="es">es</option>
			    <option value="en">en</option>
				<option value="fr">fr</option>
				<option value="de">de</option>
				<option value="it">it</option>
				<option value="ru">ru</option>
				<option value="pt">pt</option>
				<option value="dk">dk</option>
				<option value="nl">nl</option>
			</select>
		  </div>
		  <button type="submit" class="btn btn-default">Check Price</button>
		</form>
	  </div>
	</div>
</body>
</html>