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
		<title>Índice</title>
		
	</head>

	<body>

		<%@ include file='/WEB-INF/jsp/navbar/navbar.jspf' %>
		
		<div class="valign-wrapper row">
		
			<div class="col card hoverable s12 m10 pull-m1 l6 pull-l3">
				
				<form id="form_main" method="POST">
						
					<div class="card-content">
					
		        		<span class="card-title" id="card_title"></span>
		        			
		        			<div class="col s12">
		        			
		        				<div class="center-align">
		        			
		        					<img style="max-width:100%; max-height:100%" src="logo.png">
		        				
		        				</div>
		        			
		        			</div>
		        			
		        			<div class="row col s12"></div>
		        			
		        			<div class="col s12">
		        			
		        				<p style="font-size:16px">
			        				<c:if test='${not empty pageContext.request.remoteUser}'>
			        					¡Bienvenido, <i id="text_user"></i>!
			        				</c:if>
			        				<c:if test='${empty pageContext.request.remoteUser}'>
			        					¡Bienvenido!
			        				</c:if>
		        				</p>
		        				
		        				<br>
		        				
		        				<p>
		        				
			        				Esta página está dedicada al almacenamiento de recursos asociados a la programación, disponibles tanto para alumnos como para profesores, con fines didácticos.
			        				Aquí podrá encontrar todo tipo de ejercicios, así como exámenes y otras pruebas en las que fueron usados.</p>
		        				
		        				<c:if test='${empty pageContext.request.remoteUser}'>
		        					<br>
		        					<p>Si es usted miembro, puede acceder <a href="login.html">aquí</a></p>
		        				</c:if>
		        			
		        			</div>
		        			
		        		<div class="row">
		        		
		        		</div>
		        		
		        	</div>
		        	
		        </form>
		        
			</div>
		       
		</div>
	
	</body>
	
	<script type="text/javascript">
	
	if ('${model.profileData}' != '') {
		
		var profileData = JSON.parse('${model.profileData}');
	
		document.getElementById("text_user").innerHTML = profileData.nombre;
		
	}
	
	</script>
		
</html>