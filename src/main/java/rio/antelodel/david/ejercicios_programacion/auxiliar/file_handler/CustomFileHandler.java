package rio.antelodel.david.ejercicios_programacion.auxiliar.file_handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class CustomFileHandler implements ICustomFileHandler {
	
	// Get File
	
	public HttpEntity<byte[]> getFile (byte [] archivoBytes, String name, String extension) {
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType("application", extension));
        headers.set("Content-Disposition", "attachment; filename=\"" + name + "\"");
        headers.setContentLength(archivoBytes.length);

        return new HttpEntity<>(archivoBytes, headers);
		
	}
	
	public  HttpEntity<byte[]> getFile (Path path) throws IOException {
		
		byte [] fileByteArray = Files.readAllBytes(path);
		return getFile(fileByteArray, FilenameUtils.getName(path.toString()), FilenameUtils.getExtension(path.toString()));
		
	}
	
	// Get path for moving file
	
	public String getFileMovePath (String path) {
		
		final String pathWithoutExtension = FilenameUtils.removeExtension(path);
		final String extension = FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(path);
		
		File file = new File(path);
		
		int i = 1;
		while (file.exists()) {
			
			file = new File(pathWithoutExtension + " (" + i + ")" + extension);
			i++;
		}
		
		return file.getPath();
		
	}
	
}
