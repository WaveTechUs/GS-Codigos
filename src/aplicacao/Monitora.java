package aplicacao;

import java.util.Random;
import java.util.Scanner;

public class Monitora {
	public static final float PH_MIN = 4;
	public static final float PH_MAX = 8;
	public static final int UMIDADE_MIN = 20;
	public static final int UMIDADE_MAX = 70 - UMIDADE_MIN;

	public static void main(String[] args) {
		Scanner le = new Scanner(System.in);
		Random gerador = new Random();

		String coordenadasSensores[] = { "47o55’44”W,21o00’34”S", "47o55’42”W,21o00’35”S", "47o55’39”W,21o00’37”S",
				"47o55’44”W,21o00’39”S", "47o55’40”W,21o00’40”S", "47o55’32”W,21o00’34”S", "47o55’28”W,21o00’34”S",
				"47o55’28”W,21o00’38”S", "47o55’33”W,21o00’39”S" };
		int n = coordenadasSensores.length;

		System.out.println(" Coordenadas Iniciais");
		for (int i = 0; i < n; i++) {
			System.out.println("Sensor " + i + ": " + coordenadasSensores[i]);
		}

		/*
		 * exemplo de geação de valores aleatórios para simular alteração de medidas dos
		 * sensores
		 */
		System.out.println("\n Exemplo de valores de umidade");
		for (int i = 0; i < n; i++) {
			int u = gerador.nextInt(UMIDADE_MAX) + UMIDADE_MIN;
			System.out.println("Valor inteiro entre " + UMIDADE_MIN + " e " + UMIDADE_MAX + ": " + u);
		}
		System.out.println("\n Exemplo de valores de pH");
		for (int i = 0; i < n; i++) {
			float v = PH_MAX - (PH_MIN * ((float) gerador.nextInt(10) / 10));
			System.out.println("Valor real entre " + PH_MIN + " e " + PH_MAX + ": " + v);
		}

		/*
		 * Esse trecho de programa pode ser usado para a implementação de testes com
		 * coordenadas e valores de medidas em intervalos específicos
		 */

	}

}