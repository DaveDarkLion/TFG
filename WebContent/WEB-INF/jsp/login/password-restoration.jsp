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
		<title>Restaurar password</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>

		<div class="valign-wrapper row login-box">
	
			<div class="col card hoverable s12 m8 pull-m2 l6 pull-l3">
				
				<form accept-charset="ISO-8859-1">
						
					<div class="card-content">
					
		        		<span class="card-title">Restablecer password</span>
		        			
		        		<div class="row">	
		        		
							<div class="input-field col s12">
								<i class="material-icons prefix">email</i>
								<input type="email" class="validate" id="email" name="email" required>
					          	<label for="email">Email</label>
							</div>
					
						</div>
						
						<c:if test='${model.emailNotInDB > 0}'>
							<div class="row center-align">
								<font color="red">El email introducido no corresponde a ningún usuario</font>
							</div>
						</c:if>
						<c:if test='${model.notEnoughTime > 0}'>
							<div class="row center-align">
								<font color="red">El tiempo transcurrido desde la última restauración de password es demasiado corto</font>
							</div>
						</c:if>
					
					</div>
					
					<div class="card-action right-align hide-on-small-only">
					
						<button class="btn waves-effect green darken-1" type="submit" formmethod="POST"><i class="material-icons left">send</i>Restablecer</button>
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">
					
						<button class="row btn waves-effect green darken-1 col s12" type="submit" formmethod="POST"><i class="material-icons left">send</i>Restablecer</button>
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
			
				</form>
			
			</div>
		
		</div>
	
	</body>

</html>