<html>

	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

	<script type='text/javascript' src='//code.jquery.com/jquery-1.10.1.min.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/common.js'></script>
	<script type='text/javascript' src='/ejercicios-programacion/js/data.js'></script>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/materialize.css'>
	<link rel='stylesheet' type='text/css' href='/ejercicios-programacion/css/mystyles.css'>
	<script type='text/javascript' src='/ejercicios-programacion/js/materialize.js'></script>
	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<head>
	
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
	
		<link href="/ejercicios-programacion/favicon.ico" rel="icon" type="image/x-icon" />
		<title>Información</title>
	
	</head>

	<body>
	
		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
	
			<div class="col card hoverable s12 m6 pull-m3 l4 pull-l4">
				
				<form>
						
					<div class="card-content">
					
		        		<span class="card-title">Información</span>
		
						<div class="card-action center-align">
			
							<div class="row">
			
								<c:if test='${model.userNumber <= 0}'>
								
									<div class="row input-field">
									
										No se han creado nuevos usuarios
										
									</div>
								
								</c:if>
			
								<c:if test='${model.userNumber > 0}'>
								
									<div class="row input-field">
								
										Se han creado ${model.userNumber} de los ${model.userNumberTotal} usuarios listados. Los usuarios creados han sido los siguientes:
									
									</div>
									
									<div class="input-field">
									
										<ul class="collection" id="users">
										</ul>
										
									</div>
								
								</c:if>
									
							</div>
							
						</div>
							
					</div>
					
					<div class="card-action center-align">
					
						<button type="submit" class="btn-flat grey lighten-1 waves-effect">Volver al índice</button>
						
					</div>
						
				</form>
					
			</div>
				
		</div>
	
	</body>
	
	<script type="text/javascript">
		
		var data = JSON.parse('${model.data}');
		
		if (data.length > 0) {
		
			var listContainer = document.getElementById("users");
			
			var list = new Array(data.length);
			
			for (var i = 0; i < data.length; i++) list[i] = data[i].email;
			
			listDisplay(listContainer, list);
			
		}
		
	</script>

</html>