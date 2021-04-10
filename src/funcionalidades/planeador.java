package funcionalidades;
import java.util.ArrayList;
import java.util.Scanner;

import IdentificadorUsuario.Estudiante;
import Sistema.analizadorArchivo;
import curriculo.Materia;
import curriculo.Pensum;

public class planeador {
    //Atributos
    private ArrayList<Materia> materias;
    private Integer creditos;
    private boolean posible;

    //Constructor

    public planeador(ArrayList<Materia> pMaterias, Integer pCreditos)
    {
        materias = pMaterias;
        creditos = pCreditos;
        posible = false;
    }

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

    public Integer darCreditosPlaneacion()
    {
        return creditos;
    }

    public boolean darPosibilidad()
    {
        return posible;
    }

    public ArrayList<Materia> darMaterias()
    {
        return materias;
    }

}
