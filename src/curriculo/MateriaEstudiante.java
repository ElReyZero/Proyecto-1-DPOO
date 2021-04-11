package curriculo;

public class MateriaEstudiante extends Materia 
{
	//Atributos
	private Double nota;
	private int semestre;

	public MateriaEstudiante(Materia materia, Double pNota, int pSemestre) 
	{
		super(materia.darNombre(), materia.darCodigo(), materia.darPreRequisitos(), materia.darRequisitos(), materia.darCreditos(), materia.darTipoMateria(), materia.darNivel(), materia.darSemanas16());
		nota =  pNota;
		semestre = pSemestre;
	}
	
	public Double darNota()
	{
		return nota;
	}
	public int darSemestre()
	{
		return semestre;
	}
	public void setCredits(int credits)
	{
		super.creditos = credits;
	}
}
