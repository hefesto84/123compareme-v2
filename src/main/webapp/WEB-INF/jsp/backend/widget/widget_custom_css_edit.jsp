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
	<h1>Edit <s:property value="widget.identifier"/></h1>
	<input type="hidden" id="form_id" value="<s:property value="widget.id"/>"/>
	<table class="table-edit">
		<tr>
			<th>Identifier</th>
			<td><input type="text" id="form_identifier" value="<s:property value="widget.identifier"/>"></td>
		</tr>
		<tr>
			<th>CSS</th>
			<td><textarea cols="60" rows="12" id="form_css"><s:property value="widget.css"/></textarea>
		</tr>
	<tr>
		<th></th>
		<td class="td-edit-unique"><div id="button_send">Modificar</div></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	var url = "<s:url value='/backend/widget-custom-css/modify'/>";
	$('#button_send').click(function(){
		$.ajax({
			  method: "POST",
			  url: url,
			  data: { form_id: $('#form_id').val(), 
				  	  form_identifier: $('#form_identifier').val(),
				  	  form_css: $('#form_css').val()
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