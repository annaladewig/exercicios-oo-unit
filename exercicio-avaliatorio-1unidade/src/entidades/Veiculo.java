package entidades;

public class Veiculo {
	
	private int id;
	private char tipoCombustivel;
	private int ultimaMarcacaoHodometro;
	private int kmPercorrido;
	private float kmPorLitro;
	
	public Veiculo() {	
	}
	
	public Veiculo(int id, char tipoCombustivel, int ultimaMarcacaoHodometro) {
		this.id = id;
		this.tipoCombustivel = tipoCombustivel;
		this.ultimaMarcacaoHodometro = ultimaMarcacaoHodometro;
		this.kmPercorrido = 0;
		this.kmPorLitro = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(char tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public int getUltimaMarcacaoHodometro() {
		return ultimaMarcacaoHodometro;
	}

	public int getKmPercorrido() {
		return kmPercorrido;
	}

	public float getKmPorLitro() {
		return kmPorLitro;
	}
	
	public void calcularKmPercorrido(int marcacaoAtual) {
		kmPercorrido = marcacaoAtual - ultimaMarcacaoHodometro;
	}
	
	public void atualizarMarcacao(int marcacaoAtual) {
		ultimaMarcacaoHodometro = marcacaoAtual;
	}
	
	public void calcularKmPorLitro(float qtdeLitro) {
		kmPorLitro = kmPercorrido / qtdeLitro;
	}

	@Override
	public String toString() {
		return "\n---Veiculo---\n"
				+ "\n ID: " + id 
				+ "\n Tipo de Combustivel: " + tipoCombustivel 
				+ "\n Última Marcação do Hodômetro: " + ultimaMarcacaoHodometro 
				+ "\n KM Percorridos: " + kmPercorrido 
				+ "\n KM por Litro: " + kmPorLitro;
	}
}
