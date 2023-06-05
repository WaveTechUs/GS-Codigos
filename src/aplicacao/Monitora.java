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
	public static final float TEORMOS_MAX = 70;
	public int index = 1;
	ListaSensorCrescente listaSensor = new ListaSensorCrescente();
	FilaSensor filaSensor = new FilaSensor();
	Scanner le = new Scanner(System.in);
	
	public void main() {
		boolean continua = true;
		do {
			int escolha = menu();
			continua = acao(escolha);
			if(escolha !=3 && escolha !=8)
				verificaSensores();
			
		}while(continua);
	}
	
	public void adicionarLista() {
		String coordenadas = recebeCoordenada();
		int umidade = recebeUmidade();
		float ph = recebePh();
		float teorMOS = recebeTeor();
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
			if(teorMOS > 15.0 && teorMOS < 31) {
				result = "Solo medio";
			}
			else {
				if(teorMOS > 31.0 && teorMOS < 60) {
					result = "Solo argiloso";
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
	        int escolha = 0; // Adicione uma atribuição inicial para evitar o loop infinito
	        
	        do {
	            System.out.println("=====================================");
	            System.out.println("1- Adicionar na Sensor");
	            System.out.println("2- Gerar Sensores");
	            if (!listaVazia) {
	                System.out.println("3- Verificar dados dos Sensores");
	                System.out.println("4- Aprenseta Sensor");
	                System.out.println("5- Apresenta Lista");
	                System.out.println("6- Aprenseta Fila");
	                System.out.println("7- Remover");
	            }
	            System.out.println("8- Sair");
	            System.out.println("=====================================");
	            System.out.println("Escolha um número: ");
	            
	            try {
	                escolha = le.nextInt();
	            } catch (Exception e) {
	                System.out.println("Você não digitou um número! Tente novamente!");
	                le.next();
	                escolha = 9;
	            }
	            
	            if (escolha > 1 && escolha < 8 && !listaVazia) {
	                break;
	            }
	        } while (escolha != 1 && escolha != 8 && escolha != 2);
	        
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
	    	verificaSensores();
	    	break;
	    case 4:
	    	System.out.println("Digite o index do Sensor: ");
	    	int i = le.nextInt();
	    	System.out.println(listaSensor.getSensor(i));
	    	break;
	    case 5:
	    	System.out.println("Os Valores dentro da Lista na ordem cresente de PH são: ");
	    	listaSensor.apresenta();
	    	break;
	    case 6:
	    	filaSensor.apresenta();
	    	break;
		case 7:
			System.out.println("Os Valores dentro da Fila na ordem cresente de PH são: ");
	    	removeLista();
	    	break;
		case 8:
	    	System.out.println("Você saiu!!");
	    	continua = false;
	    	break;
		}
		return continua;
	}
	
	public void gerarSensores() {
		Random gerador = new Random();
		String coordenadasSensores[] = { "47o55\'44\"W,21o00\'34\"S", "47o55\'42\"W,21o00\'35\"S", "47o55\'39\"W,21o00\'37\"S",
				"47o55\'44\"W,21o00\'39\"S", "47o55\'40\"W,21o00\'40\"S", "47o55\'32\"W,21o00\'34\"S", "47o55\'28\"W,21o00\'34\"S",
				"47o55\'28\"W,21o00\'38\"S", "47o55\'33\"W,21o00\'39\"S" };
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
	
	public String recebeCoordenada() {
		String coordenada = "";
		while(true) {
			System.out.println("Digite a Coordenada do Sensor: (Exemplo de Coordenada: 47o55\'44\"W,21o00\'34\"S");
			coordenada = le.next();
			if(coordenada.length() == "47o55�44�W,21o00�34�S".length())
				break;
			else {
				System.out.println("Você digitou errado a coordenada! Lembre-se do exemplo");
			}
				
		}
		return coordenada;
	}
	
	public int recebeUmidade() {
		int umidade = 0;
		while(true) {
			System.out.println("Digite a Umidade:");
            try {
            	umidade = le.nextInt();
            } catch (Exception e) {
                le.next();
                umidade = 0;
            }
			if(umidade > (UMIDADE_MIN-1) && umidade < (UMIDADE_MAX+1))
				break;
			else {
				System.out.println("Você digitou errado a umidade ");
				System.out.println("A umidade precisa estar nesse intervalo: " + UMIDADE_MIN + " a " + UMIDADE_MAX);
			}
				
		}
		return umidade;
	}
	
	public float recebePh() {
		float ph = 0;
		while(true) {
			System.out.println("Digite o Ph:");
            try {
            	ph = le.nextFloat();
            } catch (Exception e) {
                le.next();
                ph = 0;
            }
			if(ph > (PH_MIN-1) && ph < (PH_MAX+1))
				break;
			else {
				System.out.println("Você digitou errado o ph");
				System.out.println("O ph precisa estar nesse intervalo: " + PH_MIN + " a " + PH_MAX);
			}
				
		}
		return ph;
	}
	
	public float recebeTeor() {
		float teorMOS = 0;
		while(true) {
			System.out.println("Digite o teor MOS:");
			System.out.println("Digite o Ph:");
            try {
            	teorMOS = le.nextFloat();
            } catch (Exception e) {
                le.next();
                teorMOS = 0;
            }
			if(teorMOS > (TEORMOS_MIN-1) && teorMOS < (TEORMOS_MAX+1))
				break;
			else {
				System.out.println("Você digitou errado o teor");
				System.out.println("O teor precisa estar nesse intervalo: " + TEORMOS_MIN + " a " + TEORMOS_MAX);
			}
				
		}
		return teorMOS;
	}
	
	public void verificaSensores() {
		int i =0;
		boolean achou = false;
		System.out.println("\n\n==============ALERTA=================");
		if(listaSensor.verificaSensores()[i]!=0) {
			System.out.println("Foram adicionados esses sensores na fila do Alertas:");
			do {
				Sensor sensor =listaSensor.getSensor(listaSensor.verificaSensores()[i]);
				if(!filaSensor.achaSensor(listaSensor.verificaSensores()[i])) {
					filaSensor.enqueue(sensor);
					System.out.println(sensor.toString());
					achou = true;
				}
				i++;
			}while(listaSensor.verificaSensores()[i] != 0);
		}
		if(!achou) {
			System.out.println("Não há sensores para ser adicionados");
		}
	}
	
}