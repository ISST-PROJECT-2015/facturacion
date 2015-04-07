
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>


<html>
	<head>
		<title>Panel de control</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
   		<script type="text/javascript" src="/js/chart.js"></script>
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
	        	<img src="images/bandera_en.gif"><a href="/dashboard"><span>English</span></a>
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
	        <div class="col-50">
				<div class="wrapper">
					<h2> Bienvenido, <c:out value="${name}"/>!</h2></br>
		        	<h4> Acontinuación se muestra la información sobre su cuenta.</h4></br>
		        	<h4><u>INFORMACIÓN DE CUENTA:</u></h4>
					<p>Dominio: <c:out value="${domain}" /></p>
					<p>Producto: <c:out value="${product}" /></p>
					<p>Plan actual: <c:out value="${plan}" /></p>	
					<p>Email de avisos: <c:out value="${warning}" /> peticiones restantes.</p>			
				</div>
				<div class="wrapper">
					<h4><u>GRÁFICOS SOBRE INFORMES:</u></h4>
					<p>No disponible aún</p>
				</div>	
			</div>
			<div class="col-50">		
				<div class="wrapper">
					<h4><u>GRÁFICO SOBRE LAS PETICIONES RESTANTES:</u></h4>
					<div id="chart_div"></div>
					<p>Su plan actual es <c:out value="${plan}" />, e incluye <span id="nreqplan"><c:out value="${nreqplan}" /></span> peticiones.</p>
					<p>En este momento, tiene <span id="nreq"><c:out value="${nreq}" /></span> peticiones nuevas.</p>		
				</div>
			</div>
			</div>
		</section>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>Todos los derechos reservados &copy;</h4>
      
    	</footer>		