package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RArchivo extends ARichEntity < IArchivo > implements IRArchivo {

	// Constructors
	
	public RArchivo (IArchivo entity) {
		
		super(entity);
		
	}
	
	public RArchivo (String ruta) {
		
		super(MFactory.newIArchivo(ruta));
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public String getRuta () { String ruta = getEntity().getRuta(); ruta = ruta.replace("\\", "\\\\"); return ruta; }
	@Override
	public void setRuta (String ruta) { getEntity().setRuta(ruta); }
	
	@Override
	public String getNombre () { return new File(getRuta()).getName(); }
	
	@Override
	public boolean isVisible () {
		
		List<IREjercicio> iREjercicioList = new ArrayList<>();
		
		iREjercicioList.addAll(IRFactory.getIREjercicioList(new ArrayList<>(getEntity().getIEjerciciosEntrada())));
		iREjercicioList.addAll(IRFactory.getIREjercicioList(new ArrayList<>(getEntity().getIEjerciciosValidacion())));
		iREjercicioList.addAll(IRFactory.getIREjercicioList(new ArrayList<>(getEntity().getIEjerciciosSolucion())));
		
		for (IREjercicio iRE : iREjercicioList) if (iRE.isVisible()) return true;
		return false;
		
	}
	
	// Data

	@Override
	public JSONObject getData () {

		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("ruta", org.json.simple.JSONObject.escape(getRuta()));
		data.put("nombre", org.json.simple.JSONObject.escape(new File(getRuta()).getName()));
		
		return data;
	
	}

	@Override
	public JSONObject getFullData () {
		
		return getData();
		
	}
	
	// Comparable

	@Override
	public int compareTo (IRArchivo iRArchivo) {
		
		return getNombre().compareToIgnoreCase(iRArchivo.getNombre());
		
	}
	
}
