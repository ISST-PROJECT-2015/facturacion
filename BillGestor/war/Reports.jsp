<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>


<html>
	<head>
		<title>Reports</title>
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
				<li class="active"><img src="images/reports.svg"><a href="/reports"><span>Reports</a></span></li>
				<li><img src="images/configuration.svg"><a href="/configuration"><span>Configuration</span></a></li>
				<li><img src="images/faq.svg"><a href="/faq"><span>FAQ</span></a></li>
			</ul>
			</nav>
		 </section>
		<section class="row-main">
	        	<div class="title-window">
		        	<h2> Reports </h2></br>
	            </div>
	            <div class="wrapper">
		        	<h4> If you want a detailed sales report, please click the next button: </h4>
		        	<form action="" method="post" accept-charset="utf-8">
								<input class="btn btn-default" type="submit" name="report" id="report" value="DOWNLOAD REPORT">
					</form></br>

					<p>*CSV file with sales information will be download automatically </p></br>
					<h4> But, if you prefer receive the report to email, please click here: </h4>
					<form action="/send" method="post" accept-charset="utf-8">
						<input class="btn btn-default" type="submit" name="report" id="report" value="SEND REPORT TO EMAIL">
					</form></br>
					<p>*In both cases, this action will cost 4,95€ </p>
						
				</div>	
				</section>
			</div>
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    	</footer>			
