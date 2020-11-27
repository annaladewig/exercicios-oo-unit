package entidades;

import java.util.ArrayList;
import java.util.List;

public class Carro {

	private String marca;
	private String modelo;
	private int chassi;
	private int anoFabricacao;
	private float precoCusto;
	
	private List<ItemDoCarro> listaDeItens = new ArrayList<ItemDoCarro>();
	
	public Carro() {	
	}
	
	public Carro(String marca, String modelo, int chassi, int anoFabricacao) {
		this.marca = marca;
		this.modelo = modelo;
		this.chassi = chassi;
		this.anoFabricacao = anoFabricacao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getChassi() {
		return chassi;
	}

	public void setChassi(int chassi) {
		this.chassi = chassi;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public float getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(float precoCusto) {
		this.precoCusto = precoCusto;
	}

	public List<ItemDoCarro> getListaDeItens() {
		return listaDeItens;
	}

	public void adicionarItem(ItemDoCarro item) {
		listaDeItens.add(item);
		calcularPrecoCusto();
	}
	
	public void removerItem(ItemDoCarro item) {
		listaDeItens.remove(item);
		calcularPrecoCusto();
	}
	
	public void calcularPrecoCusto() {
		float soma = 0;
		for (ItemDoCarro item : listaDeItens) {
			soma += item.getValorDoItem();
		}
		float ipi = (float) (soma * 0.10);
		float custoMontagem = (float) (soma * 0.08);
		precoCusto = soma + ipi + custoMontagem;
	}

	@Override
	public String toString() {
		return "\n ---Carro--- " 
				+ "\n Marca: " + marca 
				+ "\n Modelo: " + modelo 
				+ "\n Chassi: " + chassi 
				+ "\n Ano de Fabricação: " + anoFabricacao 
				+ "\n Preço de Custo: R$ " + String.format("%.2f",precoCusto);
	}
}
