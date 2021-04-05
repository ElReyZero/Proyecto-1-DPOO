package IdentificadorUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import curriculo.Materia;
import curriculo.Pensum;

public class Estudiante extends Usuario {

	//Atributos
	private String carrera;
	private double pga;
	private ArrayList<HashMap<Materia, Double>> cursosTomadosGrande;
	private ArrayList<Materia> cursosTomadosSmall;
	private HashMap<Materia, Double> materiasSemestre;
	private int Semestre;
	
	//Constructor
	public Estudiante(String pNombre, String pCodigo, String pCarrera) {
		super(pNombre, pCodigo);
		carrera = pCarrera;
		cursosTomadosGrande = new ArrayList<HashMap<Materia, Double>>();
		cursosTomadosSmall = new ArrayList<Materia>();
		materiasSemestre = new HashMap<Materia, Double>();
	}

	//Métodos

	public void darAvance()
	{
		//TODO
	}

	public void registrarMaterias(String codigo, int pSemestre, Double nota, Pensum pensum)
	{
		Semestre = pSemestre;
		var listaMaterias = pensum.darMateriasPensum();
		for(Materia current:listaMaterias)
		{
			if(current.darCodigo().contains(codigo) && current.darPreRequisitos() == "N/A" && current.darRequisitos() == "N/A")
			{
				materiasSemestre.put(current, nota);
				cursosTomadosSmall.add(current);
			}
			else if(current.darCodigo().contains(codigo))
			{
				String prerrequisitos = current.darPreRequisitos();
				String correquisitos = current.darRequisitos();

				if(prerrequisitos.contains("&"))
				{
					int contador=0;
					int posicion=0;
					String copiaPrerrequisitos = prerrequisitos;
					while (posicion != -1)
					 {
						contador++;           
						posicion = copiaPrerrequisitos.indexOf("&", posicion + 1);
					 }
					 contador +=1;
					for(Materia tomada:cursosTomadosSmall)
					{
					if(copiaPrerrequisitos.contains(tomada.darCodigo()))
					{
						copiaPrerrequisitos.replace(tomada.darCodigo(),"");
						contador -=1;
					}
				}
					if (contador>0)
					{
					}
					else
					{
						materiasSemestre.put(current, nota);
						cursosTomadosSmall.add(current);
					}

				}
				else
				{
					for(Materia tomada:cursosTomadosSmall)
				{
					if(tomada.darCodigo().contains(prerrequisitos))
					{
						
					}
				}
				}
				
			}
		}
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

	public int darSemestre()
	{
		return Semestre;
	}
}
