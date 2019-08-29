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
		<title>${model.setName}</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>

		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12">
				
				<form id="form_main">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title">${model.setName}</span>
		        			
		        		<div class="row">
		
							<c:if test='${model.set > 0}'>
		
								<c:if test='${model.setType > 1}'>
								
									<c:if test='${model.isProfesor > 0 || model.isAdministrador > 0}'>
								
										<ul class="collapsible col s12">
										
											<li>
				
												<div class="collapsible-header no-margin col s12">
													<div class="input-field col s6 right no-margin href-aligned text-size-default">Opciones</div>
												</div>
												
												<div class="collapsible-body no-margin row col s12">
												
													<c:if test='${model.abierto > 0}'>
												
														<div class="input-field col s12 no-margin">
												
															<label>
																<input type='checkbox' class="filled-in checkbox-indigo no-margin" id='edition_mode_checkbox' name='edition_mode_checkbox' onclick="submitForm('form_main', 'GET', '')" value='edition_mode_checkbox'>
																<span class="text-strong">Modo edición</span>
																
																<script type='text/javascript'>
																	if ('${model.editionMode}' > 0) document.getElementById('edition_mode_checkbox').setAttribute('checked', '');
																</script>
																
															</label>
															
														</div>
														
														<div class="row col s12"></div>
													
													</c:if>
												
													<div class="input-field col s12 no-margin">
													
														<button type="submit" class="btn-flat grey lighten-1 waves-effect text-strong" form="form_empty" formaction="${model.setNameLink}/${model.setValue}.html"><i class="material-icons left">edit</i>Editar atributos</button>
													
													</div>
												
												</div>
												
											</li>
											
										</ul>
										
									</c:if>
									
								</c:if>
							
								<div class="input-field col s12">
									<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-list/fragments/ejercicio-set-list.jspf' %>
								</div>
								
								<c:if test='${model.editionMode <= 0}'>
								
									<div class="input-field col s6 m3 l2">
										<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-list/fragments/document-select.jspf' %>
										<label class="active" for="document_id">Documento</label>
									</div>
								
									<div class="input-field col s6 m1 no-margin">
										<button type="submit" class="btn transparent waves-effect text-strong button-filter" id="document_button" formaction='${model.setNameLink}/${model.setValue}/document.html'><i class="material-icons center">file_download</i></button>
									</div>
									
									<script type="text/javascript">
									
										var ejerciciosSetData = JSON.parse('${model.ejerciciosSetData}');
										
										if (ejerciciosSetData.length == 0) document.getElementById("document_button").disabled = true;
									
									</script>
								
								</c:if>
								
								<div class="row col s12"></div>
								<div class="row col s12"></div>
								
							</c:if>
		
							<c:if test='${model.set <= 0 || model.editionMode > 0}'>
		
								<ul class="collapsible col s12">
								
									<li>
		
										<div class="collapsible-header no-margin col s12">
											<div class="input-field col s6 right no-margin href-aligned text-size-default">Filtros</div>
											<div class="input-field col s6 right-align">
												<button type="submit" class="btn transparent text-strong"><i class="material-icons center header-button">search</i></button>
											</div>
										</div>
										
										<div class="collapsible-body no-margin row col s12">
									
											<c:if test='${model.set > 0 && model.editionMode > 0}'>
											
												<div class="input-field no-margin col s12">
											
													<label>
														<input type='checkbox' class="filled-in checkbox-indigo no-margin" id='filter_set_not_current_checkbox' name='filter_set_not_current_checkbox' value='filter_set_not_current_checkbox'>
														<span class="text-strong">No mostrar en selección</span>
													</label>
													
													<script type='text/javascript'>
														if ('${model.filterSetNotCurrentEnabled}' > 0) document.getElementById('filter_set_not_current_checkbox').setAttribute('checked', '');
													</script>
													
												</div>
												
												<div class="row col s12"></div>
											
											</c:if>
									
											<div class="input-field no-margin col s12 m6 l3">
												<input type='text' id="filter_ejercicio" name="filter_ejercicio" value='${model.filterEjercicio}' />
												<label class="no-margin" for="filter_ejercicio">Título o enunciado</label>
											</div>
											
											<div class="input-field no-margin col s12 m6 l3">
												<%@ include file='/WEB-INF/jsp/fragments/profesor-select.jspf' %>
												<label class="no-margin active" for="profesor_email">Profesor</label>
											</div>
											
											<div class="input-field no-margin col s12 m6 l3">
												<%@ include file='/WEB-INF/jsp/fragments/dificultad-select.jspf' %>
												<label class="no-margin active" for="dificultad_id">Dificultad</label>
											</div>
											
											<div class="input-field no-margin col s12 m6 l3">
												<%@ include file='/WEB-INF/jsp/ejercicio/fragments/categorias-select-multi.jspf' %>
												<label class="no-margin active" for="categorias">Categorías</label>
											</div>
									
											<c:if test='${model.filterSetExists > 0}'>
										
												<div class="input-field no-margin col s6 m6 l2">
										
													<label>
														<input type='checkbox' class="filled-in checkbox-indigo no-margin" id='filter_set_checkbox' name='filter_set_checkbox' value='filter_set_checkbox'>
														<span class="text-strong">Filtrar por</span>
													</label>
													
													<script type='text/javascript'>
														if ('${model.filterSetEnabled}' > 0) document.getElementById('filter_set_checkbox').setAttribute('checked', '');
													</script>
													
												</div>
												
												<div class="input-field no-margin col s6 m6 l1">
										
													<label>
														<input type='checkbox' class="filled-in checkbox-indigo no-margin" id='filter_set_not_checkbox' name='filter_set_not_checkbox' value='filter_set_not_checkbox'>
														<span class="text-strong">No</span>
													</label>
													
													<script type='text/javascript'>
														if ('${model.filterSetNotEnabled}' > 0) document.getElementById('filter_set_not_checkbox').setAttribute('checked', '');
													</script>
													
												</div>
												
												<div class="row col s12 hide-on-large-only"></div>
												<div class="row col s12 hide-on-large-only"></div>
												
												<div class="input-field no-margin col s12 m6 l3">
													<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-list/fragments/set-type-select.jspf' %>
													<label class="no-margin active" for="set_type_id">Tipo</label>
												</div>
												
												<div class="input-field no-margin col s12 m6 l3">
													<%@ include file='/WEB-INF/jsp/fragments/titulacion-select.jspf' %>
													<label class="no-margin active" for="titulacion_id">Titulacion</label>
												</div>
												
												<div class="input-field no-margin col s6 m6 l1">
													<input type='number' id='filter_set_value_initial' name='filter_set_value_initial' min='${model.filterSetValueMin}' max='${model.filterSetValueMax}' value='${model.filterSetValueInitial}' />
													<label class="no-margin active" for=filter_set_value_initial>Desde</label>
												</div>
												
												<div class="input-field no-margin col s6 m6 l1">
													<input type='number' id='filter_set_value_final' name='filter_set_value_final' min='${model.filterSetValueMin}' max='${model.filterSetValueMax}' value='${model.filterSetValueFinal}' />
													<label class="no-margin active" for="filter_set_value_final">Hasta</label>
												</div>
											
											</c:if>
											
										</div>
								
									</li>
								
								</ul>
							
								<div class="input-field col s12">
									<%@ include file='/WEB-INF/jsp/ejercicio/ejercicio-list/fragments/ejercicio-list.jspf' %>
								</div>
							
							</c:if>
							
						</div>
						
					</div>
					
					<c:if test='${model.set <= 0 || model.editionMode > 0}'>
					
						<div class="card-action center-align">
						
							<%@ include file='/WEB-INF/jsp/fragments/pages-buttons.jspf' %>
							
							<div class="col s6 pull-s3 m2 l1 right">
								<%@ include file='/WEB-INF/jsp/fragments/page-sizes-select.jspf' %>
							</div>
							
						</div>
						
					</c:if>
					
					<%@ include file='/WEB-INF/jsp/fragments/criteria.jspf' %>
					
					<input type='hidden' name='set_type' value='${model.setType}'/>
					<input type='hidden' name='set_value' value='${model.setValue}'/>
					
					<input type='hidden' id='csrf' name='${_csrf.parameterName}' value='${_csrf.token}' disabled/>
					
				</form>
				
			</div>
			
		</div>
		
	</body>
	
	<form id="form_empty"></form>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$('.collapsible').collapsible();
			
		});
	
	</script>

</html>