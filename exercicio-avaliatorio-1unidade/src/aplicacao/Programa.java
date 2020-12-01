package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Abastecimento;
import entidades.Veiculo;

public class Programa {

	static Scanner sc = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	static List<Abastecimento> listaAbastecimentos = new ArrayList<Abastecimento>();
	static Veiculo veiculoProcurado = null;

	public static void main(String[] args) throws ParseException {

		Veiculo veiculo1 = new Veiculo(001, 'G', 1500);
		Veiculo veiculo2 = new Veiculo(002, 'E', 1000);
		Veiculo veiculo3 = new Veiculo(003, 'D', 2000);
		listaVeiculos.add(veiculo1);
		listaVeiculos.add(veiculo2);
		listaVeiculos.add(veiculo3);

		boolean sair = false;
		do {
			menu();
			System.out.println("Digite a opção desejada do menu: ");
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				registrarAbastecimento();
				break;
			case 2:
				auditarAbastecimento();
				break;
			case 0:
				sair = true;
				System.out.println("Saindo...\n");
				break;
			}
		} while (sair == false);
		
		System.out.println("---Programa Encerrado---");
		sc.close();
	}

	public static void menu() {
		System.out.println("\n------REGISTRAR ABASTECIMENTO------\n");
		System.out.println(" 1 - Registrar Abastecimento");
		System.out.println(" 2 - Auditar Abastecimento");
		System.out.println(" 0 - Sair\n");
	}

	public static void registrarAbastecimento() throws ParseException {
		System.out.println("\n------CONTROLE ABASTECIMENTO------\n");		
		procurarVeiculo();
		
		System.out.print("Data (dd/mm/aaaa): ");
		Date data = sdf.parse(sc.next());
		System.out.print("Quantidade de Litros Abastecido: ");
		int qtdeLitros = sc.nextInt();
		System.out.print("Marcação Hodômetro: ");
		int marcacaoHodometro = sc.nextInt();
		System.out.println();
		Abastecimento abastecimento = new Abastecimento(data, veiculoProcurado, qtdeLitros, marcacaoHodometro);
		veiculoProcurado.calcularKmPorLitro(qtdeLitros);
		listaAbastecimentos.add(abastecimento);
		
		System.out.println("\nAbastecimento Registrado com Sucesso!\n");
	}

	public static void auditarAbastecimento() {
		
		procurarVeiculo();
		
		System.out.println(veiculoProcurado);
		System.out.println();
		
		for (Abastecimento abastecimento : listaAbastecimentos) {
			if (abastecimento.getVeiculo().getId() == veiculoProcurado.getId()) {
				System.out.println(abastecimento);
				System.out.println();
			}
		}
	}
	
	public static Veiculo procurarVeiculo() {
		veiculoProcurado = null;
		do {
			System.out.print("ID Veículo: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			for (Veiculo veiculo : listaVeiculos) {
				if (id == veiculo.getId()) {
					veiculoProcurado = veiculo;
					break;
				}
			}
			if (veiculoProcurado == null) {
				System.out.println("\nERRO: ID não existe. Tente Novamente.\n");
				continue;
			}
		} while (veiculoProcurado == null);
		return veiculoProcurado;
	}
}
