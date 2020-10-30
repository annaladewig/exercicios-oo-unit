package entidades;

public class Elevador {

	private int idElevador;
	private boolean ligado;
	private boolean prioritario;
	private int andarAtual;
	
	public Elevador() {
		
	}

	public Elevador(int idElevador, int andarAtual) {
		this.idElevador = idElevador;
		this.andarAtual = andarAtual;
		this.ligado = false;
		desativarPrioridade();
	}

	public int getIdElevador() {
		return idElevador;
	}

	public void setIdElevador(int idElevador) {
		this.idElevador = idElevador;
	}

	public boolean isLigado() {
		return ligado;
	}

	public boolean isPrioritario() {
		return prioritario;
	}

	public int getAndarAtual() {
		return andarAtual;
	}

	public void setAndarAtual(int andarAtual) {
		this.andarAtual = andarAtual;
	}
	
	public void ligarDesligar() {
		if(ligado == true) {
			ligado = false;
		} else {
			ligado = true;
		}
	}
	
	public void ativarPrioridade() {
		prioritario = true;
	}
	
	public void desativarPrioridade() {
		prioritario = false;
	}
	
	public float calcularEsforco(int andarDestino) {
		if(andarAtual >= andarDestino) {
			return andarAtual - andarDestino;
		} else {
			return (float) ((andarDestino - andarAtual) + (andarDestino - andarAtual) * 0.25);
		}
	}
}
