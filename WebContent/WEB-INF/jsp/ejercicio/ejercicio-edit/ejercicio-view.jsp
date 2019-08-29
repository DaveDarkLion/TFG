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
		<title>Ver ejercicio</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m12 l8 pull-l2">
				
				<form id="form_main" method="POST" accept-charset="ISO-8859-1" onload="getAllArchivosData(false)">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title">Ver ejercicio</span>
		        			
		        		<div class="row">
							
							<div class="input-field col s12">
								<input type="text" id="titulo" name="titulo" maxlength="90" readonly>
								<label for="titulo">Título</label>
							</div>
							
							<div class="input-field col s12">
								<textarea class="materialize-textarea textarea-multirow" id="enunciado" name="enunciado" readonly></textarea>
								<label for="enunciado" class="active">Enunciado</label>
							</div>
							
							<div class="input-field col s12 m4">
								<input type="text" id="profesor" readonly>
								<label for="profesor">Profesor</label>
							</div>
							
							<div class="input-field col s12 m8">
								<input type="text" id="categorias" readonly>
								<label class="active" for="categorias">Categorías</label>
							</div>
						
							<script type="text/javascript">
							
								var categoriasCurrentData = JSON.parse('${model.categoriasCurrentData}');
								var text = document.getElementById("categorias");
								
								for (var i = 0; i < categoriasCurrentData.length - 1; i++) text.value += categoriasCurrentData[i].nombre + ", ";
								text.value += categoriasCurrentData[categoriasCurrentData.length-1].nombre;
							
							</script>
							
							<div class="input-field col s12 m4">
								<input type="text" id="dificultad" readonly>
								<label for="dificultad">Dificultad según el profesor</label>
							</div>
							
							<div class="input-field col s12 m4">
								<input type="text" id="dificultad_alumno" readonly>
								<label for="dificultad_alumno">Dificultad según los alumnos (${model.dificultadAlumnoVotes} votos)</label>
							</div>
							
							<c:if test='${model.isAlumno > 0}'>
							
								<div class="input-field col s12 m4">
									<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-edit/fragments/dificultad-alumno-select.jspf' %>
									<label class="active" for="dificultad_alumno_id">Mi dificultad</label>
								</div>
								
							</c:if>
							
							<div class="input-field col s12">
							
								<ul class="collapsible">
									<li id="collapsible">
									
										<div class="collapsible-header">Archivos<i id="icon" class="material-icons right">expand_more</i></div>
										
										<div class="collapsible-body">
										
											<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-edit/fragments/archivos-view.jspf' %>
											
										</div>
										
									</li>
									
								</ul>
								
							</div>
							
							<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">
						
						<button type="submit" class="btn waves-effect transparent" id="cart" formmethod="POST" formaction="cart.html"><i id="cart_icon" class="material-icons center"></i></button>
					
						<script type="text/javascript">
							
							var cartIcon = document.getElementById("cart_icon");
								
							if ("${model.inCart}" > 0) {
									
								cartIcon.style = "color: red";
								cartIcon.innerHTML = "remove_shopping_cart";
									
							}
								
							else {
									
								cartIcon.style = "color: green";
								cartIcon.innerHTML = "add_shopping_cart";
									
							}
							
						</script>
						
						<c:if test='${model.isProfesor > 0 || model.isAdmin > 0}'>
							<button type="submit" class="btn waves-effect green darken-1" id="edit" form="form_empty" formmethod="GET" formaction="edit.html"><i class="material-icons center">edit</i></button>
						</c:if>
						
					</div>
					
					<div class="card-action hide-on-med-and-up">
						
						<button type="submit" class="row btn waves-effect green darken-1 col s12" id="cart_small" formmethod="POST" formaction="cart.html"><i id="cart_icon_small" class="material-icons left"></i></button>
					
						<script type="text/javascript">
						
							var cart = document.getElementById("cart_small");
							var cartIcon = document.getElementById("cart_icon_small");
							
							if ("${model.inCart}" > 0) {
								
								cart.classList.remove("green");
								cart.classList.add("red");
								cartIcon.innerHTML = "remove_shopping_cart";
								cart.innerHTML += "Eliminar de selección";
								
							}
							
							else {
								
								cart.classList.add("green");
								cart.classList.remove("red");
								cartIcon.innerHTML = "add_shopping_cart";
								cart.innerHTML += "Añadir a selección";
								
							}
						
						</script>
						
						<c:if test='${model.isProfesor > 0 || model.isAdmin > 0}'>
							<button type="submit" class="btn waves-effect green darken-1 col s12" id="edit_small" form="form_empty" formmethod="GET" formaction="edit.html"><i class="material-icons left">edit</i>Editar</button>
						</c:if>
						
					</div>
					
				</form>
				
			</div>
			
		</div>
		
	</body>
	
	<form id="form_empty"></form>
	
	<script type="text/javascript">
	
		var ejercicioData = JSON.parse('${model.ejercicioData}');
		var dificultadAlumnoAverageData = JSON.parse('${model.dificultadAlumnoAverageData}');
		
		document.getElementById("titulo").value = ejercicioData.titulo;
		document.getElementById("enunciado").value = ejercicioData.enunciado;
		document.getElementById("profesor").value = getDisplayNombre(ejercicioData.profesor.persona);
		document.getElementById("dificultad").value = ejercicioData.dificultad.nombre;
		if ("${model.dificultadAlumnoVotes}" > 0) document.getElementById("dificultad_alumno").value = dificultadAlumnoAverageData.nombre;
		else document.getElementById("dificultad_alumno").value = "Sin votos";
	
	</script>

</html>