package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

public class IRDificultadHelper {

	// Private Constructor
	
	private IRDificultadHelper () { }
	
	// Seek closest RDificultad
	
	public static IRDificultad getIRDificultadAverage (List<IRDificultad> iRDificultadCurrentList, List<IRDificultad> iRDificultadAllList) {
		
		float avg = 0f;
		int num = 0;
		
		for (IRDificultad iRD : iRDificultadCurrentList) {
				
			avg += iRD.getValor();
			num++;
				
		}

		if (num != 0) avg /= num;
		
		return getIRDificultadClosest(avg, iRDificultadAllList);
		
	}
	
	public static IRDificultad getIRDificultadClosest (float fDificultad, List<IRDificultad> iRDificultadAllList) {
	
		float currentDif = -1;
		
		IRDificultad iRDificultad = null;
		for (IRDificultad iRD : iRDificultadAllList) {
		
			if (currentDif < 0 || Math.abs(fDificultad - iRD.getValor()) < currentDif) {
				
				iRDificultad = iRD;
				currentDif = Math.abs(fDificultad - iRD.getValor());
				
			}
			
		}
		
		return iRDificultad;
		
	}
	
}
