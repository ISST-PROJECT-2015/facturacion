
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<c:if test="${language == 'es'}">
<html>
	<head>
		<title>Configuración</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<meta charset="utf-8">
	</head>
	<body>				
			<!--  <h1>Hola <c:out value="${name}" /></h1>-->
		<header class="primary-header container group">
	        <div class="logo-box">
	        	<img src="images/logo.png" class="logo">
	      	</div>
	      	<div class="logout-contanier">
	        	<h3>Gestión de facturas</h3>
	        	
	            <a href="?logout=yes" class="logout-text">Cerrar sesión</a>
	        	<img src="images/logout.png" class="logout">
	        </div>
    	</header>
    	<section class="row-alt">
    	</section>
		<div class="content">
			<section class="row-nav">
			<nav class="menu">
			<ul>
				<li><img src="images/dashboard.svg"><a href="/dashboard"><span>Panel de control</span></a></li>
				<li><img src="images/chooseplan.svg"><a href="/chooseplan"><span>Escoger plan</span></a></li>
				<li><img src="images/reports.svg"><a href="/reports"><span>Informes</a></span></li>
				<li class="active"><img src="images/configuration.svg"><a href="/configuration"><span>Configuración</span></a></li>
				<li><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	        	<div class="title-window">
		        	<h2> Configuración </h2></br>
	            </div>
	            <div class="wrapper">
	            <form action="" method="post" accept-charset="utf-8">
	            	<h4>Seleccione cuándo quiere recibir mensajes de advertencia: 
	            	<select type="text" name="product" id="product" required>
			              	<option value="one" selected>1%</option>
			                <option value="five">5%</option>
			                <option value="ten">10%</option>
			                <option value="twentyfive">25%</option>
			                <option value="fifty">50%</option>
			         </select>
			         </h4>
			         <p>Actualmente tiene <c:out value="${nreq}" /> peticiones restantes.</br>
			         Con 1% será avisado con: <c:out value="${nreqwar}" /> peticiones restantes.</br>
			         Con 5% será avisado con: <c:out value="${nreqwarr}" /> peticiones restantes.</br>
			         Con 10% será avisado con: <c:out value="${nreqwarrr}" /> peticiones restantes.</br>
			         Con 25% será avisado con: <c:out value="${nreqwarrrr}" /> peticiones restantes.</br>
			         Con 50% será avisado con: <c:out value="${nreqwarrrrr}" /> peticiones restantes.</p>
			         
								<input class="btn btn-default" type="submit" name="subm" id="subm" value="CONFIRMAR">
					</form></br>
					
					<form action="/send" method="post" accept-charset="utf-8">
						<input class="btn btn-default" type="submit" value="Enviar email a la dirección de la empresa">
					</form></br>
					<h4> Si quiere cambiar el idioma a inglés, pulse el siguiente botón</h4>
					<form action="" method="Post">
   							 <input class="btn btn-default" type="submit" name="langen" id="langen" value="CAMBIAR IDIOMA" >
					</form></br>

		        	<h4> Si quiere borrar su cuenta, pulse el siguiente botón: </h4>
		        	<form action="" method="post" accept-charset="utf-8">
								<input class="btn btn-default" type="submit" name="delete" id="delete" value="BORRAR CUENTA">
					</form></br>
					<p>*Perderá todos sus datos, planes y peticiones pendientes.</p>
					
				</div>	
				</section>
			</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>Todos los derechos reservados &copy;</h4>
      
    	</footer>			

</c:if>
<c:if test="${language != 'es'}">
<html>
	<head>
		<title>Configuration</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<meta charset="utf-8">
	</head>
	<body>				
			<!--  <h1>Hola <c:out value="${name}" /></h1>-->
		<header class="primary-header container group">
	        <div class="logo-box">
	        	<img src="images/logo.png" class="logo">
	      	</div>
	      	<div class="logout-contanier">
	        	<h3>Electronic Invoice Management</h3>
	            <a href="?logout=yes" class="logout-text">Log out</a>
	        	<img src="images/logout.png" class="logout">
	        </div>
    	</header>
    	<section class="row-alt">
    	</section>
		<div class="content">
			<section class="row-nav">
			<nav class="menu">
			<ul>
				<li><img src="images/dashboard.svg"><a href="/dashboard"><span>DashBoard</span></a></li>
				<li><img src="images/chooseplan.svg"><a href="/chooseplan"><span>Choose Plan</span></a></li>
				<li><img src="images/reports.svg"><a href="/reports"><span>Reports</a></span></li>
				<li class="active"><img src="images/configuration.svg"><a href="/configuration"><span>Configuration</span></a></li>
				<li><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	        	<div class="title-window">
		        	<h2> Configuration </h2></br>
	            </div>
	            <div class="wrapper">
	            <form action="" method="post" accept-charset="utf-8">
	            	<h4>Select when you want to be warned about remaining requests: 
	            	<select type="text" name="product" id="product" required>
			              	<option value="one" selected>1%</option>
			                <option value="five">5%</option>
			                <option value="ten">10%</option>
			                <option value="twentyfive">25%</option>
			                <option value="fifty">50%</option>
			         </select>
			         </h4>
			         <p>You actually have <c:out value="${nreq}" /> remaining requests.</br>
			         With 1% you will be warned at: <c:out value="${nreqwar}" /> remaining requests.</br>
			         With 5% you will be warned at: <c:out value="${nreqwarr}" /> remaining requests.</br>
			         With 10% you will be warned at: <c:out value="${nreqwarrr}" /> remaining requests.</br>
			         With 25% you will be warned at: <c:out value="${nreqwarrrr}" /> remaining requests.</br>
			         With 50% you will be warned at: <c:out value="${nreqwarrrrr}" /> remaining requests.</p>
			         
								<input class="btn btn-default" type="submit" name="subm" id="subm" value="SUBMIT">
					</form></br>
					
					<form action="/send" method="post" accept-charset="utf-8">
						<input class="btn btn-default" type="submit" value="Send email to Enterprise address">
					</form></br>
					
					<h4> If you wish to change the language to spanish, click the following button</h4>
					<form action="" method="Post">
   							 <input class="btn btn-default" type="submit" name="langes" id="langes" value="CHANGE LANGUAGE" >
					</form></br>
				
					
		        	<h4> If you wish to close your account, click the next button: </h4>
		        	<form action="" method="post" accept-charset="utf-8">
								<input class="btn btn-default" type="submit" name="delete" id="delete" value="REMOVE ACCOUNT">
					</form></br>
					
					<p>*You will lose all your data, plans and remaining requests</p>
					
				</div>	
				</section>
			</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    	</footer>			
</body>
</html>
</c:if>