package IdentificadorUsuario;

public class CoordinadorAcademico extends Usuario {
	
	//Atributos
	private String departamento;
	
	
	//Constructor
	public CoordinadorAcademico(String pNombre, String pCodigo, String pDepto) 
	{
		super(pNombre, pCodigo);
		departamento = pDepto;
	}

	//Métodos
	
	public String darDepto()
	{
		return departamento;
	}

}
