package entidades;

import java.util.Date;

public class ContratoHorista extends Contrato{

	private int horaMes;
	private float valorHora;
	
	public ContratoHorista() {
		super();
	}
	
	public ContratoHorista(Date dataInicio, Colaborador colaborador, int horaMes, float valorHora) {
		super(dataInicio, colaborador);
		this.horaMes = horaMes;
		this.valorHora = valorHora;
	}

	public int getHoraMes() {
		return horaMes;
	}

	public void setHoraMes(int horaMes) {
		this.horaMes = horaMes;
	}

	public float getValorHora() {
		return valorHora;
	}

	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	
	public float calcVencimento() {
		return valorHora * horaMes;
	}
}
