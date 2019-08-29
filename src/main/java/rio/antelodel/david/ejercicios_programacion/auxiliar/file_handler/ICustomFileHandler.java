package rio.antelodel.david.ejercicios_programacion.auxiliar.file_handler;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.http.HttpEntity;

public interface ICustomFileHandler {
	
	public HttpEntity<byte[]> getFile (byte [] archivoBytes, String name, String extension);
	
	public HttpEntity<byte[]> getFile (Path path) throws IOException;
	
	public String getFileMovePath (String path);
	
}
