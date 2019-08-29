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
		<title>Error</title>

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
		
								<font color="red">${model.error}</font>
								
							</div>
								
						</div>
						
					</div>
					
					<div class="card-action center-align">
					
						<button class="btn-flat grey lighten-1 waves-effect" type="button" onclick="window.location='/ejercicios-programacion/index.html';" name="action">Volver al índice</button>
						
					</div>
					
				</form>
				
			</div>
			
		</div>
	
	</body>

</html>