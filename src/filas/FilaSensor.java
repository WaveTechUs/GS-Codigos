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
}
