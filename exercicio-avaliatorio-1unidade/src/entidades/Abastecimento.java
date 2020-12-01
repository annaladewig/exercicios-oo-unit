package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Abastecimento {
	static private int identificador;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int id;
	private Date data;
	private Veiculo veiculo;
	private float qtdeLitros;
	private float custoAbastecimento;
	private int marcacaoHodometro;
	
	public Abastecimento() {
	}
	
	public Abastecimento(Date data, Veiculo veiculo, float qtdeLitros, int marcacaoHodometro) {
		this.id = gerarIdAbastecimento();
		this.data = data;
		this.veiculo = veiculo;
		this.qtdeLitros = qtdeLitros;
		this.custoAbastecimento = calcularCustoAbastecimento();
		this.marcacaoHodometro = marcacaoHodometro;
		registrarAbastecimento();
	}

	public int getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public float getQtdeLitros() {
		return qtdeLitros;
	}

	public void setQtdeLitros(float qtdeLitros) {
		this.qtdeLitros = qtdeLitros;
	}

	public float getCustoAbastecimento() {
		return custoAbastecimento;
	}

	public void setCustoAbastecimento(float custoAbastecimento) {
		this.custoAbastecimento = custoAbastecimento;
	}

	public int getMarcacaoHodometro() {
		return marcacaoHodometro;
	}

	public void setMarcacaoHodometro(int marcacaoHodometro) {
		this.marcacaoHodometro = marcacaoHodometro;
	}
	
	public int gerarIdAbastecimento() {
		return ++identificador;
	}
	
	public float calcularCustoAbastecimento() {
		if (veiculo.getTipoCombustivel() == 'G') {
			return (float) (qtdeLitros	* 3.8);		
		} else if (veiculo.getTipoCombustivel() == 'E') {
			return (float) (qtdeLitros	* 3.4);		
		} else {
			return (float) (qtdeLitros	* 2.5);
		}
	}
	
	public void registrarAbastecimento() {
		veiculo.calcularKmPercorrido(marcacaoHodometro);
		veiculo.atualizarMarcacao(marcacaoHodometro);
	}

	@Override
	public String toString() {
		return "---Abastecimento " + id + "---" 
				+ "\n Data: " + sdf.format(data) 
				+ "\n Veículo: " + veiculo.getId() 
				+ "\n Quantidade de Litros: " + qtdeLitros
				+ "\n Custo do Abastecimento: R$ " + String.format("%.2f", custoAbastecimento) 
				+ "\n Marcação do Hodômetro: " + marcacaoHodometro;
	}
}
