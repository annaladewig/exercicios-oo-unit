package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Carro;
import entidades.Fornecedor;
import entidades.ItemDoCarro;
import entidades.Peca;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Peca> listaDePecas = new ArrayList<Peca>();
		List<Fornecedor> listaDeFornecedores = new ArrayList<Fornecedor>();
		List<Carro> listaDeCarros = new ArrayList<Carro>();

		// Repositório de Peças criadas
		listaDePecas.add(new Peca(001, "Peça Grande"));
		listaDePecas.add(new Peca(002, "Peça Pequena"));
		listaDePecas.add(new Peca(003, "Peça Média"));
		listaDePecas.add(new Peca(004, "Peça Larga"));
		listaDePecas.add(new Peca(005, "Peça Estreita"));

		// Repositório de Fornecedores criados
		listaDeFornecedores.add(new Fornecedor("01.223.053/0001-22", "Empresa X"));
		listaDeFornecedores.add(new Fornecedor("01.567.045/0001-09", "Empresa Y"));
		listaDeFornecedores.add(new Fornecedor("01.123.022/0001-12", "Empresa Z"));

		// Repositório de Carros criados
		listaDeCarros.add(new Carro("Marca A", "Carro Alfa", 1234567891, 2020));
		listaDeCarros.add(new Carro("Marca B", "Carro Beta", 1234567892, 2019));
		listaDeCarros.add(new Carro("Marca C", "Carro Gama", 1234567893, 2020));
		
		do {
			System.out.println("---CADASTRO DE ITENS DO CARRO---\n");

			Carro carroEscolhido = null;
			do {
				System.out.print("Chassi do carro: ");
				int chassi = sc.nextInt();
				for (Carro carro : listaDeCarros) {
					if (chassi == carro.getChassi()) {
						carroEscolhido = carro;
						break;
					}
				}
				if (carroEscolhido == null) {
					System.out.println("\nErro: Chassi informado não existe. Tente novamente. \n");
					continue;
				}
			} while (carroEscolhido == null);

			Peca pecaEscolhida = null;
			do {
				System.out.print("Código da peça: ");
				int codPeca = sc.nextInt();
				sc.nextLine();

				for (Peca peca : listaDePecas) {
					if (codPeca == peca.getCodigo()) {
						pecaEscolhida = peca;
						break;
					}
				}
				if (pecaEscolhida == null) {
					System.out.println("\nErro: Código informado não existe. Tente novamente. \n");
					continue;
				}
			} while (pecaEscolhida == null);

			Fornecedor fornecedorEscolhido = null;
			do {
				System.out.print("CNPJ do fonecedor: ");
				String cnpj = sc.nextLine();
				for (Fornecedor fornecedor : listaDeFornecedores) {
					if (fornecedor.getCnpj().equalsIgnoreCase(cnpj)) {
						fornecedorEscolhido = fornecedor;
						break;
					}
				}
				if (fornecedorEscolhido == null) {
					System.out.println("\nErro: CNPJ informado não existe. Tente novamente. \n");
					continue;
				}
			} while (fornecedorEscolhido == null);

			System.out.print("Quantidade: ");
			int quantidade = sc.nextInt();
			sc.nextLine();
			System.out.print("Cor: ");
			String cor = sc.nextLine();
			System.out.print("Preço Unitario: ");
			float precoUnitario = sc.nextFloat();

			carroEscolhido.adicionarItem(new ItemDoCarro(quantidade, cor, precoUnitario, pecaEscolhida, fornecedorEscolhido));
			System.out.println("\nItem adicionado com sucesso.\n");

			System.out.println("\n-----------------------------\n");
			System.out.println(carroEscolhido);
			System.out.println("\n Lista de Peças: \n");
			for (ItemDoCarro item : carroEscolhido.getListaDeItens()) {
				System.out.println(item);
				System.out.println();
			}
			System.out.println("\n-----------------------------------\n");

			System.out.println("Deseja adicionar um nova peça (S/N)? ");
			char resp = sc.next().charAt(0);

			if (resp == 's' || resp == 'S') {
				continue;
			} else {
				break;
			}
		} while (true);

		System.out.println("\n---Programa Encerrado---");
		sc.close();
	}
}
