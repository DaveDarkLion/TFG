<html>

	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

	<script type='text/javascript' src='//code.jquery.com/jquery-1.10.1.min.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/common.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/data.js'></script>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/materialize.css'>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/mystyles.css'>
	<script type='text/javascript' src='/ejercicios-programacion/js/materialize.js'></script>
	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<head>
	
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
		
		<link href="/ejercicios-programacion/favicon.ico" rel="icon" type="image/x-icon" />
		<title>Entrar</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>

		<div class="valign-wrapper row login-box">
		
			<div class="col card hoverable s12 m10 pull-m1 l6 pull-l3">
				
				<form accept-charset="ISO-8859-1">
						
					<div class="card-content">
					
		        		<span class="card-title">Introducir credenciales</span>
		        			
		        		<div class="row">	
		        		
							<div class="input-field col s12">
								<i class="material-icons prefix">email</i>
								<input type="email" class="validate" id="email" name="email" required>
					          	<label for="email">Email</label>
							</div>
							
							<div class="input-field col s12">
								<i class="material-icons prefix">lock</i>
								<input type="password" class="validate" id="password" name="password" required>
				          		<label for="password">Password</label>
							</div>
					
						</div>
						
						<c:if test='${model.failed > 0}'>
							<div class="row center-align">
								<font color="red">Las credenciales introducidas son incorrectas. Por favor, inténtelo de nuevo</font>
							</div>
						</c:if>
					
					</div>
					
					<div class="card-action right-align hide-on-small-only">
					
						<button type="button" class="btn-flat grey lighten-1 waves-effect" onclick="window.location='login/restore-password.html';"><i class="material-icons left">lock_open</i>Restablecer password</button>
						<button type="submit" class="btn waves-effect green darken-1" formmethod="POST"><i class="material-icons left">send</i>Entrar</button>
						
					</div>
			
					<div class="card-action right-align hide-on-med-and-up">
					
						<button type="button" class="row btn-flat grey lighten-1 waves-effect col s12" onclick="window.location='login/restore-password.html';"><i class="material-icons left">lock_open</i>Restablecer password</button>
						<button type="submit" class="row btn waves-effect green darken-1 col s12" formmethod="POST"><i class="material-icons left">send</i>Entrar</button>
						
					</div>
					
					<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
			
				</form>
			
			</div>
		
		</div>
	
	</body>

</html>