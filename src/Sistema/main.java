package Sistema;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main 
{

	public static void main(String[] args) 
	{
		Interfaz();
	}

	public static void Interfaz()
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
                        System.out.println("Has seleccionado la opcion 1");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
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
		sn.close();
    }
	public static void InterfazEstudiante()
	{

	}
	public static void InterfazCoordinadorAcademico()
	{

	}
}

