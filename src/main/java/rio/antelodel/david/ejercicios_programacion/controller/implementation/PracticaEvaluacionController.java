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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Controller
@RequestMapping("/practicas-evaluacion")
public class PracticaEvaluacionController extends ASetItemController <IRPracticaEvaluacion> {
	
	// Constants
	
	protected static final String TITLE = "Práctica Evaluable";
	
	// DAOs
	
	@Autowired
	private IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO;
	public void setIRPracticaEvaluacionDAO (IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO) { this.iRPracticaEvaluacionDAO = iRPracticaEvaluacionDAO; }
	
	@Autowired
	private IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO;
	public void setIREjercicioPracticaEvaluacionDAO (IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO) { this.iREjercicioPracticaEvaluacionDAO = iREjercicioPracticaEvaluacionDAO; }
	
	// Functions
	
	// Mappings
	
	// Get PracticaEvaluacion Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView getPracticaEvaluacionEditForm (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get PracticaEvaluacion Edit Form
		
		IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(id);
		
		if (!iRPracticaEvaluacion.isNull()) {

			return getEditForm(TITLE, iRPracticaEvaluacion);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent PracticaEvaluacion with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get PracticaEvaluacion New Form
	
	@GetMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	public ModelAndView getPracticaEvaluacionNewForm (@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		return getNewForm(TITLE, isValid(viewUseCart));
		
	}
	
	// Create PracticaEvaluacion
	
	@PostMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView createPracticaEvaluacion (	@RequestParam (name="abierto", required = false) String viewIsAbierto,
													@RequestParam (name="visible", required = false) String viewIsVisible,
													@RequestParam (name="descripcion") String viewDescripcion,
													@RequestParam ("titulacion_id") String viewTitulacion,
													@RequestParam ("mes") String viewMes,
													@RequestParam ("ano") String viewAno,
													@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get PracticaEvaluacion info
		
		try {
		
			// Get data
			
			boolean isAbierto = isValid(viewIsAbierto);
			boolean isVisible = isValid(viewIsVisible);
			IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));	
			int mes = toInt(viewMes);
			int ano = toInt(viewAno);
		
			// Save
			
			IRPracticaEvaluacion iRPracticaEvaluacion = IRFactory.newIRPracticaEvaluacion(mes, ano, Jsoup.parse(viewDescripcion).text(), iRTitulacion, isVisible, isAbierto);
			iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion);
			
			if (isValid(viewUseCart)) {
				
				for (IREjercicioPersona iRP : getUser().getIREjerciciosSet()) iREjercicioPracticaEvaluacionDAO.save(IRFactory.newIREjercicioPracticaEvaluacion(iRP.getIREjercicio(), iRPracticaEvaluacion, iRP.getPosition()));
				
			}
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created a new PracticaEvaluacion with id: " + iRPracticaEvaluacion.getId());
			return getMessageView("Práctica evaluable creada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new PracticaEvaluacion and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update PracticaEvaluacion
	
	@PostMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView updatePracticaEvaluacion (	@PathVariable int id,
													@RequestParam(name="abierto", required = false) String viewIsAbierto,
													@RequestParam(name="visible", required = false) String viewIsVisible,
													@RequestParam(name="descripcion") String viewDescripcion,
													@RequestParam(name="titulacion_id") String viewTitulacion,
													@RequestParam(name="mes") String viewMes,
													@RequestParam(name="ano") String viewAno) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
				
		// Get PracticaEvaluacion info
		
		IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(id);
		
		if (!iRPracticaEvaluacion.isNull()) {
			
			try {
				
				// Get data
				
				boolean isAbierto = isValid(viewIsAbierto);
				boolean isVisible = isValid(viewIsVisible);
				IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));
				int mes = toInt(viewMes);
				int ano = toInt(viewAno);
					
				// Save
				
				updateIRPracticaEvaluacion(iRPracticaEvaluacion, isAbierto, isVisible, Jsoup.parse(viewDescripcion).text(), iRTitulacion, mes, ano);
				
				CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated PracticaEvaluacion with id: " + iRPracticaEvaluacion.getId());
			
				return getMessageView("Práctica evaluable actualizada con éxito", model);
				
			}
			
			catch (Exception e) {
				
				CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update PracticaEvaluacion with id: " + id + " and triggered the exception: " + e.toString());
				return getGenericErrorView(model);
				
			}
				
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update non existent PracticaEvaluacion with id: " + id);
			return getGenericErrorView(model);
			
		}
			
	}
	
	private void updateIRPracticaEvaluacion (IRPracticaEvaluacion iRPracticaEvaluacion, boolean abierto, boolean visible, String descripcion, IRTitulacion iRTitulacion, int mes, int ano) {
		
		iRPracticaEvaluacion.setAbierto(abierto);
		iRPracticaEvaluacion.setVisible(visible);
		iRPracticaEvaluacion.setDescripcion(descripcion);
		iRPracticaEvaluacion.setIRTitulacion(iRTitulacion);
		iRPracticaEvaluacion.setMes(mes);
		iRPracticaEvaluacion.setAno(ano);
		
		iRPracticaEvaluacionDAO.update(iRPracticaEvaluacion);
		
	}
	
	// Delete PracticaEvaluacion
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView deletePracticaEvaluacion (@PathVariable int id) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
						
		// Delete PracticaEvaluacion
		
		IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(id);
		
		try {
		
			iRPracticaEvaluacionDAO.delete(iRPracticaEvaluacion);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted PracticaEvaluacion with id: " + id);
			return getMessageView("Práctica evaluable eliminada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete non existent PracticaEvaluacion with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get document
	
	@GetMapping(LINK_GENERIC_ID + "/document")
	@Transactional
	public HttpEntity<byte[]> getDocument (@PathVariable int id, @RequestParam(name="document_id") String viewDocumentType) {
		
		IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(id);
		
		try {
		
			return getDocument(iRPracticaEvaluacion.getDescripcion(), iRPracticaEvaluacion, viewDocumentType);
		
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to obtain document and triggered the exception: " + e.toString());
			return null;
			
		}
		
	}
	
}