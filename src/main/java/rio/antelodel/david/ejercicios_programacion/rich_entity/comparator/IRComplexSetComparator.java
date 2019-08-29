package rio.antelodel.david.ejercicios_programacion.rich_entity.comparator;

import java.util.Comparator;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public interface IRComplexSetComparator {

	@SuppressWarnings({ "rawtypes" })
	public static final  Comparator<IRComplexSet> comparatorDescripcion = new Comparator<IRComplexSet>() {
		
		@Override
		public int compare (IRComplexSet iRComplexSet1, IRComplexSet iRComplexSet2) {
			
			return iRComplexSet1.getDescripcion().compareToIgnoreCase(iRComplexSet2.getDescripcion());
			
		}
		
	};
	
	@SuppressWarnings({ "rawtypes" })
	public static final  Comparator<IRComplexSet> comparatorTitulacion = new Comparator<IRComplexSet>() {
		
		@Override
		public int compare (IRComplexSet iRComplexSet1, IRComplexSet iRComplexSet2) {
			
			return iRComplexSet1.getIRTitulacion().compareTo(iRComplexSet2.getIRTitulacion());
			
		}
		
	};
	
	@SuppressWarnings({ "rawtypes" })
	public static final  Comparator<IRComplexSet> comparatorMes = new Comparator<IRComplexSet>() {
		
		@Override
		public int compare (IRComplexSet iRComplexSet1, IRComplexSet iRComplexSet2) {
			
			return Integer.compare(iRComplexSet1.getMes(), iRComplexSet2.getMes());
			
		}
		
	};
	
	@SuppressWarnings({ "rawtypes" })
	public static final  Comparator<IRComplexSet> comparatorAno = new Comparator<IRComplexSet>() {
		
		@Override
		public int compare (IRComplexSet iRComplexSet1, IRComplexSet iRComplexSet2) {
			
			int anoComparison = Integer.compare(iRComplexSet1.getAno(), iRComplexSet2.getAno());
			int mesComparison = Integer.compare(iRComplexSet1.getMes(), iRComplexSet2.getMes());
			
			if (anoComparison != 0) return anoComparison;
			else return mesComparison;
			
		}
		
	};
	
}
