package aplicacao;

import java.util.Random;
import java.util.Scanner;

import entidades.Sensor;
import filas.FilaSensor;
import listas.ListaSensorCrescente;

public class Monitora {
	public static final float PH_MIN = 4;
	public static final float PH_MAX = 8;
	public static final int UMIDADE_MIN = 20;
	public static final int UMIDADE_MAX = 70 - UMIDADE_MIN;
	public static final float TEORMOS_MIN = 0;
	public static final float TEORMOS_MAX = 60;
	public int index = 0;
	ListaSensorCrescente listaSensor = new ListaSensorCrescente();
	FilaSensor filaSensor = new FilaSensor();
	Scanner le = new Scanner(System.in);
	
	public void main() {
		boolean continua = true;
		do {
			int escolha = menu();
			continua = acao(escolha);
			
		}while(continua);
	}
	
	public void adicionarLista() {
		System.out.println("Digite a Coordenada do Sensor:");
		String coordenadas = le.next();
		System.out.println("Digite a Umidade:");
		int umidade = le.nextInt();
		System.out.println("Digite o Ph:");
		float ph = le.nextFloat();
		System.out.println("Digite o teor MOS:");
		float teorMOS = le.nextFloat();
		String texturaSolo = verificaTexturaSolo(teorMOS);
		Sensor novoSensor = new Sensor(index, coordenadas, umidade, ph, teorMOS, texturaSolo);
		index+=1;
		listaSensor.insere(novoSensor);
	}
	
	public void removeLista() {
		boolean achou = false;
		int resposta = 1;
		do {
			System.out.println("Qual Sensor você gostaria de remover? Indique o numero dele( Ex: Sensor 1, digite 1)");
			int i = le.nextInt();
			Sensor aux = listaSensor.getSensor(i);
			if(listaSensor.remove(index)) {
				System.out.println("Você removeu este sensor: \n" + aux.toString());
				achou = true;
			}
			else {
				System.out.println("Sensor não encontrado");
				achou = false;
				System.out.println("Deseja sair? Digite 1");
				resposta = le.nextInt();
			}			
		}while(!achou && resposta !=1);
	}

	public String verificaTexturaSolo(float teorMOS) {
		String result = "";
		
		if(teorMOS < 15.0) {
			result = "Solo arenoso";
		}
		else {
			if(teorMOS<15.0 && teorMOS > 31) {
				result = "Solo medio";
			}
			else {
				if(teorMOS<31.0 && teorMOS > 60) {
					result = "Solo agiloso";
				}
				else {
					result = "Solo com má drenagem ou acidez elevada";
				}
			}
		}
		return result;
	}
	
	public int menu() {
		boolean listaVazia = listaSensor.isEmpty();
		int escolha;
		do {
			System.out.println("=====================================");
			System.out.println("1- Adicionar na Sensor");
			System.out.println("2- Gerar Sensores");
			if(!listaVazia) {
				System.out.println("3- Verificar Sensores");
				System.out.println("4- Aprenseta Sensor");
				System.out.println("5- Apresenta Lista");
				System.out.println("6- Remover");
			}
			System.out.println("7- Sair");
			System.out.println("=====================================");
			System.out.println("Escolha um numero: ");
			escolha = le.nextInt();	
			if(escolha > 1 && escolha < 7 && !listaVazia) {
				break;
			}
		}while(escolha != 1 && escolha != 7 && escolha !=2 );
		return escolha;
	}
	
	public boolean acao(int escolha) {
		boolean continua = true;
		switch (escolha) {
	    case 1:
	    	adicionarLista();
	    	break;
	    case 2:
	    	System.out.println("Aguarde um momento...");
	    	gerarSensores();
	    	listaSensor.apresenta();
	    	break;
	    case 3:
	    	break;
	    case 4:
	    	System.out.println("Digite o index do Sensor: ");
	    	int i = le.nextInt();
	    	System.out.println(listaSensor.getSensor(i));
	    	break;
	    case 5:
	    	System.out.println("Os Valores dentro da Lista na ordem cresente são: ");
	    	listaSensor.apresenta();
	    	break;
	    case 6:
	    	removeLista();
	    	break;
		case 7:
	    	System.out.println("Você saiu!!");
	    	continua = false;
	    	break;
		}
		return continua;
	}
	
	public void gerarSensores() {
		Random gerador = new Random();
		String coordenadasSensores[] = { "47o55�44�W,21o00�34�S", "47o55�42�W,21o00�35�S", "47o55�39�W,21o00�37�S",
				"47o55�44�W,21o00�39�S", "47o55�40�W,21o00�40�S", "47o55�32�W,21o00�34�S", "47o55�28�W,21o00�34�S",
				"47o55�28�W,21o00�38�S", "47o55�33�W,21o00�39�S" };
		int n = coordenadasSensores.length;
		for (int i = 0; i < n; i++) {
			int u = gerador.nextInt(UMIDADE_MAX) + UMIDADE_MIN;
			float p = PH_MAX - (PH_MIN * ((float) gerador.nextInt(10) / 10));
			float t = TEORMOS_MAX - ((float) gerador.nextInt(60));
			String tS = verificaTexturaSolo(t);
			Sensor novoSensor = new Sensor(index, coordenadasSensores[i], u, p, t, tS);
			listaSensor.insere(novoSensor);
			index+=1;
		}
	}
	
}