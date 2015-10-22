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
									<option selected value="es">Spanish</option>
									<option value="en">English</option>
									<option value="fr">French</option>
									<option value="de">German</option>
									<option value="it">Italian</option>
									<option value="ru">Russian</option>
									<option value="pt">Portuguese</option>
									<option value="dk">Dansk</option>
									<option value="nl">Dutch</option>
								</select>
							</div>
						</div>
					</div>
					<div id="center_params">
						<div class="form-group">
							<label for="hotelName">Select an available Hotel:</label> <select
								class="form-control" id="hotelName" name="hotelName">
								<s:iterator value="hotels">
									<option value="<s:property value="name"/>"><s:property value="name" /></option>
								</s:iterator>
							</select>
						</div>
					</div>
					<div id="right_params">
						<div class="form-group">
							<div id="sub_left_params">
								<label for="rooms">Number of rooms:</label><br /> <select
									class="form-control" id="rooms" name="rooms">
									<option selected value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
								</select> <br /> <label for="guests">Number of guests:</label><br /> <select
									class="form-control" id="guests" name="guests">
									<option value="1">1</option>
									<option selected value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
								</select>
							</div>
							<div id="sub_right_params">
								<label for="dateIn">Date in:</label><br /> 
								<input type="text" name="dateIn" class="form-control" id="dateIn" value="<s:property value="dateIn"/>"/> <br /> 
								<label for="dateOut">Date out:</label><br /> 
								<input type="text" name="dateOut" class="form-control" id="dateOut" value="<s:property value="dateOut"/>"/>
							</div>
						</div>
					</div>

					<button type="submit" class="btn btn-default">Check Price</button>
					<button type="submit" class="btn btn-default">Download Results</button>
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
								<td><s:property value="price" /></td>
								<td><s:property value="basePrice" /></td>
								<td>
									<a href="
											<s:url action="delete">
												<s:param name="id">
													<s:property value="id" />
												</s:param>
											</s:url>
											">
											Delete from Cache
									</a>
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