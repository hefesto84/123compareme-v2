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
		<h3><s:property value="hotel.name"/></h3>
		<s:set var="currencyHotel"><s:property value="hotel.currency"/></s:set>
		<div id="editmodalname">
			<label for="currency">Nombre:</label>
			<input type="text" class="form-control" id="name" value="<s:property value="hotel.name"/>">
		</div>
		<div id="editmodalcurrency">
			<label for="currency">Currency:</label>
			<select class="form-control" id="currency" name="currency">
				<s:iterator value="currencies">
					<s:set var="currency"><s:property/></s:set>
					<option value="<s:property/>" <s:if test="%{#currency == #currencyHotel}">selected</s:if>><s:property/></option>
				</s:iterator>
			</select>
		</div>
		<div id="editmodalstars">
			<label for="stars">Stars:</label>
			<select class="form-control" id="stars" name="stars">
				<option value="1" <s:if test="%{hotel.stars.equalsIgnoreCase('1')}">selected</s:if>>1</option>
	  		    <option value="2" <s:if test="%{hotel.stars.equalsIgnoreCase('2')}">selected</s:if>>2</option>
	  		    <option value="3" <s:if test="%{hotel.stars.equalsIgnoreCase('3')}">selected</s:if>>3</option>
	  		    <option value="4" <s:if test="%{hotel.stars.equalsIgnoreCase('4')}">selected</s:if>>4</option>
	  		    <option value="4S" <s:if test="%{hotel.stars.equalsIgnoreCase('4S')}">selected</s:if>>4S</option>
	  		    <option value="5" <s:if test="%{hotel.stars.equalsIgnoreCase('5')}">selected</s:if>>5</option>
			</select>
		</div>
		<div style="clear:both;"></div>
		<div style="margin-bottom: 5px; margin-top: 5px;"><b>Otas:</b></div>
		<s:set var="count" value="1"/>
		<s:iterator value="hotelOtas">
			<div id="ota">
				<div id="ota_name">
					<i><s:property value="otaName"/>:</i>
					<input type="text" class="form-control" id="ota<s:property value="otaName"/>" value="<s:property value="name"/>">
				</div>
				<div id="ota_status">
					<br>Actived&nbsp;<input type="checkbox" id="actived<s:property value="otaName"/>" <s:if test="%{#this.actived == true}">checked</s:if>>
				</div>
				<div style="clear:both;"></div>
				<s:set var="count" value="%{#count+1}"/>
			</div>
		</s:iterator>
		<div style="clear:both;"></div>
		<div class="boton_actualizar" id="actualizar">Update</div>
</div>
<script>

$('#actualizar').click(function(){
	var id = <s:property value="hotel.id"/>;
	var expediaActived = false;
	var bookingActived = false;
	var hotelsActived = false;
	var venereActived = false;
	var hrsActived = false;
	var hotelName = $("#name").val();
	var currency = $("#currency").val();
	var stars = $("#stars").val();
	var expedia = $("#otaExpedia").val();
	var booking = $("#otaBooking").val();
	var hotels = $("#otaHotels").val();
	var venere = $("#otaVenere").val();
	var hrs = $("#otaHRS").val();
	
	if ($("#activedExpedia").is(":checked")){
		expediaActived = true;
	}
	if ($("#activedBooking").is(":checked")){
		bookingActived = true;
	}
	if ($("#activedHotels").is(":checked")){
		hotelsActived = true;
	}
	if ($("#activedVenere").is(":checked")){
		venereActived = true;
	}
	if ($("#activedHRS").is(":checked")){
		hrsActived = true;
	}
	
	var query = "?id="+id+"&name="+hotelName+"&expediaId="+expedia+"&bookingId="+booking+"&hotelsId="+hotels+"&venereId="+venere+"&hrsId="+hrs+"&currency="+currency+"&stars="+stars+"&expediaActived="+expediaActived+"&bookingActived="+bookingActived+"&hotelsActived="+hotelsActived+"&venereActived="+venereActived+"&hrsActived="+hrsActived;
	var url = "<s:url value='/api/ht_manage_hotels_update'/>";
	
	$.getJSON( url+query, function( data ) {
		alert("Succesfully updated");
		location.reload();
	});
	
});

</script>
</body>
</html>