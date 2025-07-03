package implementacion;

import java.util.Random;
import tdas.ConjuntoStringTDA;

public class ConjuntoString implements ConjuntoStringTDA {

	class nodo {
		String valor;
		nodo siguiente;
	}

	private nodo primero;
	private int cantidad;
	private Random r;

	@Override
	public void inicializar() {
		primero = null;
		cantidad = 0;
		r = new Random();
	}

	@Override
	public void agregar(String valor) {
		if (!pertenece(valor)) {
			nodo nuevo = new nodo();
			nuevo.valor = valor;
			nuevo.siguiente = null;

			if (primero == null) {
				primero = nuevo;
			} else {
				nodo actual = primero;
				while (actual.siguiente != null) {
					actual = actual.siguiente;
				}
				actual.siguiente = nuevo;
			}

			cantidad++;
		}
	}

	@Override
	public void sacar(String valor) {
		if (primero == null) return;

		if (primero.valor.equals(valor)) {
			primero = primero.siguiente;
			cantidad--;
			return;
		}

		nodo anterior = primero;
		nodo actual = primero.siguiente;

		while (actual != null) {
			if (actual.valor.equals(valor)) {
				anterior.siguiente = actual.siguiente;
				cantidad--;
				return;
			}
			anterior = actual;
			actual = actual.siguiente;
		}
	}

	@Override
	public String elegir() {
		int pos = r.nextInt(cantidad);
		nodo actual = primero;
		for (int i = 0; i < pos; i++) {
			actual = actual.siguiente;
		}
		return actual.valor;
	}

	@Override
	public boolean pertenece(String valor) {
		nodo actual = primero;
		while (actual != null) {
			if (actual.valor.equals(valor)) {
				return true;
			}
			actual = actual.siguiente;
		}
		return false;
	}

	@Override
	public boolean estaVacio() {
		return cantidad == 0;
	}
}
