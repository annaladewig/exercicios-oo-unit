package aplicacao;

import java.util.Date;
import java.util.Scanner;

import entidades.Carrinho;
import entidades.Produto;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Carrinho carrinho = null;

		System.out.println("---CARRINHO DE COMPRAS---\n");
		System.out.println("Código do Carrinho:");
		int id = sc.nextInt();
		do {
			System.out.println("\nDIGITE OS DADOS DO PRODUTO QUE VOCÊ DESEJA INSERIR NO CARRINHO:\n");
			System.out.print("Código do Produto:");
			int codigo = sc.nextInt();
			sc.nextLine();
			System.out.print("Descrição do Produto:");
			String descricao = sc.nextLine();
			System.out.print("Preço Venda do Produto: R$");
			Float precoVenda = sc.nextFloat();
			System.out.print("Alíquota do ICMS do produto:");
			Float aliquotaIcms = sc.nextFloat();

			Produto produto = new Produto(codigo, descricao, precoVenda, aliquotaIcms);
			if (produto.isVendido() == false && carrinho == null) {
				carrinho = new Carrinho(id, new Date(), produto);
			} else if (produto.isVendido() == false && carrinho != null) {
				carrinho.inserirItem(produto);
			} else {
				System.out.println("ERRO: Produto vendido.");
			}
			System.out.print("Deseja inserir mais algum item no carrinho (S/N): ");
			char resp = sc.next().charAt(0);
			if (resp == 'S' || resp == 's') {
				continue;
			} else {
				System.out.print("Deseja confirmar a compra (S/N): ");
				char resp2 = sc.next().charAt(0);
				if (resp2 == 'S' || resp2 == 's') {
					if (carrinho.getStatus() != "Confirmada") {
						carrinho.confirmarCompra();
						System.out.println("\nCompra Realizada com sucesso\n");
					} else {
						System.out.println("ERRO: Compra já foi efetuada.");
						break;
					}
					System.out.println("-------------------------------");
					System.out.println(" Carrinho de Compras:");
					System.out.println(carrinho);
					System.out.println();
					System.out.println(" Resumo dos Produtos:");
					for (Produto produtoTeste : carrinho.getProdutos()) {
						System.out.println(produtoTeste);
					}
					System.out.println("-------------------------------");
					break;
				} else {
					carrinho = null;
					System.out.println("\nCompra não realizada.\n");
					break;
				}
			}
		} while (true);
		sc.close();
	}
}
