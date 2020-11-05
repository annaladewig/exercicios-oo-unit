package entidades;

public class Exame {
	static private int identificador;
	
	private int idExame;
	private String tipoExame;
	private int nivelGlicose;
	private int idPessoa;
		
	public Exame() {
	}
		
	public Exame(String tipoExame, int nivelGlicose, Pessoa pessoa) {
		this.idExame =  gerarIdExame();
		this.tipoExame = tipoExame;
		this.nivelGlicose = nivelGlicose;
		this.idPessoa = pessoa.getIdPessoa();
		pessoa.setDiabetes(obterDiagnostico());
	}

	public int getIdExame() {
		return idExame;
	}

	public void setIdExame(int idExame) {
		this.idExame = idExame;
	}

	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public int getNivelGlicose() {
		return nivelGlicose;
	}

	public void setNivelGlicose(int nivelGlicose) {
		this.nivelGlicose = nivelGlicose;
	}

	public int getIdPessoa() {
		return idPessoa;
	}
	
	public int gerarIdExame() {
		return ++identificador;
	}
	
	public String obterDiagnostico() {
		String diagnostico;
			
		if (nivelGlicose <= 99) {
			diagnostico = "Normal";
		} else if (nivelGlicose <= 125) {
			diagnostico =  "Pré-diabetes";
		} else {
			diagnostico =  "Diabetes";
		}			
		return diagnostico;
	}
}
