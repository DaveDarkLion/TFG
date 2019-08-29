package rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler;

import java.io.File;

public class IArchivoHandlerConfig {

	// Private Constructor
	
	private IArchivoHandlerConfig () { }
	
	// Config
	
	// Paths
	
	public static final String PATH_MAIN = "files";
	
	public static final String PATH_ENTRADA = "entrada";
	public static final String PATH_VALIDACION = "validacion";
	public static final String PATH_SOLUCION = "solucion";
	
	public static final String PATH_TEMP = PATH_MAIN + File.separator + "temp";
	
}
