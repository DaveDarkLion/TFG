package rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.encoder;

import java.util.HashMap;
import java.util.Map;

public class LaTexEncoder implements ILatexEncoder {

	Map<String, String> charactersSpecial = new HashMap<>();
	Map<String, String> characters = new HashMap<>();
	
	public LaTexEncoder () {
		
		setupSpecialSymbols();
		
		setupGreekSymbols();
		setupOther();
		
	}
	
	public void setupGreekSymbols () {
		
		characters.put("α", "$\\alpha$");
		
		characters.put("ß", "$\\beta$");
		
		characters.put("Γ", "$\\Gamma$");
		characters.put("γ", "$\\gamma$");
		
		characters.put("Δ", "$\\Delta$");
		characters.put("δ", "$\\delta$");
		
		characters.put("ε", "$\\epsilon$");
		
		characters.put("ζ", "$\\zeta$");
		
		characters.put("η", "$\\eta$");
		
		characters.put("Θ", "$\\Theta$");
		characters.put("θ", "$\\theta$");
		
		characters.put("ι", "$\\iota$");
		
		characters.put("κ", "$\\kappa$");
		
		characters.put("Λ", "$\\Lambda$");
		characters.put("λ", "$\\lambda$");
		
		characters.put("μ", "$\\mu$");
		
		characters.put("ν", "$\\nu$");
		
		characters.put("Ξ", "$\\Xi$");
		characters.put("ξ", "$\\xi$");
		
		characters.put("Π", "$\\Pi$");
		characters.put("π", "$\\pi$");
		
		characters.put("ρ", "$\\rho$");
		
		characters.put("Σ", "$\\Sigma$");
		characters.put("σ", "$\\sigma$");
		
		characters.put("τ", "$\\tau$");
		
		characters.put("Υ", "$\\Upsilon$");
		characters.put("υ", "$\\upsilon$");
		
		characters.put("Φ", "$\\Phi$");
		characters.put("φ", "$\\phi$");
		
		characters.put("χ", "$\\chi$");
		
		characters.put("Ψ", "$\\Psi$");
		characters.put("ψ", "$\\psi$");
		
		characters.put("Ω", "$\\Omega$");
		characters.put("ω", "$\\omega$");
		
	}
	
	public void setupSpecialSymbols () {
		
		charactersSpecial.put("$", "dólares");
		
	}
	
	public void setupOther () {
		
		characters.put("€", "euros");
		
	}
	
	public String encode (String text) {
		
		text = encodeSpecial(text);
		for (Map.Entry<String, String> element : characters.entrySet()) text = text.replace(element.getKey(), element.getValue());
		
		return text;
		
	}
	
	private String encodeSpecial (String text) {
		
		for (Map.Entry<String, String> element : charactersSpecial.entrySet()) text = text.replace(element.getKey(), element.getValue());
		
		return text;
		
	}
	
}
