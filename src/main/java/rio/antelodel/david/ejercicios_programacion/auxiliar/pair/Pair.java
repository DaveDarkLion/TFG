package rio.antelodel.david.ejercicios_programacion.auxiliar.pair;

import java.util.Objects;

public class Pair <T, U> {

	// Variables
	
	private T first;
	private U second;
	
	// Constructors
	
	@SuppressWarnings("unchecked")
	public Pair (T id) {
		
		this.first = id;
		this.second = (U)id;
		
	}
	
	public Pair (T id, U name) {
	
		this.first = id;
		this.second = name;
		
	}
	
	// Getters
	
	public T getFirst () { return first; }
	public U getSecond () { return second; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Pair<?, ?>) {
			
			Pair<?, ?> pair = (Pair<?, ?>)o;
			return getFirst().equals(pair.getFirst()) && getSecond().equals(pair.getSecond());
			
		}
		
		else return false;
		
	}
	
	// HashCode
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getFirst(), getSecond());
		
	}
	
}
