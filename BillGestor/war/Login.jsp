<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
		<title>Log in</title>
		<link rel="stylesheet" type="text/css" href="css/Login.css" />	
	</head>
	<body>

	<!-- header -->
	<header class="primary-header container group">
	        <div class="logo-box">
	        	<img src="images/logo.png" class="logo">
	      	</div>

	        <h3 class="tagline">Electronic Invoice Management</h3>

	        <form action="/login" method="post" class="login-form" accept-charset="utf-8">
				<table>
					<tr>
						<td><label for="email">Email:</label></td>
						<td><label for="password">Password:</label></td>
					</tr>
					<tr>
						<td><input type="email" name="email" id="email" /></td>
						<td><input type="password" name="password" id="password"/></td>
						<td><input type="submit" class="btn btn-login" value="LOGIN" /></td>
					</tr>
				</table>
			</form>
    </header>
    <section class="row-alt">
        <div class="lead container">

	        <h1>Solve your invoice troubles!</h1>

	        <p>If you are a digital enterprise and you wish to forget the specific taxes of each European Union country... We are glad to solve your problems. Join us and start using our service for free.</p>

	        <input class="btn btn-default" onClick="location.href = '/signup'" type="submit" name="submit" value="Sign up">

        </div>
    </section>	
	<footer class="primary-footer container group">

      <h3>G. E. E. F. T.</h3><h4>All rights reserved &copy;</h4>
      
    </footer>
	</body>
</html>