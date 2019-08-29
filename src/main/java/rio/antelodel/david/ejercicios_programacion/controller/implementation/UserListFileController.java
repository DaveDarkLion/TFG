package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.user.AUserEditionController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandlerConfig;
import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper;
import rio.antelodel.david.ejercicios_programacion.controller.utility.user_parser.IUserParser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Controller
@RequestMapping("/usuarios")
@Secured(Role.ADMIN)
public class UserListFileController extends AUserEditionController {

	 // Paths
	
	protected static final String PATH = IArchivoHandlerConfig.PATH_TEMP + File.separator + "user-list";
	
	// Element names
	
	protected static final String USER_NUMBER_TOTAL_NAME = "userNumberTotal";
	protected static final String USER_NUMBER_NAME = "userNumber";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// User Parser
	
	@Autowired
	private IUserParser iUserParser;
	public void setIUserParser (IUserParser iUserParser) { this.iUserParser = iUserParser; }
	
	// Views
	
	protected static final String VIEW_NEW_USERS_FORM = "admin/user/user-file";
	protected static final String VIEW_USER_FILE_RESULT = "admin/user/user-file-result";
	
	// Functions
	
	// Mapping
	
	@GetMapping("/add-from-file")
	public ModelAndView getNewUsersForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Return MAV
		
		return getDefaultMAV(VIEW_NEW_USERS_FORM, model);
		
	}
	
	@PostMapping("/add-from-file")
	@Transactional
	public ModelAndView createNewUsers (HttpServletRequest request) throws Exception {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Create Users
		
		try {
		
			// Get InputStream
			
			InputStream inputStream = getItemInputStream(request);
			
			// Parse file
			
			List<Pair<IRPersona, String []>> iRPersonaRolesList = iUserParser.parsePersonaList(getFileContent(inputStream));
			
			// Add Users
			
			List<IRPersona> iRPersonaList = new ArrayList<>();
			
			for (Pair<IRPersona, String []> iRPersonaRoles : iRPersonaRolesList) {
				
				// Persona
				
				String password = iPasswordService.randomizePassword();
				
				IRPersona iRPersona = iRPersonaRoles.getFirst();
				iRPersona.setPassword(password);
				
				// Roles
				
				String [] rolesId = iRPersonaRoles.getSecond();
				
				// Save new User
				
				if (saveNewUser(iRPersona, rolesId)) iRPersonaList.add(iRPersona);
				
			}
			
			model.put(USER_NUMBER_TOTAL_NAME, Integer.toString(iRPersonaRolesList.size()));
			model.put(USER_NUMBER_NAME, Integer.toString(iRPersonaList.size()));
			model.put(DATA_NAME, IParseableHelper.getGenericListData(iRPersonaList).toString());
			
			// Return MAV
			
			return getDefaultMAV(VIEW_USER_FILE_RESULT, model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to upload a new user list and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get InputStream of file
	
	private InputStream getItemInputStream (HttpServletRequest request) throws IOException, ServletException {
		
		// Iterator
		
		Iterator<Part> iterator = request.getParts().iterator();
		
		// Seek item
		
		Part item;
		while (!(item = iterator.next()).getName().equals("file"));
		
		return item.getInputStream();
		
	}
	
	// Get content of file from InputStream
	
	private String getFileContent (InputStream inputStream) throws IOException {
		
		StringWriter stringWriter = new StringWriter();
		IOUtils.copy(inputStream, stringWriter, StandardCharsets.UTF_8);
		
		return stringWriter.toString();
		
	}
	
}
