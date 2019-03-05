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
	<h1>Custom CSS 123Widget</h1>
	<table class="table table-striped table-bordered table-hover table-custom">
		<tr>
			<th>Modelo</th>
			<th class="table-custom-acciones">Acciones</th>
		</tr>
		<s:iterator value="estilos">
		<tr>
			<td class="table-custom-name"><s:property value="identifier"/></td>
			<td><a href="<s:url value="%{'/backend/widget-custom-css/edit?id='+id}"/>">Modificar</a>&nbsp;&nbsp;&nbsp;<span id="list_delete" onclick='deleteList("<s:url value="%{'/backend/widget-custom-css/delete?id='+id}"/>")'>Eliminar</span></td>
		</tr>
		</s:iterator>
		<tr style="border:1px solid #FFFFFF !important; background-color: #FFFFFF;">
			<td class="last-tr"></td>
			<td class="last-tr"><a href="<s:url value="/backend/widget-custom-css/new"/>"><div id="list_button_new">+ Nuevo</div></a></td>
		</tr>
	</table>
	
	<script type="text/javascript">
	 
		function deleteList(url){
			 var c = confirm("Estás seguro que quieres eliminarlo");
			    if (c == true) {
			    	$.ajax({
						method: "GET",
						url: url
					})
					.done(function( msg ) {
						if (msg.status === "ok"){
							alert("Eliminado correctamente");
							location.reload();
						}
					});
			    }
		}
		
	</script>
</body>
</html>