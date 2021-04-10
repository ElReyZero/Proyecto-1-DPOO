package funcionalidades;
import java.util.Scanner;

import IdentificadorUsuario.Estudiante;
import Sistema.analizadorArchivo;
import curriculo.Pensum;

public class planeador {

    //Métodos

    public static String crearPlaneacion(Estudiante estudiante,Pensum pensum,analizadorArchivo analizador,Scanner sn,String codigoMateria,int semestre,double nota)
    {
        Estudiante copia = estudiante;
        String plan = "";
        int registro=copia.registrarMaterias(codigoMateria, semestre, nota, pensum, sn);  
        if(registro==0)
        {
            plan += codigoMateria+"      "+String.valueOf(semestre)+"\n";
        }
        return plan;
    }


}
