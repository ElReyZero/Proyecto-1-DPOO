package IdentificadorUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
		Semestre = 1;
		cursosTomadosGrande = new ArrayList<HashMap<Materia, Double>>();
		cursosTomadosSmall = new ArrayList<Materia>();
		materiasSemestre = new HashMap<Materia, Double>();
	}

	//Métodos

	public void darAvance()
	{
		//TODO
	}

	public int registrarMaterias(String codigo, int pSemestre, Double nota, Pensum pensum, Scanner sn)
	{
		if(Semestre != pSemestre)
		{
			cursosTomadosGrande.add(materiasSemestre);
			Semestre = pSemestre;
		}
		var listaMaterias = pensum.darMateriasPensum();
		for(Materia current:listaMaterias)
		{
			if(current.darCodigo().contains(codigo) && current.darPreRequisitos().equals("N/A") && current.darRequisitos().equals("N/A"))
			{
				materiasSemestre.put(current, nota);
				cursosTomadosSmall.add(current);
				return 0;
			}
			else if(current.darCodigo().contains(codigo))
			{
				ArrayList<String> prerrequisitos = new ArrayList<String> (Arrays.asList(current.darPreRequisitos().split("&")));
				ArrayList<String> correquisitos = new ArrayList<String> (Arrays.asList(current.darRequisitos().split("&")));

				if(!prerrequisitos.get(0).equals("N/A"))
				{
					for(Materia tomada:cursosTomadosSmall)
					{
						for(int i = 0; prerrequisitos.size() > i; i++)
						{
							if(prerrequisitos.get(i).contains(tomada.darCodigo()))
							{
								prerrequisitos.remove(i);
							}
						}
				}
					if (prerrequisitos.size()!= 0)
					{
						System.out.println("Se está intentando registrar una materia sin haber cumplido todos los prerrequisitos previamente.\nPrerrequisito(s) sin cumplir:\n" + String.join("\n", prerrequisitos));
					}
					else
					{
						materiasSemestre.put(current, nota);
						cursosTomadosSmall.add(current);
					}
				}
				if(!correquisitos.get(0).equals("N/A"))
				{
					{
						for(Materia tomada:cursosTomadosSmall)
						{
							for(int i = 0; correquisitos.size() > i; i++)
							{
								if(correquisitos.get(i).contains(tomada.darCodigo()))
								{
									correquisitos.remove(i);
								}
							}
					}
						if (correquisitos.size()!= 0)
						{
							System.out.println("Se está intentando registrar una materia sin haber inscrito todos los correquisitos previamente.\nCorrequisitos(s) sin inscribir:\n" + String.join("\n", correquisitos));
							return 0;
						}
						else
						{
							materiasSemestre.put(current, nota);
							cursosTomadosSmall.add(current);
							return 0;
						}
					}
				}
			if(codigo.contains("CB"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU", "0", true);
				materiasSemestre.put(nuevaMateria, nota);
				cursosTomadosSmall.add(nuevaMateria);
			}
			else if (codigo.contains("MBIO") || codigo.contains("QUIM") || codigo.contains("MATE") || codigo.contains("FISI") || codigo.contains("BIOL"))
			{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva en Ciencias", "0", true);
			materiasSemestre.put(nuevaMateria, nota);
			cursosTomadosSmall.add(nuevaMateria);
			}
			else if(codigo.contains("IBIO")|| codigo.contains("ICYA") || codigo.contains("IELE") || codigo.contains("IIND") || codigo.contains("IMEC") || codigo.contains("IQUI"))
			{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Ingeniería", "0", true);
			materiasSemestre.put(nuevaMateria, nota);
			cursosTomadosSmall.add(nuevaMateria);
			}
			else if(codigo.contains("ARTI")|| codigo.contains("BCOM") || codigo.contains("MBIT") || codigo.contains("MSIN") || codigo.contains("MINE") || codigo.contains("ISIS") || codigo.contains("MISO"))
			{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Profesional", "0", true);
			materiasSemestre.put(nuevaMateria, nota);
			cursosTomadosSmall.add(nuevaMateria);
			}				
			else if (codigo.contains("-"))
			{
				System.out.println("No se encontró la materia en el pensum, ¿estás seguro de que quieres inscribrla como curso de libre elección?");
				System.out.println("1. Sí");
                System.out.println("2. No");
				int opcion = sn.nextInt();
                switch (opcion)
                {
                    case 1:
					Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Curso de Libre Elección", "0", true);
					materiasSemestre.put(nuevaMateria, nota);
					cursosTomadosSmall.add(nuevaMateria);
					case 2:
					return 0;
                }
				return 1;
			}
			else
			{
				return 0;
			}
			}
		}
		return 0;
		
	}

	public Double darPGA()
	{
		return pga;
	}

	public ArrayList<HashMap<Materia, Double>> darCursosTomadosGrande()
	{
		return cursosTomadosGrande;
	}

	public ArrayList<Materia> darCursosTomadosSmall()
	{
		return cursosTomadosSmall;
	}

	public String darCarrera()
	{
		return carrera;
	}

	public int darSemestre()
	{
		return Semestre;
	}

	public HashMap<Materia, Double> darMateriasSemestre()
	{
		return materiasSemestre;
	}
}
