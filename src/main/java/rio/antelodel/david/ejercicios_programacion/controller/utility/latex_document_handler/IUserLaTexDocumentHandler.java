package rio.antelodel.david.ejercicios_programacion.controller.utility.latex_document_handler;

import java.util.List;

import org.springframework.http.HttpEntity;

import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.ILaTexDocumentHelper.DocumentType;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

public interface IUserLaTexDocumentHandler {
	
	public <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> HttpEntity<byte[]> getDocument (String documentName, String nombre, int mes, int ano, List<U> iREjercicioSetList, DocumentType type);
	
	public <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> HttpEntity<byte[]> getDocument (String documentName, String nombre, List<U> iREjercicioSetList, DocumentType type);

}
