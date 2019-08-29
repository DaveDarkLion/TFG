package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARComplexSet;

@Transactional
public class RPractica extends ARComplexSet < IPractica, IEjercicioPractica, IREjercicioPractica, IRPractica > implements IRPractica {
	
	// Constructors
	
	public RPractica (IPractica entity) {
		
		super(entity);
		
	}
	
	public RPractica (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
		
		super(MFactory.newIPractica(mes, ano, descripcion, iRTitulacion.getEntity(), visible, abierto));
		
	}
	
	// Get Ejercicios Set
	
	@Override
	public List<IREjercicioPractica> getIREjerciciosSet() {
		
		return IRFactory.getIREjercicioPracticaList(new ArrayList<>(getEntity().getIEjerciciosSet()));
		
	}
	
	// Filters
	
	// RPractica
	
	@Override
	public List<IRPractica> getFilterIRPractica () {

		return new ArrayList<>(Arrays.asList(this));
		
	}

}
