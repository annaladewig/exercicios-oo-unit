package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Estacionamento;

public class Programa {

	static Scanner sc = new Scanner(System.in);		
	static List<Estacionamento> estacionamento = new ArrayList<>();
	
	public static void main(String[] args) {
	
	boolean sair = false;
		do {
			menu();
			System.out.println("Digite o c�digo da opera��o que voc� deseja executar: ");
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				cadastrar();
				break;
			case 2:
				consultar();
				break;
			case 3:
				ativar();
				break;
			case 4:
				desativar();
				break;
			case 5:
				sair = true;
				System.out.println("Saindo...\n");
				break;
			}
		} while (sair == false);
		
		System.out.println("---Programa Finalizado---");
		sc.close();
		
	}
	
	public static void menu() {
		System.out.println("----------------------\n" 
				+ "MENU:\n"
				+ "1 - CADASTRAR\n" 
				+ "2 - CONSULTAR\n" 
				+ "3 - ATIVAR\n" 
				+ "4 - DESATIVAR\n" 
				+ "5 - SAIR\n"
				+ "----------------------\n");
	}
	
	public static void cadastrar() {
		System.out.println("---CADASTRO---");
		System.out.print("C�digo: ");
		int codigo = sc.nextInt();
		System.out.print("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();
		System.out.print("Cidade: ");
		String cidade = sc.nextLine();
		System.out.print("Vagas: ");
		int vagas = sc.nextInt();
		System.out.print("Valor Inicial: R$ ");
		float valorInicial = sc.nextFloat();
		System.out.print("Valor Adicional: R$ ");
		float valorAdicional = sc.nextFloat();
		System.out.print("Dura��o em horas da franquia inicial: ");
		int horaFranquiaInicial = sc.nextInt();
		
		estacionamento.add(new Estacionamento(codigo, nome, cidade, vagas, valorInicial, valorAdicional, horaFranquiaInicial));
		System.out.println("\nCadastro realizado com sucesso.\n");
	}
	
	public static void consultar() {
		System.out.println("---CONSULTA---");
		System.out.println("Digite o c�digo do estacionamento que voc� deseja consultar:");
		int codConsulta = sc.nextInt();
		
		for(Estacionamento e : estacionamento) {
			if(e.getCodigo() == codConsulta) {
				System.out.println(e);
				if (e.getSituacao() == true) {
					System.out.println("Situa��o: ATIVADO\n");
				} else {
					System.out.println("Situa��o: DESATIVADO\n");
				}
			}
		}
	}
	
	public static void ativar() {
		System.out.println("---ATIVAR---");
		System.out.println("Digite o c�digo do estacionamento que voc� deseja ativar:");
		int codAtivar = sc.nextInt();
		
		for(Estacionamento e : estacionamento) {
			if(e.getCodigo() == codAtivar) {
				if(e.getSituacao() != true) {
					e.ativar();
					System.out.println("Estacionamento Ativado.\n");
					break;
				} else {
					System.out.println("ERRO: O estacionamento j� est� ativado.\n");
					break;
				}
			}
		}
	}
	
	public static void desativar() {
		System.out.println("---DESATIVAR---");
		System.out.println("Digite o c�digo do estacionamento que voc� deseja desativar:");
		int codDesativar = sc.nextInt();
		
		for(Estacionamento e : estacionamento) {
			if(e.getCodigo() == codDesativar) {
				if (e.getSituacao() != false) {
					e.desativar();
					System.out.println("Estacionamento Desativado.\n");
					break;
				} else {
					System.out.println("ERRO: O estacionamento j� est� desativado.\n");
					break;
				}
			}
		}
	}
}
