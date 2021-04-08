package funcionalidades;
import java.util.ArrayList;

import IdentificadorUsuario.Estudiante;
import curriculo.Materia;

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

    public static void crearPlaneacion(Estudiante estudiante)
    {
        //TODO
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
