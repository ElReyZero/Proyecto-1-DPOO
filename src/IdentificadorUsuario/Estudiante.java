package IdentificadorUsuario;
import java.util.HashMap;

public class Estudiante extends Usuario {

	private String carrera;
	private double pga;
	private HashMap<Integer, HashMap<Materia, Double>> cursosTomados;
	private HashMap<String, Double> promedioSemestre;
	
	public Estudiante(String pNombre, String pCodigo) {
		super(pNombre, pCodigo);
		cursosTomados = new HashMap();
	}

}
