package Sistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import curriculo.Pensum;
import funcionalidades.candidaturaGrado;
import funcionalidades.planeador;
import funcionalidades.reporteNotas;

///Hecho por: Juan Andrés Romero C - 202013449
/// Luccas Rojas - 201923052

public class systemMain 
{

	public static void main(String[] args)
	{
		File archivo = new File("./data/curriculoISIS.csv");
		analizadorArchivo analizador = new analizadorArchivo();
		analizador.cargarPensum(archivo);
        Pensum pensum = analizador.darPensum();
		Consola(pensum, analizador);
	}

	public static void Consola(Pensum pensum, analizadorArchivo analizador)
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
                    seleccionEstudiante(sn, pensum, estudiante, analizador);      
                    break;                 
                    case 2:
                    String nombreCoordinador = "";
                    String codigoCoordinador = "";
                    String departamento = "";
                    System.out.println("\nBienvenido coordinador \n-----------------------");
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
                    seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, null);
                    break;
                    case 3:
                        sn.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            
        }
	

    public static void seleccionEstudiante(Scanner sn, Pensum pensum, Estudiante estudiante, analizadorArchivo analizador)
    {
        System.out.println("Seleccione la opción a realizar: ");
        System.out.println("1. Registrar Materias Manualmente");
        System.out.println("2. Registrar Materias desde un archivo");
        System.out.println("3. Guardar registro de materias en un archivo");
        System.out.println("4. Generar reporte notas");
        System.out.println("5. Dar candidatura grado");
        System.out.println("6. Crear planeación");
        System.out.println("7. Salir");
        String opcion = String.valueOf(sn.nextInt());
        System.out.println(opcion);
        if(opcion.equals("1"))
        {
            registrarMateriaEstudiante(sn, estudiante, pensum, analizador);  
        }
        else if(opcion.equals("2"))
        {
            System.out.println("Escribe exit para salir");
            System.out.println("Ingresa la ruta donde se encuentra el archivo: ");
            if(sn.next().toLowerCase().contains("exit"))
            {
                seleccionEstudiante(sn, pensum, estudiante, analizador);
            }
            else
            {
                File avance = new File(sn.next());
                analizador.cargarAvanceEstudiante(avance, estudiante, sn);
                seleccionEstudiante(sn, pensum, estudiante, analizador);
            }   
        }
            
        else if(opcion.equals("3"))
        {
            File archivoMaterias = new File("./data/materias"+estudiante.darCodigo()+".csv");
            try {
               estudiante.guardarAvance(analizador, archivoMaterias);
               seleccionEstudiante(sn, pensum, estudiante, analizador);
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else if(opcion.equals("4"))
        {
            System.out.println(reporteNotas.darReporteNotas(estudiante));
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }
        else if(opcion.equals("5"))
        {
            candidaturaGrado.darCandidaturaGrado(estudiante,pensum);
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }
            
        else if(opcion.equals("6"))
        {
            registrarMateriaPlaneadorEstudiante(sn,estudiante,pensum, analizador,"");
        }
        else if(opcion.equals("7"))
        {
            sn.close();
            System.exit(0);
        }
        else
        {
            System.out.println("Debes ingresar una opción válida.");
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }        
    }
    public static void seleccionCoordinadorAcademico(Scanner sn, Pensum pensum, CoordinadorAcademico coordinador, analizadorArchivo analizador, File avance)
    {
        System.out.println("\nEscriba el código del estudiante que desea revisar: ");
        System.out.println("Escriba exit para volver al menú anterior.");
        String codigoEstudianteRevisar = sn.next();
        if(codigoEstudianteRevisar.toLowerCase().contains("exit"))
        {
            Consola(pensum, analizador);
        }
        Estudiante estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
        if(avance == null)
        {
            System.out.println("Ingresa la ruta donde se encuentra el archivo con la información del estudiante: ");
            avance = new File(sn.next());
            analizador.cargarAvanceCoordinador(avance, coordinador, sn);
            estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
        }
        if(estudiante == null)
        {
            System.out.println("Debes cargar la información del estudiante primero.");
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, null);
        }
        System.out.println("\nSeleccione la opción a realizar: ");
        System.out.println("1. Cargar información del estudiante nuevamente.");
        System.out.println("2. Revisar avance de estudiante");
        System.out.println("3. Generar reporte notas");
        System.out.println("4. Dar candidatura grado");
        System.out.println("5. Crear planeación");
        System.out.println("6. Cambiar Estudiante.");
        System.out.println("7. Salir");
        int opcion = sn.nextInt();
        switch (opcion)
        {
            case 1:
            System.out.println("Ingresa la ruta donde se encuentra el archivo: ");
            File avanceopc = new File(sn.next());
            analizador.cargarAvanceCoordinador(avanceopc, coordinador, sn);
            estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
            break;
            case 2:
            CoordinadorAcademico.revisarAvance(estudiante);
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 3: 
            System.out.println(reporteNotas.darReporteNotas(estudiante));
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 4:
            candidaturaGrado.darCandidaturaGrado(estudiante,pensum);
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 5:
            registrarMateriaPlaneadorCoordinador(sn, estudiante, coordinador, pensum, analizador, avance,"");
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 6:
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 7:
            sn.close();
            System.exit(0); 
            break;
            }
    }

    public static void registrarMateriaEstudiante(Scanner sn, Estudiante estudiante, Pensum pensum, analizadorArchivo analizador)
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
            
            estudiante.registrarMaterias(codigoMateria, semestre, nota, pensum, sn);
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaEstudiante(sn, estudiante, pensum, analizador);
                case 2:
                seleccionEstudiante(sn, pensum, estudiante, analizador); 
        }
    }
    public static void registrarMateriaPlaneadorEstudiante(Scanner sn, Estudiante estudiante, Pensum pensum, analizadorArchivo analizador,String planactual)
    {
        int semestre = 0;
        Double nota = 0.0;
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que se verá la materia: ");
        try
        {
            semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }   
        planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota);
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaPlaneadorEstudiante(sn, estudiante, pensum, analizador,planactual);
                case 2:
                System.out.println("El plan actual es: \n"+"Materia     Semestre\n"+planactual);
                System.out.println("Ingresa la ruta para guardar la planeación: ");
                File planeacion = new File(sn.next());
                try {
                    planeador.guardarPlaneación(planactual, analizador, estudiante, planeacion);
                    System.out.println("La planeación fue guardada en: "+planeacion.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                seleccionEstudiante(sn, pensum, estudiante, analizador); 
        }
    }

    public static void registrarMateriaPlaneadorCoordinador(Scanner sn, Estudiante estudiante, CoordinadorAcademico coordinador, Pensum pensum, analizadorArchivo analizador, File archivo,String planactual)
    {
        int semestre = 0;
        Double nota = 0.0;
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que se verá la materia: ");
        try
        {
            semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }   
        planactual += planeador.crearPlaneacion(estudiante, pensum, sn, codigoMateria, semestre, nota);
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaPlaneadorCoordinador(sn, estudiante, coordinador, pensum, analizador, archivo,planactual);
                case 2:
                System.out.println("El plan actual es: \n"+"Materia     Semestre\n"+planactual);
                System.out.println("Ingresa la ruta para guardar la planeación: ");
                File planeacion = new File(sn.next());
                try {
                    planeador.guardarPlaneación(planactual, analizador, estudiante, planeacion);
                    System.out.println("La planeación fue guardada en: "+planeacion.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, archivo); 
        }
    }
}


