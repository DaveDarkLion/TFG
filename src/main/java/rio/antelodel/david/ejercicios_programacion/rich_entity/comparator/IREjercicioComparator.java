package rio.antelodel.david.ejercicios_programacion.rich_entity.comparator;

import java.util.Comparator;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface IREjercicioComparator {

	public static final Comparator<IREjercicio> comparatorTitulo = new Comparator<IREjercicio>() {
		
		@Override
		public int compare (IREjercicio iREjercicio1, IREjercicio iREjercicio2) {
			
			return iREjercicio1.getTitulo().compareToIgnoreCase(iREjercicio2.getTitulo());
			
		}
		
	};
	
	public static final Comparator<IREjercicio> comparatorProfesor = new Comparator<IREjercicio>() {
		
		@Override
		public int compare (IREjercicio iREjercicio1, IREjercicio iREjercicio2) {
			
			return iREjercicio1.getIRProfesor().compareTo(iREjercicio2.getIRProfesor());
			
		}
		
	};
	
	public static final Comparator<IREjercicio> comparatorDificultad = new Comparator<IREjercicio>() {
		
		@Override
		public int compare (IREjercicio iREjercicio1, IREjercicio iREjercicio2) {
			
			return iREjercicio1.getIRDificultad().compareTo(iREjercicio2.getIRDificultad());
			
		}
		
	};
	
}
