package tdas;

/**
 * debe hacer una implementacion dinamica.
 * debe hacer que las menores prioridades queden primero.
 * */
public interface ColaPrioridadTDA {
	
	public void inicializarCola();
	
	/**
	 * inicializada
	 * */
	public void acolarPrioridad(int valor, int prioridad);
	
	/**
	 * inicializada y no vacia
	 * */
	public void desacolar();
	
	/**
	 * inicializada y no vacia
	 * */
	public int primero();
	
	/**
	 * inicializada y no vacia
	 * */
	public int prioridad();
	
	/**
	 * inicializada 
	 * */
	public boolean colaVacia();
}
