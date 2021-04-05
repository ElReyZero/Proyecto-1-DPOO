package curriculo;

import java.util.ArrayList;

public class Materia
 {
    private String nombre;
    private ArrayList<Materia> preRequisitos = new ArrayList<Materia>();
    private ArrayList<Materia> requisitos = new ArrayList<Materia>();
    private int creditos;
    private String tipoMateria;
    private boolean semanas16;
    private String codigo;

    public Materia(String pnombre,String pcodigo ArrayList<Materia> ppreRequisitos, ArrayList<Materia> prequisitos, int pcreditos, String ptipoMateria, boolean psemanas16 )

    {
        nombre = pnombre;
        codigo = pcodigo;
        preRequisitos = ppreRequisitos;
        requisitos = prequisitos;
        creditos = pcreditos;
        tipoMateria = ptipoMateria;
        semanas16 = psemanas16;
    }

public String darNombre()
{
    return nombre;
}
public String darCodigo()
{
    return codigo;
}
public ArrayList<Materia> darPreRequisitos()
{
    return preRequisitos;
}
public ArrayList<Materia> darRequisitos()
{
   return requisitos; 
}
public int darCreditos()
{
    return creditos;
}
public String darTipoMateria()
{
    return tipoMateria;
}
public boolean darSemanas16()
{
    return semanas16;
}


}
