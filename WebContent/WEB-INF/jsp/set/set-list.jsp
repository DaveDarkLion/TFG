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
		<title>${model.tableName}</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>

		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12">
				
				<form id="form_main">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title"></span>
		        			
		        		<div class="row">
		
							<ul class="collapsible col s12">
							
								<li>
		
									<div class="collapsible-header no-margin col s12">
										<div class="input-field col s6 right no-margin href-aligned text-size-default">Filtros</div>
										<div class="input-field col s6 right-align">
											<button type="submit" class="btn transparent text-strong"><i class="material-icons center header-button">search</i></button>
										</div>
									</div>
									
									<div class="collapsible-body no-margin row col s12">
					
										<div class="input-field no-margin col s12 m6 l3">
											<input type='text' id="filter_set" name="filter_set" value='${model.filterSet}' />
											<label class="no-margin" for="filter_set">Descripción</label>
										</div>
										
										<div class="input-field no-margin col s12 m6 l3">
											<%@ include file='/WEB-INF/jsp/fragments/profesor-select.jspf' %>
											<label class="no-margin active" for="profesor_email">Profesor</label>
										</div>

										<div class="input-field no-margin col s12 m6 l3">
											<%@ include file='/WEB-INF/jsp/fragments/titulacion-select.jspf' %>
											<label class="no-margin active" for="titulacion_id">Titulacion</label>
										</div>
										
										<div class="input-field no-margin col s12 m6 l3">
											<%@ include file='/WEB-INF/jsp/ejercicio/fragments/categorias-select-multi.jspf' %>
											<label class="no-margin active" for="categorias">Categorías</label>
										</div>
										
										<div class="input-field no-margin col s6 m3">
											<input type='number' id='filter_set_value_initial' name='filter_set_value_initial' min='${model.filterSetValueMin}' max='${model.filterSetValueMax}' value='${model.filterSetValueInitial}' />
											<label class="no-margin active" for=filter_set_value_initial>Desde</label>
										</div>
										
										<div class="input-field no-margin col s6 m3">
											<input type='number' id='filter_set_value_final' name='filter_set_value_final' min='${model.filterSetValueMin}' max='${model.filterSetValueMax}' value='${model.filterSetValueFinal}' />
											<label class="no-margin active" for="filter_set_value_final">Hasta</label>
										</div>
										
									</div>
										
								</li>
									
							</ul>
							
							<div class="input-field col s12">
								<%@ include file='/WEB-INF/jsp/set/fragments/set-list.jspf' %>
							</div>
							
						</div>
						
					</div>
					
					<div class="card-action center-align">
					
						<%@ include file='/WEB-INF/jsp/fragments/pages-buttons.jspf' %>
						
						<div class="col s6 pull-s3 m2 l1 right">
							<%@ include file='/WEB-INF/jsp/fragments/page-sizes-select.jspf' %>
						</div>
						
					</div>
					
					<%@ include file='/WEB-INF/jsp/fragments/criteria.jspf' %>
					
				</form>
				
			</div>
			
		</div>
	
	</body>
	
	<form id="form_empty"></form>
	
	<script type="text/javascript">
	
		var cardTitle = document.getElementById("card_title");
		
		cardTitle.innerHTML = "Listado de " + ("${model.tableName}").toLowerCase();
	
		$(document).ready(function(){
			
			$('.collapsible').collapsible();
			
		});
		
	</script>

</html>