package implementacion;

import tdas.ColaStringTDA;

public class ColaString implements ColaStringTDA {

	class nodo {
		String valor;
		nodo siguiente;
	}

	private nodo primero;
	private nodo ultimo;

	@Override
	public void inicializarCola() {
		primero = null;
		ultimo = null;
	}

	@Override
	public void acolar(String valor) {
		nodo nuevo = new nodo();
		nuevo.valor = valor;
		nuevo.siguiente = null;

		if (primero == null) {
			primero = nuevo;
        } else {
			ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
    }

	@Override
	public void desacolar() {
		if (primero != null) {
			primero = primero.siguiente;
			if (primero == null) {
				ultimo = null;
			}
		}
	}

	@Override
	public String primero() {
		return primero.valor;
	}

	@Override
	public boolean colaVacia() {
		return primero == null;
	}
}
