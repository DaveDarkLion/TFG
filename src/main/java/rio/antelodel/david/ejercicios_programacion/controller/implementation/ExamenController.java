package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.set.ASetItemController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Controller
@RequestMapping("/examenes")
public class ExamenController extends ASetItemController <IRExamen> {
	
	// Constants
	
	protected static final String TITLE = "Examen";
	
	// DAOs
	
	@Autowired
	private IRExamenDAO iRExamenDAO;
	public void setIRExamenDAO (IRExamenDAO iRExamenDAO) { this.iRExamenDAO = iRExamenDAO; }
	
	@Autowired
	private IREjercicioExamenDAO iREjercicioExamenDAO;
	public void setIREjercicioExamenDAO (IREjercicioExamenDAO iREjercicioExamenDAO) { this.iREjercicioExamenDAO = iREjercicioExamenDAO; }
	
	// Functions
	
	// Mappings
	
	// Get Examen Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView getExamenEditForm (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Examen Edit Form
		
		IRExamen iRExamen = iRExamenDAO.find(id);
		
		if (!iRExamen.isNull()) {

			return getEditForm(TITLE, iRExamen);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Examen with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get Examen New Form
	
	@GetMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	public ModelAndView getExamenNewForm (@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		return getNewForm(TITLE, isValid(viewUseCart));
		
	}
	
	// Create Examen
	
	@PostMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView createExamen (	@RequestParam (name="abierto", required = false) String viewIsAbierto,
										@RequestParam (name="visible", required = false) String viewIsVisible,
										@RequestParam (name="descripcion") String viewDescripcion,
										@RequestParam ("titulacion_id") String viewTitulacion,
										@RequestParam ("mes") String viewMes,
										@RequestParam ("ano") String viewAno,
										@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Examen info
		
		try {
		
			// Get data
			
			boolean isAbierto = isValid(viewIsAbierto);
			boolean isVisible = isValid(viewIsVisible);
			IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));	
			int mes = toInt(viewMes);
			int ano = toInt(viewAno);
		
			// Save
			
			IRExamen iRExamen = IRFactory.newIRExamen(mes, ano, Jsoup.parse(viewDescripcion).text(), iRTitulacion, isVisible, isAbierto);
			iRExamenDAO.save(iRExamen);
			
			if (isValid(viewUseCart)) {
				
				for (IREjercicioPersona iRP : getUser().getIREjerciciosSet()) iREjercicioExamenDAO.save(IRFactory.newIREjercicioExamen(iRP.getIREjercicio(), iRExamen, iRP.getPosition()));
				
			}
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created a new Examen with id: " + iRExamen.getId());
			return getMessageView("Examen creado con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Examen and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Examen
	
	@PostMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView updateExamen (	@PathVariable int id,
										@RequestParam(name="abierto", required = false) String viewIsAbierto,
										@RequestParam(name="visible", required = false) String viewIsVisible,
										@RequestParam(name="descripcion") String viewDescripcion,
										@RequestParam(name="titulacion_id") String viewTitulacion,
										@RequestParam(name="mes") String viewMes,
										@RequestParam(name="ano") String viewAno) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
				
		// Get Examen info
		
		IRExamen iRExamen = iRExamenDAO.find(id);
		
		if (!iRExamen.isNull()) {
			
			try {
				
				// Get data
				
				boolean isAbierto = isValid(viewIsAbierto);
				boolean isVisible = isValid(viewIsVisible);
				IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));
				int mes = toInt(viewMes);
				int ano = toInt(viewAno);
					
				// Save
				
				updateIRExamen(iRExamen, isAbierto, isVisible, Jsoup.parse(viewDescripcion).text(), iRTitulacion, mes, ano);
				
				CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Examen with id: " + iRExamen.getId());
			
				return getMessageView("Examen actualizado con éxito", model);
				
			}
			
			catch (Exception e) {
				
				CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update Examen with id: " + id + " and triggered the exception: " + e.toString());
				return getGenericErrorView(model);
				
			}
				
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update non existent Examen with id: " + id);
			return getGenericErrorView(model);
			
		}
			
	}
	
	private void updateIRExamen (IRExamen iRExamen, boolean abierto, boolean visible, String descripcion, IRTitulacion iRTitulacion, int mes, int ano) {
		
		iRExamen.setAbierto(abierto);
		iRExamen.setVisible(visible);
		iRExamen.setDescripcion(descripcion);
		iRExamen.setIRTitulacion(iRTitulacion);
		iRExamen.setMes(mes);
		iRExamen.setAno(ano);
		
		iRExamenDAO.update(iRExamen);
		
	}
	
	// Delete Examen
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView deleteExamen (@PathVariable int id) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
						
		// Delete Examen
		
		IRExamen iRExamen = iRExamenDAO.find(id);
		
		try {
		
			iRExamenDAO.delete(iRExamen);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Examen with id: " + id);
			return getMessageView("Examen eliminado con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete non existent Examen with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get document
	
	@GetMapping(LINK_GENERIC_ID + "/document")
	@Transactional
	public HttpEntity<byte[]> getDocument (@PathVariable int id, @RequestParam(name="document_id") String viewDocumentType) {
		
		IRExamen iRExamen = iRExamenDAO.find(id);
		
		try {
		
			return getDocument(iRExamen.getDescripcion(), iRExamen, viewDocumentType);
			
		}
	
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to obtain document and triggered the exception: " + e.toString());
			return null;
			
		}
		
	}
	
}