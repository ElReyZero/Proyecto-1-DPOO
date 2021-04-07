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
import java.util.HashMap;
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
                int totalcred = 0;
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String headerline = br.readLine();
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
                    String nivel = partes[5];
                    String tipoMateria = partes[6];
                    boolean semanas = Boolean.parseBoolean(partes[7]);
                    Materia currentSubject = new Materia(nombre, codigo, prerrequisitos, correquisitos, creditos, nivel, tipoMateria, semanas);
                    listaMaterias.add(currentSubject);
					linea = br.readLine();  
				}
				br.close();
                pensum = new Pensum(totalcred, archivo.getName(), listaMaterias);
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

	public void guardarAvanceArchivo(File archivo, String nombre, String codigo, String carrera, ArrayList<MateriaEstudiante> materias) throws FileNotFoundException, UnsupportedEncodingException
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
				String nameline = br.readLine();
				String codeline = br.readLine();
				String majorline = br.readLine();
                String linea = br.readLine();
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String codigo = partes[0];
					Double nota = Double.parseDouble(partes[1]);
                    int semestre = Integer.parseInt(partes[3]);
                    int casos = estudiante.registrarMaterias(codigo, semestre, nota, pensum, sn);

					if(casos == 1)
					{
						System.out.println("Hubo un problema en el registro de la materia " + partes[0]+".\nRevisa que hayas cumplido con correquisitos y prerrequisitos antes de inscribir esta.");
						linea = null;
					}
					else if(casos == 2)
					{
						System.out.println("La materia " + partes[0] + "no fue encontrada como materia válida.");
						linea = null;
					}
					linea = br.readLine();
				}
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
