package aplicacao;

import java.util.Scanner;

import entidades.Exame;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o c�digo do exame: ");
		int codExame = sc.nextInt();
		sc.nextLine();
		System.out.print("Digite o tipo do exame: ");
		String tipoDoExame = sc.nextLine();
		System.out.print("Digite o n�vel de glicose: ");
		int nivelDeGlicose = sc.nextInt();
		
		Exame exame = new Exame(codExame, tipoDoExame, nivelDeGlicose);
		
		System.out.println("\n-------------------------------");
		System.out.printf("\nC�digo do exame : %d", exame.getIdExame());
		System.out.printf("\nTipo  do exame  : %s", exame.getTipoExame());
		System.out.printf("\nN�vel de glicose: %d", exame.getNivelGlicose());
		System.out.printf("\nDiagn�stico......: %s\n", exame.obterDiagnostico());
		System.out.println("\n-------------------------------\n");
		
		sc.close();
	}
}
