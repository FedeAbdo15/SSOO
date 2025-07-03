package tdas;

/**
 * Debe realizar una implementacion dinamica
 * 
 * */
public interface ColaStringTDA {
	
	public void inicializarCola( );
	
	/**
	 * inicializado
	 * */
	public void acolar(String valor);
	
	/**
	 * inicializada y no vacia
	 * */
	public void desacolar( );
	
	/**
	 * inicializad y no vacia
	 * */
	public String primero( );
	
	/**
	 * inicializada
	 * */
	public boolean colaVacia( ); 
}
