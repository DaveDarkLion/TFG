package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.user;

import org.springframework.beans.factory.annotation.Autowired;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;

public class AUserController extends AController {
	
	// DAOs
	
	@Autowired
	protected IRAlumnoDAO iRAlumnoDAO;
	public void setIRAlumnoDAO (IRAlumnoDAO iRAlumnoDAO) { this.iRAlumnoDAO = iRAlumnoDAO; }
	
	@Autowired
	protected IRProfesorDAO iRProfesorDAO;
	public void setIRProfesorDAO (IRProfesorDAO iRProfesorDAO) { this.iRProfesorDAO = iRProfesorDAO; }
	
	@Autowired
	protected IRAdministradorDAO iRAdministradorDAO;
	public void setIRAdministradorDAO (IRAdministradorDAO iRAdministradorDAO) { this.iRAdministradorDAO = iRAdministradorDAO; }
	
}
