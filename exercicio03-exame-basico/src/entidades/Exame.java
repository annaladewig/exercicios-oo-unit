package entidades;

public class Exame {
	
	private int idExame;
	private String tipoExame;
	private int nivelGlicose;
		
	public Exame() {
	}
		
	public Exame(int idExame, String tipoExame, int nivelGlicose) {
		this.idExame = idExame;
		this.tipoExame = tipoExame;
		this.nivelGlicose = nivelGlicose;
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
