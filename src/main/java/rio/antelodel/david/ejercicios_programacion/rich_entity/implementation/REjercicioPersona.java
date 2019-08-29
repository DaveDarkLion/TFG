package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.AREjercicioSet;

public class REjercicioPersona extends AREjercicioSet < IEjercicioPersona, IREjercicioPersona > implements IREjercicioPersona {

	// Constructors
	
	public REjercicioPersona () { }
	
	public REjercicioPersona (IEjercicioPersona entity) {
		
		super(entity);
		
	}
	
	public REjercicioPersona (IREjercicio rEjercicio, IRPersona rPersona, int position) {
	
		super(MFactory.newIEjercicioPersona(rEjercicio.getEntity(), rPersona.getEntity(), position));
		
	}
	
	// Entity Functions
	
	@Override
	public IRPersona getIRPersona () { return IRFactory.newIRPersona(getEntity().getIPersona()); }
	@Override
	public void setIRPersona (IRPersona iRPersona) { getEntity().setIPersona(iRPersona.getEntity()); }
	
	// Get RSet
	
	@Override
	public IRSet<?, ?, ?, ?> getIRSet () { return getIRPersona(); }
	
}
