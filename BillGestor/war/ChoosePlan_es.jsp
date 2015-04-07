
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>


<html>
	<head>
	<script type="text/javascript">
   			function validate(form){
   				return confirm("Está intentando comprar más peticiones y puede que tenga algunas restantes. \n \n ¿Desea continuar?");
   			}
   		
   		</script>
		<title>Escoger plan</title>
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
	        	<img src="images/bandera_en.gif" ><a href="/chooseplan"><span>English</span></a>
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
	  <!--      <div class="lead container"> -->
	        	<div class="title-window">
	        	<h2> Tarifas </h2></br>
	        	<h4> Escoja el plan que le venga mejor </h4>
	        </div>
	        	<div class="table-plan">
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>STARTUP</td></tr>
						<tr class="request-cell"><td>100 peticiones</td></tr>
						<tr class="price-cell"><td>9,95 €</td></tr>
						<tr class="button-cell"><td>
					<!-- 	<form action="" method="post" accept-charset="utf-8">
							<input class="btn-table" type="submit" name="startup" id="startup" value="BUY NOW">
						</form> -->
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="startup" id="startup" value="COMPRAR AHORA" >
						</form>
					<td></tr>
					</table>	
				</section>
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>PREMIUM</td></tr>
						<tr class="request-cell"><td>1.000 peticiones</td></tr>
						<tr class="price-cell"><td>59,95 €</td></tr>
						<tr class="button-cell"><td>
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="premium" id="premium" value="COMPRAR AHORA" >
						</form>
					<td></tr>
					</table>
				</section>
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>GOLD</td></tr>
						<tr class="request-cell"><td>10.000 peticiones</td></tr>
						<tr class="price-cell"><td>299,95 €</td></tr>
						<tr class="button-cell"><td>
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="gold" id="gold" value="COMPRAR AHORA" >
						</form>
					<td></tr>
					</table>
				</section>
			<!--	</div> -->
			</div>
		</section>
		</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>Todos los derechos reservados &copy;</h4>
      
    	</footer>			
