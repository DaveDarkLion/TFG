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
		
			<div class="col card hoverable s12 m10 pull-m1 l6 pull-l3">
				
				<form id="form_main" method="POST" accept-charset="ISO-8859-1">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title"></span>
		        			
		        		<div class="row">
		
							<div class="input-field col s12 m6 l7">
								<input type="text" class="validate" id="nombre" name="nombre" maxlength="90" required>
								<label for="nombre">Nombre</label>
							</div>
							
							<div class="input-field col s12 m6 l5">
								<input type="text" id="profesor" name="profesor" required readonly>
								<label for="profesor">Profesor</label>
							</div>
							
							<div class="input-field col s12">
								<textarea class="materialize-textarea textarea-multirow-idea validate" id="idea_text" name="idea_text" required></textarea>
								<label for="idea_text" class="active">Idea</label>
							</div>
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">
					
						<c:if test='${model.isNew <= 0}'>
						
							<button class="btn transparent" form="form_ejercicio_add" formaction='/ejercicios-programacion/ejercicios/add.html'><i class="material-icons center" style="color:green">open_in_new</i></button>
							<button class="btn modal-trigger waves-effect red darken-1" data-target="modal1" id="delete"><i class="material-icons center">delete</i></button>
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test='${model.isNew > 0}'>
						
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">
					
						<c:if test='${model.isNew <= 0}'>
						
							<button class="row btn transparent col s12 text-strong" form="form_ejercicio_add" formaction='/ejercicios-programacion/ejercicios/add.html'><i class="material-icons left" style="color:green">open_in_new</i>Crear ejercicio</button>
							<button class="row btn modal-trigger waves-effect red darken-1 col s12" data-target="modal1_small" id="delete_small"><i class="material-icons left">delete</i>Eliminar</button>
							<button type="submit" class="row btn waves-effect green darken-1 col s12"><i class="material-icons left">send</i>Aceptar</button>
							
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
	
	<form id="form_ejercicio_add">
	
		<input type="hidden" name="idea" value="${model.id}" />
	
	</form>
	
	<div id="modal1" class="modal modal-med-large">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar esta idea?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept" formmethod="POST">Si</button>
		</div>
	</div>
	
	<div id="modal1_small" class="modal modal-small">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar esta idea?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel_small" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept_small" formmethod="POST">Si</button>
		</div>
	</div>
	
	<script type='text/javascript'>
		
			var data = JSON.parse('${model.data}');
			
			if (!objectIsEmpty(data)) {
			
				document.getElementById('nombre').value = data.nombre;
				document.getElementById('idea_text').value = data.ideaText;
				document.getElementById("profesor").value = getDisplayNombre(data.profesor.persona);
				
			}
			
			else {
				
				var profesorData = JSON.parse('${model.profesorData}');
				
				var profesor = document.getElementById("profesor").value = getDisplayNombre(profesorData.persona);
				
			}
			
			var title = document.getElementById("title");
			var cardTitle = document.getElementById("card_title");
			
			var description;
			
			if ('${model.isNew}' <= 0) {
				
				document.getElementById("delete_accept").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
				document.getElementById("delete_accept_small").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
				
				description = "Editar ";
				
			}
			
			else description = "Crear ";
			description += "idea";
			
			title.innerHTML = description;
			cardTitle.innerHTML = description;
			
			$(document).ready(function() {

				M.updateTextFields();
				M.textareaAutoResize($('#idea'));
				$('.modal').modal();
				
			}); 
			
		</script>
	
</html>