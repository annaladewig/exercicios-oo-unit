package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Edicao;
import entidades.Revista;

public class Programa {

	static Scanner sc = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	static List<Revista> listaRevistas = new ArrayList<>();
	
	static int codigoRevista, codigoEdicao, contador;
	static boolean idValido;
	static Revista revistaEscolhida;
	static Edicao edicaoEscolhida;

	public static void main(String[] args) throws ParseException {
		
		// Reposit�rio de Revistas criadas
		Revista revista1 = new Revista(001, "Tecnologia da Informa��o");
		listaRevistas.add(revista1);
		
		// Reposit�rio de Edi��es Criadas
		Edicao edicao1 = new Edicao(01, sdf.parse("01/01/2020"), 1000, 850);
		revista1.adicionarEdicao(edicao1);
		Edicao edicao2 = new Edicao(02, sdf.parse("01/02/2020"), 1000, 750);
		revista1.adicionarEdicao(edicao2);
		Edicao edicao3 = new Edicao(03, sdf.parse("01/03/2020"), 1000, 800);
		revista1.adicionarEdicao(edicao3);
		Edicao edicao4 = new Edicao(04, sdf.parse("01/04/2020"), 1000, 700);
		revista1.adicionarEdicao(edicao4);


		boolean sair = false;
		do {
			menu();
			System.out.println("Digite o c�digo da opera��o que voc� deseja executar: ");
			int n = sc.nextInt();

			switch (n) {
			case 1:
				cadastrarRevista();
				break;
			case 2:
				cadastrarEdicao();
				break;
			case 3:
				consultarRevista();
				break;
			case 4:
				consultarEdicao();
				break;
			case 5:
				reciclarEdicao();
				break;
			case 6:
				removerRevista();
				break;
			case 7:
				removerEdicao();
				break;
			case 0:
				sair = true;
				System.out.println("Saindo...\n");
				break;
			}
		} while (sair == false);

		System.out.println("---Programa Finalizado---");
		sc.close();
	}

	public static void menu() {
		System.out.println("----------------------------\n" 
							+ "MENU:\n" 
							+ "1 - CADASTRAR REVISTA\n"
							+ "2 - CADASTRAR EDI��O\n"
							+ "3 - CONSULTAR REVISTA\n"
							+ "4 - CONSULTAR EDI��O\n"
							+ "5 - RECICLAR EDI��O\n" 
							+ "6 - REMOVER REVISTA\n" 
							+ "7 - REMOVER EDI��O\n" 
							+ "0 - SAIR\n" 
							+ "----------------------------\n");
	}

	public static void cadastrarRevista() throws ParseException {
		System.out.println("\n---CADASTRO REVISTA---");
		int codigo;
		do {
			System.out.print("C�digo: ");
			codigo = sc.nextInt();
			sc.nextLine();
			for(Revista revista : listaRevistas) {
				if(revista.getCodigo() == codigo) {
					System.out.println("\nERRO: C�digo j� cadastrado\n");
					idValido = false;
					break;
				} else {
					idValido = true;
				}
			}
		} while(idValido != true);
		idValido = false; 
		
		System.out.print("T�tulo: ");
		String titulo = sc.nextLine();
		
		Revista revista = new Revista(codigo, titulo);
		listaRevistas.add(revista);
		System.out.println("\nCadastro realizado com sucesso.\n");
	}
	
	public static void cadastrarEdicao() throws ParseException {
		System.out.println("\n---CADASTRO EDI��O---");
		consultaIdRevista();
		
		int num;
		do {
			System.out.print("N�mero da edi��o: ");
			num = sc.nextInt();
			if(revistaEscolhida.getEdicoes().isEmpty()) {
				idValido = true;
			} else {
				for(Edicao edicao : revistaEscolhida.getEdicoes()) {
					if(edicao.getNum() == num) {
						System.out.println("\nERRO: N�mero de edi��o j� cadastrado\n");
						idValido = false;
						break;
					} else {
						idValido = true;
					}
				}
			}
		} while(idValido != true);
		idValido = false; 
		
		
		Date data = null;
		do {
			try {
				System.out.print("Data (DD/MM/AAAA): ");
				data = sdf.parse(sc.next());
				break;
			} catch (ParseException e) {
				System.out.println("\nERRO: Digite apenas n�meros e no formato indicado.\n");
				continue;
			}
		} while(true);
		
		System.out.print("Tiragem: ");
		int tiragem = sc.nextInt();
		System.out.print("Vendidas: ");
		int vendidas = sc.nextInt();

		Edicao edicao = new Edicao(num, data, tiragem, vendidas);
		revistaEscolhida.adicionarEdicao(edicao);
		System.out.println("\nCadastro realizado com sucesso.\n");
	}
	
	public static void consultarRevista() {
		System.out.println("\n---CONSULTA REVISTA---");
		consultaIdRevista();
		
		System.out.println("\n============================");
		System.out.println(revistaEscolhida);
		System.out.println("============================\n");
	}	
		
	public static void consultarEdicao() {
		System.out.println("\n---CONSULTA EDI��O---");
		consultaIdRevista();
		consultaIdEdicao();
		
		for (Edicao edicao : revistaEscolhida.getEdicoes()) {
			if (edicao == edicaoEscolhida) {
				System.out.println("\n============================");
				System.out.println(edicao);
				System.out.println("============================\n");
				break;
			}	
		}			
	}

	public static void reciclarEdicao() {
		System.out.println("\n---RECICLAR EDI��O---");
		consultaIdRevista();
		consultaIdEdicao();
		
		if (edicaoEscolhida.isReciclagemAtualizada()) {
			System.out.println("\nERRO: Essa edi��o j� foi reciclada.\n");
		} else {
			revistaEscolhida.setReciclagemProduzida(revistaEscolhida.getReciclagemProduzida() + edicaoEscolhida.qtdeRecicladas());
			edicaoEscolhida.setReciclagemAtualizada(true);
			System.out.println("\nEdi��o reciclada com sucesso.\n");
			System.out.println(edicaoEscolhida.qtdeRecicladas() + " exemplares foram reciclados.");
		}
		
	}
	
	public static void removerRevista() {
		System.out.println("\n---REMOVER REVISTA---");
		consultaIdRevista();
		
		listaRevistas.remove(revistaEscolhida);
		System.out.println("\nRemo��o realizada com sucesso.\n");
		 
	}
	
	public static void removerEdicao() {
		System.out.println("\n---REMOVER EDI��O---");
		consultaIdRevista();
		consultaIdEdicao();
		
		revistaEscolhida.removerEdicao(edicaoEscolhida);
		System.out.println("\nRemo��o realizada com sucesso.\n");
		 
	}
	
	public static void consultaIdRevista() {
		do {
			System.out.print("Digite o c�digo da revista: ");
			codigoRevista = sc.nextInt();
			for(Revista revista : listaRevistas) {
				if(revista.getCodigo() == codigoRevista) {
					idValido = true;
					revistaEscolhida = revista;
					break;
				} else {
					contador += 1;
				}
				if (contador == listaRevistas.size()) {
					System.out.println("\nERRO: Esse c�digo de revista n�o existe.\n");
					break;
				}
			}
			contador = 0;  
		} while (idValido != true);
		idValido = false; 
	}
	
	public static void consultaIdEdicao() {
		do {
			System.out.print("Digite o n�mero da edi��o: ");
			codigoEdicao = sc.nextInt();
			for(Edicao edicao : revistaEscolhida.getEdicoes()) {
				if(edicao.getNum() == codigoEdicao) {
					idValido = true;
					edicaoEscolhida = edicao;
					break;
				} else {
					contador += 1;
				}
				if (contador == revistaEscolhida.getEdicoes().size()) {
					System.out.println("\nERRO: Esse n�mero de edi��o n�o existe.\n");
					break;
				}
			}
			contador = 0;  
		} while (idValido != true);
		idValido = false; 
	}
}
