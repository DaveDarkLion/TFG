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
							
							<c:if test='${model.isNew > 0}'>
							
								<div class="input-field col s12">
									<label>
										<input type='checkbox' class="filled-in checkbox-indigo" id='use_cart' name='use_cart' value='use_cart'>
										<span class="text-strong">Crear a partir de mi selección</span>
									</label>
								</div>
								
								<script type="text/javascript">
									if ("${model.useCart}" > 0) document.getElementById("use_cart").checked = true;
								</script>
								
								<div class="row col s12"></div>
								
							</c:if>
							
							<div class="input-field col s6 m12">
								<label>
									<input type='checkbox' class="filled-in checkbox-indigo" id='abierto' name='abierto' value='abierto'>
									<span class="text-strong">Abierto</span>
								</label>
							</div>
							
							<div class="input-field col s6 m12">
								<label>
									<input type='checkbox' class="filled-in checkbox-indigo" id='visible' name='visible' value='visible'>
									<span class="text-strong">Visible</span>
								</label>
							</div>
							
							<div class="row col s12"></div>
							<div class="row col s12"></div>
							
							<div class="input-field col s12 m12">
								<input type="text" class="validate" id="descripcion" name="descripcion" maxlength="90" required>
								<label for="descripcion">Descripción</label>
							</div>

							<div class="input-field col s12 m6">
								<%@ include file='/WEB-INF/jsp/fragments/titulacion-select.jspf' %>
								<label class="active" for="titulacion_id">Titulación</label>
							</div>
							
							<div class="input-field col s6 m3 ">
								<input type="number" class="validate" id="mes" name="mes" min=1 max=12 value=1 required>
								<label for="mes">Mes</label>
							</div>
							
							<div class="input-field col s6 m3">
								<input type="number" class="validate" id="ano" name="ano" min="${model.anoMin}" max="${model.anoMax}" value="${model.anoMin}" required>
								<label for="ano">Año</label>
							</div>
							
						</div>
						
					</div>
					
					<div class="card-action right-align hide-on-small-only">
					
						<c:if test="${model.isNew > 0}">
						
							<button type="button" class="btn green darken-1"onclick="checkVisible('modal_visible')"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<c:if test="${model.isNew <= 0}">
						
							<button type="button" class="btn modal-trigger waves-effect red darken-1" data-target="modal1" id="delete"><i class="material-icons center">delete</i></button>
							
							<button type="button" class="btn green waves-effect darken-1" onclick="checkAbierto('modal_abierto', 'modal_visible')"><i class="material-icons left">send</i>Aceptar cambios</button>

						</c:if>

						<input type='hidden' name="${_csrf.parameterName}" value="${_csrf.token}" />
						
					</div>
					
					<div class="card-action right-align hide-on-med-and-up">
					
						<c:if test="${model.isNew > 0}">
						
							<button type="button" class="row btn waves-effect green darken-1 col s12" onclick="checkVisible('modal_visible_small')"><i class="material-icons left">send</i>Crear</button>
							
						</c:if>
						
						<c:if test="${model.isNew <= 0}">
						
							<button type="button" class="row waves-effect btn modal-trigger red darken-1 col s12" data-target="modal1_small" id="delete_small"><i class="material-icons left">delete</i>Eliminar</button>
							<button type="button" class="row waves-effect btn green darken-1 col s12"  onclick="checkAbierto('modal_abierto_small', 'modal_visible_small')"><i class="material-icons left">send</i>Aceptar cambios</button>
						
						</c:if>
						
						<input type='hidden' name="${_csrf.parameterName}" value="${_csrf.token}" />
						
					</div>
					
				</form>
				
			</div>
			
		</div>
		
	</body>
	
	<div id="modal_abierto" class="modal modal-med-large">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea reabrir este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent">No</button>
			<button type="submit" class="modal-close btn-flat" onclick="checkVisible('modal_visible')">Si</button>
		</div>
		
	</div>
	
	<div id="modal_visible" class="modal modal-med-large">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea hacer visible este elemento? Todos los ejercicios que contenga serán visibles mientras este lo sea</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent">No</button>
			<button type="submit" class="modal-close btn-flat" onclick="submitForm('form_main', 'POST', '')">Si</button>
		</div>
		
	</div>
	
	<div id="modal_abierto_small" class="modal modal-small">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea reabrir este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent">No</button>
			<button type="submit" class="modal-close btn-flat" onclick="checkVisible('modal_visible_small')">Si</button>
		</div>
		
	</div>
	
	<div id="modal_visible_small" class="modal modal-small">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea hacer visible este elemento? Todos los ejercicios que contenga serán visibles mientras este lo sea</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent">No</button>
			<button type="submit" class="modal-close btn-flat" onclick="submitForm('form_main', 'POST', '')">Si</button>
		</div>
		
	</div>
	
	<div id="modal1" class="modal modal-med-large">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat white" id="delete_cancel" formmethod="POST">No</button>
			<button type="submit" class="modal-close waves-effect btn-flat" id="delete_accept" formmethod="POST">Si</button>
		</div>
		
	</div>
	
	<div id="modal1_small" class="modal modal-small">
	
		<div class="modal-content">
			<p>¿Está seguro de que desea eliminar este elemento?</p>
		</div>
		<div class="modal-footer">
			<button type="submit" class="modal-close btn-flat transparent" id="delete_cancel_small" formmethod="POST">No</button>
			<button type="submit" class="btn-flat transparent" id="delete_accept_small" formmethod="POST">Si</button>
		</div>
		
	</div>
	
	<script type='text/javascript'>
	
		function checkAbierto (modalAbierto, modalVisible) {
			
			var data = JSON.parse('${model.data}');
			
			if (!data.abierto && document.getElementById("abierto").checked) {
				
				const elem = document.getElementById(modalAbierto);
				const instance = M.Modal.init(elem, {dismissible: false});
				instance.open();
				
			}
			
			else checkVisible(modalVisible);
			
		}
		
		function checkVisible (modal) {
			
			var data = JSON.parse('${model.data}');
			
			if (!data.visible && document.getElementById("visible").checked) {
				
				const elem = document.getElementById(modal);
				const instance = M.Modal.init(elem, {dismissible: false});
				instance.open();
				
			}
			
			else submitForm("form_main", "POST", "");
			
		}
	
	</script>
	
	<script type='text/javascript'>
		
		var data = JSON.parse('${model.data}');
			
		var title = document.getElementById("title");
		var cardTitle = document.getElementById("card_title");
		
		var description;
		
		if (!objectIsEmpty(data)) {
				
			if (data.abierto > 0) document.getElementById("abierto").setAttribute("checked", "");
			if (data.visible > 0) document.getElementById("visible").setAttribute("checked", "");
			document.getElementById("descripcion").value = data.descripcion;
			document.getElementById("mes").value = data.mes;
			document.getElementById("ano").value = data.ano;
			document.getElementById("delete_accept").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
			document.getElementById("delete_accept_small").setAttribute("onclick", "submitForm('form_main', 'POST', '" + data.id + "/delete.html');");
			
			description = "Editar ";
			
		}
		
		else description = "Crear ";
		
		description += ("${model.title}").toLowerCase();
		
		title.innerHTML = description;
		cardTitle.innerHTML = description;
		
		$(document).ready(function() {

			$('.modal').modal();
			        
		}); 
		
	</script>

</html>