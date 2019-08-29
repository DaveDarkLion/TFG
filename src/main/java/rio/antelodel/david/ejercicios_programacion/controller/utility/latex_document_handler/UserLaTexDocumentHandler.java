package rio.antelodel.david.ejercicios_programacion.controller.utility.latex_document_handler;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.auxiliar.file_handler.ICustomFileHandler;
import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.ILaTexDocumentHelper.DocumentType;
import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.LaTexDocument;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IREjercicioSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

@Service
public class UserLaTexDocumentHandler implements IUserLaTexDocumentHandler {

	// Extensions
	
	private static final String LATEX_EXT = "tex";
	private static final String PDF_EXT = "pdf";
	
	// FileHandler
	
	@Autowired
	private ICustomFileHandler iCustomFileHandler;
	public void setICustomFileHandler (ICustomFileHandler iCustomFileHandler) { this.iCustomFileHandler = iCustomFileHandler; }
	
	// Functions
	
	// Get document
	
	@Override
	public <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> HttpEntity<byte[]> getDocument (String documentName, String nombre, int mes, int ano, List<U> iREjercicioSetList, DocumentType type) {
		
		List<Pair<IREjercicio, Integer>> iREjercicioPositionList = IREjercicioSetHelper.getIREjercicioPositionSortedList(iREjercicioSetList);
		
		LaTexDocument document = new LaTexDocument();
		
		document.setHeader(nombre);
		document.setDate(Calendar.getInstance());
		document.getDate().set(ano, mes, 1);
		document.setIREjerciciosPosition(iREjercicioPositionList);
		
		if (type == DocumentType.LATEX) return iCustomFileHandler.getFile(document.getLaTex(), documentName + FilenameUtils.EXTENSION_SEPARATOR_STR + LATEX_EXT, LATEX_EXT);
		else return iCustomFileHandler.getFile(document.getPDF(), documentName + FilenameUtils.EXTENSION_SEPARATOR_STR + PDF_EXT, PDF_EXT);
		
	}

	public <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> HttpEntity<byte[]> getDocument (String documentName, String nombre, List<U> iREjercicioSetList, DocumentType type) {
		
		Calendar calendar = Calendar.getInstance();
		
		return getDocument (documentName, nombre, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), iREjercicioSetList, type);
		
	}
	
}
