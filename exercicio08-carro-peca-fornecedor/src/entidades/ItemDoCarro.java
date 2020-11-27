package entidades;

public class ItemDoCarro {
	
	private int quantidade;
	private String cor;
	private float precoUnitario;
	private float valorDoItem;
	private Peca item;
	private Fornecedor fornecedor;
	
	public ItemDoCarro() {
	}
	
	public ItemDoCarro(int quantidade, String cor, float precoUnitario, Peca item, Fornecedor fornecedor) {
		this.quantidade = quantidade;
		this.cor = cor;
		this.precoUnitario = precoUnitario;
		this.item = item;
		this.fornecedor = fornecedor;
		calcularValorItem();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public float getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public float getValorDoItem() {
		return valorDoItem;
	}

	public Peca getItem() {
		return item;
	}

	public void setItem(Peca item) {
		this.item = item;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void calcularValorItem() {
		valorDoItem = quantidade * precoUnitario;
	}

	@Override
	public String toString() {
		return "\n " + item.getDescricao() 
				+ "\n Quantidade: " + quantidade 
				+ "\n Cor: " + cor 
				+ "\n Preço Unitário: R$ " + String.format("%.2f",precoUnitario)
				+ "\n Valor do Item: R$ " + String.format("%.2f",valorDoItem) 
				+ "\n Fornecedor: " + fornecedor.getRazaoSocial();
	}
}
