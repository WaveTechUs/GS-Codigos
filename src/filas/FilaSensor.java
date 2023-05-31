package filas;

import entidades.Sensor;


public class FilaSensor {
	
	private class NO {
		Sensor sensor;
		NO prox;
	}
	NO inicio, fim;

	public void init() {
		inicio = fim = null;
	}
	public boolean isEmpty() {
		if (inicio == null && fim == null)
			return true;
		else
			return false;
	}
	public void enqueue(Sensor elem) {
		NO novo = new NO();
		novo.sensor = elem;
		if (isEmpty())
			inicio = novo;
		else
			fim.prox = novo;
		fim = novo;
	}
	public String dequeue() {
		Sensor valor = inicio.sensor;
		inicio = inicio.prox;
		if (inicio == null)
			fim = null;
		return valor.toString();
	}
	public String first() {
		return (inicio.sensor.toString());
	}
	
	public void apresenta() {
		NO aux = inicio;
		System.out.println("\n *** FILA ***");
		while (aux != null) {
			System.out.println("\t " + aux.sensor.toString());
			aux = aux.prox;
		}
	}
	public boolean achaSensor(int valor) {
		boolean achou = false;
		if (inicio != null) {
			if (valor == inicio.sensor.getIndex()) {
				achou = true;
			} else {
				NO aux = inicio;
				while (aux.prox != null && !achou) {
					if (aux.prox.sensor.getIndex() != valor)
						aux = aux.prox;
					else {
						achou = true;
					}
				}
			}
		}
		return achou;
	}
}
