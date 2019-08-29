package rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document;

public class ILaTexDocumentHelper {

	// Private Constructor
	
	private ILaTexDocumentHelper () { }
	
	// Document Type
	
	public enum DocumentType { LATEX, PDF }
	
	protected static final String [] months = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
	
	// Functions
	
	// Cast int to DocumentType
	
	public static DocumentType intToDocumentType (int iDocumentType) {
			
		if (iDocumentType == 0) return DocumentType.LATEX;
		else return DocumentType.PDF;
			
	}
	
	// Cast int to month
	
	public static String getMonthName (int monthId) {
		
		return months[monthId];
		
	}
	
}
