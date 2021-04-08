package funcionalidades;

import java.util.ArrayList;

import IdentificadorUsuario.Estudiante;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class candidaturaGrado {

    private static boolean posible;

    public static boolean darCandidaturaGrado(Estudiante estudiante, Pensum pensum)
    {
        int creditosPensum = pensum.darCreditosPensum();
        ArrayList<MateriaEstudiante> listacursos = estudiante.darCursosTomados();
        int creditosVistos = 0;
        int cantidadCBPC = 0;
        int cantidadCBCO = 0;
        int cantidadCBCA = 0;
        int cantidadCBU = 0;
        int cantidadElectIng = 0;
        for (MateriaEstudiante materia : listacursos) 
        {
            creditosVistos += materia.darCreditos();
            if(materia.darTipoMateria().equals("Electiva CBU"))
            {
                cantidadCBU +=1;
                if(materia.darTipoMateria().equals("Electiva CBU CO"))
                {
                    cantidadCBCO += 1;
                }
                else if(materia.darTipoMateria().equals("Electiva CBU CA"))
                {
                    cantidadCBCA += 1;
                }
                else if(materia.darTipoMateria().equals("Electiva CBU PC"))
                {
                    cantidadCBPC +=1;
                }
            }
            else if(materia.darTipoMateria().equals("Electiva Ingeniería"))
            {
                cantidadElectIng +=1;
            }
        }

        if(creditosVistos<creditosPensum)
        {
            posible = false;
        }
        else if(cantidadCBU < 6)
        {
            posible = false;
        }
        else if(cantidadCBCA <1 || cantidadCBCO<1 || cantidadCBPC<1)
        {
            posible = false;
        }
        else if(cantidadElectIng<1)
        {
            posible = false;
        }
        return posible;
    }
}
