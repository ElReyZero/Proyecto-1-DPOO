package curriculo;
import java.util.ArrayList;

public class Pensum {
    //Atributos

    private Integer creditos;
    private String nombre;
    private ArrayList<Materia> materias;
    private String materiasString;
    private String nivel1;
    private String nivel2;

    //constructor
    public Pensum(Integer pCreditos, String pNombre, ArrayList<Materia> pMaterias, String pMateriasString, String pNivel1, String pNivel2)
    {
        creditos = pCreditos;
        nombre = pNombre;
        materias = pMaterias;
        materiasString = pMateriasString;
        nivel1 = pNivel1;
        nivel2 = pNivel2;
    }

    //Métodos

    public Integer darCreditosPensum()
    {
        return creditos;
    }

    public String darNombrePensum()
    {
        return nombre;
    }

    public ArrayList<Materia> darMateriasPensum()
    {
        return materias;
    }
    
    public String darMateriasString()
    {
        return materiasString;
    }

    public String darMateriasNivel1String()
    {
        return nivel1;
    }

    public String darMateriasNivel2String()
    {
        return nivel2;
    }
}
