package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRIdeaNombreIdeaText;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@Controller
@RequestMapping("/ideas")
@Secured(Role.PROFESOR)
public class IdeaListController extends AController {

	// Element Names
	
	protected static final String FILTER_IDEA_NAME = "filterIdea";
	protected static final String FILTER_SHOW_ALL_NAME = "filterShowAll";
	
	// Data names
	
	protected static final String IDEAS_DATA_NAME = "ideasData";
	
	// Views
	
	protected static final String VIEW_IDEA_LIST = "idea/idea-list";
	
	// DAOs
	
	@Autowired
	private IRIdeaDAO iRIdeaDAO;
	public void setIRIdeaDAO (IRIdeaDAO iRIdeaDAO) { this.iRIdeaDAO = iRIdeaDAO; }
	
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	public void setIRProfesorDAO (IRProfesorDAO iRProfesorDAO) { this.iRProfesorDAO = iRProfesorDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRIdeaNombreIdeaText iFilterIRIdeaNombreIdeaText;
	public void setIFilterIRIdeaNombreIdeaText (IFilterIRIdeaNombreIdeaText iFilterIRIdeaNombreIdeaText) { this.iFilterIRIdeaNombreIdeaText = iFilterIRIdeaNombreIdeaText; }
	
	@Autowired
	private IFilterIRProfesor iFilterIRProfesor;
	public void setIFilterIRProfesor (IFilterIRProfesor iFilterIRProfesor) { this.iFilterIRProfesor = iFilterIRProfesor; }
	
	// Functions
	
	// Mapping

	// Get Idea list
	
	@GetMapping
	public ModelAndView getIdeaListForm (	@RequestParam (value="filter_idea", required=false) String viewFilterIdea,
											@RequestParam (value="filter_show_all", required=false) String viewFilterShowAll,
											@RequestParam (value="page", required=false) String viewPage,
											@RequestParam (value="page_size_id", required=false) String viewPageSize) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRProfesor iRProfesor = iRProfesorDAO.find(getUser().getEmail());
		List<IRIdea> iRIdeaList = iRIdeaDAO.getAll();
		
		// Idea filter
		
		iRIdeaList = iFilterIRIdeaNombreIdeaText.cleanAndApply(iRIdeaList, viewFilterIdea, IFilterHelper.Operation.OR);
		model.put(FILTER_IDEA_NAME, IFilterHelper.getCleanFilters(viewFilterIdea));
		
		// Show All Filter
		
		if (!isValid(viewFilterShowAll)) iRIdeaList = iFilterIRProfesor.cleanAndApply(iRIdeaList, iRProfesor.getEmail(), IFilterHelper.Operation.OR);
		model.put(FILTER_SHOW_ALL_NAME, ViewBoolean.toViewBoolean(isValid(viewFilterShowAll)));
		
		// Page data
		
		int page = getPageInfo(viewPage, 1);
		model.put(PAGE_NAME, Integer.toString(page));
		
		// Page size data
		
		int pageSize = getPageInfo(viewPageSize, PAGE_SIZE_DEFAULT);
		
		JSONObject pageSizeData = getPageSizeData(pageSize);
		model.put(PAGE_SIZES_DATA_NAME, pageSizeData.toString());
		
		// Page last data
		
		int pageLast = getPageLast(iRIdeaList, pageSize);
		model.put(PAGE_LAST_NAME, Integer.toString(pageLast));
		
		// Get paged list
		
		iRIdeaList = getPagedList(iRIdeaList, page, pageSize);
		
		// Ideas Data
		
		JSONArray ideasData = getGenericListSortedData(iRIdeaList);
		model.put(IDEAS_DATA_NAME, ideasData.toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_IDEA_LIST, model);
		
	}
	
}