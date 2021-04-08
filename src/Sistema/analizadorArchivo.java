package Sistema;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class analizadorArchivo {

    private Pensum pensum;

    public analizadorArchivo()
    {
        pensum = null;
    }
    
    public void cargarPensum(File archivo)
    {
        try
			{
                ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
				String materiasString = "";
				String nivel1 = "";
				String nivel2 = "";
                int totalcred = 0;
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				br.readLine();
                String linea = br.readLine();
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String nombre = partes[0];
					String codigo = partes[1];
                    String prerrequisitos = partes[2];
                    String correquisitos = partes[3];
                    int creditos = Integer.parseInt(partes[4]);
                    totalcred += creditos;
                    int nivel = Integer.parseInt(partes[5]);
                    String tipoMateria = partes[6];
                    boolean semanas = Boolean.parseBoolean(partes[7]);
                    Materia currentSubject = new Materia(nombre, codigo, prerrequisitos, correquisitos, creditos, tipoMateria, nivel, semanas);
                    listaMaterias.add(currentSubject);
					linea = br.readLine();
					materiasString += codigo+";";
					if(currentSubject.darNivel() == 1) 
					{
						nivel1 += codigo+";";
					}
					else if(currentSubject.darNivel() == 2)
					{
						nivel2 += codigo + ";";
					} 
				}
				br.close();
                pensum = new Pensum(totalcred, archivo.getName(), listaMaterias, materiasString, nivel1, nivel2);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número no se pudo convertir a int ...");
				e.printStackTrace();
			}
    }

	public void guardarAvanceEstudianteArchivo(File archivo, String nombre, String codigo, String carrera, ArrayList<MateriaEstudiante> materias) throws FileNotFoundException, UnsupportedEncodingException
	{
		OutputStream os = new FileOutputStream(archivo);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		pw.println(nombre);
		pw.println(codigo);
		pw.println(carrera);
		
		for (MateriaEstudiante materia : materias)
		{	

			Double nota = materia.darNota();
			String curso = materia.darCodigo();
			int creditos = materia.darCreditos();
			int numSemestre = materia.darSemestre();
			pw.println(curso + ";" + nota + ";" + creditos + ";" + numSemestre);			
		}
		pw.close();
	}

	public void cargarAvanceEstudiante(File archivo, Estudiante estudiante, Scanner sn)
	{
		try
			{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				br.readLine();
				br.readLine();
				br.readLine();
                String linea = br.readLine();
				int caso = 0;
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String codigo = partes[0];
					Double nota = Double.parseDouble(partes[1]);
                    int semestre = Integer.parseInt(partes[3]);
                    caso = estudiante.registrarMaterias(codigo, semestre, nota, pensum, sn);
					linea = br.readLine();
				}
				if(caso == 0)
				{
					System.out.println("Materias cargadas satisfactoriamente");
				}
				System.out.println();
				br.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número no se pudo convertir a int ...");
				e.printStackTrace();
			}
	}

    public Pensum darPensum()
    {
        return pensum;
    }
}
