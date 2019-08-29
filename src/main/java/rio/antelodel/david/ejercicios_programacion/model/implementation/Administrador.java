package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

@Entity
@Table(name="administrador")
public class Administrador implements IAdministrador {

	private static final long serialVersionUID = 2049889234750863031L;
	
	@Id
	private String email;
	
	@MapsId("email")
	@OneToOne(targetEntity=Persona.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="email")
	private IPersona iPersona;
	
	public Administrador () { }
	
	public Administrador (IPersona iPersona) {
		
		super();
		
		this.iPersona = iPersona;
		this.email = iPersona.getEmail();
		
	}
	
	@Override
	public IPersona getIPersona() { return iPersona; }
	@Override
	public void setIPersona (IPersona iPersona) { this.iPersona = iPersona; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Administrador) {
		
			Administrador administrador = (Administrador)o;
			return getIPersona().equals(administrador.getIPersona());
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIPersona());
		
	}
	
}
