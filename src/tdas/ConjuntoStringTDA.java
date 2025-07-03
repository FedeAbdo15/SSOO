package tdas;

/**
 * debe hacer una implementacion dinamica
 * */
public interface ConjuntoStringTDA {
	
	public void inicializar();
	
	/**
	 * inicializado
	 * */
	public void agregar(String valor);
	
	/**
	 * inicializado
	 * */
	public void sacar(String valor); 
	
	/**
	 * inicializado y no vacio
	 * */
	public String elegir(); 
	
	/**
	 * inicializado
	 * */
	public boolean pertenece(String valor); 
	
	/**
	 * inicializado
	 * */
	public boolean estaVacio(); 
}
