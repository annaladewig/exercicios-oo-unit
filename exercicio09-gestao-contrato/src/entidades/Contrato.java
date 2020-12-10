package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Contrato {
	static private int sequencial;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int id;
	private Date dataInicio;
	private Date dataEncerramento;
	private Colaborador colaborador;
	private boolean ativo;
	
	public Contrato() {
	}
	
	public Contrato(Date dataInicio, Colaborador colaborador) {
		this.id = gerarIdContrato();
		this.dataInicio = dataInicio;
		this.colaborador = colaborador;
		this.ativo = true;
		colaborador.ativar();
	}

	public int getId() {
		return id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public int gerarIdContrato() {
		return ++sequencial;
	}
	
	public void encerrarContrato() {
		ativo = false;
	}
	
	@Override
	public String toString() {
		String situacao;
		if (isAtivo()) {
			situacao = "Ativo";
		} else {
			situacao = "Inativo";
		}
		return " Contrato nº " + id 
				+ "\n Situação: " + situacao
				+ "\n Data Inicio: " + sdf.format(dataInicio)
				+ "\n Data Encerramento: " + (dataEncerramento != null ? sdf.format(dataEncerramento) : "N/A");
	}
}
