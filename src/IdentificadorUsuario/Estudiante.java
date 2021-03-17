package IdentificadorUsuario;
import java.util.HashMap;

public class Estudiante extends Usuario {

	private String carrera;
	private double pga;
	private HashMap<Integer, HashMap<Materia, Double>>Outer;
	
	public Estudiante(String pNombre, String pCodigo) {
		super(pNombre, pCodigo);
		// TODO Auto-generated constructor stub
	}

}
