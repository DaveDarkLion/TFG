<html>

	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

	<script type='text/javascript' src='//code.jquery.com/jquery-1.10.1.min.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/common.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/data.js'></script>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/materialize.css'>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/mystyles.css'>
	<script type='text/javascript' src='/ejercicios-programacion/js/materialize.js'></script>
	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<head>
	
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
	
		<link href="/ejercicios-programacion/favicon.ico" rel="icon" type="image/x-icon" />
		<title>Actualizar password</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m8 pull-m2 l6 pull-l3">
				
				<form method="POST" accept-charset="ISO-8859-1">
						
					<div class="card-content">
					
		        		<span class="card-title">Introducir passwords</span>
		        			
		        		<div class="row">
		        		
							<div class="input-field col s12">
								<i class="material-icons prefix">lock</i>
								<input type="password" class="validate" id="pass_old" name="pass_old" required>
				          		<label for="pass_old">Password antiguo</label>
							</div>
							
							<c:if test='${model.wrongPassword > 0}'>
								<div class="input-field col s12">
									<font color="red">El password introducido es incorrecto</font>
								</div>
							</c:if>
							
							<div class="input-field col s12">
								<i class="material-icons prefix">lock</i>
								<input type="password" class="validate" id="pass_new1" name="pass_new1" name="pass_old" minlength="6" maxlength="10" required>
				          		<label for="pass_new1">Password nuevo</label>
							</div>
							
							<c:if test='${model.wrongPasswordFormat > 0}'>
								<div class="input-field col s12">
									<font color="red">El nuevo password debe tener entre 6 y 10 caracteres y tan solo puede incluír letras, números y el caracter '_'</font>
								</div>
							</c:if>
							
							<div class="input-field col s12">
								<i class="material-icons prefix">lock</i>
								<input type="password" class="validate" id="pass_new2" name="pass_new2" name="pass_old" minlength="6" maxlength="10" required>
				          		<label for="pass_new2">Repetir password</label>
							</div>
							
							<c:if test='${model.passwordsDoNotMatch > 0}'>
								<div class="input-field col s12">
									<font color="red">Los passwords no coinciden</font>
								</div>
							</c:if>
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">

						<button type="submit" class="btn waves-effect green darken-1" formmethod="POST"><i class="material-icons left">send</i>Actualizar</button>
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">

						<button type="submit" class="row btn waves-effect green darken-1 col s12" formmethod="POST"><i class="material-icons left">send</i>Actualizar</button>
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
				</form>
				
			</div>
			
		</div>
	
	</body>

</html>