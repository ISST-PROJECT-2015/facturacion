<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
	<head>
		<title>Paises</title>
		<link rel="stylesheet" type="text/css" href="css/localizacionCountry.css" />
		<meta charset="utf-8">
	</head>
	<body>
	
		<div style="width: 100%;">
			<div class="line"></div>
			<div class="topLine">
				<div style="float: left;" class="headline">Paises</div>
				<div style="float: right;">
					<a
						href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a>
					<c:if test="${user != null}"><c:out value="${user.nickname}"/></c:if>
				</div>
			</div>
		</div>
	
		<div style="clear: both;" />
	
		<table>
			<tr>
				<th>Pais</th>
				<th>Iva</th>
			</tr>
	
			<c:forEach items="${paises}" var="country">
				<tr>
					<td><c:out value="${country.name}" /></td>
					<td><c:out value="${country.iva}" /></td>
					<td><a class="done"
						href="<c:url value="/done?id=${country.id}" />">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	
	
		<hr />
	
		<div class="main">
	
			<div class="headline">Nuevo pais</div>
	
			<c:choose>
				<c:when test="${user != null}">
					<form action="/new" method="post" accept-charset="utf-8">
						<table>
							<tr>
								<td><label for="name">Pais</label></td>
								<td><input type="text" name="name" id="name" size="65" /></td>
							</tr>
							<tr>
								<td><label for="iva">Iva</label></td>
								<td><input type="text" name="iva" id="iva" size="65" /></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="submit"
									value="Crear" /></td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
	Necesita ser administrador para modificar la pagina
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>