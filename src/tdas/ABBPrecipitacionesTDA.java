package tdas;

public interface ABBPrecipitacionesTDA {
	
	public void inicializar();
	/**
	 * inicializado
	 * 
	 * Agrega un nuevo campo y crea el primer diccionario de mediciones con el mes y el anio corriente y sin precipitaciones.
	 * */
	public void agregar(String valor); //árbol inicializado

	/**
	 * inicializado
	 * 
	 * Agrega una nueva precipitacion para un campo existente para una fecha determinada.
	 * - si el campo no existe lo crea y agrega la nueva precipitacion
	 * - si la medicion ya existe para ese dia la reemplaza
	 * */
	public void agregarMedicion(String valor, String anio, String mes, int dia, int precipitacion);
	
	/**
	 * inicializado
	 * 
	 * Elimina el campo y todas sus mediciones
	 * */
	public void eliminar(String valor); 
	
	/**
	 * inicializado
	 * 
	 * Elimina una precipitacion para un campo existente para una fecha determinada.
	 * - si el campo no existe no hace nada
	 * - si la medicion no existe para ese periodo y dia no hace nada
	 * */
	public void eliminarMedicion(String valor, String anio, String mes, int dia);
	
	/**
	 * inicializado y no vacio
	 * */
	public String raiz();
	
	/**
	 * inicializado y no vacio
	 * */
	public ColaStringTDA periodos();

	/**
	 * inicializado
	 * */
	public ColaPrioridadTDA precipitaciones(String periodo);
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBPrecipitacionesTDA hijoIzq();
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBPrecipitacionesTDA hijoDer();
	
	/**
	 * inicializado
	 * */
	public boolean arbolVacio();
}
