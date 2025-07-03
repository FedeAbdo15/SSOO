package implementacion;

import tdas.ConjuntoTDA;

public class DiccionarioSimple implements tdas.DiccionarioSimpleTDA {

	class nodo{
		int clave;
		int valor;
		nodo siguiente;
	}

	private nodo primero;

	@Override
	public void inicializar() {
		primero = null;
	}

	@Override
	public void agregar(int clave, int valor) {
		nodo nuevo = new nodo();
		nuevo.clave = clave;
		nuevo.valor = valor;

		if (primero == null) primero = nuevo;

		else {
			nodo actual = primero;
			while (actual.siguiente != null) {
				actual = actual.siguiente;
			}
			actual.siguiente = nuevo;
		}
	}

	@Override
	public void eliminar(int clave) {
		if (primero == null) return;
		if (primero.clave == clave){
			primero = primero.siguiente;
			return;
		}

		nodo anterior = primero;
		nodo actual = primero.siguiente;

		while (actual != null){
			if (actual.clave == clave){
				anterior.siguiente = actual.siguiente;
				return;
			}
			anterior = actual;
			actual = actual.siguiente;
		}
	}

	@Override
	public int recuperar(int clave) {
		nodo actual = primero;
		while (actual != null){
			if (actual.clave == clave){
				return actual.valor;
			}
			actual = actual.siguiente;
		}
		return 0;
	}

	@Override
	public ConjuntoTDA obtenerClaves() {
		ConjuntoTDA resultado = new Conjunto();
		resultado.inicializar();
		if (primero == null) return resultado;

		nodo actual = primero;


		while (actual != null){
			resultado.agregar(actual.clave);
			actual = actual.siguiente;
		}
		return resultado;
	}
}
