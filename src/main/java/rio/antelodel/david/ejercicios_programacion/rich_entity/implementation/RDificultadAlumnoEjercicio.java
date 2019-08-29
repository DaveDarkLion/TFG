package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RDificultadAlumnoEjercicio extends ARichEntity <IDificultadAlumnoEjercicio> implements IRDificultadAlumnoEjercicio {

	// Constructors
	
	public RDificultadAlumnoEjercicio (IDificultadAlumnoEjercicio entity) {
		
		super(entity);
		
	}
	
	public RDificultadAlumnoEjercicio (IRAlumno iRAlumno, IREjercicio iREjercicio, IRDificultad iRDificultad) {
		
		super(MFactory.newIDificultadAlumnoEjercicio(iRAlumno.getEntity(), iREjercicio.getEntity(), iRDificultad.getEntity()));
		
	}
	
	// Entity Functions
	
	@Override
	public IRAlumno getIRAlumno () { return IRFactory.newIRAlumno(getEntity().getIAlumno()); }
	
	@Override
	public IREjercicio getIREjercicio () { return IRFactory.newIREjercicio(getEntity().getIEjercicio()); }
	
	@Override
	public IRDificultad getIRDificultad () { return IRFactory.newIRDificultad(getEntity().getIDificultad()); }
	@Override
	public void setIRDificultad (IRDificultad iRDificultad) { getEntity().setIDificultad(iRDificultad.getEntity()); }

}
