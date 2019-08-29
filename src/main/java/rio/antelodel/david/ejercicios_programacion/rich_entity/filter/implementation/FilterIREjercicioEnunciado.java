package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIREjercicioGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIREjercicioEnunciado;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

@Component
public class FilterIREjercicioEnunciado extends AFilter <IREjercicio, IFilterIREjercicioGetter> implements IFilterIREjercicioEnunciado {
	
	// Functions
	
	@Override
	protected <V extends IFilterIREjercicioGetter> List<IREjercicio> getFilterTarget (V object) {
		
		return object.getFilterIREjercicio();
		
	}
	
	@Override
	public boolean matches (IREjercicio target, String filter) {
	
		return 	filter == null
				|| target.getEnunciado().toLowerCase().contains(filter.toLowerCase()) || target.getEnunciado().equalsIgnoreCase(filter)
				|| target.getTitulo().toLowerCase().contains(filter.toLowerCase()) || target.getTitulo().equalsIgnoreCase(filter);

	}
	
}
