package tdas;

/**
 * debe hacer una implementacion dinamica
 * */
public interface ConjuntoTDA {
	
	public void inicializar();
	
	/**
	 * inicializado
	 * */
	public void agregar(int valor);
	
	/**
	 * inicializado
	 * */
	public void sacar(int valor);
	
	/**
	 * inicializado y no vacio
	 * */
	public int elegir();
	
	/**
	 * inicializado
	 * */
	public boolean pertenece(int valor);
	
	/**
	 * inicializado
	 * */
	public boolean estaVacio();

}
