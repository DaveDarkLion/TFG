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
					
		        		<span class="card-title" id="card_title"></span>
		        			
		        		<div class="row">	
		
							<div class="input-field col s12">
								<input type="email" class="validate" id="email" name="email" required>
								<label for="email">Email</label>
								
								<script type='text/javascript'>
								
									if ("${model.isNew}" <= 0 || "${isProfile}" > 0) {
										
										var email = document.getElementById("email");
										email.readonly = true;
										email.className = "";
										
									}
									
								</script>
								
							</div>
							
							<div class="input-field col s12 m12 l4">
								<input type="text" class="validate" id="nombre" name="nombre" maxlength="50" required>
								<label for="nombre">Nombre</label>
							</div>
							
							<div class="input-field col s12 m12 l4">
								<input type="text" class="validate" id="apellido1" name="apellido1" maxlength="50" required>
								<label for="apellido1">Primer apellido</label>
							</div>
							
							<div class="input-field col s12 m12 l4">
								<input type="text" class="validate" id="apellido2" name="apellido2" maxlength="50" required>
								<label for="apellido2">Segundo apellido</label>
							</div>
							
							<c:if test='${model.isProfile <= 0}'>
							
								<div class="input-field col s12 m12 l8">
									<%@ include file='/WEB-INF/jsp/admin/user/fragments/roles-select-multi.jspf' %>
									<label class="active" for="roles_id">Roles</label>
								</div>
							
							</c:if>
							
							<c:if test='${model.isProfile > 0}'>
							
								<div class="input-field col s12 m12 l8">
									<input type="text" id="roles_id" name="roles_id[]" readonly>
									<label class="active" for="roles_id">Roles</label>
								</div>
							
								<script type="text/javascript">
								
									var rolesCurrentData = JSON.parse('${model.rolesCurrentData}');
									var text = document.getElementById("roles_id");
									
									for (var i = 0; i < rolesCurrentData.length - 1; i++) text.value += rolesCurrentData[i].name + ", ";
									text.value += rolesCurrentData[rolesCurrentData.length-1].name;
								
								</script>
							
							</c:if>
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">
					
						<c:if test="${model.isNew > 0}">
						
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
					
						<c:if test="${model.isNew <= 0 && model.isProfile <= 0}">
						
							<c:if test="${model.canDelete > 0}">
						
								<button class="btn modal-trigger waves-effect red darken-1" data-target="modal1" id="delete"><i class="material-icons center">delete</i></button>
							
							</c:if>
							
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test="${model.isProfile > 0}">
						
							<button type="button" class="btn-flat grey lighten-1 waves-effect text-strong" onclick="window.location='/ejercicios-programacion/profile/password.html';"><i class="material-icons left">lock</i>Actualizar password</button>
							
						</c:if>
						
						<input type='hidden' name="${_csrf.parameterName}" value="${_csrf.token}" />
						
					</div>
					
					<div class="card-action hide-on-med-and-up">
					
						<c:if test="${model.isNew > 0}">
						
							<button type="submit" class="btn waves-effect green darken-1 col s12"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
					
						<c:if test="${model.isNew <= 0 && model.isProfile <= 0}">
						
							<c:if test="${model.canDelete > 0}">
						
								<button class="row btn modal-trigger waves-effect red darken-1 col s12" data-target="modal1_small" id="delete_small"><i class="material-icons left">delete</i>Eliminar</button>
							
							</c:if>
							
							<button type="submit" class="row btn waves-effect green darken-1 col s12"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test="${model.isProfile > 0}">
						
							<button type="button" class="row btn-flat grey lighten-1 waves-effect col s12" onclick="window.location='/ejercicios-programacion/profile/password.html';"><i class="material-icons left">lock</i>Actualizar password</button>
							
						</c:if>
						
						<input type='hidden' name="${_csrf.parameterName}" value="${_csrf.token}" />
						
					</div>
					
				</form>
				
			</div>
			
		</div>
	
	</body>
	
	<div id="modal1" class="modal modal-med-large">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este usuario?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept" formmethod="POST">Si</button>
		</div>
	</div>
	
	<div id="modal1_small" class="modal modal-small">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este usuario?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel_small" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept_small" formmethod="POST">Si</button>
		</div>
	</div>
	
	<script type='text/javascript'>
	
		var data = JSON.parse('${model.data}');
		
		var email = document.getElementById("email");
		var nombre = document.getElementById("nombre");
		var apellido1 = document.getElementById("apellido1");
		var apellido2 = document.getElementById("apellido2");
		
		if (!objectIsEmpty(data)) {
			
			email.value = data.email;
			email.readOnly = true;
			nombre.value = data.nombre;
			apellido1.value = data.apellido1;
			apellido2.value = data.apellido2;
			document.getElementById("delete_accept").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.email + "/delete.html');");
			document.getElementById("delete_accept_small").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.email + "/delete.html');");
			
			if ("${model.isProfile}" > 0) {
				
				email.readOnly = true;
				email.className = "";
				nombre.readOnly = true;
				nombre.className = "";
				apellido1.readOnly = true;
				apellido1.className = "";
				apellido2.readOnly = true;
				apellido2.className = "";
				
			}
			
		}
		
		var title = document.getElementById("title");
		var cardTitle = document.getElementById("card_title");
		
		var description;
		
		if ("${model.isProfile}" > 0) description = "Perfil";
		else if ("${model.isNew}" > 0) description = "Crear usuario";
		else description = "Editar usuario";
	
		title.innerHTML = description;
		cardTitle.innerHTML = description;
		
		$(document).ready(function() {

			$('.modal').modal();
			$('select').formSelect();
			
		});
		
	</script>

</html>