package implementacion;

import tdas.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA {
	
	class nodo {
		int prioridad;
		int valor;
		nodo siguiente;
	}
	
	private nodo primero;
	
	@Override
	public void inicializarCola() {
		primero = null;
	}


	@Override
	public void acolarPrioridad(int valor, int prioridad) {
		nodo nuevo = new nodo();
		nuevo.valor = valor;
		nuevo.prioridad = prioridad;

		if (primero == null || prioridad > primero.prioridad) {
			nuevo.siguiente = primero;
			primero = nuevo;
			return;
		}

		nodo actual = primero;
		while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
			actual = actual.siguiente;
		}

		nuevo.siguiente = actual.siguiente;
		actual.siguiente = nuevo;
	}


	@Override
	public void desacolar() {
		if (primero != null) {
			primero = primero.siguiente;
		}
	}



	@Override
	public int primero() {
		return primero.valor;
	}


	@Override
	public int prioridad() {
		return primero.prioridad;
	}


	@Override
	public boolean colaVacia() {
		return primero == null;
	}
}