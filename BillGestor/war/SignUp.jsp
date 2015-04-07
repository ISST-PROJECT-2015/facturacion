<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
	<head>
		<meta charset="utf-8">
		<title>Sign up </title>
		<link rel="stylesheet" type="text/css" href="css/signup.css" />	
	</head>
	<body>
		<header class="primary-header container group">
	        <div class="logo-box">
	        	<img src="images/logo.png" class="logo">
	      	</div>

	        <h3 class="tagline">Electronic Invoice Management</h3>
    	</header>
    	<section class="row-alt">
    	</section>
    	<section class="row">
	        <div class="lead container">

		        <form class="col" action="/signup" method="post" accept-charset="utf-8">
		        	<fieldset class="register-group">

			            <label for="name"> Enterprise name</label>
			            <input input type="text" name="name" id="name" required>
			            
			            <label for="domain"> Website </label>
			            <input type="text" name="domain" id="domain" required>
			           
			            <label for="email"> Email </label>
			            <input type="email" name="email" id="email" required>
			            
			            <label for="password"> Password </label>
			            <input type="password" name="password" id="password" required>
			            
			            <label for="product"> Product offered </label>
			            <select type="text" name="product" id="product" required>
			              	<option value="APIs" selected>APIs</option>
			                <option value="Electronic books">Electronic books</option>
			                <option value="Multimedia">Multimedia</option>
			                <option value="Online courses">Online courses</option>
			                <option value="Online file sharing services">Online file sharing services</option>
			                <option value="Software">Software</option>
			                <option value="Streaming services">Streaming services</option>
			                <option value="Other">Other</option>
			            </select>

			        </fieldset>
			        <input type="submit" class="btn btn-default" value="SUBMIT" />
				</form>

	        </div>
    	</section>	
		<footer class="primary-footer container group">

      		<h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    	</footer>
		

	</body>
</html>
