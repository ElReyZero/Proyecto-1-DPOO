package curriculo;
import java.util.ArrayList;

public class Pensum {
    //Atributos

    private Integer creditos;
    private String nombre;
    private ArrayList<Materia> materias;

    //constructor
    public Pensum(Integer pCreditos, String pNombre)
    {
        creditos = pCreditos;
        nombre = pNombre;
        materias = new ArrayList<Materia>();
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


}
