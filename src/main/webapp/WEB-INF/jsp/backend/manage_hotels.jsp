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
		<button type="button" style="margin-top:-7px;" class="btn btn-info" data-toggle="modal" data-target="#myModal">Add Hotel</button>
	</div>
</div>
<div class="panel-body">  

<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add new Hotel</h4>
        </div>
        <div class="modal-body">
          <div id="modalbodydata">
          	<div id="modalbodyname">
          		<label for="hotelName">Hotel Name:</label><br /> 
		  		<input type="text" name="hotelName" class="form-control" id="hotelName" value="<s:property value="hotelName"/>"/> <br /> 
          	</div>
          	<div id="modalbodycustomer">
          		<label for="customer">Customer:</label> 
          		<select class="form-control" id="customer" name="customer">
					<s:iterator value="customers">
					<option value="<s:property value="id"/>"><s:property value="name" /></option>
					</s:iterator>
				</select>
          	</div>
          	<div id="modalbodycurrency">
          		<label for="currency">Currency:</label><br /> 
          		<select class="form-control" id="currency" name="currency">
					<s:iterator value="currencies">
					<option value="<s:property/>"><s:property/></option>
					</s:iterator>
				</select>
          	</div>
          	<div id="modalbodystars">
          		<label for="currency">Stars:</label><br /> 
          		<select class="form-control" id="stars" name="stars">
          		    <option value="1">1</option>
          		    <option value="2">2</option>
          		    <option value="3" selected>3</option>
          		    <option value="4">4</option>
          		    <option value="4S">4S</option>
          		    <option value="5">5</option>
				</select>
          	</div>
          </div>
          <label for="expediaId">Expedia ID:</label><br /> 
		  <input type="text" name="expediaId" class="form-control" id="expediaId" value="<s:property value="expediaId"/>"/> <br />
		  <label for="bookingId">Booking ID:</label><br /> 
		  <input type="text" name="bookingId" class="form-control" id="bookingId" value="<s:property value="bookingId"/>"/> <br /> 
		  <label for="hotelsId">Hotels ID:</label><br /> 
		  <input type="text" name="hotelsId" class="form-control" id="hotelsId" value="<s:property value="hotelsId"/>"/> <br /> 
		  <label for="venereId">Venere ID:</label><br /> 
		  <input type="text" name="venereId" class="form-control" id="venereId" value="<s:property value="venereId"/>"/> <br />  
		  <label for="hrsId">HRS ID:</label><br /> 
		  <input type="text" name="hrsId" class="form-control" id="hrsId" value="<s:property value="hrsId"/>"/> <br /> 
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-info" id="autofill">Autofill Fields</button>
        <button type="button" class="btn btn-info" id="save">Save</button>
        </div>
      </div>
      
    </div>
  </div>
  
<table class="table table-hover" name="tableHotels">
<tbody>
	<tr>
		<td><b>ACTION</b></td>
		<td><b>ID</b></td>
		<td><b>NAME</b></td>
		<td><b>CUSTOMER ID</b></td>
		<td><b>CUSTOMER NAME</b></td>
		<td><b>CURRENCY</b></td>
		<td><b>STARS</b></td>
	</tr>
	<s:iterator value="hotels">
	<tr>
		<td><a href="<s:url value="%{'/backend/ht_manage_hotels_edit?id='+id}"/>">Edit</a></td>
		<td><s:property value="id"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="customerId"/></td>
		<td><s:property value="customerName"/></td>
		<td><s:property value="currency"/></td>
		<td><s:property value="stars"/></td>
	</tr>
	</s:iterator>
</tbody>
</table>
</div>
</div>
<script>
$( "#autofill" ).click(function() {
	var hotelName = $("#hotelName").val();
	$("#autofill" ).html("Searching...");
	$.getJSON( "../api/find?name="+hotelName, function( data ) {
		$("#autofill" ).html("Autofill fields");
		$("#expediaId").val(data.data["www.expedia.com"]);
		$("#bookingId").val(data.data["www.booking.com"]);
		$("#hotelsId").val(data.data["www.hotels.com"]);
		$("#venereId").val(data.data["www.venere.com"]);
		$("#hrsId").val(data.data["www.hrs.com"]);
	});
});

$( "#save" ).click(function() {
	var hotelName = $("#hotelName").val();
	var expediaId = $("#expediaId").val();
	var bookingId = $("#bookingId").val();
	var hotelsId = $("#hotelsId").val();
	var venereId = $("#venereId").val();
	var hrsId = $("#hrsId").val();
	var currency = $("#currency").val();
	var stars = $("#stars").val();
	var customer = $("#customer").val();
	var query = "?name="+hotelName+"&expediaId="+expediaId+"&bookingId="+bookingId+"&hotelsId="+hotelsId+"&venereId="+venereId+"&hrsId="+hrsId+"&currency="+currency+"&stars="+stars+"&customer="+customer;
	$("#save" ).html("Saving...");
	$.getJSON( "../api/add"+query, function( data ) {
		$("#autofill" ).html("Save");
		$("button.close").trigger("click");
	});
});
</script>
</body>
</html>