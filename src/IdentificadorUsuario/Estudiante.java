package IdentificadorUsuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Sistema.analizadorArchivo;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class Estudiante extends Usuario {

	//Atributos
	private String carrera;
	private Double pga;
	private ArrayList<MateriaEstudiante> cursosTomados;
	private int Semestre;
	private String tomadosString;
	
	//Constructor
	public Estudiante(String pNombre, String pCodigo, String pCarrera) {
		super(pNombre, pCodigo);
		carrera = pCarrera;
		cursosTomados = new ArrayList<MateriaEstudiante>();
		tomadosString = "-----------------------------------\n";
	}

	//Métodos
	public int registrarMaterias(String codigo, int semestre, double nota, Pensum pensum, Scanner sn)
	{
		var listaMaterias = pensum.darMateriasPensum();
		String matString = pensum.darMateriasString();
		
		if(!tomadosString.contains(codigo))
		{	if(matString.contains(codigo))
			{
				for(Materia current:listaMaterias)
				{
					if (current.darCodigo().contains(codigo) && current.darNivel() >=3)
					{
						//TODO
					}
					else if(current.darCodigo().contains(codigo) && current.darPreRequisitos().equals("N/A") && current.darRequisitos().equals("N/A"))
					{
						cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
						tomadosString += current.darCodigo()+"\n";
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
								System.out.println("Se está intentando registrar "+ codigo +" sin haber cumplido todos los prerrequisitos previamente.\nPrerrequisito(s) sin cumplir:\n" + String.join("\n", prerrequisitos));
								return 1;
							}
							else
							{
								cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
								tomadosString += current.darCodigo()+"\n";
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
									System.out.println("Se está intentando registrar "+ codigo +" sin haber inscrito todos los correquisitos previamente.\nCorrequisitos(s) sin inscribir:\n" + String.join("\n", correquisitos));
									System.out.println(tomadosString);
									return 1;
								}
								else
								{
									cursosTomados.add(new MateriaEstudiante(current, nota, semestre));
									tomadosString += current.darCodigo()+"\n";
									return 0;
								}
							}
						}
					}
				}
			}
		else if(codigo.contains("CB"))
		{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU", 0, true);
			cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			tomadosString += nuevaMateria.darCodigo();
			return 0;
		}
		else if (codigo.contains("MBIO") || codigo.contains("QUIM-2") || codigo.contains("MATE-2") || codigo.contains("MATE-3")|| codigo.contains("MATE-1107") || codigo.contains("FISI-1038") || codigo.contains("FISI-1048") || codigo.contains("BIOL-3"))
		{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva en Ciencias", 0, true);
			cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			tomadosString += nuevaMateria.darCodigo();
			return 0;
		}
		else if(codigo.contains("IBIO")|| codigo.contains("ICYA") || codigo.contains("IELE") || codigo.contains("IIND") || codigo.contains("IMEC") || codigo.contains("IQUI"))
		{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Ingeniería", 0, true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
				tomadosString += nuevaMateria.darCodigo();
				return 0;
		}
		else if(codigo.contains("ARTI")|| codigo.contains("BCOM") || codigo.contains("MBIT") || codigo.contains("MSIN") || codigo.contains("MINE") || codigo.contains("ISIS-4") || codigo.contains("MISO"))
		{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Profesional", 4, true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
				tomadosString += nuevaMateria.darCodigo();
				return 0;
		}				
		else if (codigo.contains("-"))
		{
			System.out.println("No se encontró la materia "+ codigo+" en el pensum, ¿estás seguro de que quieres inscribrla como curso de libre elección?");
			System.out.println("1. Sí");
            System.out.println("2. No");
			int opcion = sn.nextInt();
            switch (opcion)
            {
                case 1:
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Curso de Libre Elección", 0, true);
				cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
				tomadosString += nuevaMateria.darCodigo();
				return 0;
				case 2:
				return 0;
            }
			return 0;
		}				
	}
	else if(codigo.contains("CB"))
		{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU", 0, true);
			cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
			tomadosString += nuevaMateria.darCodigo();
		}
	else if(codigo.contains("ISIS-4"))
	{
		Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Ingeniería", 0, true);
		cursosTomados.add(new MateriaEstudiante(nuevaMateria, nota, semestre));
		tomadosString += nuevaMateria.darCodigo();
	}
	else
	{
		System.out.println(codigo +" no fue encontrada.");
		System.out.println(tomadosString);
		return 2;
	}
		
	return 0;
		
	}


	public void guardarAvance(analizadorArchivo analizador, File archivo) throws FileNotFoundException, UnsupportedEncodingException
	{
		analizador.guardarAvanceEstudianteArchivo(archivo, nombre, codigo, carrera, cursosTomados);
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
