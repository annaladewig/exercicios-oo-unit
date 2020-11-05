package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Exame;
import entidades.Pessoa;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		
		System.out.println("\n---CADASTRO PACIENTES---\n");
		System.out.println("Quantas pessoas voc� deseja cadastrar? ");
		int nPessoas = sc.nextInt();
		
		for (int i = 0; i < nPessoas; i++) {
	
			System.out.print("C�digo " + (i + 1) + "� do Paciente: ");
			int idPessoa = sc.nextInt();
			System.out.print("Nome: ");
			sc.nextLine();
			String nome = sc.nextLine();
			listaPessoas.add(new Pessoa(idPessoa, nome));
			System.out.println("");
		}
		
		
		System.out.println("Quantos exames voc� deseja cadastrar? ");
		int nExames = sc.nextInt();
		
		for (int i = 0; i < nExames ; i++) {
			
			System.out.println("\n---CADASTRO EXAME---\n");
			System.out.print("Digite o ID do Paciente: ");
			int idPaciente = sc.nextInt();
			sc.nextLine();
			
			Pessoa paciente = null;
			for (Pessoa pessoa : listaPessoas) {
				if (idPaciente == pessoa.getIdPessoa()) {
					paciente = pessoa; 
				}
			}
			
			System.out.print("Digite o tipo do " + (i + 1) +"� exame: ");
			String tipoExame = sc.nextLine();
			System.out.print("Digite o n�vel de glicose do " + (i + 1) +"� exame: ");
			int nivelGlicose = sc.nextInt();
		
			Exame exame = new Exame(tipoExame, nivelGlicose, paciente);
			//paciente.setDiabetes(exame.obterDiagnostico());
		}
		
		System.out.println("\n---LISTAR PACIENTES---\n");
		for (Pessoa pessoa : listaPessoas) {
			System.out.println(pessoa);
		}
		
		sc.close();
	}
}
