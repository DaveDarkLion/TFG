package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.ejercicio;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.getSetTypeList;

import org.springframework.beans.factory.annotation.Autowired;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType.Type;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRExamenAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaEvaluacionAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public abstract class AEjercicioController extends AController {

	// DAOs
	
	@Autowired
	protected IREjercicioDAO iREjercicioDAO;
	public void setIREjercicioDAO (IREjercicioDAO iREjercicioDAO) { this.iREjercicioDAO = iREjercicioDAO; }
	
	@Autowired
	protected IRProfesorDAO iRProfesorDAO;
	public void setIRProfesorDAO (IRProfesorDAO iRProfesorDAO) { this.iRProfesorDAO = iRProfesorDAO; }
	
	@Autowired
	protected IRCategoriaDAO iRCategoriaDAO;
	public void setIRCategoriaDAO (IRCategoriaDAO iRCategoriaDAO) { this.iRCategoriaDAO = iRCategoriaDAO; }
	
	@Autowired
	protected IRTitulacionDAO iRTitulacionDAO;
	public void setIRTitulacionDAO (IRTitulacionDAO iRTitulacionDAO) { this.iRTitulacionDAO = iRTitulacionDAO; }
	
	@Autowired
	protected IRExamenDAO iRExamenDAO;
	public void setIRExamenDAO (IRExamenDAO iRExamenDAO) { this.iRExamenDAO = iRExamenDAO; }
	
	@Autowired
	protected IRPracticaDAO iRPracticaDAO;
	public void setIRPracticaDAO (IRPracticaDAO iRPracticaDAO) { this.iRPracticaDAO = iRPracticaDAO; }
	
	@Autowired
	protected IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO;
	public void setIRPracticaEvaluacionDAO (IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO) { this.iRPracticaEvaluacionDAO = iRPracticaEvaluacionDAO; }
	
	
	// Filters
	
	@Autowired
	protected IFilterIRExamenAnoRange iFilterIRExamenAnoRange;
	public void setIFilterIRExamenAnoRange (IFilterIRExamenAnoRange iFilterIRExamenAnoRange) { this.iFilterIRExamenAnoRange = iFilterIRExamenAnoRange; }
	
	@Autowired
	protected IFilterIRPracticaAnoRange iFilterIRPracticaAnoRange;
	public void setIFilterIRPracticaAnoRange (IFilterIRPracticaAnoRange iFilterIRPracticaAnoRange) { this.iFilterIRPracticaAnoRange = iFilterIRPracticaAnoRange; }
	
	@Autowired
	protected IFilterIRPracticaEvaluacionAnoRange iFilterIRPracticaEvaluacionAnoRange;
	public void setIFilterIRPracticaEvaluacionAnoRange (IFilterIRPracticaEvaluacionAnoRange iFilterIRPracticaEvaluacionAnoRange) { this.iFilterIRPracticaEvaluacionAnoRange = iFilterIRPracticaEvaluacionAnoRange; }
	
	// View
	
	protected static final String VIEW_MAIN_PATH = "ejercicio/";
	
	// Functions
	
	protected boolean isSet (Type type) {
		
		return type != Type.ALL;
		
	}
	
	protected boolean isComplexSet (Type type) {
		
		return isSet(type) && type != Type.CART;
		
	}
	
	protected boolean isAbierto (Type type, int setValue) {
		
		return (isComplexSet(type) && getSetEntity(type, setValue).isAbierto()) || type == Type.CART;
		
	}
	
	protected IRComplexSet<?, ?, ?, ?> getSetEntity (Type type, int setValue) {
		
		switch (type) {
	
			case EXAMEN : 				return iRExamenDAO.find(setValue);
			case PRACTICA :				return iRPracticaDAO.find(setValue);
			case PRACTICA_EVALUABLE :	return iRPracticaEvaluacionDAO.find(setValue);
			
			// Never executed
				
			default : return null;
			
		}
		
	}
	
	protected boolean isEditionMode (Type type, boolean editionMode) {
		
		return isSet(type) && editionMode && userIsPrivileged();
		
	}
	
	protected SetType getSetType (int iSetType) {
		
		if (iSetType < 0 || iSetType > getSetTypeList().size()) return getSetTypeList().get(0);
		else return getSetTypeList().get(iSetType);
		
	}
	
}
