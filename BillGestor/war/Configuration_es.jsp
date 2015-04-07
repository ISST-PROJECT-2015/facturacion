
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>


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
	        	<img src="images/bandera_en.gif"><a href="/configuration"><span>English</span></a>
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
				<li class="active"><img src="images/dashboard.svg"><a href="/dashboard?l=es"><span>Panel de control</span></a></li>
				<li><img src="images/chooseplan.svg"><a href="/chooseplan?l=es"><span>Escoger plan</span></a></li>
				<li><img src="images/reports.svg"><a href=""><span>Informes</a></span></li>
				<li><img src="images/configuration.svg"><a href="/configuration?l=es"><span>Configuración</span></a></li>
				<li><img src="images/faq.svg"><a href=""><span>FAQ</span></a></li>
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
