package funcionalidades;

import java.util.ArrayList;

import IdentificadorUsuario.Estudiante;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class candidaturaGrado {

    private boolean posible;

    public boolean darCandidaturaGrado(Estudiante estudiante, Pensum pensum)
    {
        int creditosPensum = pensum.darCreditosPensum();
        ArrayList<MateriaEstudiante> listacursos = estudiante.darCursosTomados();
        int creditosVistos = 0;
        int cantidadCBPC = 0;
        int cantidadCBCO = 0;
        int cantidadCBCA = 0;
        for (MateriaEstudiante materia : listacursos) 
        {
            creditosVistos += materia.darCreditos();
        }

        if(creditosVistos<creditosPensum)
        {
            posible = false;
        }
        else if(cantidadCBCA <1 || cantidadCBCO<1 || cantidadCBPC<1)
        {
            posible = false;
        }
        return posible;
    }
}
