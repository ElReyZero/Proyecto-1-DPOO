package funcionalidades;

import java.util.ArrayList;
import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class candidaturaGrado {  

    public static void darCandidaturaGrado(Estudiante estudiante, Pensum pensum)
    {
        int creditosPensum = pensum.darCreditosPensum();
        ArrayList<MateriaEstudiante> listacursos = estudiante.darCursosTomados();
        ArrayList<String> tomadosString = estudiante.darCursosTomadosString();
        String faltantes = "";
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
        for (Materia matGeneral : pensum.darMateriasPensum()) 
        {
            String code = matGeneral.darCodigo();
            if(!tomadosString.contains(code))
            {
                faltantes += code+"\n";
            }
        }

        if(creditosVistos<creditosPensum)
        {
            System.out.println("No has visto suficientes créditos para poder ser candidato a grado.");
            System.out.println("Créditos vistos: " + creditosVistos);
            System.out.print("Créditos faltantes: "+(creditosPensum-creditosVistos));
        }
        else if(cantidadCBU < 6)
        {
            System.out.println("No has visto suficientes CBUs para ser candidato a grado. Tienes que ver mínimo 6.");
            System.out.println("Cantidad de CBU vistas:" + cantidadCBU);
        }
        else if(cantidadCBCA <1 || cantidadCBCO<1 || cantidadCBPC<1)
        {
            System.out.println("No has visto suficientes CBUs de cada tipo.\nMínimo 1 CBCA, 1 CBPC, 1 CBCO");
            System.out.println("Cantidad de CBCA vistas: "+cantidadCBCA);
            System.out.println("Cantidad de CBPC vistas: "+cantidadCBPC);
            System.out.println("Cantidad de CBCO vistas: "+cantidadCBCO);
        }
        else if(cantidadElectIng<1)
        {
            System.out.println("No has visto suficientes electivas en ingeniería. Mínimo 1.");
        }
    }
}
