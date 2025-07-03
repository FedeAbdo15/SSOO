package implementacion;

import tdas.ConjuntoStringTDA;
import tdas.DiccionarioSimpleStringTDA;
import tdas.DiccionarioSimpleTDA;

public class DiccionarioSimpleString implements DiccionarioSimpleStringTDA {

	class nodo {
		String periodo;
		DiccionarioSimpleTDA precipitacionesMes;
		nodo siguiente;
	}

	private nodo primero;

	@Override
	public void inicializarDiccionario() {
		primero = null;
	}

	@Override
	public void agregar(String periodo, int dia, int cantidad) {
		nodo actual = primero;

		while (actual != null) {
			if (actual.periodo.equals(periodo)) {
				actual.precipitacionesMes.agregar(dia, cantidad);
				return;
			}
			actual = actual.siguiente;
		}

		nodo nuevo = new nodo();
		nuevo.periodo = periodo;
		nuevo.precipitacionesMes = new DiccionarioSimple();
		nuevo.precipitacionesMes.inicializar();
		nuevo.precipitacionesMes.agregar(dia, cantidad);
		nuevo.siguiente = primero;
		primero = nuevo;
	}

	@Override
	public void eliminar(String periodo) {
		if (primero == null) return;

		if (primero.periodo.equals(periodo)) {
			primero = primero.siguiente;
			return;
		}

		nodo anterior = primero;
		nodo actual = primero.siguiente;

		while (actual != null) {
			if (actual.periodo.equals(periodo)) {
				anterior.siguiente = actual.siguiente;
				return;
			}
			anterior = actual;
			actual = actual.siguiente;
		}
	}

	@Override
	public DiccionarioSimpleTDA recuperar(String periodo) {
		nodo actual = primero;
		while (actual != null) {
			if (actual.periodo.equals(periodo)) {
				return actual.precipitacionesMes;
			}
			actual = actual.siguiente;
		}
		return null;
	}

	@Override
	public ConjuntoStringTDA claves() {
		ConjuntoStringTDA resultado = new ConjuntoString();
		resultado.inicializar();

		nodo actual = primero;
		while (actual != null) {
			resultado.agregar(actual.periodo);
			actual = actual.siguiente;
		}
		return resultado;
	}
}
