
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<c:if test="${language == 'es'}">
<html>
	<head>
		<title>FAQ</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<meta charset="utf-8">
	</head>
	<body>				
		
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
			<li ><img src="images/dashboard.svg"><a href="/dashboard"><span>Panel de control</span></a></li>
			<li><img src="images/chooseplan.svg"><a href="/chooseplan"><span>Escoger plan</span></a></li>
			<li><img src="images/reports.svg"><a href="/reports"><span>Informes</a></span></li>
			<li><img src="images/configuration.svg"><a href="/configuration"><span>Configuración</span></a></li>
			<li class="active"><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	        	<div class="title-window">
		        	<h2> FAQ </h2></br>
	            </div>
	            <div class="wrapper">
	           <p><b>¿Qué plan debería elegir?</b></p>
				<p>Puede elegir el plan que mejor se adapte a sus necesidades. Puede ver todos los detalles en la pestaña escoger plan.</p>
	           <br>
				<p><b>¿Seré avisado de las peticiones restantes?</b></p>
				<p>En la sección Configuración, podrá elegir cuándo ser avisado. Le mandaremos un email con toda la información.</p>
		        <br>
		        <p><b>¿Cómo puedo obtener mis informes de ventas?</b></p>
				<p>Puede descargar informes detallados sobre sus ventas en la sección Informes, o si lo prefiere, podemos mandarselos en un email.</p>
		        <br>
		        <p><b>¿Cómo puedo borrar mi cuenta?</b></p>
				<p>En la sección Configuración encontrarás el botón para borrar tu cuenta. Borraremos toda la información referida a su empresa.</p>
		       	
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
		<title>FAQ</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<meta charset="utf-8">
	</head>
	<body>				
		
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
				<li><img src="images/configuration.svg"><a href="/configuration"><span>Configuration</span></a></li>
				<li class="active"><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	        	<div class="title-window">
		        	<h2> FAQ </h2></br>
	            </div>
	            <div class="wrapper">
	           <p><b>What plan should I select?</b></p>
				<p>You should select the most suitable plan for your needs. You can find all the plans information in "Choose Plan" section.</p>
	           <br>
				<p><b>How will I be warned about remaining requests?</b></p>
				<p>In "Configuration" section, you can select when you want to be warned. We will send an e-mail with the information.</p>
		        <br>
		        <p><b>How can I get my sales reports?</b></p>
				<p>You have to go to "Reports" section, where you can download a detailed sales report or, if you prefer, we will send you by email</p>
		        <br>
		        <p><b>How can I remove my account?</b></p>
				<p>In "Configuration" section, you can find the button "Remove account". We will remove your account and all the information related to your enterprise. </p>
		       	
				</div>	
		</section>
			</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    	</footer>
    	
    </body>
    </html>
    </c:if>			