package rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandlerConfig.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.auxiliar.file_handler.ICustomFileHandler;
import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

@Service
public class ArchivoHandler implements IArchivoHandler {
	
	// File Handler
	
	@Autowired
	private ICustomFileHandler iCustomFileHandler;
	public void setICustomFileHandler (ICustomFileHandler iCustomFileHandler) { this.iCustomFileHandler = iCustomFileHandler; }
	
	// Functions
	
	// Get IRArchivo
	
	public HttpEntity<byte[]> getFileFromArchivo (IRArchivo iRArchivo) throws IOException {
		
		return iCustomFileHandler.getFile(Paths.get(iRArchivo.getRuta()));
		
	}
	
	// Dirs
	
	// Create Dirs
	
	public void makeDirs (IREjercicio iREjercicio) {
		
		try {
			
			Files.createDirectories(Paths.get(getPath(iREjercicio, PATH_ENTRADA)));
			Files.createDirectories(Paths.get(getPath(iREjercicio, PATH_VALIDACION)));
			Files.createDirectories(Paths.get(getPath(iREjercicio, PATH_SOLUCION)));
			
		} catch (IOException e) { CustomLogger.LOGGER.severe("Creating dirs for Ejercicio triggered the exception: " + e.toString()); }
		
	}
	
	// Delete Dirs
	
	public void deleteDirs (IREjercicio iREjercicio) {
		
		try {
		
			FileUtils.deleteDirectory(new File(getBasePath(iREjercicio)));
			
		} catch (IOException e) { CustomLogger.LOGGER.severe("Deleting dirs for Ejercicio triggered the exception: " + e.toString()); }
		
	}
	
	private String getPath (IREjercicio iREjercicio, String pathEnd) {
		
		return getBasePath(iREjercicio) + File.separator + pathEnd;
		
	}
	
	private String getBasePath(IREjercicio iREjercicio) {
		
		return PATH_MAIN + File.separator + iREjercicio.getId();
		
	}
	
	// Delete File from Archivo
	
	public void deleteArchivoFile (IRArchivo iRArchivo) {
		
		try { Files.delete(Paths.get(iRArchivo.getRuta())); }
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe("Deleting file triggered the exception: " + e.toString());
			
		}
		
	}
	
	// Get Archivo updated
	
	public IRArchivo getArchivoFromFile (File file, String pathRoot) { 
		
		// Get paths
		
		final String pathOriginal = pathRoot + File.separator + file.getName();
		final String path = iCustomFileHandler.getFileMovePath(pathOriginal);
		
		// Move file
		
		if (!file.renameTo(new File(path))) return null;
		
		// Archivo
		
		return IRFactory.newIRArchivo(path);
		
	}
	
}
