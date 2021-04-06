package Sistema;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import curriculo.Pensum;

public class main 
{

	public static void main(String[] args) 
	{
		File archivo = new File("./data/curriculoISIS.csv");
		analizadorArchivo analizador = new analizadorArchivo();
		analizador.cargarDatos(archivo);
        Pensum pensum = analizador.darPensum();
		Consola(pensum);
	}

	public static void Consola(Pensum pensum)
	{

		Scanner sn = new Scanner(System.in);
        int opcion; 

			System.out.println("Bienvenido a Banner \n-----------------------");
			System.out.println("Elija su tipo de usuario");
 
            System.out.println("1. Estudiante");
            System.out.println("2. Coordinador Académico");
            System.out.println("3. Salir");
 
            try {
                System.out.println("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                    String nombreEstudiante = "";
                    String codigoEstudiante = "";
                    String carrera = "";
                    System.out.println("\nBienvenido estudiante \n-----------------------");
                    System.out.println("Digite su nombre:");
                    try
                    {
                    nombreEstudiante = sn.next();
                    } catch (InputMismatchException e) 
                    {
                    System.out.println("Debes insertar tu nombre");
                    sn.next();
                    }
                    System.out.println("Digite su codigo:");
                    try
                    {
                    codigoEstudiante = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu codigo");
                        sn.next();
                    }
                    System.out.println("Digite su carrera:");
                    try
                    {
                    carrera = sn.next();
                    } catch (InputMismatchException e) 
                    {
                    System.out.println("Debes insertar el nombre de tu carrera");
                    sn.next();
                    }
                    Estudiante estudiante = new Estudiante(nombreEstudiante,codigoEstudiante,carrera);
                    System.out.println("Bienvenido " + nombreEstudiante+ "\n-----------------------");
                    seleccionEstudiante(sn, pensum, estudiante);                       
                    case 2:
                    String nombreCoordinador = "";
                    String codigoCoordinador = "";
                    String departamento = "";
                    System.out.println("\nBienvenido estudiante \n-----------------------");
                    System.out.println("Digite su nombre:");
                    try
                    {
                        nombreCoordinador = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu nombre");
                        sn.next();
                    }
                    System.out.println("Digite su codigo:");
                    try
                    {
                        codigoCoordinador = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu codigo");
                        sn.next();
                    }
                    System.out.println("Digite su departamento:");
                    try
                    {
                        departamento = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu departamento");
                        sn.next();
                    }
                    CoordinadorAcademico coordinador = new CoordinadorAcademico(nombreCoordinador,codigoCoordinador,departamento);
                        break;
                    case 3:
                        sn.close();
                        System.exit(0);
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            
        }
	

    public static void seleccionEstudiante(Scanner sn, Pensum pensum, Estudiante estudiante)
    {
        System.out.println("Seleccione la opción a realizar: ");
        System.out.println("1. Registrar Materias");
        System.out.println("2. Salir");
        int opcion = sn.nextInt();
        switch (opcion)
        {
            case 1:
            registrarMateriaEstudiante(sn, estudiante, pensum);     
            case 2:
            sn.close();
            System.exit(0); 
            }
    }

    public static void registrarMateriaEstudiante(Scanner sn, Estudiante estudiante, Pensum pensum)
    {
        int semestre = 0;
        Double nota = 0.0;
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que viste la materia: ");
        try
        {
            semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }
        System.out.println("Introduce la nota definitiva que sacaste en la materia.");
        try
        {
            nota = Double.valueOf(sn.next());
            if(nota>5.0 || nota < 0.0)
            {
                System.out.println("Debes insertar una nota válida.");
            }
        }
        catch (InputMismatchException e) 
        {
            System.out.println("InputMismatchException: Debes insertar una nota válida.");
            e.printStackTrace();
        }       
            
            int materiaRegistrada = estudiante.registrarMaterias(codigoMateria, semestre, nota, pensum, sn);
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaEstudiante(sn, estudiante, pensum);
                case 2:
                seleccionEstudiante(sn, pensum, estudiante); 
        }
    }
}


