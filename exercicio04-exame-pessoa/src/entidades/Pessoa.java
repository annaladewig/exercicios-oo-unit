package entidades;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String diabetes;

	public Pessoa() {
		
	}
	
	public Pessoa(int idPessoa, String nome) {
		this.idPessoa = idPessoa;
		this.nome = nome;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	@Override
	public String toString() {
		return idPessoa + ", " + nome + ", " + diabetes;
	}
}
