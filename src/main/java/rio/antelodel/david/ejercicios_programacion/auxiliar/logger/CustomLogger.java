package rio.antelodel.david.ejercicios_programacion.auxiliar.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.auxiliar.file_handler.ICustomFileHandler;

@Component
public class CustomLogger {
	
	// Logger
	
	public static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
	
	// File Handler
	
	@Autowired
	public ICustomFileHandler iCustomFileHandler;
	public void setiCustomFileHandler(ICustomFileHandler customFileHandler) { this.iCustomFileHandler = customFileHandler; }
	
	// Consts
	
	public static final String USER = "User ";
	
	// Functions
	
	private CustomLogger () { }
	
	@PostConstruct
	private void init () throws IOException {
		
		FileHandler fileHandler = new FileHandler(iCustomFileHandler.getFileMovePath("out.log"));  
		SimpleFormatter formatter = new SimpleFormatter();  
        fileHandler.setFormatter(formatter);
        
        LOGGER.setLevel(Level.FINE);
        
        LOGGER.addHandler(fileHandler);
        
        LOGGER.info("Execution initialized");
		
	}
	
	@PreDestroy
	private void finish () {
		
		LOGGER.info("Execution finished");
		
	}
	
}
