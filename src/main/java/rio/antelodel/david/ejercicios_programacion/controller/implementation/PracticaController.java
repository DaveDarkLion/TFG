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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Controller
@RequestMapping("/practicas")
public class PracticaController extends ASetItemController <IRPractica> {
	
	// Constants
	
	protected static final String TITLE = "Práctica";
	
	// DAOs
	
	@Autowired
	private IRPracticaDAO iRPracticaDAO;
	public void setIRPracticaDAO (IRPracticaDAO iRPracticaDAO) { this.iRPracticaDAO = iRPracticaDAO; }
	
	@Autowired
	private IREjercicioPracticaDAO iREjercicioPracticaDAO;
	public void setIREjercicioPracticaDAO (IREjercicioPracticaDAO iREjercicioPracticaDAO) { this.iREjercicioPracticaDAO = iREjercicioPracticaDAO; }
	
	// Functions
	
	// Mappings
	
	// Get Practica Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView getPracticaEditForm (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Practica Edit Form
		
		IRPractica iRPractica = iRPracticaDAO.find(id);
		
		if (!iRPractica.isNull()) {

			return getEditForm(TITLE, iRPractica);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Practica with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get Practica New Form
	
	@GetMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	public ModelAndView getPracticaNewForm (@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		return getNewForm(TITLE, isValid(viewUseCart));
		
	}
	
	// Create Practica
	
	@PostMapping(LINK_ADD)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView createPractica (	@RequestParam (name="abierto", required = false) String viewIsAbierto,
											@RequestParam (name="visible", required = false) String viewIsVisible,
											@RequestParam (name="descripcion") String viewDescripcion,
											@RequestParam ("titulacion_id") String viewTitulacion,
											@RequestParam ("mes") String viewMes,
											@RequestParam ("ano") String viewAno,
											@RequestParam (name="use_cart", required=false) String viewUseCart) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Practica info
		
		try {
		
			// Get data
			
			boolean isAbierto = isValid(viewIsAbierto);
			boolean isVisible = isValid(viewIsVisible);
			IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));	
			int mes = toInt(viewMes);
			int ano = toInt(viewAno);
		
			// Save
			
			IRPractica iRPractica = IRFactory.newIRPractica(mes, ano, Jsoup.parse(viewDescripcion).text(), iRTitulacion, isVisible, isAbierto);
			iRPracticaDAO.save(iRPractica);
			
			if (isValid(viewUseCart)) {
				
				for (IREjercicioPersona iRP : getUser().getIREjerciciosSet()) iREjercicioPracticaDAO.save(IRFactory.newIREjercicioPractica(iRP.getIREjercicio(), iRPractica, iRP.getPosition()));
				
			}
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created a new Practica with id: " + iRPractica.getId());
			return getMessageView("Practica creada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Practica and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Practica
	
	@PostMapping(LINK_GENERIC_ID)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView updatePractica (	@PathVariable int id,
											@RequestParam(name="abierto", required = false) String viewIsAbierto,
											@RequestParam(name="visible", required = false) String viewIsVisible,
											@RequestParam(name="descripcion") String viewDescripcion,
											@RequestParam(name="titulacion_id") String viewTitulacion,
											@RequestParam(name="mes") String viewMes,
											@RequestParam(name="ano") String viewAno) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
				
		// Get Practica info
		
		IRPractica iRPractica = iRPracticaDAO.find(id);
		
		if (!iRPractica.isNull()) {
			
			try {
				
				// Get data
				
				boolean isAbierto = isValid(viewIsAbierto);
				boolean isVisible = isValid(viewIsVisible);
				IRTitulacion iRTitulacion = iRTitulacionDAO.find(Integer.parseInt(viewTitulacion));
				int mes = toInt(viewMes);
				int ano = toInt(viewAno);
					
				// Save
				
				updateIRPractica(iRPractica, isAbierto, isVisible, Jsoup.parse(viewDescripcion).text(), iRTitulacion, mes, ano);
				
				CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Practica with id: " + iRPractica.getId());
			
				return getMessageView("Práctica actualizada con éxito", model);
				
			}
			
			catch (Exception e) {
				
				CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update Practica with id: " + id + " and triggered the exception: " + e.toString());
				return getGenericErrorView(model);
				
			}
				
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update non existent Practica with id: " + id);
			return getGenericErrorView(model);
			
		}
			
	}
	
	private void updateIRPractica (IRPractica iRPractica, boolean abierto, boolean visible, String descripcion, IRTitulacion iRTitulacion, int mes, int ano) {
		
		iRPractica.setAbierto(abierto);
		iRPractica.setVisible(visible);
		iRPractica.setDescripcion(descripcion);
		iRPractica.setIRTitulacion(iRTitulacion);
		iRPractica.setMes(mes);
		iRPractica.setAno(ano);
		
		iRPracticaDAO.update(iRPractica);
		
	}
	
	// Delete Practica
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Secured ({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView deletePractica (@PathVariable int id) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
						
		// Delete Practica
		
		IRPractica iRPractica = iRPracticaDAO.find(id);
		
		try {
		
			iRPracticaDAO.delete(iRPractica);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Practica with id: " + id);
			return getMessageView("Práctica eliminada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete non existent Practica with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get document
	
	@GetMapping(LINK_GENERIC_ID + "/document")
	@Transactional
	public HttpEntity<byte[]> getDocument (@PathVariable int id, @RequestParam(name="document_id") String viewDocumentType) {
		
		IRPractica iRPractica = iRPracticaDAO.find(id);
		
		try {
		
			return getDocument(iRPractica.getDescripcion(), iRPractica, viewDocumentType);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to obtain document and triggered the exception: " + e.toString());
			return null;
			
		}
		
	}
	
}