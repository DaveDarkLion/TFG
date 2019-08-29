package rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document;

public class ILaTexDocumentFactory {

	// Private Constructor
	
	private ILaTexDocumentFactory () { }
	
	// Factory
	
	public static ILaTexDocument newILatexDocument () { return new LaTexDocument(); }
	
}
