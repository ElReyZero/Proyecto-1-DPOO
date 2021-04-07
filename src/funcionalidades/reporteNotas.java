package funcionalidades;

import java.util.ArrayList;
import java.util.HashMap;

import org.graalvm.compiler.lir.amd64.AMD64ControlFlow.FloatCondSetOp;

import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;

public class reporteNotas 
{
    private Double promedio;

    public static void darReporteNotas(Estudiante estudiante)
    {
        String Reporte;
        int semestreActual=0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() >semestreActual)
            {
                semestreActual = curso.darSemestre();
            }
        }
        String reportePorSemestre="";
        for (int i = 1;i<=semestreActual;i++)
        {
            String materiasSemestre="";
            for (String materia:materiasVistasSemestre(estudiante, i))
            {
                materiasSemestre = "\n"+materia;
            }
            if(i == semestreActual)
            {
                reportePorSemestre+="\nSemestre actual ( "+String.valueOf(i)+"):\n\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\n"+ materiasSemestre;
            }
            else
            {
            reportePorSemestre = "\nSemestre "+String.valueOf(i)+":\n\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\n"+materiasSemestre;
            }
        }
        Reporte = "El PGA es:"+promedioPGA(estudiante)+"El estado académico es:"+estadoAcademico(estudiante)+reportePorSemestre;
        System.out.println(Reporte);
    }
    public static String promedioSemestre(Estudiante estudiante, int semestre)
    {
        double promedio = 0;
        int contador = 0;
        double nota = 0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() == semestre)
            {
                contador +=1;
                nota += curso.darNota();
            }
        }
        promedio = nota/contador;

        return String.valueOf(promedio);

    }
    public static String promedioPGA(Estudiante estudiante)
    {
        double pga;
        int total =0;
        double nota = 0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            total+=curso.darCreditos();
            nota +=curso.darNota()*curso.darCreditos();
        }
        pga =nota/total;
        return String.valueOf(pga);
    }
    public static ArrayList<String> materiasVistasSemestre(Estudiante estudiante, int semestre)
    {
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() == semestre)
            {
                lista.add(curso.darCodigo());
            }
        }
        return lista;
    }
    public static String estadoAcademico(Estudiante estudiante)
    {
        if(Double.parseDouble(promedioPGA(estudiante))>3.25)
        {
            return "Normal";
        }
        else
        {
            return "En prueba";
        }
    }
}

