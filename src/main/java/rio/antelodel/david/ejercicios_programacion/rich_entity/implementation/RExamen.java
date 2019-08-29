package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARComplexSet;

public class RExamen extends ARComplexSet < IExamen, IEjercicioExamen, IREjercicioExamen, IRExamen > implements IRExamen {
	
	// Constructors
	
	public RExamen (IExamen entity) {
		
		super(entity);
		
	}
	
	public RExamen (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
	
		super(MFactory.newIExamen(mes, ano, descripcion, iRTitulacion.getEntity(), visible, abierto));
		
	}
	
	// Get Ejercicios Set
	
	@Override
	public List<IREjercicioExamen> getIREjerciciosSet () {
		
		return IRFactory.getIREjercicioExamenList(new ArrayList<>(getEntity().getIEjerciciosSet()));
		
	}
	
	// Filters
	
	// RExamen
	
	@Override
	public List<IRExamen> getFilterIRExamen () {

		return Arrays.asList(this);
		
	}
	
}
