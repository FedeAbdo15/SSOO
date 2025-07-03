package tdas;

/**
 * debe hacer una implememtacion dinamica 
 * */
public interface DiccionarioSimpleTDA {

	public void inicializar();
	
	/**
	 * inicializada
	 * */
	public void agregar(int clave, int valor);
	
	/**
	 * inicializada
	 * */	
	public void eliminar(int clave);
	
	/**
	 * no vacia y existe clave
	 * */
	public int recuperar(int clave);
	
	/**
	 * inicializada
	 * */	
	public ConjuntoTDA obtenerClaves();
	
}
