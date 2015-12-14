<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Custom CSS 123Widget</title>
</head>
<body>
	<h1>NEW CSS 123Widget</h1>
	<table class="table-edit">
		<tr>
			<th>Identifier</th>
			<td><input type="text" id="form_identifier" value=""></td>
		</tr>
		<tr>
			<th>CSS</th>
			<td><textarea cols="60" rows="12" id="form_css"></textarea>
		</tr>
	<tr>
		<th></th>
		<td class="td-edit-unique"><div id="button_send">Crear</div></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	
	$('#button_send').click(function(){
		if($('#form_identifier').val() === ''){
			alert("No has puesto un identificador");
		} else if($('#form_css').val() === ''){
			alert("No has escrito ningún CSS");
		} else {
			var url = "<s:url value='/backend/widget-custom-css/add'/>";
			$.ajax({
				  method: "POST",
				  url: url,
				  data: { form_identifier: $('#form_identifier').val(),
					  	  form_css: $('#form_css').val()
					  	}
			})
			.done(function( msg ) {
				if (msg.status === "ok"){
					alert("Se ha añadido correctamente los datos");
				    window.location.href = "list";
				}
			});
		}
	});
	
	</script>
	
</body>
</html>