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
		<title>Añadir usuarios</title>
		
	</head>

	<body>
		
		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m6 pull-m3 l4 pull-l4">
				
				<form id="form_main" enctype="multipart/form-data" method="POST">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title">Añadir usuarios desde archivo</span>
		        			
		        		<div class="row">
							
							<div class="input-field col s12">
			
								<label>Lista de usuarios</label>
					
	              				<div class="file-field input-field">
	              		
				                 	<div class="btn">
				                    	<span>Seleccionar</span>
				                    	<input type="file" id="file" name="file" required />
				                 	</div>
										
				                  	<div class="file-path-wrapper">
				                    	<input class="file-path validate" type="text" />
				                  	</div>
	                  	
	              				</div>
								
							</div>
							
						</div>
						
					</div>
						
					<div class="card-action right-align hide-on-small-only">
					
						<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Aceptar</button>
		
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">
					
						<button type="submit" class="btn waves-effect green darken-1 col s12 row"><i class="material-icons left">send</i>Aceptar</button>
		
					</div>
						
					<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
					
				</form>
				
			</div>
			
		</div>
	
	</body>
	
</html>