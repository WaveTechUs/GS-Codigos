package listas;

import entidades.Sensor;
import filas.FilaSensor;

public class ListaSensorCrescente {
	
	
	private class NO {
		Sensor sensor;
		NO prox;
	}

	private NO lista = null;
	
	// Ordem crescente de acordo com ph

	public void insere(Sensor elem) {
		NO novo = new NO();
		novo.sensor = elem;
		if (lista == null) {
			novo.prox = null;
			lista = novo;
		} else {
			if (novo.sensor.getPh() < lista.sensor.getPh()) {
				novo.prox = lista;
				lista = novo;
			} else {
				NO aux = lista;
				boolean achou = false;
				while (aux.prox != null && !achou) {
					if (aux.prox.sensor.getPh() < novo.sensor.getPh())
						aux = aux.prox;
					else
						achou = true;
				}
				novo.prox = aux.prox;
				aux.prox = novo;
			}
		}
		System.out.println("Adicionou o sensor: " + novo.sensor.toString());
	}

	// removoção por algum atributo 
	public boolean remove(int valor) {
		boolean achou = false;
		if (lista != null) {
			if (valor == lista.sensor.getIndex()) {
				achou = true;
				lista = lista.prox;
			} else {
				NO aux = lista;
				while (aux.prox != null && !achou) {
					if (aux.prox.sensor.getIndex() != valor)
						aux = aux.prox;
					else {
						achou = true;
						aux.prox = aux.prox.prox;
					}
				}
			}
		}
		return achou;
	}
	
	public boolean encontrar(String coordenada) {
		boolean achou = false;
		if (lista != null) {
			if (coordenada == lista.sensor.getCoordenadas()) {
				achou = true;
				System.out.println("Sensor :" + lista.sensor.toString());
			} else {
				NO aux = lista;
				while (aux.prox != null && !achou) {
					if (aux.prox.sensor.getCoordenadas() != coordenada)
						aux = aux.prox;
					else {
						achou = true;
						System.out.println("Sensor :" + lista.sensor.toString());
					}
				}
			}
		}
		return achou;
	}
	
	public int contaNos() {
		int cont=0;
		NO aux = lista;
		while (aux!=null) {
			cont++;
			aux=aux.prox;
		}
		return cont;
	}
	
	public void apresenta() {
		NO aux = lista;
		System.out.println("\n *** LISTA ***");
		while (aux != null) {
			System.out.println("\t " + aux.sensor.toString());
			aux = aux.prox;
		}
	}
	
	public Sensor getSensor(int valor) {
		Sensor sensor = null;
		boolean achou = false;
		if (lista != null) {
			if (valor == lista.sensor.getIndex()) {
				achou = true;
				sensor = lista.sensor;
			} else {
				NO aux = lista;
				while (aux.prox != null && !achou) {
					if (aux.prox.sensor.getIndex() != valor)
						aux = aux.prox;
					else {
						achou = true;
						sensor = aux.prox.sensor;
					}
				}
			}
		}
		return sensor;
	}
	
	public boolean isEmpty() {
		return lista == null;
	}
	
}
