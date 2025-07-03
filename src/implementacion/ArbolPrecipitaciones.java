package implementacion;

import tdas.ABBPrecipitacionesTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaStringTDA;
import tdas.ConjuntoStringTDA;
import tdas.ConjuntoTDA;
import tdas.DiccionarioSimpleStringTDA;
import tdas.DiccionarioSimpleTDA;

public class ArbolPrecipitaciones implements ABBPrecipitacionesTDA {

	class nodoArbol {
		String campo;
		DiccionarioSimpleStringTDA mensualPrecipitaciones;
		ABBPrecipitacionesTDA hijoIzquierdo;
		ABBPrecipitacionesTDA hijoDerecho;
	}
	
	private nodoArbol raiz;
	
	@Override
	public void inicializar() {
		raiz = null;
	}

	@Override
	public void agregar(String valor) {
		// Creo un nuevo campo
		nodoArbol nuevo = new nodoArbol();
		nuevo.campo = valor;
		nuevo.mensualPrecipitaciones = new DiccionarioSimpleString();

		// Si no hay raiz lo agrego como raiz
		if (raiz == null) {
			raiz = nuevo;
		}

		// Si hay raiz, me muevo a la izquierda si es menor o a la derecha si es mayor.
		// Repito esto en los subarboles hijos hasta encontrar la ubicacion correcta
		else {
			if (esMenor(valor, raiz.campo)) {
				if (raiz.hijoIzquierdo == null) {
					raiz.hijoIzquierdo = new ArbolPrecipitaciones();
					raiz.hijoIzquierdo.inicializar();
				}
				raiz.hijoIzquierdo.agregar(valor);
			} else if (esMayor(valor, raiz.campo)) {
				if (raiz.hijoDerecho == null) {
					raiz.hijoDerecho = new ArbolPrecipitaciones();
					raiz.hijoDerecho.inicializar();
				}
				raiz.hijoDerecho.agregar(valor);
			}
		}
	}

	@Override
	public void agregarMedicion(String valor, String anio, String mes, int dia, int precipitacion) {
		if (raiz == null) {
			String periodo = anio + formatMes(mes);
			nodoArbol n = new nodoArbol();
			n.campo = valor;
			n.mensualPrecipitaciones = new DiccionarioSimpleString();
			n.mensualPrecipitaciones.agregar(periodo, dia, precipitacion);
			raiz = n;
			return;
		}

		// Si el campo existe y el dia es valido, agrego la medicion
		if (valor.equalsIgnoreCase(raiz.campo)) {
			String periodo = anio + formatMes(mes);
			if (!diaValido(Integer.parseInt(anio), Integer.parseInt(mes), dia)) return;

			raiz.mensualPrecipitaciones.agregar(periodo, dia, precipitacion);
		}
		// Si el campo no es donde estoy parado, recorro para izquierda o derecha segun
		// valor hasta encontrarlo. Si en algun momento tengo un espacio libre donde iria
		// Una comparacion, agrego el campo ahi (es su lugar correcto) y luego la medicion.
		else if (esMenor(valor, raiz.campo)) {
			if (raiz.hijoIzquierdo == null) {
				raiz.hijoIzquierdo = new ArbolPrecipitaciones();
				raiz.hijoIzquierdo.inicializar();
				raiz.hijoIzquierdo.agregar(valor);
			}
			raiz.hijoIzquierdo.agregarMedicion(valor, anio, mes, dia, precipitacion);
		}

		else if (esMayor(valor, raiz.campo)) {
			if (raiz.hijoDerecho == null) {
				raiz.hijoDerecho = new ArbolPrecipitaciones();
				raiz.hijoDerecho.inicializar();
				raiz.hijoDerecho.agregar(valor);
			}
			raiz.hijoDerecho.agregarMedicion(valor, anio, mes, dia, precipitacion);
		}
	}



	@Override
	public void eliminar(String valor) {
		// Reemplazo la raíz con el resultado de eliminarRecursivo
		ABBPrecipitacionesTDA nuevoArbol = eliminarRecursivo(this, valor);
		this.raiz = ((ArbolPrecipitaciones) nuevoArbol).raiz;
	}

	private ABBPrecipitacionesTDA eliminarRecursivo(ABBPrecipitacionesTDA actual, String valor) {
		if (actual == null || actual.arbolVacio()) return actual;

		String campoActual = actual.raiz();

		// Caso: Encontré el nodo a eliminar
		if (valor.equalsIgnoreCase(campoActual)) {
			ABBPrecipitacionesTDA izq = actual.hijoIzq();
			ABBPrecipitacionesTDA der = actual.hijoDer();

			// Caso 1: sin hijos, devuelve un arbol vacio
			if ((izq == null || izq.arbolVacio()) && (der == null || der.arbolVacio())) {
				return new ArbolPrecipitaciones();
			}

			// Caso 2: un solo hijo, devuelvo ese arbol
			if (izq == null || izq.arbolVacio()) return der;
			if (der == null || der.arbolVacio()) return izq;

			// Caso 3: dos hijos, reemplazo con el sucesor (mínimo del derecho)
			nodoArbol sucesor = obtenerMin(((ArbolPrecipitaciones) der).raiz);

			((ArbolPrecipitaciones) actual).raiz.campo = sucesor.campo;
			((ArbolPrecipitaciones) actual).raiz.mensualPrecipitaciones = sucesor.mensualPrecipitaciones;

			ABBPrecipitacionesTDA nuevoDer = eliminarRecursivo(der, sucesor.campo);
			((ArbolPrecipitaciones) actual).raiz.hijoDerecho = nuevoDer;
			return actual;
		}

		// Si valor es menor, voy a la izquierda
		else if (esMenor(valor, campoActual)) {
			ABBPrecipitacionesTDA nuevoIzq = eliminarRecursivo(actual.hijoIzq(), valor);
			((ArbolPrecipitaciones) actual).raiz.hijoIzquierdo = nuevoIzq;
		}

		// Si valor es mayor, voy a la derecha
		else {
			ABBPrecipitacionesTDA nuevoDer = eliminarRecursivo(actual.hijoDer(), valor);
			((ArbolPrecipitaciones) actual).raiz.hijoDerecho = nuevoDer;
		}

		return actual;
	}


	private nodoArbol obtenerMin(nodoArbol nodo) {
		// Devuelvo el nodo de mas a la izquierda (por ser ABB, sera el minimo.)
		ABBPrecipitacionesTDA subarbol = nodo.hijoIzquierdo;
		if (subarbol == null) return nodo;

		while (!subarbol.arbolVacio() && subarbol.hijoIzq() != null) {
			subarbol = subarbol.hijoIzq();
		}
		return ((ArbolPrecipitaciones) subarbol).raiz;
	}


	@Override
	public String raiz() {
		return raiz.campo;
	}

	@Override
	public void eliminarMedicion(String valor, String anio, String mes, int dia) {
		if (raiz == null) return;

		// Si estoy en el campo
		if (valor.equalsIgnoreCase(raiz.campo)) {
			String periodo = anio + formatMes(mes);

			// Recupero datos
			DiccionarioSimpleTDA diccDias = raiz.mensualPrecipitaciones.recuperar(periodo);

			// Si hay datos para ese dia, lo elimino
			if (diccDias != null) {
				diccDias.eliminar(dia);
			}
		}

		// Si no estoy en el campo, me muevo por el arbol hacia izquierda o derecha segun corresponda.
		else if (esMenor(valor, raiz.campo)) {
			if (raiz.hijoIzquierdo != null) {
				raiz.hijoIzquierdo.eliminarMedicion(valor, anio, mes, dia);
			}
		}

		else {
			if (raiz.hijoDerecho != null) {
				raiz.hijoDerecho.eliminarMedicion(valor, anio, mes, dia);
			}
		}
	}


	@Override
	public ColaStringTDA periodos() {

		// Devuelvo las claves del diccionario (seran los periodos).
		ColaStringTDA cola = new ColaString();
		if (raiz == null) return cola;

		ConjuntoStringTDA cs = raiz.mensualPrecipitaciones.claves();
		while (!cs.estaVacio()) {
			String p = cs.elegir();
			cola.acolar(p);
			cs.sacar(p);
		}
		return cola;
	}
	@Override
	public ColaPrioridadTDA precipitaciones(String periodo) {
		// Devuelvo las precipitaciones del nodo donde estoy parado.
		ColaPrioridadTDA cola = new ColaPrioridad();
		cola.inicializarCola();

		if (raiz == null) return cola;

		// Recupero los dias para el periodo
		DiccionarioSimpleTDA dias = raiz.mensualPrecipitaciones.recuperar(periodo);

		// Guardo el dia y la cantidad de lluvia en una cola de prioridad a ser devuelta.
		if (dias != null) {
			ConjuntoTDA claves = dias.obtenerClaves();
			while (!claves.estaVacio()) {
				int dia = claves.elegir();
				int cantidad = dias.recuperar(dia);
				cola.acolarPrioridad(dia, cantidad);
				claves.sacar(dia);
			}
		}
		return cola;
	}


	@Override
	public ABBPrecipitacionesTDA hijoIzq() {
		return raiz != null ? raiz.hijoIzquierdo : null;
	}

	@Override
	public ABBPrecipitacionesTDA hijoDer() {
		return raiz != null ? raiz.hijoDerecho : null;
	}

	@Override
	public boolean arbolVacio() {
		return raiz == null;
	}

	private boolean esMenor(String v1, String v2) {
		return v1.compareToIgnoreCase(v2) < 0;
	}

	private boolean esMayor(String v1, String v2) {
		return v1.compareToIgnoreCase(v2) > 0;
	}
	private boolean diaValido(int anio, int mes, int dia) {
		if (mes < 1 || mes > 12 || anio < 0) return false;

		int[] diasPorMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		return dia >= 1 && dia <= diasPorMes[mes - 1];
	}
	private String formatMes(String mes) {
		int numero = Integer.parseInt(mes);
		return (numero < 10 ? "0" : "") + numero;
	}

}
