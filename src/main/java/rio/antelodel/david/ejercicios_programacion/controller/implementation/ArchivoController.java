package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandler;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;

@Controller
@RequestMapping("/archivos")
@Secured({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class ArchivoController extends AController {
	
	// Handlers
	
	@Autowired
	private IArchivoHandler iArchivoHandler;
	public void setIArchivoHandler (IArchivoHandler iArchivoHandler) { this.iArchivoHandler = iArchivoHandler; }
	
	// DAOs
	
	@Autowired
	private IRArchivoDAO iRArchivoDAO;
	public void setIRArchivoDAO (IRArchivoDAO iRArchivoDAO) { this.iRArchivoDAO = iRArchivoDAO; }
	
	// Functions
	
	// Mapping
	
	@GetMapping(LINK_GENERIC_ID)
	@Transactional
	public HttpEntity<byte[]> getArchivoFile (@PathVariable int id) {
		
		IRArchivo iRArchivo = iRArchivoDAO.find(id);
			
		if (!iRArchivo.isNull() && hasPermission(iRArchivo)) {
		
			try {
				
				return iArchivoHandler.getFileFromArchivo(iRArchivo);
				
			} catch (IOException e) {
				
				CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + ", while trying to retrieve the file with id " + id + ", triggered the exception: " + e.toString());
				return null;
				
			}
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + ", tried to retrieve the non existent or not visible file with id " + id);
			return null;
			
		}
		
	}
	
	private boolean hasPermission (IRArchivo iRArchivo) {
		
		return (userIsPrivileged() || iRArchivo.isVisible());
		
	}
	
}
