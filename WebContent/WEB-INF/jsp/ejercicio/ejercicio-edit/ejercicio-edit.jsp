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
		
			<div class="col card hoverable s12 m12 l8 pull-l2">
				
				<form id="form_main" enctype="multipart/form-data" method="POST" onload="getAllArchivosData(false)">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title"></span>
		        			
		        		<div class="row">
							
							<div class="input-field col s12">
								<label>
									<input type='checkbox' class="filled-in checkbox-indigo" id='visible' name='visible' value='visible'>
									<span id="visible_span" class="text-strong">Visible</span>
								</label>
							</div>
							
							<div class="row col s12"></div>
							<div class="row col s12"></div>
							
							<div class="input-field col s12">
								<input type="text" class="validate" id="titulo" name="titulo" maxlength="90" required>
								<label for="titulo">Título</label>
							</div>
							
							<div class="input-field col s12">
								<textarea class="materialize-textarea textarea-multirow validate" id="enunciado" name="enunciado" required>${model.enunciado}</textarea>
								<label for="enunciado" class="active">Enunciado</label>	
							</div>
							
							<div class="input-field col s12 m6 l4">
								<%@ include file='/WEB-INF/jsp/fragments/profesor-select.jspf' %>
								<label class="active" for="profesor_email">Profesor</label>
							</div>
							
							<div class="input-field col s12 m6 l8">
								<%@ include file='/WEB-INF/jsp/ejercicio/fragments/categorias-select-multi.jspf' %>
								<label class="active" for="categorias">Categorías</label>
							</div>
							
							<div class="input-field col s12 m6 l4">
								<%@ include file='/WEB-INF/jsp/fragments/dificultad-select.jspf' %>
								<label class="active" for="dificultad">Dificultad según el profesor</label>
							</div>
							
							<c:if test='${model.isNew <= 0}'>
							
								<div class="input-field col s12 m6 l4">
									<input type="text" id="dificultad_alumno" readonly>
									<label for="dificultad_alumno">Dificultad según los alumnos (${model.dificultadAlumnoVotes} votos)</label>
								</div>
								
							</c:if>
							
							<div class="input-field col s12">
							
								<ul class="collapsible">
									<li id="collapsible">
									
										<div class="collapsible-header">Archivos<i id="icon" class="material-icons right">expand_more</i></div>
										
										<div class="collapsible-body">
										
											<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-edit/fragments/archivos-edit.jspf' %>
											
										</div>
										
									</li>
									
								</ul>
								
							</div>
							
							<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">
						
						<c:if test='${model.isNew <= 0}'>
						
							<button class="btn modal-trigger waves-effect red darken-1" data-target="modal1" id="delete"><i class="material-icons center">delete</i></button>
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Aceptar cambios</button>
							
						</c:if>
						
						<c:if test='${model.isNew > 0}'>
						
							<button type="submit" class="btn waves-effect green darken-1"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
						
					</div>
					
					<div class="card-action hide-on-med-and-up">
						
						<c:if test='${model.isNew <= 0}'>
						
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
	
	<div id="modal1" class="modal modal-med-large">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este ejercicio?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept" formmethod="POST">Si</button>
		</div>
	</div>
	
	<div id="modal1_small" class="modal modal-small">
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este ejercicio?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel_small" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect waves-green btn-flat" id="delete_accept_small" formmethod="POST">Si</button>
		</div>
	</div>
	
	<script type="text/javascript">
	
		var ejercicioData = JSON.parse('${model.ejercicioData}');

		if (!objectIsEmpty(ejercicioData)) {
		
			document.getElementById('visible').checked = ejercicioData.visible;
			document.getElementById('titulo').value = ejercicioData.titulo;
			document.getElementById('enunciado').value = ejercicioData.enunciado;
			
			var dificultadAlumnoAverageData = JSON.parse('${model.dificultadAlumnoAverageData}');
			
			var dificultadAlumno = document.getElementById('dificultad_alumno');
			
			if ('${model.dificultadAlumnoVotes}' > 0) dificultadAlumno.value = dificultadAlumnoAverageData.nombre;
			else dificultadAlumno.value = "Sin votos";
			
			document.getElementById("delete_accept").setAttribute("onclick", "submitForm('form_main', 'POST', '" + ejercicioData.id + "/delete.html');");
			document.getElementById("delete_accept_small").setAttribute("onclick", "submitForm('form_main', 'POST', '" + ejercicioData.id + "/delete.html');");
			
			if (ejercicioData.visibleForUnprivileged > 0 && ejercicioData.visible <= 0)
				document.getElementById("visible_span").innerHTML += " <font color='#e65100'>(actualmente visible)</font>"
			
		}
		
		var title = document.getElementById("title");
		var cardTitle = document.getElementById("card_title");
		
		var description;
		
		if ('${model.isNew}' <= 0) {
			
			description = "Editar ";
			
		}
		
		else description = "Crear ";
		description += "ejercicio";
		
		title.innerHTML = description;
		cardTitle.innerHTML = description;
		
		$(document).ready(function() {

			M.updateTextFields();
			M.textareaAutoResize($('#enunciado'));
			$('.modal').modal();
			
		}); 
		
	</script>

</html>