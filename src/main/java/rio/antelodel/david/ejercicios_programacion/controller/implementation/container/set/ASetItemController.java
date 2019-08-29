package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.set;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.utility.latex_document_handler.IUserLaTexDocumentHandler;
import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.ILaTexDocumentHelper;
import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public abstract class ASetItemController <T extends IRComplexSet<?, ?, ?, T>> extends AController {
	
	// Element names
	
	protected static final String ANO_MIN_NAME = "anoMin";
	protected static final String ANO_MAX_NAME = "anoMax";
	protected static final String USE_CART_NAME = "useCart";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// DAOs
	
	@Autowired
	protected IRTitulacionDAO iRTitulacionDAO;
	public void setIRTitulacionDAO (IRTitulacionDAO iRTitulacionDAO) { this.iRTitulacionDAO = iRTitulacionDAO; }
	
	// Handlers
	
	@Autowired
	private IUserLaTexDocumentHandler iLaTexDocumentHandler;
	public void setILaTexDocumentHandler (IUserLaTexDocumentHandler iLatexDocumentHandler) { this.iLaTexDocumentHandler = iLatexDocumentHandler; }
	
	// Views
	
	protected static final String VIEW_SET_EDIT = "set/set-edit";
	
	// Functions
	
	// Get set item edit view
	
	protected ModelAndView getEditForm (String title, T iRComplexSet) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Fill info
		
		fillDefaultInfo(model, title, false, false);
		model.put(DATA_NAME, iRComplexSet.getFullData().toString());
		
		// Year
		
		model.put(ANO_MIN_NAME, Integer.toString(getAnoMin()));
		model.put(ANO_MAX_NAME, Integer.toString(getAnoMax()));
		
		// Titulacion
		
		model.put(TITULACION_ANY_NAME, ViewBoolean.FALSE);
		
		JSONObject titulacionCurrentData = getNullableData(iRComplexSet.getIRTitulacion());
		model.put(TITULACION_CURRENT_DATA_NAME, titulacionCurrentData.toString());
		
		JSONArray titulacionesData = getGenericListSortedData(iRTitulacionDAO.getAll());
		model.put(TITULACIONES_DATA_NAME, titulacionesData.toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_SET_EDIT, model);
		
	}
	
	protected ModelAndView getNewForm (String title, Boolean useCart) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Fill info
		
		fillDefaultInfo(model, title, true, useCart);
		model.put(DATA_NAME, new JSONObject().toString());
		
		// Titulacion
		
		model.put(TITULACION_ANY_NAME, ViewBoolean.FALSE);
		
		JSONObject titulacionCurrentData = getNullableData(iRTitulacionDAO.getDefault());
		model.put(TITULACION_CURRENT_DATA_NAME, titulacionCurrentData.toString());
		
		JSONArray titulacionesData = getGenericListSortedData(iRTitulacionDAO.getAll());
		model.put(TITULACIONES_DATA_NAME, titulacionesData.toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_SET_EDIT, model);
		
	}
	
	private void fillDefaultInfo (Map<String, String> model, String title, Boolean isNew, Boolean useCart) {
		
		model.put(TITLE_NAME, title);
		model.put(IS_NEW_NAME, ViewBoolean.toViewBoolean(isNew));
		model.put(ANO_MIN_NAME, Integer.toString(getAnoMin()));
		model.put(ANO_MAX_NAME, Integer.toString(getAnoMax()));
		model.put(USE_CART_NAME, ViewBoolean.toViewBoolean(useCart));
		
	}

	// Get Document
	
	public HttpEntity<byte[]> getDocument (String nombre, T iRComplexSet, String viewDocumentType) {
		
		if (setIsVisible(iRComplexSet))
			return iLaTexDocumentHandler.getDocument(nombre, iRComplexSet.getDescripcion(), iRComplexSet.getMes() - 1, iRComplexSet.getAno(), iRComplexSet.getIREjerciciosSet(), ILaTexDocumentHelper.intToDocumentType(toInt(viewDocumentType)));
			
		else {
					
			CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to obtain document from a non visible Set as a non privileged user");
			return null;
				
		}
		
	}
	
	protected boolean setIsVisible (IRComplexSet<?, ?, ?, ?> iRComplexSet) {
		
		return userIsPrivileged() || iRComplexSet.isVisible(); 
		
	}
	
}
