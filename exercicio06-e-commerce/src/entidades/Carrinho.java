package entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Carrinho {

	private int id;
	private Date data;
	private int numDeItens;
	private float valorIcms;
	private float valorVenda;
	private String status;
	
	private List<Produto> produtos = new ArrayList<>();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Carrinho(int id, Date data, Produto produto) {
		this.id = id;
		this.data = data;
		this.status = "Disponível";
		inserirItem(produto);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(float valorIcms) {
		this.valorIcms = valorIcms;
	}

	public float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public int getNumDeItens() {
		return numDeItens;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void inserirItem(Produto produto) {
		produtos.add(produto);
		valorIcms += produto.calcularIcms();
		valorVenda += produto.getPrecoVenda();
		numDeItens++;
	}
	
	public float confirmarCompra() {
		for (Produto produto : produtos) {
			produto.confirmaVenda();
		}
		status = "Confirmada";
		return valorVenda;
	}

	@Override
	public String toString() {
		return "\n ID: " + id 
				+ "\n Data: " + sdf.format(data)
				+ "\n Status: " + status
				+ "\n Número de Itens: " + numDeItens 
				+ "\n Valor ICMS: R$ " + String.format("%.2f", valorIcms)
				+ "\n Valor Venda: R$ " + String.format("%.2f", valorVenda);
	}
}

