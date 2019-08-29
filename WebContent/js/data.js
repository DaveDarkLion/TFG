// Check if JSON is empty

function objectIsEmpty (obj) {
	
	  return (Object.getOwnPropertyNames(obj).length == 0);
	  
}

//Get display name

function getDisplayNombre (persona) {
	
	return persona.apellido1 + " " + persona.apellido2 + ", " + persona.nombre;
	
}

// Get display name simple

function getDisplayNombreSimple (persona) {
	
	return persona.nombre + " " + persona.apellido1;
	
}