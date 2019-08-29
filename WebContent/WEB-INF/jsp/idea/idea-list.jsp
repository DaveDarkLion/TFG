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
		<title>Listado de ideas</title>
		
	</head>
	
	<body>
	
		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
	
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m8 pull-m2 l6 pull-l3">
				
				<form id="form_main">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title">Listado de ideas</span>
		        			
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
		
										<div class="input-field no-margin col s12 m6">
											<input type='text' id="filter_idea" name="filter_idea" value='${model.filterIdea}' />
											<label class="no-margin" for="filter_idea">Texto</label>
										</div>
										
										<div class="input-field col s12 m6">
										
											<label>
												<input type='checkbox' class="row filled-in checkbox-indigo" id='filter_show_all' name="filter_show_all" value="filter_show_all" />
												<span class="text-strong">Mostrar todas</span>
											</label>
								
											<script type='text/javascript'>
												if ("${model.filterShowAll}" > 0) document.getElementById("filter_show_all").setAttribute("checked", "");
											</script>
											
										</div>
										
										<div class="row col s12 hide-on-med-and-up"></div>
										
									</div>
									
								</li>
								
							</ul>
							
							<div class="input-field col s12">
							
					      		<ul class="collection text-size-default" id='idea_list'>
					      		</ul>
					      		
					      	</div>
							
						</div>
						
					</div>
					
					<div class="card-action center-align">
					
						<%@ include file='/WEB-INF/jsp/fragments/pages-buttons.jspf' %>
						
						<div class="col s6 pull-s3 m2 l2 right">
							<%@ include file='/WEB-INF/jsp/fragments/page-sizes-select.jspf' %>
						</div>
						
					</div>
					
				</form>
				
			</div>
			
		</div>
	
	</body>
	
	<script type='text/javascript'>
				
		var ideasData = JSON.parse('${model.ideasData}');
	
		var list = document.getElementById('idea_list');
			
		var ideasArray = new Array();
		
		for (i = 0; i < ideasData.length; i++) {
			
			ideasArray[i] = new Object();
			ideasArray[i] = '<a href="ideas/' + ideasData[i].id + '.html">' + ideasData[i].nombre + '</a> <font size="1">' + getDisplayNombre(ideasData[i].profesor.persona) + '</font>';
				
		}
		
		listDisplay(list, ideasArray)
			
		$(document).ready(function(){
		
			$('.collapsible').collapsible();
			
		});
		
	</script>
	
</html>