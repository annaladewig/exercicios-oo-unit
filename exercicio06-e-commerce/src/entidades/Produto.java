package entidades;

public class Produto {
	
	private int codigo;
	private String descricao;
	private float precoVenda;
	private float aliquotaIcms;
	private boolean vendido;
	
	public Produto(int codigo, String descricao, float precoVenda, float aliquotaIcms) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.precoVenda = precoVenda;
		this.aliquotaIcms = aliquotaIcms;
		this.vendido = false;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public float getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(float aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public boolean isVendido() {
		return vendido;
	}

	public float calcularIcms() {
		return precoVenda * aliquotaIcms;
	}
	
	public void confirmaVenda() {
		vendido = true;
	}

	@Override
	public String toString() {
		return "\n Código: " + codigo
				+ "\n Descricao: " + descricao 
				+ "\n Preco da Venda: R$ " + String.format("%.2f", precoVenda)
				+ "\n Aliquota ICMS: " + String.format("%.2f", aliquotaIcms);
	}
}
