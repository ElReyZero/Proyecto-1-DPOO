package IdentificadorUsuario;
import java.util.HashMap;
import curriculo.Materia;

public class Estudiante extends Usuario {

	//Atributos
	private String carrera;
	private double pga;
	private HashMap<Integer, HashMap<Materia, Double>> cursosTomados;
	private HashMap<String, Double> promedioSemestre;
	
	//Constructor
	public Estudiante(String pNombre, String pCodigo, String pCarrera) {
		super(pNombre, pCodigo);
		carrera = pCarrera;
		cursosTomados = new HashMap<Integer, HashMap<Materia, Double>>();
		promedioSemestre = new HashMap<String, Double>();
	}

	//Métodos

	public void darAvance()
	{
		//TODO
	}

	public void registrarMaterias()
	{
		//TODO
	}

	public Double darPGA()
	{
		return pga;
	}

	public HashMap<Integer, HashMap<Materia, Double>> darCursosTomados()
	{
		return cursosTomados;
	}

	public HashMap<String, Double> darPromedioSemestre()
	{
		return promedioSemestre;
	}

	public String darCarrera()
	{
		return carrera;
	}
}
