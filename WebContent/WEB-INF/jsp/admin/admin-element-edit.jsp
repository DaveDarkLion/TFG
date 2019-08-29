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
		<title id="title"></title>
		
	</head>
	
	<body>
	
		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m8 pull-m2 l6 pull-l3">
				
				<form id="form_main" method="POST" accept-charset="ISO-8859-1">
						
					<div class="card-content">
					
		        		<span id="card_title" class="card-title"></span>
		        			
		        		<div class="row">	
		        		
		        			<c:if test='${model.isDificultad <= 0}'>
		        		
								<div class="input-field col s12">
									<input type="text" class="validate" id="nombre" name="nombre" maxlength="50" required>
						          	<label for="nombre">Nombre</label>
								</div>
								
							</c:if>
								
							<c:if test='${model.isDificultad > 0}'>
							
								<div class="input-field col s12 m9">
									<input type="text" class="validate" id="nombre" name="nombre" maxlength="50" required>
						          	<label for="nombre">Nombre</label>
								</div>
							
								<div class="input-field col s12 m3">
									<input type="number" class="validate center-align" id="valor" name="valor" step="0.5" min="0" max="10" value="5" required>
					          		<label for="valor">Valor</label>
								</div>
								
							</c:if>
					
						</div>
						
					</div>
						
					<div class="card-action right-align hide-on-small-only">
					
						<c:if test='${model.isNew <= 0}'>
						
							<button class="btn modal-trigger waves-effect red darken-1" data-target="modal1" id="delete"><i class="material-icons center">delete</i></button>
							
							<script type='text/javascript'>
								if ('${model.canDelete}' <= 0) document.getElementById('delete').disabled = true;
							</script>
							
							<button type="submit" class="hide-on-small-only btn waves-effect green darken-1"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test='${model.isNew > 0}'>
						
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">
					
						<c:if test='${model.isNew <= 0}'>
						
							<button class="row btn modal-trigger waves-effect red darken-1 col s12" data-target="modal1_small" id="delete_small"><i class="material-icons left">delete</i>Eliminar</button>
							
							<script type='text/javascript'>
								if ('${model.canDelete}' <= 0) document.getElementById('delete_small').disabled = true;
							</script>
							
							<button type="submit" class="row btn waves-effect green darken-1 col s12"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test='${model.isNew > 0}'>
						
							<button type="submit" class="row btn waves-effect green darken-1 col s12"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
						
				</form>
						
			</div>
						
		</div>
	
	</body>
	
	<div id="modal1" class="modal modal-med-large">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept" formmethod="POST">Si</button>
		</div>
		
	</div>
	
	<div id="modal1_small" class="modal modal-small">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent" id="delete_cancel_small" formmethod="POST">No</button>
			<button type="submit" class="transparent btn-flat" id="delete_accept_small" formmethod="POST">Si</button>
		</div>
		
	</div>
	
	<script type='text/javascript'>
		
		var data = JSON.parse('${model.data}');
		
		if (!objectIsEmpty(data)) {
		
			document.getElementById('nombre').value = data.nombre;
			
			if ("${model.canDelete}" > 0) {
				
				document.getElementById("delete_accept").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
				document.getElementById("delete_accept_small").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
				
			}
			
			if ("${model.isDificultad}" > 0) document.getElementById("valor").value = data.valor;
			
		}
		
		var title = document.getElementById("title");
		var cardTitle = document.getElementById("card_title");
		
		var description;
		
		if ("${model.isNew}" > 0) description = "Crear ";
		else description = "Editar ";
		
		description += ("${model.title}").toLowerCase();
		
		title.innerHTML = description;
		cardTitle.innerHTML = description;
		
		$(document).ready(function() {

			$('.modal').modal();
			
		}); 
		
	</script>
	
</html>