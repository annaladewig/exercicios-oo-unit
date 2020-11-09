package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Elevador;

public class Programa {

	static Scanner sc = new Scanner(System.in);
	static List<Elevador> listaElevadores = new ArrayList<>();
	static int qtdLigado = 0, idElevador, contador;
	static boolean idValido = false;
	
	public static void main(String[] args) {
		
		boolean sair = false;
		do {
			menu();
			System.out.println("Digite o código da ação desejada: ");
			int opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				cadastrar();
				break;
			case 2:
				ligarDesligar();
				break;
			case 3:
				priorizar();
				break;
			case 4:
				solicitar();
				break;
			case 0:
				sair = true;
				System.out.println("\nSaindo...\n");
				break;
			}
				
		} while(sair == false);
		System.out.println("---Programa Encerrado---");
		sc.close();
	}
	
	public static void menu() {
		System.out.println();
		System.out.println("---Sistema dos Elevadores---\n"
				+ "1 - Cadastrar\n"
				+ "2 - Ligar/Desligar\n"
				+ "3 - Priorizar\n"
				+ "4 - Solicitar\n"
				+ "0 - Sair\n"
				+ "----------------------------\n");
	}

	public static void cadastrar() {
		System.out.println("---Cadastro---\n");
		
			if (listaElevadores.size() == 2) {				
				System.out.println("\nO cadastro atingiu a quantidade máxima.\n");
			} else {
				do {
					System.out.print("Digite o ID do elevador: ");
					idElevador = sc.nextInt();
					if (listaElevadores.isEmpty() == true) {
						idValido = true;
					}
					for(Elevador elevador : listaElevadores) {
						if(elevador.getIdElevador() == idElevador) {
							System.out.println("\nERRO: ID digitado já existe.\n");
							break;
						} else {
							contador += 1;
						}
						if (contador == listaElevadores.size()) {
							idValido = true;
							break;
						}
					} 
					contador = 0;
				} while (idValido != true);
				idValido = false;
				
				System.out.print("Digite o andar atual: ");
				int andarAtual = sc.nextInt();
				
				listaElevadores.add(new Elevador(idElevador, andarAtual));
				System.out.println("\nCadastro realizado com sucesso.\n");
			}
	}
	
	public static void ligarDesligar() {
		System.out.println("---Ligar ou Desligar---\n");
		
		if (listaElevadores.isEmpty() == true) {
			System.out.println("\nNão existe elevador cadastrado.\n");
		} else {
			consultarId();
			
			for (Elevador elevador: listaElevadores) {
				if (idElevador == elevador.getIdElevador()) {
					System.out.print("O status do elevador " + elevador.getIdElevador() + " é ");
					if(elevador.isLigado() == true) {
						System.out.println("LIGADO. Deseja desligar? (s/n)");
					} else {
						System.out.println("DESLIGADO. Deseja ligar? (s/n)");
					}
					char confirmacao = sc.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						elevador.ligarDesligar();
						if(elevador.isLigado() == true) {
							elevador.setAndarAtual(0);
							qtdLigado += 1;
							System.out.print("\nO elevador " + elevador.getIdElevador() + " foi LIGADO e direcionado ao andar térreo.\n");
							if(qtdLigado == 1) {
								elevador.ativarPrioridade();
								System.out.println("Status: Prioritário\n");
							}
							break;
						} else {
							System.out.println("\nO elevador "  + elevador.getIdElevador() + " foi DESLIGADO.\n");
							break;
						}
					} 
				}
			}
		}
	}
	
	public static void priorizar() {
		System.out.println("---Priorizar---\n");
		
		if (listaElevadores.isEmpty() == true) {
			System.out.println("\nNão existe elevador cadastrado.\n");
		} else {
			consultarId();
			
			for (Elevador elevador: listaElevadores) {
				if (idElevador == elevador.getIdElevador()) {
					System.out.print("\nO status do elevador " + elevador.getIdElevador() + " é: ");
					if(elevador.isPrioritario() == true) {
						System.out.println("Prioritário.\n");
						break;
					} else {
						System.out.println("Não prioritário. Deseja priorizá-lo? (s/n)\n"
									+ "AVISO: Este será o único elevador prioritário.\n");
					}
					char confirmacao = sc.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						for (Elevador elevadorPrioridade : listaElevadores) {    
							elevadorPrioridade.desativarPrioridade();
						}
						elevador.ativarPrioridade();
						System.out.println("\nO elevador " + elevador.getIdElevador() + " foi definido como prioritário.\n");
						break;
					}
				} 
			}
		}
	}
	
	public static void solicitar() {
		System.out.println("---Solicitar---\n");
		
		if(consultarLigado() == false) {
			System.out.println("\nERRO: Não existe elevador ligado.\n");
		} else {
			System.out.print("Em qual andar você está? ");
			int andarAtualPessoa = sc.nextInt();
			System.out.print("Para qual andar você deseja ir? ");
			int andarDestino = sc.nextInt();
		
			Elevador elevadorSolicitado = calcularMenorEsforco(andarAtualPessoa);
			System.out.println("\nO elevador " + elevadorSolicitado.getIdElevador() + " irá atender à sua solicitação.\n");
		
			elevadorSolicitado.setAndarAtual(andarAtualPessoa);
			System.out.println("\nO elevador chegou ao " + elevadorSolicitado.getAndarAtual() + "º andar. Pode entrar.\n");
		
			elevadorSolicitado.setAndarAtual(andarDestino);
			System.out.println("\nVocê chegou ao " + elevadorSolicitado.getAndarAtual() + "º andar.\n");	
		}
	}
	
	public static Elevador calcularMenorEsforco(int andarDestino) {
		float esforco = 0, menorEsforco = 9999;
		Elevador menorEsforcoElevador = null, prioritario = null;
		
		for(Elevador elevador : listaElevadores) {
			if (elevador.isLigado() == true) {
				esforco = elevador.calcularEsforco(andarDestino);	
				
				if(elevador.isPrioritario() == true) {
					prioritario = elevador;
				}
				
				if(esforco == menorEsforco) {
					menorEsforcoElevador = prioritario;
				}
				
				if(esforco < menorEsforco) {
					menorEsforco = esforco;
					menorEsforcoElevador = elevador;
				} 
			}
		}
		return menorEsforcoElevador;
	}
	
	public static void consultarId() {
		do {
			System.out.print("Digite o ID do elevador: ");
			idElevador = sc.nextInt();
			for(Elevador elevador : listaElevadores) {
				if(elevador.getIdElevador() == idElevador) {
					idValido = true;
					break;
				} else {
					contador += 1;
				}
				if (contador == listaElevadores.size()) {
					System.out.println("\nERRO: ID não existe.\n");
					break;
				}
			}
			contador = 0;  
		} while (idValido != true);
		idValido = false; 
	}
	
	public static boolean consultarLigado() {
		boolean ligado = false;
		for(Elevador elevador : listaElevadores) {
			if(elevador.isLigado() == true) {
				ligado = true;
				break;
			} else {
				contador += 1;
			}
			if (contador == listaElevadores.size()) {
				ligado = false;
				break;
			}
		}
		contador = 0;  
		return ligado;
	}
}

	
