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
			System.out.println("Digite o c�digo da a��o desejada: ");
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
				System.out.println("\nO cadastro atingiu a quantidade m�xima.\n");
			} else {
				do {
					System.out.print("Digite o ID do elevador: ");
					idElevador = sc.nextInt();
					if (listaElevadores.isEmpty() == true) {
						idValido = true;
					}
					for(Elevador elevador : listaElevadores) {
						if(elevador.getIdElevador() == idElevador) {
							System.out.println("\nERRO: ID digitado j� existe.\n");
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
			System.out.println("\nN�o existe elevador cadastrado.\n");
		} else {
			consultarId();
			
			for (Elevador elevador: listaElevadores) {
				if (idElevador == elevador.getIdElevador()) {
					System.out.print("O status do elevador " + elevador.getIdElevador() + " � ");
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
							System.out.print("\nO elevador " + elevador.getIdElevador() + " foi LIGADO e direcionado ao andar t�rreo.\n");
							if(qtdLigado == 1) {
								elevador.ativarPrioridade();
								System.out.println("Status: Priorit�rio\n");
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
			System.out.println("\nN�o existe elevador cadastrado.\n");
		} else {
			consultarId();
			
			for (Elevador elevador: listaElevadores) {
				if (idElevador == elevador.getIdElevador()) {
					System.out.print("\nO status do elevador " + elevador.getIdElevador() + " �: ");
					if(elevador.isPrioritario() == true) {
						System.out.println("Priorit�rio.\n");
						break;
					} else {
						System.out.println("N�o priorit�rio. Deseja prioriz�-lo? (s/n)\n"
									+ "AVISO: Este ser� o �nico elevador priorit�rio.\n");
					}
					char confirmacao = sc.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						for (Elevador elevadorPrioridade : listaElevadores) {    
							elevadorPrioridade.desativarPrioridade();
						}
						elevador.ativarPrioridade();
						System.out.println("\nO elevador " + elevador.getIdElevador() + " foi definido como priorit�rio.\n");
						break;
					}
				} 
			}
		}
	}
	
	public static void solicitar() {
		System.out.println("---Solicitar---\n");
		
		if(consultarLigado() == false) {
			System.out.println("\nERRO: N�o existe elevador ligado.\n");
		} else {
			System.out.print("Em qual andar voc� est�? ");
			int andarAtualPessoa = sc.nextInt();
			System.out.print("Para qual andar voc� deseja ir? ");
			int andarDestino = sc.nextInt();
		
			Elevador elevadorSolicitado = calcularMenorEsforco(andarAtualPessoa);
			System.out.println("\nO elevador " + elevadorSolicitado.getIdElevador() + " ir� atender � sua solicita��o.\n");
		
			elevadorSolicitado.setAndarAtual(andarAtualPessoa);
			System.out.println("\nO elevador chegou ao " + elevadorSolicitado.getAndarAtual() + "� andar. Pode entrar.\n");
		
			elevadorSolicitado.setAndarAtual(andarDestino);
			System.out.println("\nVoc� chegou ao " + elevadorSolicitado.getAndarAtual() + "� andar.\n");	
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
					System.out.println("\nERRO: ID n�o existe.\n");
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

	
