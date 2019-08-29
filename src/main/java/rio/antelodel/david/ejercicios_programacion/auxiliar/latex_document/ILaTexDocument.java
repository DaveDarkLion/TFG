package rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document;

import java.util.Calendar;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface ILaTexDocument {
	
	public void setHeader (String title);
	public void setDate (Calendar date);
	public void setIREjerciciosPosition (List<Pair<IREjercicio, Integer>> iREjerciciosPosition);
	
	public byte [] getLaTex ();
	public byte [] getPDF ();
	
}
