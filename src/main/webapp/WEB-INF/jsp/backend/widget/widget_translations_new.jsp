<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Translations 123Widget</title>
</head>
<body>
	<h1>NEW Translation 123Widget</h1>
			
	<table class="table-edit">
		<tr>
			<th>Customer</th>
			<s:if test="%{#customers.size()==1}">
				<td><input type="text" id="form_customer" value="customers.identifier"></td>
			</s:if>
			<s:else>
			<td>
				<select id="form_customer">
					<s:iterator value="customers">
						<option value="<s:property value="identifier"/>"><s:property value="name"/></option>
					</s:iterator>
				</select>
			</td>
			</s:else>
		</tr>
		<tr>
			<th>Lang</th>
			<td><input type="text" id="form_lang" value=""></td>
		</tr>
		<tr>
			<th>Label</th>
			<td><input type="text" id="form_label" value=""></td>
		</tr>
		<tr>
			<th>Translation</th>
			<td><textarea cols="60" rows="12" id="form_translation"></textarea>
		</tr>
	<tr>
		<th></th>
		<td class="td-edit-unique"><div id="button_send">Crear</div></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	
	$('#button_send').click(function(){
		if($('#form_customer').val() === ''){
			alert("No has seleccionado un customer");
		} else if($('#form_lang').val() === ''){
			alert("No has escrito ningún idioma");
		} else if($('#form_label').val() === ''){
			alert("No has escrito ningún label");
		}  else if($('#form_translation').val() === ''){
			alert("No has escrito ninguna traducción");
		}  else {
			var url = "<s:url value='/backend/widget-translations/add'/>";
			$.ajax({
				  method: "POST",
				  url: url,
				  data: { form_customer: $('#form_customer').val(),
					  	  form_lang: $('#form_lang').val(),
					  	  form_label: $('#form_label').val(),
					  	  form_translation: $('#form_translation').val()
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