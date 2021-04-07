package IdentificadorUsuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import Sistema.analizadorArchivo;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;
import funcionalidades.reporteNotas;

public class Estudiante extends Usuario {

	//Atributos
	private String carrera;
	private Double pga;
	private ArrayList<MateriaEstudiante> cursosTomados;
	private int Semestre;
	
	//Constructor
	public Estudiante(String pNombre, String pCodigo, String pCarrera) {
		super(pNombre, pCodigo);
		carrera = pCarrera;
		cursosTomados = new ArrayList<MateriaEstudiante>();
	}

	//Métodos
	public int registrarMaterias(String codigo, int semestre, Double nota, Pensum pensum, Scanner sn)
	{
		var listaMaterias = pensum.darMateriasPensum();
		for(Materia current:listaMaterias)
		{
			if(current.darCodigo().contains(codigo) && current.darPreRequisitos().equals("N/A") && current.darRequisitos().equals("N/A"))
			{
				cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
				return 0;
			}
			else if(current.darCodigo().contains(codigo))
			{
				ArrayList<String> prerrequisitos = new ArrayList<String> (Arrays.asList(current.darPreRequisitos().split("&")));
				ArrayList<String> correquisitos = new ArrayList<String> (Arrays.asList(current.darRequisitos().split("&")));

				if(!prerrequisitos.get(0).equals("N/A"))
				{
					for(Materia tomada:cursosTomados)
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
						cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
					}
				}
				if(!correquisitos.get(0).equals("N/A"))
				{
					{
						for(Materia tomada:cursosTomados)
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
							cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
							return 0;
						}
					}
				}
			if(codigo.contains("CB"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU", "0", true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			}
			else if (codigo.contains("MBIO") || codigo.contains("QUIM") || codigo.contains("MATE") || codigo.contains("FISI") || codigo.contains("BIOL"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva en Ciencias", "0", true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			}
			else if(codigo.contains("IBIO")|| codigo.contains("ICYA") || codigo.contains("IELE") || codigo.contains("IIND") || codigo.contains("IMEC") || codigo.contains("IQUI"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Ingeniería", "0", true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			}
			else if(codigo.contains("ARTI")|| codigo.contains("BCOM") || codigo.contains("MBIT") || codigo.contains("MSIN") || codigo.contains("MINE") || codigo.contains("ISIS") || codigo.contains("MISO"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Profesional", "0", true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
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
					cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
					case 2:
					return 0;
                }
				return 1;
			}
			else
			{
				System.out.println("La materia que ingresaste no fue encontrada.");
				return 0;
			}
			}
		}
		return 0;
		
	}


	public void guardarAvance(analizadorArchivo analizador, File archivo) throws FileNotFoundException, UnsupportedEncodingException
	{
		analizador.guardarAvanceArchivo(archivo, nombre, codigo, carrera, cursosTomados);
		System.out.println("El archivo fue guardado en: " + archivo.getAbsolutePath());
	}

	public Double darPGA()
	{
		return pga;
	}

	public ArrayList<MateriaEstudiante> darCursosTomados()
	{
		return cursosTomados;
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
