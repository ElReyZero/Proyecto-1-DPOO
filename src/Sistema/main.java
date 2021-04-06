package Sistema;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;

public class main 
{

	public static void main(String[] args) 
	{
		File archivo = new File("./data/curriculoISIS.csv");
		analizadorArchivo analizador = new analizadorArchivo();
		analizador.cargarDatos(archivo);
		Consola();
	}

	public static void Consola()
	{

		Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; 

        while (!salir) {
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
                    System.out.println("\n\n\nBienvenido estudiante \n-----------------------");
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
                        break;
                    case 2:
                    String nombreCoordinador = "";
                    String codigoCoordinador = "";
                    String departamento = "";
                    System.out.println("\n\n\nBienvenido estudiante \n-----------------------");
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
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            
        }
    }
	
	}


