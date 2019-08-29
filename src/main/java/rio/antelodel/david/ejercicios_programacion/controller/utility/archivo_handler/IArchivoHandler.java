package rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpEntity;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface IArchivoHandler {

	public HttpEntity<byte[]> getFileFromArchivo (IRArchivo iRArchivo) throws IOException;
	
	public void makeDirs (IREjercicio rEjercicio);
	public void deleteDirs (IREjercicio rEjercicio);
	
	public void deleteArchivoFile (IRArchivo iRArchivo);
	
	public IRArchivo getArchivoFromFile (File file, String pathRoot);
	
}
