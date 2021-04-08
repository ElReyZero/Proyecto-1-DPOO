package curriculo;
import java.util.ArrayList;

public class Pensum {
    //Atributos

    private Integer creditos;
    private String nombre;
    private ArrayList<Materia> materias;
    private String materiasString;

    //constructor
    public Pensum(Integer pCreditos, String pNombre, ArrayList<Materia> pMaterias, String pMateriasString)
    {
        creditos = pCreditos;
        nombre = pNombre;
        materias = pMaterias;
        materiasString = pMateriasString;
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
}
