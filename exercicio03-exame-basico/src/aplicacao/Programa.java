package aplicacao;

import java.util.Scanner;

import entidades.Exame;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o código do exame: ");
		int codExame = sc.nextInt();
		sc.nextLine();
		System.out.print("Digite o tipo do exame: ");
		String tipoDoExame = sc.nextLine();
		System.out.print("Digite o nível de glicose: ");
		int nivelDeGlicose = sc.nextInt();
		
		Exame exame = new Exame(codExame, tipoDoExame, nivelDeGlicose);
		
		System.out.println("\n-------------------------------");
		System.out.printf("\nCódigo do exame : %d", exame.getIdExame());
		System.out.printf("\nTipo  do exame  : %s", exame.getTipoExame());
		System.out.printf("\nNível de glicose: %d", exame.getNivelGlicose());
		System.out.printf("\nDiagnóstico......: %s\n", exame.obterDiagnostico());
		System.out.println("\n-------------------------------\n");
		
		sc.close();
	}
}
