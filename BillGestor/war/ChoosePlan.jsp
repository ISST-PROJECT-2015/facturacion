
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<c:if test="${language == 'es'}">
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
				<li class="active"><img src="images/chooseplan.svg"><a href="/chooseplan"><span>Escoger plan</span></a></li>
				<li><img src="images/reports.svg"><a href="/reports"><span>Informes</a></span></li>
				<li><img src="images/configuration.svg"><a href="/configuration"><span>Configuración</span></a></li>
				<li><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
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

</c:if>
<c:if test="${language != 'es'}">
<html>
	<head>
	<script type="text/javascript">
   			function validate(form){
   				return confirm("You are trying to buy a new plan but you might have some requests left. \n \n Do you want to confirm?");
   			}
   		
   		</script>
		<title>Choose Plan</title>
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
				<li class="active"><img src="images/chooseplan.svg"><a href="/chooseplan"><span>Choose Plan</span></a></li>
				<li><img src="images/reports.svg"><a href="/reports"><span>Reports</a></span></li>
				<li><img src="images/configuration.svg"><a href="/configuration"><span>Configuration</span></a></li>
				<li><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	  <!--      <div class="lead container"> -->
	        	<div class="title-window">
	        	<h2> Pricing plans </h2></br>
	        	<h4> Please, select the best plan for you! </h4>
	        </div>
	        	<div class="table-plan">
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>STARTUP</td></tr>
						<tr class="request-cell"><td>100 requests</td></tr>
						<tr class="price-cell"><td>9,95 €</td></tr>
						<tr class="button-cell"><td>
					<!-- 	<form action="" method="post" accept-charset="utf-8">
							<input class="btn-table" type="submit" name="startup" id="startup" value="BUY NOW">
						</form> -->
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="startup" id="startup" value="BUY NOW" >
						</form>
					<td></tr>
					</table>	
				</section>
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>PREMIUM</td></tr>
						<tr class="request-cell"><td>1.000 requests</td></tr>
						<tr class="price-cell"><td>59,95 €</td></tr>
						<tr class="button-cell"><td>
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="premium" id="premium" value="BUY NOW" >
						</form>
					<td></tr>
					</table>
				</section>
				<section class="col-1-3">
					<table class="plan">
						<tr class="first-cell"><td>GOLD</td></tr>
						<tr class="request-cell"><td>10.000 requests</td></tr>
						<tr class="price-cell"><td>299,95 €</td></tr>
						<tr class="button-cell"><td>
						<form action="" method="Post" onsubmit="return validate(this);">
   							 <input class="btn-table" type="submit" name="gold" id="gold" value="BUY NOW" >
						</form>
					<td></tr>
					</table>
				</section>
			<!--	</div> -->
			</div>
		</section>
		</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    	</footer>			
</body>
</html>
</c:if>