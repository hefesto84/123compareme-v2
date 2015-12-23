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
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Fast Price Check Tool - Source IP: <s:property value="ip"/></b>
			</h3>
		</div>
		<div class="panel-body">
			<div id="filters">
				<form action="fast_price_check" method="GET">
					<div id="left_params">
						<div id="left_params_ota">
							<div class="form-group">
								<label for="otaId">Select an available OTA:</label> <select
									class="form-control" id="otaId" name="otaId">
									<option selected value="0">All</option>
									<s:iterator value="otas">
										<option value="<s:property value="id"/>"><s:property value="name" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div id="left_params_lang">
							<div class="form-group">
								<label for="lang">Select language:</label> <select
									class="form-control" id="lang" name="lang">
									<option value="es" <s:if test="%{lastLanguage == 'es'}">selected</s:if>>Spanish</option>
									<option value="en" <s:if test="%{lastLanguage == 'en'}">selected</s:if>>English</option>
									<option value="fr" <s:if test="%{lastLanguage == 'fr'}">selected</s:if>>French</option>
									<option value="de" <s:if test="%{lastLanguage == 'de'}">selected</s:if>>German</option>
									<option value="it" <s:if test="%{lastLanguage == 'it'}">selected</s:if>>Italian</option>
									<option value="ru" <s:if test="%{lastLanguage == 'ru'}">selected</s:if>>Russian</option>
									<option value="pt" <s:if test="%{lastLanguage == 'pt'}">selected</s:if>>Portuguese</option>
									<option value="dk" <s:if test="%{lastLanguage == 'dk'}">selected</s:if>>Dansk</option>
									<option value="nl" <s:if test="%{lastLanguage == 'nl'}">selected</s:if>>Dutch</option>
								</select>
							</div>
							<div class="form-group">
								<label for="currency">Currency:</label><br /> 
				          		<select class="form-control" id="currency" name="currency">
									<s:iterator value="currencies">
									<option value="<s:property value="currency"/>" <s:if test="%{lastCurrency == currency}">selected</s:if> /><s:property value="currency"/> | <s:property value="name" /></option>
									<!--  <option value="<s:property value="name"/>" <s:if test="%{id == idHotel}">selected</s:if>><s:property value="name" /></option> -->
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<div id="center_params">
						<div class="form-group">
							<label for="hotelName">Select an available Hotel:</label> <select
								class="form-control" id="hotelName" name="hotelName">
								<s:iterator value="hotels">
									<option value="<s:property value="name"/>" <s:if test="%{id == idHotel}">selected</s:if>><s:property value="name" /></option>
								</s:iterator>
							</select>
						</div>
					</div>
					<div id="right_params">
						<div class="form-group">
							<div id="sub_left_params">
								<label for="rooms">Number of rooms:</label><br /> 
								<input class="form-control" type="text" id="rooms" name="rooms" value="<s:property value="rooms"/>"/>
								<br /> 
								<label for="guests">Number of guests:</label><br />
								<input class="form-control" type="text" id="guests" name="guests" value="<s:property value="guests"/>"/>
							</div>
							<div id="sub_right_params">
								<label for="dateIn">Date in:</label><br /> 
								<input type="text" name="dateIn" class="form-control" id="dateIn" value="<s:property value="dateIn"/>"/> <br /> 
								<label for="dateOut">Date out:</label><br /> 
								<input type="text" name="dateOut" class="form-control" id="dateOut" value="<s:property value="dateOut"/>"/>
							</div>
						</div>
					</div>

					<button type="submit" class="btn btn-success">Check Price</button>
				</form>
			</div>
			<div id="results">
				<table class="table table-hover" name="tablePrices">
					<tbody>
						<tr>
							<td><b>ID</b></td>
							<td><b>Ota</b></td>
							<td><b>Language</b></td>
							<td><b>Date in</b></td>
							<td><b>Date Out</b></td>
							<td><b>Guests</b></td>
							<td><b>Rooms</b></td>
							<td><b>Price</b></td>
							<td><b>Pure Price</b></td>
							<td><b>Base Price</b></td>
							<td><b>Actions</b></td>
						</tr>
						<s:iterator value="datos">
							<tr>
								<td><s:property value="id" /></td>
								<td><img style="width:128px;height:32px;" src="../img/otas/<s:property value="otaId" />.png"/></td>
								<td><img src="../img/flags/<s:property value="lang" />.png"/></td>
								<td><s:property value="dateIn" /></td>
								<td><s:property value="dateOut" /></td>
								<td><s:property value="guests" /></td>
								<td><s:property value="rooms" /></td>
								<td><s:property value="price" /> <s:property value="currency"/></td>
								<td><s:property value="purePrice"/></td>	
								<td><s:property value="basePrice" /></td>
								<td>
									<a href="<s:property value="query" />">View Query</a>
											
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			var dateIn = new Date();
			if($("#dateIn").val().length < 2){
				$("#dateIn").val(dateIn.getDate()+"/"+(dateIn.getMonth()+1)+"/"+dateIn.getFullYear());
			}
			$("#dateIn").datepicker();
		});
		$(function() {
			var dateOut = new Date();
			if($("#dateOut").val().length < 2){
				$("#dateOut").val((dateOut.getDate()+1)+"/"+(dateOut.getMonth()+1)+"/"+dateOut.getFullYear());
			}
			$("#dateOut").datepicker();
		});
	</script>
</body>
</html>