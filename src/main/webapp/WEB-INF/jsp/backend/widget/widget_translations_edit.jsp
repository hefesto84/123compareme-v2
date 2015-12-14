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
	<h1>Edit <s:property value="traduccion.label"/></h1>
	<input type="hidden" id="form_id" value="<s:property value="traduccion.id"/>"/>
	<table class="table-edit">
		<tr>
			<th>Customer</th>
			<td><input type="text" id="form_customer" value="<s:property value="traduccion.customer"/>" readonly></td>
		</tr>
		<tr>
			<th>Lang</th>
			<td><input type="text" id="form_lang" value="<s:property value="traduccion.lang"/>"></td>
		</tr>
		<tr>
			<th>Label</th>
			<td><input type="text" id="form_label" value="<s:property value="traduccion.label"/>"></td>
		</tr>
		<tr>
			<th>Translation</th>
			<td><textarea cols="60" rows="12" id="form_translation"><s:property value="traduccion.translation"/></textarea>
		</tr>
	<tr>
		<th></th>
		<td class="td-edit-unique"><div id="button_send">Crear</div></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	var url = "<s:url value='/backend/widget-translations/modify'/>";
	$('#button_send').click(function(){
		$.ajax({
			  method: "POST",
			  url: url,
			  data: { form_id: $('#form_id').val(), 
				  	  form_lang: $('#form_lang').val(), 
				  	  form_label: $('#form_label').val(),
				  	  form_translation: $('#form_translation').val()
				  	}
			})
			  .done(function( msg ) {
			    if (msg.status === "ok"){
			    	$('.table-edit').after("<div class='edit-success'>! Se han modificado correctamente los datos</div>");
			    }
			  });
	});
	</script>
	
</body>
</html>