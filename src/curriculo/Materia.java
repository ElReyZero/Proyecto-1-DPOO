package curriculo;
public class Materia
 {
    private String nombre;
    private String preRequisitos;
    private String requisitos;
    private int creditos;
    private String tipoMateria;
    private boolean semanas16;
    private String codigo;
    private int nivel;

    public Materia(String pnombre,String pcodigo, String ppreRequisitos, String prequisitos, int pcreditos, String ptipoMateria, int pNivel, boolean psemanas16 )

    {
        nombre = pnombre;
        codigo = pcodigo;
        preRequisitos = ppreRequisitos;
        requisitos = prequisitos;
        creditos = pcreditos;
        tipoMateria = ptipoMateria;
        semanas16 = psemanas16;
        nivel = pNivel;
    }

public String darNombre()
{
    return nombre;
}
public String darCodigo()
{
    return codigo;
}
public String darPreRequisitos()
{
    return preRequisitos;
}
public String darRequisitos()
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

public int darNivel()
{
    return nivel;
}

}
