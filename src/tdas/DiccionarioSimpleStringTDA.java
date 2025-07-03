package tdas;

/**
 * debe hacer una implementacion dinamica de un diccionario cuya clave es un String y el 
 * valor asociado es un DiccionarioSimpleTDA.
 */
public interface DiccionarioSimpleStringTDA {
	
	public void inicializarDiccionario();
	
	/**
	 * inicializada
	 * */	
	public void agregar(String clave, int dia, int cantidad); 
	
	/**
	 * inicializada
	 * */	
	public void eliminar(String clave); 
	
	/**
	 * inicializada y existe la clave
	 * */	
	public DiccionarioSimpleTDA recuperar(String clave); 
	
	/**
	 * inicializada
	 * */	
	public ConjuntoStringTDA claves(); //diccionario inicializado
	
}
